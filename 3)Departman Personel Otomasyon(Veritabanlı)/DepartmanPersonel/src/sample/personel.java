package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class personel implements Initializable
{
    String sql = "";
    Connection baglantı = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;

    public personel() {
        baglantı = baglanti.Baglan();
    }

    ObservableList<personel_sinif> veriler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane personel_ancpane;

    @FXML
    private TableView<personel_sinif> tableview;

    @FXML
    private TableColumn<personel, Integer> colon_id;

    @FXML
    private TableColumn<personel, String> colon_ad;

    @FXML
    private TableColumn<personel, String> colon_soyad;

    @FXML
    private TableColumn<personel, String> colon_tc;

    @FXML
    private TableColumn<personel, String> colon_sicilno;

    @FXML
    private TableColumn<personel, String> colon_telefon;

    @FXML
    private TableColumn<personel, String> colon_adres;

    @FXML
    private TableColumn<personel, String> colon_departman;

    @FXML
    private TableColumn<personel, String> colon_durum;

    @FXML
    private TableColumn<personel, String> colon_tarih_baslama;

    @FXML
    private TableColumn<personel, String> colon_tarih_bitis;

    @FXML
    private TableColumn<personel, String> colon_iban;

    @FXML
    private TableColumn<personel, Integer> colon_maas;


    @FXML
    private JFXButton btn_geri;

    @FXML
    private JFXButton btn_üye;

    @FXML
    private JFXButton btn_sil;

    @FXML
    private JFXButton btn_güncelle;

    @FXML
    private JFXButton btn_emekli_yap;

    @FXML
    private JFXTextField txt_ad;

    @FXML
    private JFXTextField txt_soyad;

    @FXML
    private JFXTextField txt_tc;

    @FXML
    private JFXTextField txt_sicil;

    @FXML
    private JFXTextField txt_telefon;

    @FXML
    private JFXTextField txt_adres;

    @FXML
    private JFXTextField txt_iban;

    @FXML
    private JFXComboBox<String> combo_departman;

    @FXML
    private JFXDatePicker data_tarih_baslangic;

    @FXML
    private JFXDatePicker data_tarih_bitis;

    @FXML
    private JFXSlider slider_maas;

    @FXML
    private Label label_maas_deger;

    @FXML
    private JFXRadioButton radio_calisiyor;

    @FXML
    private ToggleGroup radio_durum;

    @FXML
    private JFXRadioButton radio_izinli;

    @FXML
    private JFXRadioButton radio_emekli;

    @FXML
    private Label label_baslama;

    @FXML
    private Label label_bitis;

    @FXML
    void data_tarih_bitis(ActionEvent event) {
        LocalDate ld=data_tarih_bitis.getValue();
        label_bitis.setText(ld.toString());
    }
    @FXML
    void data_tarih_baslangic(ActionEvent event) {
        LocalDate ld=data_tarih_baslangic.getValue();
        label_baslama.setText(ld.toString());
    }

    @FXML
    void event_emekli_yap(ActionEvent event) {
        personel_sinif bilgiler=new personel_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int seciliId=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("İzin İşlemi Mesaj");
        mesAlert.setHeaderText("İzin Uyarısı");
        mesAlert.setContentText("İzin Etmeye emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="insert into izinler(ad,soyad,tc,sicilno,telefon,adres,departman,durum,tarih_baslangic,tarih_bitis,iban,maaş) values(?,?,?,?,?,?,?,?,?,?,?,?)";

            String secilidepartman = combo_departman.getSelectionModel().getSelectedItem().trim();

            String secilidurum = "";
            if (radio_calisiyor.isSelected()) {
                secilidurum = radio_calisiyor.getText();
            }
            if (radio_emekli.isSelected()) {
                secilidurum = radio_emekli.getText();
            }
            if (radio_izinli.isSelected()) {
                secilidurum = radio_izinli.getText();
            }

            Double maas = Double.parseDouble(label_maas_deger.getText());

            String secilitarih_baslangic = String.valueOf(data_tarih_baslangic.getValue());
            String secilitarih_bitis = String.valueOf(data_tarih_bitis.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_ad.getText().trim());
                sorguifadesi.setString(2, txt_soyad.getText().trim());
                sorguifadesi.setString(3, txt_tc.getText().trim());
                sorguifadesi.setString(4, txt_sicil.getText().trim());
                sorguifadesi.setString(5, txt_telefon.getText().trim());
                sorguifadesi.setString(6, txt_adres.getText().trim());
                sorguifadesi.setString(7, secilidepartman.trim());
                sorguifadesi.setString(8, secilidurum.trim());
                sorguifadesi.setString(9, secilitarih_baslangic.trim());
                sorguifadesi.setString(10, secilitarih_bitis.trim());
                sorguifadesi.setString(11, txt_iban.getText().trim());
                sorguifadesi.setDouble(12, maas);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        bılgıler(tableview);

            sql="delete from personel where Id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, seciliId);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
            Temizle();
        }

        if (result.get()==ButtonType.CANCEL) { mesAlert.setContentText("İzin islemi iptal oldu"); }
        bılgıler(tableview);

    }

    @FXML
    void event_geri(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            personel_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void event_görüntüle(MouseEvent event) {
        personel_sinif bilgiler=new personel_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        combo_departman.setValue(bilgiler.getDepartman());
        txt_ad.setText(bilgiler.getAd());
        txt_adres.setText(bilgiler.getAdres());
        txt_tc.setText(bilgiler.getTc());
        txt_iban.setText(bilgiler.getIban());
        txt_telefon.setText(bilgiler.getTelefon());
        txt_sicil.setText(bilgiler.getSicilno());
        txt_soyad.setText(bilgiler.getSoyad());
        if(bilgiler.getDurum().equals(radio_izinli.getText())){radio_izinli.setSelected(true);}
        if (bilgiler.getDurum().equals(radio_emekli.getText())) {radio_emekli.setSelected(true);}
        if (bilgiler.getDurum().equals(radio_calisiyor.getText())) {radio_calisiyor.setSelected(true);}
        slider_maas.setValue(bilgiler.getMaas());
        label_baslama.setText(bilgiler.getTarih());
        label_bitis.setText(bilgiler.getTarih_bitis());
    }

    @FXML
    void event_güncelle(ActionEvent event) {
        personel_sinif bilgiler = new personel_sinif();
        bilgiler = tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int seciliId = bilgiler.getId();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Guncelleme");
        alert.setHeaderText("Uyari");
        alert.setContentText("Guncellemek istediginize Emin misini?");

        Optional<ButtonType> sonucOptional = alert.showAndWait();
        if (sonucOptional.get() == ButtonType.OK) {
            int sira = tableview.getSelectionModel().getSelectedIndex();
            tableview.getItems().set(sira, bilgiler);
            sql = "update personel set ad=?, soyad=?,tc=?, sicilno=? , telefon=?, adres=?, departman=?,durum=?,tarih_baslangic=?,tarih_bitis=?,iban=?,maaş=? where Id=?";

            String secilidepartman = combo_departman.getSelectionModel().getSelectedItem().trim();

            String secilidurum = "";
            if (radio_calisiyor.isSelected()) {
                secilidurum = radio_calisiyor.getText();
            }
            if (radio_emekli.isSelected()) {
                secilidurum = radio_emekli.getText();
            }
            if (radio_izinli.isSelected()) {
                secilidurum = radio_izinli.getText();
            }

            Double maas = Double.parseDouble(label_maas_deger.getText());

            String secilitarih_baslangic = String.valueOf(data_tarih_baslangic.getValue());
            String secilitarih_bitis = String.valueOf(data_tarih_bitis.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_ad.getText().trim());
                sorguifadesi.setString(2, txt_soyad.getText().trim());
                sorguifadesi.setString(3, txt_tc.getText().trim());
                sorguifadesi.setString(4, txt_sicil.getText().trim());
                sorguifadesi.setString(5, txt_telefon.getText().trim());
                sorguifadesi.setString(6, txt_adres.getText().trim());
                sorguifadesi.setString(7, secilidepartman.trim());
                sorguifadesi.setString(8, secilidurum.trim());
                sorguifadesi.setString(9, secilitarih_baslangic.trim());
                sorguifadesi.setString(10, secilitarih_bitis.trim());
                sorguifadesi.setString(11, txt_iban.getText().trim());
                sorguifadesi.setDouble(12, maas);
                sorguifadesi.setInt(13, seciliId);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            alert.setContentText("Kayit Guncellendi!!!");
              Temizle();
        } else {
            alert.setContentText("Kayit Guncellenmedi!!!");
        }
        bılgıler(tableview);
    }

    @FXML
    void event_sil(ActionEvent event) {
        personel_sinif bilgiler=new personel_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int seciliId=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Kalıcı Silme İşlemi Mesaj");
        mesAlert.setHeaderText("Kalıcı Silme Uyarısı");
        mesAlert.setContentText("Kalıcı Silmeye emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {

            sql="delete from personel where Id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, seciliId);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
            Temizle();
        }

        else { mesAlert.setContentText("Kalıcı Silme islemi iptal oldu"); }
        bılgıler(tableview);
    }

    @FXML
    void event_üye(ActionEvent event)
    {
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Ekleme İşlemi Mesaj");
        mesAlert.setHeaderText("Ekleme Uyarısı");
        mesAlert.setContentText("Ekleme Etmeye emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql = "insert into personel(ad,soyad,tc,sicilno,telefon,adres,departman,durum,tarih_baslangic,tarih_bitis,iban,maaş) values(?,?,?,?,?,?,?,?,?,?,?,?)";

            String secilidepartman = combo_departman.getSelectionModel().getSelectedItem().trim();

            String secilidurum = "";
            if (radio_calisiyor.isSelected()) {
                secilidurum = radio_calisiyor.getText();
            }
            if (radio_emekli.isSelected()) {
                secilidurum = radio_emekli.getText();
            }
            if (radio_izinli.isSelected()) {
                secilidurum = radio_izinli.getText();
            }

            Double maas = Double.parseDouble(label_maas_deger.getText());

            String secilitarih_baslangic = String.valueOf(data_tarih_baslangic.getValue());
            String secilitarih_bitis = String.valueOf(data_tarih_bitis.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_ad.getText().trim());
                sorguifadesi.setString(2, txt_soyad.getText().trim());
                sorguifadesi.setString(3, txt_tc.getText().trim());
                sorguifadesi.setString(4, txt_sicil.getText().trim());
                sorguifadesi.setString(5, txt_telefon.getText().trim());
                sorguifadesi.setString(6, txt_adres.getText().trim());
                sorguifadesi.setString(7, secilidepartman.trim());
                sorguifadesi.setString(8, secilidurum.trim());
                sorguifadesi.setString(9, secilitarih_baslangic.trim());
                sorguifadesi.setString(10, secilitarih_bitis.trim());
                sorguifadesi.setString(11, txt_iban.getText().trim());
                sorguifadesi.setDouble(12, maas);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
               Temizle();
            bılgıler(tableview);

        }
    }
    @FXML
    public void Temizle() {
        combo_departman.getSelectionModel().select(-1);
        slider_maas.setValue(0);
        txt_soyad.setText("");
        txt_adres.setText("");
        txt_tc.setText("");
        txt_sicil.setText("");
        txt_ad.setText("");
        txt_telefon.setText("");
        txt_iban.setText("");
        label_maas_deger.setText(String.valueOf(1000));
        radio_calisiyor.setSelected(false);
        radio_emekli.setSelected(false);
        radio_izinli.setSelected(false);
        label_bitis.setText(null);
        label_baslama.setText(null);
        label_maas_deger.setText(String.valueOf(1000));
        slider_maas.setValue(1000);
        data_tarih_baslangic.setShowWeekNumbers(false);
        data_tarih_bitis.setShowWeekNumbers(false);
    }

    public void bılgıler(TableView<personel_sinif> tablo)
    {
        sql = "select*from personel";

        try {
            sorguifadesi = baglantı.prepareStatement(sql);
            getirilen = sorguifadesi.executeQuery();
            ObservableList<personel_sinif> veriler = FXCollections.observableArrayList();
            while (getirilen.next()) {
                veriler.add(new personel_sinif(getirilen.getInt("id"), getirilen.getString("ad"), getirilen.getString("soyad"),
                        getirilen.getString("tc"), getirilen.getString("sicilno"), getirilen.getString("telefon"),
                        getirilen.getString("adres"), getirilen.getString("departman"), getirilen.getString("durum"),
                        getirilen.getString("tarih_baslangic"), getirilen.getString("tarih_bitis"), getirilen.getString("iban"),
                        getirilen.getInt("maaş")));
            }

            colon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            colon_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
            colon_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
            colon_tc.setCellValueFactory(new PropertyValueFactory<>("tc"));
            colon_sicilno.setCellValueFactory(new PropertyValueFactory<>("sicilno"));
            colon_telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            colon_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
            colon_departman.setCellValueFactory(new PropertyValueFactory<>("departman"));
            colon_durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
            colon_tarih_baslama.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            colon_tarih_bitis.setCellValueFactory(new PropertyValueFactory<>("tarih_bitis"));
            colon_iban.setCellValueFactory(new PropertyValueFactory<>("iban"));
            colon_maas.setCellValueFactory(new PropertyValueFactory<>("maas"));
            tableview.setItems(veriler);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        combo_departman.getItems().addAll("Yazılım", "Memur", "İşletme", "Müdür", "Temizlik Personel");
        slider_maas.valueProperty().addListener((obs, OldValue, newValue) -> {label_maas_deger.setText(newValue.toString());
        });
        bılgıler(tableview);
    }
}
