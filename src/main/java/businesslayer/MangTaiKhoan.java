package businesslayer;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import datalayer.tblTaiKhoan;

public class MangTaiKhoan {
    private tblTaiKhoan DSTK = new tblTaiKhoan();
    private ArrayList<TaiKhoan> TKArr = new ArrayList<TaiKhoan>();
    private String[] moreTitle = { "Ảnh đại diện", "Mã TK", "Mã quyền", "Tài khoản", "Mật khẩu", "Cấm" };

    public MangTaiKhoan() {
        this.TKArr = DSTK.readDB();
    }

    public String[] title() {
        return moreTitle;
    }

    public void setArr(ArrayList<TaiKhoan> arr) {
        TKArr = arr;
    }

    public ArrayList<TaiKhoan> getTKArr() {
        return this.TKArr;
    }

    public ArrayList<TaiKhoan> get(int soLuongRow, int trang) {
        ArrayList<TaiKhoan> newTKArr = new ArrayList<TaiKhoan>();
        int start = soLuongRow * trang - soLuongRow;
        int length = soLuongRow * trang;
        for (int i = start; i < length && i < TKArr.size(); i++) {
            newTKArr.add(TKArr.get(i));
        }
        return newTKArr;
    }

    public ArrayList<TaiKhoan> getAll() {
        return this.TKArr;
    }

    public double getTongSoTrang(int soLuong) {
        return TKArr.size() > 0 ? Math.ceil((double) TKArr.size() / soLuong) : 1.0;
    }

    public ArrayList<TaiKhoan> search(String col, String giaTriTim) {
        ArrayList<TaiKhoan> tkArr = new ArrayList<TaiKhoan>();
        for (TaiKhoan tk : TKArr) {
            switch (col) {
                case "Mã TK": {
                    if (tk.getMaTK() == Integer.parseInt(giaTriTim)) {
                        tkArr.add(tk);
                    }
                    break;
                }
                case "Mã quyền": {
                    if (tk.getMaQuyen() == Integer.parseInt(giaTriTim)) {
                        tkArr.add(tk);
                    }
                    break;
                }
                case "Tài khoản": {
                    if (tk.getTenTaiKhoan().contains(giaTriTim)) {
                        tkArr.add(tk);
                    }
                    break;
                }
                case "Mật khẩu": {
                    if (tk.getMatKhau().contains(giaTriTim)) {
                        tkArr.add(tk);
                    }
                    break;
                }
                case "Cấm": {
                    if (tk.getTrangThai() == Integer.parseInt(giaTriTim)) {
                        tkArr.add(tk);
                    }
                    break;
                }
            }
        }
        return tkArr;
    }

}
