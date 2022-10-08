
package datalayer;

import businesslayer.Loai;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class tblLoai {

    private MySQLConnect mySQLConnect = new MySQLConnect("localhost", "root", "", "banhang");
    private ResultSet result;

    public ArrayList<Loai> getAllLoai() {

        ArrayList<Loai> loaiList = new ArrayList<Loai>();
        String sql = "select * from loaisanpham";

        try {
            result = mySQLConnect.executeQuery(sql);
            while (result.next()) {
                Loai l = new Loai(result.getInt("maLoai"), result.getString("tenLoai"));
                loaiList.add(l);
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return loaiList;
    }

    public int updateLoai(Loai l, int id) {
        int re = 0;
        String spl = "update loai set maLoai=" + l.getMaLoai() + ",tenLoai='" + l.getTenLoai() + "' where maLoai=" + id;
        try {
            re = mySQLConnect.executeUpdate(spl);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int deleteLoai(int id) {
        int re = 0;
        String sql = "delete from loaisanpham where maLoai=" + id;
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;
    }

    public int insertLoai(Loai l) {
        int re = 0;
        String sql = "insert into loai(maLoai,tenLoai) values ('" + l.getMaLoai() + "','" + l.getTenLoai() + "')";
        try {
            re = mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return re;

    }

    public int getLastMaLoai() {
        String sql = "select maLoai from loaisanpham  order by maLoai desc limit 1";
        int ma = 0;
        try {
            result = mySQLConnect.executeQuery(sql);
            if (result.next()) {
                ma = result.getInt("maLoai");
            }
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ma;
    }

}
