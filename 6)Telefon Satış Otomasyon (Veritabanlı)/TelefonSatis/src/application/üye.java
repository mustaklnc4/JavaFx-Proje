package application;

public class �ye {
private int id;
private String kulad;
private String sifre;
private String yetki;
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

public �ye(int id, String kulad, String sifre, String yetki) {
	super();
	this.id = id;
	this.kulad = kulad;
	this.sifre = sifre;
	this.yetki = yetki;
}
public �ye() {
}

}
