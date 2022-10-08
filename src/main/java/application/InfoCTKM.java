
package application;

import businesslayer.Another;
import businesslayer.MangChiTietKhuyenMai;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;


public class InfoCTKM extends JFrame implements ActionListener{
    private JPanel content,jpnMa,jpnTen,jpnNBT,jpnNKT,jpnBT,jpnTF1,jpnTF2,jpnTF3,jpnTF4;
    private JLabel heading,jlbMa,jlbTen,jlbNBT,jlbNKT;
    private JTextField jtMa,jtTen,jtPT;
    private JComboBox jcb;
    private JButton back,add,edit;
    private JSeparator jS1,jS2,jS3,jS4;
    private MangChiTietKhuyenMai CTKMArr=new MangChiTietKhuyenMai();
    private Color mainColor=new Color(0xC6E1FF);
    private boolean alter;
    InfoCTKM () {
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
            
            jpnNBT=new JPanel();
            jpnNBT.setPreferredSize(new Dimension(500,50));
            jpnNBT.setLayout(new FlowLayout(0,0,0));
            jpnNBT.setBackground(new Color(0,0,0,0));
            
            jpnNKT=new JPanel();
            jpnNKT.setPreferredSize(new Dimension(500,50));
            jpnNKT.setLayout(new FlowLayout(0,0,0));
            jpnNKT.setBackground(new Color(0,0,0,0));
            
            jpnBT=new JPanel();
            jpnBT.setPreferredSize(new Dimension(500,100));
            jpnBT.setLayout(new FlowLayout(2,0,0));
            jpnBT.setBackground(new Color(0,0,0,0));
            
            
            jpnTF1=new JPanel();
            jpnTF1.setPreferredSize(new Dimension(350,50));
            jpnTF1.setLayout(new FlowLayout(0,0,0));
            jpnTF1.setBackground(mainColor);
            
            jpnTF2=new JPanel();
            jpnTF2.setPreferredSize(new Dimension(350,50));
            jpnTF2.setLayout(new FlowLayout(0,0,0));
            jpnTF2.setBackground(mainColor);
            
            jpnTF3=new JPanel();
            jpnTF3.setPreferredSize(new Dimension(350,50));
            jpnTF3.setLayout(new FlowLayout(0,0,0));
            jpnTF3.setBackground(mainColor);
            
            jpnTF4=new JPanel();
            jpnTF4.setPreferredSize(new Dimension(350,50));
            jpnTF4.setLayout(new FlowLayout(0,0,0));
            jpnTF4.setBackground(mainColor);
            
            
            heading=new JLabel();
            heading.setPreferredSize(new Dimension(500,30));
            heading.setFont(new Font("Arial",Font.ITALIC,20));
            heading.setHorizontalAlignment(JLabel.CENTER);
            heading.setVerticalAlignment(JLabel.CENTER);
            heading.setBackground(new Color(0,0,0,0));
            heading.setOpaque(true);
            
            jlbMa=new JLabel("Mã KM:");
            jlbMa.setPreferredSize(new Dimension(150,50));
            jlbMa.setBackground(new Color(0,0,0,0));
            jlbMa.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbMa.setOpaque(true);
            
            jlbTen=new JLabel("Mã SP:");
            jlbTen.setPreferredSize(new Dimension(150,50));
            jlbTen.setBackground(new Color(0,0,0,0));
            jlbTen.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbTen.setOpaque(true);
            
            jlbNBT=new JLabel("Hình thức:");
            jlbNBT.setPreferredSize(new Dimension(150,50));
            jlbNBT.setBackground(new Color(0,0,0,0));
            jlbNBT.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbNBT.setOpaque(true);
            
            jlbNKT=new JLabel("Phần trăm:");
            jlbNKT.setPreferredSize(new Dimension(150,50));
            jlbNKT.setBackground(new Color(0,0,0,0));
            jlbNKT.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
            jlbNKT.setOpaque(true);
            
            
            jS1=new JSeparator();
            jS1.setPreferredSize(new Dimension(250,2));
            
            jS2=new JSeparator();
            jS2.setPreferredSize(new Dimension(250,2));
            
            jS3=new JSeparator();
            jS3.setPreferredSize(new Dimension(200,2));
            
            jS4=new JSeparator();
            jS4.setPreferredSize(new Dimension(250,2));
            
            
            
            jtMa=new JTextField();
            jtMa.setPreferredSize(new Dimension(250,48));
            jtMa.setBorder(null);
            jtMa.setFont(new Font("Arial",Font.PLAIN,18));
            jtMa.setBackground(new Color(0,0,0,0));
            //jtMa.setForeground(Color.white);
            //jtMa.setCaretColor(Color.red);
            jtMa.setOpaque(false);
            
            jtTen=new JTextField();
            jtTen.setPreferredSize(new Dimension(250,48));
            jtTen.setBorder(null);
            jtTen.setFont(new Font("Arial",Font.PLAIN,18));
            jtTen.setBackground(new Color(0,0,0,0));
           // jtTen.setForeground(Color.white);
            //jtTen.setCaretColor(Color.red);
            jtTen.setOpaque(false);
            
            
            String[] itemName={"Online","Mua trực tiếp"};
            jcb=new JComboBox(itemName);
            
            jcb.setPreferredSize(new Dimension(200,38));
            //jcb.setBorder(null);
            jcb.setFont(new Font("Arial",Font.PLAIN,18));
            //jcb.setBackground(new Color(0,0,0,0));
            //jcb.setForeground(Color.white);
            //jcb.setCaretColor(Color.red);
            //jcb.setOpaque(false);
            
            jtPT=new JTextField();
            jtPT.setPreferredSize(new Dimension(250,48));
            jtPT.setBorder(null);
            jtPT.setFont(new Font("Arial",Font.PLAIN,18));
            jtPT.setBackground(new Color(0,0,0,0));
           // jtPT.setForeground(Color.white);
            //jtPT.setCaretColor(Color.red);
            jtPT.setOpaque(false);
            
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
            
            jpnTF3.add(jcb);
            jpnTF3.add(jS3);
            
            
            jpnTF4.add(jtPT);
            jpnTF4.add(jS4);
            
            jpnMa.add(jlbMa);
            jpnMa.add(jpnTF1);
            
            jpnTen.add(jlbTen);
            jpnTen.add(jpnTF2);
            
            jpnNBT.add(jlbNBT);
            jpnNBT.add(jpnTF3);
            
            jpnNKT.add(jlbNKT);
            jpnNKT.add(jpnTF4);
            
            jpnBT.add(back);
            
            content.add(heading);
            content.add(jpnMa);   
            content.add(jpnTen);   
            content.add(jpnNBT);   
            content.add(jpnNKT); 
            content.add(jpnBT);
            
            add(content);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setVisible(true);
    }
    
