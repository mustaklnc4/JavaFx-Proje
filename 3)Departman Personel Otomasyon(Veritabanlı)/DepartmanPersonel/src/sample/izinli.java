package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import com.jfoenix.controls.JFXButton;
public class izinli {

    String sql="";
    Connection baglantı=null;
    PreparedStatement sorguıfadesı=null;
    ResultSet getirilen=null;
    public izinli() { baglantı=baglanti.Baglan(); }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane izin_ancpane;

    @FXML
    private BarChart<String, Number> Salarychart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private PieChart pastadilimi;

    @FXML
    private JFXButton btn_geri;

    @FXML
    private JFXButton btn_sorgula;

    @FXML
    private JFXButton btn_sil;

    @FXML
    private JFXButton btn_izin_bitir;

    @FXML
    private Label label_departman;

    @FXML
    private Label label_izinli;

    @FXML
    private Label label_tarih;

    @FXML
    private Label lbl_deger;

    @FXML
    private Label lbl_ad;

    @FXML
    private Label lbl_soyad;

    @FXML
    private Label lbl_tc;

    @FXML
    private Label lbl_sicil;

    @FXML
    private Label lbl_telefon;

    @FXML
    private Label lbl_adres;

    @FXML
    private Label lbl_departman;

    @FXML
    private Label lbl_durum;

    @FXML
    private Label lbl_basla_tarih;

    @FXML
    private Label lbl_bitis_tarih;

    @FXML
    private Label lbl_iban;

    @FXML
    private Label lbl_maas;

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
        void event_geri(ActionEvent event) {
            try {
                AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
                Stage stage=new Stage();
                Scene scene=new Scene(anchorPane);
                stage.setScene(scene);
                izin_ancpane.getScene().getWindow().hide();
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
            label_izinli.setText(bilgiler.getDurum());
            label_departman.setText(bilgiler.getDepartman());
            label_tarih.setText(bilgiler.getTarih()+" / "+ bilgiler.getTarih_bitis());

            lbl_durum.setText(bilgiler.getDurum());
            lbl_ad.setText(bilgiler.getAd());
            lbl_soyad.setText(bilgiler.getSoyad());
            lbl_telefon.setText(bilgiler.getTelefon());
            lbl_tc.setText(bilgiler.getTc());
            lbl_sicil.setText(bilgiler.getSicilno());
            lbl_basla_tarih.setText(bilgiler.getTarih());
            lbl_bitis_tarih.setText(bilgiler.getTarih_bitis());
            lbl_iban.setText(bilgiler.getIban());
            lbl_departman.setText(bilgiler.getDepartman());
            lbl_maas.setText(""+bilgiler.getMaas());
            lbl_adres.setText(bilgiler.getAdres());
        }

        @FXML
        void event_izin_bitir(ActionEvent event) {
            personel_sinif bilgiler=new personel_sinif();
            bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

            int seciliId=bilgiler.getId();
            Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
            mesAlert.setTitle("İzin Bitirme İşlemi Mesaj");
            mesAlert.setHeaderText("İzin Bitirme Uyarısı");
            mesAlert.setContentText("İzin Bitirme emin misiniz?");
            Optional<ButtonType> result=mesAlert.showAndWait();
            if (result.get()==ButtonType.OK) {

                sql = "insert into personel(ad,soyad,tc,sicilno,telefon,adres,departman,durum,tarih_baslangic,tarih_bitis,iban,maaş) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    sorguıfadesı = baglantı.prepareStatement(sql);
                    sorguıfadesı.setString(1, lbl_ad.getText().trim());
                    sorguıfadesı.setString(2, lbl_soyad.getText().trim());
                    sorguıfadesı.setString(3, lbl_tc.getText().trim());
                    sorguıfadesı.setString(4, lbl_sicil.getText().trim());
                    sorguıfadesı.setString(5, lbl_telefon.getText().trim());
                    sorguıfadesı.setString(6, lbl_adres.getText().trim());
                    sorguıfadesı.setString(7, lbl_departman.getText().trim());
                    sorguıfadesı.setString(8, lbl_durum.getText().trim());
                    sorguıfadesı.setString(9, lbl_basla_tarih.getText().trim());
                    sorguıfadesı.setString(10, lbl_bitis_tarih.getText().trim());
                    sorguıfadesı.setString(11, lbl_iban.getText().trim());
                    sorguıfadesı.setString(12, lbl_maas.getText().trim());
                    sorguıfadesı.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                bılgıler(tableview);

                sql="delete from izinler where Id=?";
                try {
                    sorguıfadesı=baglantı.prepareStatement(sql);
                    sorguıfadesı.setInt(1, seciliId);
                    sorguıfadesı.executeUpdate();
                } catch (SQLException e) {e.printStackTrace();}

            }

            else { mesAlert.setContentText("İzin Bitirme islemi iptal oldu"); }
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

                sql="delete from izinler where Id=?";
                try {
                    sorguıfadesı=baglantı.prepareStatement(sql);
                    sorguıfadesı.setInt(1, seciliId);
                    sorguıfadesı.executeUpdate();
                } catch (SQLException e) {e.printStackTrace();}

            }

            else { mesAlert.setContentText("Kalıcı Silme islemi iptal oldu"); }
            bılgıler(tableview);
        }

