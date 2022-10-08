
package businesslayer;

public class KhuyenMai {
    private int maKM;
    private String tenKM;
    private String ngayBatDau;
    private String ngayKetThuc;

    public KhuyenMai() {
        this.maKM=0;
        this.tenKM=null;
        this.ngayBatDau=null;
        this.ngayKetThuc=null;
    }
    
    public KhuyenMai(int maKM, String tenKM, String ngayBatDau, String ngayKetThuc) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    @Override
    public String toString() {
        return "maKM=\"" + getMaKM() + "\"" + ", tenKM=\"" + getTenKM() + "\"" + ", ngayBatDau=\"" + getNgayBatDau() + "\"" + ", ngayKetThuc=\""
                + getNgayKetThuc() + "\"" ;
    }
}

