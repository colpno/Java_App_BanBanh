package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslayer.DungChung;
import businesslayer.KhachHang;

public class tblKhachHang {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public tblKhachHang() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<KhachHang> get() {
        KhachHang kh;
        ArrayList<KhachHang> KHArr = new ArrayList<KhachHang>();
        String sql = "SELECT * FROM khachhang";

        try {
            rs = conn.makeQuery(sql);
            ConnectToDatabase c = new ConnectToDatabase();
            c.getConnection();

            while (rs.next()) {
                String ngayMoi = DungChung.dashToSlash(rs.getString("ngaySinh").toString());
                kh = new KhachHang(rs.getInt("maKH"), rs.getInt("maTK"), rs.getString("ho"), rs.getString("ten"),
                        ngayMoi, rs.getString("diaChi"), rs.getString("soDienThoai"));
                KHArr.add(kh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return KHArr;
    };

    public Boolean add(KhachHang kh) {
        String resetAI = "ALTER TABLE khachhang AUTO_INCREMENT = 1";
        String sql = "INSERT INTO khachhang(maTK,ho,ten,ngaySinh,diaChi,soDienThoai) VALUES(\"" + 1 + "\",\""
                + kh.getHo() + "\",\"" + kh.getTen() + "\",\"" + DungChung.slashToDash(kh.getNgaySinh()) + "\",\""
                + kh.getDiaChi() + "\",\"" + kh.getSdt() + "\")";

        try {
            conn.makeUpdate(resetAI);
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Boolean excelToDB(String inserString) {
        String resetAI = "ALTER TABLE nhanvien AUTO_INCREMENT = 1";
        String sql = "INSERT INTO khachhang(maTK,ho,ten,ngaySinh,diaChi,soDienThoai) VALUES" + inserString;

        try {
            conn.makeUpdate(resetAI);
            conn.makeUpdate(sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tblNhanVien -> Add : Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Boolean remove(Integer[] maKH) {
        String sql = "DELETE FROM khachhang WHERE maKH IN (";
        int length = maKH.length;

        for (int i = 0; i < length; i++) {
            sql += maKH[i] + ",";
        }
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1) + ")";
        }

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void edit(KhachHang kh) {
        String sql = "UPDATE khachhang SET " + kh.toString() + " WHERE maKH = " + kh.getMaKH();

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
