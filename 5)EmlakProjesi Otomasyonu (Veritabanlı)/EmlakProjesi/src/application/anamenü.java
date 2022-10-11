package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class anamenü {

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane ancpane;
    
    @FXML
    private URL location;

    @FXML
    private Button emlak;

    @FXML
    private Button cikis;

    @FXML
    void cikis(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("Giris.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane);
		stage.setScene(scene);
		ancpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void emlak(ActionEvent event) {
    	try {
    	AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("evsatinal.fxml"));
		Stage stage=new Stage();
		Scene scene=new Scene(anchorPane);
		stage.setScene(scene);
		ancpane.getScene().getWindow().hide();
		stage.showAndWait();
    	} 
        catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    void initialize() {
        assert emlak != null : "fx:id=\"emlak\" was not injected: check your FXML file 'anamenü.fxml'.";
        assert cikis != null : "fx:id=\"cikis\" was not injected: check your FXML file 'anamenü.fxml'.";

    }
}
