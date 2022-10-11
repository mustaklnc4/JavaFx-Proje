package sample;
import com.jfoenix.controls.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class üyecontrol implements Initializable {
    String sql="";
    Connection baglantı=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    public üyecontrol() { baglantı=baglanti.Baglan(); }

    ObservableList<üye_sinif> veriler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anasayfa_ancpane;

    @FXML
    private TableView<üye_sinif> tableview;

    @FXML
    private TableColumn<üye_sinif,Integer> colon_id;

    @FXML
    private TableColumn<üye_sinif,String> colon_kulad;

    @FXML
    private TableColumn<üye_sinif,String> colon_parola;

    @FXML
    private TableColumn<üye_sinif,String> colon_yetki;

    @FXML
    private JFXTextField txt_user;

    @FXML
    private JFXPasswordField txt_parola;

    @FXML
    private JFXPasswordField txt_parolatekrar;

    @FXML
    private JFXRadioButton rb_kullanıcı;

    @FXML
    private ToggleGroup grup_yetki;

    @FXML
    private JFXRadioButton rb_yönetici;

    @FXML
    private JFXButton btn_geri;

    @FXML
    private JFXButton btn_üye;

    @FXML
    private JFXButton btn_sil;

    @FXML
    private JFXButton btn_güncelle;

    @FXML
    private Label lbl_deger;

    @FXML
    private JFXComboBox<String> combo_yetki;


    @FXML
    void event_geri(ActionEvent event) {
        try {
            AnchorPane anchorPane;
            anchorPane = FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(anchorPane);
            stage.setScene(scene);
            anasayfa_ancpane.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); }
    }
    public void sıfırla() {
        combo_yetki.getSelectionModel().select(-1);
        txt_parola.setText("");
        txt_parolatekrar.setText("");
        txt_user.setText("");
        lbl_deger.setText("");
    }

    @FXML
    void event_üye(ActionEvent event) {
        String yetki=combo_yetki.getSelectionModel().getSelectedItem().trim();
        String mesaj="";
        Alert alert1=new Alert(AlertType.WARNING);
        alert1.setTitle("Ekleme Yapılamadı");
        alert1.setHeaderText("Hata Uyarisi");
        if(!(txt_parola.getText().equals(txt_parolatekrar.getText()))) {
            mesaj+="Şifreler Aynı değil\n";
        }
        if (txt_parola.getText().equals("")) {
            mesaj+="Şifre Alanı Boş\n";
        }
        if (txt_user.getText().equals("")) {
            mesaj+="Kullanıcı adı Boş\n";;
        }
        if (yetki.equals("Kullanıcı")||yetki.equals("Yönetici")) {
        }
        else {
            mesaj+="Yetki Alanı Boş\n";
        }
        alert1.setContentText(mesaj);
        Optional<ButtonType> sonucOptional1=alert1.showAndWait();
        if (mesaj.length()==0) {
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Ekleme");
            alert.setHeaderText("Uyari");
            alert.setContentText("Eklemek ?");
            Optional<ButtonType> sonucOptional=alert.showAndWait();
            sql="insert into login(kul_ad,sifre,yetki) values(?,?,?)";
            if(sonucOptional.get()==ButtonType.OK)
            {
                try {
                    sorguifadesi=baglantı.prepareStatement(sql);
                    sorguifadesi.setString(1, txt_user.getText().trim());
                    sorguifadesi.setString(2, txt_parola.getText().trim());
                    sorguifadesi.setString(3, yetki.trim());
                    sorguifadesi.executeUpdate();
                }
                catch (SQLException e) {
                    e.getMessage().toString();}
                bılgıler(tableview);
            }
        }
    }

    @FXML
    void event_görüntüle(MouseEvent event) {
        üye_sinif bilgiler= new üye_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        combo_yetki.setValue(bilgiler.getYetki());
        txt_parolatekrar.setText(bilgiler.getSifre());
        txt_parola.setText(bilgiler.getSifre());
        txt_user.setText(bilgiler.getKulad());
    }

    @FXML
    void event_güncelle(ActionEvent event) {
        üye_sinif bilgiler=new üye_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int seciliid=bilgiler.getId();
        int sira =tableview.getSelectionModel().getSelectedIndex();
        tableview.getItems().set(sira, bilgiler);
        String yetki=combo_yetki.getSelectionModel().getSelectedItem().trim();
        String mesaj="";
        Alert alert1=new Alert(AlertType.WARNING);
        alert1.setTitle("Güncelleme Yapılamadı");
        alert1.setHeaderText("Hata Uyarisi");

        if (yetki.equals("Kullanıcı")||yetki.equals("Yönetici")) {
        }
        else {
            mesaj+="Yetki Alanı Boş Girdiniz\n";
        }

        alert1.setContentText(mesaj);
        Optional<ButtonType> sonucOptional1=alert1.showAndWait();
        if (mesaj.length()==0) {
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Güncellemek");
            alert.setHeaderText("Uyari");
            alert.setContentText("Güncelle ?");
            Optional<ButtonType> sonucOptional=alert.showAndWait();
            sql="update login set  yetki=? where id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setString(1, yetki);
                sorguifadesi.setInt(2, seciliid);
                sorguifadesi.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();}
            bılgıler(tableview);
        }

    }

    @FXML
    void event_sil(ActionEvent event)
    {
        üye_sinif bilgiler=new üye_sinif();
        bilgiler=tableview.getItems().get(tableview.getSelectionModel().getFocusedIndex());
        int seciliId=bilgiler.getId();
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Silme");
        alert.setHeaderText("Uyari");
        alert.setContentText("Simek ");
        Optional<ButtonType> sonucOptional=alert.showAndWait();
        if(sonucOptional.get()==ButtonType.OK)
        { sql="delete from login where id=?";
            try {
                sorguifadesi=baglantı.prepareStatement(sql);
                sorguifadesi.setInt(1, seciliId);
                sorguifadesi.executeUpdate();
            }
            catch (SQLException e) {e.printStackTrace();}
            bılgıler(tableview);
            sıfırla();
        }
    }

    public void bılgıler(TableView<üye_sinif> tablo) {
        sql="select*from login";

        try {
            sorguifadesi=baglantı.prepareStatement(sql);
            getirilen=sorguifadesi.executeQuery();
            ObservableList<üye_sinif> veriler=FXCollections.observableArrayList();
            while(getirilen.next())
            { veriler.add(new üye_sinif(getirilen.getInt("id"),getirilen.getString("kul_ad"),getirilen.getString("sifre"),getirilen.getString("yetki"))); }
            colon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            colon_kulad.setCellValueFactory(new PropertyValueFactory<>("kulad"));
            colon_parola.setCellValueFactory(new PropertyValueFactory<>("sifre"));
            colon_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
            tableview.setItems(veriler); }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        combo_yetki.getItems().addAll("Kullanıcı","Yönetici");
        bılgıler(tableview);
    }
}
