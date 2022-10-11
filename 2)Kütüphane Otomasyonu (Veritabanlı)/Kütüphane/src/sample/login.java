package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
public class login {
    String sql="";
    Connection baglantı=null;
    PreparedStatement sorguıfadesı=null;
    ResultSet getirilen=null;
    public login(){
        baglantı=baglanti.Baglan();
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXComboBox<String> combo_yetki;

    @FXML
    private AnchorPane login_ancpane;

    @FXML
    private JFXTextField txt_ad;

    @FXML
    private JFXPasswordField txt_sifre;

    @FXML
    void click_üyekayıt(ActionEvent event) {
        try
        {
            AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("üyekayit.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(anchorPane);
            stage.setScene(scene);
            login_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void btngiris(ActionEvent event) {

        sql = "select * from login where kul_ad=? and sifre=? and yetki=?";
        String yetki=combo_yetki.getSelectionModel().getSelectedItem().trim();
        try {
            sorguıfadesı = baglantı.prepareStatement(sql);
            sorguıfadesı.setString(1, txt_ad.getText().trim());
            sorguıfadesı.setString(2, txt_sifre.getText().trim());
            sorguıfadesı.setString(3, yetki.trim());
            ResultSet getirilen = sorguıfadesı.executeQuery();
            if (!getirilen.next())
            {
                String mesaj="";
                Alert alert=new Alert(AlertType.WARNING);
                alert.setTitle("Hatalı Giriş ");
                alert.setHeaderText("Hata Uyarı!!");

                if (yetki.equals(""))
                { mesaj+="Yetki Alanı Boş\n"; }

                if (txt_ad.getText().equals(""))
                { mesaj+="Kullanıcı adı Boş \n"; }

                if (txt_sifre.getText().equals(""))
                { mesaj+="Şifre Alanı Boş \n"; }


                alert.setContentText(mesaj);
                Optional<ButtonType> sonucOptional1=alert.showAndWait();
                if(sonucOptional1.get()==ButtonType.OK)
                {}
            }

            else
            { if (yetki.equals("Yönetici"))
                {
                    try {
                        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
                        Stage stage=new Stage();
                        Scene scene=new Scene(anchorPane);
                        stage.setScene(scene);
                        login_ancpane.getScene().getWindow().hide();
                        stage.show();
                    }
                    catch (IOException e) {e.printStackTrace();} }
                else
                { try {
                        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("kullanici.fxml"));
                        Stage stage=new Stage();
                        Scene scene=new Scene(anchorPane);
                        stage.setScene(scene);
                        login_ancpane.getScene().getWindow().hide();
                        stage.show();
                    } catch (IOException e) {e.printStackTrace();} } }
        }catch (Exception e) {e.printStackTrace();}
    }
    @FXML
    void initialize() {
        combo_yetki.getItems().addAll("Kullanıcı","Yönetici"); }
}
