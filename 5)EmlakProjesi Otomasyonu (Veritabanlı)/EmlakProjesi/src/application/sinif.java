package application;

public class sinif {
	private int id;
	private String yapi_türü;
	private String deger;
	private String yas;
	private String adres;
	
	public sinif(int id, String yapi_türü, String deger, String yas, String adres) {
		super();
		this.id = id;
		this.yapi_türü = yapi_türü;
		this.deger = deger;
		this.yas = yas;
		this.adres = adres;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getYapi_türü() {
		return yapi_türü;
	}



	public void setYapi_türü(String yapi_türü) {
		this.yapi_türü = yapi_türü;
	}



	public String getDeger() {
		return deger;
	}



	public void setDeger(String deger) {
		this.deger = deger;
	}



	public String getYas() {
		return yas;
	}



	public void setYas(String yas) {
		this.yas = yas;
	}



	public String getAdres() {
		return adres;
	}



	public void setAdres(String adres) {
		this.adres = adres;
	}



	public sinif(){
		
	}
}
