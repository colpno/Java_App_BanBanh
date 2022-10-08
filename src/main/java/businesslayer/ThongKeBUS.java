
package businesslayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThongKeBUS {
    
    private MangHoaDon hdArr=new MangHoaDon();
    private MangCTHD cthdArr=new MangCTHD();
    private MangSanPham spArr=new MangSanPham();
    
    private int namBD=2019;
    
    public ThongKeBUS(){
        
    }
    
    
    
    public JTable thongKe(JTable table,DefaultTableModel model,String[] name,int year){
        
        model=spArr.modelHeader(model, name);
        
        ArrayList<ChiTietHoaDon> cthdList=new ArrayList<ChiTietHoaDon>();
        
        for(SanPham sp: spArr.getAllSP()){
            
            Vector record=new Vector();
            
            record.add(sp.getTenSP());
            
            for(int i=1;i<=12;i++){
                
                float tongTienSP=0;
                
                for(HoaDon hd: hdArr.getALLHD()){
                    
                    String[] datePath=hd.getNgayLapHoaDon().split("/");
                    int hdMonth=Integer.parseInt(datePath[1]);
                    int hdYear=Integer.parseInt(datePath[2]);
                    
                    if(hdMonth==i&&hdYear==year){
                        
                        cthdList=cthdArr.getALLCTHD(hd.getMaHD());
                        
                        for(ChiTietHoaDon cthd: cthdList){
                            
                            if(cthd.getMaSP()==sp.getMaSP()){
                                tongTienSP+=cthd.getThanhTien();
                                
                            }
                            
                        }
                    }
                }
                record.add(tongTienSP);
            }
            
            
            float tongCong=0;
            for(int i=1;i<record.size();i++)
                tongCong+=Float.parseFloat(record.get(i).toString());
            record.add(tongCong);
            
            model.addRow(record);
        }
        
        Vector tong=new Vector();

        tong.add("Tổng Cộng");

        for(int i=1;i<model.getColumnCount();i++){
            
            float tongTien=0;
            for(int j=0;j<model.getRowCount();j++){

                tongTien+=Float.parseFloat(model.getValueAt(j,i).toString());
            }

            tong.add(tongTien);
        }
        
        model.addRow(tong);
            
        table.setModel(model);
        
        return table;
    }
    public Vector getYear(){
        Vector yearList=new Vector();
        for(int year=namBD;year<=Calendar.getInstance().get(Calendar.YEAR);year++){
            yearList.add(""+year);
        }
        return yearList;
    }
}
