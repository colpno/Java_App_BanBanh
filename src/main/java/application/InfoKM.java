
package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InfoKM extends JPanel {
        private JPanel contentInfo,headerInfo,jpnKM,jpnTT,jpnMa,jpnTen,jpnNBD,jpnNKT,jpnTB,jpnBT1,jpnBT2,jpnCB,jpnTBM;
        private JLabel heading1,heading2,heading3,jlbMa,jlbTen,jlbNBD,jlbNKT,jlbHT;
        private JTextField jtMa,jtTen,jtNBD,jtNKT;
        private JScrollPane jsp3;
        private JTable jt3;
        private DefaultTableModel model3;
        private JButton add,exit,save;
        private JComboBox jcb;
        private String[] ht={"Online","Mua trực tiếp"};
        InfoKM () {
            contentInfo=new JPanel();
            contentInfo.setPreferredSize(new Dimension(1050,700));
            //setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
            contentInfo.setBackground(Color.red);
            contentInfo.setLayout(new BorderLayout());
            
            headerInfo=new JPanel();
            headerInfo.setPreferredSize(new Dimension(1050,50));
            headerInfo.setLayout(new FlowLayout(0,0,0));
            
            jpnKM=new JPanel();
            jpnKM.setPreferredSize(new Dimension(1050,50));
            jpnKM.setLayout(new BorderLayout());
            
            jpnTT=new JPanel();
            jpnTT.setPreferredSize(new Dimension(1050,150));
            jpnTT.setLayout(new FlowLayout(0,20,10));
            
            jpnTB=new JPanel();
            jpnTB.setPreferredSize(new Dimension(1050,470));
            jpnTB.setLayout(new BorderLayout());
            jpnTB.setBackground(Color.cyan);
            
            jpnMa=new JPanel();
            jpnMa.setPreferredSize(new Dimension(400,50));
            jpnMa.setBackground(Color.pink);
            jpnMa.setLayout(new FlowLayout(0,0,0));
            jpnMa.setOpaque(true);
            
            jpnTen=new JPanel();
            jpnTen.setPreferredSize(new Dimension(400,50));
            jpnTen.setBackground(Color.gray);
            jpnTen.setLayout(new FlowLayout(0,0,0));
            jpnTen.setOpaque(true);
            
            jpnNBD=new JPanel();
            jpnNBD.setPreferredSize(new Dimension(400,50));
            jpnNBD.setBackground(Color.orange);
            jpnNBD.setLayout(new FlowLayout(0,0,0));
            jpnNBD.setOpaque(true);
            
            jpnNKT=new JPanel();
            jpnNKT.setPreferredSize(new Dimension(400,50));
            jpnNKT.setBackground(Color.MAGENTA);
            jpnNKT.setLayout(new FlowLayout(0,0,0));
            jpnNKT.setOpaque(true);
            
            jpnBT1=new JPanel();
            jpnBT1.setPreferredSize(new Dimension(1050,50));
            jpnBT1.setLayout(new FlowLayout(0,30,0));
            
            jpnBT2=new JPanel();
            jpnBT2.setPreferredSize(new Dimension(1050,50));
            jpnBT2.setLayout(new FlowLayout(2,0,0));
            
            jpnCB=new JPanel();
            jpnCB.setPreferredSize(new Dimension(500,50));
            jpnCB.setLayout(new FlowLayout(0,0,0));
            jpnCB.setBackground(Color.red);
            
            jpnTBM=new JPanel();
            jpnTBM.setPreferredSize(new Dimension(1050,420));
            jpnTBM.setLayout(new BorderLayout());
            
            
            
            heading1=new JLabel("Thêm khuyến mãi");
            heading1.setPreferredSize(new Dimension(525,50));
            heading1.setBackground(Color.blue);
            heading1.setHorizontalAlignment(JLabel.CENTER);
            heading1.setVerticalAlignment(JLabel.CENTER);
            heading1.setOpaque(true);
            
            heading2=new JLabel("Thay đổi thông tin khuyến mãi");
            heading2.setPreferredSize(new Dimension(525,50));
            heading2.setBackground(Color.green);
            heading2.setHorizontalAlignment(JLabel.CENTER);
            heading2.setVerticalAlignment(JLabel.CENTER);
            heading2.setOpaque(true);
            
            heading3=new JLabel("Thông tin khuyến mãi");
            heading3.setPreferredSize(new Dimension(525,50));
            heading3.setBackground(Color.YELLOW);
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
            
            jcb=new JComboBox(ht);
            jcb.setPreferredSize(new Dimension(150,50));
            
            jtMa=new JTextField();
            jtMa.setPreferredSize(new Dimension(250,50));
            
            jtTen=new JTextField();
            jtTen.setPreferredSize(new Dimension(250,50));
            
            jtNBD=new JTextField();
            jtNBD.setPreferredSize(new Dimension(250,50));
            
            jtNKT=new JTextField();
            jtNKT.setPreferredSize(new Dimension(250,50));
            
            add=new JButton("Thêm");
            add.setPreferredSize(new Dimension(80,50));
            
            exit=new JButton("Quay lại");
            
            save=new JButton("Lưu lại");
            
            jt3=new JTable();
            
            jsp3=new JScrollPane(jt3);
            jsp3.setPreferredSize(new Dimension(1050,300));
            
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
            
            jpnKM.add(heading3,BorderLayout.NORTH);
            jpnKM.add(jpnTT,BorderLayout.CENTER);
            
            headerInfo.add(heading1);
            headerInfo.add(heading2);
            
            jpnCB.add(jlbHT);
            jpnCB.add(jcb);
            
            jpnBT1.add(jpnCB);
            jpnBT1.add(add);
            
            jpnBT2.add(exit);
            jpnBT2.add(save);
            
            jpnTBM.add(jsp3,BorderLayout.NORTH);
            jpnTBM.add(jpnBT2,BorderLayout.CENTER);
            
            jpnTB.add(jpnBT1,BorderLayout.NORTH);
            jpnTB.add(jpnTBM,BorderLayout.CENTER);
            
            contentInfo.add(headerInfo,BorderLayout.NORTH);
            contentInfo.add(jpnKM,BorderLayout.CENTER);
            contentInfo.add(jpnTB,BorderLayout.SOUTH);
        }
}