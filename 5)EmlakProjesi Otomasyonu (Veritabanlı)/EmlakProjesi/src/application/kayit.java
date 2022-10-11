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
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class kayit implements Initializable {
	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public kayit() { baglantý=sqlbag.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField text_kullanici;

    @FXML
    private PasswordField text_sifre;

    @FXML
    private Button btn_giris;

    @FXML
    private Button btn_kayýt;

    @FXML
    private PasswordField text_tekrar;
    
    @FXML
    private AnchorPane ancpane;

    @FXML
    void event_giris(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("giris.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane);
		stage.setScene(scene);
		ancpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void event_kayit(ActionEvent event) {
    	sql = "insert into giris(kul_ad,sifre)  values(?,?)";
    	
		try {
			String mesaj="";
			Alert alert1=new Alert(AlertType.WARNING);
			alert1.setTitle("Giriþ Yapýlamadý");
			alert1.setHeaderText("Hata Uyarisi");
			
	    	if (text_sifre.getText().equals(""))
	    	{
	    		mesaj+="Þifre Alaný Boþ Girdiniz\n";

	    	}
	    	else if(!(text_sifre.getText().equals(text_tekrar.getText()))) 
			{
					mesaj+="Þifreler Ayný Olmalýdýr\n";
			}
	    	
	    	if (text_kullanici.getText().equals("")) 
	    	{
	    		mesaj+="Kullanýcý adý Boþ Girdiniz!\n";
			}

        	alert1.setContentText(mesaj);
			Optional<ButtonType> sonucOptional1=alert1.showAndWait();
			if(sonucOptional1.get()==ButtonType.OK) 
			if (mesaj.length()==0) 
			{
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Kayýt");
				alert.setHeaderText("Kayýt");
				alert.setContentText("Artýk Üyemizsiniz");
				Optional<ButtonType> sonucOptional=alert.showAndWait();
				if(sonucOptional.get()==ButtonType.OK) 
			sorguýfadesý = baglantý.prepareStatement(sql);
			sorguýfadesý.setString(1, text_kullanici.getText().trim());
			sorguýfadesý.setString(2, text_sifre.getText().trim());
			sorguýfadesý.executeUpdate();
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		}
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
