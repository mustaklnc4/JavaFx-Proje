package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SampleController implements Initializable {

	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public SampleController() { baglantý=Baglantý.Baglan(); }
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kayýtlar> tableviev;

    @FXML
    private AnchorPane paneSample;

    @FXML
    private TableColumn<Kayýtlar, Integer> ID;

    @FXML
    private TableColumn<Kayýtlar, String> colon_Adi;

    @FXML
    private TableColumn<Kayýtlar, String> colon_tc;

    @FXML
    private TableColumn<Kayýtlar, String> colon_cinsiyet;

    @FXML
    private TableColumn<Kayýtlar, String> colon_kangrubu;

    @FXML
    private TableColumn<Kayýtlar, String> colon_poliklinik;

    @FXML
    private TableColumn<Kayýtlar, String> colon_hastatürü;

    @FXML
    private TableColumn<Kayýtlar, String> colon_sehir;

    @FXML
    private TableColumn<Kayýtlar, String> colon_adres;

    @FXML
    private TableColumn<Kayýtlar, String> colon_sigorta;

    @FXML
    private TableColumn<Kayýtlar, Double> colon_ates;

    @FXML
    private TableColumn<Kayýtlar, String> colon_corona;

    @FXML
    private TableColumn<Kayýtlar, String> colon_tarih;

    @FXML
    private TableColumn<Kayýtlar, String> colon_doktor;

    @FXML
    private ComboBox<String> combo_poliklinik;

    @FXML
    private ComboBox<String> combo_hasta_türü;

    @FXML
    private ComboBox<String> combo_sehir;

    @FXML
    private ComboBox<String> combo_sigorta;

    @FXML
    private ComboBox<String> combo_doktor;

    @FXML
    private RadioButton r_0rh_p;

    @FXML
    private ToggleGroup kan_grubu;

    @FXML
    private RadioButton r_a_rh_p;

    @FXML
    private RadioButton r_b_rh_p;

    @FXML
    private RadioButton r_ab_rh_p;

    @FXML
    private RadioButton r_0rh_n;

    @FXML
    private RadioButton r_a_rh_n;

    @FXML
    private RadioButton r_b_rh_n;

    @FXML
    private RadioButton r_ab_rh_n;

    @FXML
    private RadioButton rd_var;

    @FXML
    private ToggleGroup durum;

    @FXML
    private RadioButton rd_yok;

    @FXML
    private RadioButton rd_kadýn;

    @FXML
    private ToggleGroup cinsiyet_durum;

    @FXML
    private RadioButton rd_erkek;

    @FXML
    private Slider s_ates;

    @FXML
    private Label lbl_ates;

    @FXML
    private Label lbl_ates_ort;
    
    @FXML
    private TextField txt_ad_soyad;

    @FXML
    private TextField txt_tc;
    
    @FXML
    private TextField  txt_hasta_türü;

    @FXML
    private DatePicker data_tarih;

    @FXML
    private Label lbl_tarih;
    
    @FXML
    private TextArea txt_adres;

    @FXML
    private Label lbl_þehir;

    @FXML
    private Label lbl_cinsiyet;

    @FXML
    private Button btn_geridön;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sorgula;
    
    @FXML
    private Button btn_taburcu;

    @FXML
    private Button btn_güncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_temizle;

    @FXML
    private Label lbl_corona;
    ObservableList<Kayýtlar> veriler;
    @FXML
    void event_geridön(ActionEvent event) {
    	try {
			AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane,1100,710);
			stage.setScene(scene);
			paneSample.getScene().getWindow().hide();
			stage.show();
		} catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void event_getir(ActionEvent event) {
    	if (combo_hasta_türü.getSelectionModel().getSelectedItem().equals("Diger")) 
    	{		txt_hasta_türü.setVisible(true);	}
    }
    
    @FXML
    void event_güncelle(ActionEvent event) 
    {
       	Kayýtlar bilgiler=new Kayýtlar();
     	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
     	 int seciliId=bilgiler.getId();
     	 
     	 
  	Alert alert=new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Guncelleme");
	alert.setHeaderText("Uyari");
	alert.setContentText("Guncellemek istediginize Emin misini?");
	
	Optional<ButtonType> sonucOptional=alert.showAndWait();
	if(sonucOptional.get()==ButtonType.OK) 
	{
		int sira =tableviev.getSelectionModel().getSelectedIndex();
    	tableviev.getItems().set(sira, bilgiler);
      	sql="update üye_kayýt set hasta_ad_soyad=?, hasta_tc=?,cinsiyet=?, kan_grubu=? , poliklinik=?, hasta_cesidi=?, il=?,adres=?,sigorta_durumu=?,hasta_ates=?,corona=?,tarih=?,doktor=? where Id=?";
       	
       	String poliklinik=combo_poliklinik.getSelectionModel().getSelectedItem().trim();
       	String hastatürü=combo_hasta_türü.getSelectionModel().getSelectedItem().trim();
       	String þehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
       	String sigortadurum=combo_sigorta.getSelectionModel().getSelectedItem().trim();
       	String doktor=combo_doktor.getSelectionModel().getSelectedItem().trim();
    	String secilicinsiyet="";
    	if (rd_erkek.isSelected())  {secilicinsiyet=rd_erkek.getText();}
    	if (rd_kadýn.isSelected()) {secilicinsiyet=rd_kadýn.getText();}

    	String secilicorona="";
    	if (rd_var.isSelected())  {secilicorona=rd_var.getText();}
    	if (rd_yok.isSelected()) {secilicorona=rd_yok.getText();}
    	
    	Double ates=Double.parseDouble(lbl_ates.getText());
    	
    	String tür="";
    	if (hastatürü.equals("Diger")) 
    	{
			tür=txt_hasta_türü.getText().trim();
		}
    	else 
    	{
			tür=combo_hasta_türü.getSelectionModel().getSelectedItem().trim();
		}
    	String kan="";
    	if (r_0rh_n.isSelected()) {kan=r_0rh_n.getText();}
    	if (r_0rh_p.isSelected()) {kan=r_0rh_p.getText();}
    	if (r_a_rh_n.isSelected()) {kan=r_a_rh_n.getText();}
    	if (r_a_rh_p.isSelected()) {kan=r_a_rh_p.getText();}
    	if (r_ab_rh_n.isSelected()) {kan=r_ab_rh_n.getText();}
    	if (r_ab_rh_p.isSelected()) {kan=r_ab_rh_p.getText();}
    	if (r_b_rh_n.isSelected()) {kan=r_b_rh_n.getText();}
    	if (r_b_rh_p.isSelected()) {kan=r_b_rh_p.getText();}
    	
    	String secilitarih=String.valueOf(data_tarih.getValue());
    	
    	try 
    	{
    		sorguýfadesý=baglantý.prepareStatement(sql);
    		sorguýfadesý.setString(1, txt_ad_soyad.getText().trim());
    		sorguýfadesý.setString(2, txt_tc.getText().trim());
    		sorguýfadesý.setString(3, secilicinsiyet.trim());
    		sorguýfadesý.setString(4, kan.trim());
    		sorguýfadesý.setString(5, poliklinik.trim());
    		sorguýfadesý.setString(6, tür.trim());
    		sorguýfadesý.setString(7, þehir.trim());
    		sorguýfadesý.setString(8, txt_adres.getText().trim());
    		sorguýfadesý.setString(9, sigortadurum.trim());
    		sorguýfadesý.setDouble(10, ates);
    		sorguýfadesý.setString(11, secilicorona.trim());
    		sorguýfadesý.setString(12, secilitarih.trim());
    		sorguýfadesý.setString(13, doktor.trim());
    		sorguýfadesý.setInt(14, seciliId);
    		sorguýfadesý.executeUpdate();		
    	} 
    	catch (SQLException e) {e.printStackTrace();}
    	alert.setContentText("Kayit Guncellendi!!!");
    	Temizle();
	}
	else 
	{
		alert.setContentText("Kayit Guncellenmedi!!!");
	}
	býlgýler(tableviev);
    }

    
    
    @FXML
    void click_tarih(ActionEvent event) {
    	LocalDate ld=data_tarih.getValue();
    	lbl_tarih.setText(ld.toString());
    }
    
    @FXML
    void event_ekle(ActionEvent event)
    {
      	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
       	 mesAlert.setTitle("Ekleme Ýþlemi Mesaj");
       	 mesAlert.setHeaderText("Ekleme Uyarýsý");
       	 mesAlert.setContentText("Eklemek istediginize emin misiniz?");
       	 Optional<ButtonType> result=mesAlert.showAndWait();
       	 if (result.get()==ButtonType.OK) 
       	 {
    	sql="insert into üye_kayýt(hasta_ad_soyad,hasta_tc,cinsiyet,kan_grubu,poliklinik,hasta_cesidi,il,adres,sigorta_durumu,hasta_ates,corona,tarih,doktor) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
       	 	
    	String poliklinik=combo_poliklinik.getSelectionModel().getSelectedItem().trim();
       	String hastatürü=combo_hasta_türü.getSelectionModel().getSelectedItem().trim();
       	String þehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
       	String sigortadurum=combo_sigorta.getSelectionModel().getSelectedItem().trim();
       	String doktor=combo_doktor.getSelectionModel().getSelectedItem().trim();
    	String secilicinsiyet="";
    	if (rd_erkek.isSelected())  {secilicinsiyet=rd_erkek.getText();}
    	if (rd_kadýn.isSelected()) {secilicinsiyet=rd_kadýn.getText();}

    	String secilicorona="";
    	if (rd_var.isSelected())  {secilicorona=rd_var.getText();}
    	if (rd_yok.isSelected()) {secilicorona=rd_yok.getText();}
    	String tür="";
    	if (hastatürü.equals("Diger")) 
    	{
			tür=txt_hasta_türü.getText().trim();
		}
    	else 
    	{
			tür=combo_hasta_türü.getSelectionModel().getSelectedItem().trim();
		}
    	Double ates=Double.parseDouble(lbl_ates.getText());
    	
    	String kan="";
    	if (r_0rh_n.isSelected()) {kan=r_0rh_n.getText();}
    	if (r_0rh_p.isSelected()) {kan=r_0rh_p.getText();}
    	if (r_a_rh_n.isSelected()) {kan=r_a_rh_n.getText();}
    	if (r_a_rh_p.isSelected()) {kan=r_a_rh_p.getText();}
    	if (r_ab_rh_n.isSelected()) {kan=r_ab_rh_n.getText();}
    	if (r_ab_rh_p.isSelected()) {kan=r_ab_rh_p.getText();}
    	if (r_b_rh_n.isSelected()) {kan=r_b_rh_n.getText();}
    	if (r_b_rh_p.isSelected()) {kan=r_b_rh_p.getText();}
    	
    	String secilitarih=String.valueOf(data_tarih.getValue());
    	String mesaj="";
    	if (txt_tc.getText().length()<11) 
        {
        	mesaj+="+";
        	txt_tc.setStyle("-fx-border-color: #800000;   -fx-border-width: 2;");
        }
		if (mesaj.length()==0) 
		{
			txt_tc.setStyle("-fx-border-color: #116062;   -fx-border-width: 2;");
    	try 
    	{
    		sorguýfadesý=baglantý.prepareStatement(sql);
    		sorguýfadesý.setString(1, txt_ad_soyad.getText().trim());
    		sorguýfadesý.setString(2, txt_tc.getText().trim());
    		sorguýfadesý.setString(3, secilicinsiyet.trim());
    		sorguýfadesý.setString(4, kan.trim());
    		sorguýfadesý.setString(5, poliklinik.trim());
    		sorguýfadesý.setString(6, tür.trim());
    		sorguýfadesý.setString(7, þehir.trim());
    		sorguýfadesý.setString(8, txt_adres.getText().trim());
    		sorguýfadesý.setString(9, sigortadurum.trim());
    		sorguýfadesý.setDouble(10, ates);
    		sorguýfadesý.setString(11, secilicorona.trim());
    		sorguýfadesý.setString(12, secilitarih.trim());
    		sorguýfadesý.setString(13, doktor.trim());
    		sorguýfadesý.executeUpdate();		
    	
		} 
    	catch (SQLException e) {e.printStackTrace();}
    	mesAlert.setContentText("Ekleme iþlemi Baþarýlý bir Þekilde Gerçekleþti");	
    	
    	Temizle();
       	 }
       	 }
       	
       	 if (result.get()==ButtonType.CANCEL)
       	 {
    		 mesAlert.setContentText("Ekleme iþlemi iptal edildi");	 
    	 }
       	býlgýler(tableviev);
    }

    
    @FXML
    void event_taburcu(ActionEvent event) {
       	Kayýtlar bilgiler=new Kayýtlar();
      	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
   	
      	 
      	 int seciliId=bilgiler.getId();
      	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
   	 mesAlert.setTitle("Kalýcý Silme Ýþlemi Mesaj");
   	 mesAlert.setHeaderText("Kalýcý Silme Uyarýsý");
   	 mesAlert.setContentText("Kalýcý Silmeye emin misiniz?");
   	 Optional<ButtonType> result=mesAlert.showAndWait();
   	 if (result.get()==ButtonType.OK) {
   		 

 		sql="delete from üye_kayýt where Id=?";
 	    	try {
 					sorguýfadesý=baglantý.prepareStatement(sql);
 					sorguýfadesý.setInt(1, seciliId);
 					sorguýfadesý.executeUpdate();
 				} catch (SQLException e) {e.printStackTrace();}  
 	        Temizle();
 	}
 	 
   	 else { mesAlert.setContentText("Kalýcý Silme islemi iptal oldu"); }
 	 	býlgýler(tableviev);
    }
    
    @FXML
    void event_sil(ActionEvent event)
    {
   	Kayýtlar bilgiler=new Kayýtlar();
   	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
	
   	 
   	 int seciliId=bilgiler.getId();
   	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
	 mesAlert.setTitle("Taburcu Ýþlemi Mesaj");
	 mesAlert.setHeaderText("Taburcu Uyarýsý");
	 mesAlert.setContentText("Taburcu Etmeye emin misiniz?");
	 Optional<ButtonType> result=mesAlert.showAndWait();
	 if (result.get()==ButtonType.OK) {
		 sql="insert into taburcu(hasta_ad_soyad,hasta_tc,cinsiyet,kan_grubu,poliklinik,hasta_cesidi,il,adres,sigorta_durumu,hasta_ates,corona,tarih,doktor) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
	    	String poliklinik=combo_poliklinik.getSelectionModel().getSelectedItem().trim();
	       	String hastatürü=combo_hasta_türü.getSelectionModel().getSelectedItem().trim();
	       	String þehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
	       	String sigortadurum=combo_sigorta.getSelectionModel().getSelectedItem().trim();
	       	String doktor=combo_doktor.getSelectionModel().getSelectedItem().trim();
	    	String secilicinsiyet="";
	    	if (rd_erkek.isSelected())  {secilicinsiyet=rd_erkek.getText();}
	    	if (rd_kadýn.isSelected()) {secilicinsiyet=rd_kadýn.getText();}

	    	String secilicorona="";
	    	if (rd_var.isSelected())  {secilicorona=rd_var.getText();}
	    	if (rd_yok.isSelected()) {secilicorona=rd_yok.getText();}
	    	
	    	Double ates=Double.parseDouble(lbl_ates.getText());
	    	
	    	String kan="";
	    	if (r_0rh_n.isSelected()) {kan=r_0rh_n.getText();}
	    	if (r_0rh_p.isSelected()) {kan=r_0rh_p.getText();}
	    	if (r_a_rh_n.isSelected()) {kan=r_a_rh_n.getText();}
	    	if (r_a_rh_p.isSelected()) {kan=r_a_rh_p.getText();}
	    	if (r_ab_rh_n.isSelected()) {kan=r_ab_rh_n.getText();}
	    	if (r_ab_rh_p.isSelected()) {kan=r_ab_rh_p.getText();}
	    	if (r_b_rh_n.isSelected()) {kan=r_b_rh_n.getText();}
	    	if (r_b_rh_p.isSelected()) {kan=r_b_rh_p.getText();}
	    	
	    	String secilitarih=String.valueOf(data_tarih.getValue());
	    	
	    	try 
	    	{
	    		sorguýfadesý=baglantý.prepareStatement(sql);
	    		sorguýfadesý.setString(1, txt_ad_soyad.getText().trim());
	    		sorguýfadesý.setString(2, txt_tc.getText().trim());
	    		sorguýfadesý.setString(3, secilicinsiyet.trim());
	    		sorguýfadesý.setString(4, kan.trim());
	    		sorguýfadesý.setString(5, poliklinik.trim());
	    		sorguýfadesý.setString(6, hastatürü.trim());
	    		sorguýfadesý.setString(7, þehir.trim());
	    		sorguýfadesý.setString(8, txt_adres.getText().trim());
	    		sorguýfadesý.setString(9, sigortadurum.trim());
	    		sorguýfadesý.setDouble(10, ates);
	    		sorguýfadesý.setString(11, secilicorona.trim());
	    		sorguýfadesý.setString(12, secilitarih.trim());
	    		sorguýfadesý.setString(13, doktor.trim());
	    		sorguýfadesý.executeUpdate();		
	    	
			} 
	    	catch (SQLException e) {e.printStackTrace();}
	       	býlgýler(tableviev);

		sql="delete from üye_kayýt where Id=?";
	    	try {
					sorguýfadesý=baglantý.prepareStatement(sql);
					sorguýfadesý.setInt(1, seciliId);
					sorguýfadesý.executeUpdate();
				} catch (SQLException e) {e.printStackTrace();}  
	        Temizle();
	}
	 
	 if (result.get()==ButtonType.CANCEL) { mesAlert.setContentText("Taburcu islemi iptal oldu"); }
	 	býlgýler(tableviev);
    }
    
    @FXML
    void event_temizle(ActionEvent event) {
    Temizle();
    }
    @FXML
    void event_click(MouseEvent event) {
   	Kayýtlar bilgiler=new Kayýtlar();
   	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
   	
   	  combo_poliklinik.setValue(bilgiler.getPoliklinik());
   	  combo_hasta_türü.setValue(bilgiler.getHastatürü());
   	  combo_doktor.setValue(bilgiler.getDoktor());
   	  combo_sigorta.setValue(bilgiler.getSigorta());
   	  combo_sehir.setValue(bilgiler.getIl());
   	  txt_ad_soyad.setText(bilgiler.getAd());
   	  txt_adres.setText(bilgiler.getAdres());
   	  txt_tc.setText(bilgiler.getTc());
   	  if(bilgiler.getCinsiyet().equals(rd_erkek.getText())){rd_erkek.setSelected(true);}
   	  if (bilgiler.getCinsiyet().equals(rd_kadýn.getText())) {rd_kadýn.setSelected(true);}
   	  if(bilgiler.getCorona().equals(rd_var.getText())){rd_var.setSelected(true);}
   	  if(bilgiler.getCorona().equals(rd_yok.getText())){rd_yok.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_0rh_n.getText())) {r_0rh_n.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_0rh_p.getText())) {r_0rh_p.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_a_rh_n.getText())) {r_a_rh_n.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_a_rh_p.getText())) {r_a_rh_p.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_ab_rh_n.getText())) {r_ab_rh_n.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_ab_rh_p.getText())) {r_ab_rh_p.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_b_rh_n.getText())) {r_b_rh_n.setSelected(true);}
   	  if (bilgiler.getKan().equals(r_b_rh_p.getText())) {r_b_rh_p.setSelected(true);}
   	  s_ates.setValue(bilgiler.getAtes());
   	  
   }
   
    @FXML
    public void Temizle() {
    	combo_doktor.getSelectionModel().select(-1);
    	combo_hasta_türü.getSelectionModel().select(-1);
    	combo_poliklinik.getSelectionModel().select(-1);
    	combo_sehir.getSelectionModel().select(-1);
    	combo_sigorta.getSelectionModel().select(-1);
    	s_ates.setValue(0);
    	txt_ad_soyad.setText("");
    	txt_adres.setText("");
    	txt_tc.setText("");
    	txt_hasta_türü.setText("");
    	lbl_ates.setText(String.valueOf(1000));
    	rd_erkek.setSelected(false);
    	rd_kadýn.setSelected(false);
    	rd_var.setSelected(false);
    	rd_yok.setSelected(false);
    	r_0rh_n.setSelected(false);
    	r_0rh_p.setSelected(false);
    	r_a_rh_n.setSelected(false);
    	r_a_rh_p.setSelected(false);
    	r_ab_rh_n.setSelected(false);
    	r_ab_rh_p.setSelected(false);
    	r_b_rh_n.setSelected(false);
    	r_b_rh_p.setSelected(false);
    	lbl_ates.setText(null);
    	lbl_cinsiyet.setText(null);
    	lbl_þehir.setText(null);
    	lbl_ates.setText(String.valueOf(36));
    	s_ates.setValue(36);
    	data_tarih.setShowWeekNumbers(false);
    	lbl_tarih.setText(null);
		txt_hasta_türü.setVisible(false);
    }
    
    
    @FXML
    void event_sorgula(ActionEvent event) 
    {
    	

     Alert mesAlert=new Alert(AlertType.CONFIRMATION);
   	 mesAlert.setTitle("Sorgulama Ýþlemi Mesaj");
   	 mesAlert.setHeaderText("Sorgulama Uyarýsý");
   	 mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
   	 Optional<ButtonType> result=mesAlert.showAndWait();
   	 if (result.get()==ButtonType.OK) {
    	
   		int sayac=0;
    	double ates=0.0;
    	int corona=0;
    	int sehir=0;
    	int cinsiyet=0;
    	int tek=0;
    	ObservableList<Kayýtlar>Bütünkayýtlar;
    	Bütünkayýtlar=tableviev.getItems();
    	String týklanancorona="";
    	if (rd_var.isSelected())  {týklanancorona=rd_var.getText();}
    	if (rd_yok.isSelected()) {týklanancorona=rd_yok.getText();}
    	
    	String týklanancinsiyet="";
    	if (rd_erkek.isSelected())  {týklanancinsiyet=rd_erkek.getText();}
    	if (rd_kadýn.isSelected()) {týklanancinsiyet=rd_kadýn.getText();}

    	String týklanansehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
    	for(Kayýtlar bilgiKullanýcý :Bütünkayýtlar) 
    	{
    			sayac++;

    		if(bilgiKullanýcý.getIl().equals(týklanansehir))
    		{
    			sehir++;
        	}
    		if(bilgiKullanýcý.getIl().equals(týklanansehir)&&bilgiKullanýcý.getCinsiyet().equals(týklanancinsiyet)) 
    		{
    			cinsiyet++;
    		try 
    		{
    			ates+=bilgiKullanýcý.getAtes();
    		} 
    		catch (Exception e){ e.printStackTrace(); }
    		}
	
    		 if(bilgiKullanýcý.getCorona().equals(týklanancorona)&&bilgiKullanýcý.getIl().equals(týklanansehir))
    		 {
    			 corona++;
    		 }

    		 if(bilgiKullanýcý.getCorona().equals(týklanancorona)&&bilgiKullanýcý.getIl().equals(týklanansehir)&&
    				 bilgiKullanýcý.getCinsiyet().equals(týklanancinsiyet)) 
    		{
    			 tek++;
    			 
    		}
    		
    	}
    	NumberFormat formatter = new DecimalFormat("#0.0");     
    	double ortalama=(ates/cinsiyet);
    lbl_þehir.setText("Seçilen "+týklanansehir+" Þehrin Kiþi Sayýsý : " + sehir);
    lbl_ates_ort.setText("Seçilen "+týklanansehir+" Þehrin Ateþ Ort : "+ formatter.format(ortalama));
    lbl_cinsiyet.setText("Seçilen "+týklanansehir+" "+týklanancinsiyet+" Cinsiyet Sayýsý: "+cinsiyet);
    lbl_corona.setText("Seçilen "+týklanansehir+" Þehrin Corona Sayýsý: "+corona);
    tek=0;
    cinsiyet=0;
    ates=0;
    sehir=0;
    sayac=0;
    corona=0;
    mesAlert.setContentText("Basarili bir sekilde Sorgu Ýþlemi Yapýldý");
    }
   	if (result.get()==ButtonType.CANCEL)
   	{
   		mesAlert.setContentText("Ýþlem Ýptal Edildi");
   	}
    }
    

    
   public void býlgýler(TableView<Kayýtlar> tablo) {
	   sql="select*from üye_kayýt";
	   
   	try {
			sorguýfadesý=baglantý.prepareStatement(sql);
			getirilen=sorguýfadesý.executeQuery();
			ObservableList<Kayýtlar> veriler=FXCollections.observableArrayList();
			while(getirilen.next()) 
			{
veriler.add(new Kayýtlar(getirilen.getInt("id"),getirilen.getString("hasta_ad_soyad"),getirilen.getString("hasta_tc"),
getirilen.getString("cinsiyet"),getirilen.getString("kan_grubu"),getirilen.getString("poliklinik"),
getirilen.getString("hasta_cesidi"),getirilen.getString("il"),getirilen.getString("adres"),
getirilen.getString("sigorta_durumu"),getirilen.getDouble("hasta_ates"),getirilen.getString("corona"),
getirilen.getString("tarih"),getirilen.getString("doktor")));
			}
		
			ID.setCellValueFactory(new PropertyValueFactory<>("id"));
			colon_Adi.setCellValueFactory(new PropertyValueFactory<>("ad"));
			colon_tc.setCellValueFactory(new PropertyValueFactory<>("tc"));
			colon_cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
			colon_kangrubu.setCellValueFactory(new PropertyValueFactory<>("kan"));
			colon_poliklinik.setCellValueFactory(new PropertyValueFactory<>("poliklinik"));
			colon_hastatürü.setCellValueFactory(new PropertyValueFactory<>("hastatürü"));
			colon_sehir.setCellValueFactory(new PropertyValueFactory<>("il"));
			colon_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
			colon_sigorta.setCellValueFactory(new PropertyValueFactory<>("sigorta"));
			colon_ates.setCellValueFactory(new PropertyValueFactory<>("ates"));
			colon_corona.setCellValueFactory(new PropertyValueFactory<>("corona"));
			colon_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
			colon_doktor.setCellValueFactory(new PropertyValueFactory<>("doktor"));
			tableviev.setItems(veriler);

		} 
   	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
}
 
   
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combo_poliklinik.getItems().addAll("Acil","Kulak Burun Boðaz","Göz Hastalýklarý", "Genel Cerrahi", "Göðüs Hastalýklarý", "Çocuk Hastalýklarý","Çocuk Cerrahisi","Üroloji","Hematoloji","Gastroloji","Ortopedi","Dahiliye");
		combo_doktor.getItems().addAll("Dr.Ahmet Yetkin","Dr.Ali Budak", "Dr.Mustafa Kýlýnç", "Dr.Gökhan Altan", "Dr. Yakup Kutlu");
		
		combo_hasta_türü.getItems().addAll("Þeker","Tansiyon","Kalp","Kanser","Nefes Darlýðý","Kolestrol","Kireçlenme","Bel fýtýðý-siyatik","Kulaðýn diðer bozukluklarý, baþka yerde sýnýflanmýþ","Kulaðýn dejeneratif ve vasküler bozukluklarý",
				"Tinnitus", "Akustik sinir bozukluklarý","Anormal iþitsel algýlama", "Kol kýrýðýnýn sekeli","Enzim bozukluðuna baðlý anemi, tanýmlanmamýþ","Baþparmak kýrýðý","Anormal iþitsel algýlama","Kalp Damar Týkanýklýðý","Böbrek Taþý","Diger");
		txt_hasta_türü.setVisible(false);
		combo_sigorta.getItems().addAll("Emekli Sandýðý","SSK", "Baðkur", "Yeþil Kart", "Ücretli");
		combo_sehir.getItems().addAll("ADANA","ADIYAMAN", "AFYON", "AÐRI", "AMASYA","ÞANLIURFA","ÝSTANBUL","BURSA","HATAY","ANTEP","ANKARA","ÝZMÝR","ANTALYA");
		s_ates.valueProperty().addListener((obs, OldValue, newValue) -> {lbl_ates.setText(newValue.toString());});

    	býlgýler(tableviev);
		
	}
}
