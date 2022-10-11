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
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public coronatakip() { baglant�=Baglant�.Baglan(); }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneSat�slar;
    
    @FXML
    private TableView<Kay�tlar> tableviev;

    @FXML
    private TableView<�yeKayitSinif> tableviev1;
    
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
    private TableColumn<Kay�tlar, Integer > ID;

    @FXML
    private TableColumn<Kay�tlar, String > colon_Adi;

    @FXML
    private TableColumn<Kay�tlar, String > colon_tc;

    @FXML
    private TableColumn<Kay�tlar, String > colon_cinsiyet;

    @FXML
    private TableColumn<Kay�tlar, String > colon_kangrubu;

    @FXML
    private TableColumn<Kay�tlar, String > colon_poliklinik;

    @FXML
    private TableColumn<Kay�tlar, String > colon_hastat�r�;

    @FXML
    private TableColumn<Kay�tlar, String > colon_sehir;

    @FXML
    private TableColumn<Kay�tlar, String > colon_adres;

    @FXML
    private TableColumn<Kay�tlar, String > colon_sigorta;

    @FXML
    private TableColumn<Kay�tlar, Double > colon_ates;

    @FXML
    private TableColumn<Kay�tlar, String > colon_corona;

    @FXML
    private TableColumn<Kay�tlar, String > colon_tarih;

    @FXML
    private TableColumn<Kay�tlar, String > colon_doktor;


    @FXML
    void event_sorgula(ActionEvent event) 
    {
    	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
      	 mesAlert.setTitle("Sorgulama ��lemi Mesaj");
      	 mesAlert.setHeaderText("Sorgulama Uyar�s�");
      	 mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
      	 Optional<ButtonType> result=mesAlert.showAndWait();
      
      	 if (result.get()==ButtonType.OK) 
      	 {
      
        	int sehirs=0;
        	int cinsiyet=0;
        	int ates=0;
        	int veri=0;
        	int corona=0;
        	ObservableList<Kay�tlar>B�t�nkay�tlar;
        	B�t�nkay�tlar=tableviev.getItems();
        	String t�klanansehir=txt_sehir.getText().trim();
        	String t�klanancinsiyet=txt_cinsiyet.getText().trim();	
        	String t�klananates=txt_ates_ort.getText().trim();
        	String t�klanancorona=txt_corona.getText().trim();
        	for(Kay�tlar bilgiKullan�c� :B�t�nkay�tlar) 
        	{	
        		veri=veri+1;

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
        			 sehirs++;
            		XYChart.Series srm=new XYChart.Series();
          			srm.getData().add(new XYChart.Data(t�klananates+" "+t�klanancinsiyet+" "+t�klanansehir,ates/sehirs));
          		  	srm.setName(veri+". Kay�t "+t�klanansehir+" "+t�klanancinsiyet+" "+t�klananates);
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
        if(t�klanansehir.equals("ADANA"))
    	{
        	pcData.add(new PieChart.Data("ADIYAMAN,AFYON,A�RI,AMASYA,�ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
    	}
        if(t�klanansehir.equals("ADIYAMAN"))
    	{
        	pcData.add(new PieChart.Data("ADANA, AFYON, A�RI, AMASYA,�ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
    	}
        if(t�klanansehir.equals("AFYON"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, A�RI, AMASYA,�ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("A�RI"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, AMASYA,�ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("�ANLIURFA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, AMASYA,�STANBUL,BURSA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("�STANBUL"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,AMASYA,BURSA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("BURSA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,�STANBUL,AMASYA,HATAY,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("HATAY"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,�STANBUL,BURSA,AMASYA,ANTEP,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("ANTEP"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,�STANBUL,BURSA,HATAY,AMASYA,ANKARA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("ANKARA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,AMASYA,�ZM�R,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("�ZM�R"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,AMASYA,ANKARA,ANTALYA",veri-1));
     	}
        if(t�klanansehir.equals("ANTALYA"))
     	{
         	pcData.add(new PieChart.Data("ADANA,ADIYAMAN, AFYON, A�RI, �ANLIURFA,�STANBUL,BURSA,HATAY,ANTEP,AMASYA,ANKARA,�ZM�R",veri-1));
     	}
    	pcData.addAll(new PieChart.Data(t�klanansehir,sehirs),new PieChart.Data(cinsiyet+" Ki�i "+t�klanancinsiyet,cinsiyet),new PieChart.Data(corona+" Ki�ide "+t�klanancorona,corona));
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
       		mesAlert.setContentText("��lem �ptal Edildi");
       	}
    }

public void b�lg�ler(TableView<Kay�tlar> tablo) 
{
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


    
    @FXML
    void event_Click(MouseEvent event) {
    	Kay�tlar bilgiler=new Kay�tlar();
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
			paneSat�slar.getScene().getWindow().hide();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void initialize() {
    	b�lg�ler(tableviev);
    }
    
}
