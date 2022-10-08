package businesslayer;

public class TaiKhoan {
    private int maTK;
    private int maQuyen;
    private String tenTaiKhoan;
    private String matKhau;
    private int trangThai;
    private String anhDaiDien;
    private int dangNhap;

    public TaiKhoan(int maQuyen, String tenTaiKhoan, String matKhau) {
        this.maQuyen = maQuyen;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public TaiKhoan(int maTK, int maQuyen, String tenTaiKhoan, String matKhau, int trangThai, String anhDaiDien,
            int dangNhap) {
        this.maTK = maTK;
        this.maQuyen = maQuyen;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.anhDaiDien = anhDaiDien;
        this.dangNhap = dangNhap;
    }

    public TaiKhoan() {
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public int getDangNhap() {
        return dangNhap;
    }

    public void setDangNhap(int dangNhap) {
        this.dangNhap = dangNhap;
    }

    @Override
    public String toString() {
        return "TaiKhoan(" + "maTK=" + getMaTK() + ", maQuyen=" + getMaQuyen() + ", tenTaiKhoan=" + getTenTaiKhoan()
                + ", matKhau=" + getMatKhau() + ", trangThai=" + getTrangThai() + ", anhDaiDien=" + getAnhDaiDien()
                + ", dangNhap=" + getDangNhap() + '}';
    }
}
