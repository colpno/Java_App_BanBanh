
package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import businesslayer.NhaSanXuat;

public class tblNhaSanXuat {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public tblNhaSanXuat() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<NhaSanXuat> get() {
        NhaSanXuat ncc;
        ArrayList<NhaSanXuat> NCCArr = new ArrayList<NhaSanXuat>();
        String sql = "SELECT * FROM nhasanxuat";
        try {
            rs = conn.makeQuery(sql);
            while (rs.next()) {
                ncc = new NhaSanXuat(rs.getInt("maNSX"),rs.getString("tenNSX"), rs.getString("diaChi"), rs.getString("soDienThoai"));
                NCCArr.add(ncc);
            }
        }
         catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
        return NCCArr;
    }
    
    public ArrayList<NhaSanXuat> get(int soLuong, int trang) {
        NhaSanXuat nsx;
        ArrayList<NhaSanXuat> NSXArr = new ArrayList<NhaSanXuat>();
        String sql;
        if (trang == 0 && soLuong == 0) {
            sql = "SELECT * FROM nhasanxuat";
        } else {
            sql = "SELECT * FROM nhasanxuat LIMIT " + soLuong + " OFFSET " + (trang - 1) * soLuong;
        }

        try {
            rs = conn.makeQuery(sql);
            /*ConnectToDatabase c = new ConnectToDatabase();
            c.getConnection();
            ResultSet ngayMoi = c.makeQuery("SELECT DATE_FORMAT(ngaySinh, \"%d/%l/%Y\") as ngayMoi FROM nhasanxuat");*/

            while (rs.next() /*&& ngayMoi.next()*/) {
                nsx = new NhaSanXuat(rs.getInt("maNSX"),rs.getString("tenNSX"), rs.getString("diaChi"), rs.getString("soDienThoai"));
                NSXArr.add(nsx);
                nsx.getMaNSX();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
        return NSXArr;
    };

    public void add(NhaSanXuat nsx) {
        String sql1="ALTER TABLE nhasanxuat AUTO_INCREMENT = 1 ;";
        String sql = "INSERT INTO nhasanxuat(tenNSX,diaChi,soDienThoai) VALUES(\"" 
            + nsx.getTenNSX() + "\",\"" + nsx.getDiaChi() + "\",\"" + nsx.getSoDienThoai() +  "\")";

        try {
            conn.makeUpdate(sql1);
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
    }

    public void remove(int maNSX) {
        String sql = "DELETE FROM nhasanxuat WHERE maNSX IN (" + maNSX + ")";
        // String sql = "DELETE FROM nhasanxuat WHERE maNV IN (";
        // int length = maNV.length;

        // for (int i = 0; i < length; i++) {
        // sql += maNV[i] + ",";
        // }
        // if (sql.endsWith(",")) {
        // sql = sql.substring(0, sql.length() - 1) + ")";
        // }

        try {
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
    }

    public void edit(NhaSanXuat nsx) {
        String sql = "UPDATE nhasanxuat SET " + nsx.toString() + " WHERE maNSX = " + nsx.getMaNSX();
        System.out.println(nsx.toString() );
        System.out.println(nsx.getMaNSX());
        try {
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
    }

    public NhaSanXuat search(int maNSX) {
        String sql = "SELECT * FROM nhasanxuat WHERE maNSX = " + maNSX;
        ArrayList<NhaSanXuat> NSXArr=new ArrayList<NhaSanXuat>();
        NhaSanXuat nsx=new NhaSanXuat();
        try {
            rs = conn.makeQuery(sql);
            rs.next();
                nsx.setMaNSX(rs.getInt(1));
                nsx.setTenNSX(rs.getString(2));
                nsx.setDiaChi(rs.getString(3));
                nsx.setSoDienThoai(rs.getString(4));
            return nsx;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
        return null;
    }
}
