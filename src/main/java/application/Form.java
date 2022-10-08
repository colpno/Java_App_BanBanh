
package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Form extends JFrame implements MouseListener{
        private JPanel leftMenu,content,top,topRight,topLeft;
        private JLabel logo,menu1,menu2,menu3,menu4,fullS,miniS,exit,hide,logoC;
        private ImageIcon iconL,iconD,iconF,iconMF,iconH;
        Form () {
            setSize(1316,739);
            setLayout(new BorderLayout());
            
            //setUndecorated(true);
            
            iconL=new ImageIcon("image/icons8_malwarebytes_30px.png");
            iconD=new ImageIcon("image/icons8_delete_20px.png");
            iconF=new ImageIcon("image/icons8_unchecked_checkbox_20px.png");
            iconMF=new ImageIcon("image/icons8_restore_down_20px.png");
            iconH=new ImageIcon("image/icons8_minus_20px_1.png");

            top=new JPanel();
            top.setPreferredSize(new Dimension(0,36));
            top.setLayout(new BorderLayout());
            top.setBackground(Color.white);

            topRight=new JPanel();
            topRight.setPreferredSize(new Dimension(180,0));
            topRight.setBackground(Color.white);
            topRight.setLayout(new BorderLayout());

            topLeft=new JPanel();
            topLeft.setPreferredSize(new Dimension(200,0));
            topLeft.setBackground(Color.white);
            topLeft.setLayout(new FlowLayout(0,0,0));

            fullS = new JLabel();
            fullS.setIcon(iconF);
            fullS.setPreferredSize(new Dimension(60,36));
            fullS.setBackground(Color.white);
            fullS.setHorizontalAlignment(JLabel.CENTER);
            fullS.setOpaque(true);
            fullS.addMouseListener(this);

            miniS=new JLabel();
            miniS.setIcon(iconMF);
            miniS.setPreferredSize(new Dimension(60,36));
            miniS.setBackground(Color.white);
            miniS.setHorizontalAlignment(JLabel.CENTER);
            miniS.setOpaque(true);
            miniS.addMouseListener(this);

            exit=new JLabel();
            exit.setIcon(iconD);
            exit.setPreferredSize(new Dimension(60,36));
            exit.setBackground(Color.white);
            exit.setHorizontalAlignment(JLabel.CENTER);
            exit.setOpaque(true);
            exit.addMouseListener(this);

            hide=new JLabel();
            hide.setIcon(iconH);
            hide.setPreferredSize(new Dimension(60,36));
            hide.setBackground(Color.white);
            hide.setHorizontalAlignment(JLabel.CENTER);
            hide.setOpaque(true);
            hide.addMouseListener(this);

            logoC=new JLabel("Mun");;
            logoC.setIcon(iconL);
            logoC.setPreferredSize(new Dimension(200,36));
            logoC.setVerticalAlignment(JLabel.CENTER);
            logoC.setFont(new Font("Arial",Font.BOLD,22));
            logoC.setBackground(Color.white);
            logoC.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
            logoC.setIconTextGap(20);
            logoC.setOpaque(true);
            logoC.addMouseListener(this);

            
            
            leftMenu=new JPanel();
            leftMenu.setPreferredSize(new Dimension(250,700));
            leftMenu.setBackground(new Color(0xfc498e));
            leftMenu.setLayout(new FlowLayout(0,0,0));
            
            content=new JPanel();
            content.setLayout(new BorderLayout());
            content.setBorder(BorderFactory.createLineBorder(Color.black));
            
            logo=new JLabel("Logo");
            logo.setPreferredSize(new Dimension(250,50));
            logo.setFont(new Font("Arial",Font.BOLD,30));
            logo.setBackground(Color.blue);
            logo.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            logo.setOpaque(true);
            
            menu1=new JLabel("Trang chủ");
            menu1.setPreferredSize(new Dimension(250,50));
            menu1.setVerticalAlignment(JLabel.CENTER);
            menu1.setFont(new Font("Arial",Font.BOLD,14));
            menu1.setBackground(Color.green);
            menu1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            menu1.addMouseListener(this);
            menu1.setOpaque(true);
            
            menu2=new JLabel("Khuyến mãi");
            menu2.setPreferredSize(new Dimension(250,50));
            menu2.setVerticalAlignment(JLabel.CENTER);
            menu2.setFont(new Font("Arial",Font.BOLD,14));
            menu2.setBackground(Color.pink);
            menu2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            menu2.addMouseListener(this);
            menu2.setOpaque(true);
            
            menu3=new JLabel("Nhà cung cấp");
            menu3.setPreferredSize(new Dimension(250,50));
            menu3.setVerticalAlignment(JLabel.CENTER);
            menu3.setFont(new Font("Arial",Font.BOLD,14));
            menu3.setBackground(Color.gray);
            menu3.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            menu3.addMouseListener(this);
            menu3.setOpaque(true);
            
            menu4=new JLabel("Nhà sản xuất");
            menu4.setPreferredSize(new Dimension(250,50));
            menu4.setVerticalAlignment(JLabel.CENTER);
            menu4.setFont(new Font("Arial",Font.BOLD,14));
            menu4.setBackground(Color.yellow);
            menu4.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            menu4.addMouseListener(this);
            menu4.setOpaque(true);
            
            topRight.add(hide,BorderLayout.WEST);
            topRight.add(fullS,BorderLayout.CENTER);
            topRight.add(exit,BorderLayout.EAST);

            topLeft.add(logoC);

            top.add(topRight,BorderLayout.EAST);
            top.add(topLeft,BorderLayout.WEST);
            
            leftMenu.add(logo);
            leftMenu.add(menu1);
            leftMenu.add(menu2);
            leftMenu.add(menu3);
            leftMenu.add(menu4);
            
            //add(top,BorderLayout.NORTH);
            add(leftMenu,BorderLayout.WEST);
            add(content,BorderLayout.CENTER);
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }
        public static void main(String[] args) {
            Form x=new Form();
            x.setVisible(true);
        }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==fullS) {
            topRight.remove(fullS);
            setExtendedState(MAXIMIZED_BOTH);
            topRight.add(miniS);
        }
        if (e.getSource()==miniS) {
            topRight.remove(miniS);
            setSize(1300,736);
            setLocationRelativeTo(null);
            topRight.add(fullS);
        }
        if (e.getSource()==exit) {
            dispose();
        }
        if (e.getSource()==hide) {
            setState(ICONIFIED);
        }
        if (e.getSource()==menu2) {
            content.removeAll();
            content.repaint();
            content.validate();
            
            content.add(new KhuyenMaiPanel(),BorderLayout.CENTER);
            //content.add(new InfoKM(),BorderLayout.CENTER);
            
            content.repaint();
            content.validate();
        }
        if (e.getSource()==menu3) {
            content.removeAll();
            content.repaint();
            content.validate();
            
            content.add(new NhaCungCapPanel(),BorderLayout.CENTER);
            
            content.repaint();
            content.validate();
        }
        if (e.getSource()==menu4) {
            content.removeAll();
            content.repaint();
            content.validate();
            
            content.add(new NhaSanXuatPanel(),BorderLayout.CENTER);
            
            content.repaint();
            content.validate();
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
        if (e.getSource()==hide) {
            hide.setBackground(new Color(0xB4B4B4));
        }
        if (e.getSource()==fullS) {
            fullS.setBackground(new Color(0xB4B4B4));
        }
        if (e.getSource()==miniS) {
            miniS.setBackground(new Color(0xB4B4B4));
        }
        if (e.getSource()==exit) {
            exit.setBackground(new Color(0xFF4F55));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==hide) {
            hide.setBackground(Color.white);
        }
        if (e.getSource()==fullS) {
            fullS.setBackground(Color.white);
        }
        if (e.getSource()==miniS) {
            miniS.setBackground(Color.white);
        }
        if (e.getSource()==exit) {
            exit.setBackground(Color.white);
        }
    }
}
