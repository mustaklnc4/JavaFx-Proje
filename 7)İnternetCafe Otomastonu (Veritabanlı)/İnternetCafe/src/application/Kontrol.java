
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

public class Kontrol implements Initializable{
	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	public Kontrol() { baglantý=Baglantý.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Sinif_Kayit> tableview;

    @FXML
    private TableColumn<Sinif_Kayit, Integer> colon_id;

    @FXML
    private TableColumn<Sinif_Kayit, String> colon_kulad;

    @FXML
    private TableColumn<Sinif_Kayit, String> colon_parola;

    @FXML
    private TableColumn<Sinif_Kayit, String> colon_mail;
    @FXML
    private TableColumn<Sinif_Kayit, String> colon_yetki;

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
    private AnchorPane kayýt_ancpane;
    @FXML
    private Label lbl_deger;
    
    ObservableList<Sinif_Kayit> veriler;
    
    @FXML
    void event_click(MouseEvent event) {

    	Sinif_Kayit bilgiler=new Sinif_Kayit();
     	 bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
       	Alert alert=new Alert(AlertType.CONFIRMATION);
       	alert.setTitle("Görüntüleme");
       	alert.setHeaderText("Uyari");
       	alert.setContentText("Görüntülemek istediginize Emin misini?");  
        	Optional<ButtonType> sonucOptional=alert.showAndWait();

         	if(sonucOptional.get()==ButtonType.OK) 
         	{
         	  combo_yetki.setValue(bilgiler.getYetki());
         	  txt_mail.setText(bilgiler.getEmail());
         	  txt_user.setText(bilgiler.getKulad());
         	  txt_parola.setText(bilgiler.getSifre());
         	  txt_parolatekrar.setText(bilgiler.getSifre());
         	}
    }
    
    @FXML
    void event_geri(ActionEvent event) {
    
		try {
			AnchorPane anchorPane;
			anchorPane = FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane);
			stage.setScene(scene);
			kayýt_ancpane.getScene().getWindow().hide();
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
    	alert1.setTitle("Ekleme Yapýlamadý");
    	alert1.setHeaderText("Hata Uyarisi");
    	if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
    	{
    		lbl_deger.setText("Þifreler Farklý");
    		mesaj+="Þifreler Ayný Olmalýdýr\n";
    	}

    if (txt_parola.getText().equals(""))
    {
    	mesaj+="Þifre Alaný Boþ Girdiniz\n";
    }

    else 
    {
    	lbl_deger.setText("Þifreler Ayný");
    }

    if (txt_user.getText().equals("")) 
    {
    	mesaj+="Kullanýcý adý Boþ Girdiniz!\n";
    }
    
    if (txt_mail.getText().equals("")) 
    {
    	mesaj+="Email adý Boþ Girdiniz!\n";
    }

    if (yetki.equals("Kullanýcý")||yetki.equals("Yönetici"))  
    {
    }
    else
    {
 		mesaj+="Yetki Alaný Boþ Girdiniz\n";
    }

    alert1.setContentText(mesaj);
    Optional<ButtonType> sonucOptional1=alert1.showAndWait();
 
  if (mesaj.length()==0) {
		
	Alert alert=new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Ekleme");
	alert.setHeaderText("Uyari");
	alert.setContentText("Eklemek istediginize Emin misini?"); 	
	Optional<ButtonType> sonucOptional=alert.showAndWait();
    	sql="insert into login(kul_ad,sifre,mail,yetki) values(?,?,?,?)";

    	
    	if(sonucOptional.get()==ButtonType.OK) 
    	{	
    	try 
    	{
			sorguifadesi=baglantý.prepareStatement(sql);
			sorguifadesi.setString(1, txt_user.getText().trim());
			sorguifadesi.setString(2, txt_parola.getText().trim());
			sorguifadesi.setString(3, txt_mail.getText().trim());
			sorguifadesi.setString(4, yetki.trim());
			sorguifadesi.executeUpdate();	
		 	lbl_deger.setText("Baþarýlý Ekleme");
		} 
    	catch (SQLException e) {e.getMessage().toString();}
    	býlgýler(tableview);
    	}
    }
}

    @FXML
    void event_görüntüle(ActionEvent event)
    {
          colon_parola.setVisible(true);
    }
    
    @FXML
    void event_temizle(ActionEvent event) {
    	Temizle();
        colon_parola.setVisible(false);
    }
    
    
    @FXML
    void event_guncelle(ActionEvent event) {
    	Sinif_Kayit bilgiler=new Sinif_Kayit();
    	bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
    	int seciliid=bilgiler.getId();
    	int sira =tableview.getSelectionModel().getSelectedIndex();
     	tableview.getItems().set(sira, bilgiler);
    
        String yetki=combo_yetki.getSelectionModel().getSelectedItem().trim();
    	String mesaj="";
    	Alert alert1=new Alert(AlertType.WARNING);
    	alert1.setTitle("Güncelleme Yapýlamadý");
    	alert1.setHeaderText("Hata Uyarisi");
    	if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) 
    	{
    		lbl_deger.setText("Þifreler Farklý");
    		mesaj+="Þifreler Ayný Olmalýdýr\n";
    	}

    if (txt_parola.getText().equals(""))
    {
    	mesaj+="Þifre Alaný Boþ Girdiniz\n";
    }

    else 
    {
    	lbl_deger.setText("Þifreler Ayný");
    }

    if (txt_user.getText().equals("")) 
    {
    	mesaj+="Kullanýcý adý Boþ Girdiniz!\n";
    }

    if (txt_mail.getText().equals("")) 
    {
    	mesaj+="Email adý Boþ Girdiniz!\n";
    }

    if (yetki.equals("Kullanýcý")||yetki.equals("Yönetici"))  
    {	
    }
    else
    {
    		mesaj+="Yetki Alaný Boþ Girdiniz\n";
    }

    alert1.setContentText(mesaj);
    Optional<ButtonType> sonucOptional1=alert1.showAndWait();
 
     	
     	if (mesaj.length()==0) {
     	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Güncellemek");
    	alert.setHeaderText("Uyari");
    	alert.setContentText("Güncellemek istediginize Emin misini?");   
    	Optional<ButtonType> sonucOptional=alert.showAndWait();
	 	sql="update login set kul_ad=?, sifre=?,mail=?, yetki=? where id=?"; 
    	try 
    	{
    		sorguifadesi=baglantý.prepareStatement(sql);
    		sorguifadesi.setString(1, txt_user.getText().trim());
    		sorguifadesi.setString(2, txt_parola.getText().trim());
    		sorguifadesi.setString(3, txt_mail.getText().trim());
    		sorguifadesi.setString(4, yetki);
    		sorguifadesi.setInt(5, seciliid);
    		sorguifadesi.executeUpdate();
    	 	lbl_deger.setText("Baþarýlý Güncellendi");
    	} 
    	catch (SQLException e) {e.printStackTrace();}
    	býlgýler(tableview);
		}
  	
    }

