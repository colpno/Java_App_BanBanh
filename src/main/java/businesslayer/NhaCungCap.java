
package businesslayer;

public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private String soDienThoai;

    public NhaCungCap() {
        this.maNCC = 0;
        this.tenNCC = null;
        this.diaChi = null;
        this.soDienThoai = null;
    }

    public NhaCungCap(int maNCC, String tenNCC, String diaChi, String soDienThoai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
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
        return "maNCC=\"" + getMaNCC() + "\"" + ", tenNCC=\"" + getTenNCC() + "\"" + ", diaChi=\"" + getDiaChi() + "\""
                + ", soDienThoai=\"" + getSoDienThoai() + "\"";
    }
}
