
package businesslayer;

import datalayer.tblHoaDon;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MangHoaDon {
    private DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    
    private tblHoaDon tblHD=new tblHoaDon();
    
    public ArrayList<HoaDon> hdList=new ArrayList<HoaDon>();
    
    public MangHoaDon(){

    }
    
    public ArrayList<HoaDon> getALLHD(){
        return tblHD.getALLHD(0,0);
        
    }
    
    public ArrayList<HoaDon> getALLHD(int soLuong,int trang){
        return tblHD.getALLHD(soLuong,trang);
    }
    
    public HoaDon getHDByID(int id){
        int t=0;
        hdList=getALLHD();
        for(int i=0;i<hdList.size();i++){
            if(hdList.get(i).getMaHD()==id){
                t=i;
            }
                
        }
        return hdList.get(t);
    
    }
    
    public ArrayList<HoaDon> getHDByTen(String ten){
        ArrayList<HoaDon> kq=new ArrayList<HoaDon>();
        hdList=getALLHD();
        
        String s1=ten.toLowerCase();
     
        for(HoaDon hd: hdList){
            String s2=getTenKH(hd.getMaKH()).toLowerCase();
            
            if(s2.indexOf(s1)>=0)
                kq.add(hd);
        }
        return kq;
    
    }
    public ArrayList<HoaDon> timNangCao(Date dateFrom,Date dateTo,float min,float max){
        
        ArrayList<HoaDon> kq=new ArrayList<HoaDon>();
        hdList=getALLHD();
        for(HoaDon hd: hdList){
            try {
                Date date=fmt.parse(hd.getNgayLapHoaDon());
                
                float tt=hd.getTongTien();
                if(min==0&&max==0){
                    if(date.after(dateFrom)&&date.before(dateTo)){
                        kq.add(hd);
                    }
                }
                else
                    if(date.after(dateFrom)&&date.before(dateTo)&&tt>=min&&tt<=max){
                        kq.add(hd);
                    }
            } catch (ParseException ex) {
                Logger.getLogger(MangHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        return kq;
    }
    
    public ArrayList<KhachHang> getKHByTen(String ten){
        ArrayList<KhachHang> khList=new ArrayList<KhachHang>();
        ArrayList<KhachHang> kq=new ArrayList<KhachHang>();
        khList=getALLKH();
        
        String s1=ten.toLowerCase();
        for(KhachHang kh: khList){
            String s2=kh.getTen().toLowerCase();
            if(s2.indexOf(s1)>=0)
                kq.add(kh);
        }
        return kq;
        
    }
    
    public ArrayList<NhanVien> getNVByTen(String ten){
        ArrayList<NhanVien> nvList=new ArrayList<NhanVien>();
        ArrayList<NhanVien> kq=new ArrayList<NhanVien>();
        nvList=getALLNV();
        
        String s1=ten.toLowerCase();
        for(NhanVien nv: nvList){
            String s2=nv.getTen().toLowerCase();
            if(s2.indexOf(s1)>=0)
                kq.add(nv);
        }
        return kq;
        
    }
    public String getTenKH(int ma){
        ArrayList<KhachHang> khList=getALLKH();
        String ho="",ten="";
        for(KhachHang kh: khList){
            if(ma==kh.getMaKH()){
                ho=kh.getHo();
                ten=kh.getTen();
            }
        }
        return ho+" "+ten;
    }
    
    public String getTenNV(int ma){
        ArrayList<NhanVien> nvList=getALLNV();
        String ho="",ten="";
        for(NhanVien nv: nvList){
            if(ma==nv.getMaNV()){
                ho=nv.getHo();
                ten=nv.getTen();
            }
        }
        return ho+" "+ten;
    }
    
    public DefaultTableModel modelHeader(DefaultTableModel model,String[] name){
        Vector header=new Vector();
        for(String s: name){
            header.add(s);
        }
        model=new DefaultTableModel(header,0);
        return model;
    }
    
    public JTable loadAllHD(JTable table,DefaultTableModel model,ArrayList<HoaDon> hdList,String[] name){
        model=modelHeader(model,name);
        
        ArrayList<KhachHang> khList=getALLKH();
        ArrayList<NhanVien> nvList=getALLNV();
        
        for(HoaDon hd:hdList){
            Vector record=new Vector();
            
            record.add(false);
            record.add(hd.getMaHD());
            record.add(getTenKH(hd.getMaKH()));
            record.add(getTenNV(hd.getMaNV()));
            record.add(hd.getNgayLapHoaDon());
            record.add(hd.getTongTien());
            
            
            model.addRow(record);
       
        }
        table.setModel(model);
        
        return table;
        
    }
    
    public JTable loadAllNV(JTable table,DefaultTableModel model,ArrayList<NhanVien> nvList,String[] name){
        
        model=modelHeader(model,name);

        for(NhanVien nv:nvList){
            Vector record=new Vector();
            

            record.add(nv.getMaNV());
            record.add(nv.getMaTK());
            record.add(nv.getHo());
            record.add(nv.getTen());
            record.add(nv.getNgaySinh());
            record.add(nv.getDiaChi());
            record.add(nv.getSdt());
            record.add(nv.getLuong());
            
            
            model.addRow(record);
       
        }
        table.setModel(model);
        
        return table;
        
    }
    
    public JTable loadAllKH(JTable table,DefaultTableModel model,ArrayList<KhachHang> khList,String[] name){
        
        model=modelHeader(model,name);

        for(KhachHang kh:khList){
            Vector record=new Vector();

            record.add(kh.getMaKH());
            record.add(kh.getMaTK());
            record.add(kh.getHo());
            record.add(kh.getTen());
            record.add(kh.getNgaySinh());
            record.add(kh.getDiaChi());
            record.add(kh.getSdt());

            model.addRow(record);
       
        }
        table.setModel(model);
        
        return table;
        
    }
    
    public float getTotal(ArrayList<HoaDon> hdList){
        
        float total=0;
        for(HoaDon hd: hdList){
            total+=hd.getTongTien();
        }
        return total;
    }
    
    public float getTT(int id){
        return tblHD.getTT(id);
    }
    
    public int updateHD(HoaDon hd,int id){
        return tblHD.updateHD(hd,id);
    }
    
    public int updateTT(int id,float tt){
        return tblHD.updateTT(id,tt);
    }
    
    public int deleteHD(int id){
        return tblHD.deleteHD(id);
    }
    
    public int insertHD(HoaDon hd){
        return tblHD.insertHD(hd);
    }

    public ArrayList<NhanVien> getALLNV(){
        return tblHD.getALLNV();
    }
    
    public ArrayList<KhachHang> getALLKH(){
        return tblHD.getALLKH();
    }
    
    public double getTongSoTrang(int soLuong){
        hdList=getALLHD();
        return Math.ceil((double)hdList.size()/soLuong);
    }
    
    public int getLastMaHD(){
        return tblHD.getLastMaHD();
    }
    
    public int updateTT(int id,float thOld,float thNew){
        float tt=getTT(id);
        tt-=thOld;
        tt+=thNew;
        int re=tblHD.updateTT(id,tt);
        return re;
    }
    
    public boolean checkEmptyHD(String maKH,String maNV){
        if(maKH.equals("")||maNV.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin", "Lỗi",0);
            return false;
        }
        return true;
    }

}
