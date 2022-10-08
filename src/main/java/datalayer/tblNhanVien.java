package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslayer.DungChung;
import businesslayer.NhanVien;

public class tblNhanVien {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public tblNhanVien() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tblNhanVien -> Constructor : Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<NhanVien> get() {
        NhanVien nv;
        ArrayList<NhanVien> NVArr = new ArrayList<NhanVien>();
        String sql = "SELECT * FROM nhanvien";

        try {
            rs = conn.makeQuery(sql);
            ConnectToDatabase c = new ConnectToDatabase();
            c.getConnection();

            while (rs.next()) {
                String ngayMoi = DungChung.dashToSlash(rs.getString("ngaySinh").toString());
                nv = new NhanVien(rs.getInt("maNV"), rs.getInt("maTK"), rs.getString("ho"), rs.getString("ten"),
                        ngayMoi, rs.getString("diaChi"), rs.getString("soDienThoai"), rs.getInt("luong"));
                NVArr.add(nv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tblNhanVien -> Get : Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return NVArr;
    };

    public Boolean add(NhanVien nv) {
        String resetAI = "ALTER TABLE nhanvien AUTO_INCREMENT = 1";
        String sql = "INSERT INTO nhanvien(maTK,ho,ten,ngaySinh,diaChi,soDienThoai,luong) VALUES(\"" + 1 + "\",\""
                + nv.getHo() + "\",\"" + nv.getTen() + "\",\"" + DungChung.slashToDash(nv.getNgaySinh()) + "\",\""
                + nv.getDiaChi() + "\",\"" + nv.getSdt() + "\",\"" + nv.getLuong() + "\")";

        try {
            conn.makeUpdate(resetAI);
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tblNhanVien -> Add : Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Boolean excelToDB(String inserString) {
        String resetAI = "ALTER TABLE nhanvien AUTO_INCREMENT = 1";
        String sql = "INSERT INTO nhanvien(maTK,ho,ten,ngaySinh,diaChi,soDienThoai,luong) VALUES" + inserString;

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

    public Boolean remove(Integer[] maNV) {
        String sql = "DELETE FROM nhanvien WHERE maNV IN (";
        int length = maNV.length;

        for (int i = 0; i < length; i++) {
            sql += maNV[i] + ",";
        }
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1) + ")";
        }

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tblNhanVien -> Remove : Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Boolean edit(NhanVien nv) {
        String sql = "UPDATE nhanvien SET " + nv.toString() + " WHERE maNV = " + nv.getMaNV();

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tblNhanVien -> Edit : Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
