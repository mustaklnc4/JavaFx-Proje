package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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


public class ÜyegirisController{

	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public ÜyegirisController() { baglantý=Baglantý.Baglan(); }

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
    private RadioButton rb_yönetici;
    
    @FXML
    private ToggleGroup yetkili;
   
    @FXML
    void event_üyeol(ActionEvent event) {
		try {
			AnchorPane anchorPane;
			anchorPane = FXMLLoader.load(getClass().getResource("ilküyeKaydol.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane);
			stage.setScene(scene);
			üye_ancPance.getScene().getWindow().hide();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	@FXML
	void event_login(ActionEvent event) 
	{
		
		sql = "select * from login where kul_ad=? and sifre=? and yetki=?";
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
		    		txt_ad.setStyle("-fx-border-color:#800000");		
				}
	    		else 
	    		{
	    			txt_ad.setStyle("-fx-border-color:#116062");
	    		}
		    	
		    	if (txt_sifre.getText().equals(""))
			    {
			    		mesaj+="Þifre Alaný Boþ Girdiniz\n";
			    		txt_sifre.setStyle("-fx-border-color:#800000");	
				}
	    		else 
	    		{
	    			txt_sifre.setStyle("-fx-border-color:#116062");
	    		}
			    	
		    	if (txt_sifre.getText().length()<5) 
			    {
			    		mesaj+="Þifreniz 5 karakterden az olmamalý\n";
			        	txt_sifre.setStyle("-fx-border-color:#800000");
				}
	    		else 
	    		{
	    			txt_sifre.setStyle("-fx-border-color:#116062");
	    		}
			    
		    	if (!(rb_kullanýcý.isSelected()||rb_yönetici.isSelected())) 
				{
		    		mesaj+="Yetkili Alaný Boþ girdiniz!\n";
		        	rb_kullanýcý.setStyle("-fx-border-color:#800000");
		        	rb_yönetici.setStyle("-fx-border-color:#800000");
				}	
		    	else if (rb_yönetici.isSelected()) 
				{
		    		mesaj+="Yetkili Alaný Kontrol Ediniz!\n";
		        	rb_kullanýcý.setStyle("-fx-border-color:#800000");
		        	rb_yönetici.setStyle("-fx-border-color:#800000");
				}
				    
		    	else
		    	{
		    			mesaj+="Yetki alaný Yönetice deðilse\n";
		    			mesaj+="Þifre Veya Kullanýcý Adý Hatalý Olabilir\n";
				    	rb_yönetici.setStyle("-fx-border-color:#116062");
				    	rb_kullanýcý.setStyle("-fx-border-color:#116062");
				    	txt_sifre.setStyle("-fx-border-color:#800000");
				    	txt_ad.setStyle("-fx-border-color:#800000");				    	
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
						AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("AnasayfaKullanici.fxml"));
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
					AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
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

