
package businesslayer;

public class ChiTietHoaDon {
    private int maHD;
    private int maSP;
    private int soLuong;
    private float donGia;
    private float thanhTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int maHD, int maSP, int soLuong, float donGia, float thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "maHD=" + maHD + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia + ", thanhTien=" + thanhTien + '}';
    }
    
    
}
