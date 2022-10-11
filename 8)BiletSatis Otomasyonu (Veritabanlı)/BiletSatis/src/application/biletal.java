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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class biletal implements Initializable {
	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public biletal() { baglantý=Baglantý.Baglan(); }
    ObservableList<bilet_sinif> veriler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneSample;

    @FXML
    private Pane pane_salon1;

    @FXML
    private RadioButton s1_koltuk9;

    @FXML
    private ToggleGroup koltuk;

    @FXML
    private RadioButton s1_koltuk10;

    @FXML
    private RadioButton s1_koltuk7;

    @FXML
    private RadioButton s1_koltuk8;

    @FXML
    private RadioButton s1_koltuk5;

    @FXML
    private RadioButton s1_koltuk6;

    @FXML
    private RadioButton s1_koltuk3;

    @FXML
    private RadioButton s1_koltuk4;

    @FXML
    private RadioButton s1_koltuk1;

    @FXML
    private RadioButton s1_koltuk2;

    @FXML
    private TableView<bilet_sinif> tableviev;

    @FXML
    private TableColumn<bilet_sinif, String> id_c;

    @FXML
    private TableColumn<bilet_sinif, String> salon_c;

    @FXML
    private TableColumn<bilet_sinif, String> koltuk_c;

    @FXML
    private TableColumn<bilet_sinif, String> film_c;

    @FXML
    private TableColumn<bilet_sinif, String> isim_c;

    @FXML
    private TableColumn<bilet_sinif, String> tarih_c;

    @FXML
    private TableColumn<bilet_sinif, String> fiyat_c;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_güncelle;

    @FXML
    private Button btn_cik;

    @FXML
    private ComboBox<String> cmb_salon;
    
    @FXML
    private ComboBox<String> cmb_tür;
    

    @FXML
    private TextField field_isim;

    @FXML
    private Button btn_salon;

    @FXML
    private DatePicker data_tarih;

    @FXML
    private Label lbl_tarih;

    @FXML
    private Slider Sldr_fiyat;

    @FXML
    private Label lbl_fiyat;

    @FXML
    private Pane pane_salon2;

    @FXML
    private RadioButton s2_koltuk9;

    @FXML
    private RadioButton s2_koltuk10;

    @FXML
    private RadioButton s2_koltuk7;

    @FXML
    private RadioButton s2_koltuk8;

    @FXML
    private RadioButton s2_koltuk5;

    @FXML
    private RadioButton s2_koltuk6;

    @FXML
    private RadioButton s2_koltuk3;

    @FXML
    private RadioButton s2_koltuk4;

    @FXML
    private RadioButton s2_koltuk1;

    @FXML
    private RadioButton s2_koltuk2;


    @FXML
    void click_tarih(ActionEvent event) {
    	 LocalDate ld=data_tarih.getValue();
         lbl_tarih.setText(ld.toString());
    }
    @FXML
    void event_cik(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("ana.fxml"));
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
        bilet_sinif bilgiler=new bilet_sinif();
        bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
        cmb_tür.setValue(bilgiler.getTür());
        cmb_salon.setValue(bilgiler.getSalon());
        field_isim.setText(bilgiler.getIsim());
        Sldr_fiyat.setValue(bilgiler.getFiyat());
        lbl_tarih.setText(bilgiler.getTarih());
        lbl_fiyat.setText(bilgiler.getFiyat()+"");
        if (bilgiler.getKoltuk().equals(s1_koltuk1.getText())) {s1_koltuk1.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk2.getText())) {s1_koltuk2.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk3.getText())) {s1_koltuk3.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk4.getText())) {s1_koltuk4.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk5.getText())) {s1_koltuk5.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk6.getText())) {s1_koltuk6.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk7.getText())) {s1_koltuk7.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk8.getText())) {s1_koltuk8.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk9.getText())) {s1_koltuk9.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s1_koltuk10.getText())) {s1_koltuk10.setSelected(true);}

        if (bilgiler.getKoltuk().equals(s2_koltuk1.getText())) {s2_koltuk1.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk2.getText())) {s2_koltuk2.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk3.getText())) {s2_koltuk3.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk4.getText())) {s2_koltuk4.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk5.getText())) {s2_koltuk5.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk6.getText())) {s2_koltuk6.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk7.getText())) {s2_koltuk7.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk8.getText())) {s2_koltuk8.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk9.getText())) {s2_koltuk9.setSelected(true);}
        if (bilgiler.getKoltuk().equals(s2_koltuk10.getText())) {s2_koltuk10.setSelected(true);}
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
    	sql="insert into biletal(salon,koltuk,tür,isim,tarih,fiyat) values(?,?,?,?,?,?)";

    	String salon=cmb_salon.getSelectionModel().getSelectedItem().trim();
    	String tür=cmb_tür.getSelectionModel().getSelectedItem().trim();
    	Double fiyat=Double.parseDouble(lbl_fiyat.getText());
    	String tarih=String.valueOf(data_tarih.getValue());
    	
    	String koltuk="";
    	if (s1_koltuk1.isSelected()) {koltuk=s1_koltuk1.getText();}
    	if (s1_koltuk2.isSelected()) {koltuk=s1_koltuk2.getText();}
    	if (s1_koltuk3.isSelected()) {koltuk=s1_koltuk3.getText();}
    	if (s1_koltuk4.isSelected()) {koltuk=s1_koltuk4.getText();}
    	if (s1_koltuk5.isSelected()) {koltuk=s1_koltuk5.getText();}
    	if (s1_koltuk6.isSelected()) {koltuk=s1_koltuk6.getText();}
    	if (s1_koltuk7.isSelected()) {koltuk=s1_koltuk7.getText();}
    	if (s1_koltuk8.isSelected()) {koltuk=s1_koltuk8.getText();}
    	if (s1_koltuk9.isSelected()) {koltuk=s1_koltuk9.getText();}
    	if (s1_koltuk10.isSelected()) {koltuk=s1_koltuk10.getText();}
    	
    	if (s2_koltuk1.isSelected()) {koltuk=s2_koltuk1.getText();}
    	if (s2_koltuk2.isSelected()) {koltuk=s2_koltuk2.getText();}
    	if (s2_koltuk3.isSelected()) {koltuk=s2_koltuk3.getText();}
    	if (s2_koltuk4.isSelected()) {koltuk=s2_koltuk4.getText();}
    	if (s2_koltuk5.isSelected()) {koltuk=s2_koltuk5.getText();}
    	if (s2_koltuk6.isSelected()) {koltuk=s2_koltuk6.getText();}
    	if (s2_koltuk7.isSelected()) {koltuk=s2_koltuk7.getText();}
    	if (s2_koltuk8.isSelected()) {koltuk=s2_koltuk8.getText();}
    	if (s2_koltuk9.isSelected()) {koltuk=s2_koltuk9.getText();}
    	if (s2_koltuk10.isSelected()) {koltuk=s2_koltuk10.getText();}
    	
    	try 
    	{
    		sorguýfadesý=baglantý.prepareStatement(sql);
    		sorguýfadesý.setString(1, salon.trim());
    		sorguýfadesý.setString(2, koltuk.trim());
    		sorguýfadesý.setString(3, tür.trim());
    		sorguýfadesý.setString(4, field_isim.getText().trim());
    		sorguýfadesý.setString(5, tarih.trim());
    		sorguýfadesý.setDouble(6, fiyat);
    		sorguýfadesý.executeUpdate();			
		} 
    	catch (SQLException e) {e.printStackTrace();}
    	mesAlert.setContentText("Ekleme Baþarýlý");	
    	 }
       	býlgýler(tableviev);
    }

    @FXML
    void event_güncelle(ActionEvent event) {
    	  bilet_sinif bilgiler = new bilet_sinif();
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
              sql = "update biletal set salon=?, koltuk=?,tür=?, isim=? , tarih=?, fiyat=? where id=?";
      
          	String salon=cmb_salon.getSelectionModel().getSelectedItem().trim();
          	String tür=cmb_tür.getSelectionModel().getSelectedItem().trim();
          	Double fiyat=Double.parseDouble(lbl_fiyat.getText());
          	String tarih=String.valueOf(data_tarih.getValue());
          	
          	String koltuk="";
          	if (s1_koltuk1.isSelected()) {koltuk=s1_koltuk1.getText();}
          	if (s1_koltuk2.isSelected()) {koltuk=s1_koltuk2.getText();}
          	if (s1_koltuk3.isSelected()) {koltuk=s1_koltuk3.getText();}
          	if (s1_koltuk4.isSelected()) {koltuk=s1_koltuk4.getText();}
          	if (s1_koltuk5.isSelected()) {koltuk=s1_koltuk5.getText();}
          	if (s1_koltuk6.isSelected()) {koltuk=s1_koltuk6.getText();}
          	if (s1_koltuk7.isSelected()) {koltuk=s1_koltuk7.getText();}
          	if (s1_koltuk8.isSelected()) {koltuk=s1_koltuk8.getText();}
          	if (s1_koltuk9.isSelected()) {koltuk=s1_koltuk9.getText();}
          	if (s1_koltuk10.isSelected()) {koltuk=s1_koltuk10.getText();}
          	
          	if (s2_koltuk1.isSelected()) {koltuk=s2_koltuk1.getText();}
          	if (s2_koltuk2.isSelected()) {koltuk=s2_koltuk2.getText();}
          	if (s2_koltuk3.isSelected()) {koltuk=s2_koltuk3.getText();}
          	if (s2_koltuk4.isSelected()) {koltuk=s2_koltuk4.getText();}
          	if (s2_koltuk5.isSelected()) {koltuk=s2_koltuk5.getText();}
          	if (s2_koltuk6.isSelected()) {koltuk=s2_koltuk6.getText();}
          	if (s2_koltuk7.isSelected()) {koltuk=s2_koltuk7.getText();}
          	if (s2_koltuk8.isSelected()) {koltuk=s2_koltuk8.getText();}
          	if (s2_koltuk9.isSelected()) {koltuk=s2_koltuk9.getText();}
          	if (s2_koltuk10.isSelected()) {koltuk=s2_koltuk10.getText();}
          	
          	try 
          	{
          		sorguýfadesý=baglantý.prepareStatement(sql);
          		sorguýfadesý.setString(1, salon.trim());
          		sorguýfadesý.setString(2, koltuk.trim());
          		sorguýfadesý.setString(3, tür.trim());
          		sorguýfadesý.setString(4, field_isim.getText().trim());
          		sorguýfadesý.setString(5, tarih.trim());
          		sorguýfadesý.setDouble(6, fiyat);
         		sorguýfadesý.setInt(7, ids);
          		sorguýfadesý.executeUpdate();			
      		} 
          	catch (SQLException e) {e.printStackTrace();}
          	 }
             	býlgýler(tableviev);

    }

    @FXML
    void event_salon(ActionEvent event) {
      	if (cmb_salon.getSelectionModel().getSelectedItem().equals("Salon 1")) 
    	{		pane_salon1.setVisible(true);
    			pane_salon2.setVisible(false);
    	}
      	if (cmb_salon.getSelectionModel().getSelectedItem().equals("Salon 2")) 
    	{		pane_salon2.setVisible(true);	
    			pane_salon1.setVisible(false);
    	}
    }

    @FXML
    void event_sil(ActionEvent event) {
    	bilet_sinif bilgiler=new bilet_sinif();
        bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());

        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyarý Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="delete from biletal where id=?";
            try {
                sorguýfadesý=baglantý.prepareStatement(sql);
                sorguýfadesý.setInt(1, t_id);
                sorguýfadesý.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
        }
        býlgýler(tableviev);
    }
    
    public void býlgýler(TableView<bilet_sinif> tablo) {
 	   sql="select*from biletal";
 	   
    	try {
 			sorguýfadesý=baglantý.prepareStatement(sql);
 			getirilen=sorguýfadesý.executeQuery();
 			ObservableList<bilet_sinif> veriler=FXCollections.observableArrayList();
 			while(getirilen.next()) 
 			{
 veriler.add(new bilet_sinif(getirilen.getInt("id"),getirilen.getString("salon"),getirilen.getString("koltuk"),
 getirilen.getString("tür"),getirilen.getString("isim"),getirilen.getString("tarih"),
 getirilen.getDouble("fiyat")));
 			}
 			
 			id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
 			salon_c.setCellValueFactory(new PropertyValueFactory<>("salon"));
 			koltuk_c.setCellValueFactory(new PropertyValueFactory<>("koltuk"));
 			film_c.setCellValueFactory(new PropertyValueFactory<>("tür"));
 			isim_c.setCellValueFactory(new PropertyValueFactory<>("isim"));
 			tarih_c.setCellValueFactory(new PropertyValueFactory<>("tarih"));
 			fiyat_c.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
 			tableviev.setItems(veriler);

 		} 
    	catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}	
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		   cmb_salon.getItems().addAll("Salon 1", "Salon 2");
		   cmb_tür.getItems().addAll("Korku", "Heyecan","Macera","Belgesel");
	        Sldr_fiyat.valueProperty().addListener((obs, OldValue, newValue) -> {lbl_fiyat.setText(newValue.toString());
	        });
	        býlgýler(tableviev);
	       pane_salon1.setVisible(false);
	       pane_salon2.setVisible(false);
	}
}
