
package application;

import businesslayer.ConfigTable;
import businesslayer.CreateExcelFile;
import businesslayer.ChiTietKhuyenMai;
import businesslayer.KhuyenMai;
import businesslayer.MangChiTietKhuyenMai;
import businesslayer.MangKhuyenMai;
import businesslayer.SanPham;
import businesslayer.SanPhamBUS;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import test.InputTester;



public class KhuyenMaiPanel extends JPanel implements ActionListener,MouseListener,WindowListener,KeyListener {
        private JPanel contentKM,contentInfo,headerKM,container,jpnTB1,jpnTB2,jpnPage1,jpnPage2,jpnHead,jpnBT,jpnDay,
                headerInfo,jpnKM,jpnTT,jpnMa,jpnTen,jpnNBD,jpnNKT,jpnTB,jpnBT1,jpnBT2,jpnCB,jpnTBM,jpnClick,jpnS,jpnBC,jpnG,jpnPT;
        private JLabel heading,number1,number2,day1,day2,heading1,heading2,heading3,jlbMa,jlbTen,jlbNBD,jlbNKT,jlbHT,jlbCSP,jlbCKM,jlbPT;
        private ImageIcon iconEdit,iconDelete,iconSr,iconPlus,iconFile;
        private JButton jbtnSearch,jbtnAdd,jbtnExel,prev1,prev2,next1,next2,jbtnDay,add,comeback,addKM,save,jbtnClick,jbtnClose,
                jbtnCSP,jbtnCKM;
        private JTextField text,jtMa,jtTen,jtSP,jtKM,jtPT;
        private JDateChooser textD1,textD2,jtNBD,jtNKT;
        private JScrollPane jsp1,jsp2,jsp3,jsp4;
        private JTable jt1,jt2,jt3,jt4,jt5;
        private DefaultTableModel model1,model2,model3,dtmSr1,dtmSr2,modelSP,modelKM;
        private MangKhuyenMai KMArr=new MangKhuyenMai();
        private MangChiTietKhuyenMai CTKMArr=new MangChiTietKhuyenMai();
        private SanPhamBUS spArr=new SanPhamBUS();
        private JComboBox jcb;
        private String[] title1=KMArr.title(),title2=CTKMArr.title(),title3=spArr.titleH(),ht={"Online","Mua trực tiếp"},title4=KMArr.titleC(),
                title5=spArr.titleC();
        private int isSr1,isSr2,soLuongRow=20;
        private InfoKM jfchon;
        private ArrayList<KhuyenMai> dsKM=KMArr.getKmArr();
        private ArrayList<ChiTietKhuyenMai> dsCTKM=CTKMArr.getCtkmArr(),dsSPT,dsSPSr;
        private ArrayList<SanPham> dsSP=spArr.getSpArr();
        private CardLayout card;
        private JFrame info;
        private String m="abc",tenSP,tenKM,ngayBD,ngayKT,maSP="",maLoai,maNSX,donGia,phanTram,maKM="";
        private Color mainColor=new Color(0xF5F5F5);
        KhuyenMaiPanel() {   
            iconEdit=new ImageIcon("image/icons8_edit_20px.png");
            iconDelete=new ImageIcon("image/icons8_trash_20px.png");
            iconSr=new ImageIcon("image/icons8_search_20px.png");
            iconPlus=new ImageIcon("image/icons8_plus_20px_2.png");
            iconFile=new ImageIcon("image/icons8_file_20px.png");
            
            dsSPT=new ArrayList<ChiTietKhuyenMai>();
            dsSPSr=new ArrayList<ChiTietKhuyenMai>();
            
            contentKM=new JPanel();
            contentKM.setPreferredSize(new Dimension(1050,700));
            contentKM.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
            contentKM.setBackground(mainColor);
            contentKM.setLayout(new BorderLayout());
            
            headerKM =new JPanel();
            headerKM.setPreferredSize(new Dimension(1050,100));
            headerKM.setLayout(new BorderLayout());
            headerKM.setBackground(mainColor);
            
            
            container=new JPanel();
            container.setPreferredSize(new Dimension(1050,600));
            container.setLayout(new BorderLayout());
            container.setBackground(mainColor);
            
            
            jpnHead=new JPanel();
            jpnHead.setPreferredSize(new Dimension(525,60));
            jpnHead.setLayout(new BorderLayout());
            jpnHead.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            jpnHead.setBackground(mainColor);
            
            
            jpnBT=new JPanel();
            jpnBT.setPreferredSize(new Dimension(300,60));
            jpnBT.setLayout(new FlowLayout(0,10,0));
            
            jpnDay=new JPanel();
            jpnDay.setPreferredSize(new Dimension(400,60));
            jpnDay.setLayout(new FlowLayout(0,10,0));
            jpnDay.setBackground(mainColor);
            
            
            jpnTB1=new JPanel();
            jpnTB1.setPreferredSize(new Dimension(600,540));
            jpnTB1.setLayout(new BorderLayout());
            jpnTB1.setBackground(mainColor);
            
            jpnTB2=new JPanel();
            jpnTB2.setPreferredSize(new Dimension(450,540));
            jpnTB2.setLayout(new BorderLayout());
            jpnTB2.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jpnTB2.setBackground(mainColor);
            
            jpnPage1=new JPanel();
            jpnPage1.setPreferredSize(new Dimension(525,50));
            jpnPage1.setLayout(new FlowLayout(0,0,0));
            jpnPage1.setBorder(BorderFactory.createEmptyBorder(10,20,0,0));
            jpnPage1.setBackground(mainColor);
            
            jpnPage2=new JPanel();
            jpnPage2.setPreferredSize(new Dimension(525,50));
            jpnPage2.setLayout(new FlowLayout(0,0,0));
            jpnPage2.setBorder(BorderFactory.createEmptyBorder(10,20,0,0));
            jpnPage2.setBackground(mainColor);
            
            contentInfo=new JPanel();
            contentInfo.setPreferredSize(new Dimension(1050,700));
            contentInfo.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
            contentInfo.setBackground(mainColor);
            contentInfo.setLayout(new BorderLayout());
            
            headerInfo=new JPanel();
            headerInfo.setPreferredSize(new Dimension(1050,50));
            headerInfo.setLayout(new BorderLayout());
            headerInfo.setBackground(new Color(0xfc498e));
            
            jpnKM=new JPanel();
            jpnKM.setPreferredSize(new Dimension(1050,50));
            jpnKM.setLayout(new BorderLayout());
            
            jpnTT=new JPanel();
            jpnTT.setPreferredSize(new Dimension(1050,200));
            jpnTT.setLayout(new FlowLayout(0,20,20));
            jpnTT.setBackground(mainColor);
            
            jpnTB=new JPanel();
            jpnTB.setPreferredSize(new Dimension(1050,450));
            jpnTB.setLayout(new BorderLayout());
            jpnTB.setBackground(mainColor);
            
            jpnMa=new JPanel();
            jpnMa.setPreferredSize(new Dimension(400,50));
            jpnMa.setBackground(mainColor);
            jpnMa.setLayout(new FlowLayout(0,0,0));
            jpnMa.setOpaque(true);
            
            
            jpnTen=new JPanel();
            jpnTen.setPreferredSize(new Dimension(400,50));
            jpnTen.setBackground(mainColor);
            jpnTen.setLayout(new FlowLayout(0,0,0));
            jpnTen.setOpaque(true);
            
            jpnNBD=new JPanel();
            jpnNBD.setPreferredSize(new Dimension(400,50));
            jpnNBD.setBackground(mainColor);
            jpnNBD.setLayout(new FlowLayout(0,0,0));
            jpnNBD.setOpaque(true);
            
            jpnNKT=new JPanel();
            jpnNKT.setPreferredSize(new Dimension(400,50));
            jpnNKT.setBackground(mainColor);
            jpnNKT.setLayout(new FlowLayout(0,0,0));
            jpnNKT.setOpaque(true);
            
            jpnBT1=new JPanel();
            jpnBT1.setPreferredSize(new Dimension(1050,50));
            jpnBT1.setLayout(new FlowLayout(1,0,0));
            jpnBT1.setBackground(mainColor);
            
            jpnBT2=new JPanel();
            jpnBT2.setPreferredSize(new Dimension(1050,100));
            jpnBT2.setLayout(new FlowLayout(2,20,10));
            jpnBT2.setBackground(mainColor);
            
            jpnCB=new JPanel();
            jpnCB.setPreferredSize(new Dimension(500,50));
            jpnCB.setLayout(new FlowLayout(0,0,0));
            jpnCB.setBackground(mainColor);
            
            jpnTBM=new JPanel();
            jpnTBM.setPreferredSize(new Dimension(1050,420));
            jpnTBM.setLayout(new BorderLayout());
            jpnTBM.setBackground(mainColor);
            
            jpnClick=new JPanel();
            jpnClick.setPreferredSize(new Dimension(1050,50));
            jpnClick.setLayout(new FlowLayout(0,10,0));
            jpnClick.setBackground(mainColor);
            
            jpnS=new JPanel();
            jpnS.setPreferredSize(new Dimension(900,100));
            jpnS.setLayout(new BorderLayout());
            jpnS.setBackground(new Color(0xf6f6f6));
            jpnS.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
            
            jpnBC=new JPanel();
            jpnBC.setPreferredSize(new Dimension(900,50));
            jpnBC.setLayout(new FlowLayout(2,20,5));
            jpnBC.setBackground(new Color(0xf6f6f6));
            jpnBC.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
            
            jpnG=new JPanel();
            jpnG.setPreferredSize(new Dimension(1050,250));
            jpnG.setLayout(new BorderLayout());
            jpnG.setBackground(mainColor);
            
            jpnPT=new JPanel();
            jpnPT.setPreferredSize(new Dimension(200,50));
            jpnPT.setLayout(new FlowLayout(0,10,0));
            jpnPT.setBackground(new Color(0xf6f6f6));
            
            heading=new JLabel("Danh sách khuyến mãi");
            heading.setPreferredSize(new Dimension(1050,40));
            heading.setFont(new Font("Arial",Font.BOLD,18));
            heading.setBackground(mainColor);
            heading.addMouseListener(this);
            heading.setOpaque(true);
            
            number1=new JLabel("1");
            number1.setPreferredSize(new Dimension(30,30));
            number1.setHorizontalAlignment(JLabel.CENTER);
            number1.setBackground(Color.blue);
            number1.setForeground(Color.white);
            number1.setOpaque(true);
            
            number2=new JLabel("1");
            number2.setPreferredSize(new Dimension(30,30));
            number2.setHorizontalAlignment(JLabel.CENTER);
            number2.setBackground(Color.blue);
            number2.setForeground(Color.white);
            number2.setOpaque(true);
            
            day1=new JLabel("Từ ngày");
            day1.setPreferredSize(new Dimension(50,40));
            day1.setHorizontalAlignment(JLabel.CENTER);
            day1.setVerticalAlignment(JLabel.CENTER);
            
            day2=new JLabel("đến");
            day2.setPreferredSize(new Dimension(30,40));
            day2.setHorizontalAlignment(JLabel.CENTER);
            day2.setVerticalAlignment(JLabel.CENTER);
            
            heading1=new JLabel("Thêm khuyến mãi");
            heading1.setPreferredSize(new Dimension(525,50));
            heading1.setBackground(new Color(0xfc498e));
            heading1.setForeground(Color.white);
            heading1.setFont(new Font("Arial",Font.BOLD,20));
            heading1.setHorizontalAlignment(JLabel.CENTER);
            heading1.setVerticalAlignment(JLabel.CENTER);
            heading1.addMouseListener(this);
            heading1.setOpaque(true);
            
            heading2=new JLabel("Thay đổi thông tin khuyến mãi");
            heading2.setPreferredSize(new Dimension(525,50));
            heading2.setBackground(mainColor);
            heading2.setFont(new Font("Arial",Font.BOLD,20));
            heading2.setHorizontalAlignment(JLabel.CENTER);
            heading2.setVerticalAlignment(JLabel.CENTER);
            heading2.addMouseListener(this);
            heading2.setOpaque(true);
            
            heading3=new JLabel("Thông tin khuyến mãi");
            heading3.setPreferredSize(new Dimension(200,50));
            heading3.setBackground(mainColor);
            heading3.setFont(new Font("Arial",Font.BOLD,16));
            heading3.setHorizontalAlignment(JLabel.LEFT);
            heading3.setVerticalAlignment(JLabel.CENTER);
            heading3.setOpaque(true);
            
            jlbMa=new JLabel("Mã khuyến mãi");
            jlbMa.setPreferredSize(new Dimension(150,50));
            jlbMa.setHorizontalAlignment(JLabel.LEFT);
            jlbMa.setVerticalAlignment(JLabel.CENTER);

            jlbTen=new JLabel("Tên khuyến mãi");
            jlbTen.setPreferredSize(new Dimension(150,50));
            jlbTen.setHorizontalAlignment(JLabel.LEFT);
            jlbTen.setVerticalAlignment(JLabel.CENTER);

            
            jlbNBD=new JLabel("Ngày bắt đầu");
            jlbNBD.setPreferredSize(new Dimension(150,50));
            jlbNBD.setHorizontalAlignment(JLabel.LEFT);
            jlbNBD.setVerticalAlignment(JLabel.CENTER);
            
            jlbNKT=new JLabel("Ngày kết thúc");
            jlbNKT.setPreferredSize(new Dimension(150,50));
            jlbNKT.setHorizontalAlignment(JLabel.LEFT);
            jlbNKT.setVerticalAlignment(JLabel.CENTER);

            
            jlbHT=new JLabel("Hình thức");
            jlbHT.setPreferredSize(new Dimension(100,50));
            jlbHT.setHorizontalAlignment(JLabel.LEFT);
            jlbHT.setVerticalAlignment(JLabel.CENTER);
            
            
            jlbCSP=new JLabel();
            jlbCSP.setPreferredSize(new Dimension(900,50));
            jlbCSP.setHorizontalAlignment(JLabel.CENTER);
            jlbCSP.setVerticalAlignment(JLabel.CENTER);
            jlbCSP.setText("Thông tin sản phẩm");
            jlbCSP.setFont(new Font("Arial",Font.BOLD,20));
            jlbCSP.setBackground(new Color(0xfc498e));
            jlbCSP.setForeground(Color.white);
            jlbCSP.setOpaque(true);
            jlbCSP.addMouseListener(this);
            
            jlbCKM=new JLabel();
            jlbCKM.setPreferredSize(new Dimension(900,50));
            jlbCKM.setHorizontalAlignment(JLabel.CENTER);
            jlbCKM.setVerticalAlignment(JLabel.CENTER);
            jlbCKM.setText("Thông tin khuyến mãi");
            jlbCKM.setFont(new Font("Arial",Font.BOLD,20));
            jlbCKM.setBackground(new Color(0xfc498e));
            jlbCKM.setForeground(Color.white);
            jlbCKM.setOpaque(true);
            jlbCKM.addMouseListener(this);
            
            jlbPT=new JLabel("Phần trăm");
            jlbPT.setPreferredSize(new Dimension(60,40));
            jlbPT.setBackground(new Color(0xf6f6f6));
             
            jbtnSearch=new JButton("Tìm");
            jbtnSearch.setPreferredSize(new Dimension(80,40));
            jbtnSearch.addActionListener(this);
            jbtnSearch.setActionCommand("timkiem");
            jbtnSearch.setIcon(iconSr);
            jbtnSearch.setBackground(Color.ORANGE);
            jbtnSearch.setForeground(Color.white);
            jbtnSearch.setFont(new Font("Arial",Font.BOLD,10));
            
            jbtnAdd=new JButton("Thêm");
            jbtnAdd.setPreferredSize(new Dimension(90,40));
            jbtnAdd.addActionListener(this);
            jbtnAdd.setActionCommand("them");
            jbtnAdd.setIcon(iconPlus);
            jbtnAdd.setBackground(Color.blue);
            jbtnAdd.setForeground(Color.white);
            jbtnAdd.setFont(new Font("Arial",Font.BOLD,10));
            
            jbtnExel=new JButton("Excel");
            jbtnExel.setPreferredSize(new Dimension(100,40));
            jbtnExel.addActionListener(this);
            jbtnExel.setActionCommand("excel");
            jbtnExel.setIcon(iconFile);
            jbtnExel.setBackground(Color.green);
            jbtnExel.setForeground(Color.white);
            jbtnExel.setFont(new Font("Arial",Font.BOLD,10));
            
            
            prev1=new JButton("Trở lại");
            prev1.setBackground(Color.white);
            prev1.setPreferredSize(new Dimension(80,30));
            prev1.addActionListener(this);
            prev1.setActionCommand("prev1");
            prev1.setOpaque(true);
            
            next1=new JButton("Kế tiếp");
            next1.setBackground(Color.white);
            next1.setPreferredSize(new Dimension(80,30));
            next1.addActionListener(this);
            next1.setActionCommand("next1");
            next1.setOpaque(true);
            
            prev2=new JButton("Trở lại");
            prev2.setBackground(Color.white);
            prev2.setPreferredSize(new Dimension(80,30));
            prev2.addActionListener(this);
            prev2.setActionCommand("prev2");
            prev2.setOpaque(true);
            
            next2=new JButton("Kế tiếp");
            next2.setBackground(Color.white);
            next2.setPreferredSize(new Dimension(80,30));
            next2.addActionListener(this);
            next2.setActionCommand("next2");
            next2.setOpaque(true);
            
            jbtnDay=new JButton(">");
            jbtnDay.setPreferredSize(new Dimension(50,40));
            jbtnDay.setActionCommand("loc");
            jbtnDay.addActionListener(this);
            
            add=new JButton("Thêm sản phẩm khuyến mãi");
            add.setPreferredSize(new Dimension(300,40));
            add.addActionListener(this);
            add.setActionCommand("themspkm");
            add.setBackground(Color.white);
            add.setForeground(new Color(0xfc498e));
            
            comeback=new JButton("Quay lại");
            comeback.setPreferredSize(new Dimension(100,40));
            comeback.addActionListener(this);
            comeback.setActionCommand("comeback");
            comeback.setBackground(Color.white);
            
            save=new JButton("Lưu lại");
            save.setPreferredSize(new Dimension(100,40));
            save.addActionListener(this);
            save.setActionCommand("luukm");
            save.setBackground(Color.green);
            save.setForeground(Color.white);
            
            addKM=new JButton("Thêm");
            addKM.setPreferredSize(new Dimension(100,40));
            addKM.addActionListener(this);
            addKM.setActionCommand("themkm");
            addKM.setBackground(Color.blue);
            addKM.setForeground(Color.white);
            
            jbtnClick=new JButton("Chọn");
            jbtnClick.setPreferredSize(new Dimension(80,40));
            jbtnClick.addActionListener(this);
            jbtnClick.setBackground(Color.white);
            jbtnClick.setActionCommand("hienkm");
            
            jbtnCSP=new JButton("Chọn");
            jbtnCSP.setPreferredSize(new Dimension(80,40));
            jbtnCSP.addActionListener(this);
            jbtnCSP.setActionCommand("chonsp");
            jbtnCSP.setBackground(Color.orange);
            jbtnCSP.setForeground(Color.white);
            
            jbtnCKM=new JButton("Chọn");
            jbtnCKM.setPreferredSize(new Dimension(80,40));
            jbtnCKM.addActionListener(this);
            jbtnCKM.setActionCommand("chonkm");
            jbtnCKM.setBackground(Color.orange);
            jbtnCKM.setForeground(Color.white);
            
            jbtnClose=new JButton("Đóng ");
            jbtnClose.setPreferredSize(new Dimension(80,40));
            jbtnClose.addActionListener(this);
            jbtnClose.setActionCommand("dong");
            jbtnClose.setBackground(Color.white);
            
            jcb=new JComboBox(ht);
            jcb.setPreferredSize(new Dimension(150,50));
            
            text=new JTextField("Tìm kiếm theo tên khuyến mãi");
            text.setPreferredSize(new Dimension(225,50));
            text.addMouseListener(this);
            text.addKeyListener(this);
           
            jtMa=new JTextField("Mã tự động tạo");
            jtMa.setPreferredSize(new Dimension(250,50));
            jtMa.setEditable(false);
            
            jtTen=new JTextField();
            jtTen.setPreferredSize(new Dimension(250,50));
                        
            jtSP=new JTextField("Tìm kiếm theo tên sản phẩm");
            jtSP.setPreferredSize(new Dimension(900,50));
            jtSP.addMouseListener(this);
            jtSP.addKeyListener(this);
            
            jtKM=new JTextField("Tìm kiếm theo tên khuyến mãi");
            jtKM.setPreferredSize(new Dimension(900,50));
            jtKM.addMouseListener(this);
            jtKM.addKeyListener(this);
            
            jtPT=new JTextField();
            jtPT.setPreferredSize(new Dimension(100,50));
            
            textD1=new JDateChooser();
            textD1.setPreferredSize(new Dimension(100,40));
            textD1.setDateFormatString("yyyy-MM-dd");
            
            textD2=new JDateChooser();
            textD2.setPreferredSize(new Dimension(100,40));
            textD2.setDateFormatString("yyyy-MM-dd");
            
            jtNBD=new JDateChooser();
            jtNBD.setPreferredSize(new Dimension(250,50));
            jtNBD.setDateFormatString("yyyy-MM-dd");
            
            jtNKT=new JDateChooser();
            jtNKT.setPreferredSize(new Dimension(250,50));
            jtNKT.setDateFormatString("yyyy-MM-dd");
            
            jt1=new JTable();
            //jt1.setPreferredSize(new Dimension(1050,522));
            jt1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jt1.addMouseListener(this);
            
            jt2=new JTable();
            jt2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            jt3=new JTable();
            jt3.addMouseListener(this);
            
            jt4=new JTable();
            jt4.addMouseListener(this);
            
            jt5=new JTable();
            jt5.addMouseListener(this);
            
            jsp1=new JScrollPane(jt1,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jsp1.setPreferredSize(new Dimension(0,537));
            
            jsp2=new JScrollPane(jt2,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jsp2.setPreferredSize(new Dimension(0,537));
            
            jsp3=new JScrollPane(jt3);
            jsp3.setPreferredSize(new Dimension(1050,300));
            
            jsp4=new JScrollPane();
            jsp4.setPreferredSize(new Dimension(900,300));
            jsp4.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
            jsp4.setBackground(new Color(0xf6f6f6));
            
            model1=new DefaultTableModel();
            
            model2=new DefaultTableModel();
            
            model3=new DefaultTableModel();
            
            modelSP=new DefaultTableModel();
            
            modelKM=new DefaultTableModel();
            
            getTableData1(soLuongRow,1);
            getTableData2(soLuongRow,1);
            
            jpnBT.add(jbtnSearch);
            jpnBT.add(jbtnAdd);
            jpnBT.add(jbtnExel);
            
            jpnPage1.add(prev1);
            jpnPage1.add(number1);
            jpnPage1.add(next1);
            
            
            jpnTB1.add(jsp1,BorderLayout.NORTH);
            jpnTB1.add(jpnPage1,BorderLayout.CENTER);

            
            jpnPage2.add(prev2);
            jpnPage2.add(number2);
            jpnPage2.add(next2);
            
            
            jpnTB2.add(jsp2,BorderLayout.NORTH);
            jpnTB2.add(jpnPage2,BorderLayout.CENTER);
            
            
            jpnDay.add(day1);
            jpnDay.add(textD1);
            jpnDay.add(day2);
            jpnDay.add(textD2);
            jpnDay.add(jbtnDay);
            
            jpnHead.add(jpnDay,BorderLayout.WEST);
            jpnHead.add(text,BorderLayout.CENTER);
            jpnHead.add(jpnBT,BorderLayout.EAST);
              
            headerKM.add(heading,BorderLayout.NORTH);
            headerKM.add(jpnHead,BorderLayout.CENTER);
            
            container.add(jpnTB1,BorderLayout.CENTER);
            container.add(jpnTB2,BorderLayout.EAST);
            
            contentKM.add(headerKM,BorderLayout.NORTH);
            contentKM.add(container,BorderLayout.CENTER);
            
            jpnMa.add(jlbMa);
            jpnMa.add(jtMa);
            
            jpnTen.add(jlbTen);
            jpnTen.add(jtTen);
            
            jpnNBD.add(jlbNBD);
            jpnNBD.add(jtNBD);
            
            jpnNKT.add(jlbNKT);
            jpnNKT.add(jtNKT);
            
            jpnTT.add(jpnMa);
            jpnTT.add(jpnTen);
            jpnTT.add(jpnNBD);
            jpnTT.add(jpnNKT);
            
            jpnClick.add(heading3);
            
            jpnKM.add(jpnClick,BorderLayout.NORTH);
            jpnKM.add(jpnTT,BorderLayout.CENTER);
            
            headerInfo.add(heading1,BorderLayout.WEST);
            headerInfo.add(heading2,BorderLayout.EAST);
            
            jpnCB.add(jlbHT);
            jpnCB.add(jcb);
            
            //jpnBT1.add(jpnCB);
            jpnBT1.add(add);
            
            jpnBT2.add(comeback);
            jpnBT2.add(addKM);
            
            jpnTBM.add(jsp3,BorderLayout.CENTER);
            jpnTBM.add(jpnBT2,BorderLayout.SOUTH);
            
            jpnTB.add(jpnBT1,BorderLayout.NORTH);
            jpnTB.add(jpnTBM,BorderLayout.CENTER);
            
            jpnG.add(headerInfo,BorderLayout.NORTH);
            jpnG.add(jpnKM,BorderLayout.CENTER);
            
            contentInfo.add(jpnG,BorderLayout.NORTH);
            contentInfo.add(jpnTB,BorderLayout.CENTER);
            
            
            card =new CardLayout();
            setPreferredSize(new Dimension(1050,700));
            //setBorder(BorderFactory.createEmptyBorder(10,30,10,30));   
            setLayout(card);
            
            add(contentKM,"KM");
            add(contentInfo,"Info");
            
            
            jpnPT.add(jlbPT);
            jpnPT.add(jtPT);
        }

        private void getTableData1(int soLuongRow, int trang) {
            model1=ConfigTable.addTableHeader(title1,5); 
            /*ArrayList<KhuyenMai> DSNCC=new ArrayList<KhuyenMai>();
            DSNCC=KMArr.get(soLuongRow,trang);
            int length=DSNCC.size();
            for (int i=0;i<length;i++) {
                Vector row=new Vector();
                row.add(soLuongRow*(trang-1)+i+1);
                row.add(DSNCC.get(i).getMaKM());
                row.add(DSNCC.get(i).getTenKM());
                row.add(DSNCC.get(i).getNgayBatDau());
                row.add(DSNCC.get(i).getNgayKetThuc());
                model1.addRow(row);
            }*/
            int length=dsKM.size();
            int vtBD=(trang-1)*soLuongRow;
            int conLai=0;
            if (length-vtBD>soLuongRow) {
                conLai=soLuongRow;
            }
            else {
                conLai=length-vtBD;
            }
            
            int vtKT=vtBD+conLai;
            for (int i=vtBD;i<vtKT;i++) {
                Vector row=new Vector();
                row.add(i+1);
                row.add(dsKM.get(i).getMaKM());
                row.add(dsKM.get(i).getTenKM());
                row.add(dsKM.get(i).getNgayBatDau());
                row.add(dsKM.get(i).getNgayKetThuc());
                model1.addRow(row);
            }
            jt1.setModel(model1);
            retable(jt1);
            //jt.getTableHeader().setReorderingAllowed(false); // cam keo cot qua lai
            AcceptRejectRenderer renderer = new AcceptRejectRenderer(1);
            jt1.getColumnModel().getColumn(5).setCellRenderer(renderer);
            jt1.getColumnModel().getColumn(5).setCellEditor(new AcceptRejectEditor(1));
            isSr1=0;
        }
        
        private void getTableData2(int soLuongRow, int trang) {
            model2=ConfigTable.addTableHeader(title2,-1); 
            /*ArrayList<ChiTietKhuyenMai> DSNCC=new ArrayList<ChiTietKhuyenMai>();
            DSNCC=CTKMArr.get(soLuongRow,trang);
            int length=DSNCC.size();
            for (int i=0;i<length;i++) {
                Vector row=new Vector();
                row.add(soLuongRow*(trang-1)+i+1);
                row.add(DSNCC.get(i).getMaKM());
                row.add(DSNCC.get(i).getMaSP());
                row.add(DSNCC.get(i).getHinhThucKhuyenMai());
                row.add(DSNCC.get(i).getPhanTramKhuyenMai());
                model2.addRow(row);
            }*/
            int length=dsCTKM.size();
            int vtBD=(trang-1)*soLuongRow;
            int conLai=0;
            if (length-vtBD>soLuongRow) {
                conLai=soLuongRow;
            }
            else {
                conLai=length-vtBD;
            }
            
            int vtKT=vtBD+conLai;
            for (int i=vtBD;i<vtKT;i++) {
                Vector row=new Vector();
                row.add(i+1);
                row.add(dsCTKM.get(i).getMaKM());
                row.add(dsCTKM.get(i).getMaSP());
                row.add(dsCTKM.get(i).getPhanTramKhuyenMai());
                model2.addRow(row);
            }
            jt2.setModel(model2);
            retable(jt2);
            //jt.getTableHeader().setReorderingAllowed(false); // cam keo cot qua lai
            /*AcceptRejectRenderer renderer = new AcceptRejectRenderer(2);
            jt2.getColumnModel().getColumn(4).setCellRenderer(renderer);
            jt2.getColumnModel().getColumn(4).setCellEditor(new AcceptRejectEditor(2));*/
            isSr2=0;
        }
        public void getTableData3() {
             model3=ConfigTable.addTableHeader(title3,7); 
             //jt3.getColumnModel().getColumn(6).set;
             //model3=ConfigTable.addTableHeader(title3,6); 
             /*int i=1;
            for (SanPham sp : dsSP) {
                Vector row=new Vector();
                row.add(i++);
                row.add(sp.getMaSP());
                row.add(sp.getMaLoai());
                row.add(sp.getMaNSX());
                row.add(sp.getTenSP());
                row.add(sp.getDonGia());
                model3.addRow(row);
            }*/
            jt3.setModel(model3);
            retable(jt3);
            //jt.getTableHeader().setReorderingAllowed(false); // cam keo cot qua lai
            AcceptRejectRenderer renderer = new AcceptRejectRenderer(3);
            jt3.getColumnModel().getColumn(7).setCellRenderer(renderer);
            jt3.getColumnModel().getColumn(7).setCellEditor(new AcceptRejectEditor(3));
        }
        public void timKiem() {
            if (text.getText()!="") {
                ArrayList<KhuyenMai> kmArrSr=new ArrayList<KhuyenMai>();
                kmArrSr=KMArr.searchTenKM(text.getText());
                dsKM=kmArrSr;
                getTableData1(soLuongRow,1);
            }
            
            
         /*KhuyenMai KMSr=KMArr.search(Integer.parseInt(text.getText()));
                dtmSr1=ConfigTable.addTableHeader(title1,5); 
                Vector row=new Vector();
                row.add(1);
                row.add(KMSr.getMaKM());
                row.add(KMSr.getTenKM());
                row.add(KMSr.getNgayBatDau());
                row.add(KMSr.getNgayKetThuc());
                dtmSr1.addRow(row);
                jt1.setModel(dtmSr1);
                retable(jt1);
                AcceptRejectRenderer renderer = new AcceptRejectRenderer(1);
                jt1.getColumnModel().getColumn(5).setCellRenderer(renderer);
                jt1.getColumnModel().getColumn(5).setCellEditor(new AcceptRejectEditor(1));
                isSr1=1;*/
        }
        
        public void timKiem1() {
            ArrayList<KhuyenMai> kmArrSr=new ArrayList<KhuyenMai>();
            kmArrSr=KMArr.searchTenKM(jtKM.getText());
            DefaultTableModel modelSr=new DefaultTableModel();
            modelSr=ConfigTable.addTableHeader(title4);
            int i=0;
            for (KhuyenMai km : kmArrSr ) {
                Vector row=new Vector();
                row.add(i+1);
                row.add(km.getMaKM());
                row.add(km.getTenKM());
                row.add(km.getNgayBatDau());
                row.add(km.getNgayKetThuc());
                modelSr.addRow(row);
                i++;
            }
            jt4.setModel(modelSr);
            retable(jt4);
        }
        public void timKiem2() {
             ArrayList<SanPham> spArrSr=new ArrayList<SanPham>();
            spArrSr=spArr.searchTenSP(jtSP.getText());
            DefaultTableModel modelSr=new DefaultTableModel();
            modelSr=ConfigTable.addTableHeader(title5);
            int i=0;
            for (SanPham sp : spArrSr ) {
               Vector row=new Vector();
                row.add(i+1);
                row.add(sp.getMaSP());
                row.add(sp.getMaLoai());
                row.add(sp.getMaNSX());
                row.add(sp.getTenSP());
                row.add(sp.getDonGia());
                modelSr.addRow(row);
                i++;
            }
            jt5.setModel(modelSr);
            retable(jt5);
        }
        
    public void sua() throws ParseException {
        card.show(this,"Info");
        getTableData3();
        int n=jt1.getSelectedRow();
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(jt1.getModel().getValueAt(n,3).toString());
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(jt1.getModel().getValueAt(n,4).toString());

        
        
        jtMa.setText(jt1.getModel().getValueAt(n,1).toString());
        jtTen.setText(jt1.getModel().getValueAt(n,2).toString());
        jtNBD.setDate(date1);
        jtNKT.setDate(date2);
        
        jtTen.setEditable(true);
        jtNBD.setEnabled(true);
        jtNKT.setEnabled(true);
        
        dsSPT.clear();
        while (model3.getRowCount()>0) {
             model3.removeRow(0);
        }
        dsSPT=CTKMArr.search(Integer.parseInt(jt1.getModel().getValueAt(n,1).toString()));
                
        int i=1;
        for (ChiTietKhuyenMai ctkm : dsSPT) {
            Vector row=new Vector();
            row.add(i++);
            SanPham sp=spArr.search(ctkm.getMaSP());
            row.add(sp.getMaSP());
            row.add(sp.getMaLoai());
            row.add(sp.getMaNSX());
            row.add(sp.getTenSP());
            row.add(sp.getDonGia());
            row.add(ctkm.getPhanTramKhuyenMai());
            model3.addRow(row);
         }
                
         jt3.setModel(model3);
                
         jpnClick.removeAll();
         jpnClick.repaint();
         jpnClick.validate();
         jpnBT1.removeAll();
         jpnBT1.repaint();
         jpnBT1.validate();
         jpnBT2.removeAll();
         jpnBT2.repaint();
         jpnBT2.validate();

         heading1.setBackground(mainColor);
         heading1.setForeground(Color.black);
         heading2.setBackground(new Color(0xfc498e));
         heading2.setForeground(Color.white);
         jpnClick.add(heading3);
         jpnClick.add(jbtnClick);
         
         jpnBT1.add(add);
         jpnBT2.add(comeback);
         jpnBT2.add(save);

         jpnClick.repaint();
         jpnClick.validate();
         jpnBT1.repaint();
         jpnBT1.validate();
         jpnBT2.repaint();
         jpnBT2.validate();
    }

    
    public void xoa1() {
        int result=JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa!", "Chú ý",JOptionPane.OK_CANCEL_OPTION);
        if (result==0) {
            int x=jt1.getSelectedRow();
            KMArr.remove(Integer.parseInt(jt1.getModel().getValueAt(x,1).toString()));
            KMArr.get();
            dsKM=KMArr.getKmArr();
            int trang=Integer.parseInt(number1.getText());
            getTableData1(soLuongRow, trang);    
        }
    }
    
    public void xoa2() {
        int result=JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa!", "Chú ý",JOptionPane.OK_CANCEL_OPTION);
        if (result==0) {
            int x=jt2.getSelectedRow();
            CTKMArr.remove(Integer.parseInt(jt2.getModel().getValueAt(x,1).toString()));
            CTKMArr.get();
            CTKMArr.getCtkmArr();
            int trang=Integer.parseInt(number2.getText());
            getTableData2(soLuongRow, trang);    
        }
    }
    
    public void xoa3() {
        int result=JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa!", "Chú ý",JOptionPane.OK_CANCEL_OPTION);
        if (result==0) {
            int i=jt3.getSelectedRow();
            model3.removeRow(i);
            for (int j=0;j<model3.getRowCount();j++) {
                 jt3.getModel().setValueAt(j+1,j, 0);
            }
            jt3.setModel(model3);
        }
    }
    
    public void modelDataSP(DefaultTableModel model) {
        int i=0;
        for (SanPham sp : dsSP ) {
            Vector row=new Vector();
            row.add(i+1);
            row.add(dsSP.get(i).getMaSP());
            row.add(dsSP.get(i).getMaLoai());
            row.add(dsSP.get(i).getMaNSX());
            row.add(dsSP.get(i).getTenSP());
            row.add(dsSP.get(i).getDonGia());
            model.addRow(row);
            i++;
        }
    }
    
    public void modelDataKM(DefaultTableModel model) {
         int i=0;
        for (KhuyenMai sp : dsKM ) {
            Vector row=new Vector();
            row.add(i+1);
            row.add(dsKM.get(i).getMaKM());
            row.add(dsKM.get(i).getTenKM());
            row.add(dsKM.get(i).getNgayBatDau());
            row.add(dsKM.get(i).getNgayKetThuc());
            model.addRow(row);
            i++;
        }
    }
    
    public void retable(JTable tableName) {
        if (tableName.equals(jt1)) {
            int[] colsGetCenter1 = { 0, 1};
            String[] setWidthOptions = { "p-50", "p-50", "mi-300", "p-120", "p-120", "p-190"};
            ConfigTable.decorateTable(jt1, colsGetCenter1, title1.length, setWidthOptions);
        }
        if (tableName.equals(jt2)) {
            int[] colsGetCenter1 = { 0,1,2,3};
            String[] setWidthOptions = { "p-100", "p-108", "p-108", "p-100"};
            ConfigTable.decorateTable(jt2, colsGetCenter1, title2.length, setWidthOptions);
        }
         if (tableName.equals(jt3)) {
            int[] colsGetCenter1 = {0,1,2,3,6};
            String[] setWidthOptions = { "p-50", "p-50", "p-50", "p-50", "p-300", "p-150","p-50","p-100"};
            ConfigTable.decorateTable(jt3, colsGetCenter1, title3.length, setWidthOptions);
        }
        if (tableName.equals(jt4)) {
            int[] colsGetCenter1 = {0};
            String[] setWidthOptions = { "p-50", "p-50","p-300", "p-150","p-150"};
            ConfigTable.decorateTable(jt4, colsGetCenter1, title4.length, setWidthOptions);
        }
        if (tableName.equals(jt5)) {
            int[] colsGetCenter1 = {0};
            String[] setWidthOptions = { "p-50", "p-50", "p-50", "p-50", "p-300", "p-150"};
            ConfigTable.decorateTable(jt5, colsGetCenter1, title5.length, setWidthOptions);
        }
    }

    public void tableDataKM() {
        info=new JFrame();
        info.setSize(900,500);
        info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        info.setLocationRelativeTo(null);
        info.setLayout(new BorderLayout());
        info.setUndecorated(true);
        info.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
    }
    public void dataSP() {
        jpnS.removeAll();
        jpnS.repaint();
        jpnS.validate();
        
        jpnBC.removeAll();
        jpnBC.repaint();
        jpnBC.validate();
        
        jsp4.remove(jt4);
        jsp4.repaint();
        jsp4.validate();
        
        jtSP.setText("Tìm kiếm theo tên sản phẩm");
        jtPT.setText("");
        jpnS.add(jlbCSP,BorderLayout.NORTH);
        jpnS.add(jtSP,BorderLayout.CENTER);
        jpnS.add(jpnPT,BorderLayout.EAST);
        
        jpnBC.add(jbtnClose);
        jpnBC.add(jbtnCSP);
        
        
        modelSP=ConfigTable.addTableHeader(title5);
        modelDataSP(modelSP);
        jt5.setModel(modelSP);
        retable(jt5);
        jsp4.getViewport().add(jt5);
        
        jpnS.repaint();
        jpnS.validate();
        jpnBC.repaint();
        jpnBC.validate();
        jsp4.repaint();
        jsp4.validate();
        
        
        
        
        info.add(jpnS,BorderLayout.NORTH);
        info.add(jsp4,BorderLayout.CENTER);
        info.add(jpnBC,BorderLayout.SOUTH);
    }
    
    public void dataKM() {
        jpnS.removeAll();
        jpnS.repaint();
        jpnS.validate();
        
        jpnBC.removeAll();
        jpnBC.repaint();
        jpnBC.validate();
        
        jsp4.remove(jt5);
        jsp4.repaint();
        jsp4.validate();
        
        jtKM.setText("Tìm kiếm theo tên khuyến mãi");
        jpnS.add(jlbCKM,BorderLayout.NORTH);
        jpnS.add(jtKM,BorderLayout.CENTER);
        
        jpnBC.add(jbtnClose);
        jpnBC.add(jbtnCKM);
        
       
        modelKM=ConfigTable.addTableHeader(title4);
        modelDataKM(modelKM);
        jt4.setModel(modelKM);
        retable(jt4);
        jsp4.getViewport().add(jt4);
        
        jpnS.repaint();
        jpnS.validate();
        jpnBC.repaint();
        jpnBC.validate();
        jsp4.repaint();
        jsp4.validate();
        
        
        
        info.add(jpnS,BorderLayout.NORTH);
        info.add(jsp4,BorderLayout.CENTER);
        info.add(jpnBC,BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         switch (e.getActionCommand()) {
            case "prev1":
                if (!number1.getText().equals("1")) {
                    int trangTruoc = Integer.parseInt(number1.getText()) - 1;
                    getTableData1(soLuongRow, trangTruoc);
                    number1.setText(Integer.toString(trangTruoc));
                }
                break;
            case "next1": {
                //Double trangCuoi = KMArr.getTongSoTrang(soLuongRow);
                Double trangHienTai = Double.parseDouble(number1.getText());
                Double length=dsKM.size()-trangHienTai*soLuongRow;
                if (/*!trangHienTai.equals(trangCuoi)*/ length>0) {
                    int trangSau = Integer.parseInt(number1.getText()) + 1;
                    number1.setText(Integer.toString(trangSau));
                    getTableData1(soLuongRow, trangSau);
                }
                break;
             }
            case "prev2":
                if (!number2.getText().equals("1")) {
                    int trangTruoc = Integer.parseInt(number2.getText()) - 1;
                    getTableData2(soLuongRow, trangTruoc);
                    number2.setText(Integer.toString(trangTruoc));
                }
                break;
            case "next2": {
                Double trangCuoi = CTKMArr.getTongSoTrang(soLuongRow);
                Double trangHienTai = Double.parseDouble(number2.getText());
                Double length=dsCTKM.size()-trangHienTai*soLuongRow;
                if (/*!trangHienTai.equals(trangCuoi)*/ length>0) {
                    int trangSau = Integer.parseInt(number2.getText()) + 1;
                    number2.setText(Integer.toString(trangSau));
                    getTableData2(soLuongRow, trangSau);
                }
                break;
             }
            case "them" : {
                card.show(this,"Info");
                jtMa.setText("");
                jtTen.setText("");
                jtNBD.setDate(null);
                jtNKT.setDate(null);
                getTableData3();
                break;
            }
            case "comeback" : {
                card.show(this,"KM");
                dsKM=KMArr.getKmArr();
                getTableData1(soLuongRow, 1);
                dsCTKM=CTKMArr.getCtkmArr();
                getTableData2(soLuongRow, 1);
                number1.setText("1");
                number2.setText("1");
                break;
            }
            case "timkiem" : {
                timKiem();
                break;
            }
            case "excel" : {
                CreateExcelFile.fileKhuyenMai(KMArr.getKmArr(),CTKMArr.getCtkmArr());
                break;
            }
            case "loc" : {
                SimpleDateFormat sDF=new SimpleDateFormat("yyyy-MM-dd");
                int t=0;
                int count=0;
                Date d1=textD1.getDate();
                Date d2=textD2.getDate();
                String dS1="",dS2="";
                if (d1==null) {
                    t++;
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầu đủ ngày tháng năm của ngày bắt đầu (yyyy-MM-dd)",  "Lỗi", 0);
                }
                else {
                    dS1 =sDF.format(d1);
                }
                if (d2==null) {
                    t++;
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầu đủ ngày tháng năm của ngày kết thúc (yyyy-MM-dd)",  "Lỗi", 0);         
                }
                else {
                    dS2 =sDF.format(d2);
                }
                if (t==0) {
                    if (dS1.compareTo(dS2)>0) {
                        count++;
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc",  "Lỗi", 0);
                    }
                    if (count==0) {
                        ArrayList<KhuyenMai> filterKM=new ArrayList<KhuyenMai>();
                        for (KhuyenMai km: dsKM) {
                            if (km.getNgayBatDau().compareTo(dS1) >=0 && km.getNgayKetThuc().compareTo(dS2) <= 0) {
                                 filterKM.add(km);
                            } 
                        }
                        dsKM=filterKM;
                        getTableData1(soLuongRow, 1);
                        number1.setText("1");
                    }
                }
               
                break;
            }
            case "themspkm": {
                tableDataKM();
                dataSP();
                info.setVisible(true);
                break;
            }
            case "hienkm": {
                tableDataKM();
                dataKM();
                info.setVisible(true);
                break;
            }
            case "dong": {
                info.dispose();
                maSP="";
                maKM="";
                break;
            }
            case "chonkm": {
                if (maKM.compareTo("")==0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng khuyến mãi!",  "Lỗi", 0);
                }
                else {
                    dsSPT.clear();
                    while (model3.getRowCount()>0) {
                        model3.removeRow(0);
                    }
                     Date date1 = null,date2=null;
                    try {
                        date1 =new SimpleDateFormat("yyyy-MM-dd").parse(ngayBD);
                        date2=new SimpleDateFormat("yyyy-MM-dd").parse(ngayKT);
                    } catch (ParseException ex) {
                        Logger.getLogger(KhuyenMaiPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }



                    jtMa.setText(maKM);
                    jtTen.setText(tenKM);
                    jtNBD.setDate(date1);
                    jtNKT.setDate(date2);

                    dsSPT=CTKMArr.search(Integer.parseInt(maKM));

                    int i=1;
                    for (ChiTietKhuyenMai ctkm : dsSPT) {
                        Vector row=new Vector();
                        row.add(i++);
                        SanPham sp=spArr.search(ctkm.getMaSP());
                        row.add(sp.getMaSP());
                        row.add(sp.getMaLoai());
                        row.add(sp.getMaNSX());
                        row.add(sp.getTenSP());
                        row.add(sp.getDonGia());
                        row.add(ctkm.getPhanTramKhuyenMai());
                        model3.addRow(row);
                    }

                    jt3.setModel(model3);
                    jtTen.setEditable(true);
                    jtNBD.setEnabled(true);
                    jtNKT.setEnabled(true);

                    jpnBT1.removeAll();
                    jpnBT1.repaint();
                    jpnBT1.validate();
                    jpnBT1.add(add);
                    jpnBT1.repaint();
                    jpnBT1.validate();

                    maKM="";
                    dsSPT.clear();
                    info.dispose();
                }
                
                break;
            }
            
            case "chonsp": {
                if (maSP.compareTo("")==0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng sản phẩm!",  "Lỗi", 0);
                }
                else {
                    int t=0;
                    int sodong=model3.getRowCount();
                    for (int i=0;i<sodong;i++) {
                        int masp=Integer.parseInt(model3.getValueAt(i,1).toString());
                        if (masp==Integer.parseInt(maSP)) {
                             t=1;
                            break;
                        }
                    }
                    if (t==1) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm đã có trong danh sách!",  "Lỗi", 0);
                    }
                    else {
                         phanTram=jtPT.getText();
                        if (InputTester.chiSo(phanTram, "Phần trăm khuyến mãi",2)) {
                            Vector row=new Vector();
                            row.add(model3.getRowCount()+1);
                            row.add(maSP);
                            row.add(maLoai);
                            row.add(maNSX);
                            row.add(tenSP);
                            row.add(donGia);
                            row.add(phanTram);
                            model3.addRow(row);
                            //ChiTietKhuyenMai ctkmT=new ChiTietKhuyenMai(0,Integer.parseInt(maSP),"",Integer.parseInt(phanTram));
                            //dsSPT.add(ctkmT);
                            jt3.setModel(model3);
                            maSP="";
                            info.dispose();
                        }
                    }
                }
                
                break;
            }
            case "themkm": {
                SimpleDateFormat sDF=new SimpleDateFormat("yyyy-MM-dd");
                int t=0;
                int count=0;
                Date d1=jtNBD.getDate();
                Date d2=jtNKT.getDate();
                String dS1="",dS2="";
                if (d1==null) {
                    t++;
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng ngày tháng năm của ngày bắt đầu (yyyy-MM-dd)",  "Lỗi", 0);
                }
                else {
                    dS1 =sDF.format(d1);
                }
                if (d2==null) {
                    t++;
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng ngày tháng năm của ngày kết thúc (yyyy-MM-dd)",  "Lỗi", 0);         
                }
                else {
                    dS2 =sDF.format(d2);
                }
                if (t==0) {
                    if (dS1.compareTo(dS2)>0) {
                        count++;
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc",  "Lỗi", 0);
                    }
                    if (!InputTester.chiChuVaKhoangTrang(jtTen.getText(),"Tên khuyến mãi", 255)) {
                        count++;
                    }
                    if (model3.getRowCount()<=0) {
                        count++;
                        JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 sản phẩm trong khuyến mãi",  "Lỗi", 0);
                    }
                    if (count==0) {
                        int sodong=model3.getRowCount();
                        for (int i=0;i<sodong;i++) {
                            int masp=Integer.parseInt(model3.getValueAt(i,1).toString());
                            int pt=Integer.parseInt(model3.getValueAt(i,6).toString());
                            dsSPT.add(new ChiTietKhuyenMai(0,masp,"",pt));
                        }
                        KMArr.add(0,jtTen.getText(),dS1,dS2,dsSPT);
                        jtTen.setText("");
                        jtNBD.setDate(null);
                        jtNKT.setDate(null);
                        while (model3.getRowCount()>0) {
                            model3.removeRow(0);
                        }
                        dsSPT.clear();
                        KMArr.get();
                        dsKM=KMArr.getKmArr();
                        CTKMArr.get();
                        dsCTKM=CTKMArr.getCtkmArr();
                        JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!", "", JOptionPane.PLAIN_MESSAGE);
                        } 
                }
                
               
                break;
            }
            case "luukm": {
                if (jtMa.getText().compareTo("")==0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi cần sửa!",  "Lỗi", 0);
                }
                else {
                    SimpleDateFormat sDF=new SimpleDateFormat("yyyy-MM-dd");
                    int t=0;
                    int count=0;
                    Date d1=jtNBD.getDate();
                    Date d2=jtNKT.getDate();
                    String dS1="",dS2="";
                    if (d1==null) {
                        t++;
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng ngày tháng năm của ngày bắt đầu (yyyy-MM-dd)",  "Lỗi", 0);
                    }
                    else {
                        dS1 =sDF.format(d1);
                    }
                    if (d2==null) {
                        t++;
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng ngày tháng năm của ngày kết thúc (yyyy-MM-dd)",  "Lỗi", 0);         
                    }
                    else {
                        dS2 =sDF.format(d2);
                    }
                    if (t==0) {
                        if (dS1.compareTo(dS2)>0) {
                            count++;
                            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc",  "Lỗi", 0);
                        }
                        if (!InputTester.chiChuVaKhoangTrang(jtTen.getText(),"Tên khuyến mãi", 255)) {
                            count++;
                        }
                        if (model3.getRowCount()<=0) {
                            count++;
                            JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 sản phẩm trong khuyến mãi",  "Lỗi", 0);
                        }
                        if (count==0) {
                            int sodong=model3.getRowCount();
                            for (int i=0;i<sodong;i++) {
                                int makm=Integer.parseInt(jtMa.getText());
                                int masp=Integer.parseInt(model3.getValueAt(i,1).toString());
                                int pt=Integer.parseInt(model3.getValueAt(i,6).toString());
                                dsSPT.add(new ChiTietKhuyenMai(makm,masp,"",pt));
                                System.out.println(model3.getValueAt(i,1));
                            }
                            for (int k=0;k<dsSPT.size();k++) {
                                System.out.println(dsSPT.get(k).toString());
                            }
                            KMArr.edit(Integer.parseInt(jtMa.getText()),jtTen.getText(),dS1,dS2,dsSPT);
                            KMArr.get();
                            dsKM=KMArr.getKmArr();
                            CTKMArr.get();
                            dsCTKM=CTKMArr.getCtkmArr();
                            dsSPT.clear();
                            System.out.println(dsSPT.size());
                            JOptionPane.showMessageDialog(null, "Sửa thông tin khuyến mãi thành công!", "", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
                break;
            }
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==text) {
            text.setText("");
        }
        if (e.getSource()==jt1) {
            int i=jt1.getSelectedRow();
            dsCTKM=CTKMArr.search(Integer.parseInt(jt1.getModel().getValueAt(i, 1).toString()));
            number2.setText("1");
            getTableData2(soLuongRow, 1);
        }
        if (e.getSource()==heading) {
            dsKM=KMArr.getKmArr();
            getTableData1(soLuongRow, 1);
            dsCTKM=CTKMArr.getCtkmArr();
            getTableData2(soLuongRow, 1);
            number1.setText("1");
            number2.setText("1");
        }
        if (e.getSource()==heading1) {
            while (model3.getRowCount()>0) {
                model3.removeRow(0);
            }
            
            jpnClick.removeAll();
            jpnClick.repaint();
            jpnClick.validate();
            jpnBT1.removeAll();
            jpnBT1.repaint();
            jpnBT1.validate();
            jpnBT2.removeAll();
            jpnBT2.repaint();
            jpnBT2.validate();
            
            heading1.setBackground(new Color(0xfc498e));
            heading1.setForeground(Color.white);
            heading2.setBackground(mainColor);
            heading2.setForeground(Color.black);
            
            jpnClick.add(heading3);
            jtMa.setText("Mã tự động tạo");
            jtTen.setText("");
            jtNBD.setDate(null);
            jtNKT.setDate(null);
            jpnBT1.add(add);
                
            
            jtTen.setEditable(true);
            jtNBD.setEnabled(true);
            jtNKT.setEnabled(true);
            
            jpnBT2.add(comeback);
            jpnBT2.add(addKM);
            
            dsSPT.clear();
            
            jpnClick.repaint();
            jpnClick.validate();
            jpnBT1.repaint();
            jpnBT1.validate();
            jpnBT2.repaint();
            jpnBT2.validate();
        }
        if (e.getSource()==heading2) {
            while (model3.getRowCount()>0) {
                model3.removeRow(0);
            }
            jpnClick.removeAll();
            jpnClick.repaint();
            jpnClick.validate();
            jpnBT1.removeAll();
            jpnBT1.repaint();
            jpnBT1.validate();
            jpnBT2.removeAll();
            jpnBT2.repaint();
            jpnBT2.validate();
            
            heading1.setBackground(mainColor);
            heading1.setForeground(Color.black);
            heading2.setBackground(new Color(0xfc498e));
            heading2.setForeground(Color.white);
            
            jpnClick.add(heading3);
            jpnClick.add(jbtnClick);
            jtMa.setText("");
            jtTen.setText("");
            jtNBD.setDate(null);
            jtNKT.setDate(null);
            
            jtTen.setEditable(false);
            jtNBD.setEnabled(false);
            jtNKT.setEnabled(false);

            jpnBT2.add(comeback);
            jpnBT2.add(save);
            
            dsSPT.clear();
            
            jpnClick.repaint();
            jpnClick.validate();
            jpnBT1.repaint();
            jpnBT1.validate();
            jpnBT2.repaint();
            jpnBT2.validate();
        }
        
        if (e.getSource()==jt4) {
            int i=jt4.getSelectedRow();
            maKM=jt4.getModel().getValueAt(i, 1).toString();
            tenKM=jt4.getModel().getValueAt(i, 2).toString();
            ngayBD=jt4.getModel().getValueAt(i, 3).toString();
            ngayKT=jt4.getModel().getValueAt(i, 4).toString();
        }
        
        if (e.getSource()==jt5) {
            int i=jt5.getSelectedRow();
            maSP=jt5.getModel().getValueAt(i,1).toString();
            maLoai=jt5.getModel().getValueAt(i,2).toString();
            maNSX=jt5.getModel().getValueAt(i,3).toString();
            tenSP=jt5.getModel().getValueAt(i,4).toString();
            donGia=jt5.getModel().getValueAt(i,5).toString();
        }
        if (e.getSource()==jlbCKM) {
            jt4.setModel(modelKM);
            retable(jt4);
        }
        if (e.getSource()==jlbCSP) {
            jt5.setModel(modelSP);
            retable(jt5);
        }
        if (e.getSource()==jtSP) {
            jtSP.setText("");
        }
        if (e.getSource()==jtKM) {
            jtKM.setText("");
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
        if (e.getSource()==jtKM) {
            timKiem1();
        }
        if (e.getSource()==jtSP) {
            timKiem2();
        }
    }
    
    
    public class AcceptRejectPane extends JPanel {

        private JButton jbtSua;
        private JButton jbtXoa;
        private String state;

        public AcceptRejectPane(int i) {
            setLayout(new GridBagLayout());
            jbtSua = new JButton();
            jbtSua.setIcon(iconEdit);
            jbtSua.setBackground(Color.green);
            jbtXoa = new JButton();
            jbtXoa.setIcon(iconDelete);
            jbtXoa.setBackground(Color.red);
            
            if (i==1) {
                jbtSua.setActionCommand("sua1");
                jbtXoa.setActionCommand("xoa1");
            }
            if (i==2) {
                jbtSua.setActionCommand("sua2");
                jbtXoa.setActionCommand("xoa2");
            }
            if (i==3) {
                jbtXoa.setActionCommand("xoa3");
            }
            if (i==3) {
                add(jbtXoa);
            }
            else {
                add(jbtSua);
                add(jbtXoa);
            }
            

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (e.getActionCommand()) {
                        case "sua1": {
                            try {
                                sua();
                            } catch (ParseException ex) {
                                Logger.getLogger(KhuyenMaiPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        }
                        case "xoa1": {
                            xoa1();
                            break;
                        }
                        case "sua2": {
                            try {
                                sua();
                            } catch (ParseException ex) {
                                Logger.getLogger(KhuyenMaiPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                break;
                        }
                        case "xoa2": {
                            xoa2();
                            break;
                        }
                        case "xoa3": {
                            xoa3();
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

        public AcceptRejectRenderer(int i) {
            acceptRejectPane = new AcceptRejectPane(i);
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

        public AcceptRejectEditor(int i) {
            acceptRejectPane = new AcceptRejectPane(i);
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
