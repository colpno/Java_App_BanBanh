package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslayer.ChiTietPhieuNhapHang;

public class tblChiTietPhieuNhapHang {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public tblChiTietPhieuNhapHang() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<ChiTietPhieuNhapHang> get() {
        ChiTietPhieuNhapHang CTPNH;
        ArrayList<ChiTietPhieuNhapHang> CTPNHArr = new ArrayList<ChiTietPhieuNhapHang>();
        String sql = "SELECT * FROM chitietphieunhaphang ORDER BY maPhieu asc";

        try {
            rs = conn.makeQuery(sql);

            while (rs.next()) {
                CTPNH = new ChiTietPhieuNhapHang(rs.getInt("maPhieu"), rs.getInt("maSP"), rs.getInt("soLuong"),
                        rs.getInt("donGiaGoc"), rs.getInt("thanhTien"));
                CTPNHArr.add(CTPNH);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return CTPNHArr;
    };

    public Boolean add(ChiTietPhieuNhapHang CTPNH) {
        String resetAI = "ALTER TABLE chitietphieunhaphang AUTO_INCREMENT = 1";
        String sql = "INSERT INTO chitietphieunhaphang(maPhieu,maSP,soLuong,donGiaGoc,thanhTien) VALUES(\""
                + CTPNH.getMaPhieu() + "\",\"" + CTPNH.getMaSP() + "\",\"" + CTPNH.getSoLuong() + "\",\""
                + CTPNH.getDonGiaGoc() + "\",\"" + CTPNH.getThanhTien() + "\")";

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
        String sql = "INSERT INTO chitietphieunhaphang(maPhieu,maSP,soLuong,donGiaGoc,thanhTien) VALUES" + inserString;

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

    public Boolean removeByMaSP(int maPhieu, String removeString) {
        String sql = "DELETE FROM chitietphieunhaphang WHERE maPhieu = " + maPhieu + " AND " + removeString;

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Boolean removeByMaPhieu(Integer[] maPhieu) {
        String sql = "DELETE FROM chitietphieunhaphang WHERE maPhieu IN (";

        for (Integer i : maPhieu) {
            sql += i + ",";
        }
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1) + ")";
        }

        try {
            conn.makeUpdate(sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Boolean edit(ChiTietPhieuNhapHang CTPNH) {
        String sql = "UPDATE chitietphieunhaphang SET " + CTPNH.toString() + " WHERE maSP = " + CTPNH.getMaSP()
                + " AND maPhieu = " + CTPNH.getMaPhieu();

        try {
            conn.makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
