package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
    private JFXButton btn_ekle;

    @FXML
    private JFXButton btn_geri;
    @FXML
    private Label kullanıcı;
    @FXML
    private JFXTextField txt_yorum;

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
        sql = "insert into login(kul_ad,sifre,yetki,yorum)  values(?,?,?,?)";
        try {
            String mesaj="";
            Alert alert1=new Alert(AlertType.WARNING);
            alert1.setTitle("Giriş Yapılamadı");
            alert1.setHeaderText("Hata Uyarisi");

            if (txt_parola.getText().equals(""))
            { mesaj+="Şifre Alanı Boş Girdiniz\n"; }

            else if(!(txt_parola.getText().equals(txt_parolatekrar.getText())))
            { mesaj+="Şifreler Aynı Olmalıdır\n"; }

            if (txt_user.getText().equals(""))
            { mesaj+="Kullanıcı adı Boş Girdiniz!\n"; }
            alert1.setContentText("Yanlızca Kullanıcı olabilirisiniz\n\n"+mesaj);
            Optional<ButtonType> sonucOptional1=alert1.showAndWait();
            if(sonucOptional1.get()==ButtonType.OK)

                if (mesaj.length()==0)
                {
                    Alert alert=new Alert(AlertType.INFORMATION);
                    alert.setTitle("Kayıt Yapıldı");
                    alert.setHeaderText("Başarılı");
                    alert.setContentText(" Kayıt Başarılı");
                    Optional<ButtonType> sonucOptional=alert.showAndWait();
                    if(sonucOptional.get()==ButtonType.OK)
                        sorguıfadesı = baglantı.prepareStatement(sql);
                    sorguıfadesı.setString(1, txt_user.getText().trim());
                    sorguıfadesı.setString(2, txt_parola.getText().trim());
                    sorguıfadesı.setString(3, kullanıcı.getText().trim());
                    sorguıfadesı.setString(4, txt_yorum.getText().trim());
                    sorguıfadesı.executeUpdate();
                }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void initialize() {

    }
}
