
package businesslayer;

public class HoaDon {
    
    private int maHD;
    private int maNV;
    private int maKH;
    private String ngayLapHoaDon;
    private float tongTien;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maNV, int maKH, String ngayLapHoaDon, float tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.tongTien = tongTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public String getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setNgayLapHoaDon(String ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", maNV=" + maNV + ", maKH=" + maKH + ", ngayLapHoaDon=" + ngayLapHoaDon + ", tongTien=" + tongTien + '}';
    }
    
    
}
