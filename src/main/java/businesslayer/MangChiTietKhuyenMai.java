
package businesslayer;

import datalayer.tblChiTietKhuyenMai;
import java.sql.ResultSet;
import java.util.ArrayList;


public class MangChiTietKhuyenMai {
    private tblChiTietKhuyenMai DSCTKM = new tblChiTietKhuyenMai();
    public ArrayList<ChiTietKhuyenMai> ctkmArr=new ArrayList<ChiTietKhuyenMai>(); 
    public MangChiTietKhuyenMai() {
        get();
    }

    public void get() {
        ctkmArr=DSCTKM.get();
    }
    public String[] title() {
        String[] title = {"STT", "Mã KM","Mã SP","%"};
        return title;
    }

    public double getTongSoTrang(int soLuong) {
        return Math.ceil((double) DSCTKM.get(0, 0).size() / soLuong);
    }

    public ArrayList<ChiTietKhuyenMai> get(int soLuong, int trang) {
        return DSCTKM.get(soLuong, trang);
    }

    public void add(int maKM, int maSP, String hinhThucKhuyenMai, int phanTramKhuyenMai) {
        ChiTietKhuyenMai newCTKM = new ChiTietKhuyenMai(maKM, maSP, hinhThucKhuyenMai, phanTramKhuyenMai);
        DSCTKM.add(newCTKM);
    }

    public void remove(int maKM) {
        DSCTKM.remove(maKM);
    }

    public void edit(int maKM, int maSP, String hinhThucKhuyenMai, int phanTramKhuyenMai) {
        ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai(maKM, maSP, hinhThucKhuyenMai, phanTramKhuyenMai);
        DSCTKM.edit(ctkm);
    }

    /*public ChiTietKhuyenMai search(int maKM) {
        return DSCTKM.search(maKM);
    }*/
    
    public ArrayList<ChiTietKhuyenMai> search(int maKM) {
        ArrayList<ChiTietKhuyenMai> ctkmSr=new ArrayList<ChiTietKhuyenMai>();
        for (ChiTietKhuyenMai ctkm : ctkmArr) {
            if (ctkm.getMaKM()==maKM) {
                ctkmSr.add(ctkm);
            }
        }
        return ctkmSr;
    }

    public ArrayList<ChiTietKhuyenMai> getCtkmArr() {
        return ctkmArr;
    }

    public void setCtkmArr(ArrayList<ChiTietKhuyenMai> ctkmArr) {
        this.ctkmArr = ctkmArr;
    }
    
}
