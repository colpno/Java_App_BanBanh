
package businesslayer;

import datalayer.tblNhaSanXuat;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MangNhaSanXuat {
    private tblNhaSanXuat DSNSX = new tblNhaSanXuat();
    public ArrayList<NhaSanXuat> nsxArr=new ArrayList<NhaSanXuat>(); 
    public MangNhaSanXuat() {
        nsxArr=DSNSX.get();
    }

    public String[] title() {
        String[] title = { "STT","Mã NSX","Tên NSX","Địa chỉ","Số điện thoại","Sửa/Xóa"};
        return title;
    }

    public double getTongSoTrang(int soLuong) {
        return Math.ceil((double) DSNSX.get(0, 0).size() / soLuong);
    }

    public ArrayList<NhaSanXuat> get(int soLuong, int trang) {
        return DSNSX.get(soLuong, trang);
    }

    public void add(int maNSX, String tenNSX, String diaChi, String soDienThoai) {
        NhaSanXuat newNSX = new NhaSanXuat(maNSX,tenNSX,diaChi,soDienThoai);
        DSNSX.add(newNSX);
    }

    public void remove(int maNSX) {
        DSNSX.remove(maNSX);
    }

    public void edit(int maNSX, String tenNSX, String diaChi, String soDienThoai) {
        NhaSanXuat nsx = new NhaSanXuat(maNSX,tenNSX,diaChi,soDienThoai);
        DSNSX.edit(nsx);
    }

    
    public NhaSanXuat search(int maNSX) {
        //return DSNCC.search(maNCC);
        for (NhaSanXuat nsx : nsxArr ) {
            if (nsx.getMaNSX()==maNSX) {
                return nsx;
            }
        }
        return null;
    }
    public ArrayList<NhaSanXuat> search(String tenNSX) {
        ArrayList<NhaSanXuat> NSXSr=new ArrayList<NhaSanXuat>();
        String s2=tenNSX.toLowerCase();
        for (NhaSanXuat nsx : nsxArr ) {
            String s1=nsx.getTenNSX().toLowerCase();
            if (s1.indexOf(s2)>=0) {
                NSXSr.add(nsx);
            }
        }
        return NSXSr;
    }

    public ArrayList<NhaSanXuat> getNsxArr() {
        return nsxArr;
    }

    public void setNsxArr(ArrayList<NhaSanXuat> nsxArr) {
        this.nsxArr = nsxArr;
    }

}
