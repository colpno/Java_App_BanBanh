package businesslayer;

import datalayer.tblNhaCungCap;
import java.util.ArrayList;

public class MangNhaCungCap {
    private tblNhaCungCap DSNCC = new tblNhaCungCap();
    public ArrayList<NhaCungCap> nccArr = new ArrayList<NhaCungCap>();

    public MangNhaCungCap() {
        nccArr = DSNCC.get();
    }

    public String[] title() {
        String[] title = { "STT", "Mã NCC", "Tên NCC", "Địa chỉ", "Số điện thoại", "Sửa/Xóa" };
        return title;
    }

    public double getTongSoTrang(int soLuong) {
        return Math.ceil((double) DSNCC.get(0, 0).size() / soLuong);
    }

    public ArrayList<NhaCungCap> get(int soLuong, int trang) {
        return DSNCC.get(soLuong, trang);
    }

    public void add(int MaNCC, String tenNCC, String diaChi, String soDienThoai) {
        NhaCungCap newNCC = new NhaCungCap(0, tenNCC, diaChi, soDienThoai);
        DSNCC.add(newNCC);
    }

    public void remove(int maNCC) {
        DSNCC.remove(maNCC);
    }

    public void edit(int maNCC, String tenNCC, String diaChi, String soDienThoai) {
        NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai);
        DSNCC.edit(ncc);
    }

    public NhaCungCap search(int maNCC) {
        // return DSNCC.search(maNCC);
        for (NhaCungCap ncc : nccArr) {
            if (ncc.getMaNCC() == maNCC) {
                return ncc;
            }
        }
        return null;
    }
    public ArrayList<NhaCungCap> search(String tenNCC) {
        // return DSNCC.search(maNCC);
        nccArr=DSNCC.get();
        for (NhaCungCap ncc : nccArr) {
            if (ncc.getTenNCC().toLowerCase().indexOf(tenNCC.toLowerCase())>=0) {
                nccArr.add(ncc);
            }
        }
        return nccArr;
    }
    

    public ArrayList<NhaCungCap> searchMul(String col, String giaTriTim) {
        ArrayList<NhaCungCap> NCCSr = new ArrayList<NhaCungCap>();
        for (NhaCungCap ncc : nccArr) {
            switch (col) {
                case "Mã NCC": {
                    if (ncc.getMaNCC() == Integer.parseInt(giaTriTim)) {
                        NCCSr.add(ncc);
                    }
                    break;
                }
                case "Tên NCC": {
                    if (ncc.getTenNCC().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        NCCSr.add(ncc);
                    }
                    break;
                }
                case "Địa chỉ": {
                    if (ncc.getDiaChi().contains(giaTriTim)) {
                        NCCSr.add(ncc);
                    }
                    break;
                }
                case "Số điện thoại": {
                    if (ncc.getSoDienThoai().contains(giaTriTim)) {
                        NCCSr.add(ncc);
                    }
                    break;
                }
            }
        }
        return NCCSr;
    }

    public ArrayList<NhaCungCap> getNccArr() {
        return nccArr;
    }

    public void setNccArr(ArrayList<NhaCungCap> nccArr) {
        this.nccArr = nccArr;
    }

}
