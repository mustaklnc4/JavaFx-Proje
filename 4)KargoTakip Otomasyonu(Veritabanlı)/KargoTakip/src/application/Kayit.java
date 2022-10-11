package application;

public class Kayit {
	private int id;
	private String adi;
	private String Mail;
	private String Sifre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}
	public String getSifre() {
		return Sifre;
	}
	public void setSifre(String sifre) {
		Sifre = sifre;
	}
	public Kayit(int id, String adi, String mail, String sifre) {
		super();
		this.id = id;
		this.adi = adi;
		this.Mail = mail;
		this.Sifre = sifre;
	}
	public Kayit() 
	{
		id=0;
		adi=null;
		Mail=null;
		Sifre=null;
	}

}
