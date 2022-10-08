/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import businesslayer.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class SanPhamDAO {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public SanPhamDAO() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*
     * public ArrayList<SanPham> get() { SanPham sp; ArrayList<SanPham> spArr = new
     * ArrayList<SanPham>(); String sql = "SELECT * FROM sanpham"; try { rs =
     * conn.makeQuery(sql); while (rs.next()) { SanPham sp=new
     * SanPham(rs.getInt("maSP"),rs.getString("tenSP"),rs.getInt("maLoai"),
     * rs.getInt("maNSX"),rs.getInt("soLuong"),rs.getFloat("donGia"),rs.getString(
     * "donViTinh"), rs.getString("moTa"),rs.getString("anhDaiDien"));
     * spArr.add(sp); } } catch (Exception e) { JOptionPane.showMessageDialog(null,
     * "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } return
     * spArr; }
     */
}
