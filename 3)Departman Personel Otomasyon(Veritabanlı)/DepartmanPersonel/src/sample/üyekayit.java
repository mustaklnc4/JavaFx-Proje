package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class üyekayit {

    String sql="";
    Connection baglantı=null;
    PreparedStatement sorguıfadesı=null;
    ResultSet getirilen=null;
    public üyekayit() { baglantı=baglanti.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane kayıt_ancpane;

    @FXML
    private JFXTextField txt_user;

    @FXML
    private JFXPasswordField txt_parola;

    @FXML
    private JFXPasswordField txt_parolatekrar;
    @FXML
    private JFXComboBox<String> combo_yetki;

    @FXML
    private JFXButton btn_ekle;

    @FXML
    private JFXButton btn_geri;

    @FXML
    private Label lbl_deger;
    @FXML
    void event_geridön(ActionEvent event) {
        try
        {
            AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(anchorPane);
            stage.setScene(scene);
            kayıt_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void event_ekle(ActionEvent event) {
        sql = "insert into login(kul_ad,sifre,yetki)  values(?,?,?)";
        try {
            String mesaj="";
            Alert alert1=new Alert(AlertType.WARNING);
            alert1.setTitle("Giriş Yapılamadı");
            alert1.setHeaderText("Hata Uyarisi");
            String s_yetki = combo_yetki.getSelectionModel().getSelectedItem().trim();
            if (txt_parola.getText().equals(""))
            {
                mesaj+="Şifre Alanı Boş Girdiniz\n";
                txt_parola.setStyle("-fx-border-color: red;   -fx-border-width:1;");
                txt_parolatekrar.setStyle("-fx-border-color: red;   -fx-border-width:1;");

            }
            else if(!(txt_parola.getText().equals(txt_parolatekrar.getText())))
            {
                lbl_deger.setText("Şifreler Farklı");
                mesaj+="Şifreler Aynı Olmalıdır\n";
                txt_parolatekrar.setStyle("-fx-border-color: red;   -fx-border-width:1;");
                txt_parola.setStyle("-fx-border-color: red;   -fx-border-width:1;");
            }
            else if(txt_parola.getText().equals(txt_parolatekrar.getText()))
            {
                lbl_deger.setText("Şifreler Aynı\n");
                txt_parolatekrar.setStyle("-fx-border-color: turquoise;   -fx-border-width:1;");
                txt_parola.setStyle("-fx-border-color: turquoise;   -fx-border-width:1;");
            }

            else if (txt_parola.getText().length()<3||txt_parolatekrar.getText().length()<3)
            {
                mesaj+="Şifreniz En az 3 karakterden fazla olmalı\n";
                txt_parola.setStyle("-fx-border-color: red;   -fx-border-width:1;");
                txt_parolatekrar.setStyle("-fx-border-color: red;   -fx-border-width:1;");
            }

            else
            {
                txt_parola.setStyle("-fx-border-color: turquoise;   -fx-border-width:1;");
                txt_parolatekrar.setStyle("-fx-border-color: turquoise;   -fx-border-width:1;");
            }

            if (txt_user.getText().equals(""))
            {
                mesaj+="Kullanıcı adı Boş Girdiniz!\n";
                txt_user.setStyle("-fx-border-color: red;   -fx-border-width:1;");
            }
            else
            {
                txt_user.setStyle("-fx-border-color: turquoise;   -fx-border-width:1;");
            }

            if (s_yetki.equals("Kullanıcı"))
            {
            }
            else
            {
                mesaj+="Yetki Alanı Boş Girdiniz\n";
            }
            alert1.setContentText("\nYöneticinin Olamazsın\n"+mesaj);
            Optional<ButtonType> sonucOptional1=alert1.showAndWait();
            if(sonucOptional1.get()==ButtonType.OK)

                if (mesaj.length()==0)
                {
                    Alert alert=new Alert(AlertType.INFORMATION);
                    alert.setTitle("Giriş Yapıldı");
                    alert.setHeaderText("Başarılı");
                    alert.setContentText("Başarılı Eklemi oldu");
                    Optional<ButtonType> sonucOptional=alert.showAndWait();
                    if(sonucOptional.get()==ButtonType.OK)
                        sorguıfadesı = baglantı.prepareStatement(sql);
                    sorguıfadesı.setString(1, txt_user.getText().trim());
                    sorguıfadesı.setString(2, txt_parola.getText().trim());
                    sorguıfadesı.setString(3, s_yetki.trim());
                    sorguıfadesı.executeUpdate();
                }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void initialize() {
        combo_yetki.getItems().addAll("Kullanıcı","");
    }
}
