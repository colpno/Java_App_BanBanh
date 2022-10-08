
package businesslayer;

import java.util.ArrayList;

public class ChiTietKhuyenMai {
    private int maKM;
    private int maSP;
    private String hinhThucKhuyenMai;
    private int phanTramKhuyenMai;
    
    public ChiTietKhuyenMai() {
        this.maKM = 0;
        this.maSP = 0;
        this.hinhThucKhuyenMai = null;
        this.phanTramKhuyenMai = 0;
    }
    
    
    public ChiTietKhuyenMai(int maKM, int maSP, String hinhThucKhuyenMai, int phanTramKhuyenMai) {
        this.maKM = maKM;
        this.maSP = maSP;
        this.hinhThucKhuyenMai = hinhThucKhuyenMai;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getHinhThucKhuyenMai() {
        return hinhThucKhuyenMai;
    }

    public void setHinhThucKhuyenMai(String hinhThucKhuyenMai) {
        this.hinhThucKhuyenMai = hinhThucKhuyenMai;
    }

    public int getPhanTramKhuyenMai() {
        return phanTramKhuyenMai;
    }

    public void setPhanTramKhuyenMai(int phanTramKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }
    @Override
    public String toString() {
        return "maKM=\"" + getMaKM() + "\"" + ", maSP=\"" + getMaSP() + "\"" + ", hinhThucKhuyenMai=\"" + getHinhThucKhuyenMai() + "\"" 
                + ", ngayKetThuc=\""+ getPhanTramKhuyenMai() + "\"" ;
    }
    
}