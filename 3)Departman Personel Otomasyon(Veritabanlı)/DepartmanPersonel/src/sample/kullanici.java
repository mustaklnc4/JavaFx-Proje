package sample;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class kullanici {
    @FXML
    private AnchorPane anasayfa_ancpane;

    @FXML
    private JFXButton btn_geri;

    @FXML
    private JFXButton btn_emekli;

    @FXML
    private JFXButton btn_personel;

    @FXML
    private JFXButton btn_üye;

    @FXML
    void event_emekli(ActionEvent event) {

    }


    @FXML
    void event_personel(ActionEvent event) {

    }

    @FXML
    private ResourceBundle resources;


    @FXML
    private URL location;


    @FXML
    void event_geri(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(anchorPane);
            stage.setScene(scene);
            anasayfa_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void event_üye(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("üyekayit.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(anchorPane);
            stage.setScene(scene);
            anasayfa_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
