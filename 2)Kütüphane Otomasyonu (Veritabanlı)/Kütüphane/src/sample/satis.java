package sample;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class satis {
    String sql = "";
    Connection baglantı = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;

    public satis() {
        baglantı = baglanti.Baglan();
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane satis_ancpane;

    @FXML
    private BarChart<String, Number> Salarychart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private PieChart pastadilimi;

    @FXML
    private JFXButton btn_tara;

    @FXML
    private JFXButton btn_sil;

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
    private TableColumn<sinif_kitap, String> colon_tarih;;

    @FXML
    private JFXButton btn_cikis;

    @FXML
    private Label label_kitaptürü;

    @FXML
    private Label label_tarih;

    @FXML
    private Label label_yazar;
    @FXML
    void event_cikis(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            satis_ancpane.getScene().getWindow().hide();
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
        label_kitaptürü.setText(bilgiler.getKitap_türü());
        label_yazar.setText(bilgiler.getYazar());
        label_tarih.setText(bilgiler.getTarih());
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
            sql="delete from satislar where id=?";
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
    public void süpür() {
        label_kitaptürü.setText("");
        label_tarih.setText("");
        label_yazar.setText("");
    }

    public void bılgıler(TableView<sinif_kitap> tablo)
    {
        sql = "select*from satislar";

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
    @FXML
    void event_tara(ActionEvent event) {
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Sorgulama İşlemi Mesaj");
        mesAlert.setHeaderText("Sorgulama Uyarısı");
        mesAlert.setContentText("Sorgulamak istediginize emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();

        if (result.get()==ButtonType.OK)
        {
            int tür=0;
            int yazar=0;
            int tarih=0;
            int deger=0;

            ObservableList<sinif_kitap>Bütünkayıtlar;
            Bütünkayıtlar=tableview.getItems();

            String s_türü= label_kitaptürü.getText().trim();
            String s_yazar= label_yazar.getText().trim();
            String s_tarih= label_tarih.getText().trim();

            for(sinif_kitap bilgiKullanıcı :Bütünkayıtlar)
            {
                deger++;
                if(bilgiKullanıcı.getKitap_türü().equals(s_türü)&&bilgiKullanıcı.getYazar().equals(s_yazar))
                {
                    yazar++;
                }

                if(bilgiKullanıcı.getYazar().equals(s_yazar)&&bilgiKullanıcı.getTarih().equals(s_tarih))
                {
                    tarih++;
                }

                if(bilgiKullanıcı.getKitap_türü().equals(s_türü)&&bilgiKullanıcı.getYazar().equals(s_yazar))
                {
                    tür++;
                    XYChart.Series srm=new XYChart.Series();
                    srm.getData().add(new XYChart.Data(s_yazar+" "+s_türü+" "+s_tarih,yazar/tür));
                    srm.setName(deger+". Kayıt "+s_türü+" "+s_yazar+" "+s_tarih);
                    try {
                        Salarychart.getData().add(srm);
                        Salarychart.getChildrenUnmodifiable().addAll(x,y);
                        Salarychart.setBarGap (3);
                        Salarychart.setCategoryGap (20);
                        Salarychart.getChildrenUnmodifiable().clear();

                    } catch (Exception e) {		}

                }
            }
            // "Hikaye, Roman,Makale,Şiir,Biyografi,Çocuk,Gezi,Anı"
            ObservableList<PieChart.Data> pcData=FXCollections.observableArrayList();
            if(s_türü.equals("Hikaye"))
            {
                pcData.add(new PieChart.Data( "Roman,Makale,Şiir,Biyografi,Çocuk,Gezi,Anı",deger-1));
            }
            if(s_türü.equals("Roman"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Makale,Şiir,Biyografi,Çocuk,Gezi,Anı",deger-1));
            }
            if(s_türü.equals("Makale"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Roman,Şiir,Biyografi,Çocuk,Gezi,Anı",deger-1));
            }
            if(s_türü.equals("Şiir"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Roman,Makale,Biyografi,Çocuk,Gezi,Anı",deger-1));
            }
            if(s_türü.equals("Biyografi"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Roman,Makale,Şiir,Çocuk,Gezi,Anı",deger-1));
            }
            if(s_türü.equals("Çocuk"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Roman,Makale,Şiir,Biyografi,Gezi,Anı",deger-1));
            }
            if(s_türü.equals("Gezi"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Roman,Makale,Şiir,Biyografi,Çocuk,Anı",deger-1));
            }
            if(s_türü.equals("Anı"))
            {
                pcData.add(new PieChart.Data( "Hikaye,Roman,Makale,Şiir,Biyografi,Gezi,Çocuk",deger-1));
            }
            pcData.addAll(new PieChart.Data(s_yazar,tür),new PieChart.Data(yazar+" Kişi "+s_yazar,yazar),new PieChart.Data(tür+" Kişide "+s_türü,tür));
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
                        label_tarih.setText(String.valueOf(data.getPieValue()) + " Tane");
                    }
                });
            }
            tür=0;
            yazar=0;
            tarih=0;
            deger=0;
            tarih=0;
        }
    }
    @FXML
    void initialize() {
        bılgıler(tableview);
    }
}
