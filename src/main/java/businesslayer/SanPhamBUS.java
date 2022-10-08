/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import datalayer.tblSanPham;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class SanPhamBUS {
    private tblSanPham dsSP = new tblSanPham();
    public ArrayList<SanPham> spArr=new ArrayList<SanPham>(); 
    public SanPhamBUS() {
        spArr=dsSP.getAllSP(0,0);
    }
    public String[] titleH() {
        String[] title = { "STT","Mã SP","Mã loại","Mã NSX","Tên SP","Đơn giá","%","Xóa"};
        return title;
    }
    public String[] titleC() {
        String[] title = { "STT","Mã SP","Mã loại","Mã NSX","Tên SP","Đơn giá"};
        return title;
    }

    public ArrayList<SanPham> getSpArr() {
        return spArr;
    }

    public void setSpArr(ArrayList<SanPham> spArr) {
        this.spArr = spArr;
    }
    
    public SanPham search(int maSP) {
        SanPham spSr=new SanPham();
        for (SanPham sp : spArr ) {
            if (sp.getMaSP()==maSP) {
                spSr=sp;
                break;
            }
        }
        return spSr;
    }
     public ArrayList<SanPham> searchTenSP(String tenSP) {
        ArrayList<SanPham> spSr=new ArrayList<SanPham>();
        String s2=tenSP.toLowerCase();
        for (SanPham sp : spArr ) {
            String s1=sp.getTenSP().toLowerCase();
            if (s1.indexOf(s2)>=0) {
                spSr.add(sp);
            }
        }
        return spSr;
    }
}
