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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CafeMenü implements Initializable{
	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public CafeMenü() { baglantý=Baglantý.Baglan(); }
    ObservableList<sinif> veriler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneSample;

    @FXML
    private TableView<sinif> tableviev;

    @FXML
    private TableColumn<sinif, Integer> colon_id;

    @FXML
    private TableColumn<sinif, String> colon_masa_numara;

    @FXML
    private TableColumn<sinif, String> colon_süre;

    @FXML
    private TableColumn<sinif, String> colon_adsoyad;

    @FXML
    private TableColumn<sinif, String> colon_tarih;

    @FXML
    private TableColumn<sinif, String> colon_yiyecek;

    @FXML
    private TableColumn<sinif, Double> colon_fiyat;

    @FXML
    private ComboBox<String> combo_süre;

    @FXML
    private ComboBox<String> combo_yiyecek;

    @FXML
    private TextField txt_ad_soyad;

    @FXML
    private TextField txt_süre;

    @FXML
    private DatePicker data_tarih;

    @FXML
    private Label lbl_tarih;

    @FXML
    private Slider Sldr_fiyat;

    @FXML
    private Label lbl_fiyat;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_güncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_sorgu;

    @FXML
    private Button btn_süpür;

    @FXML
    private Button btn_cik;

    @FXML
    private Button btn_süre;
    
    @FXML
    private BarChart<String, Number> Salarychart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private PieChart pastadilimi;

    @FXML
    private Label lbl_tarih_aralik;

    @FXML
    private RadioButton masa_1;

    @FXML
    private ToggleGroup masa;

    @FXML
    private RadioButton masa_2;

    @FXML
    private RadioButton masa_3;

    @FXML
    private RadioButton masa_4;

    @FXML
    private RadioButton masa_5;

    @FXML
    private RadioButton masa_6;

    @FXML
    private RadioButton masa_7;

    @FXML
    private RadioButton masa_8;

    @FXML
    private RadioButton masa_9;

    @FXML
    private RadioButton masa_10;

    @FXML
    private RadioButton masa_11;

    @FXML
    private RadioButton masa_12;

    @FXML
    private RadioButton masa_13;

    @FXML
    private RadioButton masa_14;

    @FXML
    private RadioButton masa_15;
    
    @FXML
    private ImageView resim1;
    
    @FXML
    void event_sorgu(ActionEvent event) {
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Sorgulama Ýþlemi Mesaj");
        mesAlert.setHeaderText("Sorgulama Uyarýsý");
        mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();

        if (result.get()==ButtonType.OK)
        {
            int tarih=0;
            int deger=0;
            int masas=0;
            int süres=0;
            ObservableList<sinif>Bütünkayýtlar;
            Bütünkayýtlar=tableviev.getItems();
        	String süre=combo_süre.getSelectionModel().getSelectedItem().trim();
            String s_tarih= lbl_tarih.getText().trim();
            
        	String masa="";
        	if (masa_1.isSelected()) {masa=masa_1.getText();}
        	if (masa_2.isSelected()) {masa=masa_2.getText();}
        	if (masa_3.isSelected()) {masa=masa_3.getText();}
        	if (masa_4.isSelected()) {masa=masa_4.getText();}
        	if (masa_5.isSelected()) {masa=masa_5.getText();}
        	if (masa_6.isSelected()) {masa=masa_6.getText();}
        	if (masa_7.isSelected()) {masa=masa_7.getText();}
        	if (masa_8.isSelected()) {masa=masa_8.getText();}
        	if (masa_9.isSelected()) {masa=masa_9.getText();}
        	if (masa_10.isSelected()) {masa=masa_10.getText();}
        	if (masa_11.isSelected()) {masa=masa_11.getText();}
        	if (masa_12.isSelected()) {masa=masa_12.getText();}
        	if (masa_13.isSelected()) {masa=masa_13.getText();}
        	if (masa_14.isSelected()) {masa=masa_14.getText();}
        	if (masa_15.isSelected()) {masa=masa_15.getText();}


            for(sinif bilgiKullanýcý :Bütünkayýtlar)
            {
                deger++;
                if(bilgiKullanýcý.getMasa_numara().equals(masa)&&bilgiKullanýcý.getMasa_süre().equals(süre))
                {
                    süres++;
                }

                if(bilgiKullanýcý.getMasa_süre().equals(süre)&&bilgiKullanýcý.getTarih().equals(s_tarih))
                {
                    tarih++;
                }

                if(bilgiKullanýcý.getMasa_numara().equals(masa)&&bilgiKullanýcý.getMasa_süre().equals(süre))
                {
                    masas++;
                    XYChart.Series srm=new XYChart.Series();
                    srm.getData().add(new XYChart.Data(süre+" "+masa+" "+s_tarih,süres/masas));
                    srm.setName(deger+". Kayýt "+masa+" "+süre+" "+s_tarih);
                    try {
                        Salarychart.getData().add(srm);
                        Salarychart.getChildrenUnmodifiable().addAll(x,y);
                        Salarychart.setBarGap (3);
                        Salarychart.setCategoryGap (20);
                        Salarychart.getChildrenUnmodifiable().clear();

                    } catch (Exception e) {		}

                }
            }
            //
            ObservableList<PieChart.Data> pcData=FXCollections.observableArrayList();
            if(masa.equals("masa_1"))
            {
                pcData.add(new PieChart.Data( "masa_2,masa_3....masa_15",deger-1));
            }
            if(masa.equals("masa_2"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_3....masa_15",deger-1));
            }
            if(masa.equals("masa_3"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_4"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_5"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_6"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_7"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_8"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_9"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa10"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_11"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_12"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_13"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_14"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_15",deger-1));
            }
            if(masa.equals("masa_15"))
            {
                pcData.add(new PieChart.Data( "masa_1,masa_2....masa_14",deger-1));
            }
            pcData.addAll(new PieChart.Data(süre,masas),new PieChart.Data(süres+" Kiþi "+süre,süres),new PieChart.Data(masas+" Kiþide "+masa,masas));
            pastadilimi.setData(pcData);
            pastadilimi.setLabelLineLength (20);
            pastadilimi.getChildrenUnmodifiable();

            for (final PieChart.Data data : pastadilimi.getData())
            {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        lbl_tarih_aralik.setText(String.valueOf(data.getPieValue()) + " Tane");
                    }
                });
            }
            masas=0;
            süres=0;
            tarih=0;
            deger=0;
        }
    }

    @FXML
    void click_tarih(ActionEvent event) {
    	 LocalDate ld=data_tarih.getValue();
         lbl_tarih.setText(ld.toString());
    }
   
    @FXML
    void event_süre(ActionEvent event) {
    	if (combo_süre.getSelectionModel().getSelectedItem().equals("Süresiz")) 
    	{		txt_süre.setVisible(true);	}
    }
    
    @FXML
    void event_cik(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
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
        sinif bilgiler=new sinif();
        bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());

        combo_süre.setValue(bilgiler.getMasa_süre());
        combo_yiyecek.setValue(bilgiler.getYiyecek());
        txt_ad_soyad.setText(bilgiler.getAd_soyad());
        Sldr_fiyat.setValue(bilgiler.getFiyat());
        lbl_tarih.setText(bilgiler.getTarih());
        lbl_fiyat.setText(bilgiler.getFiyat()+"");
        if (bilgiler.getMasa_numara().equals(masa_1.getText())) {masa_1.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_2.getText())) {masa_2.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_3.getText())) {masa_3.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_4.getText())) {masa_4.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_5.getText())) {masa_5.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_6.getText())) {masa_6.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_7.getText())) {masa_7.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_8.getText())) {masa_8.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_9.getText())) {masa_9.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_10.getText())) {masa_10.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_11.getText())) {masa_11.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_12.getText())) {masa_12.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_13.getText())) {masa_13.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_14.getText())) {masa_14.setSelected(true);}
        if (bilgiler.getMasa_numara().equals(masa_15.getText())) {masa_15.setSelected(true);}
       
    	if (masa_1.getText().equals("Masa 1")) {resim1.setStyle("-fx-border-color:blue");}
  
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
    	sql="insert into masa_kayit(masa_numara,masa_süre,ad_soyad,tarih,yiyecek,fiyat) values(?,?,?,?,?,?)";
     	//	id,masa_numara,masa_süre,ad_soyad,tarih,yiyecek,fiyat
    	String süre=combo_süre.getSelectionModel().getSelectedItem().trim();
    	String yiyecek=combo_yiyecek.getSelectionModel().getSelectedItem().trim();
    	Double fiyat=Double.parseDouble(lbl_fiyat.getText());
    	String tarih=String.valueOf(data_tarih.getValue());
    	
    	String tür="";
    	if (süre.equals("Süresiz")) 
    	{tür=txt_süre.getText().trim();}
    	else 
    	{tür=combo_süre.getSelectionModel().getSelectedItem().trim();}
    	
    	String masa="";
    	if (masa_1.isSelected()) {masa=masa_1.getText();}
    	if (masa_2.isSelected()) {masa=masa_2.getText();}
    	if (masa_3.isSelected()) {masa=masa_3.getText();}
    	if (masa_4.isSelected()) {masa=masa_4.getText();}
    	if (masa_5.isSelected()) {masa=masa_5.getText();}
    	if (masa_6.isSelected()) {masa=masa_6.getText();}
    	if (masa_7.isSelected()) {masa=masa_7.getText();}
    	if (masa_8.isSelected()) {masa=masa_8.getText();}
    	if (masa_9.isSelected()) {masa=masa_9.getText();}
    	if (masa_10.isSelected()) {masa=masa_10.getText();}
    	if (masa_11.isSelected()) {masa=masa_11.getText();}
    	if (masa_12.isSelected()) {masa=masa_12.getText();}
    	if (masa_13.isSelected()) {masa=masa_13.getText();}
    	if (masa_14.isSelected()) {masa=masa_14.getText();}
    	if (masa_15.isSelected()) {masa=masa_15.getText();}
   
    	try 
    	{
    		sorguýfadesý=baglantý.prepareStatement(sql);
    		sorguýfadesý.setString(1, masa.trim());
    		sorguýfadesý.setString(2, tür.trim());
    		sorguýfadesý.setString(3, txt_ad_soyad.getText().trim());
    		sorguýfadesý.setString(4, tarih.trim());
    		sorguýfadesý.setString(5, yiyecek.trim());
    		sorguýfadesý.setDouble(6, fiyat);
    		sorguýfadesý.executeUpdate();			
		} 
    	catch (SQLException e) {e.printStackTrace();}
    	mesAlert.setContentText("Ekleme iþlemi Baþarýlý bir Þekilde Gerçekleþti");	
    	 }
       	býlgýler(tableviev);
    }

    @FXML
    void event_güncelle(ActionEvent event) {
        sinif bilgiler = new sinif();
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
            sql = "update masa_kayit set masa_numara=?, masa_süre=?,ad_soyad=?, tarih=? , yiyecek=?, fiyat=? where id=?";
        	//	id,masa_numara,masa_süre,ad_soyad,tarih,yiyecek,fiyat
        	String süre=combo_süre.getSelectionModel().getSelectedItem().trim();
        	String yiyecek=combo_yiyecek.getSelectionModel().getSelectedItem().trim();
        	Double fiyat=Double.parseDouble(lbl_fiyat.getText());
        	String tarih=String.valueOf(data_tarih.getValue());
        	
        	String tür="";
        	if (süre.equals("Süresiz")) 
        	{tür=txt_süre.getText().trim();}
        	else 
        	{tür=combo_süre.getSelectionModel().getSelectedItem().trim();}
        	
        	String masa="";
        	if (masa_1.isSelected()) {masa=masa_1.getText();}
        	if (masa_2.isSelected()) {masa=masa_2.getText();}
        	if (masa_3.isSelected()) {masa=masa_3.getText();}
        	if (masa_4.isSelected()) {masa=masa_4.getText();}
        	if (masa_5.isSelected()) {masa=masa_5.getText();}
        	if (masa_6.isSelected()) {masa=masa_6.getText();}
        	if (masa_7.isSelected()) {masa=masa_7.getText();}
        	if (masa_8.isSelected()) {masa=masa_8.getText();}
        	if (masa_9.isSelected()) {masa=masa_9.getText();}
        	if (masa_10.isSelected()) {masa=masa_10.getText();}
        	if (masa_11.isSelected()) {masa=masa_11.getText();}
        	if (masa_12.isSelected()) {masa=masa_12.getText();}
        	if (masa_13.isSelected()) {masa=masa_13.getText();}
        	if (masa_14.isSelected()) {masa=masa_14.getText();}
        	if (masa_15.isSelected()) {masa=masa_15.getText();}
        
        	try 
        	{
        		sorguýfadesý=baglantý.prepareStatement(sql);
        		sorguýfadesý.setString(1, masa.trim());
        		sorguýfadesý.setString(2, tür.trim());
        		sorguýfadesý.setString(3, txt_ad_soyad.getText().trim());
        		sorguýfadesý.setString(4, tarih.trim());
        		sorguýfadesý.setString(5, yiyecek.trim());
        		sorguýfadesý.setDouble(6, fiyat);
        		sorguýfadesý.setInt(7, ids);
        		sorguýfadesý.executeUpdate();			
    		} 
        	catch (SQLException e) {e.printStackTrace();}
   	
        }
           	býlgýler(tableviev);
    }

    @FXML
    void event_sil(ActionEvent event) {
    	sinif bilgiler=new sinif();
        bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());

        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyarý Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="delete from masa_kayit where id=?";
            try {
                sorguýfadesý=baglantý.prepareStatement(sql);
                sorguýfadesý.setInt(1, t_id);
                sorguýfadesý.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
        }
        býlgýler(tableviev);
    }

    @FXML
    void event_süpür(ActionEvent event) {
    		süpür();
    }
    
    @FXML
    public void süpür() {
        combo_süre.getSelectionModel().select(-1);
        combo_yiyecek.getSelectionModel().select(-1);
        Sldr_fiyat.setValue(2);
        txt_ad_soyad.setText("");
        lbl_fiyat.setText(String.valueOf(2));
        lbl_tarih.setText(null);
        lbl_tarih_aralik.setText(null);
        data_tarih.setShowWeekNumbers(false);
        masa_1.setSelected(false);
        masa_2.setSelected(false);
        masa_3.setSelected(false);
        masa_4.setSelected(false);
        masa_5.setSelected(false);
        masa_6.setSelected(false);
        masa_7.setSelected(false);
        masa_8.setSelected(false);
        masa_9.setSelected(false);
        masa_10.setSelected(false);
        masa_11.setSelected(false);
        masa_12.setSelected(false);
        masa_13.setSelected(false);
        masa_14.setSelected(false);
        masa_15.setSelected(false);
        
    
    }
   public void býlgýler(TableView<sinif> tablo) {
	   sql="select*from masa_kayit";
	   
   	try {
			sorguýfadesý=baglantý.prepareStatement(sql);
			getirilen=sorguýfadesý.executeQuery();
			ObservableList<sinif> veriler=FXCollections.observableArrayList();
			while(getirilen.next()) 
			{
veriler.add(new sinif(getirilen.getInt("id"),getirilen.getString("masa_numara"),getirilen.getString("masa_süre"),
getirilen.getString("ad_soyad"),getirilen.getString("tarih"),getirilen.getString("yiyecek"),
getirilen.getDouble("fiyat")));
			}
		//	id,masa_numara,masa_süre,ad_soyad,tarih,yiyecek,fiyat

			colon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			colon_masa_numara.setCellValueFactory(new PropertyValueFactory<>("masa_numara"));
			colon_süre.setCellValueFactory(new PropertyValueFactory<>("masa_süre"));
			colon_adsoyad.setCellValueFactory(new PropertyValueFactory<>("ad_soyad"));
			colon_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
			colon_yiyecek.setCellValueFactory(new PropertyValueFactory<>("yiyecek"));
			colon_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
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
		   combo_süre.getItems().addAll("30 dakika", "45 dakika", "60 dakika", "2 saat", "Süresiz","Aç Kapa");
		   combo_yiyecek.getItems().addAll("Çay", "Kahve", "Tost", "Soda","Çay + Tost","Kola","kola + Tost");
	        Sldr_fiyat.valueProperty().addListener((obs, OldValue, newValue) -> {lbl_fiyat.setText(newValue.toString());
	        });
	        býlgýler(tableviev);
	       txt_süre.setVisible(false);
	}
}
