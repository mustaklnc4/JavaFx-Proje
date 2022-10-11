package sample;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

public class oto_kayit implements Initializable
{
    String sql = "";
    Connection baglantı = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;

    public oto_kayit() {
        baglantı = baglanti.Baglan();
    }

    ObservableList<arac_sinif> veriler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private AnchorPane a_otokayit;

    @FXML
    private JFXButton btn_geri;

    @FXML
    private JFXButton btn_üye;

    @FXML
    private JFXButton btn_sil;

    @FXML
    private JFXButton btn_güncelle;

    @FXML
    private JFXButton btn_hazir;

    @FXML
    private TableView<arac_sinif> tableview;

    @FXML
    private TableColumn<arac_sinif, Integer> colon_id;

    @FXML
    private TableColumn<arac_sinif, String>colon_ad_soyad;

    @FXML
    private TableColumn<arac_sinif, String> colon_plaka;

    @FXML
    private TableColumn<arac_sinif, String> colon_tc;

    @FXML
    private TableColumn<arac_sinif, String>colon_telefon;

    @FXML
    private TableColumn<arac_sinif, String> colon_giris_taih;

    @FXML
    private TableColumn<arac_sinif, String> colon_cikis_tarih;

    @FXML
    private TableColumn<arac_sinif, String> colon_paket;

    @FXML
    private TableColumn<arac_sinif, String> colon_marka;

    @FXML
    private TableColumn<arac_sinif, String> colon_model;

    @FXML
    private TableColumn<arac_sinif, Integer> colon_ücret;

    @FXML
    private JFXTextField txt_ad_soyad;

    @FXML
    private JFXTextField txt_plaka;

    @FXML
    private JFXTextField txt_tc;

    @FXML
    private JFXTextField txt_telefon;

    @FXML
    private JFXComboBox<String> cmb_marka;

    @FXML
    private JFXTextField txt_model;

    @FXML
    private JFXComboBox<String> cmb_paket;

    @FXML
    private JFXRadioButton rd_hazir;

    @FXML
    private ToggleGroup radio_durum;

    @FXML
    private JFXRadioButton rd_temizlemede;

    @FXML
    private JFXDatePicker data_giris;

    @FXML
    private Label lbl_giris_tarih;

    @FXML
    private JFXDatePicker data_cikis;

    @FXML
    private Label lbl_ücret;

    @FXML
    private JFXSlider slider_ücret;

    @FXML
    private Label lbl_cikis_tarih;

    @FXML
    void event_görüntüle(MouseEvent event) {
        arac_sinif bilgiler=new arac_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        cmb_paket.setValue(bilgiler.getPaket());
        cmb_marka.setValue(bilgiler.getArac_marka());
        txt_ad_soyad.setText(bilgiler.getMüsteri_ad_soyad());
        txt_model.setText(bilgiler.getArac_model());
        txt_tc.setText(bilgiler.getTc());
        txt_plaka.setText(bilgiler.getPlaka());
        txt_telefon.setText(bilgiler.getTelefon());
        // if (bilgiler.getDurum().equals(rd_temizlemede.getText())) {rd_temizlemede.setSelected(true);}
        // if (bilgiler.getDurum().equals(rd_hazir.getText())) {rd_hazir.setSelected(true);}
        slider_ücret.setValue(bilgiler.getÜcret());
        lbl_giris_tarih.setText(bilgiler.getTarih_giris());
        lbl_cikis_tarih.setText(bilgiler.getTarih_cikis());
        lbl_ücret.setText(bilgiler.getÜcret()+"");
    }

    @FXML
    void data_cikis(ActionEvent event) {
        LocalDate ld=data_cikis.getValue();
        lbl_cikis_tarih.setText(ld.toString());
    }
    @FXML
    void data_giris(ActionEvent event) {
        LocalDate ld=data_giris.getValue();
        lbl_giris_tarih.setText(ld.toString());
    }

