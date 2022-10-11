package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;


public class giris_kayit{

	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public giris_kayit() { baglantý=Baglantý.Baglan(); }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_ad;

    @FXML
    private PasswordField txt_sifre;

    @FXML
    private Button btn_login;
    
    @FXML
    private Button btn_üyeol;
    
    @FXML
    private AnchorPane üye_ancPance;
    
    @FXML
    private RadioButton rb_kullanýcý;
    
    @FXML
    private RadioButton rb_kullanýcý_kayit;

    @FXML
    private RadioButton rb_yönetici;
    
    @FXML
    private ToggleGroup yetkili;

    @FXML
    private TextField txt_user;

    @FXML
    private PasswordField  txt_parola;

    @FXML
    private Button btn_ekle;
    
    @FXML
    private PasswordField  txt_parolatekrar;
   
    @FXML
    void event_ekle(ActionEvent event) {
		sql = "insert into giris(kul_ad,sifre,yetki)  values(?,?,?)";
		try {
			String mesaj="";
			Alert alert1=new Alert(AlertType.WARNING);
			alert1.setTitle("Giriþ Yapýlamadý");
			alert1.setHeaderText("Hata Uyarisi");
			
	    	if (txt_parola.getText().equals(""))
	    	{
	    		mesaj+="Þifre Alaný Boþ Girdiniz\n";

	    	}
	    	else if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
			{
					mesaj+="Þifreler Ayný Olmalýdýr\n";
			}
	    	
	    	if (txt_user.getText().equals("")) 
	    	{
	    		mesaj+="Kullanýcý adý Boþ Girdiniz!\n";
			}

	    	if (!(rb_kullanýcý_kayit.isSelected()))
			{	  		
	    		mesaj+="Yetki Alaný Boþ Girdiniz\n";
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
			sorguýfadesý.setString(1, txt_user.getText().trim());
			sorguýfadesý.setString(2, txt_parola.getText().trim());
			sorguýfadesý.setString(3, rb_kullanýcý_kayit.getText().trim());
			sorguýfadesý.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

	@FXML
	void event_login(ActionEvent event) 
	{
		
		sql = "select * from giris where kul_ad=? and sifre=? and yetki=?";
			String seciliyetkili="";
	    	if (rb_kullanýcý.isSelected())  {seciliyetkili=rb_kullanýcý.getText();}
	    	if (rb_yönetici.isSelected()) {seciliyetkili=rb_yönetici.getText();}
			try {
			sorguýfadesý = baglantý.prepareStatement(sql);
			sorguýfadesý.setString(1, txt_ad.getText().trim());
			sorguýfadesý.setString(2, txt_sifre.getText().trim());
			sorguýfadesý.setString(3, seciliyetkili.trim());
			ResultSet getirilen = sorguýfadesý.executeQuery();

			if (!getirilen.next()) 
			{  
				String mesaj="";
				Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Giriþ Yapýlamadý");
				alert.setHeaderText("Hata Uyarisi");
	    		  
		    	if (txt_ad.getText().equals("")) 
		    	{
		    		mesaj+="Kullanýcý adý Boþ Girdiniz!\n";		
				}
		    	
		    	if (txt_sifre.getText().equals(""))
			    {
			    		mesaj+="Þifre Alaný Boþ Girdiniz\n";
				}
			    	
		    	if (!(rb_kullanýcý.isSelected()||rb_yönetici.isSelected())) 
				{
		    		mesaj+="Yetkili Alaný Boþ girdiniz!\n";

				}	
		    	
		    	alert.setContentText(mesaj);
				Optional<ButtonType> sonucOptional1=alert.showAndWait();
				if(sonucOptional1.get()==ButtonType.OK) 
				{}
			}

			else 
			{
				
			if (rb_kullanýcý.isSelected()) 
			{
			    try 
			    {
						AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("kul.fxml"));
						Stage stage=new Stage();
						Scene scene=new Scene(anchorPane);
						stage.setScene(scene);
						üye_ancPance.getScene().getWindow().hide();
						stage.show();
				} catch (IOException e) {e.printStackTrace();}
			
			}
			else 
			{
		    try 
		    {
					AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("ana.fxml"));
					Stage stage=new Stage();
					Scene scene=new Scene(anchorPane);
					stage.setScene(scene);
					üye_ancPance.getScene().getWindow().hide();
					stage.show();
			} catch (IOException e) {e.printStackTrace();}
		    
			}
			}			
			}catch (Exception e) {e.printStackTrace();}

	}

}
