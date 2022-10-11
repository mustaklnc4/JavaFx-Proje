package application;



public class Kay�tlar {
private int id;
private String ad;
private String tc;
private String cinsiyet;
private String kan;
private String poliklinik;
private String hastat�r�;
private String il;
private String adres;
private String sigorta;
private Double ates;
private String corona;
private String tarih;
private String doktor;



public Kay�tlar() {
	id=0;
	ad=null;
	tc=null;
	cinsiyet=null;
	kan=null;
	poliklinik=null;
	hastat�r�=null;
	il=null;
	adres=null;
	sigorta=null;
	ates=0.0;
	corona=null;
	tarih=null;
	doktor=null;
	}
public Kay�tlar(int id, String ad, String tc, String cinsiyet, String kan, String poliklinik, String hastat�r�,String il, String adres, String sigorta, Double ates, String corona, String tarih, String doktor) {

	this.id = id;
	this.ad = ad;
	this.tc = tc;
	this.cinsiyet = cinsiyet;
	this.kan = kan;
	this.poliklinik = poliklinik;
	this.hastat�r� = hastat�r�;
	this.il = il;
	this.adres = adres;
	this.sigorta = sigorta;
	this.ates = ates;
	this.corona = corona;
	this.tarih = tarih;
	this.doktor = doktor;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAd() {
	return ad;
}
public void setAd(String ad) {
	this.ad = ad;
}
public String getTc() {
	return tc;
}
public void setTc(String tc) {
	this.tc = tc;
}
public String getCinsiyet() {
	return cinsiyet;
}
public void setCinsiyet(String cinsiyet) {
	this.cinsiyet = cinsiyet;
}
public String getKan() {
	return kan;
}
public void setKan(String kan) {
	this.kan = kan;
}
public String getPoliklinik() {
	return poliklinik;
}
public void setPoliklinik(String poliklinik) {
	this.poliklinik = poliklinik;
}
public String getHastat�r�() {
	return hastat�r�;
}
public void setHastat�r�(String hastat�r�) {
	this.hastat�r� = hastat�r�;
}
public String getIl() {
	return il;
}
public void setIl(String il) {
	this.il = il;
}
public String getAdres() {
	return adres;
}
public void setAdres(String adres) {
	this.adres = adres;
}
public String getSigorta() {
	return sigorta;
}
public void setSigorta(String sigorta) {
	this.sigorta = sigorta;
}
public Double getAtes() {
	return ates;
}
public void setAtes(Double ates) {
	this.ates = ates;
}
public String getCorona() {
	return corona;
}
public void setCorona(String corona) {
	this.corona = corona;
}
public String getTarih() {
	return tarih;
}
public void setTarih(String tarih) {
	this.tarih = tarih;
}
public String getDoktor() {
	return doktor;
}
public void setDoktor(String doktor) {
	this.doktor = doktor;
}

}
