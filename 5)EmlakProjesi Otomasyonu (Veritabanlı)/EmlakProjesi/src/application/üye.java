package application;

public class üye {
private int id;
private String kulad;
private String sifre;
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
public üye(int id, String kulad, String sifre) {
	super();
	this.id = id;
	this.kulad = kulad;
	this.sifre = sifre;
}
public üye() {
}

}
