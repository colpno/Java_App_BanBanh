
package businesslayer;

import datalayer.tblKhuyenMai;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MangKhuyenMai {
        private tblKhuyenMai DSKM = new tblKhuyenMai();
        private ArrayList<KhuyenMai> kmArr=new ArrayList<KhuyenMai>();
        public MangKhuyenMai() {
            get();
        }

    public void get() {
        kmArr=DSKM.get();
    }    
        
    public String[] title() {
        String[] title = { "STT","Mã KM","Tên KM","Ngày bắt đầu","Ngày kết thúc","Sửa/Xóa"};
        return title;
    }
    
     public String[] titleC() {
        String[] title = { "STT","Mã KM","Tên KM","Ngày bắt đầu","Ngày kết thúc"};
        return title;
    }

    public double getTongSoTrang(int soLuong) {
        return Math.ceil((double) DSKM.get(0, 0).size() / soLuong);
    }

    public ArrayList<KhuyenMai> get(int soLuong, int trang) {
        return DSKM.get(soLuong, trang);
    }

    public void add(int maKM, String tenKM, String ngayBatDau, String ngayKetThuc,ArrayList<ChiTietKhuyenMai> dsSP) {
        KhuyenMai newKM = new KhuyenMai(maKM,tenKM,ngayBatDau,ngayKetThuc);
        DSKM.add(newKM,dsSP);
    }

    public void remove(int maKM) {
        DSKM.remove(maKM);
    }

    public void edit(int maKM, String tenKM, String ngayBatDau, String ngayKetThuc,ArrayList<ChiTietKhuyenMai> dsSP) {
        KhuyenMai km = new KhuyenMai(maKM,tenKM,ngayBatDau,ngayKetThuc);
        DSKM.edit(km,dsSP);
    }

    public ArrayList<KhuyenMai> searchTenKM(String tenKM) {
        ArrayList<KhuyenMai> kmSr=new ArrayList<KhuyenMai>();
        String s2=tenKM.toLowerCase();
        for (KhuyenMai km : kmArr ) {
            String s1=km.getTenKM().toLowerCase();
            if (s1.indexOf(s2)>=0) {
                kmSr.add(km);
            }
        }
        return kmSr;
    }

    public ArrayList<KhuyenMai> getKmArr() {
        return kmArr;
    }

    public void setKmArr(ArrayList<KhuyenMai> kmArr) {
        this.kmArr = kmArr;
    }
    
}
