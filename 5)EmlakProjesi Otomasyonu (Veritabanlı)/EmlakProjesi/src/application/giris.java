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

public class giris implements Initializable {
	String sql="";
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public giris() { baglant�=sqlbag.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancpane;

    @FXML
    private TextField kullanici;

    @FXML
    private PasswordField sifre;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_login;

    @FXML
    void event_ekle(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("kayit.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane);
		stage.setScene(scene);
		ancpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void event_login(ActionEvent event) {
    	sql = "select * from giris where kul_ad=? and sifre=?";

		try {
		sorgu�fades� = baglant�.prepareStatement(sql);
		sorgu�fades�.setString(1, kullanici.getText().trim());
		sorgu�fades�.setString(2, sifre.getText().trim());
		ResultSet getirilen = sorgu�fades�.executeQuery();

		if (!getirilen.next()) 
		{  
			String mesaj="";
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("Giri� Yap�lamad�");
			alert.setHeaderText("Hata Uyarisi");
    		  
	    	if (kullanici.getText().equals("")) 
	    	{
	    		mesaj+="Kullan�c� ad� Bo� Girdiniz!\n";		
			}
	    	
	    	if (sifre.getText().equals(""))
		    {
		    		mesaj+="�ifre Alan� Bo� Girdiniz\n";
			}
		    	
	    	alert.setContentText(mesaj);
			Optional<ButtonType> sonucOptional1=alert.showAndWait();
			if(sonucOptional1.get()==ButtonType.OK) 
			{}
		}

		else 
		{
			
		    try 
		    {
					AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("anamen�.fxml"));
					Stage stage=new Stage();
					Scene scene=new Scene(anchorPane);
					stage.setScene(scene);
					ancpane.getScene().getWindow().hide();
					stage.show();
			} catch (IOException e) {e.printStackTrace();}
			
		}}	
		catch (Exception e) {e.printStackTrace();}

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
