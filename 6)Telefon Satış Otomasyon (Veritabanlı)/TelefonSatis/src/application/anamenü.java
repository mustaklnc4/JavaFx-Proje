package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class anamenü {
	String sorgu="";
	Connection baglantiConnection=null;
	PreparedStatement sorguPreparedStatement=null;
	ResultSet getirilenResultSet=null;
    public anamenü() {
    	baglantiConnection=sqlbag.Baglan();	
    }
    ObservableList<üye> veriler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Ana_Anchorpane;

    @FXML
    private ImageView imageview;

    @FXML
    private Button btn_cikis;

    @FXML
    private TableView<üye> tableviev;
    @FXML
    private TableColumn<üye, Integer> col_id;

    @FXML
    private TableColumn<üye, String> col_kullanici;

    @FXML
    private TableColumn<üye, String> col_sifre;

    @FXML
    private TableColumn<üye, String> col_yetki;
    
    @FXML
    private Button btn_telefon;

    @FXML
    private Button btn_kayit;

    @FXML
    private Pane pane_tablo;

    @FXML
    void click_kayit(ActionEvent event) {
    	 pane_tablo.setVisible(true);
    }

    @FXML
    void click_telefon(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("telefon.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }
    
    public void Kayitlar(TableView<üye> table) {
    	
    	sorgu="select*from gir";
    	try {
			sorguPreparedStatement=baglantiConnection.prepareStatement(sorgu);
			getirilenResultSet=sorguPreparedStatement.executeQuery();
			veriler=FXCollections.observableArrayList();
			while(getirilenResultSet.next()) 
			{
				veriler.addAll(new üye(
						getirilenResultSet.getInt("id"),
						getirilenResultSet.getString("kul_ad"), 
						getirilenResultSet.getString("sifre"),
						getirilenResultSet.getString("yetki")));
			}
			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			col_kullanici.setCellValueFactory(new PropertyValueFactory<>("kulad"));
			col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
			col_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
			tableviev.setItems(veriler);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
    
    @FXML
    void click_cikis(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("kaydol_giris.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }


    @FXML
    void initialize() {
    	  Kayitlar(tableviev);
    	    pane_tablo.setVisible(false);
    }
}
