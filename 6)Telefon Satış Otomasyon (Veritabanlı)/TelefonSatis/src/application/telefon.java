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
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class telefon implements Initializable {
	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public telefon() { baglantý=sqlbag.Baglan(); }
    ObservableList<tel_sinif> veriler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneSample;

    @FXML
    private TableView<tel_sinif> tableviev;

    @FXML
    private TableColumn<tel_sinif, Integer> col_id;

    @FXML
    private TableColumn<tel_sinif, String> col_ad;

    @FXML
    private TableColumn<tel_sinif, String> col_marka;

    @FXML
    private TableColumn<tel_sinif, String> col_model;

    @FXML
    private TableColumn<tel_sinif, Integer> col_adet;

    @FXML
    private TableColumn<tel_sinif, String> col_tarih;

    @FXML
    private TableColumn<tel_sinif, Integer> col_ücret;

    @FXML
    private ComboBox<String> cmb_marka;

    @FXML
    private ComboBox<String> cmb_model;

    @FXML
    private TextField text_ad;

    @FXML
    private TextField text_adet;

    @FXML
    private Label lbl_tarih;

    @FXML
    private DatePicker data_tarih;

    @FXML
    private Label lbl_fiyat;

    @FXML
    private Slider Sldr_ücret;

    @FXML
    private Button btn_ana;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_güncelle;

    @FXML
    private Button btn_sil;
    public void býlgýler(TableView<tel_sinif> tablo) {
  	   sql="select*from telefon";
  	   
     	try {
  			sorguýfadesý=baglantý.prepareStatement(sql);
  			getirilen=sorguýfadesý.executeQuery();
  			ObservableList<tel_sinif> veriler=FXCollections.observableArrayList();
  			while(getirilen.next()) 
  			{
  veriler.add(new tel_sinif(getirilen.getInt("id"),getirilen.getString("ad"),getirilen.getString("marka"),
  getirilen.getString("model"),getirilen.getInt("adet"),getirilen.getString("tarih"),
  getirilen.getDouble("ücret")));
  			}		
  			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
  			col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
  			col_marka.setCellValueFactory(new PropertyValueFactory<>("marka"));
  			col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
  			col_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
  			col_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
  			col_ücret.setCellValueFactory(new PropertyValueFactory<>("ücret"));
  			tableviev.setItems(veriler);

  		} 
     	catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}	
     }
    @FXML
    void event_ana(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("anamenü.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            paneSample.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void event_click(MouseEvent event) {
        tel_sinif bilgiler=new tel_sinif();
        bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
        cmb_marka.setValue(bilgiler.getMarka());
        cmb_model.setValue(bilgiler.getModel());
        text_ad.setText(bilgiler.getAd());
        text_adet.setText(bilgiler.getAdet()+"");
        Sldr_ücret.setValue(bilgiler.getÜcret());
        lbl_tarih.setText(bilgiler.getTarih());
        lbl_fiyat.setText(bilgiler.getÜcret()+"");
    }
    @FXML
    void click_tarih(ActionEvent event) {
    	 LocalDate ld=data_tarih.getValue();
         lbl_tarih.setText(ld.toString());
    }
    @FXML
    void event_ekle(ActionEvent event) {
   	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
   	 mesAlert.setTitle("Ekleme Ýþlemi Mesaj");
   	 mesAlert.setHeaderText("Ekleme Uyarýsý");
   	 mesAlert.setContentText("Eklemek istediginize emin misiniz?");
   	 Optional<ButtonType> result=mesAlert.showAndWait();
   	 if (result.get()==ButtonType.OK) 
   	 {
	sql="insert into telefon(ad,marka,model,adet,tarih,ücret) values(?,?,?,?,?,?)";
	String marka=cmb_marka.getSelectionModel().getSelectedItem().trim();
	String model=cmb_model.getSelectionModel().getSelectedItem().trim();
	Double ücret=Double.parseDouble(lbl_fiyat.getText());
	String tarih=String.valueOf(data_tarih.getValue());
	try 
	{
		sorguýfadesý=baglantý.prepareStatement(sql);
		sorguýfadesý.setString(1, text_ad.getText().trim());
		sorguýfadesý.setString(2, marka.trim());
		sorguýfadesý.setString(3, model.trim());
		sorguýfadesý.setInt(4, Integer.valueOf(text_adet.getText().trim()));
		sorguýfadesý.setString(5, tarih.trim());
		sorguýfadesý.setDouble(6, ücret);
		sorguýfadesý.executeUpdate();			
	} 
	catch (SQLException e) {e.printStackTrace();}
	mesAlert.setContentText("Ekleme Baþarýlý");	
	 }
   	býlgýler(tableviev);
    }

    @FXML
    void event_güncelle(ActionEvent event) {
  	  tel_sinif bilgiler = new tel_sinif();
      bilgiler = tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
      int ids = bilgiler.getId();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Alert Guncelle");
      alert.setHeaderText("Güncelle Uyarý");
      alert.setContentText("Guncelle, Eminmisiniz?");
      Optional<ButtonType> sonucOptional = alert.showAndWait();
      if (sonucOptional.get() == ButtonType.OK) {
          int sira = tableviev.getSelectionModel().getSelectedIndex();
          tableviev.getItems().set(sira, bilgiler);
          sql = "update telefon set ad=?, marka=?,model=?, adet=? , tarih=?, ücret=? where id=?";
      	String marka=cmb_marka.getSelectionModel().getSelectedItem().trim();
      	String model=cmb_model.getSelectionModel().getSelectedItem().trim();
      	Double ücret=Double.parseDouble(lbl_fiyat.getText());
      	String tarih=String.valueOf(data_tarih.getValue());
      	try {
      		sorguýfadesý=baglantý.prepareStatement(sql);
      		sorguýfadesý.setString(1, text_ad.getText().trim());
      		sorguýfadesý.setString(2, marka.trim());
      		sorguýfadesý.setString(3, model.trim());
      		sorguýfadesý.setInt(4, Integer.valueOf(text_adet.getText().trim()));
      		sorguýfadesý.setString(5, tarih.trim());
      		sorguýfadesý.setDouble(6, ücret);
      		sorguýfadesý.setInt(7, ids);
      		sorguýfadesý.executeUpdate();			
      	} 
      	catch (SQLException e) {
      		e.printStackTrace();}
      	 }
         	býlgýler(tableviev);
    }

    @FXML
    void event_sil(ActionEvent event) {
    	tel_sinif bilgiler=new tel_sinif();
        bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyarý Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="delete from telefon where id=?";
            try {
                sorguýfadesý=baglantý.prepareStatement(sql);
                sorguýfadesý.setInt(1, t_id);
                sorguýfadesý.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
        }
        býlgýler(tableviev);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		   cmb_marka.getItems().addAll("LG", "Ýphone","Samsung","Xiaomi","HUAWEÝ");
		   cmb_model.getItems().addAll("iPhone 11", "Galaxy A51","Redmi Note 8","Redmi Note 8 Pro","iPhone XR","Redmi 8A","Redmi 8","iPhone 11 Pro","P20");
	        Sldr_ücret.valueProperty().addListener((obs, OldValue, newValue) -> {lbl_fiyat.setText(newValue.toString());
	        });
	        býlgýler(tableviev);
	}
}
