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
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public evsatinal() { baglantý=sqlbag.Baglan(); }
    ObservableList<sinif> veriler;
    @FXML
    private ResourceBundle resources;


    @FXML
    private AnchorPane paneSample;
    
    @FXML
    private URL location;

    @FXML
    private TextField tex_tür;

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
    private Button güncelle;

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
        tex_tür.setText(bilgiler.getYapi_türü());
        tex_yas.setText(bilgiler.getYas());
    }

    public void býlgýler(TableView<sinif> tablo) {
   	   sql="select*from evsatis";	
      	try {
   			sorguýfadesý=baglantý.prepareStatement(sql);
   			getirilen=sorguýfadesý.executeQuery();
   			ObservableList<sinif> veriler=FXCollections.observableArrayList();
   			while(getirilen.next()) 
   			{
   veriler.add(new sinif(getirilen.getInt("id"),getirilen.getString("yapi_türü"),getirilen.getString("deger"),
   getirilen.getString("yas"),getirilen.getString("adres")));
   			}	
   			colid.setCellValueFactory(new PropertyValueFactory<>("id"));
   			colyapi.setCellValueFactory(new PropertyValueFactory<>("yapi_türü"));
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
    void event_güncelle(ActionEvent event) {
    	  sinif bilgiler = new sinif();
          bilgiler = tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
          int ids = bilgiler.getId();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Alert Guncelle");
          alert.setHeaderText("Güncelle Uyarý");
          alert.setContentText("Guncelle, Eminmisiniz?");
          Optional<ButtonType> sonucOptional = alert.showAndWait();
          if (sonucOptional.get() == ButtonType.OK) {
              int sira = tableview.getSelectionModel().getSelectedIndex();
              tableview.getItems().set(sira, bilgiler);
              sql = "update evsatis set yapi_türü=?, deger=?,yas=?, adres=? where id=?";
              try 
            	{
            		sorguýfadesý=baglantý.prepareStatement(sql);
            		sorguýfadesý.setString(1, tex_tür.getText().trim());
            		sorguýfadesý.setString(2, tex_deger.getText().trim());
            		sorguýfadesý.setString(3,  tex_yas.getText().trim());
            		sorguýfadesý.setString(4,  tex_adres.getText().trim());
            		sorguýfadesý.setInt(5, ids);
            		sorguýfadesý.executeUpdate();			
            	} 
            	catch (SQLException e) {e.printStackTrace();}
             	býlgýler(tableview);
          }
    }

    @FXML
    void event_kaydet(ActionEvent event) {
      	 Alert mesAlert=new Alert(AlertType.CONFIRMATION);
       	 mesAlert.setTitle("Ekleme Ýþlemi Mesaj");
       	 mesAlert.setHeaderText("Ekleme Uyarýsý");
       	 mesAlert.setContentText("Eklemek istediginize emin misiniz?");
       	 Optional<ButtonType> result=mesAlert.showAndWait();
       	 if (result.get()==ButtonType.OK) 
       	 {
    	sql="insert into evsatis(yapi_türü,deger,yas,adres) values(?,?,?,?)";
    	try 
    	{
    		sorguýfadesý=baglantý.prepareStatement(sql);
    		sorguýfadesý.setString(1, tex_tür.getText().trim());
    		sorguýfadesý.setString(2, tex_deger.getText().trim());
    		sorguýfadesý.setString(3,  tex_yas.getText().trim());
    		sorguýfadesý.setString(4,  tex_adres.getText().trim());
    		sorguýfadesý.executeUpdate();			
    	} 
    	catch (SQLException e) {e.printStackTrace();}
       	 }
     	býlgýler(tableview);
    }

    @FXML
    void event_sil(ActionEvent event) {
    	sinif bilgiler=new sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyarý Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="delete from evsatis where id=?";
            try {
                sorguýfadesý=baglantý.prepareStatement(sql);
                sorguýfadesý.setInt(1, t_id);
                sorguýfadesý.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
        }
        býlgýler(tableview);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		býlgýler(tableview);
	}
}
