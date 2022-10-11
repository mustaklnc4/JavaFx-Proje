package sample;

public class arac_sinif {

    private int id;
    private String müsteri_ad_soyad;
    private  String plaka;
    private String tc;
    private String telefon;
    private String tarih_giris;
    private String tarih_cikis;
    private String paket;
    private String arac_marka;
    private String arac_model;
    private int ücret;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMüsteri_ad_soyad() {
        return müsteri_ad_soyad;
    }

    public void setMüsteri_ad_soyad(String müsteri_ad_soyad) {
        this.müsteri_ad_soyad = müsteri_ad_soyad;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTarih_giris() {
        return tarih_giris;
    }

    public void setTarih_giris(String tarih_giris) {
        this.tarih_giris = tarih_giris;
    }

    public String getTarih_cikis() {
        return tarih_cikis;
    }

    public void setTarih_cikis(String tarih_cikis) {
        this.tarih_cikis = tarih_cikis;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getArac_marka() {
        return arac_marka;
    }

    public void setArac_marka(String arac_marka) {
        this.arac_marka = arac_marka;
    }

    public String getArac_model() {
        return arac_model;
    }

    public void setArac_model(String arac_model) {
        this.arac_model = arac_model;
    }

    public int getÜcret() {
        return ücret;
    }

    public void setÜcret(int ücret) {
        this.ücret = ücret;
    }

    public arac_sinif(int id, String müsteri_ad_soyad, String plaka, String tc, String telefon, String tarih_giris, String tarih_cikis, String paket, String arac_marka, String arac_model, int ücret) {
        this.id = id;
        this.müsteri_ad_soyad = müsteri_ad_soyad;
        this.plaka = plaka;
        this.tc = tc;
        this.telefon = telefon;
        this.tarih_giris = tarih_giris;
        this.tarih_cikis = tarih_cikis;
        this.paket = paket;
        this.arac_marka = arac_marka;
        this.arac_model = arac_model;
        this.ücret = ücret;
    }
    public arac_sinif() {
        id=0;
        müsteri_ad_soyad="";
        plaka="";
        tc="";
        tarih_giris="";
        telefon="";
        tarih_cikis="";
        paket="";
        arac_marka="";
        arac_model="";
        ücret=0;
    }
}
