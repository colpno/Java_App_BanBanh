
package businesslayer;

import datalayer.tblLoai;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MangLoai {
    private tblLoai tblLoai=new tblLoai();
    private ArrayList<Loai> loaiList=new ArrayList<>();

    public ArrayList<Loai> getAllLoai(){
        return tblLoai.getAllLoai();
    }
    
    public Loai getLoaiByID(int id){
        ArrayList<Loai> loaiList=getAllLoai();
        Loai kq=new Loai();
        for(Loai l: loaiList){
            if(l.getMaLoai()==id)
               kq=l;
        }
        return kq;
    }
    
    
    public ArrayList<Loai> getLoaiByTen(String ten){
        ArrayList<Loai> kq=new ArrayList<Loai>();
        loaiList=getAllLoai();
        String s1=ten.toLowerCase();
        for(Loai l: loaiList){
            String s2=l.getTenLoai().toLowerCase();
            if(s2.indexOf(s1)>=0)
                kq.add(l);
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
    public JTable loadAllLoai(JTable table,DefaultTableModel model,ArrayList<Loai> loaiList,String[] name){
        
        model=modelHeader(model,name);

        for(Loai l:loaiList){
            Vector record=new Vector();

            record.add(l.getMaLoai());
            record.add(l.getTenLoai());
            
            model.addRow(record);
        }
        table.setModel(model);
        
        
        return table;
    }

    public int updateLoai(Loai l,int id){
        return tblLoai.updateLoai(l,id);
    }
    
    public int deleteLoai(int id){
        return tblLoai.deleteLoai(id);
    }
    public int insertLoai(Loai l){
        return tblLoai.insertLoai(l);
    }
    public int getLastMaLoai(){
        return tblLoai.getLastMaLoai();
    }
    public boolean ktLoai(String name){
        loaiList=getAllLoai();
        for(Loai l: loaiList){
            if(l.getTenLoai().toLowerCase().equals(name.toLowerCase()))
                return false;
        }
        return true;
    }
}

