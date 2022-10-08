
package application;

import businesslayer.ConfigTable;
import businesslayer.CreateExcelFile;
import businesslayer.MangNhaCungCap;
import businesslayer.NhaCungCap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public class NhaCungCapPanel extends JPanel  implements ActionListener,MouseListener,WindowListener,KeyListener{
        private JPanel header,content2,jpnHead,jpnPage,jpnBT;
        private JLabel heading,number;
        private JButton jbtnSearch,jbtnAdd,jbtnExel,prev,next;
        private JTextField text;
        private JScrollPane jsp;
        private JTable jt;
        private DefaultTableModel model,dtmSr;
        private MangNhaCungCap NCCArr = new MangNhaCungCap();
        private int soLuongRow=20,isSr;
        private InfoNCC addNCC;
        private String[] title=NCCArr.title();
        private ImageIcon iconEdit,iconDelete,iconSr,iconPlus,iconFile;
        private Color mainColor=new Color(0xF5F5F5);
        private ArrayList<NhaCungCap> dsNCC=NCCArr.getNccArr();
        NhaCungCapPanel() {
            setPreferredSize(new Dimension(1050,700));
            setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
            setBackground(mainColor);
            setLayout(new BorderLayout());
            
            iconEdit=new ImageIcon("image/icons8_edit_20px.png");
            iconDelete=new ImageIcon("image/icons8_trash_20px.png");
            iconSr=new ImageIcon("image/icons8_search_20px.png");
            iconPlus=new ImageIcon("image/icons8_plus_20px_2.png");
            iconFile=new ImageIcon("image/icons8_file_20px.png");
            
            jt=new JTable();
            //jt.setPreferredSize(new Dimension(900,100));
            jsp=new JScrollPane(jt);
            jsp.setPreferredSize(new Dimension(1050,522));
            
            model=new DefaultTableModel();
            
            header=new JPanel();
            header.setPreferredSize(new Dimension(1050,100));
            header.setBackground(mainColor);
            header.setLayout(new BorderLayout());
            
            content2=new JPanel();
            content2.setPreferredSize(new Dimension(1050,600));
            content2.setBackground(mainColor);
            content2.setLayout(new BorderLayout());
            
            jpnHead=new JPanel();
            jpnHead.setBackground(mainColor);
            jpnHead.setPreferredSize(new Dimension(1050,60));
            jpnHead.setLayout(new BorderLayout());
            jpnHead.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            
            jpnPage=new JPanel();
            jpnPage.setPreferredSize(new Dimension(1050,78));
            jpnPage.setLayout(new FlowLayout(0,0,10));
            jpnPage.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            jpnPage.setBackground(mainColor);
            
            jpnBT=new JPanel();
            jpnBT.setLayout(new FlowLayout(0,20,0));
            jpnBT.setBackground(mainColor);
            jpnBT.setPreferredSize(new Dimension(400,0));
            
            getTableData(soLuongRow,1);
            //jt.setPreferredScrollableViewportSize(new Dimension(1050,522));
           // jt.setFillsViewportHeight(true);
            //jsp.setViewportView(jt);
            
            heading=new JLabel("Danh sách nhà cung cấp");
            heading.setPreferredSize(new Dimension(1050,40));
            heading.setFont(new Font("Arial",Font.BOLD,18));
            heading.setBackground(mainColor);
            heading.addMouseListener(this);
            heading.setOpaque(true);
            
            number =new JLabel("1");
            number.setPreferredSize(new Dimension(30,30));
            number.setHorizontalAlignment(JLabel.CENTER);
            number.setBackground(Color.blue);
            number.setForeground(Color.white);
            number.setOpaque(true);
            
            text=new JTextField("Tìm kiếm theo tên");
            text.setPreferredSize(new Dimension(200,50));
            text.addMouseListener(this);
            text.addKeyListener(this);
         
            
            jbtnSearch=new JButton("TÌM");
            jbtnSearch.setPreferredSize(new Dimension(100,40));
            jbtnSearch.addActionListener(this);
            jbtnSearch.setActionCommand("timkiem");
            jbtnSearch.setIcon(iconSr);
            jbtnSearch.setBackground(Color.ORANGE);
            jbtnSearch.setForeground(Color.white);
            jbtnSearch.setFont(new Font("Arial",Font.BOLD,14));
            
            jbtnAdd=new JButton("THÊM");
            jbtnAdd.setPreferredSize(new Dimension(100,40));
            jbtnAdd.addActionListener(this);
            jbtnAdd.setActionCommand("them");
            jbtnAdd.setIcon(iconPlus);
            jbtnAdd.setBackground(Color.blue);
            jbtnAdd.setForeground(Color.white);
            jbtnAdd.setFont(new Font("Arial",Font.BOLD,14));
            
            jbtnExel=new JButton("EXCEL");
            jbtnExel.setPreferredSize(new Dimension(110,40));
            jbtnExel.addActionListener(this);
            jbtnExel.setActionCommand("excel");
            jbtnExel.setIcon(iconFile);
            jbtnExel.setBackground(Color.green);
            jbtnExel.setForeground(Color.white);
            jbtnExel.setFont(new Font("Arial",Font.BOLD,14));
            
            prev=new JButton("Trở lại");
            prev.setBackground(Color.white);
            prev.setPreferredSize(new Dimension(80,30));
            prev.addActionListener(this);
            prev.setActionCommand("prev");
            prev.setOpaque(true);
            
            next=new JButton("Kế tiếp");
            next.setBackground(Color.white);
            next.setPreferredSize(new Dimension(80,30));
            next.addActionListener(this);
            next.setActionCommand("next");
            next.setOpaque(true);
            
            
            jpnBT.add(jbtnSearch);
            jpnBT.add(jbtnAdd);
            jpnBT.add(jbtnExel);
            
            
            jpnPage.add(prev);
            jpnPage.add(number);
            jpnPage.add(next);
              
            
            content2.add(jsp,BorderLayout.NORTH);
            content2.add(jpnPage,BorderLayout.CENTER);
            
                    
            jpnHead.add(text,BorderLayout.CENTER);
            jpnHead.add(jpnBT,BorderLayout.EAST);
            
            header.add(heading,BorderLayout.NORTH);
            header.add(jpnHead,BorderLayout.CENTER);
            
            
            add(header,BorderLayout.NORTH);
            add(content2,BorderLayout.CENTER);
        }

    private void getTableData(int soLuongRow, int trang) {
            model=ConfigTable.addTableHeader(title,5); 
           /* ArrayList<NhaCungCap> DSNCC=new ArrayList<NhaCungCap>();
            DSNCC=NCCArr.get(soLuongRow,trang);
            int length=DSNCC.size();
            for (int i=0;i<length;i++) {
                Vector row=new Vector();
                row.add(soLuongRow*(trang-1)+i+1);
                row.add(DSNCC.get(i).getMaNCC());
                row.add(DSNCC.get(i).getTenNCC());
                row.add(DSNCC.get(i).getDiaChi());
                row.add(DSNCC.get(i).getSoDienThoai());
                model.addRow(row);
            }*/
            int length=dsNCC.size();
            int vtBD=(trang-1)*soLuongRow;
            System.out.print(vtBD);
            int conLai=0;
            if (length-vtBD>soLuongRow) {
                conLai=soLuongRow;
            }
            else {
                conLai=length-vtBD;
            }
            
            int vtKT=vtBD+conLai;
            System.out.print(vtKT);
            for (int i=vtBD;i<vtKT;i++) {
                Vector row=new Vector();
                row.add(i+1);
                row.add(dsNCC.get(i).getMaNCC());
                row.add(dsNCC.get(i).getTenNCC());
                row.add(dsNCC.get(i).getDiaChi());
                row.add(dsNCC.get(i).getSoDienThoai());
                model.addRow(row);
            }
            jt.setModel(model);
            retable();
            //jt.getTableHeader().setReorderingAllowed(false); // cam keo cot qua lai
            AcceptRejectRenderer renderer = new AcceptRejectRenderer();
            jt.getColumnModel().getColumn(5).setCellRenderer(renderer);
            jt.getColumnModel().getColumn(5).setCellEditor(new AcceptRejectEditor());
            isSr=0;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
            case "prev":
                if (!number.getText().equals("1")) {
                    int trangTruoc = Integer.parseInt(number.getText()) - 1;
                    getTableData(soLuongRow, trangTruoc);
                    number.setText(Integer.toString(trangTruoc));
                }
                break;
            case "next": {
                //Double trangCuoi = NCCArr.getTongSoTrang(soLuongRow);
                Double trangHienTai = Double.parseDouble(number.getText());
                Double length=dsNCC.size()-trangHienTai*soLuongRow;
                if (/*!trangHienTai.equals(trangCuoi)*/ length>0) {
                    int trangSau = Integer.parseInt(number.getText()) + 1;
                    number.setText(Integer.toString(trangSau));
                    getTableData(soLuongRow, trangSau);
                }
                break;
                }
            case "them" : {
                them();
                break;
            }
            case "timkiem" : {
                timKiem();
                break;
            }
            case "excel": {
                CreateExcelFile.fileNCC(NCCArr.getNccArr());
                break;
            }
         }
    }
    
    public void timKiem() {
         ArrayList<NhaCungCap> NCCSr=new  ArrayList<NhaCungCap>();
         NCCSr=NCCArr.search(text.getText());
         /*NhaCungCap NCCSR=NCCArr.search(Integer.parseInt(text.getText()));
                
                Vector row=new Vector();
                row.add(1);
                row.add(NCCSr.getMaNCC());
                row.add(NCCSr.getTenNCC());
                row.add(NCCSr.getDiaChi());
                row.add(NCCSr.getSoDienThoai());*/
                /*dtmSr=ConfigTable.addTableHeader(title,5); 
                for (NhaCungCap ncc: NCCSr) {
                    Vector row=new Vector();
                    row.add(1);
                    row.add(ncc.getMaNCC());
                    row.add(ncc.getTenNCC());
                    row.add(ncc.getDiaChi());
                    row.add(ncc.getSoDienThoai());
                    dtmSr.addRow(row);
                }
                jt.setModel(dtmSr);
                retable();
                AcceptRejectRenderer renderer = new AcceptRejectRenderer();
                jt.getColumnModel().getColumn(5).setCellRenderer(renderer);
                jt.getColumnModel().getColumn(5).setCellEditor(new AcceptRejectEditor());
                isSr=1;*/
                dsNCC=NCCSr;
                getTableData(soLuongRow,1);
    }
    
    public void them() {
        addNCC=new InfoNCC();
        addNCC.add(); 
        addNCC.addWindowListener(this);
        addNCC.setVisible(true);      
    }
    
    public void sua() {
        addNCC=new InfoNCC(); 
        int x=jt.getSelectedRow();
        addNCC.edit(Integer.parseInt(jt.getModel().getValueAt(x,1).toString()),jt.getModel().getValueAt(x,2).toString(),
                     jt.getModel().getValueAt(x,3).toString(),jt.getModel().getValueAt(x,4).toString());
        addNCC.addWindowListener(this);
        addNCC.setVisible(true);
    }
    
    public void xoa() {
        int result=JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa!", "Chú ý",JOptionPane.OK_CANCEL_OPTION);
        if (result==0) {
            int x=jt.getSelectedRow();
            NCCArr.remove(Integer.parseInt(jt.getModel().getValueAt(x,1).toString()));
            int trang=Integer.parseInt(number.getText());
            NCCArr=new MangNhaCungCap();
            dsNCC=NCCArr.getNccArr();
            getTableData(soLuongRow, trang);    
        }
    }
    
    public void retable() {
        int[] colsGetCenter = { 0, 1};
        String[] setWidthOptions = { "p-10", "p-30", "mi-250", "mi-250", "p-60", "p-100"};
        ConfigTable.decorateTable(jt, colsGetCenter, title.length, setWidthOptions);
    }
    
   
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==text) {
            text.setText("");
        }
        if (e.getSource()==heading) {
            dsNCC=NCCArr.getNccArr();
            getTableData(soLuongRow,1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
            if (e.getSource()==addNCC && addNCC.getAlter()==true) {
                    if (isSr==0) {
                        int trang=Integer.parseInt(number.getText());
                        NCCArr=new MangNhaCungCap();
                        dsNCC=NCCArr.getNccArr();
                        getTableData(soLuongRow, trang);   
                    }
                    else {
                        timKiem();
                    }
            }
    }

    @Override
    public void windowIconified(WindowEvent e) {
      
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
       
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
      
    }

    @Override
    public void keyTyped(KeyEvent e) {
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==text) {
           timKiem();  
        }
    }
     


    public class AcceptRejectPane extends JPanel {

        private JButton jbtSua;
        private JButton jbtXoa;
        private String state;

        public AcceptRejectPane() {
            setLayout(new GridBagLayout());
            
            jbtSua = new JButton();
            jbtSua.setIcon(iconEdit);
            jbtSua.setActionCommand("sua");
            jbtSua.setBackground(Color.green);
            jbtXoa = new JButton();
            jbtXoa.setIcon(iconDelete);
            jbtXoa.setActionCommand("xoa");
            jbtXoa.setBackground(Color.red);
            
            GridBagConstraints gbc=new GridBagConstraints();
            gbc.insets=new Insets(0,10,0,0);

            
            add(jbtSua,gbc);
            add(jbtXoa,gbc);
            
            
            

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (e.getActionCommand()) {
                        case "sua": {
                                sua();
                                break;
                        }
                        case "xoa": {
                            xoa();
                            break;
                        }
                    }
                }
            };

            jbtSua.addActionListener(listener);
            jbtXoa.addActionListener(listener);
        }

        public void addActionListener(ActionListener listener) {
            jbtSua.addActionListener(listener);
            jbtXoa.addActionListener(listener);
        }

        public String getState() {
            return state;
        }
    }

    public class AcceptRejectRenderer extends DefaultTableCellRenderer {

        private AcceptRejectPane acceptRejectPane;

        public AcceptRejectRenderer() {
            acceptRejectPane = new AcceptRejectPane();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                acceptRejectPane.setBackground(table.getSelectionBackground());
            } else {
                acceptRejectPane.setBackground(table.getBackground());
            }
            return acceptRejectPane;
        }
    }

    public class AcceptRejectEditor extends AbstractCellEditor implements TableCellEditor {

        private AcceptRejectPane acceptRejectPane;

        public AcceptRejectEditor() {
            acceptRejectPane = new AcceptRejectPane();
            acceptRejectPane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            stopCellEditing();
                        }
                    });
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return acceptRejectPane.getState();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                acceptRejectPane.setBackground(table.getSelectionBackground());
            } else {
                acceptRejectPane.setBackground(table.getBackground());
            }
            return acceptRejectPane;
        }
    }
}
