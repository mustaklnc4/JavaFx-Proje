package application;

public class sinif {
	private int id;
	private String masa_numara;
	private String masa_s�re;
	private String ad_soyad;
	private String tarih;
	private String yiyecek;
	private double fiyat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMasa_s�re() {
		return masa_s�re;
	}
	public void setMasa_s�re(String masa_s�re) {
		this.masa_s�re = masa_s�re;
	}
	public String getMasa_numara() {
		return masa_numara;
	}
	public void setMasa_numara(String masa_numara) {
		this.masa_numara = masa_numara;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public String getAd_soyad() {
		return ad_soyad;
	}
	public void setAd_soyad(String ad_soyad) {
		this.ad_soyad = ad_soyad;
	}
	public String getYiyecek() {
		return yiyecek;
	}
	public void setYiyecek(String yiyecek) {
		this.yiyecek = yiyecek;
	}
	public double getFiyat() {
		return fiyat;
	}
	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	public sinif(int id, String masa_numara, String masa_s�re, String ad_soyad, String tarih, String yiyecek, double fiyat) {
		super();
		this.id = id;
		this.masa_numara = masa_numara;
		this.masa_s�re = masa_s�re;
		this.ad_soyad = ad_soyad;
		this.tarih = tarih;
		this.yiyecek = yiyecek;
		this.fiyat = fiyat;
	}
	public sinif() {
		id=0;
		masa_numara=null;
		masa_s�re=null;
		ad_soyad=null;
		tarih=null;
		yiyecek=null;
		fiyat=0.0;
		}

}
