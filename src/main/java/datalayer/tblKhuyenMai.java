
package datalayer;

import businesslayer.ChiTietKhuyenMai;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import businesslayer.KhuyenMai;
import businesslayer.SanPham;

public class tblKhuyenMai {
        private ConnectToDatabase conn = new ConnectToDatabase();
        private ResultSet rs;
        private tblChiTietKhuyenMai tCTKM=new tblChiTietKhuyenMai();
        public tblKhuyenMai() {
            try {
                conn.getConnection();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        public ArrayList<KhuyenMai> get() {
        KhuyenMai km;
        ArrayList<KhuyenMai> KMArr = new ArrayList<KhuyenMai>();
        String sql = "SELECT * FROM khuyenmai";
        try {
            rs = conn.makeQuery(sql);
            
            ConnectToDatabase c1 = new ConnectToDatabase();
            c1.getConnection();
            ResultSet ngayBatDauM = c1.makeQuery("SELECT DATE_FORMAT(ngayBatDau, \"%d/%l/%Y\") as ngayBatDauM FROM khuyenmai");
            
            ConnectToDatabase c2 = new ConnectToDatabase();
            c2.getConnection();
            ResultSet ngayKetThucM = c2.makeQuery("SELECT DATE_FORMAT(ngayKetThuc, \"%d/%l/%Y\") as ngayKetThucM FROM khuyenmai");

            while (rs.next()  && ngayBatDauM.next() && ngayKetThucM.next()) {
                km = new KhuyenMai(rs.getInt("maKM"), rs.getString("tenKM"),/*ngayBatDauM.getString("ngayBatDauM").toString()*/rs.getString("ngayBatDau").toString(),
                        /*ngayKetThucM.getString("ngayKetThucM").toString()*/rs.getString("ngayKetThuc").toString());
                KMArr.add(km);
            }
            for (KhuyenMai ym: KMArr) {
                System.out.println(ym.getNgayBatDau());
            }
            
        }
         catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
        return KMArr;
        }
        public ArrayList<KhuyenMai> get(int soLuong, int trang) {
        KhuyenMai km;
        ArrayList<KhuyenMai> KMArr = new ArrayList<KhuyenMai>();
        String sql;
        if (trang == 0 && soLuong == 0) {
            sql = "SELECT * FROM khuyenmai";
        } else {
            sql = "SELECT * FROM khuyenmai LIMIT " + soLuong + " OFFSET " + (trang - 1) * soLuong;
        }

        try {
            rs = conn.makeQuery(sql);
            
            ConnectToDatabase c1 = new ConnectToDatabase();
            c1.getConnection();
            ResultSet ngayBatDauM = c1.makeQuery("SELECT DATE_FORMAT(ngayBatDau, \"%d/%l/%Y\") as ngayBatDauM FROM khuyenmai");
            
            ConnectToDatabase c2 = new ConnectToDatabase();
            c2.getConnection();
            ResultSet ngayKetThucM = c2.makeQuery("SELECT DATE_FORMAT(ngayKetThuc, \"%d/%l/%Y\") as ngayKetThucM FROM khuyenmai");

            while (rs.next()  && ngayBatDauM.next() && ngayKetThucM.next()) {
                km = new KhuyenMai(rs.getInt("maKM"), rs.getString("tenKM"),ngayBatDauM.getString("ngayBatDauM").toString(),
                        ngayKetThucM.getString("ngayKetThucM").toString());
                KMArr.add(km);
                //km.getMaKM();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
        return KMArr;
    };

    public void add(KhuyenMai nv,ArrayList<ChiTietKhuyenMai> dsSP) {
        String sql="ALTER TABLE khuyenmai AUTO_INCREMENT = 1 ;";
        String sql1 = "INSERT INTO khuyenmai(tenKM,ngayBatDau,ngayKetThuc) VALUES(\"" + nv.getTenKM() 
                + "\",\"" + nv.getNgayBatDau() + "\",\"" + nv.getNgayKetThuc() + "\")";
        String sql2="SELECT AUTO_INCREMENT AS 'maKMN' FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'banhang' "
                + "AND   TABLE_NAME   = 'khuyenmai';";
        try {
            conn.makeUpdate(sql);
            conn.makeUpdate(sql1);
            rs=conn.makeQuery(sql2);
            rs.next();
            tCTKM.addList(rs.getInt("maKMN")-1, dsSP);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
    }

    public void remove(int maKM) {
        String sql = "DELETE FROM khuyenmai WHERE maKM IN (" + maKM + ")";
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

    public void edit(KhuyenMai km,ArrayList<ChiTietKhuyenMai> dsSP) {
        String sql = "UPDATE khuyenmai SET " + km.toString() + " WHERE maKM = " + km.getMaKM();
        
        try {
            conn.makeUpdate(sql);
            tCTKM.addList(km.getMaKM(), dsSP);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(sql);
    }

    public KhuyenMai search(int maKM) {
        String sql = "SELECT * FROM khuyenmai WHERE maKM = " + maKM;
        KhuyenMai km=new KhuyenMai();
        try {
            rs = conn.makeQuery(sql);
            ConnectToDatabase c1 = new ConnectToDatabase();
            c1.getConnection();
            ResultSet ngayBatDauM = c1.makeQuery("SELECT DATE_FORMAT(ngayBatDau, \"%d/%l/%Y\") as ngayBatDauM FROM khuyenmai");
            
            ConnectToDatabase c2 = new ConnectToDatabase();
            c2.getConnection();
            ResultSet ngayKetThucM = c2.makeQuery("SELECT DATE_FORMAT(ngayKetThuc, \"%d/%l/%Y\") as ngayKetThucM FROM khuyenmai");
            
            rs.next();
            ngayBatDauM.next();
            ngayKetThucM.next();
                km.setMaKM(rs.getInt(1));
                km.setTenKM(rs.getString(2));
                km.setNgayBatDau(ngayBatDauM.getString("ngayBatDauM"));
                km.setNgayKetThuc(ngayKetThucM.getString("ngayKetThucM"));
            return km;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
        return null;
    }
}
