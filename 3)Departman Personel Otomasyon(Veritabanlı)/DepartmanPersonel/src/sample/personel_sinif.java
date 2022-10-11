package sample;

public class personel_sinif {

    private int id;
    private String ad;
    private  String soyad;
    private String tc;
    private String sicilno;
    private String telefon;
    private String adres;
    private String departman;
    private String durum;
    private String tarih;
    private String tarih_bitis;
    private String iban;
    private int maas;

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getTc() {
        return tc;
    }

    public String getSicilno() {
        return sicilno;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdres() {
        return adres;
    }

    public String getDepartman() {
        return departman;
    }

    public String getDurum() {
        return durum;
    }

    public String getTarih() {
        return tarih;
    }

    public String getIban() {
        return iban;
    }

    public int getMaas() {
        return maas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public void setSicilno(String sicilno) {
        this.sicilno = sicilno;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }

    public String getTarih_bitis() {
        return tarih_bitis;
    }

    public void setTarih_bitis(String tarih_bitis) {
        this.tarih_bitis = tarih_bitis;
    }
    public personel_sinif() {
        id=0;
        ad="";
        soyad="";
        tc="";
        sicilno="";
        telefon="";
        adres="";
        departman="";
        durum="";
        tarih="";
        tarih_bitis="";
        iban="";
        maas=0;
    }
    public personel_sinif(int id, String ad, String soyad, String tc, String sicilno, String telefon, String adres, String departman, String durum, String tarih,  String tarih_bitis,String iban, int maas) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.tc = tc;
        this.sicilno = sicilno;
        this.telefon = telefon;
        this.adres = adres;
        this.departman = departman;
        this.durum = durum;
        this.tarih = tarih;
        this.tarih_bitis = tarih_bitis;
        this.iban = iban;
        this.maas = maas;
    }
}
