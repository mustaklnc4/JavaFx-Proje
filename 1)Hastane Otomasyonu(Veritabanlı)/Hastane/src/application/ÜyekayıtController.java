
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class �yekay�tController implements Initializable{
	String sql="";
	Connection baglant�=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	public �yekay�tController() { baglant�=Baglant�.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<�yeKayitSinif> tableview;

    @FXML
    private TableColumn<�yeKayitSinif, Integer> colon_id;

    @FXML
    private TableColumn<�yeKayitSinif, String> colon_kulad;

    @FXML
    private TableColumn<�yeKayitSinif, String> colon_parola;

    @FXML
    private TableColumn<�yeKayitSinif, String> colon_mail;
    @FXML
    private TableColumn<�yeKayitSinif, String> colon_yetki;

    @FXML
    private Button btn_temizle;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_geri;

    @FXML
    private TextField txt_user;

    @FXML
    private PasswordField txt_parola;

    @FXML
    private PasswordField txt_parolatekrar;

    @FXML
    private TextField txt_mail;

    @FXML
    private ComboBox<String> combo_yetki;

    @FXML
    private AnchorPane kay�t_ancpane;
    @FXML
    private Label lbl_deger;
    
    ObservableList<�yeKayitSinif> veriler;
    
    @FXML
    void event_click(MouseEvent event) {

   	  
    }
    
    @FXML
    void event_geri(ActionEvent event) {
    
		try {
			AnchorPane anchorPane;
			anchorPane = FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
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
    public void Temizle() 
    {
    	combo_yetki.getSelectionModel().select(-1);
    	txt_mail.setText("");
    	txt_parola.setText("");
    	txt_parolatekrar.setText("");
    	txt_user.setText("");
    	lbl_deger.setText("");
    }
    
    
    @FXML
    void event_ekle(ActionEvent event) {

        String yetki=combo_yetki.getSelectionModel().getSelectedItem().trim();
    	String mesaj="";
    	Alert alert1=new Alert(AlertType.WARNING);
    	alert1.setTitle("Ekleme Yap�lamad�");
    	alert1.setHeaderText("Hata Uyarisi");
    	if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
    	{
    		lbl_deger.setText("�ifreler Farkl�");
    		mesaj+="�ifreler Ayn� Olmal�d�r\n";
    		txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
    			txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");

    	}

    if (txt_parola.getText().equals(""))
    {
    	mesaj+="�ifre Alan� Bo� Girdiniz\n";
    	txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
    	txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");	

    }

    if (txt_parola.getText().length()<3||txt_parolatekrar.getText().length()<3) 
    {
    	mesaj+="�ifreniz En az 3 karakterden fazla olmal�\n";
    	txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
    	txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");

    }

    else 
    {
    	lbl_deger.setText("�ifreler Ayn�");
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

    if (yetki.equals("Kullan�c�")||yetki.equals("Y�netici"))  
    {
    	combo_yetki.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");	
    }
    else
    {
    		mesaj+="Yetki Alan� Bo� Girdiniz\n";
    	combo_yetki.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");	

    }

    alert1.setContentText(mesaj);
    Optional<ButtonType> sonucOptional1=alert1.showAndWait();
 
  if (mesaj.length()==0) {
		
	Alert alert=new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Ekleme");
	alert.setHeaderText("Uyari");
	alert.setContentText("Eklemek istediginize Emin misini?"); 	
	Optional<ButtonType> sonucOptional=alert.showAndWait();
    	sql="insert into login(kul_ad,sifre,email,yetki) values(?,?,?,?)";

    	
    	if(sonucOptional.get()==ButtonType.OK) 
    	{	
    	try 
    	{
			sorguifadesi=baglant�.prepareStatement(sql);
			sorguifadesi.setString(1, txt_user.getText().trim());
			sorguifadesi.setString(2, txt_parola.getText().trim());
			sorguifadesi.setString(3, txt_mail.getText().trim());
			sorguifadesi.setString(4, yetki.trim());
			sorguifadesi.executeUpdate();	
		 	lbl_deger.setText("Ba�ar�l� Ekleme");
		} 
    	catch (SQLException e) {e.getMessage().toString();}
    	b�lg�ler(tableview);
    	}
    }
}

    @FXML
    void event_g�r�nt�le(ActionEvent event)
    {
    	�yeKayitSinif bilgiler=new �yeKayitSinif();
      	 bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        	Alert alert=new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("G�r�nt�leme");
        	alert.setHeaderText("Uyari");
        	alert.setContentText("G�r�nt�lemek istediginize Emin misini?");  
         	Optional<ButtonType> sonucOptional=alert.showAndWait();

          	if(sonucOptional.get()==ButtonType.OK) 
          	{

          	  combo_yetki.setValue(bilgiler.getYetki());
          	  txt_mail.setText(bilgiler.getEmail());
          	  txt_user.setText(bilgiler.getKulad());
          	  txt_parola.setText(bilgiler.getSifre());
        	  txt_parolatekrar.setText(bilgiler.getSifre());
          	  tableview.setVisible(true);
          	}
    }
    
    @FXML
    void event_temizle(ActionEvent event) {
    	Temizle();
    }
    
    
    @FXML
    void event_guncelle(ActionEvent event) {
    	�yeKayitSinif bilgiler=new �yeKayitSinif();
    	bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
    	int seciliid=bilgiler.getId();
    	int sira =tableview.getSelectionModel().getSelectedIndex();
     	tableview.getItems().set(sira, bilgiler);
    
        String yetki=combo_yetki.getSelectionModel().getSelectedItem().trim();
    	String mesaj="";
    	Alert alert1=new Alert(AlertType.WARNING);
    	alert1.setTitle("G�ncelleme Yap�lamad�");
    	alert1.setHeaderText("Hata Uyarisi");
    	if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
    	{
    		lbl_deger.setText("�ifreler Farkl�");
    		mesaj+="�ifreler Ayn� Olmal�d�r\n";
    		txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
    			txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");

    	}

    if (txt_parola.getText().equals(""))
    {
    	mesaj+="�ifre Alan� Bo� Girdiniz\n";
    	txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
    	txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");	

    }

    if (txt_parola.getText().length()<3||txt_parolatekrar.getText().length()<3) 
    {
    	mesaj+="�ifreniz En az 3 karakterden fazla olmal�\n";
    	txt_parola.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
    	txt_parolatekrar.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");

    }

    else 
    {
    	lbl_deger.setText("�ifreler Ayn�");
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

    if (yetki.equals("Kullan�c�")||yetki.equals("Y�netici"))  
    {
    	combo_yetki.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");	
    }
    else
    {
    		mesaj+="Yetki Alan� Bo� Girdiniz\n";
    	combo_yetki.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");	

    }

    alert1.setContentText(mesaj);
    Optional<ButtonType> sonucOptional1=alert1.showAndWait();
 
     	
     	if (mesaj.length()==0) {
     	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("G�ncellemek");
    	alert.setHeaderText("Uyari");
    	alert.setContentText("G�ncellemek istediginize Emin misini?");   
    	Optional<ButtonType> sonucOptional=alert.showAndWait();
	 	sql="update login set kul_ad=?, sifre=?,email=?, yetki=? where id=?"; 
    	try 
    	{
    		sorguifadesi=baglant�.prepareStatement(sql);
    		sorguifadesi.setString(1, txt_user.getText().trim());
    		sorguifadesi.setString(2, txt_parola.getText().trim());
    		sorguifadesi.setString(3, txt_mail.getText().trim());
    		sorguifadesi.setString(4, yetki);
    		sorguifadesi.setInt(5, seciliid);
    		sorguifadesi.executeUpdate();
    	 	lbl_deger.setText("Ba�ar�l� G�ncellendi");
    	} 
    	catch (SQLException e) {e.printStackTrace();}
    	b�lg�ler(tableview);
		}
  	
    }

    @FXML
    void event_sil(ActionEvent event) 
    {
     	�yeKayitSinif bilgiler=new �yeKayitSinif();
      	bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
      	int seciliId=bilgiler.getId();
		
      	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Silme");
    	alert.setHeaderText("Uyari");
    	alert.setContentText("Simek istediginize Emin misini?");
	   	TextInputDialog dialog=new TextInputDialog();
	 	dialog.setTitle("��fre");
	 	dialog.setHeaderText("�ifre Tan�mlama");
	 	dialog.setContentText("L�tfen Bir �ifre Giriniz");
	 	Optional<String>sonuc=dialog.showAndWait();
    	
	 	Optional<ButtonType> sonucOptional=alert.showAndWait();
    	if(sonucOptional.get()==ButtonType.OK) 
    	{
      	
      	sql="delete from login where id=?";

		try 
		{
			sorguifadesi=baglant�.prepareStatement(sql);
			sorguifadesi.setInt(1, seciliId);
			sorguifadesi.executeUpdate();
		} 
	    	catch (SQLException e) {e.printStackTrace();}  
	    	b�lg�ler(tableview);
	    	Temizle();
    	}
    	else 
    	{lbl_deger.setText("Silme �ptal Edildi");}
        
	}
	
   public void b�lg�ler(TableView<�yeKayitSinif> tablo) {
	   sql="select*from login";
	   
   	try {
			sorguifadesi=baglant�.prepareStatement(sql);
			getirilen=sorguifadesi.executeQuery();
			ObservableList<�yeKayitSinif> veriler=FXCollections.observableArrayList();
			while(getirilen.next()) 
			{
				veriler.add(new �yeKayitSinif(getirilen.getInt("id"),getirilen.getString("kul_ad"),
				getirilen.getString("sifre"),getirilen.getString("email"),getirilen.getString("yetki")));
			}

			colon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			colon_kulad.setCellValueFactory(new PropertyValueFactory<>("kulad"));
			colon_parola.setCellValueFactory(new PropertyValueFactory<>("sifre"));
			colon_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colon_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
			tableview.setItems(veriler);
		} 
   	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combo_yetki.getItems().addAll("Kullan�c�","Y�netici");
		colon_parola.setVisible(false);
		b�lg�ler(tableview);
		tableview.setVisible(false);
	}
}
