package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Listele {
	String sorgu="";
	Connection baglantiConnection=null;
	PreparedStatement sorguPreparedStatement=null;
	ResultSet getirilenResultSet=null;
	
    public Listele() {
    	baglantiConnection=sqlBaglanti.Baglan();	
    }
    
    ObservableList<Kayit> veriler;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane a_kayit;

    @FXML
    private TableView<Kayit> PersonelListele;

    @FXML
    private TableColumn<Kayit, Integer> c_id;

    @FXML
    private TableColumn<Kayit, String> c_adi;

    @FXML
    private TableColumn<Kayit, String> c_mail;

    @FXML
    private TableColumn<Kayit, String> c_sifre;
    
    public void Kayitlar(TableView<Kayit> table) {
    	
    	sorgu="select*from giris";
    	
    	try {
			sorguPreparedStatement=baglantiConnection.prepareStatement(sorgu);
			
			getirilenResultSet=sorguPreparedStatement.executeQuery();
			
			veriler=FXCollections.observableArrayList();
			
			while(getirilenResultSet.next()) 
			{
				veriler.addAll(new Kayit(
						getirilenResultSet.getInt("id"),
						getirilenResultSet.getString("adi"), 
						getirilenResultSet.getString("Mail"),
						getirilenResultSet.getString("Sifre")));
			}
			c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			c_adi.setCellValueFactory(new PropertyValueFactory<>("adi"));
			c_mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
			c_sifre.setCellValueFactory(new PropertyValueFactory<>("Sifre"));
			PersonelListele.setItems(veriler);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    @FXML
    void initialize() {
        Kayitlar(PersonelListele);

    }
}
