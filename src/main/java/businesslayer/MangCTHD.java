
package businesslayer;

import datalayer.tblCTHD;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MangCTHD {
    private tblCTHD tblCTHD=new tblCTHD();
    public ArrayList<ChiTietHoaDon> cthdList=new ArrayList<ChiTietHoaDon>();
    
    public MangCTHD(){
        
    }
    
    
    public ArrayList<ChiTietHoaDon> getALLCTHD(int ma){
        return tblCTHD.getALLCTHD(ma);
    }
    
    public int updateCTHD(ChiTietHoaDon cthd,int idHD,int idSP){
        return tblCTHD.updateCTHD(cthd,idHD,idSP);
    }
    
    public int deleteCTHD(int idHD,int idSP){
        return tblCTHD.deleteCTHD(idHD,idSP);
    }
    
    public int deleteCTHD(int idHD){
        return tblCTHD.deleteCTHD(idHD);
    }
    
    public int insertCTHD(ChiTietHoaDon cthd){
        return tblCTHD.insertCTHD(cthd);
    }


    public ArrayList<ChiTietHoaDon> getCTHDByTen(int id,String ten){
        
        MangSanPham spArr=new MangSanPham();
        
        cthdList=getALLCTHD(id);

        ArrayList<ChiTietHoaDon> kq=new ArrayList<ChiTietHoaDon>();
        
        for(ChiTietHoaDon cthd: cthdList){
            String s=spArr.getSPByID(cthd.getMaSP()).getTenSP().toLowerCase();
            if(s.indexOf(ten.toLowerCase())>=0)
                kq.add(cthd);
        }
        
        return kq;
    
    }
    
    public DefaultTableModel modelHeader(DefaultTableModel model,String[] name){
        Vector header=new Vector();
        for(String s: name){
            header.add(s);
        }
        model=new DefaultTableModel(header,0);
        return model;
    }
    
    public JTable loadAllCTHD(JTable table,DefaultTableModel model,ArrayList<ChiTietHoaDon> cthdList,String[] name){
        model=modelHeader(model,name);
        MangSanPham spArr=new MangSanPham();
        
        ArrayList<SanPham> spList=spArr.getAllSP();
        
        for(ChiTietHoaDon cthd:cthdList){
            
            Vector record=new Vector();
            int maSP=cthd.getMaSP();
            record.add(false);
            record.add(cthd.getMaHD());
            record.add(maSP);
            record.add(spArr.getSPByID(maSP).getTenSP());
            record.add(cthd.getSoLuong());
            record.add(cthd.getDonGia());
            record.add(cthd.getThanhTien());
            
            model.addRow(record);
       
        }
        table.setModel(model);
        
        return table;
        
    }
    
    public JTable loadAllSP(JTable table,DefaultTableModel model,ArrayList<SanPham> spList,String[] name){
        
        model=modelHeader(model,name);

        MangSanPham spArr=new MangSanPham();
        
        for(SanPham sp:spList){
            Vector record=new Vector();

            float ptkm=spArr.getPTKM(sp.getMaSP());
            float donGia=sp.getDonGia();
            
            record.add(sp.getMaSP());
            record.add(sp.getTenSP());
            record.add(sp.getMaLoai());
            record.add(sp.getMaNSX());
            record.add(sp.getSoLuong());
            record.add(donGia);
            record.add(ptkm);
            record.add(spArr.getGiaKM(donGia, ptkm));
            record.add(sp.getDonViTinh());
            
            model.addRow(record);
       
        }
        table.setModel(model);
        
        return table;
        
    }

    
    public double getTongSoTrang(int soLuong){
        
        return Math.ceil((double)cthdList.size()/soLuong);
    }
    
    public int getSL(int maSP){
        return tblCTHD.getSL(maSP);
    }
    
    public int updateQty(int ma,int qtyOld,int qtyNew){
        int qty=getSL(ma);
        qty+=qtyOld;
        qty-=qtyNew;
        int re=tblCTHD.updateQty(ma,qty);
        return re;
    }
    public ArrayList<ChiTietHoaDon> KTCTHD(ArrayList<ChiTietHoaDon> cthdList,ChiTietHoaDon cthd){
        int flag=0;
        for(ChiTietHoaDon ct: cthdList)
            if(ct.getMaSP()==cthd.getMaSP()){
                ct.setSoLuong(ct.getSoLuong()+cthd.getSoLuong());
                ct.setThanhTien(ct.getSoLuong()*ct.getDonGia());
                flag=1;
                break;
            }
        if(flag==0){
            cthdList.add(cthd);
        }
        return cthdList;
    }
    public ChiTietHoaDon KTCTHD(int maHD,int maSpOld,ChiTietHoaDon cthd){
        ChiTietHoaDon kq=null;

        ArrayList<ChiTietHoaDon> cthdList=getALLCTHD(maHD);
        for(ChiTietHoaDon ct: cthdList){
            if(ct.getMaSP()==cthd.getMaSP()&&(cthd.getMaSP()!=maSpOld||maSpOld==0)){
                kq=ct;
                break;
            }
        }
        
        return kq;
            
    }
    public int updateQtyCT(int maHD,int maSP,int qty){
        return tblCTHD.updateQty(maHD, maSP, qty);
    }
    
    public float updateTht(int maHD,int maSP,float total){
        return tblCTHD.updateThT(maHD, maSP,total);
    }
    
    public boolean checkEmptyCTHD(String maSP){
        if(maSP.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin", "Lỗi",0);
            return false;
        }
        return true;
    }
    public boolean checkQty(int bQty,int eQty){
        if(eQty>bQty){
            JOptionPane.showMessageDialog(null,"So luong san pham khong hop le", "Lỗi",0);
            return false;
        }
        return true;
    }
}
