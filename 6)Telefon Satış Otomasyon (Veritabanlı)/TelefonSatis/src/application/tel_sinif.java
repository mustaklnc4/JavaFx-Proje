package application;

public class tel_sinif {

	private int id;
	private String ad;
	private String marka;
	private String model;
	private int adet;
	private String tarih;
	private double ücret;
	
	public tel_sinif() 
	{
		super();
	}
	
	public tel_sinif(int id, String ad, String marka, String model, int adet, String tarih, double ücret) {
		super();
		this.id = id;
		this.ad = ad;
		this.marka = marka;
		this.model = model;
		this.adet = adet;
		this.tarih = tarih;
		this.ücret = ücret;
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
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public double getÜcret() {
		return ücret;
	}
	public void setÜcret(double ücret) {
		this.ücret = ücret;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	
}
