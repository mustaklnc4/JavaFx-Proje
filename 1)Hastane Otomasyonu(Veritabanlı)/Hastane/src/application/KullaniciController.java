package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class KullaniciController implements Initializable {

	String sql="";
	Connection baglantý=null;
	PreparedStatement sorguýfadesý=null;
	ResultSet getirilen=null;
	public KullaniciController() { baglantý=Baglantý.Baglan(); }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageview;

    @FXML
    private ImageView imageview2;
   
    @FXML
    private AnchorPane Ana_Anchorpane;
    
    @FXML
    private Button btn_üyeol;

    @FXML
    private Button btn_cikis;


    @FXML
    private Button btn_randevu;

    @FXML
    private Button btn_coronatakip;




    @FXML
    void click_coronatakip(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("coronatakipkullanýcý.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane,1200,710);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void click_randevu(ActionEvent event) {
     	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("randevu.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane,1200,710);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
	} 
    catch (IOException e) {e.printStackTrace();}

    }

    @FXML
    void click_cikis(ActionEvent event) {
     	try 
     	{
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("Üyegiris.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane,1100,710);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
     	} 
     	catch (IOException e) {e.printStackTrace();}
     	
    }

    @FXML
    void click_üyeol(ActionEvent event) {

		try {
			AnchorPane anchorPane;
			anchorPane = FXMLLoader.load(getClass().getResource("ilküyekaydol.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(anchorPane);
			stage.setScene(scene);
			Ana_Anchorpane.getScene().getWindow().hide();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
