
package application;

import businesslayer.MangNhaCungCap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import test.InputTester;


public class InfoNCC extends JFrame implements ActionListener{
    private JPanel content,jpnMa,jpnTen,jpnDC,jpnSDT,jpnBT,jpnTF1,jpnTF2,jpnTF3,jpnTF4;
    private JLabel heading,jlbMa,jlbTen,jlbDC,jlbSDT;
    private JTextField jtMa,jtTen,jtDC,jtSDT;
    private JButton back,add,edit;
    private JSeparator jS1,jS2,jS3,jS4;
    private MangNhaCungCap NCCArr=new MangNhaCungCap();
    private Color mainColor=new Color(0xC6E1FF);
    private boolean alter;
    InfoNCC () {
            setSize(500,500);
            setLocationRelativeTo(null);
            setBackground(mainColor);
            setLayout(new FlowLayout(0,0,0));
            
            
            content=new JPanel();
            content.setPreferredSize(new Dimension(500,500));
            content.setLayout(new FlowLayout(0,0,30));
            content.setBackground(new Color(0,0,0,0));
            
            
            jpnMa=new JPanel();
            jpnMa.setPreferredSize(new Dimension(500,50));
            jpnMa.setLayout(new FlowLayout(0,0,0));
            jpnMa.setBackground(new Color(0,0,0,0));
            
            jpnTen=new JPanel();
            jpnTen.setPreferredSize(new Dimension(500,50));
            jpnTen.setLayout(new FlowLayout(0,0,0));
            jpnTen.setBackground(new Color(0,0,0,0));
            
            jpnDC=new JPanel();
            jpnDC.setPreferredSize(new Dimension(500,50));
            jpnDC.setLayout(new FlowLayout(0,0,0));
            jpnDC.setBackground(new Color(0,0,0,0));
            
            jpnSDT=new JPanel();
            jpnSDT.setPreferredSize(new Dimension(500,50));
            jpnSDT.setLayout(new FlowLayout(0,0,0));
            jpnSDT.setBackground(new Color(0,0,0,0));
            
            jpnBT=new JPanel();
            jpnBT.setPreferredSize(new Dimension(500,100));
            jpnBT.setLayout(new FlowLayout(2,0,0));
            jpnBT.setBackground(new Color(0,0,0,0));
            
            
            jpnTF1=new JPanel();
            jpnTF1.setPreferredSize(new Dimension(400,50));
            jpnTF1.setLayout(new FlowLayout(0,0,0));
            jpnTF1.setBackground(mainColor);
            
            jpnTF2=new JPanel();
            jpnTF2.setPreferredSize(new Dimension(400,50));
            jpnTF2.setLayout(new FlowLayout(0,0,0));
            jpnTF2.setBackground(mainColor);
            
            jpnTF3=new JPanel();
            jpnTF3.setPreferredSize(new Dimension(400,50));
            jpnTF3.setLayout(new FlowLayout(0,0,0));
            jpnTF3.setBackground(mainColor);
            
            jpnTF4=new JPanel();
            jpnTF4.setPreferredSize(new Dimension(400,50));
            jpnTF4.setLayout(new FlowLayout(0,0,0));
            jpnTF4.setBackground(mainColor);
            
            
            heading=new JLabel();
            heading.setPreferredSize(new Dimension(500,30));
            heading.setFont(new Font("Arial",Font.ITALIC,20));
            heading.setHorizontalAlignment(JLabel.CENTER);
            heading.setVerticalAlignment(JLabel.CENTER);
            heading.setBackground(new Color(0,0,0,0));
            heading.setOpaque(true);
            
            jlbMa=new JLabel("Mã NCC:");
            jlbMa.setPreferredSize(new Dimension(100,50));
            jlbMa.setBackground(new Color(0,0,0,0));
            jlbMa.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbMa.setOpaque(true);
            
            jlbTen=new JLabel("Tên NCC:");
            jlbTen.setPreferredSize(new Dimension(100,50));
            jlbTen.setBackground(new Color(0,0,0,0));
            jlbTen.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbTen.setOpaque(true);
            
            jlbDC=new JLabel("Địa chỉ:");
            jlbDC.setPreferredSize(new Dimension(100,50));
            jlbDC.setBackground(new Color(0,0,0,0));
            jlbDC.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbDC.setOpaque(true);
            
            jlbSDT=new JLabel("Số ĐT:");
            jlbSDT.setPreferredSize(new Dimension(100,50));
            jlbSDT.setBackground(new Color(0,0,0,0));
            jlbSDT.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbSDT.setOpaque(true);
            
            
            jS1=new JSeparator();
            jS1.setPreferredSize(new Dimension(300,2));
            
            jS2=new JSeparator();
            jS2.setPreferredSize(new Dimension(300,2));
            
            jS3=new JSeparator();
            jS3.setPreferredSize(new Dimension(300,2));
            
            jS4=new JSeparator();
            jS4.setPreferredSize(new Dimension(300,2));
            
            
            
            jtMa=new JTextField("Mã nhà cung cấp tự động tạo!");
            jtMa.setPreferredSize(new Dimension(300,48));
            jtMa.setBorder(null);
            jtMa.setFont(new Font("Arial",Font.PLAIN,18));
            jtMa.setBackground(new Color(0,0,0,0));
            //jtMa.setForeground(Color.white);
            //jtMa.setCaretColor(Color.red);
            jtMa.setEditable(false);
            jtMa.setOpaque(false);
            
            jtTen=new JTextField();
            jtTen.setPreferredSize(new Dimension(300,48));
            jtTen.setBorder(null);
            jtTen.setFont(new Font("Arial",Font.PLAIN,18));
            jtTen.setBackground(new Color(0,0,0,0));
           // jtTen.setForeground(Color.white);
            //jtTen.setCaretColor(Color.red);
            jtTen.setOpaque(false);
            
            jtDC=new JTextField();
            jtDC.setPreferredSize(new Dimension(300,48));
            jtDC.setBorder(null);
            jtDC.setFont(new Font("Arial",Font.PLAIN,18));
            jtDC.setBackground(new Color(0,0,0,0));
            //jtDC.setForeground(Color.white);
            //jtDC.setCaretColor(Color.red);
            jtDC.setOpaque(false);
            
            jtSDT=new JTextField();
            jtSDT.setPreferredSize(new Dimension(300,48));
            jtSDT.setBorder(null);
            jtSDT.setFont(new Font("Arial",Font.PLAIN,18));
            jtSDT.setBackground(new Color(0,0,0,0));
           // jtSDT.setForeground(Color.white);
            //jtSDT.setCaretColor(Color.red);
            jtSDT.setOpaque(false);
            
            back=new JButton("Quay lại");
            back.setPreferredSize(new Dimension(80,40));
            back.setBackground(Color.white);
            back.addActionListener(this);
            
            add=new JButton("Thêm");
            add.setPreferredSize(new Dimension(80,40));
            add.setBackground(Color.blue);
            add.setForeground(Color.white);
            add.addActionListener(this);
            
            edit=new JButton("Sửa");
            edit.setPreferredSize(new Dimension(80,40));
            edit.setBackground(Color.green);
            edit.setForeground(Color.white);
            edit.addActionListener(this);
            
            
            jpnTF1.add(jtMa);
            jpnTF1.add(jS1);
            
            jpnTF2.add(jtTen);
            jpnTF2.add(jS2);
            
            jpnTF3.add(jtDC);
            jpnTF3.add(jS3);
            
            jpnTF4.add(jtSDT);
            jpnTF4.add(jS4);
            
            jpnMa.add(jlbMa);
            jpnMa.add(jpnTF1);
            
            jpnTen.add(jlbTen);
            jpnTen.add(jpnTF2);
            
            jpnDC.add(jlbDC);
            jpnDC.add(jpnTF3);
            
            jpnSDT.add(jlbSDT);
            jpnSDT.add(jpnTF4);
            
            jpnBT.add(back);
            
            content.add(heading);
            content.add(jpnMa);   
            content.add(jpnTen);   
            content.add(jpnDC);   
            content.add(jpnSDT); 
            content.add(jpnBT);
            
            add(content);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setVisible(true);
    }
    
