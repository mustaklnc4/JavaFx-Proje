package sample;

public class kayit_sinif {

    private int id;
    private String kulad;
    private String sifre;
    private String yetki;
    private String yorum;

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public kayit_sinif(int id, String kulad, String sifre, String yetki, String yorum) {
        this.id = id;
        this.kulad = kulad;
        this.sifre = sifre;
        this.yetki = yetki;
        this.yorum = yorum;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKulad() {
        return kulad;
    }
    public void setKulad(String kulad) {
        this.kulad = kulad;
    }
    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getYetki() {
        return yetki;
    }
    public void setYetki(String yetki) {
        this.yetki = yetki;
    }

    public kayit_sinif() {
        id=0;
        kulad=null;
        sifre=null;
        yetki=null;
        yorum=null;
    }

}
