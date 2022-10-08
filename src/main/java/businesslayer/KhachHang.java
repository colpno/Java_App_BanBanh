package businesslayer;

public class KhachHang {
    private int maKH;
    private int maTK;
    private String ho;
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String sdt;

    public KhachHang() {
    }

    public KhachHang(int maKH, int maTK, String ho, String ten, String ngaySinh, String diaChi, String sdt) {
        this.maKH = maKH;
        this.maTK = maTK;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public int getMaKH() {
        return this.maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaTK() {
        return this.maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

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

    public String getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return this.sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "ho='" + getHo() + "'" + ", ten='" + getTen() + "'" + ", ngaySinh='" + getNgaySinh() + "'" + ", diaChi='"
                + getDiaChi() + "'" + ", sdt='" + getSdt() + "'";
    }

}
