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
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public giris_kayit() { baglant�=Baglant�.Baglan(); }

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
    private RadioButton rb_kullan�c�_kayit;

    @FXML
    private RadioButton rb_y�netici;
    
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
			alert1.setTitle("Giri� Yap�lamad�");
			alert1.setHeaderText("Hata Uyarisi");
			
	    	if (txt_parola.getText().equals(""))
	    	{
	    		mesaj+="�ifre Alan� Bo� Girdiniz\n";

	    	}
	    	else if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
			{
					mesaj+="�ifreler Ayn� Olmal�d�r\n";
			}
	    	
	    	if (txt_user.getText().equals("")) 
	    	{
	    		mesaj+="Kullan�c� ad� Bo� Girdiniz!\n";
			}

	    	if (!(rb_kullan�c�_kayit.isSelected()))
			{	  		
	    		mesaj+="Yetki Alan� Bo� Girdiniz\n";
			}
        	alert1.setContentText(mesaj);
			Optional<ButtonType> sonucOptional1=alert1.showAndWait();
			if(sonucOptional1.get()==ButtonType.OK) 

			if (mesaj.length()==0) 
			{
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Kay�t");
				alert.setHeaderText("Kay�t");
				alert.setContentText("Art�k �yemizsiniz");
				Optional<ButtonType> sonucOptional=alert.showAndWait();
				if(sonucOptional.get()==ButtonType.OK) 
			sorgu�fades� = baglant�.prepareStatement(sql);
			sorgu�fades�.setString(1, txt_user.getText().trim());
			sorgu�fades�.setString(2, txt_parola.getText().trim());
			sorgu�fades�.setString(3, rb_kullan�c�_kayit.getText().trim());
			sorgu�fades�.executeUpdate();
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
				}
		    	
		    	if (txt_sifre.getText().equals(""))
			    {
			    		mesaj+="�ifre Alan� Bo� Girdiniz\n";
				}
			    	
		    	if (!(rb_kullan�c�.isSelected()||rb_y�netici.isSelected())) 
				{
		    		mesaj+="Yetkili Alan� Bo� girdiniz!\n";

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
						AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("kul.fxml"));
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
					AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("ana.fxml"));
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
