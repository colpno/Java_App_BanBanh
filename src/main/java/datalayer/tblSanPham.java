
package datalayer;

import businesslayer.ChiTietKhuyenMai;
import businesslayer.SanPham;
import businesslayer.KhuyenMai;
import businesslayer.NhaSanXuat;
import businesslayer.Loai;
import businesslayer.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class tblSanPham {
    private MySQLConnect mySQLConnect = new MySQLConnect("localhost", "root", "", "banhang");
    private ResultSet result;

    public ArrayList<SanPham> getAllSP(int soLuong, int trang) {
        ArrayList<SanPham> spList = new ArrayList<SanPham>();
        String sql;
        if (trang==0&&soLuong==0)
            sql = "select * from sanpham";
        else
            sql = "select * from sanpham LIMIT " + soLuong + " OFFSET " + (trang - 1) * soLuong;

        try {
            result = mySQLConnect.executeQuery(sql);
            while (result.next()) {
                SanPham sp = new SanPham(result.getInt("maSP"), result.getString("tenSP"), result.getInt("maLoai"),
                        result.getInt("maNSX"), result.getInt("soLuong"), result.getFloat("donGia"),
                        result.getString("donViTinh"), result.getString("moTa"), result.getString("anhDaiDien"));
                spList.add(sp);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return spList;
    }

    public int updateSP(SanPham sp, int id) {
        int re = 0;
        String spl = "update sanpham set maSP=" + sp.getMaSP() + ",maLoai=" + sp.getMaLoai() + ",maNSX=" + sp.getMaNSX()
                + ",tenSP=\"" + sp.getTenSP() + "\",soLuong=" + sp.getSoLuong() + ",donGia=" + sp.getDonGia()
                + ",donViTinh='" + sp.getDonViTinh() + "',moTa='" + sp.getMoTa() + "',anhDaiDien='" + sp.getanhDaiDien()
                + "' where maSP=" + id;
        try {
            re = mySQLConnect.executeUpdate(spl);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int deleteSP(int id) {
        int re = 0;
        String sql = "delete from sanpham where maSP=" + id;
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int insertSP(SanPham sp) {
        int re = 0;
        String sql = "insert into sanpham(maSP,maLoai,maNSX,tenSP,soLuong,donGia,donViTinh,moTa,anhDaiDien) values ("
                + sp.getMaSP() + "," + sp.getMaLoai() + "," + sp.getMaNSX() + ",'" + sp.getTenSP() + "',"
                + sp.getSoLuong() + "," + sp.getDonGia() + ",'" + sp.getDonViTinh() + "','" + sp.getMoTa() + "','"
                + sp.getanhDaiDien() + "')";
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;

    }

    public ArrayList<NhaSanXuat> getAllNSX() {
        ArrayList<NhaSanXuat> nsxList = new ArrayList<NhaSanXuat>();
        String sql = "select * from nhasanxuat";

        try {
            result = mySQLConnect.executeQuery(sql);
            while (result.next()) {

                NhaSanXuat nsx = new NhaSanXuat(result.getInt("maNSX"), result.getString("tenNSX"),
                        result.getString("diaChi"), result.getString("soDienThoai"));
                nsxList.add(nsx);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return nsxList;
    }

    public ArrayList<KhuyenMai> getALLKM() {
        ArrayList<KhuyenMai> kmList = new ArrayList<KhuyenMai>();
        String sql = "select maKM,tenKM,DATE_FORMAT(ngayBatDau,'%d/%m/%Y') AS BD,"
                + "DATE_FORMAT(ngayKetThuc,'%d/%m/%Y') AS KT from khuyenmai";
        try {
            result = mySQLConnect.executeQuery(sql);
            while (result.next()) {
                KhuyenMai km = new KhuyenMai(result.getInt("maKM"), result.getString("tenKM"), result.getString("BD"),
                        result.getString("KT"));
                kmList.add(km);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return kmList;

    }

    public ChiTietKhuyenMai getCTKM(int maKM, int maSP) {

        ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai();
        String sql = "select * from chitietkhuyenmai where maKM=" + maKM + " and maSP=" + maSP;
        ResultSet result;
        try {
            result = mySQLConnect.executeQuery(sql);
            if (result.next()) {
                ctkm.setMaKM(result.getInt("maKM"));
                ctkm.setMaSP(result.getInt("maSP"));
                ctkm.setHinhThucKhuyenMai(result.getString("hinhThucKM"));
                ctkm.setPhanTramKhuyenMai(result.getInt("phanTramKM"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return ctkm;

    }

    public int getLastMaSP() {
        String sql = "select maSP from sanpham order by maSP desc limit 1";
        int ma = 0;
        try {
            result = mySQLConnect.executeQuery(sql);
            if (result.next()) {
                ma = result.getInt("maSP");
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ma;
    }

    public int updateQty(int ma, int qty) {
        int re = 0;
        String sql = "update sanpham set soluong=" + qty + " where maSP=" + ma;
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }
}
