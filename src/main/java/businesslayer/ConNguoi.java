package businesslayer;

public abstract class ConNguoi {
	private String ho;
	private String ten;
	private String soDT;
	private String diaChi;

	public ConNguoi() {
		ho = null;
		ten = null;
		soDT = null;
		diaChi = null;
	}

	public ConNguoi(String ho, String ten, String soDT, String diaChi) {
		this.ho = ho;
		this.ten = ten;
		this.soDT = soDT;
		this.diaChi = diaChi;
	}

	public abstract void display();

	public String getHo() {
		return this.ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSoDT() {
		return this.soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
}
