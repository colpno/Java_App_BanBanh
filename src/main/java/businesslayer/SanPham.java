
package businesslayer;

public class SanPham {
    private int maSP;
    private String tenSP;
    private int maLoai;
    private int maNSX;
    private int soLuong;
    private float donGia;
    private String donViTinh;
    private String moTa;
    private String anhDaiDien;

    public SanPham() {

    }

    public SanPham(int maSP, String tenSP, int maLoai, int maNSX, int soLuong, float donGia, String donViTinh,
            String moTa, String anhDaiDien) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.maNSX = maNSX;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
        this.moTa = moTa;
        this.anhDaiDien = anhDaiDien;
    }

    public int getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public int getMaNSX() {
        return maNSX;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public String getanhDaiDien() {
        return anhDaiDien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setMaNSX(int maNSX) {
        this.maNSX = maNSX;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public void setanhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", maLoai=" + maLoai + ", maNSX=" + maNSX
                + ", soLuong=" + soLuong + ", donGia=" + donGia + ", donViTinh=" + donViTinh + ", moTa=" + moTa
                + ", anhDaiDien=" + anhDaiDien + '}';
    }

}
