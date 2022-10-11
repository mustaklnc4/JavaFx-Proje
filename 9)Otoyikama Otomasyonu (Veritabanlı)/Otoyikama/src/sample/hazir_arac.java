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
public class hazir_arac {

    String sql="";
    Connection baglantı=null;
    PreparedStatement sorguıfadesı=null;
    ResultSet getirilen=null;
    public hazir_arac() { baglantı=baglanti.Baglan(); }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane a_hazir;

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
    private Label lbl_marka;

    @FXML
    private Label lbl_model;

    @FXML
    private Label lbl_giris_tarih;

    @FXML
    private Label lbl_cikis_tarih;

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
    void event_geri(ActionEvent event) {
        try {
            AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(anchorPane);
            stage.setScene(scene);
            a_hazir.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void event_görüntüle(MouseEvent event) {
        arac_sinif bilgiler=new arac_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        lbl_marka.setText(bilgiler.getArac_marka());
        lbl_model.setText(bilgiler.getArac_model());
        lbl_cikis_tarih.setText(bilgiler.getTarih_cikis());
        lbl_giris_tarih.setText(bilgiler.getTarih_giris());
    }

    @FXML
    void event_sil(ActionEvent event) {
        arac_sinif bilgiler=new arac_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());

        int seciliId=bilgiler.getId();
        Alert mesAlert=new Alert(Alert.AlertType.CONFIRMATION);
        mesAlert.setTitle("Kalıcı Silme İşlemi Mesaj");
        mesAlert.setHeaderText("Kalıcı Silme Uyarısı");
        mesAlert.setContentText("Kalıcı Silmeye emin misiniz?");
        Optional<ButtonType> result=mesAlert.showAndWait();
        if (result.get()==ButtonType.OK) {

            sql="delete from hazır_araclar where id=?";
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

            int model=0;
            int marka=0;
            int tarih=0;
            int veri=0;
            ObservableList<arac_sinif>Bütünkayıtlar;
            Bütünkayıtlar=tableview.getItems();

            String t_model=lbl_model.getText().trim();
            String t_marka=lbl_marka.getText().trim();
            String tıklanan_tarih=lbl_giris_tarih.getText().trim();

            for(arac_sinif bilgiKullanıcı :Bütünkayıtlar)
            {
                veri=veri+1;

                if(bilgiKullanıcı.getArac_model().equals(t_model)&&bilgiKullanıcı.getArac_marka().equals(t_marka))
                {
                    marka++;
                }

                if(bilgiKullanıcı.getArac_marka().equals(t_marka)&&bilgiKullanıcı.getTarih_giris().equals(tıklanan_tarih))
                {
                    tarih++;
                }

                if(bilgiKullanıcı.getArac_model().equals(t_model)&&bilgiKullanıcı.getArac_marka().equals(t_marka))
                {
                    model++;
                    XYChart.Series srm=new XYChart.Series();
                    srm.getData().add(new XYChart.Data(tıklanan_tarih+" "+t_marka+" "+t_model,marka/model));
                    srm.setName(veri+". veri "+t_model+" "+t_marka+" "+tıklanan_tarih);
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
            if(t_marka.equals("Fiat"))
            {
                pcData.add(new PieChart.Data("Renault,Ford,Volkswagen,Toyota,Peugeot",veri-1));
            }
            if(t_marka.equals("Renault"))
            {
                pcData.add(new PieChart.Data("Fiat,,Ford,Volkswagen,Toyota,Peugeot",veri-1));
            }
            if(t_marka.equals("Ford"))
            {
                pcData.add(new PieChart.Data("Fiat,Renault,,Volkswagen,Toyota,Peugeot",veri-1));
            }
            if(t_marka.equals("Volkswagen"))
            {
                pcData.add(new PieChart.Data("Fiat,Renault,Ford,,Toyota,Peugeot",veri-1));
            }
            if(t_marka.equals("Toyota"))
            {
                pcData.add(new PieChart.Data("Fiat,Renault,Ford,Volkswagen,,Peugeot",veri-1));
            }
            if(t_marka.equals("Peugeot"))
            {
                pcData.add(new PieChart.Data("Fiat,Renault,Ford,Volkswagen,Toyota,",veri-1));
            }
            pcData.addAll(new PieChart.Data(t_marka,model),new PieChart.Data(marka+" Araç "+t_marka,marka),new PieChart.Data(model+" Araç da "+t_model,model));
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
                        lbl_cikis_tarih.setText(String.valueOf(data.getPieValue()) + " Tane");
                    }
                });
            }
            marka=0;
            model=0;
            veri=0;
            tarih=0;
        }
        if (result.get()==ButtonType.CANCEL)
        {
            mesAlert.setContentText("İşlem İptal Edildi");
        }
    }

    public void bılgıler(TableView<arac_sinif> tablo)
    {
        sql = "select*from hazır_araclar";

        try {
            sorguıfadesı = baglantı.prepareStatement(sql);
            getirilen = sorguıfadesı.executeQuery();
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

    @FXML
    void initialize() {
        bılgıler(tableview);
    }

}
