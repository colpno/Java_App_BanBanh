package datalayer;

import java.sql.*;
import java.util.ArrayList;
import businesslayer.TaiKhoan;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class tblTaiKhoan {
    private ConnectToDatabase conn = new ConnectToDatabase();

    public tblTaiKhoan() {
        try {
            conn.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<TaiKhoan> readDB() {
        ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
        try {
            String sql = "SELECT * FROM taikhoan";
            ResultSet r = conn.makeQuery(sql);
            if (r != null) {
                while (r.next()) {
                    int matk = r.getInt("maTK");
                    int maquyen = r.getInt("maQuyen");
                    String tentk = r.getString("tenTaiKhoan");
                    String mk = r.getString("MatKhau");
                    int tt = r.getInt("trangThai");
                    String anhdd = r.getString("anhDaiDien");
                    int dangnhap = r.getInt("dangNhap");

                    dstk.add(new TaiKhoan(matk, maquyen, tentk, mk, tt, anhdd, dangnhap));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dstk;
    }

    public ArrayList<TaiKhoan> readDB(int soLuong, int trang) {
        ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
        String sql;
        if (trang == 0 && soLuong == 0) {
            sql = "SELECT * FROM taikhoan";
        } else {
            sql = "SELECT * FROM taikhoan LIMIT " + soLuong + " OFFSET " + (trang - 1) * soLuong;
        }

        try {
            ResultSet r = conn.makeQuery(sql);
            if (r != null) {
                while (r.next()) {
                    int matk = r.getInt("maTK");
                    int maquyen = r.getInt("maQuyen");
                    String tentk = r.getString("tenTaiKhoan");
                    String mk = r.getString("MatKhau");
                    int tt = r.getInt("trangThai");
                    String anhdd = r.getString("anhDaiDien");
                    int dangnhap = r.getInt("dangNhap");

                    dstk.add(new TaiKhoan(matk, maquyen, tentk, mk, tt, anhdd, dangnhap));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dstk;
    }

    public Boolean add(TaiKhoan tk) {
        try {
            conn.makeUpdate(
                    "INSERT INTO `taikhoan` (`maTK`, `maQuyen`, `tenTaiKhoan`, `matKhau`, `trangThai`, `anhDaiDien`, `dangNhap`) VALUES ('"
                            + tk.getMaTK() + "', '" + tk.getMaQuyen() + "', '" + tk.getTenTaiKhoan() + "', '"
                            + tk.getMatKhau() + "', '" + tk.getTrangThai() + "', '" + tk.getAnhDaiDien() + "', '"
                            + tk.getDangNhap() + "');");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Boolean delete(int maTK) {
        try {
            conn.makeUpdate("DELETE FROM `taikhoan` WHERE `taikhoan`.`maTK` = '" + maTK + "'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Boolean update(int maTK, int maQuyen, String tenTaiKhoan, String matKhau, int trangThai, String anhDaiDien,
            int dangNhap) {
        try {
            conn.makeUpdate("Update taikhoan Set maQuyen='" + maQuyen + "',tenTaiKhoan='" + tenTaiKhoan + "',matKhau='"
                    + matKhau + "',trangThai='" + trangThai + "',anhDaiDien='" + anhDaiDien + "',dangNhap='" + dangNhap
                    + "' where maTK='" + maTK + "'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public TaiKhoan search(int maTK) {
        String sql = "SELECT * FROM taikhoan WHERE maTK = " + maTK;
        ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
        TaiKhoan tk = new TaiKhoan();
        try {
            ResultSet r = conn.makeQuery(sql);
            r.next();
            tk.setMaTK(r.getInt("maTK"));
            tk.setMaQuyen(r.getInt("maQuyen"));
            tk.setTenTaiKhoan(r.getString("tenTaiKhoan"));
            tk.setMatKhau(r.getString("matKhau"));
            tk.setTrangThai(r.getInt("trangThai"));
            tk.setAnhDaiDien(r.getString("anhDaiDien"));
            tk.setDangNhap(r.getInt("dangNhap"));
            return tk;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(sql);
        try {
            conn.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void close() {
        try {
            conn.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(tblTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}