    @FXML
    void event_hazir(ActionEvent event) {
        arac_sinif bilgiler=new arac_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int seciliId=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Araç Hazır");
        mesAlert.setHeaderText("Uyarı Araç Hazır");
        mesAlert.setContentText("Araç Hazır, eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="insert into hazır_araclar(müsteri_ad_soyad, plaka, tc, telefon, tarih_giris, tarih_cikis, paket, arac_marka, arac_model, ücret) values(?,?,?,?,?,?,?,?,?,?)";
            String s_marka = cmb_marka.getSelectionModel().getSelectedItem().trim();
            String s_paket = cmb_paket.getSelectionModel().getSelectedItem().trim();
          /*  String secilidurum = "";
            if (rd_hazir.isSelected()) {
                secilidurum = rd_hazir.getText();
            }
            if (rd_temizlemede.isSelected()) {
                secilidurum = rd_temizlemede.getText();
            }*/
            Double ücret = Double.parseDouble(lbl_ücret.getText());

            String s_t_giris= String.valueOf(data_giris.getValue());
            String s_t_cikis= String.valueOf(data_cikis.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_ad_soyad.getText().trim());
                sorguifadesi.setString(2, txt_plaka.getText().trim());
                sorguifadesi.setString(3, txt_tc.getText().trim());
                sorguifadesi.setString(4, txt_telefon.getText().trim());
                sorguifadesi.setString(5, s_t_giris.trim());
                sorguifadesi.setString(6, s_t_cikis.trim());
                sorguifadesi.setString(7, s_paket.trim());
                sorguifadesi.setString(8, s_marka.trim());
                sorguifadesi.setString(9, txt_model.getText().trim());
                sorguifadesi.setDouble(10, ücret);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            bılgıler(tableview);

            sql="delete from üye_kayit where id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, seciliId);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}

        }
        sıfırla();
        if (result.get()==ButtonType.CANCEL) { mesAlert.setContentText("Aarç Hazır İptal ettiniz"); }
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
            a_otokayit.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void event_güncelle(ActionEvent event) {
        arac_sinif bilgiler = new arac_sinif();
        bilgiler = tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int seciliId = bilgiler.getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert Guncelle");
        alert.setHeaderText("Güncelle Uyarı");
        alert.setContentText("Guncelle, Eminmisiniz?");

        Optional<ButtonType> sonucOptional = alert.showAndWait();
        if (sonucOptional.get() == ButtonType.OK) {
            int sira = tableview.getSelectionModel().getSelectedIndex();
            tableview.getItems().set(sira, bilgiler);
            sql = "update üye_kayit set müsteri_ad_soyad=?, plaka=?,tc=?, telefon=? , tarih_giris=?, tarih_cikis=?, paket=?,arac_marka=?,arac_model=?,ücret=? where id=?";
            String s_marka = cmb_marka.getSelectionModel().getSelectedItem().trim();
            String s_paket = cmb_paket.getSelectionModel().getSelectedItem().trim();
           /* String secilidurum = "";
            if (rd_hazir.isSelected()) {
                secilidurum = rd_hazir.getText();
            }
            if (rd_temizlemede.isSelected()) {
                secilidurum = rd_temizlemede.getText();
            }*/
            Double ücret = Double.parseDouble(lbl_ücret.getText());

            String s_t_giris= String.valueOf(data_giris.getValue());
            String s_t_cikis= String.valueOf(data_cikis.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_ad_soyad.getText().trim());
                sorguifadesi.setString(2, txt_plaka.getText().trim());
                sorguifadesi.setString(3, txt_tc.getText().trim());
                sorguifadesi.setString(4, txt_telefon.getText().trim());
                sorguifadesi.setString(5, s_t_giris.trim());
                sorguifadesi.setString(6, s_t_cikis.trim());
                sorguifadesi.setString(7, s_paket.trim());
                sorguifadesi.setString(8, s_marka.trim());
                sorguifadesi.setString(9, txt_model.getText().trim());
                sorguifadesi.setDouble(10, ücret);
                sorguifadesi.setInt(11, seciliId);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            alert.setContentText("Başarılı Güncelleme");
            sıfırla();
        } else {
            alert.setContentText("Başarısız Güncelleme");
        }
        bılgıler(tableview);
    }

    @FXML
    void event_sil(ActionEvent event) {
        arac_sinif bilgiler=new arac_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int seciliId=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Sil");
        mesAlert.setHeaderText("Uyarı Silme");
        mesAlert.setContentText("Sil, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {

            sql="delete from üye_kayit where id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, seciliId);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {e.printStackTrace();}
            sıfırla();
        }

        else { mesAlert.setContentText("Silme iptal ettiniz"); }
        bılgıler(tableview);
    }

    @FXML
    void event_üye(ActionEvent event)
    {
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Alert Ekleme");
        mesAlert.setHeaderText("Uyarı Kayıt");
        mesAlert.setContentText("Kayıt, Eminmisiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {
            sql="insert into üye_kayit(müsteri_ad_soyad,plaka,tc,telefon,tarih_giris,tarih_cikis,paket,arac_marka,arac_model,ücret) values(?,?,?,?,?,?,?,?,?,?)";
            String s_marka = cmb_marka.getSelectionModel().getSelectedItem().trim();
            String s_paket = cmb_paket.getSelectionModel().getSelectedItem().trim();
          /*  String secilidurum = "";
            if (rd_hazir.isSelected()) {
                secilidurum = rd_hazir.getText();
            }
            if (rd_temizlemede.isSelected()) {
                secilidurum = rd_temizlemede.getText();
            }*/
            Double ücret = Double.parseDouble(lbl_ücret.getText());
            String s_t_giris= String.valueOf(data_giris.getValue());
            String s_t_cikis= String.valueOf(data_cikis.getValue());
            try {
                sorguifadesi = baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, txt_ad_soyad.getText().trim());
                sorguifadesi.setString(2, txt_plaka.getText().trim());
                sorguifadesi.setString(3, txt_tc.getText().trim());
                sorguifadesi.setString(4, txt_telefon.getText().trim());
                sorguifadesi.setString(5, s_t_giris.trim());
                sorguifadesi.setString(6, s_t_cikis.trim());
                sorguifadesi.setString(7, s_paket.trim());
                sorguifadesi.setString(8, s_marka.trim());
                sorguifadesi.setString(9, txt_model.getText().trim());
                sorguifadesi.setDouble(10, ücret);
                sorguifadesi.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sıfırla();
            bılgıler(tableview);
        }
    }
    @FXML
    public void sıfırla() {
        cmb_paket.getSelectionModel().select(-1);
        cmb_marka.getSelectionModel().select(-1);
        slider_ücret.setValue(30);
        txt_plaka.setText("");
        txt_model.setText("");
        txt_tc.setText("");
        txt_ad_soyad.setText("");
        txt_telefon.setText("");
        lbl_ücret.setText(String.valueOf(50));
        rd_hazir.setSelected(false);
        rd_temizlemede.setSelected(false);
        lbl_cikis_tarih.setText(null);
        lbl_giris_tarih.setText(null);
        data_cikis.setShowWeekNumbers(false);
        data_giris.setShowWeekNumbers(false);
    }

    public void bılgıler(TableView<arac_sinif> tablo)
    {
        sql = "select*from üye_kayit";

        try {
            sorguifadesi = baglantı.prepareStatement(sql);
            getirilen = sorguifadesi.executeQuery();
            ObservableList<arac_sinif> veriler = FXCollections.observableArrayList();
            while (getirilen.next()) {
                veriler.add(new arac_sinif(getirilen.getInt("id"),
                        getirilen.getString("müsteri_ad_soyad"), getirilen.getString("plaka"),
                        getirilen.getString("tc"), getirilen.getString("telefon"),
                        getirilen.getString("tarih_giris"),getirilen.getString("tarih_cikis"),
                        getirilen.getString("paket"), getirilen.getString("arac_marka"),
                        getirilen.getString("arac_model"), getirilen.getInt("ücret")));
            }
            colon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            colon_ad_soyad.setCellValueFactory(new PropertyValueFactory<>("müsteri_ad_soyad"));
            colon_plaka.setCellValueFactory(new PropertyValueFactory<>("plaka"));
            colon_tc.setCellValueFactory(new PropertyValueFactory<>("tc"));
            colon_telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            colon_giris_taih.setCellValueFactory(new PropertyValueFactory<>("tarih_giris"));
            colon_cikis_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih_cikis"));
            colon_paket.setCellValueFactory(new PropertyValueFactory<>("paket"));
            colon_marka.setCellValueFactory(new PropertyValueFactory<>("arac_marka"));
            colon_model.setCellValueFactory(new PropertyValueFactory<>("arac_model"));
            colon_ücret.setCellValueFactory(new PropertyValueFactory<>("ücret"));
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
        cmb_marka.getItems().addAll("Fiat", "Renault", "Ford", "Volkswagen", "Toyota","Peugeot");
        cmb_paket.getItems().addAll("İç Yıkama", "Dış Yıkama", "İç Dış Yıkama", "Pasta Cila", "Full Paket");
        slider_ücret.valueProperty().addListener((obs, OldValue, newValue) -> {lbl_ücret.setText(newValue.toString());
        });
        bılgıler(tableview);

    }
}
