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


public class �yegirisController{

	String sql="";
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public �yegirisController() { baglant�=Baglant�.Baglan(); }

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
    private Button btn_�yeol;
    
    @FXML
    private AnchorPane �ye_ancPance;
    
    @FXML
    private RadioButton rb_kullan�c�;

    @FXML
    private RadioButton rb_y�netici;
    
    @FXML
    private ToggleGroup yetkili;
   
    @FXML
    void event_�yeol(ActionEvent event) {
		try {
			AnchorPane anchorPane;
			anchorPane = FXMLLoader.load(getClass().getResource("ilk�yeKaydol.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane);
			stage.setScene(scene);
			�ye_ancPance.getScene().getWindow().hide();
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
	    	if (rb_kullan�c�.isSelected())  {seciliyetkili=rb_kullan�c�.getText();}
	    	if (rb_y�netici.isSelected()) {seciliyetkili=rb_y�netici.getText();}
			try {
			sorgu�fades� = baglant�.prepareStatement(sql);
			sorgu�fades�.setString(1, txt_ad.getText().trim());
			sorgu�fades�.setString(2, txt_sifre.getText().trim());
			sorgu�fades�.setString(3, seciliyetkili.trim());
			ResultSet getirilen = sorgu�fades�.executeQuery();

			if (!getirilen.next()) 
			{  
				String mesaj="";
				Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Giri� Yap�lamad�");
				alert.setHeaderText("Hata Uyarisi");
	    		  
		    	if (txt_ad.getText().equals("")) 
		    	{
		    		mesaj+="Kullan�c� ad� Bo� Girdiniz!\n";
		    		txt_ad.setStyle("-fx-border-color:#800000");		
				}
	    		else 
	    		{
	    			txt_ad.setStyle("-fx-border-color:#116062");
	    		}
		    	
		    	if (txt_sifre.getText().equals(""))
			    {
			    		mesaj+="�ifre Alan� Bo� Girdiniz\n";
			    		txt_sifre.setStyle("-fx-border-color:#800000");	
				}
	    		else 
	    		{
	    			txt_sifre.setStyle("-fx-border-color:#116062");
	    		}
			    	
		    	if (txt_sifre.getText().length()<5) 
			    {
			    		mesaj+="�ifreniz 5 karakterden az olmamal�\n";
			        	txt_sifre.setStyle("-fx-border-color:#800000");
				}
	    		else 
	    		{
	    			txt_sifre.setStyle("-fx-border-color:#116062");
	    		}
			    
		    	if (!(rb_kullan�c�.isSelected()||rb_y�netici.isSelected())) 
				{
		    		mesaj+="Yetkili Alan� Bo� girdiniz!\n";
		        	rb_kullan�c�.setStyle("-fx-border-color:#800000");
		        	rb_y�netici.setStyle("-fx-border-color:#800000");
				}	
		    	else if (rb_y�netici.isSelected()) 
				{
		    		mesaj+="Yetkili Alan� Kontrol Ediniz!\n";
		        	rb_kullan�c�.setStyle("-fx-border-color:#800000");
		        	rb_y�netici.setStyle("-fx-border-color:#800000");
				}
				    
		    	else
		    	{
		    			mesaj+="Yetki alan� Y�netice de�ilse\n";
		    			mesaj+="�ifre Veya Kullan�c� Ad� Hatal� Olabilir\n";
				    	rb_y�netici.setStyle("-fx-border-color:#116062");
				    	rb_kullan�c�.setStyle("-fx-border-color:#116062");
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
				
			if (rb_kullan�c�.isSelected()) 
			{
			    try 
			    {
						AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("AnasayfaKullanici.fxml"));
						Stage stage=new Stage();
						Scene scene=new Scene(anchorPane);
						stage.setScene(scene);
						�ye_ancPance.getScene().getWindow().hide();
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
					�ye_ancPance.getScene().getWindow().hide();
					stage.show();
			} catch (IOException e) {e.printStackTrace();}
		    
			}
			}			
			}catch (Exception e) {e.printStackTrace();}

	}

}

