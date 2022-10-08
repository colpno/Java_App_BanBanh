package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

import businesslayer.ChiTietPhieuNhapHang;
import businesslayer.ConfigTable;
import businesslayer.DungChung;
import businesslayer.MangChiTietPhieuNhapHang;
import businesslayer.MangNhaCungCap;
import businesslayer.MangNhanVien;
import businesslayer.MangPhieuNhapHang;
import businesslayer.MangSanPham;
import businesslayer.NhaCungCap;
import businesslayer.NhanVien;
import businesslayer.PhieuNhapHang;
import businesslayer.SanPham;
import test.InputTester;

public class PhieuNhapHangPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private JLabel jlbMaPhieuPNH, jlbMaNCC, jlbMaNV, jlbNgayNhap, jlbMaSP, jlbSoLuong, jlbDonGiaGoc, jlbThanhTien,
            jlbTrang, jlbThem, jlbSua, jlbTimKiem, jlbNext, jlbPrev, jlbFirst, jlbLast, jlbOk, jlbCancel, jlbLocTu,
            jlbLocDen, jlbThongKeHeader, jlbMore, jlbMoreNext, jlbMorePrev, jlbMoreFirst, jlbMoreLast, jlbMoreTrang;
    private JTextField jtfMaPhieuPNH, jtfMaNCC, jtfMaSP, jtfSoLuong, jtfDonGiaGoc, jtfThanhTien, jtfLocTu, jtfLocDen,
            jtfTim, jtfMaNV, jtfMore;
    private JDateChooser jdcNgayNhap;
    private JButton jbtXemPhieu, jbtXemChiTiet, jbtLoc, jbtGhiExcel, jbtDocExcel, jbtSapXep, jbtThongKe, jbtMoreMaNCC,
            jbtMoreMaNV, jbtMoreMaSP, jbtExportPDF;
    private Icon themIcon, suaIcon, xoaIcon, timIcon, firstIcon, prevIcon, nextIcon, lastIcon, filterIcon, okIcon,
            cancelIcon, importIcon, exportIcon, sortIcon, thongKeIcon, pdfIcon;
    private JScrollPane jscpPhieuNhapHang, jscpChiTietPhieuNhapHang, jscpThongKe, jscpMore;
    private JTable jtbPhieuNhapHang, jtbChiTietPhieuNhapHang, jtbThongKe, jtbMaNV, jtbMaNCC, jtbMaSP;
    private DefaultTableModel model, jmdThongKe, jmdMore;
    private JComboBox<String> jcbLoc, jcbTim, jcbCotSapXep, jcbKieuSapXep, jcbMore;
    private JPanel jpnLocDen, jpnLocTu, jpnLoc, jpnPhanTrang, jpnTim, jpnTrangTim, jpnLeftInput, jpnRightInput,
            jpnCommand, jpnSapXep, jpnThongKeVaPhanTrang, jpnThongKeHeader, jpnMoreHeader, jpnMaNCC, jpnMaSP, jpnMaNV,
            jpnMoreFooter;
    private JFrame jfThongKe, jfMore;
    private JSeparator jspLoc1, jspLoc2;

    private EtchedBorder etchedBorder = new EtchedBorder(EtchedBorder.LOWERED, new Color(0xe2e2e2),
            new Color(0xe2e2e2));
    private Color mainColor = new Color(0xfc498e);

    private MangPhieuNhapHang PNHArr = new MangPhieuNhapHang();
    private MangChiTietPhieuNhapHang CTPNHArr = new MangChiTietPhieuNhapHang();
    private MangNhaCungCap NCCArr = new MangNhaCungCap();
    private MangNhanVien NVArr = new MangNhanVien();
    private MangSanPham SPArr = new MangSanPham();
    private String[] PNHtitles = PNHArr.title();
    private String[] CTPNHtitles = CTPNHArr.title();
    private int soRowMoiTrang = 12;
    private int soRowMore = 14;
    private int soRowMoreSP = 6;
    private int soRowToiDaMoiTrang = 18;

    public PhieuNhapHangPanel() {
        initComponents();
    }

    private void initComponents() {

        jmdThongKe = new DefaultTableModel();
        jmdMore = new DefaultTableModel();

        jlbMaPhieuPNH = new JLabel("Mã phiếu");
        jlbMaNCC = new JLabel("Mã nhà cung cấp");
        jlbMaNV = new JLabel("Mã nhân viên");
        jlbNgayNhap = new JLabel("Ngày nhập");
        jlbTrang = new JLabel("1");
        jlbMaSP = new JLabel("Mã sản phẩm");
        jlbSoLuong = new JLabel("Số lượng");
        jlbDonGiaGoc = new JLabel("Đơn giá gốc");
        jlbThanhTien = new JLabel("Thành tiền");
        jlbLocTu = new JLabel("Từ  ");
        jlbLocDen = new JLabel("Đến");
        jlbThongKeHeader = new JLabel("Thống kê chi phí nhập hàng");
        jlbMoreTrang = new JLabel("1");

        jtfMaPhieuPNH = new JTextField();
        jtfMaNCC = new JTextField();
        jdcNgayNhap = new JDateChooser();
        jtfMaSP = new JTextField();
        jtfSoLuong = new JTextField();
        jtfDonGiaGoc = new JTextField();
        jtfThanhTien = new JTextField();
        jtfLocTu = new JTextField();
        jtfLocDen = new JTextField();
        jtfTim = new JTextField();
        jtfMore = new JTextField();
        jtfMaNV = new JTextField();

        themIcon = new ImageIcon("image/add.png");
        suaIcon = new ImageIcon("image/edit.png");
        xoaIcon = new ImageIcon("image/delete.png");
        timIcon = new ImageIcon("image/search.png");
        firstIcon = new ImageIcon("image/first.png");
        prevIcon = new ImageIcon("image/prev.png");
        nextIcon = new ImageIcon("image/next.png");
        lastIcon = new ImageIcon("image/last.png");
        filterIcon = new ImageIcon("image/filter.png");
        okIcon = new ImageIcon("image/ok.png");
        cancelIcon = new ImageIcon("image/cancel.png");
        importIcon = new ImageIcon("image/import.png");
        exportIcon = new ImageIcon("image/export.png");
        sortIcon = new ImageIcon("image/sort.png");
        thongKeIcon = new ImageIcon("image/sort.png");
        pdfIcon = new ImageIcon("image/pdf_18px.png");

        jlbThem = new JLabel(themIcon);
        jlbSua = new JLabel(suaIcon);
        jlbTimKiem = new JLabel(timIcon);
        jlbNext = new JLabel(nextIcon);
        jlbPrev = new JLabel(prevIcon);
        jlbFirst = new JLabel(firstIcon);
        jlbLast = new JLabel(lastIcon);
        jlbOk = new JLabel(okIcon);
        jlbCancel = new JLabel(cancelIcon);
        jlbMore = new JLabel(timIcon);
        jlbMoreNext = new JLabel(nextIcon);
        jlbMorePrev = new JLabel(prevIcon);
        jlbMoreFirst = new JLabel(firstIcon);
        jlbMoreLast = new JLabel(lastIcon);

        jbtXemPhieu = new JButton("Xem phiếu nhập hàng");
        jbtXemChiTiet = new JButton("Xem chi tiết phiếu nhập hàng");
        jbtLoc = new JButton("Lọc", filterIcon);
        jbtGhiExcel = new JButton("Ghi Excel", exportIcon);
        jbtDocExcel = new JButton("Đọc Excel", importIcon);
        jbtExportPDF = new JButton("Export PDF", pdfIcon);
        jbtSapXep = new JButton("Sắp xếp", sortIcon);
        jbtThongKe = new JButton("Thống kê", thongKeIcon);
        jbtMoreMaNCC = new JButton("...");
        jbtMoreMaNV = new JButton("...");
        jbtMoreMaSP = new JButton("...");

        jcbLoc = new JComboBox<>();
        jcbTim = new JComboBox<>();
        jcbCotSapXep = new JComboBox<>();
        jcbKieuSapXep = new JComboBox<>();
        jcbMore = new JComboBox<String>();

        jscpPhieuNhapHang = new JScrollPane();
        jscpChiTietPhieuNhapHang = new JScrollPane();
        jscpThongKe = new JScrollPane();
        jscpMore = new JScrollPane();

        jtbPhieuNhapHang = new JTable();
        jtbChiTietPhieuNhapHang = new JTable();
        jtbThongKe = new JTable(jmdThongKe);
        jtbMaNV = new JTable();
        jtbMaNCC = new JTable();
        jtbMaSP = new JTable();

        jpnLocDen = new JPanel();
        jpnLocTu = new JPanel();
        jpnLoc = new JPanel();
        jpnTim = new JPanel();
        jpnPhanTrang = new JPanel();
        jpnTrangTim = new JPanel();
        jpnLeftInput = new JPanel();
        jpnRightInput = new JPanel();
        jpnCommand = new JPanel();
        jpnSapXep = new JPanel();
        jpnThongKeVaPhanTrang = new JPanel();
        jpnThongKeHeader = new JPanel();
        jpnMoreHeader = new JPanel();
        jpnMoreFooter = new JPanel();
        jpnMaNCC = new JPanel();
        jpnMaNV = new JPanel();
        jpnMaSP = new JPanel();

        jfThongKe = new JFrame();
        jfMore = new JFrame();

        jspLoc1 = new JSeparator();
        jspLoc2 = new JSeparator();

        /*
         * SELF
         */
        setBackground(new Color(0xf1f5f9));

        /*
         * JScrollPane && JTable
         */
        getTableData(soRowMoiTrang, 1);

        jtbPhieuNhapHang.setPreferredScrollableViewportSize(jtbPhieuNhapHang.getPreferredSize());
        jtbPhieuNhapHang.setFillsViewportHeight(true);
        jscpPhieuNhapHang.setViewportView(jtbPhieuNhapHang);

        jtbChiTietPhieuNhapHang.setPreferredScrollableViewportSize(jtbChiTietPhieuNhapHang.getPreferredSize());
        jtbChiTietPhieuNhapHang.setFillsViewportHeight(true);
        jscpChiTietPhieuNhapHang.setViewportView(jtbChiTietPhieuNhapHang);
        jscpChiTietPhieuNhapHang.setVisible(false);

        jtbThongKe.setPreferredScrollableViewportSize(jtbThongKe.getPreferredSize());
        jtbThongKe.setFillsViewportHeight(true);
        jscpThongKe.setViewportView(jtbThongKe);

        jtbMaNV.setPreferredScrollableViewportSize(jtbMaNV.getPreferredSize());
        jtbMaNV.setFillsViewportHeight(true);

        /*
         * JComboBox
         */
        for (int i = 0; i < PNHtitles.length; i++) {
            if (i != 0 && i != PNHtitles.length - 1) {
                jcbLoc.addItem(PNHtitles[i]);
                jcbTim.addItem(PNHtitles[i]);
            }
        }

        jcbLoc.setBackground(Color.WHITE);
        jcbTim.setBackground(Color.WHITE);

        for (int i = 0; i < PNHtitles.length; i++) {
            if (i != 0 && i != PNHtitles.length - 1) {
                jcbCotSapXep.addItem(PNHtitles[i]);
            }
        }
        jcbCotSapXep.setBackground(Color.WHITE);
        jcbCotSapXep.setBorder(new EmptyBorder(4, 10, 0, 10));

        jcbKieuSapXep.setBackground(Color.WHITE);
        jcbKieuSapXep.setBorder(new EmptyBorder(4, 10, 4, 10));
        jcbKieuSapXep.addItem("Tăng dần");
        jcbKieuSapXep.addItem("Giảm dần");

        jcbMore.setBackground(Color.WHITE);

        /*
         * JButton
         */
        jbtXemPhieu.setEnabled(false);
        jbtXemPhieu.setBackground(new Color(240, 240, 240));

        jbtXemChiTiet.setBackground(mainColor);
        jbtXemChiTiet.setForeground(Color.WHITE);

        jbtSapXep.setBackground(mainColor);
        jbtSapXep.setForeground(Color.WHITE);
        jbtSapXep.setPreferredSize(new Dimension(100, 25));

        jbtDocExcel.setBackground(mainColor);
        jbtDocExcel.setForeground(Color.WHITE);

        jbtGhiExcel.setBackground(mainColor);
        jbtGhiExcel.setForeground(Color.WHITE);

        jbtExportPDF.setBackground(mainColor);
        jbtExportPDF.setForeground(Color.WHITE);

        jbtLoc.setBackground(mainColor);
        jbtLoc.setForeground(Color.WHITE);

        jbtThongKe.setBackground(mainColor);
        jbtThongKe.setForeground(Color.WHITE);

        jbtMoreMaNCC.setPreferredSize(new Dimension(20, 25));

        jbtMoreMaNV.setPreferredSize(new Dimension(20, 25));

        jbtMoreMaSP.setPreferredSize(new Dimension(20, 25));
        jbtMoreMaSP.setEnabled(false);

        /*
         * JSeparate
         */
        jspLoc1.setPreferredSize(new Dimension(10, 30));
        jspLoc1.setOrientation(SwingConstants.VERTICAL);
        jspLoc2.setPreferredSize(new Dimension(10, 30));
        jspLoc2.setOrientation(SwingConstants.VERTICAL);

        /*
         * JRame
         */
        jfThongKe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jfThongKe.setVisible(false);
        jfThongKe.setTitle("Thống kê");
        jfThongKe.setSize(1000, 600);
        jfThongKe.setLayout(new BorderLayout());
        jfThongKe.add(jpnThongKeHeader, BorderLayout.NORTH);
        jfThongKe.add(jscpThongKe, BorderLayout.CENTER);

        jfMore.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jfMore.setVisible(false);
        jfMore.setSize(1000, 500);
        jfMore.setLayout(new BorderLayout());
        jfMore.add(jpnMoreHeader, BorderLayout.NORTH);
        jfMore.add(jscpMore, BorderLayout.CENTER);
        jfMore.add(jpnMoreFooter, BorderLayout.SOUTH);

        /*
         * JPanel
         */
        jpnLocTu.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpnLocTu.add(jlbLocTu);
        jpnLocTu.add(jtfLocTu);
        jpnLocTu.add(jspLoc1);
        jpnLocTu.add(jcbLoc);
        jpnLocTu.setBackground(Color.WHITE);

        jpnLocDen.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpnLocDen.add(jlbLocDen);
        jpnLocDen.add(jtfLocDen);
        jpnLocDen.add(jspLoc2);
        jpnLocDen.add(jbtLoc);
        jpnLocDen.setBackground(Color.WHITE);

        jpnLoc.setLayout(new BoxLayout(jpnLoc, BoxLayout.Y_AXIS));
        jpnLoc.add(jpnLocTu);
        jpnLoc.add(jpnLocDen);
        jpnLoc.setBackground(Color.WHITE);
        jpnLoc.setBorder(etchedBorder);

        jpnTim.add(jcbTim);
        jpnTim.add(jtfTim);
        jpnTim.add(jlbTimKiem);
        jpnTim.setBackground(Color.WHITE);

        jpnPhanTrang.add(jlbFirst);
        jpnPhanTrang.add(jlbPrev);
        jpnPhanTrang.add(jlbTrang);
        jpnPhanTrang.add(jlbNext);
        jpnPhanTrang.add(jlbLast);
        jpnPhanTrang.setBackground(Color.WHITE);

        jpnTrangTim.add(jpnTim);
        jpnTrangTim.add(jpnThongKeVaPhanTrang);
        jpnTrangTim.setBorder(etchedBorder);
        jpnTrangTim.setBackground(Color.WHITE);
        jpnTrangTim.setLayout(new BoxLayout(jpnTrangTim, BoxLayout.Y_AXIS));

        jpnLeftInput.setBackground(Color.WHITE);
        jpnRightInput.setBackground(Color.WHITE);

        jpnCommand.setBackground(Color.WHITE);
        jpnCommand.setLayout(new GridBagLayout());
        jpnCommand.setBorder(etchedBorder);
        jpnCommand.add(jlbThem);
        jpnCommand.add(jlbOk);
        jpnCommand.add(jlbCancel);

        jpnSapXep.setBackground(Color.WHITE);
        jpnSapXep.setLayout(new BoxLayout(jpnSapXep, BoxLayout.Y_AXIS));
        jpnSapXep.setBorder(etchedBorder);
        jpnSapXep.add(jcbCotSapXep);
        jpnSapXep.add(jcbKieuSapXep);
        jpnSapXep.add(jbtSapXep);

        jpnLeftInput.setBorder(etchedBorder);
        jpnRightInput.setBorder(etchedBorder);

        jpnThongKeVaPhanTrang.add(jbtThongKe);
        jpnThongKeVaPhanTrang.add(jpnPhanTrang);
        jpnThongKeVaPhanTrang.setBackground(Color.WHITE);

        jpnThongKeHeader.add(jlbThongKeHeader);
        jpnThongKeHeader.setBackground(Color.WHITE);

        jpnMoreHeader.add(jcbMore);
        jpnMoreHeader.add(jtfMore);
        jpnMoreHeader.add(jlbMore);
        jpnMoreHeader.setBackground(Color.WHITE);

        jpnMoreFooter.add(jlbMoreFirst);
        jpnMoreFooter.add(jlbMorePrev);
        jpnMoreFooter.add(jlbMoreTrang);
        jpnMoreFooter.add(jlbMoreNext);
        jpnMoreFooter.add(jlbMoreLast);
        jpnMoreFooter.setBackground(Color.WHITE);

        jpnMaSP.add(jtfMaSP);
        jpnMaSP.add(jbtMoreMaSP);
        jpnMaSP.setBackground(Color.WHITE);
        jpnMaSP.setBorder(new EmptyBorder(-5, -5, -5, -5));

        jpnMaNCC.add(jtfMaNCC);
        jpnMaNCC.add(jbtMoreMaNCC);
        jpnMaNCC.setBackground(Color.WHITE);
        jpnMaNCC.setBorder(new EmptyBorder(-5, -5, -5, -5));

        jpnMaNV.add(jtfMaNV);
        jpnMaNV.add(jbtMoreMaNV);
        jpnMaNV.setBackground(Color.WHITE);
        jpnMaNV.setBorder(new EmptyBorder(-5, -5, -5, -5));

        /*
         * JTextField
         */
        jtfLocTu.setPreferredSize(new Dimension(100, 25));
        jtfLocDen.setPreferredSize(new Dimension(100, 25));

        jtfMaPhieuPNH.setEnabled(false);
        jtfMaPhieuPNH.setBackground(new Color(245, 245, 245));
        jtfMaPhieuPNH.setDisabledTextColor(Color.BLACK);

        jtfTim.setPreferredSize(new Dimension(300, 25));

        jtfMaSP.setEditable(false);

        jtfSoLuong.setEditable(false);

        jtfDonGiaGoc.setEditable(false);

        jtfThanhTien.setEditable(false);

        jtfMaNCC.setPreferredSize(new Dimension(265, 25));

        jtfMaNV.setPreferredSize(new Dimension(265, 25));

        jtfMaSP.setPreferredSize(new Dimension(265, 25));

        jtfMore.setPreferredSize(new Dimension(300, 30));

        jtfMaNV.setBackground(Color.WHITE);

        /*
         * JDateChooser
         */
        jdcNgayNhap.setDateFormatString("dd/MM/yyyy");
        // JTextFieldDateEditor editor = (JTextFieldDateEditor)
        // jdcNgayNhap.getDateEditor();
        // editor.setEditable(false);
        // editor.setBackground(Color.WHITE);

        /*
         * JLabel
         */
        jlbSua.setVisible(false);
        jlbOk.setVisible(false);
        jlbCancel.setVisible(false);

        jlbMaPhieuPNH.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbMaNCC.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbMaNV.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbNgayNhap.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbTrang.setFont(new java.awt.Font("Tahoma", 0, 18));

        jlbMaSP.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbMaSP.setForeground(Color.LIGHT_GRAY);

        jlbDonGiaGoc.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbDonGiaGoc.setForeground(Color.LIGHT_GRAY);

        jlbThanhTien.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbThanhTien.setForeground(Color.LIGHT_GRAY);

        jlbSoLuong.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbSoLuong.setForeground(Color.LIGHT_GRAY);

        jlbThongKeHeader.setFont(new java.awt.Font("Tahoma", 0, 24));
        jlbThongKeHeader.setForeground(Color.LIGHT_GRAY);

        /*
         * Set cursor
         */
        jlbThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbFirst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbPrev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtXemPhieu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtXemChiTiet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtSapXep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtDocExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtGhiExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtExportPDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtLoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtMoreMaNCC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtMoreMaNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtMoreMaSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMoreFirst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMorePrev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMoreNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMoreLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc;
        /*
         * LEFT INPUT
         */
        jpnLeftInput.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 0);
        jpnLeftInput.add(jlbMaPhieuPNH, gbc);

        jtfMaPhieuPNH.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnLeftInput.add(jtfMaPhieuPNH, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnLeftInput.add(jlbMaNCC, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        jpnLeftInput.add(jpnMaNCC, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnLeftInput.add(jlbMaNV, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 10);
        jpnLeftInput.add(jpnMaNV, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        jpnLeftInput.add(jlbNgayNhap, gbc);

        jdcNgayNhap.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnLeftInput.add(jdcNgayNhap, gbc);

        /*
         * RIGHT INPUT
         */
        jpnRightInput.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        jpnRightInput.add(jlbMaSP, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnRightInput.add(jpnMaSP, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        jpnRightInput.add(jlbSoLuong, gbc);

        jtfSoLuong.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnRightInput.add(jtfSoLuong, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        jpnRightInput.add(jlbDonGiaGoc, gbc);

        jtfDonGiaGoc.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnRightInput.add(jtfDonGiaGoc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnRightInput.add(jlbThanhTien, gbc);

        jtfThanhTien.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 10, 10);
        jpnRightInput.add(jtfThanhTien, gbc);

        /*
         * Main Layout
         */
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(jbtGhiExcel).addComponent(jbtDocExcel)
                        .addComponent(jbtExportPDF))
                .addGroup(layout.createSequentialGroup().addComponent(jpnLeftInput).addGap(13, 13, 13)
                        .addComponent(jpnCommand, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup().addComponent(jbtXemPhieu)
                                        .addComponent(jbtXemChiTiet))
                                .addComponent(jpnRightInput)))
                .addGroup(layout.createSequentialGroup().addComponent(jpnTrangTim).addGap(13, 13, 13)
                        .addComponent(jpnSapXep).addGap(13, 13, 13)
                        .addComponent(jpnLoc, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
                .addComponent(jscpPhieuNhapHang).addComponent(jscpChiTietPhieuNhapHang));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jbtGhiExcel)
                        .addComponent(jbtDocExcel).addComponent(jbtExportPDF).addComponent(jbtXemPhieu)
                        .addComponent(jbtXemChiTiet))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpnRightInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(jpnLeftInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpnCommand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jpnTrangTim, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jpnSapXep, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jpnLoc, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13).addComponent(jscpPhieuNhapHang).addComponent(jscpChiTietPhieuNhapHang)));

        setLayout(layout);

        jtbPhieuNhapHang.getTableHeader().addMouseListener(this);
        jtbChiTietPhieuNhapHang.getTableHeader().addMouseListener(this);
        jtbPhieuNhapHang.addMouseListener(this);
        jtbChiTietPhieuNhapHang.addMouseListener(this);
        jlbThem.addMouseListener(this);
        jlbSua.addMouseListener(this);
        jlbTimKiem.addMouseListener(this);
        jlbFirst.addMouseListener(this);
        jlbPrev.addMouseListener(this);
        jlbNext.addMouseListener(this);
        jlbLast.addMouseListener(this);
        jbtLoc.addMouseListener(this);
        jlbOk.addMouseListener(this);
        jlbCancel.addMouseListener(this);
        jbtGhiExcel.addMouseListener(this);
        jbtDocExcel.addMouseListener(this);
        jbtExportPDF.addMouseListener(this);
        jbtSapXep.addMouseListener(this);
        jbtThongKe.addMouseListener(this);
        jtbMaNCC.addMouseListener(this);
        jtbMaSP.addMouseListener(this);
        jtbMaNV.addMouseListener(this);
        jlbMoreFirst.addMouseListener(this);
        jlbMorePrev.addMouseListener(this);
        jlbMoreNext.addMouseListener(this);
        jlbMoreLast.addMouseListener(this);

        jbtXemPhieu.addActionListener(this);
        jbtXemPhieu.setActionCommand("phieu");
        jbtXemChiTiet.addActionListener(this);
        jbtXemChiTiet.setActionCommand("chitiet");
        jbtMoreMaNCC.addActionListener(this);
        jbtMoreMaNV.addActionListener(this);
        jbtMoreMaSP.addActionListener(this);

        jtfSoLuong.addKeyListener(this);
        jtfDonGiaGoc.addKeyListener(this);
    }

    public void frameMaximized() {
        getTableData(soRowToiDaMoiTrang, Integer.parseInt(jlbTrang.getText()));
    }

    public void frameRestore() {
        getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
    }

    public void getTableData(int soRowMoiTrang, int trang) {
        if (jscpPhieuNhapHang.isVisible()) {
            model = ConfigTable.addTableHeader(PNHtitles);

            ArrayList<PhieuNhapHang> DSPNH = new ArrayList<PhieuNhapHang>();
            DSPNH = PNHArr.get(soRowMoiTrang, trang);
            int length = DSPNH.size();
            for (int i = 0; i < length; i++) {
                Vector<Object> row = new Vector<Object>();
                row.add(Boolean.FALSE);
                row.add(DSPNH.get(i).getMaPhieu());
                row.add(DSPNH.get(i).getMaNCC());
                row.add(DSPNH.get(i).getMaNV());
                row.add(DSPNH.get(i).getNgayNhap());
                row.add(DSPNH.get(i).getTongTien());
                model.addRow(row);
            }
            jtbPhieuNhapHang.setModel(model);

            retable(jtbPhieuNhapHang, true);
        } else {
            String maPhieuString = jtfMaPhieuPNH.getText().equals("") ? "0" : jtfMaPhieuPNH.getText();
            model = ConfigTable.addTableHeader(CTPNHtitles);
            if (!maPhieuString.equals("0")) {
                int maPhieu = Integer.parseInt(maPhieuString);

                ArrayList<ChiTietPhieuNhapHang> DSCTPNH = new ArrayList<ChiTietPhieuNhapHang>();
                DSCTPNH = CTPNHArr.get(maPhieu, soRowMoiTrang, trang);
                int length = DSCTPNH.size();
                for (int i = 0; i < length; i++) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(Boolean.FALSE);
                    row.add(DSCTPNH.get(i).getMaPhieu());
                    row.add(DSCTPNH.get(i).getMaSP());
                    row.add(DSCTPNH.get(i).getSoLuong());
                    row.add(DSCTPNH.get(i).getDonGiaGoc());
                    row.add(DSCTPNH.get(i).getThanhTien());
                    model.addRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn phiếu nhập để xem chi tiết.", "Thông tin",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            jtbChiTietPhieuNhapHang.setModel(model);

            retable(jtbChiTietPhieuNhapHang, true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "phieu": {
                /*
                 * Set false cho Right Input
                 */
                jscpChiTietPhieuNhapHang.setVisible(false);

                jbtXemPhieu.setEnabled(false);
                jbtXemPhieu.setBackground(new Color(240, 240, 240));

                jbtMoreMaSP.setEnabled(false);

                jtfMaSP.setEditable(false);
                jtfSoLuong.setEditable(false);
                jtfDonGiaGoc.setEditable(false);

                jtfThanhTien.setBackground(new Color(240, 240, 240));

                jlbMaSP.setForeground(Color.LIGHT_GRAY);
                jlbSoLuong.setForeground(Color.LIGHT_GRAY);
                jlbDonGiaGoc.setForeground(Color.LIGHT_GRAY);
                jlbThanhTien.setForeground(Color.LIGHT_GRAY);

                /*
                 * Set true cho Left Input
                 */
                jscpPhieuNhapHang.setVisible(true);

                jbtXemChiTiet.setEnabled(true);
                jbtXemChiTiet.setForeground(Color.WHITE);
                jbtXemChiTiet.setBackground(mainColor);

                jtfMaPhieuPNH.setEditable(true);
                jtfMaPhieuPNH.setBackground(new Color(245, 245, 245));
                jtfMaPhieuPNH.setDisabledTextColor(Color.BLACK);

                jbtMoreMaNCC.setEnabled(true);
                jbtMoreMaNV.setEnabled(true);

                jtfMaNCC.setEditable(true);
                jtfMaNCC.setForeground(Color.BLACK);

                jtfMaNV.setEnabled(true);
                jtfMaNV.setBackground(Color.WHITE);

                jdcNgayNhap.setEnabled(true);
                // JTextFieldDateEditor editor = (JTextFieldDateEditor)
                // jdcNgayNhap.getDateEditor();
                // editor.setEditable(false);
                // editor.setBackground(new Color(245, 245, 245));

                jtfMaPhieuPNH.setEditable(true);
                jtfMaPhieuPNH.setBackground(new Color(245, 245, 245));

                jlbMaPhieuPNH.setForeground(Color.BLACK);
                jlbMaPhieuPNH.setForeground(Color.BLACK);
                jlbMaNCC.setForeground(Color.BLACK);
                jlbMaNV.setForeground(Color.BLACK);
                jlbNgayNhap.setForeground(Color.BLACK);

                jcbLoc.removeAllItems();
                jcbTim.removeAllItems();
                jcbCotSapXep.removeAllItems();
                for (int i = 0; i < PNHtitles.length; i++) {
                    if (i != 0 && i != PNHtitles.length - 1) {
                        jcbLoc.addItem(PNHtitles[i]);
                        jcbTim.addItem(PNHtitles[i]);
                        jcbCotSapXep.addItem(PNHtitles[i]);
                    }
                }

                getTableData(soRowMoiTrang, 1);

                DungChung.resetTextField(jtfMaSP, jtfSoLuong, jtfDonGiaGoc, jtfThanhTien);

                revalidate();
                repaint();
                break;
            }
            case "chitiet": {
                /*
                 * Set false cho Left Input
                 */
                jscpPhieuNhapHang.setVisible(false);

                jbtMoreMaNCC.setEnabled(false);
                jbtMoreMaNV.setEnabled(false);

                jbtXemChiTiet.setEnabled(false);
                jbtXemChiTiet.setBackground(new Color(240, 240, 240));

                jtfMaPhieuPNH.setEditable(false);
                jtfMaPhieuPNH.setBackground(new Color(240, 240, 240));

                jtfMaNCC.setEditable(false);
                jtfMaNCC.setBackground(new Color(240, 240, 240));
                jtfMaNCC.setForeground(new Color(184, 207, 229));

                jtfMaNV.setEnabled(false);
                jtfMaNV.setBackground(new Color(240, 240, 240));

                jdcNgayNhap.setEnabled(false);

                jlbMaPhieuPNH.setForeground(Color.LIGHT_GRAY);
                jlbMaNCC.setForeground(Color.LIGHT_GRAY);
                jlbMaNV.setForeground(Color.LIGHT_GRAY);
                jlbNgayNhap.setForeground(Color.LIGHT_GRAY);

                /*
                 * Set true cho Right Input
                 */
                jscpChiTietPhieuNhapHang.setVisible(true);

                jbtXemPhieu.setEnabled(true);
                jbtXemPhieu.setForeground(Color.WHITE);
                jbtXemPhieu.setBackground(mainColor);

                jbtMoreMaSP.setEnabled(true);

                jtfMaSP.setEditable(true);
                jtfSoLuong.setEditable(true);
                jtfDonGiaGoc.setEditable(true);

                jtfThanhTien.setBackground(new Color(245, 245, 245));
                jtfThanhTien.setDisabledTextColor(Color.BLACK);

                jlbMaSP.setForeground(Color.BLACK);
                jlbSoLuong.setForeground(Color.BLACK);
                jlbDonGiaGoc.setForeground(Color.BLACK);
                jlbThanhTien.setForeground(Color.BLACK);

                jcbLoc.removeAllItems();
                jcbTim.removeAllItems();
                jcbCotSapXep.removeAllItems();
                for (int i = 2; i < CTPNHtitles.length - 1; i++) {
                    jcbLoc.addItem(CTPNHtitles[i]);
                    jcbTim.addItem(CTPNHtitles[i]);
                    jcbCotSapXep.addItem(CTPNHtitles[i]);
                }

                getTableData(soRowMoiTrang, 1);
                revalidate();
                repaint();
                break;
            }
        }
        if (e.getSource() == jbtMoreMaNCC) {
            jtfMore.setText("");
            jcbMore.removeAllItems();

            RenderMoreTableNCC moreNCC = new RenderMoreTableNCC();
            moreNCC.renderJCombo();
            moreNCC.renderHeader();
            moreNCC.renderRow(soRowMore, 1);

            jscpMore.setViewportView(jtbMaNCC);
            jfMore.setLocationRelativeTo(null);
            jfMore.setVisible(true);

            jlbMore.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String col = jcbMore.getSelectedItem().toString();
                    String giaTriTim = jtfMore.getText();
                    if (giaTriTim.equals("")) {
                        moreNCC.renderHeader();
                        moreNCC.renderRow(soRowMore, 1);

                        return;
                    }
                    moreNCC.renderHeader();

                    ArrayList<NhaCungCap> nccArr = NCCArr.searchMul(col, giaTriTim);
                    moreNCC.renderListRow(soRowMore, Integer.parseInt(jlbMoreTrang.getText()), nccArr);
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
            });
        }
        if (e.getSource() == jbtMoreMaNV) {
            jtfMore.setText("");
            jcbMore.removeAllItems();

            RenderMoreTableNV moreNV = new RenderMoreTableNV();
            moreNV.renderJCombo();
            moreNV.renderHeader();
            moreNV.renderRow(soRowMore, 1);

            jscpMore.setViewportView(jtbMaNV);
            jfMore.setLocationRelativeTo(null);
            jfMore.setVisible(true);

            jlbMore.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    String col = jcbMore.getSelectedItem().toString();
                    String giaTriTim = jtfMore.getText();
                    if (giaTriTim.equals("")) {
                        moreNV.renderHeader();
                        moreNV.renderRow(soRowMore, 1);

                        return;
                    }
                    moreNV.renderHeader();

                    ArrayList<NhanVien> nvArr = NVArr.search(col, giaTriTim);
                    moreNV.renderListRow(soRowMore, Integer.parseInt(jlbMoreTrang.getText()), nvArr);
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
            });
        }
        if (e.getSource() == jbtMoreMaSP) {
            jtfMore.setText("");
            jcbMore.removeAllItems();

            RenderMoreTableSP moreSP = new RenderMoreTableSP();
            moreSP.renderHeader();
            moreSP.renderRow(soRowMoreSP, 1);

            jscpMore.setViewportView(jtbMaSP);
            jfMore.setLocationRelativeTo(null);
            jfMore.setVisible(true);

            jlbMore.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String col = jcbMore.getSelectedItem().toString();
                    String giaTriTim = jtfMore.getText();
                    if (giaTriTim.equals("")) {
                        moreSP.renderHeader();
                        moreSP.renderRow(soRowMoreSP, 1);

                        return;
                    }

                    moreSP.renderHeader();

                    ArrayList<SanPham> nvArr = SPArr.searchMul(col, giaTriTim);
                    moreSP.renderListRow(soRowMoreSP, Integer.parseInt(jlbMoreTrang.getText()), nvArr);
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
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object eSrc = e.getSource();

        if (eSrc == jtbChiTietPhieuNhapHang.getTableHeader()) {
            int col = jtbChiTietPhieuNhapHang.columnAtPoint(e.getPoint());
            if (col == 0) {
                if (jtbChiTietPhieuNhapHang.getRowCount() > 0) {
                    ArrayList<Integer> checkedID = new ArrayList<Integer>();
                    ArrayList<Integer> checkedDonGia = new ArrayList<Integer>();
                    Boolean coCheck = false;
                    int maPhieu = Integer.parseInt(jtbChiTietPhieuNhapHang.getValueAt(0, 1).toString());

                    for (int i = 0; i < jtbChiTietPhieuNhapHang.getRowCount(); i++) {
                        Boolean isChecked = Boolean.valueOf(jtbChiTietPhieuNhapHang.getValueAt(i, 0).toString());
                        if (isChecked) {
                            coCheck = true;

                            Integer maSP = Integer.parseInt(jtbChiTietPhieuNhapHang.getValueAt(i, 2).toString());
                            checkedID.add(maSP);

                            Integer donGiaGoc = Integer.parseInt(jtbChiTietPhieuNhapHang.getValueAt(i, 4).toString());
                            checkedDonGia.add(donGiaGoc);
                        }
                    }
                    if (coCheck == true) {
                        CTPNHArr.removeByMaSP(maPhieu, checkedID, checkedDonGia);
                        getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 ô để xóa", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            return;
        }
        if (eSrc == jtbPhieuNhapHang.getTableHeader()) {
            int col = jtbPhieuNhapHang.columnAtPoint(e.getPoint());
            if (col == 0) {
                ArrayList<Integer> checkedID = new ArrayList<Integer>();
                Boolean coCheck = false;
                for (int i = 0; i < jtbPhieuNhapHang.getRowCount(); i++) {
                    Boolean isChecked = Boolean.valueOf(jtbPhieuNhapHang.getValueAt(i, 0).toString());
                    if (isChecked) {
                        Integer maPhieu = Integer.parseInt(jtbPhieuNhapHang.getValueAt(i, 1).toString());
                        coCheck = true;

                        checkedID.add(maPhieu);
                    }
                }
                if (coCheck == true) {
                    PNHArr.remove(checkedID);
                    getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 ô để xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }
        if (eSrc == jtbPhieuNhapHang) {
            int i = jtbPhieuNhapHang.getSelectedRow();
            if (i >= 0) {
                jtfMaPhieuPNH.setText(jtbPhieuNhapHang.getModel().getValueAt(i, 1).toString());
                jtfMaNCC.setText(jtbPhieuNhapHang.getModel().getValueAt(i, 2).toString());
                jtfMaNV.setText(jtbPhieuNhapHang.getModel().getValueAt(i, 3).toString());
                try {
                    jdcNgayNhap.setDate(new SimpleDateFormat("dd/MM/yyy")
                            .parse(jtbPhieuNhapHang.getModel().getValueAt(i, 4).toString()));
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }
        if (eSrc == jtbChiTietPhieuNhapHang) {
            int i = jtbChiTietPhieuNhapHang.getSelectedRow();
            if (i >= 0) {
                jtfMaSP.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 2).toString());
                jtfSoLuong.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 3).toString());
                jtfDonGiaGoc.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 4).toString());
                jtfThanhTien.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 5).toString());
            }
            return;
        }
        if (eSrc == jtbMaNV) {
            int i = jtbMaNV.getSelectedRow();
            if (i >= 0) {
                jtfMaNV.setText(jtbMaNV.getModel().getValueAt(i, 0).toString());
            }
            return;
        }
        if (eSrc == jtbMaNCC) {
            int i = jtbMaNCC.getSelectedRow();
            if (i >= 0) {
                jtfMaNCC.setText(jtbMaNCC.getModel().getValueAt(i, 0).toString());
            }
            return;
        }
        if (eSrc == jtbMaSP) {
            int i = jtbMaSP.getSelectedRow();
            if (i >= 0) {
                jtfMaSP.setText(jtbMaSP.getModel().getValueAt(i, 1).toString());
            }
            return;
        }
        if (eSrc == jbtGhiExcel) {
            PNHArr.ghiFile();

            CTPNHArr.ghiFile();
        }
        if (eSrc == jbtDocExcel) {
            ArrayList<PhieuNhapHang> pnhArr = PNHArr.docFile();
            PNHArr.setArr(pnhArr);
            getTableData(soRowMoiTrang, 1);

            ArrayList<ChiTietPhieuNhapHang> ctpnhArr = CTPNHArr.docFile();
            CTPNHArr.setArr(ctpnhArr);
            getTableData(soRowMoiTrang, 1);
            jlbTrang.setText("1");
        }
        if (eSrc == jbtExportPDF) {
            PNHArr.pdf();
        }
        if (eSrc == jbtThongKe) {
            int namBatDau = 2015;
            int namHienTai = LocalDate.now().getYear();

            ArrayList<PhieuNhapHang> TKPhieuNhap = new ArrayList<PhieuNhapHang>();
            TKPhieuNhap = PNHArr.getAll();

            String[] thang = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
            int headerLength = thang.length;
            Vector<String> header = new Vector<String>();
            for (int i = 0; i < headerLength; i++) {
                header.add(thang[i]);
            }
            jmdThongKe = new DefaultTableModel(header, 0);

            ArrayList<Integer> nam = new ArrayList<Integer>();
            for (int i = namBatDau; i <= namHienTai; i++) {
                nam.add(i);
            }

            for (int i = 0; i < nam.size(); i++) {
                Vector<Object> row = new Vector<Object>();
                row.add(nam.get(i));
                for (int j = 0; j < thang.length - 1; j++) {
                    row.add(0);
                }
                jmdThongKe.addRow(row);
            }
            jtbThongKe.setModel(jmdThongKe);

            for (PhieuNhapHang pnh : TKPhieuNhap) {
                String[] thoiGian = pnh.getNgayNhap().split("/");
                int pnhThang = Integer.parseInt(thoiGian[1]);
                int pnhNam = Integer.parseInt(thoiGian[2]) - namBatDau;
                int tongTienCu = Integer.parseInt(jtbThongKe.getModel().getValueAt(pnhNam, pnhThang).toString());
                jtbThongKe.getModel().setValueAt(tongTienCu + pnh.getTongTien(), pnhNam, pnhThang);
            }

            int[] cotDuocCanhGiua = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
            jtbThongKe.setRowHeight(30);
            jtbThongKe.setBackground(Color.WHITE);
            jtbThongKe.setSelectionBackground(new Color(32, 136, 203));
            jtbThongKe.setSelectionForeground(Color.WHITE);
            jtbThongKe.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
            jtbThongKe.getTableHeader().setBackground(new Color(0xfc498e));
            jtbThongKe.getTableHeader().setForeground(new Color(255, 255, 255));

            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            for (int i : cotDuocCanhGiua) {
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
            }
            jtbThongKe.getColumnModel().getColumn(0).setPreferredWidth(70);

            jfThongKe.setLocationRelativeTo(null);
            jfThongKe.setVisible(true);
        }
        if (eSrc == jbtThongKe) {
            int namBatDau = 2015;
            int namHienTai = LocalDate.now().getYear();

            ArrayList<PhieuNhapHang> TKPhieuNhap = new ArrayList<PhieuNhapHang>();
            TKPhieuNhap = PNHArr.getAll();

            String[] thang = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
            int headerLength = thang.length;
            Vector<String> header = new Vector<String>();
            for (int i = 0; i < headerLength; i++) {
                header.add(thang[i]);
            }
            jmdThongKe = new DefaultTableModel(header, 0);

            ArrayList<Integer> nam = new ArrayList<Integer>();
            for (int i = namBatDau; i <= namHienTai; i++) {
                nam.add(i);
            }

            for (int i = 0; i < nam.size(); i++) {
                Vector<Object> row = new Vector<Object>();
                row.add(nam.get(i));
                for (int j = 0; j < thang.length - 1; j++) {
                    row.add(0);
                }
                jmdThongKe.addRow(row);
            }
            jtbThongKe.setModel(jmdThongKe);

            for (PhieuNhapHang pnh : TKPhieuNhap) {
                String[] thoiGian = pnh.getNgayNhap().split("/");
                int pnhThang = Integer.parseInt(thoiGian[1]);
                int pnhNam = Integer.parseInt(thoiGian[2]) - namBatDau;
                int tongTienCu = Integer.parseInt(jtbThongKe.getModel().getValueAt(pnhNam, pnhThang).toString());
                jtbThongKe.getModel().setValueAt(tongTienCu + pnh.getTongTien(), pnhNam, pnhThang);
            }

            int[] cotDuocCanhGiua = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
            jtbThongKe.setRowHeight(30);
            jtbThongKe.setBackground(Color.WHITE);
            jtbThongKe.setSelectionBackground(new Color(32, 136, 203));
            jtbThongKe.setSelectionForeground(Color.WHITE);
            jtbThongKe.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
            jtbThongKe.getTableHeader().setBackground(new Color(0xfc498e));
            jtbThongKe.getTableHeader().setForeground(new Color(255, 255, 255));

            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            for (int i : cotDuocCanhGiua) {
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
                jtbThongKe.getColumnModel().getColumn(i).setCellRenderer(center);
            }
            jtbThongKe.getColumnModel().getColumn(0).setPreferredWidth(70);

            jfThongKe.setLocationRelativeTo(null);
            jfThongKe.setVisible(true);
        }

        /*
         * Lấy dữ liệu
         */
        String maPhieuPNH = jtfMaPhieuPNH.getText(), maNCC = jtfMaNCC.getText(), maNV = jtfMaNV.getText(),
                ngayNhap = "";
        try {
            String rawDate = jdcNgayNhap.getDate() != null ? DateFormat.getDateInstance().format(jdcNgayNhap.getDate())
                    : "";
            if (!rawDate.equals("")) {
                Date parsed = new SimpleDateFormat("MMM dd, yyyy").parse(rawDate);
                ngayNhap = new SimpleDateFormat("dd/MM/yyyy").format(parsed);
            }
        } catch (ParseException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String maPhieuCTPNH = jtfMaPhieuPNH.getText(), maSP = jtfMaSP.getText(), soLuong = jtfSoLuong.getText(),
                donGiaGoc = jtfDonGiaGoc.getText(), thanhTien = jtfThanhTien.getText();

        if (eSrc == jlbThem) {
            int count = 0;
            if (jscpPhieuNhapHang.isVisible()) {
                if (!InputTester.chiSo(maNCC, "Mã tài khoản", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(maNV, "Mã nhân viên", 5)) {
                    count++;
                }
                if (!InputTester.kiemNgay(ngayNhap, "Ngày nhập")) {
                    count++;
                }
                if (count == 0) {
                    PNHArr.add(Integer.parseInt(maNCC), Integer.parseInt(maNV), ngayNhap);
                    getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                    DungChung.resetTextField(jtfMaPhieuPNH, jtfMaNV, jtfMaNCC);
                    jdcNgayNhap.setDate(null);
                } else
                    return;
            }
            if (jscpChiTietPhieuNhapHang.isVisible()) {
                if (!InputTester.chiSo(maPhieuCTPNH, "Mã phiếu", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(maSP, "Mã sản phẩm", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(soLuong, "Số lượng", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(donGiaGoc, "Đơn giá gốc", 10)) {
                    count++;
                }
                if (count == 0) {
                    CTPNHArr.add(Integer.parseInt(maSP), Integer.parseInt(maPhieuCTPNH), Integer.parseInt(soLuong),
                            Integer.parseInt(donGiaGoc), Integer.parseInt(thanhTien));
                    getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                    DungChung.resetTextField(jtfMaSP, jtfSoLuong, jtfDonGiaGoc, jtfThanhTien);
                } else
                    return;
            }
        }
        if (eSrc == jlbOk) {
            int count = 0;
            if (jscpPhieuNhapHang.isVisible()) {
                if (!InputTester.chiSo(maPhieuPNH, "Mã phiếu", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(maNCC, "Mã tài khoản", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(maNV, "Mã nhân viên", 5)) {
                    count++;
                }
                if (!InputTester.kiemNgay(ngayNhap, "Ngày nhập")) {
                    count++;
                }
                if (count == 0) {
                    if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thay đổi?", "Xác nhận",
                            JOptionPane.YES_NO_OPTION) == 0) {

                        PNHArr.edit(Integer.parseInt(maPhieuPNH), Integer.parseInt(maNCC), Integer.parseInt(maNV),
                                ngayNhap);
                        getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                        DungChung.resetTextField(jtfMaPhieuPNH, jtfMaNV, jtfMaNCC);
                        jdcNgayNhap.setDate(null);
                        jlbOk.setVisible(false);
                        jlbCancel.setVisible(false);
                        jlbThem.setVisible(true);
                    }
                } else
                    return;
            }
            if (jscpChiTietPhieuNhapHang.isVisible()) {
                if (!InputTester.chiSo(maPhieuCTPNH, "Mã phiếu", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(maSP, "Mã sản phẩm", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(soLuong, "Số lượng", 5)) {
                    count++;
                }
                if (!InputTester.chiSo(donGiaGoc, "Đơn giá gốc", 10)) {
                    count++;
                }
                if (!InputTester.chiSo(thanhTien, "Thành tiền", 10)) {
                    count++;
                }
                if (count == 0) {
                    if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thay đổi?", "Xác nhận",
                            JOptionPane.YES_NO_OPTION) == 0) {

                        CTPNHArr.edit(Integer.parseInt(maPhieuCTPNH), Integer.parseInt(maSP), Integer.parseInt(soLuong),
                                Integer.parseInt(donGiaGoc), Integer.parseInt(thanhTien));
                        getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                        DungChung.resetTextField(jtfMaSP, jtfSoLuong, jtfDonGiaGoc, jtfThanhTien);
                        jlbOk.setVisible(false);
                        jlbCancel.setVisible(false);
                        jlbThem.setVisible(true);
                    }
                } else
                    return;
            }
        }
        if (eSrc == jlbCancel) {
            jlbThem.setVisible(true);
            jlbOk.setVisible(false);
            jlbCancel.setVisible(false);

            if (jscpPhieuNhapHang.isVisible()) {
                jtfMaNCC.setEditable(true);
                jtfMaNCC.setBackground(Color.WHITE);
                jbtMoreMaNCC.setEnabled(true);
                jbtMoreMaNCC.addActionListener(this);
                jtfMaNV.setEditable(true);
                jtfMaNV.setBackground(Color.WHITE);
                jbtMoreMaNV.setEnabled(true);
                jbtMoreMaNV.addActionListener(this);

                DungChung.resetTextField(jtfMaPhieuPNH, jtfMaNV, jtfMaNCC);
                jdcNgayNhap.setDate(null);
            } else {
                jtfMaSP.setEditable(true);
                jtfMaSP.setBackground(Color.WHITE);
                jbtMoreMaSP.setEnabled(true);
                jbtMoreMaSP.addActionListener(this);

                DungChung.resetTextField(jtfMaSP, jtfSoLuong, jtfDonGiaGoc, jtfThanhTien);
            }
        }
        if (eSrc == jlbTimKiem) {
            if (jscpPhieuNhapHang.isVisible()) {
                String col = jcbTim.getSelectedItem().toString();
                String giaTriTim = jtfTim.getText();

                model = ConfigTable.addTableHeader(PNHtitles);

                ArrayList<PhieuNhapHang> pnhArr = PNHArr.search(col, giaTriTim);
                for (PhieuNhapHang pnh : pnhArr) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(Boolean.FALSE);
                    row.add(pnh.getMaPhieu());
                    row.add(pnh.getMaNCC());
                    row.add(pnh.getMaNV());
                    row.add(pnh.getNgayNhap());
                    row.add(pnh.getTongTien());
                    model.addRow(row);
                }
                jtbPhieuNhapHang.setModel(model);
                retable(jtbPhieuNhapHang, true);
            }
            if (jscpChiTietPhieuNhapHang.isVisible()) {
                String col = jcbTim.getSelectedItem().toString();
                String giaTriTim = jtfTim.getText();

                model = ConfigTable.addTableHeader(CTPNHtitles);

                ArrayList<ChiTietPhieuNhapHang> ctpnhArr = CTPNHArr.search(col, giaTriTim);
                for (ChiTietPhieuNhapHang ctpnh : ctpnhArr) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(Boolean.FALSE);
                    row.add(ctpnh.getMaSP());
                    row.add(ctpnh.getMaPhieu());
                    row.add(ctpnh.getSoLuong());
                    row.add(ctpnh.getDonGiaGoc());
                    row.add(ctpnh.getThanhTien());
                    model.addRow(row);
                }
                jtbPhieuNhapHang.setModel(model);
                retable(jtbPhieuNhapHang, true);
            }
        }
        if (eSrc == jbtLoc) {
            String from = jtfLocTu.getText();
            String to = jtfLocDen.getText();
            int count = 0;
            if (InputTester.chuoiNull(from, "Từ")) {
                count++;
            }
            if (InputTester.chuoiNull(to, "Đến")) {
                count++;
            }
            String col = jcbLoc.getSelectedItem().toString();
            switch (col) {
                case "Mã sản phẩm":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Mã phiếu":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Số lượng":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Đơn giá gốc":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Thành tiền":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Mã nhà cung cấp":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Mã nhân viên":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Ngày nhập":
                    if (!InputTester.kiemNgay(from, "Từ")) {
                        count++;
                    }
                    if (!InputTester.kiemNgay(to, "Đến")) {
                        count++;
                    }
                    break;
                case "Tổng tiền":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
            }

            if (count == 0) {
                if (jscpChiTietPhieuNhapHang.isVisible()) {
                    DefaultTableModel newModel = ConfigTable.addTableHeader(CTPNHtitles);

                    ArrayList<ChiTietPhieuNhapHang> arr = CTPNHArr.filter(col, from, to);
                    if (arr.size() != 0) {
                        for (ChiTietPhieuNhapHang kh : arr) {
                            Vector<Object> row = new Vector<Object>();
                            row.add(Boolean.FALSE);
                            row.add(kh.getMaSP());
                            row.add(kh.getMaPhieu());
                            row.add(kh.getSoLuong());
                            row.add(kh.getDonGiaGoc());
                            row.add(kh.getThanhTien());
                            newModel.addRow(row);
                        }
                        jtbChiTietPhieuNhapHang.setModel(newModel);
                        retable(jtbChiTietPhieuNhapHang, true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu trùng khớp với khoảng lọc",
                                "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if (jscpPhieuNhapHang.isVisible()) {
                    DefaultTableModel newModel = ConfigTable.addTableHeader(PNHtitles);

                    ArrayList<PhieuNhapHang> arr = PNHArr.filter(col, from, to);
                    if (arr.size() != 0) {
                        for (PhieuNhapHang kh : arr) {
                            Vector<Object> row = new Vector<Object>();
                            row.add(Boolean.FALSE);
                            row.add(kh.getMaPhieu());
                            row.add(kh.getMaNCC());
                            row.add(kh.getMaNV());
                            row.add(kh.getNgayNhap());
                            row.add(kh.getTongTien());
                            newModel.addRow(row);
                        }
                        jtbPhieuNhapHang.setModel(newModel);
                        retable(jtbPhieuNhapHang, true);
                    } else {
                        JOptionPane.showMessageDialog(null, ": Không tìm thấy dữ liệu trùng khớp với khoảng lọc",
                                "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        if (eSrc == jbtSapXep) {
            String col = jcbCotSapXep.getSelectedItem().toString();
            String order = jcbKieuSapXep.getSelectedItem().toString();

            if (jscpChiTietPhieuNhapHang.isVisible()) {
                CTPNHArr.sort(col, order);
                getTableData(soRowMoiTrang, 1);
            } else {
                PNHArr.sort(col, order);

                model = ConfigTable.addTableHeader(PNHtitles);

                ArrayList<PhieuNhapHang> DSPNH = new ArrayList<PhieuNhapHang>();
                DSPNH = PNHArr.getTuArrayList(soRowMoiTrang, 1);
                int length = DSPNH.size();
                for (int i = 0; i < length; i++) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(Boolean.FALSE);
                    row.add(DSPNH.get(i).getMaPhieu());
                    row.add(DSPNH.get(i).getMaNCC());
                    row.add(DSPNH.get(i).getMaNV());
                    row.add(DSPNH.get(i).getNgayNhap());
                    row.add(DSPNH.get(i).getTongTien());
                    model.addRow(row);
                }
                jtbPhieuNhapHang.setModel(model);

                retable(jtbPhieuNhapHang, true);
            }
        }

        /*
         * Phân trang chính
         */
        if (eSrc == jlbFirst) {
            if (jscpPhieuNhapHang.isVisible()) {
                if (!jlbTrang.getText().equals("1")) {
                    getTableData(soRowMoiTrang, 1);
                    jlbTrang.setText("1");
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            } else {
                if (!jlbTrang.getText().equals("1")) {
                    getTableData(soRowMoiTrang, 1);
                    jlbTrang.setText("1");
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
        }
        if (eSrc == jlbPrev) {
            if (jscpPhieuNhapHang.isVisible()) {
                if (!jlbTrang.getText().equals("1")) {
                    int trangTruoc = Integer.parseInt(jlbTrang.getText()) - 1;
                    getTableData(soRowMoiTrang, trangTruoc);
                    jlbTrang.setText(Integer.toString(trangTruoc));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            } else {
                if (!jlbTrang.getText().equals("1")) {
                    int trangTruoc = Integer.parseInt(jlbTrang.getText()) - 1;
                    getTableData(soRowMoiTrang, trangTruoc);
                    jlbTrang.setText(Integer.toString(trangTruoc));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
        }
        if (eSrc == jlbNext) {
            if (jscpPhieuNhapHang.isVisible()) {
                int trangCuoi = (int) PNHArr.getTongSoTrang(soRowMoiTrang);
                if (!jlbTrang.getText().equals(Integer.toString(trangCuoi))) {
                    int trangSau = Integer.parseInt(jlbTrang.getText()) + 1;
                    jlbTrang.setText(Integer.toString(trangSau));
                    getTableData(soRowMoiTrang, trangSau);
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            } else {
                int trangCuoi = (int) CTPNHArr.getTongSoTrang(soRowMoiTrang);
                if (!jlbTrang.getText().equals(Integer.toString(trangCuoi))) {
                    int trangSau = Integer.parseInt(jlbTrang.getText()) + 1;
                    jlbTrang.setText(Integer.toString(trangSau));
                    getTableData(soRowMoiTrang, trangSau);
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
        }
        if (eSrc == jlbLast) {
            if (jscpPhieuNhapHang.isVisible()) {
                int trangCuoi = (int) PNHArr.getTongSoTrang(soRowMoiTrang);
                if (!jlbTrang.getText().equals(Integer.toString(trangCuoi))) {
                    getTableData(soRowMoiTrang, trangCuoi);
                    jlbTrang.setText(Integer.toString(trangCuoi));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            } else {
                int trangCuoi = (int) CTPNHArr.getTongSoTrang(soRowMoiTrang);
                if (!jlbTrang.getText().equals(Integer.toString(trangCuoi))) {
                    getTableData(soRowMoiTrang, trangCuoi);
                    jlbTrang.setText(Integer.toString(trangCuoi));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
        }

        /*
         * Pnân trang trong frame more
         */
        if (eSrc == jlbMoreFirst) {
            if (!jlbMoreTrang.getText().equals("1")) {
                JViewport viewport = jscpMore.getViewport();
                if ((JTable) viewport.getView() == jtbMaNCC) {
                    RenderMoreTableNCC moreNCC = new RenderMoreTableNCC();
                    moreNCC.renderHeader();
                    moreNCC.renderRow(soRowMore, 1);
                }
                if ((JTable) viewport.getView() == jtbMaNV) {
                    RenderMoreTableNV moreNV = new RenderMoreTableNV();
                    moreNV.renderHeader();
                    moreNV.renderRow(soRowMore, 1);
                }
                if ((JTable) viewport.getView() == jtbMaSP) {
                    RenderMoreTableSP moreSP = new RenderMoreTableSP();
                    moreSP.renderHeader();
                    moreSP.renderRow(soRowMoreSP, 1);
                    jlbMoreTrang.setText("1");
                }
                jlbMoreTrang.setText("1");
            } else {
                JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
            }
        }
        if (eSrc == jlbMorePrev) {
            if (!jlbMoreTrang.getText().equals("1")) {
                int trangTruoc = Integer.parseInt(jlbMoreTrang.getText()) - 1;
                JViewport viewport = jscpMore.getViewport();
                if ((JTable) viewport.getView() == jtbMaNCC) {
                    RenderMoreTableNCC moreNCC = new RenderMoreTableNCC();
                    moreNCC.renderHeader();
                    moreNCC.renderRow(soRowMore, trangTruoc);
                }
                if ((JTable) viewport.getView() == jtbMaNV) {
                    RenderMoreTableNV moreNV = new RenderMoreTableNV();
                    moreNV.renderHeader();
                    moreNV.renderRow(soRowMore, trangTruoc);
                }
                if ((JTable) viewport.getView() == jtbMaSP) {
                    RenderMoreTableSP moreSP = new RenderMoreTableSP();
                    moreSP.renderHeader();
                    moreSP.renderRow(soRowMoreSP, trangTruoc);
                }
                jlbMoreTrang.setText(Integer.toString(trangTruoc));
            } else {
                JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
            }
        }
        if (eSrc == jlbMoreNext) {
            JViewport viewport = jscpMore.getViewport();
            if ((JTable) viewport.getView() == jtbMaNCC) {
                int trangCuoi = (int) NCCArr.getTongSoTrang(soRowMore);
                if (!jlbMoreTrang.getText().equals(Integer.toString(trangCuoi))) {
                    int trangSau = Integer.parseInt(jlbMoreTrang.getText()) + 1;
                    RenderMoreTableNCC moreNCC = new RenderMoreTableNCC();
                    moreNCC.renderHeader();
                    moreNCC.renderRow(soRowMore, trangSau);
                    jlbMoreTrang.setText(Integer.toString(trangSau));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
            if ((JTable) viewport.getView() == jtbMaNV) {
                int trangCuoi = (int) NVArr.getTongSoTrang(soRowMore);
                if (!jlbMoreTrang.getText().equals(Integer.toString(trangCuoi))) {
                    int trangSau = Integer.parseInt(jlbMoreTrang.getText()) + 1;
                    RenderMoreTableNV moreNV = new RenderMoreTableNV();
                    moreNV.renderHeader();
                    moreNV.renderRow(soRowMore, trangSau);
                    jlbMoreTrang.setText(Integer.toString(trangSau));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
            if ((JTable) viewport.getView() == jtbMaSP) {
                int trangCuoi = (int) SPArr.getTongSoTrang(soRowMoreSP);
                if (!jlbMoreTrang.getText().equals(Integer.toString(trangCuoi))) {
                    int trangSau = Integer.parseInt(jlbMoreTrang.getText()) + 1;
                    RenderMoreTableSP moreSP = new RenderMoreTableSP();
                    moreSP.renderHeader();
                    moreSP.renderRow(soRowMoreSP, trangSau);
                    jlbMoreTrang.setText(Integer.toString(trangSau));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
        }
        if (eSrc == jlbMoreLast) {
            JViewport viewport = jscpMore.getViewport();
            if ((JTable) viewport.getView() == jtbMaNCC) {
                int trangCuoi = (int) NCCArr.getTongSoTrang(soRowMore);
                if (!jlbMoreTrang.getText().equals(Integer.toString(trangCuoi))) {
                    RenderMoreTableNCC moreNCC = new RenderMoreTableNCC();
                    moreNCC.renderHeader();
                    moreNCC.renderRow(soRowMore, trangCuoi);
                    jlbMoreTrang.setText(Integer.toString(trangCuoi));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
            if ((JTable) viewport.getView() == jtbMaNV) {
                int trangCuoi = (int) NVArr.getTongSoTrang(soRowMore);
                if (!jlbMoreTrang.getText().equals(Integer.toString(trangCuoi))) {
                    RenderMoreTableNV moreNV = new RenderMoreTableNV();
                    moreNV.renderHeader();
                    moreNV.renderRow(soRowMore, trangCuoi);
                    jlbMoreTrang.setText(Integer.toString(trangCuoi));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
            if ((JTable) viewport.getView() == jtbMaSP) {
                int trangCuoi = (int) SPArr.getTongSoTrang(soRowMoreSP);
                if (!jlbMoreTrang.getText().equals(Integer.toString(trangCuoi))) {
                    RenderMoreTableSP moreSP = new RenderMoreTableSP();
                    moreSP.renderHeader();
                    moreSP.renderRow(soRowMoreSP, trangCuoi);
                    jlbMoreTrang.setText(Integer.toString(trangCuoi));
                } else {
                    JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
                }
            }
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
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getComponent() == jtfSoLuong
                && (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
            int donGiaGoc = Integer.parseInt(jtfDonGiaGoc.getText().equals("") ? "0" : jtfDonGiaGoc.getText());
            int soLuong = Integer.parseInt(jtfSoLuong.getText().equals("") ? "0" : jtfSoLuong.getText());
            int thanhTien = soLuong * donGiaGoc;
            jtfThanhTien.setText(Integer.toString(thanhTien));
        }
        if (e.getComponent() == jtfDonGiaGoc
                && (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
            int donGiaGoc = Integer.parseInt(jtfDonGiaGoc.getText().equals("") ? "0" : jtfDonGiaGoc.getText());
            int soLuong = Integer.parseInt(jtfSoLuong.getText().equals("") ? "0" : jtfSoLuong.getText());
            int thanhTien = donGiaGoc * soLuong;
            jtfThanhTien.setText(Integer.toString(thanhTien));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    private void renderOtherJTableData(JTable tableName) {
        AcceptRejectRenderer renderBtn = new AcceptRejectRenderer();
        tableName.getColumnModel().getColumn(6).setCellRenderer(renderBtn);
        tableName.getColumnModel().getColumn(6).setCellEditor(new AcceptRejectEditor());
    }

    private void retable(JTable tableName, Boolean renderJBtn) {
        if (renderJBtn != false) {
            renderOtherJTableData(tableName);
        }
        int[] cotDuocCanhGiua = { 0, 1, 2, 3, 4, 5 };
        if (tableName.equals(jtbPhieuNhapHang)) {
            String[] kieuSuaDoi = { "p-50", "p-100", "p-100", "mi-100", "p-200", "p-200", "mi-100" };
            ConfigTable.decorateTable(tableName, cotDuocCanhGiua, PNHtitles.length, kieuSuaDoi);
        }
        if (tableName.equals(jtbChiTietPhieuNhapHang)) {
            String[] kieuSuaDoi = { "p-50", "p-100", "p-100", "mi-200", "p-200", "p-200", "mi-100" };
            ConfigTable.decorateTable(tableName, cotDuocCanhGiua, CTPNHtitles.length, kieuSuaDoi);
        }
    }

    private class RenderMoreTableNV {
        private String[] nvTitle = NVArr.title();

        protected void renderJCombo() {
            int titleLength = nvTitle.length;
            for (int i = 0; i < titleLength - 1; i++) {
                if (i != 3) {
                    jcbMore.addItem(nvTitle[i]);
                }
            }
        }

        protected void renderHeader() {
            jcbMore.removeAllItems();
            jfMore.setTitle("Nhân viên");

            int titleLength = nvTitle.length;
            Vector<String> header = new Vector<String>();
            for (int i = 1; i < titleLength - 1; i++) {
                header.add(nvTitle[i]);
                jcbMore.addItem(nvTitle[i]);
            }
            jmdMore = new DefaultTableModel(header, 0) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }

        protected void renderRow(int soRow, int trang) {
            if (!jpnMoreFooter.isVisible()) {
                jpnMoreFooter.setVisible(true);
            }
            ArrayList<NhanVien> nvArr = NVArr.get(soRow, trang);
            for (NhanVien nv : nvArr) {
                Vector<Object> row = new Vector<Object>();
                row.add(nv.getMaNV());
                row.add(nv.getMaTK());
                row.add(nv.getHo());
                row.add(nv.getTen());
                row.add(nv.getNgaySinh());
                row.add(nv.getDiaChi());
                row.add(nv.getSdt());
                row.add(nv.getLuong());
                jmdMore.addRow(row);
            }
            jtbMaNV.setModel(jmdMore);
            retable();
        }

        protected void renderListRow(int soRow, int trang, ArrayList<NhanVien> list) {
            for (NhanVien nv : list) {
                Vector<Object> row = new Vector<Object>();
                row.add(nv.getMaNV());
                row.add(nv.getMaTK());
                row.add(nv.getHo());
                row.add(nv.getTen());
                row.add(nv.getNgaySinh());
                row.add(nv.getDiaChi());
                row.add(nv.getSdt());
                row.add(nv.getLuong());
                jmdMore.addRow(row);
            }
            jtbMaNV.setModel(jmdMore);

            retable();
        }

        private void retable() {
            int[] colsGetCenter = { 0, 1, 4, 6, 7 };
            String[] setWidthOptions = { "p-50", "p-50", "mi-200", "p-100", "p-100", "mi-300", "p-100", "p-100" };
            ConfigTable.decorateTable(jtbMaNV, colsGetCenter, nvTitle.length - 2, setWidthOptions);
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            jtbMaNV.getColumnModel().getColumn(0).setCellRenderer(center);
        }
    }

    private class RenderMoreTableNCC {
        private String[] nccTitle = NCCArr.title();

        protected void renderJCombo() {
            int titleLength = nccTitle.length;
            for (int i = 0; i < titleLength - 1; i++) {
            }
        }

        protected void renderHeader() {
            jfMore.setTitle("Nhà cung cấp");
            jcbMore.removeAllItems();

            int titleLength = nccTitle.length;
            Vector<String> header = new Vector<String>();
            for (int i = 1; i < titleLength - 1; i++) {
                header.add(nccTitle[i]);
                if (i != 3) {
                    jcbMore.addItem(nccTitle[i]);
                }
            }
            jmdMore = new DefaultTableModel(header, 0) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

        }

        protected void renderRow(int soRow, int trang) {
            if (!jpnMoreFooter.isVisible()) {
                jpnMoreFooter.setVisible(true);
            }
            ArrayList<NhaCungCap> nccArr = NCCArr.get(soRow, trang);
            for (NhaCungCap ncc : nccArr) {
                Vector<Object> row = new Vector<Object>();
                row.add(ncc.getMaNCC());
                row.add(ncc.getTenNCC());
                row.add(ncc.getDiaChi());
                row.add(ncc.getSoDienThoai());
                jmdMore.addRow(row);
            }
            jtbMaNCC.setModel(jmdMore);
            retable();
        }

        protected void renderListRow(int soRow, int trang, ArrayList<NhaCungCap> list) {
            for (NhaCungCap ncc : list) {
                Vector<Object> row = new Vector<Object>();
                row.add(ncc.getMaNCC());
                row.add(ncc.getTenNCC());
                row.add(ncc.getDiaChi());
                row.add(ncc.getSoDienThoai());
                jmdMore.addRow(row);
            }
            jtbMaNCC.setModel(jmdMore);
            retable();
        }

        private void retable() {
            int[] colsGetCenter = { 0, 3 };
            String[] setWidthOptions = { "p-50", "mi-150", "mi-200", "p-50" };
            ConfigTable.decorateTable(jtbMaNCC, colsGetCenter, nccTitle.length - 2, setWidthOptions);
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            jtbMaNCC.getColumnModel().getColumn(0).setCellRenderer(center);
        }
    }

    private class RenderMoreTableSP {
        private String[] spTitle = { "Mã SP", "Mã loại", "Mã NSX", "Ảnh", "Tên sản phẩm", "Đơn giá", "Đơn vị tính",
                "Số lượng", "" };

        protected void renderHeader() {
            jfMore.setTitle("Sản phẩm");
            jcbMore.removeAllItems();

            int titleLength = spTitle.length;
            Vector<String> header = new Vector<String>();
            for (int i = 0; i < titleLength - 1; i++) {
                header.add(spTitle[i]);
                if (i != 3) {
                    jcbMore.addItem(spTitle[i]);
                }
            }
            jmdMore = new DefaultTableModel(header, 0) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }

        protected void renderRow(int soRow, int trang) {
            ArrayList<SanPham> spArr = SPArr.getSP(soRow, trang);
            if (!jpnMoreFooter.isVisible()) {
                jpnMoreFooter.setVisible(true);
            }
            for (SanPham sp : spArr) {
                Vector<Object> row = new Vector<Object>();

                JLabel anhDaiDien = new JLabel();
                anhDaiDien.setSize(70, 70);
                String anhDaiDienString = sp.getanhDaiDien();
                String extension = anhDaiDienString.substring(anhDaiDienString.indexOf('.') + 1);
                ImageIcon ii = new ImageIcon(
                        new ImageIcon("image/SanPham/SP-" + sp.getMaSP() + "." + extension).getImage()
                                .getScaledInstance(anhDaiDien.getWidth(), anhDaiDien.getHeight(), Image.SCALE_SMOOTH));
                anhDaiDien.setIcon(ii);

                row.add(sp.getMaSP());
                row.add(sp.getMaLoai());
                row.add(sp.getMaNSX());
                row.add(anhDaiDien);
                row.add(sp.getTenSP());
                row.add(sp.getDonGia());
                row.add(sp.getDonViTinh());
                row.add(sp.getSoLuong());
                jmdMore.addRow(row);
            }
            jtbMaSP.setModel(jmdMore);
            retable();
        }

        protected void renderListRow(int soRow, int trang, ArrayList<SanPham> list) {
            if (list.size() > soRowMoreSP) {
                jpnMoreFooter.setVisible(false);
            } else {
                jpnMoreFooter.setVisible(true);
            }
            for (SanPham sp : list) {
                Vector<Object> row = new Vector<Object>();

                JLabel anhDaiDien = new JLabel();
                anhDaiDien.setSize(70, 70);
                String anhDaiDienString = sp.getanhDaiDien();
                String extension = anhDaiDienString.substring(anhDaiDienString.indexOf('.') + 1);
                ImageIcon ii = new ImageIcon(
                        new ImageIcon("image/SanPham/SP-" + sp.getMaSP() + "." + extension).getImage()
                                .getScaledInstance(anhDaiDien.getWidth(), anhDaiDien.getHeight(), Image.SCALE_SMOOTH));
                anhDaiDien.setIcon(ii);

                row.add(sp.getMaSP());
                row.add(sp.getMaLoai());
                row.add(sp.getMaNSX());
                row.add(anhDaiDien);
                row.add(sp.getTenSP());
                row.add(sp.getDonGia());
                row.add(sp.getDonViTinh());
                row.add(sp.getSoLuong());
                jmdMore.addRow(row);
            }
            jtbMaSP.setModel(jmdMore);
            retable();
        }

        private void retable() {
            int[] colsGetCenter = { 0, 1, 2, 5, 6, 7 };
            String[] setWidthOptions = { "p-50", "p-50", "p-50", "p-60", "mi-200", "p-100", "p-100", "p-100" };
            ConfigTable.decorateTable(jtbMaSP, colsGetCenter, spTitle.length - 1, setWidthOptions);
            jtbMaSP.getColumnModel().getColumn(3).setCellRenderer(new DungChung.CellRenderer(0, jtbMaSP));
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            jtbMaSP.getColumnModel().getColumn(0).setCellRenderer(center);
        }
    }

    /*
     * Tạo 2 button trong cùng 1 ô
     */
    public class AcceptRejectPane extends JPanel {

        private JButton suaButton;
        private JButton xoaButton;
        private String state;

        public AcceptRejectPane() {
            setLayout(new GridBagLayout());
            suaButton = new JButton(suaIcon);
            suaButton.setBackground(new Color(0xb9ddff));
            suaButton.setActionCommand("suaButton");
            xoaButton = new JButton(xoaIcon);
            xoaButton.setActionCommand("xoaButton");
            xoaButton.setBackground(new Color(0xd84551));

            add(suaButton);
            add(xoaButton);

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state = e.getActionCommand();
                    if (state.equals(suaButton.getActionCommand())) {
                        if (jscpPhieuNhapHang.isVisible()) {
                            jtfMaNV.setEditable(false);
                            jtfMaNV.setBackground(new Color(245, 245, 245));
                            jbtMoreMaNV.setEnabled(false);
                            jbtMoreMaNV.removeActionListener(this);
                            jtfMaNCC.setEditable(false);
                            jtfMaNCC.setBackground(new Color(245, 245, 245));
                            jbtMoreMaNCC.setEnabled(false);
                            jbtMoreMaNCC.removeActionListener(this);

                            int i = jtbPhieuNhapHang.getSelectedRow();
                            if (i >= 0) {
                                jtfMaPhieuPNH.setText(jtbPhieuNhapHang.getModel().getValueAt(i, 1).toString());
                                jtfMaNCC.setText(jtbPhieuNhapHang.getModel().getValueAt(i, 2).toString());
                                jtfMaNV.setText(jtbPhieuNhapHang.getModel().getValueAt(i, 3).toString());
                                try {
                                    jdcNgayNhap.setDate(new SimpleDateFormat("dd/MM/yyy")
                                            .parse(jtbPhieuNhapHang.getModel().getValueAt(i, 4).toString()));
                                } catch (ParseException e1) {
                                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        if (jscpChiTietPhieuNhapHang.isVisible()) {
                            jtfMaSP.setEditable(false);
                            jtfMaSP.setBackground(new Color(245, 245, 245));
                            jbtMoreMaSP.setEnabled(false);
                            jbtMoreMaSP.removeActionListener(this);

                            int i = jtbChiTietPhieuNhapHang.getSelectedRow();
                            if (i >= 0) {
                                jtfMaSP.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 2).toString());
                                jtfSoLuong.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 3).toString());
                                jtfDonGiaGoc.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 4).toString());
                                jtfThanhTien.setText(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 5).toString());
                            }
                        }

                        jlbOk.setVisible(true);
                        jlbCancel.setVisible(true);
                        jlbThem.setVisible(false);
                    }
                    if (state.equals(xoaButton.getActionCommand())) {
                        if (jscpPhieuNhapHang.isVisible()) {
                            int i = jtbPhieuNhapHang.getSelectedRow();
                            if (i >= 0) {
                                ArrayList<Integer> id = new ArrayList<Integer>();
                                id.add(Integer.parseInt(jtbPhieuNhapHang.getModel().getValueAt(i, 1).toString()));
                                PNHArr.remove(id);
                                getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                            }
                        } else {
                            int i = jtbChiTietPhieuNhapHang.getSelectedRow();
                            if (i >= 0) {
                                ArrayList<Integer> id = new ArrayList<Integer>();
                                ArrayList<Integer> donGiaGoc = new ArrayList<Integer>();

                                int maPhieu = Integer
                                        .parseInt(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 1).toString());
                                donGiaGoc.add(Integer
                                        .parseInt(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 4).toString()));
                                id.add(Integer
                                        .parseInt(jtbChiTietPhieuNhapHang.getModel().getValueAt(i, 2).toString()));

                                CTPNHArr.removeByMaSP(maPhieu, id, donGiaGoc);
                                getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                            }
                        }
                    }
                }
            };

            suaButton.addActionListener(listener);
            xoaButton.addActionListener(listener);
        }

        public void addActionListener(ActionListener listener) {
            suaButton.addActionListener(listener);
            xoaButton.addActionListener(listener);
        }

        public String getState() {
            return state;
        }
    }

    public class AcceptRejectRenderer extends DefaultTableCellRenderer {

        private AcceptRejectPane suaButtonRejectPane;

        public AcceptRejectRenderer() {
            suaButtonRejectPane = new AcceptRejectPane();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (isSelected) {
                suaButtonRejectPane.setBackground(table.getSelectionBackground());
            } else {
                suaButtonRejectPane.setBackground(table.getBackground());
            }
            return suaButtonRejectPane;
        }
    }

    public class AcceptRejectEditor extends AbstractCellEditor implements TableCellEditor {

        private AcceptRejectPane suaButtonRejectPane;

        public AcceptRejectEditor() {
            suaButtonRejectPane = new AcceptRejectPane();
            suaButtonRejectPane.addActionListener(new ActionListener() {
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
            return suaButtonRejectPane.getState();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            if (isSelected) {
                suaButtonRejectPane.setBackground(table.getSelectionBackground());
            } else {
                suaButtonRejectPane.setBackground(table.getBackground());
            }
            return suaButtonRejectPane;
        }
    }
}