package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class randevu implements Initializable {

	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public randevu() { baglantý=Baglantý.Baglan(); }
	
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
    private DatePicker data_tarih;

    @FXML
    private Label lbl_tarih;

    @FXML
    private TextField txt_adres;
    
    @FXML
    private TextField txt_hasta_türü;

    @FXML
    private Label lbl_þehir;

    @FXML
    private Label lbl_cinsiyet;

    @FXML
    private Button btn_geridön;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_temizle;

    @FXML
    private Label lbl_corona;
    ObservableList<Kayýtlar> veriler;

    @FXML
    void event_geridön(ActionEvent event) {
    	try {
			AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("AnasayfaKullanici.fxml"));
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
       	
       	 if (result.get()==ButtonType.CANCEL)
       	 {
    		 mesAlert.setContentText("Ekleme iþlemi iptal edildi");	 
    	 }
       	býlgýler(tableviev);
    }

    @FXML
    void event_temizle(ActionEvent event) {
    Temizle();
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
    	colon_adres.setVisible(false);
    	colon_cinsiyet.setVisible(false);
    	colon_tc.setVisible(false);
	}
}
