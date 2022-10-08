/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

public class NhaSanXuat {
    private int maNSX;
    private String tenNSX;
    private String diaChi;
    private String soDienThoai;
    
    public NhaSanXuat() {
        this.maNSX=0;
        this.tenNSX=null;
        this.diaChi=null;
        this.soDienThoai=null;
    }

    public NhaSanXuat(int maNSX, String tenNSX, String diaChi, String soDienThoai) {
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public int getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(int maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    @Override
    public String toString() {
        return "maNSX=\"" + getMaNSX() + "\"" + ", tenNSX=\"" + getTenNSX() + "\"" + ", diaChi=\"" + getDiaChi() + "\"" + ", soDienThoai=\""
                + getSoDienThoai() + "\"" ;
    }
}
