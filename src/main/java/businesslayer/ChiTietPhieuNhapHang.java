package businesslayer;

public class ChiTietPhieuNhapHang {
    private int maSP;
    private int maPhieu;
    private int soLuong;
    private int donGiaGoc;
    private int thanhTien;

    public ChiTietPhieuNhapHang() {
    }

    public ChiTietPhieuNhapHang(int maPhieu, int maSP, int soLuong, int donGiaGoc, int thanhTien) {
        this.maPhieu = maPhieu;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGiaGoc = donGiaGoc;
        this.thanhTien = thanhTien;
    }

    public int getMaSP() {
        return this.maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaPhieu() {
        return this.maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGiaGoc() {
        return this.donGiaGoc;
    }

    public void setDonGiaGoc(int donGiaGoc) {
        this.donGiaGoc = donGiaGoc;
    }

    public int getThanhTien() {
        return this.thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "soLuong='" + getSoLuong() + "'" + ", donGiaGoc='" + getDonGiaGoc() + "'" + ", thanhTien='"
                + getThanhTien() + "'";
    }

}