    public void add() {
        jpnBT.add(add);
        heading.setText("Thêm sản phẩm vào khuyến mãi");
    }
    public void edit(int maKM, int maSP, String hinhThucKhuyenMai, int phanTramKhuyenMai) {
        jpnBT.add(edit);
        heading.setText("Sửa thông tin");
        jtMa.setText(Integer.toString(maKM));
        jtTen.setText(Integer.toString(maSP));
        //jcb.setText(hinhThucKhuyenMai);
        jtPT.setText(Integer.toString(phanTramKhuyenMai));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==back) {
            alter=false;
            dispose();            
        }
        if (e.getSource()==add) {
            //CTKMArr.add(Integer.parseInt(jtMa.getText()),jtTen.getText(),jcb.getText(),jtPT.getText());
            alter=true;
            dispose();
            JOptionPane.showMessageDialog(null,"Thêm thành công","Thêm nhà cung cấp",JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource()==edit) {
           // CTKMArr.edit(Integer.parseInt(jtMa.getText()),jtTen.getText(),jcb.getText(),jtPT.getText());
            alter=true;
            dispose();
            JOptionPane.showMessageDialog(null,"Sửa thành công","Sửa thông tin nhà cung cấp",JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public boolean getAlter() {
        return alter;
    }
    public void setAlter(boolean alter) {
        this.alter=alter;
    }
    
    public static void main(String[] args) {
        new InfoCTKM();
        
    }
    
    
}
