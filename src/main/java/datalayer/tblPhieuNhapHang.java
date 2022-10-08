package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslayer.DungChung;
import businesslayer.PhieuNhapHang;

public class tblPhieuNhapHang {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public tblPhieuNhapHang() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<PhieuNhapHang> get() {
        PhieuNhapHang pnh;
        ArrayList<PhieuNhapHang> PNHArr = new ArrayList<PhieuNhapHang>();
        String sql = "SELECT * FROM phieunhaphang";

        try {
            rs = conn.makeQuery(sql);
            ConnectToDatabase c = new ConnectToDatabase();
            c.getConnection();

            while (rs.next()) {
                String ngayMoi = DungChung.dashToSlash(rs.getString("ngayNhap").toString());
                pnh = new PhieuNhapHang(rs.getInt("maPhieu"), rs.getInt("maNCC"), rs.getInt("maNV"), ngayMoi,
                        rs.getInt("tongTien"));
                PNHArr.add(pnh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return PNHArr;
    };

    public Boolean add(PhieuNhapHang pnh) {
        String resetAI = "ALTER TABLE phieunhaphang AUTO_INCREMENT = 1";
        String sql = "INSERT INTO phieunhaphang(maNCC,maNV,ngayNhap,tongTien) VALUES(\"" + pnh.getMaNCC() + "\",\""
                + pnh.getMaNV() + "\",\"" + DungChung.slashToDash(pnh.getNgayNhap()) + "\",\"" + pnh.getTongTien()
                + "\")";

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
        String sql = "INSERT INTO phieunhaphang(maNCC,maNV,ngayNhap,tongTien) VALUES" + inserString;

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

    public Boolean remove(Integer[] maPhieu) {
        String sql = "DELETE FROM phieunhaphang WHERE maPhieu IN (";
        int length = maPhieu.length;

        for (int i = 0; i < length; i++) {
            sql += maPhieu[i] + ",";
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

    public Boolean edit(PhieuNhapHang pnh) {
        String sql = "UPDATE phieunhaphang SET " + pnh.toString() + " WHERE maPhieu = " + pnh.getMaPhieu();

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void updateTongTien(int maPhieu, int tongTien) {
        String sql = "UPDATE phieunhaphang SET tongTien = " + tongTien + " WHERE maPhieu = " + maPhieu;

        try {
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
