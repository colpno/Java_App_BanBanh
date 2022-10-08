
package application;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI_1 extends JFrame implements ActionListener,MouseListener {
    
    private JPanel pGui,pTop,pLeft,pLeftBottom,pLeftTop,pCenter,pT;
    
    private Color color1=Color.WHITE;
    private Color color2=new Color(47,50,60);
    private Color color3=new Color(78,115,223);
    private Color color4=new Color(34,80,214);
    
    private SanPhamPanel pSP;
    private HoaDonPanel pHD;
    private ThongKePanel pTK;
    private LoaiPanel pLoai;
    private JButton btSP,btLoai,btHD,btTK;
    
    Font f1=new Font(Font.SANS_SERIF,Font.BOLD,16);
    
    public GUI_1(){
        
        initComponents();
        
    }
    
    public void initComponents(){
        
        setLayout(new BorderLayout());
        pTop=myPanel(new FlowLayout(),color4);
        pTop.setPreferredSize(new Dimension(0,25));
        
        
        pLeft=myPanel(new BorderLayout(),color3);
        pLeft.setPreferredSize(new Dimension(200,0));
        
        pLeftTop=myPanel(new FlowLayout(FlowLayout.CENTER),color3);
        pLeftTop.setPreferredSize(new Dimension(0,100));
        pLeftBottom=myPanel(new FlowLayout(0,0,FlowLayout.CENTER),color3);
        
        
        pCenter=myPanel(new FlowLayout(FlowLayout.CENTER),color1);

        btSP=myButton("SẢN PHẨM",200,30,f1);
        btLoai=myButton("LOẠI",200,30,f1);
        btHD=myButton("HÓA ĐƠN",200,30,f1);
        btTK=myButton("THỐNG KÊ",200,30,f1);
        
        pLeftBottom.add(btSP);
        pLeftBottom.add(btLoai);
        pLeftBottom.add(btHD);
        pLeftBottom.add(btTK);
        
        pLeft.add(pLeftTop,BorderLayout.NORTH);
        pLeft.add(pLeftBottom,BorderLayout.CENTER);
        
        add(pTop,BorderLayout.NORTH);
        add(pLeft,BorderLayout.WEST);
        add(pCenter,BorderLayout.CENTER);
        

        setSize(1300,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    public JPanel myPanel(LayoutManager layout,Color color){
        JPanel p=new JPanel();
        p.setLayout(layout);
        p.setBackground(color);
        return p;
    }
    public JButton myButton(String name,int width,int height,Font f){
        JButton btn=new JButton(name);
        btn.setPreferredSize(new Dimension(width,height));
        btn.setFont(f);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color3);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.WHITE));
        btn.addActionListener(this);
        btn.addMouseListener(this);
        return btn;
    }

    public JButton btHoverEnter(JButton bt){
        bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt.setContentAreaFilled(false);
        bt.setBackground(color4);
        bt.setOpaque(true);
        return bt;
    }
    
    public JButton btHoverExit(JButton bt){
        bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt.setContentAreaFilled(false);
        bt.setBackground(color3);
        bt.setOpaque(true);
        return bt;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btSP){
            pCenter.removeAll();
            pCenter.repaint();
            pCenter.validate();
            try {
                pSP=new SanPhamPanel();
            } catch (Exception ex) {
                Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
            }
            pCenter.add(pSP);
            pCenter.repaint();
            pCenter.validate();
        }
        if(e.getSource()==btLoai){
            pCenter.removeAll();
            pCenter.repaint();
            pCenter.validate();
            
            pLoai=new LoaiPanel();
            
            pCenter.add(pLoai);
            pCenter.repaint();
            pCenter.validate();
        }
        
        if(e.getSource()==btHD){
            pCenter.removeAll();
            pCenter.repaint();
            pCenter.validate();

            pHD=new HoaDonPanel();

            pCenter.add(pHD);
            pCenter.repaint();
            pCenter.validate();
        }
        if(e.getSource()==btTK){
            pCenter.removeAll();
            pCenter.repaint();
            pCenter.validate();

            pTK=new ThongKePanel();

            pCenter.add(pTK);
            pCenter.repaint();
            pCenter.validate();
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==btSP){
            btSP=btHoverEnter(btSP);
        }
        if(e.getSource()==btLoai){
            btLoai=btHoverEnter(btLoai);
        }
        if(e.getSource()==btHD){
            btHD=btHoverEnter(btHD);
        }
        if(e.getSource()==btTK){
            btTK=btHoverEnter(btTK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==btSP){
            btSP=btHoverExit(btSP);
        }
        if(e.getSource()==btLoai){
            btLoai=btHoverExit(btLoai);
        }
        if(e.getSource()==btHD){
            btHD=btHoverExit(btHD);
        }
        if(e.getSource()==btTK
                ){
            btTK=btHoverExit(btTK);
        }
    }
    
    public static void main(String[] args){
        new GUI_1();
    }
    
}
