
package datalayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import businesslayer.ChiTietKhuyenMai;
import businesslayer.SanPham;
import java.sql.SQLException;

public class tblChiTietKhuyenMai {
    private ConnectToDatabase conn = new ConnectToDatabase();
        private ResultSet rs;
        
        public tblChiTietKhuyenMai() {
            try {
                conn.getConnection();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        public ArrayList<ChiTietKhuyenMai> get() {
        ChiTietKhuyenMai ctkm;
        ArrayList<ChiTietKhuyenMai> ctkmArr = new ArrayList<ChiTietKhuyenMai>();
        String sql = "SELECT * FROM chitietkhuyenmai";
        try {
            rs = conn.makeQuery(sql);
            while (rs.next()) {
                ctkm = new ChiTietKhuyenMai(rs.getInt("maKM"),rs.getInt("maSP"), rs.getString("hinhThucKhuyenMai"), rs.getInt("phanTramKhuyenMai"));
                ctkmArr.add(ctkm);
            }
        }
         catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
        return ctkmArr;
        }
        
        public ArrayList<ChiTietKhuyenMai> get(int soLuong, int trang) {
        ChiTietKhuyenMai ctkm;
        ArrayList<ChiTietKhuyenMai> CTKMArr = new ArrayList<ChiTietKhuyenMai>();
        String sql;
        if (trang == 0 && soLuong == 0) {
            sql = "SELECT * FROM chitietkhuyenmai";
        } else {
            sql = "SELECT * FROM chitietkhuyenmai LIMIT " + soLuong + " OFFSET " + (trang - 1) * soLuong;
        }

        try {
            rs = conn.makeQuery(sql);
            
            /*ConnectToDatabase c1 = new ConnectToDatabase();
            c1.getConnection();
            ResultSet ngayBatDauM = c1.makeQuery("SELECT DATE_FORMAT(ngayBatDau, \"%d/%l/%Y\") as ngayBatDauM FROM khuyenmai");
            
            ConnectToDatabase c2 = new ConnectToDatabase();
            c2.getConnection();
            ResultSet ngayKetThucM = c2.makeQuery("SELECT DATE_FORMAT(ngayKetThuc, \"%d/%l/%Y\") as ngayKetThucM FROM khuyenmai");*/

            while (rs.next()  /*&& ngayBatDauM.next() && ngayKetThucM.next()*/) {
                ctkm = new ChiTietKhuyenMai(rs.getInt("maKM"), rs.getInt("maSP"),rs.getString("hinhThucKhuyenMai"),rs.getInt("phanTramKhuyenMai"));
                CTKMArr.add(ctkm);
                //km.getMaKM();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
        return CTKMArr;
    };

    public void add(ChiTietKhuyenMai nv) {
        String sql = "INSERT INTO chitietkhuyenmai(maKM,maSP,hinhThucKhuyenMai,phanTramKhuyenMai) VALUES(\"" + nv.getMaKM()
                + "\",\"" + nv.getMaSP() + "\",\"" + nv.getHinhThucKhuyenMai() + "\",\"" + nv.getPhanTramKhuyenMai() + "\")";

        try {
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
    }

    public void remove(int maKM) {
        String sql = "DELETE FROM chitietkhuyenmai WHERE maKM IN (" + maKM + ")";
        // String sql = "DELETE FROM nhanvien WHERE maNV IN (";
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

    public void edit(ChiTietKhuyenMai ctkm) {
        String sql = "UPDATE chitietkhuyenmai SET " + ctkm.toString() + " WHERE maKM = " + ctkm.getMaKM();

        try {
            conn.makeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
    }

    public ChiTietKhuyenMai search(int maKM) {
        String sql = "SELECT * FROM chitietkhuyenmai WHERE maKM = " + maKM;
        ChiTietKhuyenMai ctkm=new ChiTietKhuyenMai();
        try {
            rs = conn.makeQuery(sql);
            rs.next();
            ctkm.setMaKM(rs.getInt(1));
            ctkm.setMaSP(rs.getInt(2));
            ctkm.setHinhThucKhuyenMai(rs.getString(3));
            ctkm.setPhanTramKhuyenMai(rs.getInt(4));
            return ctkm;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
        return null;
    }
    
    public void addList(int maKM,ArrayList<ChiTietKhuyenMai> dsSP) throws SQLException {
        remove(maKM);
        try {
            
            for (ChiTietKhuyenMai ctkm : dsSP) {
                String sql1 = "INSERT INTO chitietkhuyenmai(maKM,maSP,phanTramKhuyenMai) VALUES(\"" + maKM
                + "\",\"" + ctkm.getMaSP() +  "\",\"" + ctkm.getPhanTramKhuyenMai() + "\")";
                conn.makeUpdate(sql1);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
}
