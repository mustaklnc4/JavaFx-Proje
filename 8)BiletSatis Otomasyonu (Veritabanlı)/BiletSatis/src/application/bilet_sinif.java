package application;

public class bilet_sinif {
	private int id;
	private String salon;
	private String koltuk;
	private String tür;
	private String isim;
	private String tarih;
	private double fiyat;
	//id,salon,koltuk,tür,isim,tarih,fiyat
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalon() {
		return salon;
	}
	public void setSalon(String salon) {
		this.salon = salon;
	}
	public String getKoltuk() {
		return koltuk;
	}
	public void setKoltuk(String koltuk) {
		this.koltuk = koltuk;
	}
	public String getTür() {
		return tür;
	}
	public void setTür(String tür) {
		this.tür = tür;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public bilet_sinif(int id, String salon, String koltuk, String tür, String isim, String tarih, double fiyat) {
		super();
		this.id = id;
		this.salon = salon;
		this.koltuk = koltuk;
		this.tür = tür;
		this.isim = isim;
		this.tarih = tarih;
		this.setFiyat(fiyat);
	}
	public double getFiyat() {
		return fiyat;
	}
	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	public bilet_sinif() 
	{
		id=0;
		salon=null;
		koltuk=null;
		tür=null;
		isim=null;
		tarih=null;
		fiyat=0.0;
	}
}
