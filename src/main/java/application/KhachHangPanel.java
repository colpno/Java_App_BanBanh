package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

import businesslayer.ConfigTable;
import businesslayer.DungChung;
import businesslayer.KhachHang;
import businesslayer.MangKhachHang;
import businesslayer.MangTaiKhoan;
import businesslayer.TaiKhoan;
import test.InputTester;

public class KhachHangPanel extends JPanel implements MouseListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel jlbMaKH, jlbMaTK, jlbHo, jlbTen, jlbNgaySinh, jlbDiaChi, jlbSDT, jlbTrang, jlbThem, jlbSua,
            jlbTimKiem, jlbNext, jlbPrev, jlbOk, jlbCancel, jlbFirst, jlbLast, jlbLocTu, jlbLocDen, jlbMore,
            jlbMoreNext, jlbMorePrev, jlbMoreFirst, jlbMoreLast, jlbMoreTrang;
    private JTextField jtfMaKH, jtfHo, jtfTen, jtfDiaChi, jtfSDT, jtfLocTu, jtfLocDen, jtfTim, jtfMaTK, jtfMore;
    private JButton jbtLoc, jbtGhiExcel, jbtDocExcel, jbtSapXep, jbtMore, jbtExportPDF;
    private JDateChooser jdcNgaySinh;
    private Icon themIcon, suaIcon, xoaIcon, timIcon, firstIcon, prevIcon, nextIcon, lastIcon, filterIcon, okIcon,
            cancelIcon, importIcon, exportIcon, sortIcon, pdfIcon;
    private JScrollPane jscpKhachHang, jscpMore;
    private JTable jtbKhachHang, jtbMore;
    private DefaultTableModel model, jmdMore;
    private JPanel jpnLocDen, jpnLocTu, jpnLoc, jpnPhanTrang, jpnTim, jpnTrangTim, jpnLeftInput, jpnRightInput,
            jpnCommand, jpnSort, jpnMoreHeader, jpnMoreFooter;
    private JComboBox<String> jcbLoc, jcbTim, jcbCotSapXep, jcbKieuSapXep, jcbMore;
    private JFrame jfMore;
    private JSeparator jspLoc1, jspLoc2;

    private EtchedBorder etchedBorder = new EtchedBorder(EtchedBorder.LOWERED, new Color(0xe2e2e2),
            new Color(0xe2e2e2));
    private Color mainColor = new Color(0xfc498e);

    private MangKhachHang KHArr = new MangKhachHang();
    private MangTaiKhoan TKArr = new MangTaiKhoan();
    private String[] titles = KHArr.title();
    private int soRowMoiTrang = 13, soRowToiDaMoiTrang = 18, soRowToiThieuMoiTrang = soRowMoiTrang;
    private int soRowMore = 6;

    public KhachHangPanel() {
        initComponents();
    }

    private void initComponents() {

        jscpKhachHang = new JScrollPane();

        model = new DefaultTableModel();
        jmdMore = new DefaultTableModel();

        jtbKhachHang = new JTable(model);
        jtbMore = new JTable();

        jlbMaKH = new JLabel("Mã khách hàng");
        jlbMaTK = new JLabel("Mã tài khoản");
        jlbHo = new JLabel("Họ");
        jlbTen = new JLabel("Tên");
        jlbNgaySinh = new JLabel("Ngày sinh");
        jlbDiaChi = new JLabel("Địa chỉ");
        jlbSDT = new JLabel("Số điện thoại");
        jlbTrang = new JLabel("1");
        jlbLocTu = new JLabel("Từ  ");
        jlbLocDen = new JLabel("Đến");
        jlbMoreTrang = new JLabel("1");

        jtfMaKH = new JTextField();
        jtfHo = new JTextField();
        jtfTen = new JTextField();
        jdcNgaySinh = new JDateChooser();
        jtfDiaChi = new JTextField();
        jtfSDT = new JTextField();
        jtfTim = new JTextField();
        jtfLocTu = new JTextField();
        jtfLocDen = new JTextField();
        jtfMaTK = new JTextField();
        jtfMore = new JTextField();

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

        jbtLoc = new JButton("Lọc", filterIcon);
        jbtGhiExcel = new JButton("Ghi Excel", exportIcon);
        jbtDocExcel = new JButton("Đọc Excel", importIcon);
        jbtSapXep = new JButton("Sắp xếp", sortIcon);
        jbtExportPDF = new JButton("Export PDF", pdfIcon);
        jbtMore = new JButton("...");

        jcbLoc = new JComboBox<>();
        jcbTim = new JComboBox<>();
        jcbKieuSapXep = new JComboBox<>();
        jcbCotSapXep = new JComboBox<>();
        jcbMore = new JComboBox<String>();

        jpnLocDen = new JPanel();
        jpnLocTu = new JPanel();
        jpnLoc = new JPanel();
        jpnTim = new JPanel();
        jpnPhanTrang = new JPanel();
        jpnTrangTim = new JPanel();
        jpnLeftInput = new JPanel();
        jpnRightInput = new JPanel();
        jpnCommand = new JPanel();
        jpnSort = new JPanel();
        jpnMoreHeader = new JPanel();
        jpnMoreFooter = new JPanel();

        jfMore = new JFrame();

        jspLoc1 = new JSeparator();
        jspLoc2 = new JSeparator();
        jscpMore = new JScrollPane();

        /*
         * SELF
         */
        setBackground(new Color(0xf1f5f9));

        /*
         * JComboBox
         */
        for (int i = 0; i < titles.length; i++) {
            if (i != 0 && i != titles.length - 1) {
                jcbCotSapXep.addItem(titles[i]);
                jcbTim.addItem(titles[i]);
            }
        }

        jcbCotSapXep.setBackground(Color.WHITE);
        jcbCotSapXep.setBorder(new EmptyBorder(4, 15, 0, 15));

        jcbKieuSapXep.setBackground(Color.WHITE);
        jcbKieuSapXep.setBorder(new EmptyBorder(4, 15, 4, 15));
        jcbKieuSapXep.addItem("Tăng dần");
        jcbKieuSapXep.addItem("Giảm dần");

        jtfMaTK.setBackground(Color.WHITE);

        jcbLoc.addItem("Mã NV");
        jcbLoc.addItem("Mã TK");
        jcbLoc.addItem("Ngày sinh");
        jcbLoc.setBackground(Color.WHITE);

        jcbTim.setBackground(Color.WHITE);

        jcbMore.setBackground(Color.WHITE);

        /*
         * JSeparate
         */
        jspLoc1.setPreferredSize(new Dimension(10, 30));
        jspLoc1.setOrientation(SwingConstants.VERTICAL);
        jspLoc2.setPreferredSize(new Dimension(10, 30));
        jspLoc2.setOrientation(SwingConstants.VERTICAL);

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
        jpnTrangTim.add(jpnPhanTrang);
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

        jpnSort.setBackground(Color.WHITE);
        jpnSort.setLayout(new BoxLayout(jpnSort, BoxLayout.Y_AXIS));
        jpnSort.setBorder(etchedBorder);
        jpnSort.add(jcbCotSapXep);
        jpnSort.add(jcbKieuSapXep);
        jpnSort.add(jbtSapXep);

        jpnLeftInput.setBorder(etchedBorder);
        jpnRightInput.setBorder(etchedBorder);

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

        /*
         * JFrame
         */
        jfMore.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jfMore.setVisible(false);
        jfMore.setTitle("Tài khoản");
        jfMore.setSize(1000, 500);
        jfMore.setLayout(new BorderLayout());
        jfMore.add(jpnMoreHeader, BorderLayout.NORTH);
        jfMore.add(jscpMore, BorderLayout.CENTER);
        jfMore.add(jpnMoreFooter, BorderLayout.SOUTH);

        /*
         * JTextField
         */
        jtfLocTu.setPreferredSize(new Dimension(100, 30));
        jtfLocDen.setPreferredSize(new Dimension(100, 30));

        jtfMaKH.setEnabled(false);
        jtfMaKH.setBackground(new Color(245, 245, 245));
        jtfMaKH.setDisabledTextColor(Color.BLACK);

        jtfTim.setPreferredSize(new Dimension(300, 25));
        jtfTim.setBackground(Color.WHITE);

        jdcNgaySinh.setDateFormatString("dd/MM/yyyy");

        jtfLocTu.setPreferredSize(new Dimension(100, 30));
        jtfLocDen.setPreferredSize(new Dimension(100, 30));

        jtfMore.setPreferredSize(new Dimension(300, 30));

        /*
         * JLabel
         */
        jlbSua.setVisible(false);
        jlbOk.setVisible(false);
        jlbCancel.setVisible(false);

        jlbMaKH.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbMaTK.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbHo.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbTen.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbSDT.setFont(new java.awt.Font("Tahoma", 0, 16));
        jlbTrang.setFont(new java.awt.Font("Tahoma", 0, 18));

        /*
         * JButton
         */
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

        jbtMore.setPreferredSize(new Dimension(20, 25));

        /*
         * JScrollPane && JTable
         */
        getTableData(soRowMoiTrang, 1);
        jscpKhachHang.setViewportView(jtbKhachHang);

        jtbKhachHang.setPreferredScrollableViewportSize(jtbKhachHang.getPreferredSize());
        jtbKhachHang.setFillsViewportHeight(true);

        /*
         * Set Cursor
         */
        jlbThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbFirst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbPrev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtDocExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtGhiExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtExportPDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtLoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtSapXep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbtMore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMoreFirst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMorePrev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMoreNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlbMoreLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /*
         * LEFT INPUT
         */
        GridBagConstraints gbc;
        jpnLeftInput.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        jpnLeftInput.add(jlbMaKH, gbc);

        jtfMaKH.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnLeftInput.add(jtfMaKH, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnLeftInput.add(jlbMaTK, gbc);

        JPanel jpnMaTK = new JPanel();
        jbtMore = new JButton("...");
        jpnMaTK.add(jtfMaTK);
        jpnMaTK.add(jbtMore);
        jpnMaTK.setBackground(Color.WHITE);
        jpnMaTK.setBorder(new EmptyBorder(-5, -5, -5, -5));
        jbtMore.setPreferredSize(new Dimension(20, 25));
        jtfMaTK.setPreferredSize(new Dimension(265, 25));
        jtfMaTK.setBackground(Color.WHITE);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        jpnLeftInput.add(jpnMaTK, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnLeftInput.add(jlbHo, gbc);

        jtfHo.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 10);
        jpnLeftInput.add(jtfHo, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnLeftInput.add(jlbTen, gbc);

        jtfTen.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 10, 10);
        jpnLeftInput.add(jtfTen, gbc);

        /*
         * RIGHT INPUT
         */
        jpnRightInput.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        jpnRightInput.add(jlbNgaySinh, gbc);

        jdcNgaySinh.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 10);
        jpnRightInput.add(jdcNgaySinh, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnRightInput.add(jlbDiaChi, gbc);

        jtfDiaChi.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        jpnRightInput.add(jtfDiaChi, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        jpnRightInput.add(jlbSDT, gbc);

        jtfSDT.setPreferredSize(new Dimension(290, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 10);
        jpnRightInput.add(jtfSDT, gbc);

        /*
         * Main layout
         */
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(jbtGhiExcel).addComponent(jbtDocExcel)
                        .addComponent(jbtExportPDF))
                .addGroup(layout.createSequentialGroup().addComponent(jpnLeftInput).addGap(13, 13, 13)
                        .addComponent(jpnCommand, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13).addComponent(jpnRightInput))
                .addGroup(layout.createSequentialGroup().addComponent(jpnTrangTim).addGap(13, 13, 13)
                        .addComponent(jpnSort).addGap(13, 13, 13)
                        .addComponent(jpnLoc, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addComponent(jscpKhachHang));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jbtGhiExcel)
                        .addComponent(jbtDocExcel).addComponent(jbtExportPDF))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpnRightInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(jpnLeftInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpnCommand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jpnTrangTim, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jpnSort, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jpnLoc, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13).addComponent(jscpKhachHang)));

        setLayout(layout);

        jtbKhachHang.getTableHeader().addMouseListener(this);
        jtbKhachHang.addMouseListener(this);
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
        jbtExportPDF.addMouseListener(this);
        jbtDocExcel.addMouseListener(this);
        jbtSapXep.addMouseListener(this);
        jtbMore.addMouseListener(this);
        jlbMoreFirst.addMouseListener(this);
        jlbMorePrev.addMouseListener(this);
        jlbMoreNext.addMouseListener(this);
        jlbMoreLast.addMouseListener(this);

        jbtMore.addActionListener(this);
    }

    public void frameMaximized() {
        this.soRowMoiTrang = this.soRowToiDaMoiTrang;
        getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
    }

    public void frameRestore() {
        this.soRowMoiTrang = this.soRowToiThieuMoiTrang;
        getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
    }

    public void getTableData(int soRowMoiTrang, int trang) {
        model = ConfigTable.addTableHeader(titles);

        ArrayList<KhachHang> DSKH = new ArrayList<KhachHang>();
        DSKH = KHArr.get(soRowMoiTrang, trang);
        int length = DSKH.size();
        for (int i = 0; i < length; i++) {
            Vector<Object> row = new Vector<Object>();
            row.add(Boolean.FALSE);
            row.add(DSKH.get(i).getMaKH());
            row.add(DSKH.get(i).getMaTK());
            row.add(DSKH.get(i).getHo());
            row.add(DSKH.get(i).getTen());
            row.add(DSKH.get(i).getNgaySinh());
            row.add(DSKH.get(i).getDiaChi());
            row.add(DSKH.get(i).getSdt());
            model.addRow(row);
        }
        jtbKhachHang.setModel(model);

        retable();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object eSrc = e.getSource();
        if (eSrc == jtbKhachHang.getTableHeader()) {
            int col = jtbKhachHang.columnAtPoint(e.getPoint());
            if (col == 0) {
                ArrayList<Integer> checkedID = new ArrayList<Integer>();
                Boolean coCheck = false;
                for (int i = 0; i < jtbKhachHang.getRowCount(); i++) {
                    Boolean isChecked = Boolean.valueOf(jtbKhachHang.getValueAt(i, 0).toString());
                    if (isChecked) {
                        Integer id = Integer.parseInt(jtbKhachHang.getValueAt(i, 1).toString());
                        coCheck = true;

                        checkedID.add(id);
                    }
                }
                if (coCheck == true) {
                    KHArr.remove(checkedID);
                    getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 ô để xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }
        if (eSrc == jtbKhachHang) {
            int i = jtbKhachHang.getSelectedRow();
            if (i >= 0) {
                jtfMaKH.setText(jtbKhachHang.getModel().getValueAt(i, 1).toString());
                jtfMaTK.setText(jtbKhachHang.getModel().getValueAt(i, 2).toString());
                jtfHo.setText(jtbKhachHang.getModel().getValueAt(i, 3).toString());
                jtfTen.setText(jtbKhachHang.getModel().getValueAt(i, 4).toString());
                try {
                    jdcNgaySinh.setDate(new SimpleDateFormat("dd/MM/yyy")
                            .parse(jtbKhachHang.getModel().getValueAt(i, 5).toString()));
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                jtfDiaChi.setText(jtbKhachHang.getModel().getValueAt(i, 6).toString());
                jtfSDT.setText(jtbKhachHang.getModel().getValueAt(i, 7).toString());
            }
            return;
        }
        if (eSrc == jtbMore) {
            int i = jtbMore.getSelectedRow();
            if (i >= 0) {
                jtfMaTK.setText(jtbMore.getModel().getValueAt(i, 1).toString());
            }
            return;
        }
        if (eSrc == jbtGhiExcel) {
            KHArr.ghiFile();
        }
        if (eSrc == jbtDocExcel) {
            ArrayList<KhachHang> arr = KHArr.docFile();
            KHArr.setArr(arr);
            getTableData(soRowMoiTrang, 1);
            jlbTrang.setText("1");
        }
        if (eSrc == jbtExportPDF) {
            KHArr.pdf();
        }

        String maKH = jtfMaKH.getText(), maTK = jtfMaTK.getText(), ho = jtfHo.getText(), ten = jtfTen.getText(),
                diaChi = jtfDiaChi.getText(), sdt = jtfSDT.getText(), ngaySinh = "";
        try {
            String rawDate = jdcNgaySinh.getDate() != null ? DateFormat.getDateInstance().format(jdcNgaySinh.getDate())
                    : "";
            if (!rawDate.equals("")) {
                Date parsed = new SimpleDateFormat("MMM dd, yyyy").parse(rawDate);
                ngaySinh = new SimpleDateFormat("dd/MM/yyyy").format(parsed);
            }
        } catch (ParseException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        if (eSrc == jlbThem) {
            int count = 0;
            if (!InputTester.chiSo(maKH, "Mã KH", 5)) {
                count++;
            }
            if (!InputTester.chiSo(maTK, "Mã TK", 5)) {
                count++;
            }
            if (!InputTester.chiChuVaKhoangTrang(ho, "Họ", 30)) {
                count++;
            }
            if (!InputTester.chiChuVaKhoangTrang(ten, "Tên", 15)) {
                count++;
            }
            if (!InputTester.kiemNgay(ngaySinh, "Ngày sinh")) {
                count++;
            }
            /*
            if (!InputTester.laDiaChi(diaChi, "Địa chỉ", 255)) {
                count++;
            }
            */
            if (!InputTester.soDienThoaiHopLe(sdt, "Số điện thoại", 10)) {
                count++;
            }
            if (count == 0) {
                KHArr.add(Integer.parseInt(maTK), ho, ten, ngaySinh, diaChi, sdt);
                getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                DungChung.resetTextField(jtfMaKH, jtfMaTK, jtfHo, jtfTen, jtfDiaChi, jtfSDT);
                jdcNgaySinh.setDate(null);
            } else
                return;
        }
        if (eSrc == jlbOk) {
            if (!InputTester.chiSo(maKH, "Mã KH", 5)) {
                return;
            }
            if (!InputTester.chiSo(maTK, "Mã TK", 5)) {
                return;
            }
            if (!InputTester.chiChuVaKhoangTrang(ho, "Họ", 30)) {
                return;
            }
            if (!InputTester.chiChuVaKhoangTrang(ten, "Tên", 15)) {
                return;
            }
            if (!InputTester.kiemNgay(ngaySinh, "Ngày sinh")) {
                return;
            }
            /*
            if (!InputTester.laDiaChi(diaChi, "Địa chỉ", 255)) {
                return;
            }
            */
            if (!InputTester.soDienThoaiHopLe(sdt, "Số điện thoại", 10)) {
                return;
            }
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thay đổi?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION) == 0) {

                KHArr.edit(Integer.parseInt(maKH), Integer.parseInt(maTK), ho, ten, ngaySinh, diaChi, sdt);
                getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
                DungChung.resetTextField(jtfMaKH, jtfMaTK, jtfHo, jtfTen, jtfDiaChi, jtfSDT);
                jdcNgaySinh.setDate(null);
                jlbOk.setVisible(false);
                jlbCancel.setVisible(false);
                jlbThem.setVisible(true);
            }
        }
        if (eSrc == jlbCancel) {
            jtfMaTK.setEditable(true);
            jtfMaTK.setBackground(Color.WHITE);
            jbtMore.setEnabled(true);
            jbtMore.addMouseListener(this);
            jlbThem.setVisible(true);
            jlbOk.setVisible(false);
            jlbCancel.setVisible(false);
            DungChung.resetTextField(jtfMaKH, jtfMaTK, jtfHo, jtfTen, jtfDiaChi, jtfSDT);
            jdcNgaySinh.setDate(null);
        }
        if (eSrc == jlbTimKiem) {
            String col = jcbTim.getSelectedItem().toString();
            String giaTriTim = jtfTim.getText();

            model = ConfigTable.addTableHeader(titles);

            ArrayList<KhachHang> khArr = KHArr.search(col, giaTriTim);
            for (KhachHang kh : khArr) {
                Vector<Object> row = new Vector<Object>();
                row.add(Boolean.FALSE);
                row.add(kh.getMaKH());
                row.add(kh.getMaTK());
                row.add(kh.getHo());
                row.add(kh.getTen());
                row.add(kh.getNgaySinh());
                row.add(kh.getDiaChi());
                row.add(kh.getSdt());
                model.addRow(row);
            }
            jtbKhachHang.setModel(model);
            retable();
        }
        if (eSrc == jbtLoc)

        {
            String from = jtfLocTu.getText();
            String to = jtfLocDen.getText();
            int count = 0;
            if (InputTester.chuoiNull(from, "Từ")) {
                count++;
            }
            if (InputTester.chuoiNull(to, "Đến")) {
                count++;
            }
            String col = "";
            switch (jcbLoc.getSelectedItem().toString()) {
                case "Mã KH":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Mã TK":
                    if (!InputTester.chiSo(from, "Từ", 5)) {
                        count++;
                    }
                    if (!InputTester.chiSo(to, "Đến", 5)) {
                        count++;
                    }
                    break;
                case "Ngày sinh":
                    if (!InputTester.kiemNgay(from, "Từ")) {
                        count++;
                    }
                    if (!InputTester.kiemNgay(to, "Đến")) {
                        count++;
                    }
                    break;
            }

            if (count == 0) {
                DefaultTableModel newModel = ConfigTable.addTableHeader(titles);

                ArrayList<KhachHang> arr = KHArr.filter(col, from, to);
                if (arr.size() > 0) {
                    for (KhachHang kh : arr) {
                        Vector<Object> row = new Vector<Object>();
                        row.add(Boolean.FALSE);
                        row.add(kh.getMaKH());
                        row.add(kh.getMaTK());
                        row.add(kh.getHo());
                        row.add(kh.getTen());
                        row.add(kh.getNgaySinh());
                        row.add(kh.getDiaChi());
                        row.add(kh.getSdt());
                        newModel.addRow(row);
                    }
                    jtbKhachHang.setModel(newModel);
                    retable();
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu trùng khớp với khoảng lọc", "Thông tin",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (eSrc == jbtSapXep) {
            String col = jcbCotSapXep.getSelectedItem().toString();
            String order = jcbKieuSapXep.getSelectedItem().toString();
            KHArr.sort(col, order);
            getTableData(soRowMoiTrang, 1);
        }
        if (eSrc == jlbFirst) {
            if (!jlbTrang.getText().equals("1")) {
                getTableData(soRowMoiTrang, 1);
                jlbTrang.setText("1");
            } else {
                JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
            }
        }
        if (eSrc == jlbPrev) {
            if (!jlbTrang.getText().equals("1")) {
                int trangTruoc = Integer.parseInt(jlbTrang.getText()) - 1;
                getTableData(soRowMoiTrang, trangTruoc);
                jlbTrang.setText(Integer.toString(trangTruoc));
            } else {
                JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang kế tiếp", "Lỗi", 0);
            }
        }
        if (eSrc == jlbNext) {
            int trangCuoi = (int) KHArr.getTongSoTrang(soRowMoiTrang);
            if (!jlbTrang.getText().equals(Integer.toString(trangCuoi))) {
                int trangSau = Integer.parseInt(jlbTrang.getText()) + 1;
                jlbTrang.setText(Integer.toString(trangSau));
                getTableData(soRowMoiTrang, trangSau);
            } else {
                JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
            }
        }
        if (eSrc == jlbLast) {
            int trangCuoi = (int) KHArr.getTongSoTrang(soRowMoiTrang);
            if (!jlbTrang.getText().equals(Integer.toString(trangCuoi))) {
                getTableData(soRowMoiTrang, trangCuoi);
                jlbTrang.setText(Integer.toString(trangCuoi));
            } else {
                JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);
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

    private void renderOtherJTableData() {
        AcceptRejectRenderer renderBtn = new AcceptRejectRenderer();
        jtbKhachHang.getColumnModel().getColumn(titles.length - 1).setCellRenderer(renderBtn);
        jtbKhachHang.getColumnModel().getColumn(titles.length - 1).setCellEditor(new AcceptRejectEditor());
    }

    private void retable() {
        renderOtherJTableData();

        int[] colsGetCenter = { 0, 1, 2, 5, 7 };
        String[] setWidthOptions = { "p-50", "p-50", "p-50", "mi-170", "p-100", "p-100", "mi-300", "p-100", "mi-100" };
        ConfigTable.decorateTable(jtbKhachHang, colsGetCenter, titles.length, setWidthOptions);
    }

    private class RenderMoreTableTK {
        private String[] tkTitle = { "Ảnh đại diện", "Mã TK", "Mã quyền", "Tài khoản", "Mật khẩu", "Cấm" };

        protected void renderHeader() {
            jcbMore.removeAllItems();

            int titleLength = tkTitle.length;
            Vector<String> header = new Vector<String>();
            for (int i = 0; i < titleLength; i++) {
                header.add(tkTitle[i]);
                if (i != 0 && i != 4) {
                    jcbMore.addItem(tkTitle[i]);
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
            ArrayList<TaiKhoan> tkArr = TKArr.get(soRow, trang);
            for (TaiKhoan tk : tkArr) {
                Vector<Object> row = new Vector<Object>();
                JLabel anhDaiDien = new JLabel();
                String anhDaiDienString = tk.getAnhDaiDien();
                String extension = anhDaiDienString.substring(anhDaiDienString.indexOf('.') + 1);
                anhDaiDien.setSize(70, 70);
                ImageIcon ii = new ImageIcon(
                        new ImageIcon("image/TaiKhoan/TK-" + tk.getMaTK() + "." + extension).getImage()
                                .getScaledInstance(anhDaiDien.getWidth(), anhDaiDien.getHeight(), Image.SCALE_SMOOTH));
                anhDaiDien.setIcon(ii);

                row.add(anhDaiDien);
                row.add(tk.getMaTK());
                row.add(tk.getMaQuyen());
                row.add(tk.getTenTaiKhoan());
                row.add(tk.getMatKhau());
                row.add(tk.getTrangThai());
                jmdMore.addRow(row);
            }
            jtbMore.setModel(jmdMore);
            retable();
        }

        protected void renderListRow(int soRow, int trang, ArrayList<TaiKhoan> list) {
            for (TaiKhoan tk : list) {
                Vector<Object> row = new Vector<Object>();

                JLabel anhDaiDien = new JLabel();
                String anhDaiDienString = tk.getAnhDaiDien();
                String extension = anhDaiDienString.substring(anhDaiDienString.indexOf('.') + 1);
                anhDaiDien.setSize(70, 70);
                ImageIcon ii = new ImageIcon(
                        new ImageIcon("image/TaiKhoan/TK-" + tk.getMaTK() + "." + extension).getImage()
                                .getScaledInstance(anhDaiDien.getWidth(), anhDaiDien.getHeight(), Image.SCALE_SMOOTH));
                anhDaiDien.setIcon(ii);

                row.add(anhDaiDien);
                row.add(tk.getMaTK());
                row.add(tk.getMaQuyen());
                row.add(tk.getTenTaiKhoan());
                row.add(tk.getMatKhau());
                row.add(tk.getTrangThai());
                jmdMore.addRow(row);
            }
            jtbMore.setModel(jmdMore);
            retable();
        }

        private void retable() {
            int[] colsGetCenter = { 1, 2, 5 };
            String[] setWidthOptions = { "p-100", "p-50", "p-50", "mi-100", "mi-200", "p-50" };
            ConfigTable.decorateTable(jtbMore, colsGetCenter, tkTitle.length, setWidthOptions);

            jtbMore.getColumnModel().getColumn(0).setCellRenderer(new DungChung.CellRenderer(0, jtbMore));
        }
    }

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
                    int i = jtbKhachHang.getSelectedRow();
                    if (i >= 0) {
                        state = e.getActionCommand();
                        if (state.equals(suaButton.getActionCommand())) {
                            jtfMaTK.setEditable(false);
                            jtfMaTK.setBackground(new Color(245, 245, 245));
                            jbtMore.setEnabled(false);
                            jbtMore.removeActionListener(this);

                            jtfMaKH.setText(jtbKhachHang.getModel().getValueAt(i, 1).toString());
                            jtfMaTK.setText(jtbKhachHang.getModel().getValueAt(i, 2).toString());
                            jtfHo.setText(jtbKhachHang.getModel().getValueAt(i, 3).toString());
                            jtfTen.setText(jtbKhachHang.getModel().getValueAt(i, 4).toString());
                            try {
                                jdcNgaySinh.setDate(new SimpleDateFormat("dd/MM/yyy")
                                        .parse(jtbKhachHang.getModel().getValueAt(i, 5).toString()));
                            } catch (ParseException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                            jtfDiaChi.setText(jtbKhachHang.getModel().getValueAt(i, 6).toString());
                            jtfSDT.setText(jtbKhachHang.getModel().getValueAt(i, 7).toString());

                            jlbOk.setVisible(true);
                            jlbCancel.setVisible(true);
                            jlbThem.setVisible(false);
                        }
                        if (state.equals(xoaButton.getActionCommand())) {
                            ArrayList<Integer> id = new ArrayList<Integer>();
                            id.add(Integer.parseInt(jtbKhachHang.getModel().getValueAt(i, 1).toString()));
                            KHArr.remove(id);
                            getTableData(soRowMoiTrang, Integer.parseInt(jlbTrang.getText()));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtMore) {
            jtfMore.setText("");

            RenderMoreTableTK moreTK = new RenderMoreTableTK();
            moreTK.renderHeader();
            moreTK.renderRow(soRowMore, 1);

            jscpMore.setViewportView(jtbMore);
            jfMore.setLocationRelativeTo(null);
            jfMore.setVisible(true);

            jlbMore.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String col = jcbMore.getSelectedItem().toString();
                    String giaTriTim = jtfMore.getText();
                    if (giaTriTim.equals("")) {
                        moreTK.renderHeader();
                        moreTK.renderRow(soRowMore, 1);

                        return;
                    }
                    moreTK.renderHeader();

                    ArrayList<TaiKhoan> nccArr = TKArr.search(col, giaTriTim);
                    moreTK.renderListRow(soRowMore, Integer.parseInt(jlbMoreTrang.getText()), nccArr);
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
}
