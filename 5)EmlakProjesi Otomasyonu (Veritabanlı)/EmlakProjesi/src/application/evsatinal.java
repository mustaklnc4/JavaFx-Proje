package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class evsatinal implements Initializable {
	String sql="";
	Connection baglant�=null;
	PreparedStatement sorgu�fades�=null;
	ResultSet getirilen=null;
	public evsatinal() { baglant�=sqlbag.Baglan(); }
    ObservableList<sinif> veriler;
    @FXML
    private ResourceBundle resources;


    @FXML
    private AnchorPane paneSample;
    
    @FXML
    private URL location;

    @FXML
    private TextField tex_t�r;

    @FXML
    private TextField tex_deger;

    @FXML
    private TextField tex_yas;

    @FXML
    private TextField tex_adres;

    @FXML
    private Button kaydet;

    @FXML
    private Button sil;

    @FXML
    private Button g�ncelle;

    @FXML
    private Button geri;

    @FXML
    private TableView<sinif> tableview;

    @FXML
    private TableColumn<sinif, String> colid;
    
    @FXML
    private TableColumn<sinif, String> colyapi;

    @FXML
    private TableColumn<sinif, String> coldeger;

    @FXML
    private TableColumn<sinif, String> colyas;

    @FXML
    private TableColumn<sinif, String> coladres;
    

    @FXML
    void mouse_click(MouseEvent event) {
        sinif bilgiler=new sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        tex_adres.setText(bilgiler.getAdres());
        tex_deger.setText(bilgiler.getDeger());
        tex_t�r.setText(bilgiler.getYapi_t�r�());
        tex_yas.setText(bilgiler.getYas());
    }

    public void b�lg�ler(TableView<sinif> tablo) {
   	   sql="select*from evsatis";	
      	try {
   			sorgu�fades�=baglant�.prepareStatement(sql);
   			getirilen=sorgu�fades�.executeQuery();
   			ObservableList<sinif> veriler=FXCollections.observableArrayList();
   			while(getirilen.next()) 
   			{
   veriler.add(new sinif(getirilen.getInt("id"),getirilen.getString("yapi_t�r�"),getirilen.getString("deger"),
   getirilen.getString("yas"),getirilen.getString("adres")));
   			}	
   			colid.setCellValueFactory(new PropertyValueFactory<>("id"));
   			colyapi.setCellValueFactory(new PropertyValueFactory<>("yapi_t�r�"));
   			coldeger.setCellValueFactory(new PropertyValueFactory<>("deger"));
   			colyas.setCellValueFactory(new PropertyValueFactory<>("yas"));
   			coladres.setCellValueFactory(new PropertyValueFactory<>("adres"));
   			tableview.setItems(veriler);

   		} 
      	catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}	
      }
    
    @FXML
    void event_geri(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("anamen�.fxml"));
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
    void event_g�ncelle(ActionEvent event) {
    	  sinif bilgiler = new sinif();
          bilgiler = tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
          int ids = bilgiler.getId();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Alert Guncelle");
          alert.setHeaderText("G�ncelle Uyar�");
          alert.setContentText("Guncelle, Eminmisiniz?");
          Optional<ButtonType> sonucOptional = alert.showAndWait();
          if (sonucOptional.get() == ButtonType.OK) {
              int sira = tableview.getSelectionModel().getSelectedIndex();
              tableview.getItems().set(sira, bilgiler);
              sql = "update evsatis set yapi_t�r�=?, deger=?,yas=?, adres=? where id=?";
              try 
            	{
            		sorgu�fades�=baglant�.prepareStatement(sql);
            		sorgu�fades�.setString(1, tex_t�r.getText().trim());
            		sorgu�fades�.setString(2, tex_deger.getText().trim());
            		sorgu�fades�.setString(3,  tex_yas.getText().trim());
            		sorgu�fades�.setString(4,  tex_adres.getText().trim());
            		sorgu�fades�.setInt(5, ids);
            		sorgu�fades�.executeUpdate();			
            	} 
            	catch (SQLException e) {e.printStackTrace();}
             	b�lg�ler(tableview);
          }
    }

    @FXML
    void event_kaydet(ActionEvent event) {
      	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
       	 mesAlert.setTitle("Ekleme ��lemi Mesaj");
       	 mesAlert.setHeaderText("Ekleme Uyar�s�");
       	 mesAlert.setContentText("Eklemek istediginize emin misiniz?");
       	 Optional<ButtonType> result=mesAlert.showAndWait();
       	 if (result.get()==ButtonType.OK) 
       	 {
    	sql="insert into evsatis(yapi_t�r�,deger,yas,adres) values(?,?,?,?)";
    	try 
    	{
    		sorgu�fades�=baglant�.prepareStatement(sql);
    		sorgu�fades�.setString(1, tex_t�r.getText().trim());
    		sorgu�fades�.setString(2, tex_deger.getText().trim());
    		sorgu�fades�.setString(3,  tex_yas.getText().trim());
    		sorgu�fades�.setString(4,  tex_adres.getText().trim());
    		sorgu�fades�.executeUpdate();			
    	} 
    	catch (SQLException e) {e.printStackTrace();}
       	 }
     	b�lg�ler(tableview);
    }

    @FXML
    void event_sil(ActionEvent event) {
    	sinif bilgiler=new sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyar� Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="delete from evsatis where id=?";
            try {
                sorgu�fades�=baglant�.prepareStatement(sql);
                sorgu�fades�.setInt(1, t_id);
                sorgu�fades�.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
        }
        b�lg�ler(tableview);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		b�lg�ler(tableview);
	}
}
