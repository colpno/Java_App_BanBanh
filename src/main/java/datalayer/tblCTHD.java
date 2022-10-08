
package datalayer;

import java.util.ArrayList;
import businesslayer.ChiTietHoaDon;
import businesslayer.HoaDon;
import businesslayer.SanPham;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class tblCTHD {
    MySQLConnect mySQLConnect=new MySQLConnect("localhost","root","","banhang");
    ResultSet result;
    public ArrayList<ChiTietHoaDon> getALLCTHD(int ma){
        ArrayList<ChiTietHoaDon> cthdList=new ArrayList<ChiTietHoaDon>();
        String sql;
        
        sql="select * from chitiethoadon where maHD="+ma;

        try {
            result = mySQLConnect.executeQuery(sql);
            while(result.next()){
                ChiTietHoaDon cthd=new ChiTietHoaDon(result.getInt("maHD"),result.getInt("maSP"),
                    result.getInt("soLuong"),result.getInt("donGia"),result.getFloat("thanhTien"));
                cthdList.add(cthd);
            }
        mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return cthdList;
    }
    
    public int updateCTHD(ChiTietHoaDon cthd,int idHD,int idSP){
        int re=0;
        String sql="update chitiethoadon set maHD="+cthd.getMaHD()+",maSP="+cthd.getMaSP()+",soLuong="+cthd.getSoLuong()+
                ",donGia="+cthd.getDonGia()+",thanhTien="+cthd.getThanhTien()+" where maHD="+idHD+" and maSP="+idSP;
        try {
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return re;
    }
    public int deleteCTHD(int idHD,int idSP){
        int re=0;
        String sql="delete from chitiethoadon where maHD="+idHD+" and maSP="+idSP;
        try {
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return re;
    }
    public int deleteCTHD(int idHD){
        int re=0;
        String sql="delete from chitiethoadon where maHD="+idHD;
        try {
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return re;
    }
    public int insertCTHD(ChiTietHoaDon cthd){
        int re=0;
        String sql="insert into chitiethoadon(maHD,maSP,soLuong,donGia,thanhTien) values ("+cthd.getMaHD()+","+
                cthd.getMaSP()+","+cthd.getSoLuong()+","+cthd.getDonGia()+","+cthd.getThanhTien()+")";
        try {
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return re;
    }
    
    public int updateQty(int ma,int qty){
        int re=0;
        String sql="update sanpham set soluong="+qty+" where maSP="+ma;
        try {
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return re;
    }
    public int updateQty(int maHD,int maSP,int qty){
        int re=0;
        String sql="update chitiethoadon set soLuong="+qty+" where maHD="+maHD+" and maSP="+maSP;
        try{
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return re;
    }
    public int updateThT(int maHD,int maSP,float total){
        int re=0;
        String sql="update chitiethoadon set thanhTien="+total+" where maHD="+maHD+" and maSP="+maSP;
        try{
            re=mySQLConnect.executeUpdate(sql);
            mySQLConnect.Close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return re;
    }
    
    public int getSL(int maSP){
        String sql="select soLuong from sanpham where maSP="+maSP;
        int qty=0;
        try {
            result = mySQLConnect.executeQuery(sql);
            if(result.next())
                qty=result.getInt("soLuong");
            mySQLConnect.Close();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return qty;
                
    }
    
}
