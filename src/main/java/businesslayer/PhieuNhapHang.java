package businesslayer;

public class PhieuNhapHang {
    private int maPhieu;
    private int maNCC;
    private int maNV;
    private String ngayNhap;
    private int tongTien;

    public PhieuNhapHang() {
    }

    public PhieuNhapHang(int maPhieu, int maNCC, int maNV, String ngayNhap) {
        this.maPhieu = maPhieu;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
    }

    public PhieuNhapHang(int maPhieu, int maNCC, int maNV, String ngayNhap, int tongTien) {
        this.maPhieu = maPhieu;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    public int getMaPhieu() {
        return this.maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getMaNCC() {
        return this.maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaNV() {
        return this.maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getNgayNhap() {
        return this.ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getTongTien() {
        return this.tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "ngayNhap='" + getNgayNhap() + "'";
    }

}