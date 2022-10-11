package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class kul {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Ana_Anchorpane;

    @FXML
    private ImageView imageview;

    @FXML
    private Button btn_biletal;

    @FXML
    private Button btn_cikis;

    @FXML
    void click_biletal(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("biletal.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane,1200,700);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void click_cikis(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("giris_kayit.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane,800,700);
		stage.setScene(scene);
		Ana_Anchorpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }


    @FXML
    void initialize() {


    }
}
