
package datalayer;

import businesslayer.NhanVien;
import businesslayer.KhachHang;
import businesslayer.SanPham;
import businesslayer.HoaDon;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class tblHoaDon {

    MySQLConnect mySQLConnect = new MySQLConnect("localhost", "root", "", "banhang");
    ResultSet result;

    public ArrayList<HoaDon> getALLHD(int soLuong, int trang) {
        ArrayList<HoaDon> hdList = new ArrayList<HoaDon>();
        String sql;
        if (trang == 0 && soLuong == 0)
            sql = "select maHD,maNV,maKH,DATE_FORMAT(ngayLapHoaDon,'%d/%m/%Y') AS ngay,tongTien from hoadon";
        else
            sql = "select maHD,maNV,maKH,DATE_FORMAT(ngayLapHoaDon,'%d/%m/%Y') AS ngay,tongTien from hoadon LIMIT "
                    + soLuong + " OFFSET " + (trang - 1) * soLuong;

        try {
            result = mySQLConnect.executeQuery(sql);

            while (result.next()) {
                HoaDon hd = new HoaDon(result.getInt("maHD"), result.getInt("maNV"), result.getInt("maKH"),
                        result.getString("ngay"), result.getFloat("tongTien"));
                hdList.add(hd);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return hdList;
    }

    public float getTT(int id) {
        String sql = "select tongTien from hoadon where maHD=" + id;
        float total = 0;
        try {
            result = mySQLConnect.executeQuery(sql);
            if (result.next())
                total = result.getFloat("tongTien");
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return total;
    }

    public int updateHD(HoaDon hd, int id) {
        int re = 0;
        String sql = "update hoadon set maHD=" + hd.getMaHD() + ",maNV=" + hd.getMaNV() + ",maKH=" + hd.getMaKH()
                + ",ngayLapHoaDon='" + hd.getNgayLapHoaDon() + "',tongTien=" + hd.getTongTien() + " where maHD=" + id;
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int updateTT(int id, float total) {
        int re = 0;
        String sql = "update hoadon set tongTien=" + total + " where maHD=" + id;
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int deleteHD(int id) {
        int re = 0;
        String sql = "delete from hoadon where maHD=" + id;
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int insertHD(HoaDon hd) {
        int re = 0;
        String sql = "insert into hoadon(maHD,maNV,maKH,ngayLapHoaDon,tongTien) values (" + hd.getMaHD() + ","
                + hd.getMaNV() + "," + hd.getMaKH() + ",'" + hd.getNgayLapHoaDon() + "'," + hd.getTongTien() + ")";
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public ArrayList<NhanVien> getALLNV() {
        ArrayList<NhanVien> nvList = new ArrayList<NhanVien>();
        String sql = "select maNV,maTK,ho,ten,DATE_FORMAT(ngaySinh,'%d/%m/%Y') as ngay,diaChi,soDienThoai,luong from nhanvien";
        ResultSet result;
        try {
            result = mySQLConnect.executeQuery(sql);
            while (result.next()) {
                NhanVien nv = new NhanVien(result.getInt("maNV"), result.getInt("maTK"), result.getString("ho"),
                        result.getString("ten"), result.getString("ngay"), result.getString("diaChi"),
                        result.getString("soDienThoai"), result.getInt("luong"));

                nvList.add(nv);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return nvList;
    }

    public ArrayList<KhachHang> getALLKH() {
        ArrayList<KhachHang> khList = new ArrayList<KhachHang>();
        String sql = "select maKH,maTK,ho,ten,DATE_FORMAT(ngaySinh,'%d/%m/%Y') as ngay,diaChi,soDienThoai from khachhang";
        ResultSet result;
        try {
            result = mySQLConnect.executeQuery(sql);
            while (result.next()) {
                KhachHang kh = new KhachHang(result.getInt("maKH"), result.getInt("maTK"), result.getString("ho"),
                        result.getString("ten"), result.getString("ngay"), result.getString("diaChi"),
                        result.getString("soDienThoai"));
                khList.add(kh);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return khList;
    }

    public int getLastMaHD() {
        int ma = 0;
        String sql = "select maHD from hoadon order by maHD desc limit 1";
        ResultSet result;
        try {
            result = mySQLConnect.executeQuery(sql);
            if (result.next())
                ma = result.getInt("maHD");
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return ma;
    }

}
