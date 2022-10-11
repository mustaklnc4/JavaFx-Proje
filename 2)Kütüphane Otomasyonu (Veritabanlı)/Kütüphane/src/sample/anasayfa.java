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

public class anasayfa {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anasayfa_ancpane;

    @FXML
    private JFXButton btn_kitap_stok;

    @FXML
    private JFXButton btn_satislar;

    @FXML
    private JFXButton btn_üye_control;

    @FXML
    private JFXButton btn_cikis;

    @FXML
    void event_cikis(ActionEvent event) {
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
    void event_kitap_stok(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("kitaplar.fxml"));
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
    void event_satislar(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("satis.fxml"));
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
    void event_üye_control(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("üyecontrol.fxml"));
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
