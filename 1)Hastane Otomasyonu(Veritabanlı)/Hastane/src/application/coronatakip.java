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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class coronatakip {
	
	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public coronatakip() { baglantý=Baglantý.Baglan(); }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneSatýslar;
    
    @FXML
    private TableView<Kayýtlar> tableviev;

    @FXML
    private TableView<ÜyeKayitSinif> tableviev1;
    
    @FXML
    private BarChart<String, Number> Salarychart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Button sorgula;

    @FXML
    private Button geri;

    @FXML
    private Label txt_sehir;

    @FXML
    private Label txt_cinsiyet;

    @FXML
    private Label txt_corona;

    @FXML
    private Label txt_ates_ort;

    @FXML
    private Label lbl_deger;

    @FXML
    private PieChart pastadilimi;

    @FXML
    private TableColumn<Kayýtlar, Integer > ID;

    @FXML
    private TableColumn<Kayýtlar, String > colon_Adi;

    @FXML
    private TableColumn<Kayýtlar, String > colon_tc;

    @FXML
    private TableColumn<Kayýtlar, String > colon_cinsiyet;

    @FXML
    private TableColumn<Kayýtlar, String > colon_kangrubu;

    @FXML
    private TableColumn<Kayýtlar, String > colon_poliklinik;

    @FXML
    private TableColumn<Kayýtlar, String > colon_hastatürü;

    @FXML
    private TableColumn<Kayýtlar, String > colon_sehir;

    @FXML
    private TableColumn<Kayýtlar, String > colon_adres;

    @FXML
    private TableColumn<Kayýtlar, String > colon_sigorta;

    @FXML
    private TableColumn<Kayýtlar, Double > colon_ates;

    @FXML
    private TableColumn<Kayýtlar, String > colon_corona;

    @FXML
    private TableColumn<Kayýtlar, String > colon_tarih;

    @FXML
    private TableColumn<Kayýtlar, String > colon_doktor;


    @FXML
    void event_sorgula(ActionEvent event) 
    {
    	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
      	 mesAlert.setTitle("Sorgulama Ýþlemi Mesaj");
      	 mesAlert.setHeaderText("Sorgulama Uyarýsý");
      	 mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
      	 Optional<ButtonType> result=mesAlert.showAndWait();
      
      	 if (result.get()==ButtonType.OK) 
      	 {
      
        	int sehirs=0;
        	int cinsiyet=0;
        	int ates=0;
        	int veri=0;
        	int corona=0;
        	ObservableList<Kayýtlar>Bütünkayýtlar;
        	Bütünkayýtlar=tableviev.getItems();
        	String týklanansehir=txt_sehir.getText().trim();
        	String týklanancinsiyet=txt_cinsiyet.getText().trim();	
        	String týklananates=txt_ates_ort.getText().trim();
        	String týklanancorona=txt_corona.getText().trim();
        	for(Kayýtlar bilgiKullanýcý :Bütünkayýtlar) 
        	{	
        		veri=veri+1;

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
        			 sehirs++;
            		XYChart.Series srm=new XYChart.Series();
          			srm.getData().add(new XYChart.Data(týklananates+" "+týklanancinsiyet+" "+týklanansehir,ates/sehirs));
          		  	srm.setName(veri+". Kayýt "+týklanansehir+" "+týklanancinsiyet+" "+týklananates);
          		  try {
          		  Salarychart.getData().add(srm);
          		  Salarychart.getChildrenUnmodifiable().addAll(x,y); 
          		  Salarychart.setBarGap (3);
          		  Salarychart.setCategoryGap (20);
          		  Salarychart.getChildrenUnmodifiable().clear();

    			} catch (Exception e) {		}

        		} 
        	} 
        	
    	ObservableList<PieChart.Data> pcData=FXCollections.observableArrayList();
        if(týklanansehir.equals("ADANA"))
    	{
        	pcData.add(new PieChart.Data("ADIYAMAN,AFYON,AÐRI,AMASYA,ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
    	}
        if(týklanansehir.equals("ADIYAMAN"))
    	{
        	pcData.add(new PieChart.Data("ADANA, AFYON, AÐRI, AMASYA,ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
    	}
        if(týklanansehir.equals("AFYON"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AÐRI, AMASYA,ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("AÐRI"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AMASYA,ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("ÞANLIURFA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, AMASYA,ÝSTANBUL,BURSA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("ÝSTANBUL"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,AMASYA,BURSA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("BURSA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,ÝSTANBUL,AMASYA,HATAY,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("HATAY"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,ÝSTANBUL,BURSA,AMASYA,ANTEP,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("ANTEP"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,AMASYA,ANKARA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("ANKARA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,AMASYA,ÝZMÝR,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("ÝZMÝR"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,AMASYA,ANKARA,ANTALYA",veri-1));
     	}
        if(týklanansehir.equals("ANTALYA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AÐRI, ÞANLIURFA,ÝSTANBUL,BURSA,HATAY,ANTEP,AMASYA,ANKARA,ÝZMÝR",veri-1));
     	}
    	pcData.addAll(new PieChart.Data(týklanansehir,sehirs),new PieChart.Data(cinsiyet+" Kiþi "+týklanancinsiyet,cinsiyet),new PieChart.Data(corona+" Kiþide "+týklanancorona,corona));
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
    				lbl_deger.setText(String.valueOf(data.getPieValue()) + " Tane");
    			}
    		});
    	}
    	cinsiyet=0;
        sehirs=0;
        veri=0;
        corona=0;
        }
      	if (result.get()==ButtonType.CANCEL)
       	{
       		mesAlert.setContentText("Ýþlem Ýptal Edildi");
       	}
    }

public void býlgýler(TableView<Kayýtlar> tablo) 
{
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


    
    @FXML
    void event_Click(MouseEvent event) {
    	Kayýtlar bilgiler=new Kayýtlar();
   	 bilgiler=tableviev.getItems().get(tableviev.getSelectionModel().getFocusedIndex());
   	  txt_sehir.setText(bilgiler.getIl());
   	  txt_cinsiyet.setText(bilgiler.getCinsiyet());
   	  txt_corona.setText(bilgiler.getCorona());
   	  txt_ates_ort.setText(bilgiler.getAtes()+"");
    }


    @FXML
    void geri(ActionEvent event) {

    	try {
			AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane);
			stage.setScene(scene);
			paneSatýslar.getScene().getWindow().hide();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void initialize() {
    	býlgýler(tableviev);
    }
    
}
