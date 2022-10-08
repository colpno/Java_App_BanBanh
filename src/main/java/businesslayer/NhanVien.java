package businesslayer;

public class NhanVien {
    private int maNV;
    private int maTK;
    private String ho;
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private int luong;

    public NhanVien() {
    }

    public NhanVien(int maTK, String ho, String ten, String ngaySinh, String diaChi, String soDienThoai, int luong) {
        this.maTK = maTK;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.luong = luong;
    }

    public NhanVien(int maNV, int maTK, String ho, String ten, String ngaySinh, String diaChi, String soDienThoai,
            int luong) {
        this.maNV = maNV;
        this.maTK = maTK;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.luong = luong;
    }

    public int getMaNV() {
        return this.maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
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
        return this.soDienThoai;
    }

    public void setSdt(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getLuong() {
        return this.luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "ho=\"" + getHo() + "\"" + ", ten=\"" + getTen() + "\"" + ", ngaySinh=\"" + getNgaySinh() + "\""
                + ", diaChi=\"" + getDiaChi() + "\"" + ", soDienThoai=\"" + getSdt() + "\"" + ", luong=\"" + getLuong()
                + "\"";
    }

}
