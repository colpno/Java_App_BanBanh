
package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import businesslayer.NhaCungCap;

public class tblNhaCungCap {
    private ConnectToDatabase conn = new ConnectToDatabase();
    private ResultSet rs;

    public tblNhaCungCap() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList<NhaCungCap> get() {
        NhaCungCap ncc;
        ArrayList<NhaCungCap> NCCArr = new ArrayList<NhaCungCap>();
        String sql = "SELECT * FROM nhacungcap";
        try {
            rs = conn.makeQuery(sql);
            while (rs.next()) {
                ncc = new NhaCungCap(rs.getInt("maNCC"),rs.getString("tenNCC"), rs.getString("diaChi"), rs.getString("soDienThoai"));
                NCCArr.add(ncc);
            }
        }
         catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
        return NCCArr;
    }
        
    public ArrayList<NhaCungCap> get(int soLuong, int trang) {  
        NhaCungCap ncc;
        ArrayList<NhaCungCap> NCCArr = new ArrayList<NhaCungCap>();
        String sql;
        if (trang == 0 && soLuong == 0) {
            sql = "SELECT * FROM nhacungcap";
        } else {
            sql = "SELECT * FROM nhacungcap LIMIT " + soLuong + " OFFSET " + (trang - 1) * soLuong;
        }

        try {
            rs = conn.makeQuery(sql);
            /*ConnectToDatabase c = new ConnectToDatabase();
            c.getConnection();
            ResultSet ngayMoi = c.makeQuery("SELECT DATE_FORMAT(ngaySinh, \"%d/%l/%Y\") as ngayMoi FROM nhacungcap");*/

            while (rs.next() /*&& ngayMoi.next()*/) {
                ncc = new NhaCungCap(rs.getInt("maNCC"),rs.getString("tenNCC"), rs.getString("diaChi"), rs.getString("soDienThoai"));
                NCCArr.add(ncc);
                ncc.getMaNCC();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
        return NCCArr;
    };

    public void add(NhaCungCap ncc) {
        String sql1="ALTER TABLE nhacungcap AUTO_INCREMENT = 1 ;";
        String sql = "INSERT INTO nhacungcap(tenNCC,diaChi,soDienThoai) VALUES(\"" 
                 + ncc.getTenNCC() + "\",\"" + ncc.getDiaChi() + "\",\"" + ncc.getSoDienThoai() +  "\")";

        try {
            conn.makeUpdate(sql1);
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
    }

    public void remove(int maNCC) {
        //String sql = "DELETE FROM nhacungcap WHERE maNCC IN (" + maNCC + ")";
        String sql = "DELETE FROM nhacungcap WHERE maNCC =" +maNCC;
        // String sql = "DELETE FROM nhacungcap WHERE maNV IN (";
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

    public void edit(NhaCungCap ncc) {
        String sql = "UPDATE nhacungcap SET " + ncc.toString() + " WHERE maNCC = " + ncc.getMaNCC();

        try {
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
    }

    public NhaCungCap search(int maNCC) {
        String sql = "SELECT * FROM nhacungcap WHERE maNCC = " + maNCC;
        ArrayList<NhaCungCap> NCCArr=new ArrayList<NhaCungCap>();
        NhaCungCap ncc=new NhaCungCap();
        try {
            rs = conn.makeQuery(sql);
            rs.next();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiaChi(rs.getString(3));
                ncc.setSoDienThoai(rs.getString(4));
            return ncc;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
        return null;
    }
    
}
