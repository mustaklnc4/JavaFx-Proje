package sample;

public class sinif_kitap {
    private int id;
    private String kitap_adi;
    private String yazar;
    private String Kitap_türü;
    private String telefon;
    private String durum;
    private double fiyat;
    private String tarih;

    public sinif_kitap(int id, String kitap_adi, String yazar, String kitap_türü, String telefon, String durum, double fiyat, String tarih) {
        this.id = id;
        this.kitap_adi = kitap_adi;
        this.yazar = yazar;
        this.Kitap_türü = kitap_türü;
        this.telefon = telefon;
        this.durum = durum;
        this.fiyat = fiyat;
        this.tarih = tarih;
    }
    public sinif_kitap() {
        id=0;
        kitap_adi="";
        yazar="";
        Kitap_türü="";
        telefon="";
        durum="";
        fiyat=0.0;
        tarih="";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getKitap_türü() {
        return Kitap_türü;
    }

    public void setKitap_türü(String kitap_türü) {
        Kitap_türü = kitap_türü;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
