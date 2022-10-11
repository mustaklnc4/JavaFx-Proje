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
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public SampleController() { baglant�=Baglant�.Baglan(); }
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kay�tlar> tableviev;

    @FXML
    private AnchorPane paneSample;

    @FXML
    private TableColumn<Kay�tlar, Integer> ID;

    @FXML
    private TableColumn<Kay�tlar, String> colon_Adi;

    @FXML
    private TableColumn<Kay�tlar, String> colon_tc;

    @FXML
    private TableColumn<Kay�tlar, String> colon_cinsiyet;

    @FXML
    private TableColumn<Kay�tlar, String> colon_kangrubu;

    @FXML
    private TableColumn<Kay�tlar, String> colon_poliklinik;

    @FXML
    private TableColumn<Kay�tlar, String> colon_hastat�r�;

    @FXML
    private TableColumn<Kay�tlar, String> colon_sehir;

    @FXML
    private TableColumn<Kay�tlar, String> colon_adres;

    @FXML
    private TableColumn<Kay�tlar, String> colon_sigorta;

    @FXML
    private TableColumn<Kay�tlar, Double> colon_ates;

    @FXML
    private TableColumn<Kay�tlar, String> colon_corona;

    @FXML
    private TableColumn<Kay�tlar, String> colon_tarih;

    @FXML
    private TableColumn<Kay�tlar, String> colon_doktor;

    @FXML
    private ComboBox<String> combo_poliklinik;

    @FXML
    private ComboBox<String> combo_hasta_t�r�;

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
    private RadioButton rd_kad�n;

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
    private TextField  txt_hasta_t�r�;

    @FXML
    private DatePicker data_tarih;

    @FXML
    private Label lbl_tarih;
    
    @FXML
    private TextArea txt_adres;

    @FXML
    private Label lbl_�ehir;

    @FXML
    private Label lbl_cinsiyet;

    @FXML
    private Button btn_gerid�n;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sorgula;
    
    @FXML
    private Button btn_taburcu;

    @FXML
    private Button btn_g�ncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_temizle;

    @FXML
    private Label lbl_corona;
    ObservableList<Kay�tlar> veriler;
    @FXML
    void event_gerid�n(ActionEvent event) {
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
    	if (combo_hasta_t�r�.getSelectionModel().getSelectedItem().equals("Diger")) 
    	{		txt_hasta_t�r�.setVisible(true);	}
    }
    
    @FXML
    void event_g�ncelle(ActionEvent event) 
    {
       	Kay�tlar bilgiler=new Kay�tlar();
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
      	sql="update �ye_kay�t set hasta_ad_soyad=?, hasta_tc=?,cinsiyet=?, kan_grubu=? , poliklinik=?, hasta_cesidi=?, il=?,adres=?,sigorta_durumu=?,hasta_ates=?,corona=?,tarih=?,doktor=? where Id=?";
       	
       	String poliklinik=combo_poliklinik.getSelectionModel().getSelectedItem().trim();
       	String hastat�r�=combo_hasta_t�r�.getSelectionModel().getSelectedItem().trim();
       	String �ehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
       	String sigortadurum=combo_sigorta.getSelectionModel().getSelectedItem().trim();
       	String doktor=combo_doktor.getSelectionModel().getSelectedItem().trim();
    	String secilicinsiyet="";
    	if (rd_erkek.isSelected())  {secilicinsiyet=rd_erkek.getText();}
    	if (rd_kad�n.isSelected()) {secilicinsiyet=rd_kad�n.getText();}

    	String secilicorona="";
    	if (rd_var.isSelected())  {secilicorona=rd_var.getText();}
    	if (rd_yok.isSelected()) {secilicorona=rd_yok.getText();}
    	
    	Double ates=Double.parseDouble(lbl_ates.getText());
    	
    	String t�r="";
    	if (hastat�r�.equals("Diger")) 
    	{
			t�r=txt_hasta_t�r�.getText().trim();
		}
    	else 
    	{
			t�r=combo_hasta_t�r�.getSelectionModel().getSelectedItem().trim();
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
    		sorgu�fades�=baglant�.prepareStatement(sql);
    		sorgu�fades�.setString(1, txt_ad_soyad.getText().trim());
    		sorgu�fades�.setString(2, txt_tc.getText().trim());
    		sorgu�fades�.setString(3, secilicinsiyet.trim());
    		sorgu�fades�.setString(4, kan.trim());
    		sorgu�fades�.setString(5, poliklinik.trim());
    		sorgu�fades�.setString(6, t�r.trim());
    		sorgu�fades�.setString(7, �ehir.trim());
    		sorgu�fades�.setString(8, txt_adres.getText().trim());
    		sorgu�fades�.setString(9, sigortadurum.trim());
    		sorgu�fades�.setDouble(10, ates);
    		sorgu�fades�.setString(11, secilicorona.trim());
    		sorgu�fades�.setString(12, secilitarih.trim());
    		sorgu�fades�.setString(13, doktor.trim());
    		sorgu�fades�.setInt(14, seciliId);
    		sorgu�fades�.executeUpdate();		
    	} 
    	catch (SQLException e) {e.printStackTrace();}
    	alert.setContentText("Kayit Guncellendi!!!");
    	Temizle();
	}
	else 
	{
		alert.setContentText("Kayit Guncellenmedi!!!");
	}
	b�lg�ler(tableviev);
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
       	 mesAlert.setTitle("Ekleme ��lemi Mesaj");
       	 mesAlert.setHeaderText("Ekleme Uyar�s�");
       	 mesAlert.setContentText("Eklemek istediginize emin misiniz?");
       	 Optional<ButtonType> result=mesAlert.showAndWait();
       	 if (result.get()==ButtonType.OK) 
       	 {
    	sql="insert into �ye_kay�t(hasta_ad_soyad,hasta_tc,cinsiyet,kan_grubu,poliklinik,hasta_cesidi,il,adres,sigorta_durumu,hasta_ates,corona,tarih,doktor) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
       	 	
    	String poliklinik=combo_poliklinik.getSelectionModel().getSelectedItem().trim();
       	String hastat�r�=combo_hasta_t�r�.getSelectionModel().getSelectedItem().trim();
       	String �ehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
       	String sigortadurum=combo_sigorta.getSelectionModel().getSelectedItem().trim();
       	String doktor=combo_doktor.getSelectionModel().getSelectedItem().trim();
    	String secilicinsiyet="";
    	if (rd_erkek.isSelected())  {secilicinsiyet=rd_erkek.getText();}
    	if (rd_kad�n.isSelected()) {secilicinsiyet=rd_kad�n.getText();}

    	String secilicorona="";
    	if (rd_var.isSelected())  {secilicorona=rd_var.getText();}
    	if (rd_yok.isSelected()) {secilicorona=rd_yok.getText();}
    	String t�r="";
    	if (hastat�r�.equals("Diger")) 
    	{
			t�r=txt_hasta_t�r�.getText().trim();
		}
    	else 
    	{
			t�r=combo_hasta_t�r�.getSelectionModel().getSelectedItem().trim();
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
    		sorgu�fades�=baglant�.prepareStatement(sql);
    		sorgu�fades�.setString(1, txt_ad_soyad.getText().trim());
    		sorgu�fades�.setString(2, txt_tc.getText().trim());
    		sorgu�fades�.setString(3, secilicinsiyet.trim());
    		sorgu�fades�.setString(4, kan.trim());
    		sorgu�fades�.setString(5, poliklinik.trim());
    		sorgu�fades�.setString(6, t�r.trim());
    		sorgu�fades�.setString(7, �ehir.trim());
    		sorgu�fades�.setString(8, txt_adres.getText().trim());
    		sorgu�fades�.setString(9, sigortadurum.trim());
    		sorgu�fades�.setDouble(10, ates);
    		sorgu�fades�.setString(11, secilicorona.trim());
    		sorgu�fades�.setString(12, secilitarih.trim());
    		sorgu�fades�.setString(13, doktor.trim());
    		sorgu�fades�.executeUpdate();		
    	
		} 
    	catch (SQLException e) {e.printStackTrace();}
    	mesAlert.setContentText("Ekleme i�lemi Ba�ar�l� bir �ekilde Ger�ekle�ti");	
    	
    	Temizle();
       	 }
       	 }
       	
       	 if (result.get()==ButtonType.CANCEL)
       	 {
    		 mesAlert.setContentText("Ekleme i�lemi iptal edildi");	 
    	 }
       	b�lg�ler(tableviev);
    }

    
    @FXML
    void event_taburcu(ActionEvent event) {
       	Kay�tlar bilgiler=new Kay�tlar();
      	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
   	
      	 
      	 int seciliId=bilgiler.getId();
      	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
   	 mesAlert.setTitle("Kal�c� Silme ��lemi Mesaj");
   	 mesAlert.setHeaderText("Kal�c� Silme Uyar�s�");
   	 mesAlert.setContentText("Kal�c� Silmeye emin misiniz?");
   	 Optional<ButtonType> result=mesAlert.showAndWait();
   	 if (result.get()==ButtonType.OK) {
   		 

 		sql="delete from �ye_kay�t where Id=?";
 	    	try {
 					sorgu�fades�=baglant�.prepareStatement(sql);
 					sorgu�fades�.setInt(1, seciliId);
 					sorgu�fades�.executeUpdate();
 				} catch (SQLException e) {e.printStackTrace();}  
 	        Temizle();
 	}
 	 
   	 else { mesAlert.setContentText("Kal�c� Silme islemi iptal oldu"); }
 	 	b�lg�ler(tableviev);
    }
    
    @FXML
    void event_sil(ActionEvent event)
    {
   	Kay�tlar bilgiler=new Kay�tlar();
   	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
	
   	 
   	 int seciliId=bilgiler.getId();
   	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
	 mesAlert.setTitle("Taburcu ��lemi Mesaj");
	 mesAlert.setHeaderText("Taburcu Uyar�s�");
	 mesAlert.setContentText("Taburcu Etmeye emin misiniz?");
	 Optional<ButtonType> result=mesAlert.showAndWait();
	 if (result.get()==ButtonType.OK) {
		 sql="insert into taburcu(hasta_ad_soyad,hasta_tc,cinsiyet,kan_grubu,poliklinik,hasta_cesidi,il,adres,sigorta_durumu,hasta_ates,corona,tarih,doktor) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
	    	String poliklinik=combo_poliklinik.getSelectionModel().getSelectedItem().trim();
	       	String hastat�r�=combo_hasta_t�r�.getSelectionModel().getSelectedItem().trim();
	       	String �ehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
	       	String sigortadurum=combo_sigorta.getSelectionModel().getSelectedItem().trim();
	       	String doktor=combo_doktor.getSelectionModel().getSelectedItem().trim();
	    	String secilicinsiyet="";
	    	if (rd_erkek.isSelected())  {secilicinsiyet=rd_erkek.getText();}
	    	if (rd_kad�n.isSelected()) {secilicinsiyet=rd_kad�n.getText();}

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
	    		sorgu�fades�=baglant�.prepareStatement(sql);
	    		sorgu�fades�.setString(1, txt_ad_soyad.getText().trim());
	    		sorgu�fades�.setString(2, txt_tc.getText().trim());
	    		sorgu�fades�.setString(3, secilicinsiyet.trim());
	    		sorgu�fades�.setString(4, kan.trim());
	    		sorgu�fades�.setString(5, poliklinik.trim());
	    		sorgu�fades�.setString(6, hastat�r�.trim());
	    		sorgu�fades�.setString(7, �ehir.trim());
	    		sorgu�fades�.setString(8, txt_adres.getText().trim());
	    		sorgu�fades�.setString(9, sigortadurum.trim());
	    		sorgu�fades�.setDouble(10, ates);
	    		sorgu�fades�.setString(11, secilicorona.trim());
	    		sorgu�fades�.setString(12, secilitarih.trim());
	    		sorgu�fades�.setString(13, doktor.trim());
	    		sorgu�fades�.executeUpdate();		
	    	
			} 
	    	catch (SQLException e) {e.printStackTrace();}
	       	b�lg�ler(tableviev);

		sql="delete from �ye_kay�t where Id=?";
	    	try {
					sorgu�fades�=baglant�.prepareStatement(sql);
					sorgu�fades�.setInt(1, seciliId);
					sorgu�fades�.executeUpdate();
				} catch (SQLException e) {e.printStackTrace();}  
	        Temizle();
	}
	 
	 if (result.get()==ButtonType.CANCEL) { mesAlert.setContentText("Taburcu islemi iptal oldu"); }
	 	b�lg�ler(tableviev);
    }
    
    @FXML
    void event_temizle(ActionEvent event) {
    Temizle();
    }
    @FXML
    void event_click(MouseEvent event) {
   	Kay�tlar bilgiler=new Kay�tlar();
   	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
   	
   	  combo_poliklinik.setValue(bilgiler.getPoliklinik());
   	  combo_hasta_t�r�.setValue(bilgiler.getHastat�r�());
   	  combo_doktor.setValue(bilgiler.getDoktor());
   	  combo_sigorta.setValue(bilgiler.getSigorta());
   	  combo_sehir.setValue(bilgiler.getIl());
   	  txt_ad_soyad.setText(bilgiler.getAd());
   	  txt_adres.setText(bilgiler.getAdres());
   	  txt_tc.setText(bilgiler.getTc());
   	  if(bilgiler.getCinsiyet().equals(rd_erkek.getText())){rd_erkek.setSelected(true);}
   	  if (bilgiler.getCinsiyet().equals(rd_kad�n.getText())) {rd_kad�n.setSelected(true);}
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
    	combo_hasta_t�r�.getSelectionModel().select(-1);
    	combo_poliklinik.getSelectionModel().select(-1);
    	combo_sehir.getSelectionModel().select(-1);
    	combo_sigorta.getSelectionModel().select(-1);
    	s_ates.setValue(0);
    	txt_ad_soyad.setText("");
    	txt_adres.setText("");
    	txt_tc.setText("");
    	txt_hasta_t�r�.setText("");
    	lbl_ates.setText(String.valueOf(1000));
    	rd_erkek.setSelected(false);
    	rd_kad�n.setSelected(false);
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
    	lbl_�ehir.setText(null);
    	lbl_ates.setText(String.valueOf(36));
    	s_ates.setValue(36);
    	data_tarih.setShowWeekNumbers(false);
    	lbl_tarih.setText(null);
		txt_hasta_t�r�.setVisible(false);
    }
    
    
    @FXML
    void event_sorgula(ActionEvent event) 
    {
    	

     Alert mesAlert=new Alert(AlertType.CONFIRMATION);
   	 mesAlert.setTitle("Sorgulama ��lemi Mesaj");
   	 mesAlert.setHeaderText("Sorgulama Uyar�s�");
   	 mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
   	 Optional<ButtonType> result=mesAlert.showAndWait();
   	 if (result.get()==ButtonType.OK) {
    	
   		int sayac=0;
    	double ates=0.0;
    	int corona=0;
    	int sehir=0;
    	int cinsiyet=0;
    	int tek=0;
    	ObservableList<Kay�tlar>B�t�nkay�tlar;
    	B�t�nkay�tlar=tableviev.getItems();
    	String t�klanancorona="";
    	if (rd_var.isSelected())  {t�klanancorona=rd_var.getText();}
    	if (rd_yok.isSelected()) {t�klanancorona=rd_yok.getText();}
    	
    	String t�klanancinsiyet="";
    	if (rd_erkek.isSelected())  {t�klanancinsiyet=rd_erkek.getText();}
    	if (rd_kad�n.isSelected()) {t�klanancinsiyet=rd_kad�n.getText();}

    	String t�klanansehir=combo_sehir.getSelectionModel().getSelectedItem().trim();
    	for(Kay�tlar bilgiKullan�c� :B�t�nkay�tlar) 
    	{
    			sayac++;

    		if(bilgiKullan�c�.getIl().equals(t�klanansehir))
    		{
    			sehir++;
        	}
    		if(bilgiKullan�c�.getIl().equals(t�klanansehir)&&bilgiKullan�c�.getCinsiyet().equals(t�klanancinsiyet)) 
    		{
    			cinsiyet++;
    		try 
    		{
    			ates+=bilgiKullan�c�.getAtes();
    		} 
    		catch (Exception e){ e.printStackTrace(); }
    		}
	
    		 if(bilgiKullan�c�.getCorona().equals(t�klanancorona)&&bilgiKullan�c�.getIl().equals(t�klanansehir))
    		 {
    			 corona++;
    		 }

    		 if(bilgiKullan�c�.getCorona().equals(t�klanancorona)&&bilgiKullan�c�.getIl().equals(t�klanansehir)&&
    				 bilgiKullan�c�.getCinsiyet().equals(t�klanancinsiyet)) 
    		{
    			 tek++;
    			 
    		}
    		
    	}
    	NumberFormat formatter = new DecimalFormat("#0.0");     
    	double ortalama=(ates/cinsiyet);
    lbl_�ehir.setText("Se�ilen "+t�klanansehir+" �ehrin Ki�i Say�s� : " + sehir);
    lbl_ates_ort.setText("Se�ilen "+t�klanansehir+" �ehrin Ate� Ort : "+ formatter.format(ortalama));
    lbl_cinsiyet.setText("Se�ilen "+t�klanansehir+" "+t�klanancinsiyet+" Cinsiyet Say�s�: "+cinsiyet);
    lbl_corona.setText("Se�ilen "+t�klanansehir+" �ehrin Corona Say�s�: "+corona);
    tek=0;
    cinsiyet=0;
    ates=0;
    sehir=0;
    sayac=0;
    corona=0;
    mesAlert.setContentText("Basarili bir sekilde Sorgu ��lemi Yap�ld�");
    }
   	if (result.get()==ButtonType.CANCEL)
   	{
   		mesAlert.setContentText("��lem �ptal Edildi");
   	}
    }
    

    
   public void b�lg�ler(TableView<Kay�tlar> tablo) {
	   sql="select*from �ye_kay�t";
	   
   	try {
			sorgu�fades�=baglant�.prepareStatement(sql);
			getirilen=sorgu�fades�.executeQuery();
			ObservableList<Kay�tlar> veriler=FXCollections.observableArrayList();
			while(getirilen.next()) 
			{
veriler.add(new Kay�tlar(getirilen.getInt("id"),getirilen.getString("hasta_ad_soyad"),getirilen.getString("hasta_tc"),
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
			colon_hastat�r�.setCellValueFactory(new PropertyValueFactory<>("hastat�r�"));
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
		combo_poliklinik.getItems().addAll("Acil","Kulak Burun Bo�az","G�z Hastal�klar�", "Genel Cerrahi", "G���s Hastal�klar�", "�ocuk Hastal�klar�","�ocuk Cerrahisi","�roloji","Hematoloji","Gastroloji","Ortopedi","Dahiliye");
		combo_doktor.getItems().addAll("Dr.Ahmet Yetkin","Dr.Ali Budak", "Dr.Mustafa K�l�n�", "Dr.G�khan Altan", "Dr. Yakup Kutlu");
		
		combo_hasta_t�r�.getItems().addAll("�eker","Tansiyon","Kalp","Kanser","Nefes Darl���","Kolestrol","Kire�lenme","Bel f�t���-siyatik","Kula��n di�er bozukluklar�, ba�ka yerde s�n�flanm��","Kula��n dejeneratif ve vask�ler bozukluklar�",
				"Tinnitus", "Akustik sinir bozukluklar�","Anormal i�itsel alg�lama", "Kol k�r���n�n sekeli","Enzim bozuklu�una ba�l� anemi, tan�mlanmam��","Ba�parmak k�r���","Anormal i�itsel alg�lama","Kalp Damar T�kan�kl���","B�brek Ta��","Diger");
		txt_hasta_t�r�.setVisible(false);
		combo_sigorta.getItems().addAll("Emekli Sand���","SSK", "Ba�kur", "Ye�il Kart", "�cretli");
		combo_sehir.getItems().addAll("ADANA","ADIYAMAN", "AFYON", "A�RI", "AMASYA","�ANLIURFA","�STANBUL","BURSA","HATAY","ANTEP","ANKARA","�ZM�R","ANTALYA");
		s_ates.valueProperty().addListener((obs, OldValue, newValue) -> {lbl_ates.setText(newValue.toString());});

    	b�lg�ler(tableviev);
		
	}
}
