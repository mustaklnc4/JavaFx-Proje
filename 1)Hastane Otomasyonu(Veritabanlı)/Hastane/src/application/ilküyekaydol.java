package application;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class ilk�yekaydol {

	String sql="";
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public ilk�yekaydol() { baglant�=Baglant�.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane kay�t_ancpane;

    @FXML
    private Button btn_geri;

    @FXML
    private TextField txt_user;

    @FXML
    private PasswordField  txt_parola;

    @FXML
    private TextField txt_mail;

    @FXML
    private Button btn_ekle;
    
    @FXML
    private PasswordField  txt_parolatekrar;
   
    @FXML
    private RadioButton rb_kullan�c�;
    
    @FXML
    private Label lbl_deger;

    @FXML
    void event_ekle(ActionEvent event) {
		sql = "insert into login(kul_ad,sifre,email,yetki)  values(?,?,?,?)";
		try {
			String mesaj="";
			Alert alert1=new Alert(AlertType.WARNING);
			alert1.setTitle("Giri� Yap�lamad�");
			alert1.setHeaderText("Hata Uyarisi");
			
	    	if (txt_parola.getText().equals(""))
	    	{
	    		mesaj+="�ifre Alan� Bo� Girdiniz\n";
	    		txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
	    		txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");	

	    	}
	    	else if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
				{
					lbl_deger.setText("�ifreler Farkl�");
					mesaj+="�ifreler Ayn� Olmal�d�r\n";
					txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
	     			txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
				}
	    	else if(txt_parola.getText().equals(txt_parolatekrar.getText()))
	    	{
		  		lbl_deger.setText("�ifreler Ayn�\n");
				txt_parolatekrar.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");
     			txt_parola.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");
			}

	    	else if (txt_parola.getText().length()<3||txt_parolatekrar.getText().length()<3) 
	        {
	        	mesaj+="�ifreniz En az 3 karakterden fazla olmal�\n";
	        	txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
	        	txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");

			}
			
			else 
			{
				txt_parola.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");
				txt_parolatekrar.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");	

			}
	    	
	    	if (txt_user.getText().equals("")) 
	    	{
	    		mesaj+="Kullan�c� ad� Bo� Girdiniz!\n";
	    		txt_user.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
			}
	    	else 
	    	{
				txt_user.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");
			}
	    	if (txt_mail.getText().equals("")) 
	    	{
	    		mesaj+="Email ad� Bo� Girdiniz!\n";
	    		txt_mail.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
			}
	    	else 
	    	{
				txt_mail.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");
			}

	    	if (rb_kullan�c�.isSelected())  
			{
				rb_kullan�c�.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");	
			}
			else
			{
		  		mesaj+="Yetki Alan� Bo� Girdiniz\n";
	    		rb_kullan�c�.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");	

			}
	    	
	    	
        	alert1.setContentText("�zg�n�z Sadece Kullan�c� Olabilirsiniz! \nY�netici olmak i�in Bir Y�neticinin Refarans Olmas� Laz�m\n\n"+mesaj);
			Optional<ButtonType> sonucOptional1=alert1.showAndWait();
			if(sonucOptional1.get()==ButtonType.OK) 

			if (mesaj.length()==0) 
			{
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Giri� Yap�ld�");
				alert.setHeaderText("Ba�ar�l�");
				alert.setContentText("Ba�ar�l� Bir �ekilde Kaydedildi Art�k Sizde �yemizsiniz :)");
				Optional<ButtonType> sonucOptional=alert.showAndWait();
				if(sonucOptional.get()==ButtonType.OK) 
			sorgu�fades� = baglant�.prepareStatement(sql);
			sorgu�fades�.setString(1, txt_user.getText().trim());
			sorgu�fades�.setString(2, txt_parola.getText().trim());
			sorgu�fades�.setString(3, txt_mail.getText().trim());
			sorgu�fades�.setString(4, rb_kullan�c�.getText().trim());
			sorgu�fades�.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    @FXML
    void event_geri(ActionEvent event) {
		try {
			AnchorPane anchorPane;
			anchorPane = FXMLLoader.load(getClass().getResource("�yegiris.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane);
			stage.setScene(scene);
			kay�t_ancpane.getScene().getWindow().hide();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
    
    }
}
