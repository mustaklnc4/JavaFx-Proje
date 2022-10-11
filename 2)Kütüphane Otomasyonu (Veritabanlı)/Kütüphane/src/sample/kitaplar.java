package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class kitaplar implements Initializable {
    String sql = "";
    Connection baglantı = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;

    public kitaplar() {
        baglantı = baglanti.Baglan();
    }

    ObservableList<sinif_kitap> veriler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane kitaplar_ancpane;

    @FXML
    private JFXTextField txt_kitap_adi;

    @FXML
    private JFXTextField txt_yazar;

    @FXML
    private JFXTextField txt_telefon;

    @FXML
    private JFXComboBox<String> combo_kitaptürü;

    @FXML
    private JFXRadioButton radio_sıfır;

    @FXML
    private ToggleGroup radio_durum;

    @FXML
    private JFXRadioButton radio_ikinci;

    @FXML
    private JFXDatePicker data_tarih;

    @FXML
    private Label label_tarih;

    @FXML
    private JFXSlider slider_fiyat;

    @FXML
    private Label label_fiyat;

    @FXML
    private JFXButton btn_üye;

    @FXML
    private JFXButton btn_sil;

    @FXML
    private JFXButton btn_güncelle;

    @FXML
    private JFXButton btn_sat;

    @FXML
    private TableView<sinif_kitap> tableview;

    @FXML
    private TableColumn<sinif_kitap, Integer> colon_id;

    @FXML
    private TableColumn<sinif_kitap, String> colon_kitap_adı;

    @FXML
    private TableColumn<sinif_kitap, String> colon_yazar;

    @FXML
    private TableColumn<sinif_kitap, String> colon_kitaptürü;

    @FXML
    private TableColumn<sinif_kitap, String> colon_telefon;

    @FXML
    private TableColumn<sinif_kitap, String> colon_durum;

    @FXML
    private TableColumn<sinif_kitap, Integer> colon_fiyat;

    @FXML
    private TableColumn<sinif_kitap, String> colon_tarih;

    @FXML
    private JFXButton btn_cikis;
    @FXML
    void Data_tarih(ActionEvent event) {
        LocalDate ld=data_tarih.getValue();
        label_tarih.setText(ld.toString());
    }
    @FXML
    void event_cikis(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            kitaplar_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void event_görüntüle(MouseEvent event) {
        sinif_kitap bilgiler=new sinif_kitap();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        combo_kitaptürü.setValue(bilgiler.getKitap_türü());
        txt_kitap_adi.setText(bilgiler.getKitap_adi());
        txt_yazar.setText(bilgiler.getYazar());
        txt_telefon.setText(bilgiler.getTelefon());
        if (bilgiler.getDurum().equals(radio_sıfır.getText())) {radio_sıfır.setSelected(true);}
        if (bilgiler.getDurum().equals(radio_ikinci.getText())) {radio_ikinci.setSelected(true);}
        slider_fiyat.setValue(bilgiler.getFiyat());
        label_tarih.setText(bilgiler.getTarih());
        label_fiyat.setText(bilgiler.getFiyat()+"");
    }

    @FXML
    void event_güncelle(ActionEvent event) {
        sinif_kitap bilgiler = new sinif_kitap();
        bilgiler = tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int t_id = bilgiler.getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert Guncelle");
        alert.setHeaderText("Güncelle Uyarı");
        alert.setContentText("Guncelle, Eminmisiniz?");

        Optional<ButtonType> sonucOptional = alert.showAndWait();
        if (sonucOptional.get() == ButtonType.OK) {
            int sira = tableview.getSelectionModel().getSelectedIndex();
            tableview.getItems().set(sira, bilgiler);
            sql = "update kitaplar set kitap_adi=?, yazar=?,Kitap_türü=?, telefon=? , durum=?, fiyat=?, tarih=? where id=?";

            String skitaptürü = combo_kitaptürü.getSelectionModel().getSelectedItem().trim();

            String sdurum = "";
            if (radio_ikinci.isSelected()) {
                sdurum= radio_ikinci.getText();
            }
            if (radio_sıfır.isSelected()) {
                sdurum = radio_sıfır.getText();
            }
            Double fiyat= Double.parseDouble(label_fiyat.getText());
            String starih= String.valueOf(data_tarih.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_kitap_adi.getText().trim());
                sorguifadesi.setString(2, txt_yazar.getText().trim());
                sorguifadesi.setString(3, skitaptürü.trim());
                sorguifadesi.setString(4, txt_telefon.getText().trim());
                sorguifadesi.setString(5, sdurum.trim());
                sorguifadesi.setDouble(6, fiyat);
                sorguifadesi.setString(7, starih.trim());
                sorguifadesi.setInt(8, t_id);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        bılgıler(tableview);
        süpür();
    }

    @FXML
    void event_sat(ActionEvent event) {
        sinif_kitap bilgiler=new sinif_kitap();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Satış işlemi");
        mesAlert.setHeaderText("Satış işlemi");
        mesAlert.setContentText("Satıl yapılsın mı ? ");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="insert into satislar(kitap_adi,yazar,Kitap_türü,telefon,durum,fiyat,tarih) values(?,?,?,?,?,?,?)";
            //id,kitap_adi,yazar,Kitap_türü,telefon,durum,fiyat,tarih;
            String skitaptürü = combo_kitaptürü.getSelectionModel().getSelectedItem().trim();
            String sdurum = "";
            if (radio_ikinci.isSelected()) {
                sdurum= radio_ikinci.getText();
            }
            if (radio_sıfır.isSelected()) {
                sdurum = radio_sıfır.getText();
            }
            Double fiyat= Double.parseDouble(label_fiyat.getText());
            String starih= String.valueOf(data_tarih.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_kitap_adi.getText().trim());
                sorguifadesi.setString(2, txt_yazar.getText().trim());
                sorguifadesi.setString(3, skitaptürü.trim());
                sorguifadesi.setString(4, txt_telefon.getText().trim());
                sorguifadesi.setString(5, sdurum.trim());
                sorguifadesi.setDouble(6, fiyat);
                sorguifadesi.setString(7, starih.trim());
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            bılgıler(tableview);

            sql="delete from kitaplar where id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, t_id);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}

        }
        bılgıler(tableview);
        süpür();
    }

    @FXML
    void event_sil(ActionEvent event) {
        sinif_kitap bilgiler=new sinif_kitap();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int t_id=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyarı Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="delete from kitaplar where id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, t_id);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
        }
        bılgıler(tableview);
        süpür();
    }

    @FXML
    void event_üye(ActionEvent event) {

        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle(" Ekleme");
        mesAlert.setHeaderText("Dikkat Kayıt");
        mesAlert.setContentText("Kayıt ekleme ?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="insert into kitaplar(kitap_adi,yazar,Kitap_türü,telefon,durum,fiyat,tarih) values(?,?,?,?,?,?,?)";
            //id,kitap_adi,yazar,Kitap_türü,telefon,durum,fiyat,tarih;
            String skitaptürü = combo_kitaptürü.getSelectionModel().getSelectedItem().trim();

            String sdurum = "";
            if (radio_ikinci.isSelected()) {
                sdurum= radio_ikinci.getText();
            }
            if (radio_sıfır.isSelected()) {
                sdurum = radio_sıfır.getText();
            }
            Double fiyat= Double.parseDouble(label_fiyat.getText());
            String starih= String.valueOf(data_tarih.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_kitap_adi.getText().trim());
                sorguifadesi.setString(2, txt_yazar.getText().trim());
                sorguifadesi.setString(3, skitaptürü.trim());
                sorguifadesi.setString(4, txt_telefon.getText().trim());
                sorguifadesi.setString(5, sdurum.trim());
                sorguifadesi.setDouble(6, fiyat);
                sorguifadesi.setString(7, starih.trim());
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            süpür();
            bılgıler(tableview);
        }

    }
    @FXML
    public void süpür() {
        combo_kitaptürü.getSelectionModel().select(-1);
        slider_fiyat.setValue(5);
        txt_yazar.setText("");
        txt_kitap_adi.setText("");
        txt_telefon.setText("");
        label_fiyat.setText(String.valueOf(5));
        radio_sıfır.setSelected(false);
        radio_ikinci.setSelected(false);
        label_tarih.setText(null);
        data_tarih.setShowWeekNumbers(false);
    }
    public void bılgıler(TableView<sinif_kitap> tablo)
    {
        sql = "select*from kitaplar";

        try {
            sorguifadesi = baglantı.prepareStatement(sql);
            getirilen = sorguifadesi.executeQuery();
            ObservableList<sinif_kitap> veriler = FXCollections.observableArrayList();
            while (getirilen.next()) {
                veriler.add(new sinif_kitap(getirilen.getInt("id"),
                        getirilen.getString("kitap_adi"), getirilen.getString("yazar"),
                        getirilen.getString("Kitap_türü"), getirilen.getString("telefon"),
                        getirilen.getString("durum"), getirilen.getDouble("fiyat"),
                        getirilen.getString("tarih")));
            }
            colon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            colon_kitap_adı.setCellValueFactory(new PropertyValueFactory<>("kitap_adi"));
            colon_yazar.setCellValueFactory(new PropertyValueFactory<>("yazar"));
            colon_kitaptürü.setCellValueFactory(new PropertyValueFactory<>("Kitap_türü"));
            colon_telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            colon_durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
            colon_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
            colon_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            tableview.setItems(veriler);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo_kitaptürü.getItems().addAll("Hikaye", "Roman", "Makale", "Şiir", "Biyografi","Çocuk","Gezi","Anı");
        slider_fiyat.valueProperty().addListener((obs, OldValue, newValue) -> {label_fiyat.setText(newValue.toString());
        });
        bılgıler(tableview);
    }
}