    @FXML
    void event_sorgula(ActionEvent event)
    {
        Alert mesAlert=new Alert(AlertType.CONFIRMATION);
        mesAlert.setTitle("Sorgulama İşlemi Mesaj");
        mesAlert.setHeaderText("Sorgulama Uyarısı");
        mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();

        if (result.get()==ButtonType.OK)
        {

            int izin=0;
            int departman=0;
            int tarih=0;
            int veri=0;
            ObservableList<personel_sinif>Bütünkayıtlar;
            Bütünkayıtlar=tableview.getItems();

            String tıklanan_izin=label_izinli.getText().trim();
            String tıklanan_departman=label_departman.getText().trim();
            String tıklanan_tarih=label_tarih.getText().trim();

            for(personel_sinif bilgiKullanıcı :Bütünkayıtlar)
            {
                veri=veri+1;

                if(bilgiKullanıcı.getDurum().equals(tıklanan_izin)&&bilgiKullanıcı.getDepartman().equals(tıklanan_departman))
                {
                    departman++;
                }

                if(bilgiKullanıcı.getDepartman().equals(tıklanan_departman)&&bilgiKullanıcı.getTarih().equals(tıklanan_tarih))
                {
                    tarih++;
                }

                if(bilgiKullanıcı.getDurum().equals(tıklanan_izin)&&bilgiKullanıcı.getDepartman().equals(tıklanan_departman))
                {
                    izin++;
                    XYChart.Series srm=new XYChart.Series();
                    srm.getData().add(new XYChart.Data(tıklanan_tarih+" "+tıklanan_departman+" "+tıklanan_izin,departman/izin));
                    srm.setName(veri+". Kayıt "+tıklanan_izin+" "+tıklanan_departman+" "+tıklanan_tarih);
                    try {
                        Salarychart.getData().add(srm);
                        Salarychart.getChildrenUnmodifiable().addAll(x,y);
                        Salarychart.setBarGap (3);
                        Salarychart.setCategoryGap (20);
                        Salarychart.getChildrenUnmodifiable().clear();

                    } catch (Exception e) {		}

                }
            }

            ObservableList<PieChart.Data> pcData=FXCollections.observableArrayList();
            if(tıklanan_departman.equals("Yazılım"))
            {
                pcData.add(new PieChart.Data("Memur,İşletme,Müdür,Temizlik Personel",veri-1));
            }
            if(tıklanan_departman.equals("Memur"))
            {
                pcData.add(new PieChart.Data("İşletme,Müdür,Temizlik Personel,Yazılım",veri-1));
            }
            if(tıklanan_departman.equals("İşletme"))
            {
                pcData.add(new PieChart.Data("Memur,Müdür,Temizlik Personel,Yazılım",veri-1));
            }
            if(tıklanan_departman.equals("Müdür"))
            {
                pcData.add(new PieChart.Data("Memur,İşletme,Temizlik Personel,Yazılım",veri-1));
            }
            if(tıklanan_departman.equals("Temizlik Personel"))
            {
                pcData.add(new PieChart.Data("Memur,Müdür,İşletme,Müdür,Yazılım",veri-1));
            }
            pcData.addAll(new PieChart.Data(tıklanan_departman,izin),new PieChart.Data(departman+" Kişi "+tıklanan_departman,departman),new PieChart.Data(izin+" Kişide "+tıklanan_izin,izin));
            pastadilimi.setData(pcData);
            pastadilimi.setLabelLineLength (20);
            pastadilimi.getChildrenUnmodifiable();

            for (final PieChart.Data data : pastadilimi.getData())
            {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        lbl_deger.setText(String.valueOf(data.getPieValue()) + " Tane");
                    }
                });
            }
            departman=0;
            izin=0;
            veri=0;
            tarih=0;
        }
        if (result.get()==ButtonType.CANCEL)
        {
            mesAlert.setContentText("İşlem İptal Edildi");
        }
    }

    public void bılgıler(TableView<personel_sinif> tablo)
    {
        sql = "select*from izinler";

        try {
            sorguıfadesı = baglantı.prepareStatement(sql);
            getirilen = sorguıfadesı.executeQuery();
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

    @FXML
    void initialize() {
        bılgıler(tableview);
    }

}
