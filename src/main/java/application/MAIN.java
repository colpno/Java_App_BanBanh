package application;

import test.InputTester;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MAIN extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel leftJPanel = new JPanel();
    private JPanel leftTopJPanel = new JPanel();
    private JPanel leftBottomJPanel = new JPanel();
    private JPanel rightJPanel = new JPanel();
    private JButton btnNV = new JButton("Nhan vien");
    private JButton btnKH = new JButton("Khach hang");
    private JButton btnPNH = new JButton("Phieu nhap hang");
    private JButton btnSP = new JButton("San Pham");
    private JButton btnKM = new JButton("Khuyen Mai");
    private JButton btnL = new JButton("Loai");
    private JButton btnHD = new JButton("Hoa Don");
    private JButton btnNSX = new JButton("Nha San Xuat");
    private JButton btnNCC = new JButton("Nha Cung Cap");
    private JButton btnTK = new JButton("Thong Ke");

    private JLabel studentName = new JLabel("Admin", JLabel.CENTER);

    private DangNhap dangNhap = new DangNhap();
    private DangKyPanel dangKy = new DangKyPanel();
    private NhanVienPanel NVPanel = new NhanVienPanel();
    private KhachHangPanel KHPanel = new KhachHangPanel();
    private PhieuNhapHangPanel PNHPanel = new PhieuNhapHangPanel();
    private SanPhamPanel SPPanel = new SanPhamPanel();
    private KhuyenMaiPanel KMPanel = new KhuyenMaiPanel();
    private LoaiPanel LPanel = new LoaiPanel();
    private HoaDonPanel HDPanel = new HoaDonPanel();
    private NhaSanXuatPanel NSXPanel = new NhaSanXuatPanel();
    private NhaCungCapPanel NCCPanel = new NhaCungCapPanel();
    private ThongKePanel TKPanel = new ThongKePanel();

    MAIN(String title, int height, int width) {
        setTitle(title);
        setSize(height, width);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {

        studentName.setFont(new Font("Times New Roman", 1, 24));
        studentName.setForeground(Color.WHITE);

        leftTopJPanel.setBackground(new Color(0xfa5973));
        leftTopJPanel.setPreferredSize(new Dimension(200, 100));
        leftTopJPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        leftTopJPanel.setLayout(new GridBagLayout());
        leftTopJPanel.add(studentName);

        leftBottomJPanel.setPreferredSize(new Dimension(200, 600));
        leftBottomJPanel.setBackground(new Color(0xfa5973));
        leftBottomJPanel.setLayout(new FlowLayout());
        leftBottomJPanel.add(btnNV);
        leftBottomJPanel.add(btnKH);
        leftBottomJPanel.add(btnPNH);
        leftBottomJPanel.add(btnSP);
        leftBottomJPanel.add(btnKM);
        leftBottomJPanel.add(btnL);
        leftBottomJPanel.add(btnHD);
        leftBottomJPanel.add(btnNSX);
        leftBottomJPanel.add(btnNCC);
        leftBottomJPanel.add(btnTK);

        leftJPanel.setPreferredSize(new Dimension(200, 0));
        leftJPanel.setBackground(new Color(0xfa5973));

        leftJPanel.add(leftTopJPanel);
        leftJPanel.add(leftBottomJPanel);

        rightJPanel.setLayout(new CardLayout());
        rightJPanel.setPreferredSize(new Dimension(680, 0));
        setSizeOfButton(195, 25, btnNV, btnKH, btnPNH, btnSP, btnKM, btnL, btnHD, btnNSX, btnNCC, btnTK);
        btnNV.addActionListener(this);
        btnKH.addActionListener(this);
        btnPNH.addActionListener(this);
        btnSP.addActionListener(this);
        btnKM.addActionListener(this);
        btnL.addActionListener(this);
        btnHD.addActionListener(this);
        btnNSX.addActionListener(this);
        btnNCC.addActionListener(this);
        btnTK.addActionListener(this);

        add(leftJPanel, BorderLayout.WEST);
        add(rightJPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object option = e.getSource();
        if (option == btnNV) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            NVPanel = new NhanVienPanel();
            rightJPanel.add(NVPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        NVPanel.frameMaximized();
                    } else {
                        NVPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnKH) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            KHPanel = new KhachHangPanel();
            rightJPanel.add(KHPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        KHPanel.frameMaximized();
                    } else {
                        KHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnPNH) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            PNHPanel = new PhieuNhapHangPanel();
            rightJPanel.add(PNHPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnSP) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            SPPanel = new SanPhamPanel();
            rightJPanel.add(SPPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnKM) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            KMPanel = new KhuyenMaiPanel();
            rightJPanel.add(KMPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnL) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            LPanel = new LoaiPanel();
            rightJPanel.add(LPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnHD) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            HDPanel = new HoaDonPanel();
            rightJPanel.add(HDPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnNSX) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            NSXPanel = new NhaSanXuatPanel();
            rightJPanel.add(NSXPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnNCC) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            NCCPanel = new NhaCungCapPanel();
            rightJPanel.add(NCCPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
        if (option == btnTK) {
            rightJPanel.removeAll();
            rightJPanel.repaint();
            rightJPanel.validate();

            TKPanel = new ThongKePanel();
            rightJPanel.add(TKPanel);
            rightJPanel.repaint();
            rightJPanel.validate();

            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
                        PNHPanel.frameMaximized();
                    } else {
                        PNHPanel.frameRestore();
                    }
                }
            });
        }
    }

    public void setSizeOfButton(int width, int height, JButton... arg) {
        for (JButton btn : arg) {
            btn.setPreferredSize(new Dimension(width, height));
        }
        return;
    }

    public static void main(String[] args) {
        MAIN g = new MAIN("test", 1200, 800);
        g.setVisible(true);
    }
}