    @FXML
    void event_sil(ActionEvent event) 
    {
     	Sinif_Kayit bilgiler=new Sinif_Kayit();
      	bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
      	int seciliId=bilgiler.getId();
		
      	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Silme");
    	alert.setHeaderText("Uyari");
    	alert.setContentText("Simek istediginize Emin misini?");
	   	TextInputDialog dialog=new TextInputDialog();
	 	dialog.setTitle("ÞÝfre");
	 	dialog.setHeaderText("Þifre Tanýmlama");
	 	dialog.setContentText("Lütfen Bir Þifre Giriniz");
	 	Optional<String>sonuc=dialog.showAndWait();
    	
	 	Optional<ButtonType> sonucOptional=alert.showAndWait();
    	if(sonucOptional.get()==ButtonType.OK) 
    	{
      	
      	sql="delete from login where id=?";

		try 
		{
			sorguifadesi=baglantý.prepareStatement(sql);
			sorguifadesi.setInt(1, seciliId);
			sorguifadesi.executeUpdate();
		} 
	    	catch (SQLException e) {e.printStackTrace();}  
	    	býlgýler(tableview);
	    	Temizle();
    	}
    	else 
    	{lbl_deger.setText("Silme Ýptal Edildi");}
        
	}
	
   public void býlgýler(TableView<Sinif_Kayit> tablo) {
	   sql="select*from login";
	   
   	try {
			sorguifadesi=baglantý.prepareStatement(sql);
			getirilen=sorguifadesi.executeQuery();
			ObservableList<Sinif_Kayit> veriler=FXCollections.observableArrayList();
			while(getirilen.next()) 
			{
				veriler.add(new Sinif_Kayit(getirilen.getInt("id"),getirilen.getString("kul_ad"),
				getirilen.getString("sifre"),getirilen.getString("mail"),getirilen.getString("yetki")));
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
		combo_yetki.getItems().addAll("Kullanýcý","Yönetici");
		colon_parola.setVisible(false);
		býlgýler(tableview);
	}
}