    public void add() {
        jpnBT.add(add);
        heading.setText("Thêm nhà cung cấp");
    }
    public void edit(int maNCC, String tenNCC, String diaChi, String soDienThoai) {
        jpnBT.add(edit);
        heading.setText("Sửa thông tin nhà cung cấp");
        jtMa.setText(Integer.toString(maNCC));
        jtTen.setText(tenNCC);
        jtDC.setText(diaChi);
        jtSDT.setText(soDienThoai);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==back) {
            alter=false;
            dispose();            
        }
        if (e.getSource()==add) {
            String ten=jtTen.getText(),diachi=jtDC.getText(),sodienthoai=jtSDT.getText();
            int count=0;
            if (!InputTester.chiChuVaKhoangTrang(ten,"Tên nhà sản xuất",100)) {
                count++;
            }
            /*
            if (!InputTester.laDiaChi(diachi,"Địa chỉ",255)) {
                count++;
            }
            */
            if (!InputTester.soDienThoaiHopLe(sodienthoai,"Số điện thoại",10)) {
                count++;
            }
            if (count==0) {
                NCCArr.add(0,ten,diachi,sodienthoai);
                alter=true;
                dispose();
                JOptionPane.showMessageDialog(null,"Thêm thành công","Thêm nhà cung cấp",JOptionPane.PLAIN_MESSAGE);
            }
            
        }
        if (e.getSource()==edit) {
            String ten=jtTen.getText(),diachi=jtDC.getText(),sodienthoai=jtSDT.getText();
            int count=0;
            if (!InputTester.chiChuVaKhoangTrang(ten,"Tên nhà sản xuất",100)) {
                count++;
            }
            /*
            if (!InputTester.laDiaChi(diachi,"Địa chỉ",255)) {
                count++;
            }
*/
            if (!InputTester.soDienThoaiHopLe(sodienthoai,"Số điện thoại",10)) {
                count++;
            }
            if (count==0) {
                NCCArr.edit(Integer.parseInt(jtMa.getText()),ten,diachi,sodienthoai);
                alter=true;
                dispose();
                JOptionPane.showMessageDialog(null,"Sửa thành công","Sửa thông tin nhà cung cấp",JOptionPane.PLAIN_MESSAGE);
            }
            
        }
    }
    
    public boolean getAlter() {
        return alter;
    }
    public void setAlter(boolean alter) {
        this.alter=alter;
    }
    
    public static void main(String[] args) {
        new InfoNCC();
        
    }
    
    
}
