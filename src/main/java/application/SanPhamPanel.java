
package application;

import businesslayer.Loai;
import businesslayer.MangLoai;
import businesslayer.Excel;
import businesslayer.MangSanPham;
import businesslayer.NhaSanXuat;
import businesslayer.SanPham;

import test.InputTester;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SanPhamPanel extends JPanel implements ActionListener, MouseListener {

    private String path = "image/SanPham/";
    
    
    
    
    private String filename = "";
    private String pathExcel = "excel/sanpham.xlsx";
    private String pathPDF = "pdf/sanpham.pdf";

    private ImageIcon icon1 = new ImageIcon("image/Editor_16.png");
    private ImageIcon icon2 = new ImageIcon("image/Delete_16.png");

    private int soLuong = 5;
    private JTable table, tableLoai, tableNSX;
    private DefaultTableModel model, model1, modelLoai, modelLoai1;
    private JScrollPane scroll, sc, scrollLoai;
    private JPanel pSP, pCenter, pTop, pTopLeft, pTopRight, pButton, pForm, pMainForm, pAnh, pFormAll, pBottom, pPrice,
            pFormSearch, pMainSearch, pLoai, pLoaiSearch, pFormBt, pLink, pLoaiTop;

    private String[] ttSP = { "", "Hình Ảnh", "Mã SP", "Tên SP", "Tên Loại", "Tên NSX", "SL", "Đơn Giá", "PTKM",
            "Giá KM", "Đơn Vị", "Chức Năng" };
    private int[] sizeSP = { 50, 180, 80, 200, 160, 160, 80, 120, 80, 120, 80, 150 };
    private String[] lbSP = { "Mã sản phẩm", "Tên sản phẩm", "Mã loại", "Tên loại", "Mã NSX", "Tên NSX", "Số lượng",
            "Đơn giá", "Đơn vị tính", "Mô tả" };
    private String[] lbLoai = { "Mã loại", "Tên loai" };
    private int[] sizeLoai = { 300, 600 };
    private int[] sizeNSX = { 100, 200, 400, 100 };
    private JLabel[] lb = new JLabel[lbSP.length];
    private String[] lbNSX = { "Mã NSX", "Tên NSX", "Địa chỉ", "SĐT" };
    private JLabel lbAnh, lbTrang, l9, l10, l11, l12, l13;
    private JTextArea taMoTa;
    private JTextField txtMaSP, txtTenSP, txtMaLoai, txtTenLoai, txtMaNSX, txtTenNSX, txtSL, txtDG, txtTK, txtPath,
            txtFrom, txtTo, txtSearchLoai;
    private JButton btThem, btAll, btSP, btSua, btXN, btTK, btAnh, btAd, btNext, btPrev, btNC, btHTTK, btHTLoai,
            btHTNSX, btLoadLoai, btReturn, btExExcel, btImExcel, btExPDF;
    private JComboBox cbDVT;
    private String[] listDVT = { "Cái", "Kg", "Gói", "Thùng" };
    private Vector dvt = new Vector();

    private ArrayList<Loai> loaiList = new ArrayList<Loai>();
    private ArrayList<NhaSanXuat> nsxList = new ArrayList<NhaSanXuat>();
    private ArrayList<JCheckBox> loai = new ArrayList<JCheckBox>();
    private ArrayList<JCheckBox> nsx = new ArrayList<JCheckBox>();

    private Excel excel = new Excel();
    private MangLoai loaiArr = new MangLoai();

    private CardLayout card = new CardLayout();
    Font f0 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    Font f1 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    private MangSanPham spArr = new MangSanPham();

    private Color color1 = Color.WHITE;
    private Color color2 = new Color(222, 222, 221);

    public SanPhamPanel() {

        initComponents();
        loadAllSP(soLuong, 1);

    }

    public void initComponents() {

        this.setLayout(card);
        this.setBackground(color1);
        this.setPreferredSize(new Dimension(1080, 600));

        pSP = myPanel(new BorderLayout(), color1);
        pTop = myPanel(new BorderLayout(), color1);
        pTopLeft = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);
        pTopRight = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);

        btThem = myButton("Thêm", 100, 30, f1);
        btAd = myButton("Tìm nâng cao", 120, 30, f1);
        btAll = myButton("Hiển thị tất cả", 140, 30, f1);
        btTK = myButton("Tìm kiếm", 100, 30, f1);
        btImExcel = myButton("Import Excel", 100, 30, f1);
        btExExcel = myButton("Export Excel", 100, 30, f1);
        btExPDF = myButton("Export PDF", 100, 30, f1);

        txtTK = myTextField(15, f2);
        txtTK.setBackground(color1);

        pTopLeft.add(btThem);
        pTopLeft.add(btAd);
        pTopLeft.add(btAll);
        pTopLeft.add(btImExcel);
        pTopLeft.add(btExExcel);
        pTopLeft.add(btExPDF);

        pTopRight.add(txtTK);
        pTopRight.add(btTK);

        pTop.add(pTopLeft, BorderLayout.LINE_START);
        pTop.add(pTopRight, BorderLayout.LINE_END);

        table = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent) comp;
                jc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
                if (!comp.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? color1 : color2);
                    comp.setBackground(c);
                    c = null;
                }
                return comp;
            }
        };

        table.setRowHeight(100);
        table.setFont(f2);

        JTableHeader header = table.getTableHeader();
        header.setOpaque(false);
        header.setBackground(color2);
        header.setFont(f1);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        table.setPreferredScrollableViewportSize(new Dimension(1000, 600));
        table.setFillsViewportHeight(true);
        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        lbTrang = myLabel("1", f2);
        btNext = new JButton("Next");
        btPrev = new JButton("Prev");
        btNext.addActionListener(this);
        btPrev.addActionListener(this);
        pBottom = new JPanel(new FlowLayout());
        pBottom.setBackground(color1);

        pBottom.add(btPrev);
        pBottom.add(lbTrang);
        pBottom.add(btNext);

        pSP.add(pTop, BorderLayout.NORTH);
        pSP.add(scroll, BorderLayout.CENTER);
        pSP.add(pBottom, BorderLayout.SOUTH);

        formSP();

        formSearch();

        formLoai();

        table.setBackground(color2);

        pMainForm.setBackground(color1);

        this.add(pSP, "sp");
        this.add(pMainForm, "form");
        this.add(pMainSearch, "search");
        this.add(pLoai, "loai");

    }

    public void formSP() {

        pMainForm = myPanel(new BorderLayout(), color2);
        pFormBt = myPanel(new FlowLayout(FlowLayout.LEFT), color1);
        pFormAll = myPanel((new BorderLayout()), color2);
        pMainForm.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));

        btSP = myButton("Danh sách SP", 150, 30, f1);
        btXN = myButton("Xác Nhận", 120, 30, f1);

        pFormBt.add(btSP);
        pFormBt.add(btXN);

        GridBagLayout layout = new GridBagLayout();
        pForm = myPanel(layout, color2);
        pAnh = myPanel(new BorderLayout(), color2);

        pAnh.setPreferredSize(new Dimension(370, 0));
        pAnh.setBorder(BorderFactory.createEmptyBorder(40, 10, 120, 10));
        pLink = myPanel(new FlowLayout(FlowLayout.CENTER), color2);

        lbAnh = myLabel("", f1);
        lbAnh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        txtPath = myTextField(15, f2);
        btAnh = myButton("Tải ảnh", 100, 30, f2);

        pLink.add(txtPath);
        pLink.add(btAnh);

        pAnh.add(lbAnh, BorderLayout.CENTER);
        pAnh.add(pLink, BorderLayout.SOUTH);

        for (int i = 0; i < lb.length; i++) {
            lb[i] = myLabel(lbSP[i], f1);
        }

        txtMaSP = myTextField(6, f2);
        txtMaSP.setEditable(false);
        txtTenSP = myTextField(20, f2);
        txtMaLoai = myTextField(6, f2);
        txtTenLoai = myTextField(12, f2);
        txtMaNSX = myTextField(6, f2);
        txtTenNSX = myTextField(12, f2);
        txtSL = myTextField(8, f2);
        txtDG = myTextField(8, f2);

        for (int i = 0; i < listDVT.length; i++) {
            dvt.add(listDVT[i]);
        }
        cbDVT = myComboBox(dvt, f2);

        taMoTa = new JTextArea(5, 30);
        taMoTa.setLineWrap(true);
        taMoTa.setWrapStyleWord(true);
        taMoTa.setFont(f2);
        taMoTa.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        taMoTa.setBackground(color2);
        sc = new JScrollPane(taMoTa);

        txtMaLoai.setEditable(false);
        txtTenLoai.setEditable(false);
        txtMaNSX.setEditable(false);
        txtTenNSX.setEditable(false);

        btHTLoai = myButton("...", 30, 20, f0);
        btHTNSX = myButton("...", 30, 20, f0);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        pForm.add(lb[0], gbc);

        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.gridy++;
        pForm.add(lb[1], gbc);

        gbc.gridy++;
        pForm.add(lb[2], gbc);

        gbc.insets = new Insets(30, 60, 0, 0);
        gbc.gridx += 3;
        pForm.add(lb[3], gbc);

        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pForm.add(lb[4], gbc);

        gbc.insets = new Insets(30, 60, 0, 0);
        gbc.gridx += 3;
        pForm.add(lb[5], gbc);

        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pForm.add(lb[6], gbc);

        gbc.insets = new Insets(30, 60, 0, 0);
        gbc.gridx += 3;
        pForm.add(lb[7], gbc);

        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pForm.add(lb[8], gbc);

        gbc.gridy++;
        pForm.add(lb[9], gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 20, 0, 0);
        pForm.add(txtMaSP, gbc);

        gbc.insets = new Insets(30, 20, 0, 0);
        gbc.gridy++;
        gbc.gridwidth = 4;
        pForm.add(txtTenSP, gbc);

        gbc.gridy++;
        pForm.add(txtMaLoai, gbc);

        gbc.gridx++;
        pForm.add(btHTLoai, gbc);

        gbc.gridx += 2;
        pForm.add(txtTenLoai, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        pForm.add(txtMaNSX, gbc);

        gbc.gridx++;
        pForm.add(btHTNSX, gbc);

        gbc.gridx += 2;
        pForm.add(txtTenNSX, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        pForm.add(txtSL, gbc);

        gbc.gridx += 3;
        pForm.add(txtDG, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        pForm.add(cbDVT, gbc);

        gbc.gridy++;
        gbc.gridwidth = 4;
        pForm.add(sc, gbc);

        pFormAll.add(pAnh, BorderLayout.WEST);
        pFormAll.add(pForm, BorderLayout.CENTER);

        TitledBorder title = new TitledBorder("THÔNG TIN SẢN PHẨM");
        title.setTitleFont(f0);

        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.TOP);

        pFormAll.setBorder(BorderFactory.createTitledBorder(title));

        pMainForm.add(pFormAll, BorderLayout.CENTER);
        pMainForm.add(pFormBt, BorderLayout.NORTH);

    }

    public void formSearch() {
        pMainSearch = new JPanel();
        pMainSearch.setLayout(null);
        pMainSearch.setBackground(color1);

        btReturn = myButton("Quay Lại", 100, 30, f1);
        btReturn.setBounds(220, 50, 100, 30);
        GridBagLayout layout = new GridBagLayout();
        pFormSearch = new JPanel(layout);
        pFormSearch.setBackground(color2);
        pFormSearch.setBounds(220, 100, 700, 400);
        pPrice = new JPanel(new FlowLayout(0, 20, FlowLayout.LEFT));

        pPrice.setBackground(color2);

        l9 = myLabel("Đơn giá", f1);
        l10 = myLabel("Từ", f1);
        l11 = myLabel("Đến", f1);
        l12 = myLabel("Loại bánh", f1);
        l13 = myLabel("Nhà sản xuất", f1);

        txtFrom = myTextField(8, f2);
        txtTo = myTextField(8, f2);

        pPrice.add(l10);
        pPrice.add(txtFrom);
        pPrice.add(l11);
        pPrice.add(txtTo);

        loaiList = loaiArr.getAllLoai();
        nsxList = spArr.getAllNSX();

        for (Loai l : loaiList) {
            
            loai.add(myCheckBox(l.getTenLoai()));
        }

        for (NhaSanXuat n : nsxList) {
            nsx.add(myCheckBox(n.getTenNSX()));

        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        pFormSearch.add(l9, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(30, 0, 0, 0);
        pFormSearch.add(l12, gbc);

        gbc.insets = new Insets(30, 20, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 1;
        gbc.gridy = 1;
        int m = 0;
        for (int i = 0; i < loai.size(); i++) {
            pFormSearch.add(loai.get(i), gbc);
            gbc.gridx++;
            m++;
            if (m == 4) {
                gbc.insets = new Insets(10, 20, 0, 0);
                m = 0;
                gbc.gridx = 1;
                gbc.gridy++;

            }

        }

        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy++;
        pFormSearch.add(l13, gbc);

        gbc.insets = new Insets(30, 20, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        int n = 0;
        for (int i = 0; i < nsx.size(); i++) {
            pFormSearch.add(nsx.get(i), gbc);
            gbc.gridx++;
            n++;
            if (n == 4) {
                gbc.insets = new Insets(10, 20, 0, 0);
                m = 0;
                gbc.gridx = 1;
                gbc.gridy++;
            }

        }

        gbc.gridx = 4;
        gbc.gridy++;
        btNC = myButton("Tìm", 100, 30, f1);
        pFormSearch.add(btNC, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;

        pFormSearch.add(pPrice, gbc);

        TitledBorder title = new TitledBorder("TÌM KIẾM NÂNG CAO");
        title.setTitleFont(f0);

        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.TOP);

        pFormSearch.setBorder(BorderFactory.createTitledBorder(title));

        pMainSearch.add(btReturn);
        pMainSearch.add(pFormSearch);

    }

    public void formLoai() {

        pLoai = myPanel(new BorderLayout(), color1);
        pLoai.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        pLoaiTop = myPanel(new BorderLayout(), color1);
        pLoaiSearch = myPanel(new FlowLayout(0, 20, FlowLayout.LEFT), color1);

        txtSearchLoai = myTextField(20, f2);
        txtSearchLoai.setBackground(color1);
        btLoadLoai = myButton("Hiển thị tất cả loại", 150, 30, f2);
        btHTTK = myButton("Tìm loại", 80, 30, f2);

        pLoaiSearch.add(txtSearchLoai);
        pLoaiSearch.add(btHTTK);

        pLoaiTop.add(btLoadLoai, BorderLayout.LINE_START);
        pLoaiTop.add(pLoaiSearch, BorderLayout.LINE_END);

        tableLoai = new JTable(modelLoai) {

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent) comp;
                jc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));

                if (!comp.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? color1 : color2);
                    comp.setBackground(c);
                    c = null;
                }
                return comp;
            }

        };

        tableLoai.setRowHeight(30);
        tableLoai.addMouseListener(this);

        tableLoai.setFont(f2);
        tableLoai.setBackground(color2);

        JTableHeader header = tableLoai.getTableHeader();
        header.setOpaque(false);
        header.setBackground(color2);
        header.setFont(f1);

        ((DefaultTableCellRenderer) tableLoai.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        tableLoai.setPreferredScrollableViewportSize(new Dimension(1190, 400));

        scrollLoai = new JScrollPane(tableLoai);
        tableLoai.setFillsViewportHeight(true);
        scrollLoai.setBackground(color2);
        scrollLoai.setBounds(150, 120, 900, 500);
        scrollLoai.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        pLoai.add(pLoaiTop, BorderLayout.NORTH);
        pLoai.add(scrollLoai, BorderLayout.CENTER);

    }

    public JCheckBox myCheckBox(String s) {
        JCheckBox cbx = new JCheckBox(s);
        cbx.setBackground(color2);
        cbx.setFont(f2);
        cbx.setFocusable(false);
        return cbx;
    }

    public JButton myButton(String name, int width, int height, Font f) {
        JButton btn = new JButton(name);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setFont(f);
        btn.setContentAreaFilled(false);
        btn.setBackground(color2);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        btn.addActionListener(this);
        btn.addMouseListener(this);
        return btn;
    }

    public JLabel myLabel(String name, Font f) {
        JLabel label = new JLabel(name);
        label.setFont(f);
        label.setForeground(Color.black);
        return label;
    }

    public JTextField myTextField(int size, Font f) {
        JTextField txt = new JTextField(size);
        txt.setFont(f);
        txt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setBackground(color2);
        return txt;
    }

    public JComboBox myComboBox(Vector name, Font f) {
        JComboBox cb = new JComboBox();
        cb.setFont(f);
        cb.setModel(new DefaultComboBoxModel(name));
        return cb;
    }

    public JPanel myPanel(LayoutManager layout, Color color) {
        JPanel p = new JPanel();
        p.setLayout(layout);
        p.setBackground(color);
        return p;
    }

    public JTable tableLayout(JTable table, int[] size) {
        String s;
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        if (table.getColumnName(0) == "") {
            TableCellRenderer btRenderer = new ButtonRenderer();

            table.getColumn("Chức Năng").setCellRenderer(btRenderer);
            table.getColumn("Chức Năng").setCellEditor(new ButtonEditor(table));

            TableCellRenderer checkBoxRenderer = new CheckBoxRenderer();
            table.getColumn("").setCellRenderer(checkBoxRenderer);
            table.getColumn("").setCellEditor(new CheckBoxEditor());

            TableCellRenderer labelRenderer = new JTableLabelRenderer();
            table.getColumn("Hình Ảnh").setCellRenderer(labelRenderer);
        }

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(size[i]);
        }
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        table.setColumnSelectionAllowed(false);

        return table;
    }

    public JButton btHoverEnter(JButton bt) {
        bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt.setContentAreaFilled(false);
        bt.setBackground(Color.CYAN);
        bt.setOpaque(true);
        return bt;
    }

    public JButton btHoverExit(JButton bt) {
        bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt.setContentAreaFilled(false);
        bt.setBackground(color2);
        bt.setOpaque(true);
        return bt;
    }

    public void mouseExited(MouseEvent m) {
        if (m.getSource() == btThem) {
            btThem = btHoverExit(btThem);
        }
        if (m.getSource() == btAd) {
            btAd = btHoverExit(btAd);
        }
        if (m.getSource() == btAll) {
            btAll = btHoverExit(btAll);
        }
        if (m.getSource() == btTK) {
            btTK = btHoverExit(btTK);
        }
        if (m.getSource() == btSP) {
            btSP = btHoverExit(btSP);
        }
        if (m.getSource() == btXN) {
            btXN = btHoverExit(btXN);
        }
        if (m.getSource() == btAnh) {
            btAnh = btHoverExit(btAnh);
        }

        if (m.getSource() == btExExcel) {
            btExExcel = btHoverExit(btExExcel);
        }
        if (m.getSource() == btImExcel) {
            btImExcel = btHoverExit(btImExcel);
        }
        if (m.getSource() == btExPDF) {
            btExPDF = btHoverExit(btExPDF);
        }
        if (m.getSource() == btHTLoai) {
            btHTLoai = btHoverExit(btHTLoai);
        }
        if (m.getSource() == btHTNSX) {
            btHTNSX = btHoverExit(btHTNSX);
        }
    }

    public void mouseEntered(MouseEvent m) {
        if (m.getSource() == btThem) {
            btThem = btHoverEnter(btThem);
        }
        if (m.getSource() == btAd) {
            btAd = btHoverEnter(btAd);
        }
        if (m.getSource() == btAll) {
            btAll = btHoverEnter(btAll);
        }
        if (m.getSource() == btTK) {
            btTK = btHoverEnter(btTK);
        }
        if (m.getSource() == btSP) {
            btSP = btHoverEnter(btSP);
        }
        if (m.getSource() == btXN) {
            btXN = btHoverEnter(btXN);
        }
        if (m.getSource() == btAnh) {
            btAnh = btHoverEnter(btAnh);
        }

        if (m.getSource() == btExExcel) {
            btExExcel = btHoverEnter(btExExcel);
        }
        if (m.getSource() == btImExcel) {
            btImExcel = btHoverEnter(btImExcel);
        }
        if (m.getSource() == btExPDF) {
            btExPDF = btHoverEnter(btExPDF);
        }
        if (m.getSource() == btHTLoai) {
            btHTLoai = btHoverEnter(btHTLoai);
        }
        if (m.getSource() == btHTNSX) {
            btHTNSX = btHoverEnter(btHTNSX);
        }

    }

    public void mouseReleased(MouseEvent m) {

    }

    public void mousePressed(MouseEvent m) {

    }

    public void mouseClicked(MouseEvent m) {
        int i = tableLoai.getSelectedRow();
        if (m.getSource() == tableLoai && btHTTK.getText() == "Tìm loại") {
            if (i != -1) {
                txtMaLoai.setText(tableLoai.getModel().getValueAt(i, 0).toString());
                txtTenLoai.setText(tableLoai.getModel().getValueAt(i, 1).toString());

            }
            card.show(this, "form");
        }
        if (m.getSource() == tableLoai && btHTTK.getText() == "Tìm NSX") {
            if (i != -1) {
                txtMaNSX.setText(tableLoai.getModel().getValueAt(i, 0).toString());
                txtTenNSX.setText(tableLoai.getModel().getValueAt(i, 1).toString());

            }
            card.show(this, "form");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Thêm") {
            resetForm();
            card.show(this, "form");

        }
        if (e.getActionCommand() == "Danh sách SP") {
            loadAllSP(soLuong, 1);
            lbTrang.setText("1");
            card.show(this, "sp");

        }
        if (e.getActionCommand() == "Danh sách SP" && btXN.getText().equals("Lưu")) {
            btXN.setText("Xác Nhận");
            card.show(this, "sp");

        }
        if (e.getActionCommand() == "Xác Nhận") {

            int count = 0;

            String tenSP = txtTenSP.getText();
            String maLoai = txtMaLoai.getText();
            String maNSX = txtMaNSX.getText();
            String soLuong = txtSL.getText();
            String donGia = txtDG.getText();

            if (!checkEmpty(tenSP, maLoai, maNSX, soLuong, donGia)) {
                count++;
            }

            if (!InputTester.chiSo(soLuong, "Số Lượng", 10)) {
                count++;
            }
            if (!InputTester.chiSo(donGia, "Đơn Giá", 10)) {
                count++;
            }
            if (count == 0) {

                SanPham sp = new SanPham(Integer.parseInt(txtMaSP.getText()), tenSP, Integer.parseInt(maLoai),
                        Integer.parseInt(maNSX), Integer.parseInt(soLuong), Float.parseFloat(donGia),
                        cbDVT.getSelectedItem().toString(), taMoTa.getText(), filename);
                SanPham kq = spArr.CheckSP(sp);
                if (kq != null) {

                    int qtyNew = kq.getSoLuong() + sp.getSoLuong();
                    int r = spArr.updateQty(kq.getMaSP(), qtyNew);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    resetForm();
                } else {
                    int re = spArr.insertSP(sp);
                    if (re != 0) {
                        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    resetForm();
                }
            }

        }

        if (e.getActionCommand() == "Lưu") {

            int count = 0;

            String tenSP = txtTenSP.getText();
            String maLoai = txtMaLoai.getText();
            String maNSX = txtMaNSX.getText();
            String SL = txtSL.getText();
            String donGia = txtDG.getText();

            if (!checkEmpty(tenSP, maLoai, maNSX, SL, donGia)) {
                count++;
            }

            if (!InputTester.chiSo(SL, "Số Lượng", 10)) {
                count++;
            }
            if (!InputTester.chiSo(donGia, "Đơn Giá", 10)) {
                count++;
            }
            if (count == 0) {
                SanPham sp = new SanPham(Integer.parseInt(txtMaSP.getText()), txtTenSP.getText(),
                        Integer.parseInt(txtMaLoai.getText()), Integer.parseInt(txtMaNSX.getText()),
                        Integer.parseInt(txtSL.getText()), Float.parseFloat(txtDG.getText()),
                        cbDVT.getSelectedItem().toString(), taMoTa.getText(), filename);

                SanPham kq = spArr.CheckSP(sp);
                if (kq != null) {

                    int qtyNew = kq.getSoLuong() + sp.getSoLuong();
                    int r = spArr.updateQty(kq.getMaSP(), qtyNew);
                    spArr.deleteSP(sp.getMaSP());
                    if (r != 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    resetForm();
                } else {

                    int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());

                    int re = spArr.updateSP(sp, id);
                    if (re != 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        loadAllSP(soLuong, Integer.parseInt(lbTrang.getText()));
                        card.show(this, "sp");

                    } else
                        JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thất bại!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }

        if (e.getActionCommand() == "Tìm kiếm") {
            String ten = txtTK.getText();
            txtTK.setText("");

            ArrayList<SanPham> kq = new ArrayList<SanPham>();

            kq = spArr.getSPByTen(ten);
            table = spArr.loadAllSP(table, model, kq, ttSP, sizeSP);
            table = tableLayout(table, sizeSP);

        }

        if (e.getActionCommand() == "Hiển thị tất cả") {
            loadAllSP(soLuong, 1);
        }

        if (e.getActionCommand() == "Tải ảnh") {
            filename = spArr.fileName(filename);
            lbAnh = spArr.setImage(lbAnh, filename);
            txtPath.setText(filename);

        }
        if (e.getActionCommand() == "Tìm nâng cao") {
            card.show(this, "search");

        }
        if (e.getActionCommand() == "Next") {

            int trangCuoi = (int) spArr.getTongSoTrang(soLuong);
            if (!lbTrang.getText().equals(Integer.toString(trangCuoi))) {
                int trangSau = Integer.parseInt(lbTrang.getText()) + 1;
                lbTrang.setText(Integer.toString(trangSau));
                loadAllSP(soLuong, trangSau);
            } else
                JOptionPane.showMessageDialog(null, "Đã đến trang cuối, không thể chuyển trang kế tiếp", "Lỗi", 0);

        }
        if (e.getActionCommand() == "Prev") {
            if (!lbTrang.getText().equals("1")) {
                int trangTruoc = Integer.parseInt(lbTrang.getText()) - 1;
                lbTrang.setText(Integer.toString(trangTruoc));
                loadAllSP(soLuong, trangTruoc);

            } else
                JOptionPane.showMessageDialog(null, "Đã đến trang đầu, không thể chuyển trang trước nữa", "Lỗi", 0);

        }
        if (e.getSource() == btNC) {

            ArrayList<SanPham> kq = new ArrayList<>();

            int min = Integer.parseInt(txtFrom.getText());
            int max = Integer.parseInt(txtTo.getText());

            kq = spArr.timNangCao(min, max, loai, nsx, loaiList, nsxList);

            table = spArr.loadAllSP(table, model, kq, ttSP, sizeSP);
            table = tableLayout(table, sizeSP);
            card.show(this, "sp");

        }

        if (e.getSource() == btReturn) {
            card.show(this, "sp");
        }

        if (e.getSource() == btHTLoai) {
            btLoadLoai.setText("Hiển thị tất cả loại");
            btHTTK.setText("Tìm loại");
            card.show(this, "loai");
            getLoai();

        }

        if (e.getActionCommand() == "Tìm loại") {

            ArrayList<Loai> kq = new ArrayList<Loai>();

            kq = loaiArr.getLoaiByTen(txtSearchLoai.getText());

            tableLoai = spArr.loadAllLoai(tableLoai, modelLoai, kq, lbLoai);
            tableLoai = tableLayout(tableLoai, sizeLoai);

        }
        if (e.getActionCommand() == "Hiển thị tất cả loại") {

            getLoai();

        }
        if (e.getActionCommand() == "Hiển thị tất cả NSX") {

            getNSX();
        }
        if (e.getSource() == btHTNSX) {
            btLoadLoai.setText("Hiển thị tất cả NSX");
            btHTTK.setText("Tìm NSX");
            getNSX();

            card.show(this, "loai");
        }
        if (e.getActionCommand() == "Tìm NSX") {
            ArrayList<NhaSanXuat> kq = new ArrayList<NhaSanXuat>();
            kq = spArr.getNSXByTen(txtSearchLoai.getText());

            tableLoai = spArr.loadAllNSX(tableLoai, modelLoai1, kq, lbNSX);
            tableLoai = tableLayout(tableLoai, sizeNSX);

        }

        if (e.getActionCommand() == "Export Excel") {

            excel.exportToExcel(table, pathExcel);

        }
        if (e.getActionCommand() == "Import Excel") {
            table = excel.importExcel(table, pathExcel, ttSP);
            table = tableLayout(table, sizeSP);
        }

        if (e.getActionCommand() == "Export PDF") {
            excel.exportPDF(table, pathPDF, 2);
        }

    }

    public void getLoai() {
        ArrayList<Loai> loaiList = new ArrayList<Loai>();
        loaiList = loaiArr.getAllLoai();

        tableLoai = spArr.loadAllLoai(tableLoai, modelLoai, loaiList, lbLoai);
        tableLoai = tableLayout(tableLoai, sizeLoai);

    }

    public void getNSX() {
        ArrayList<NhaSanXuat> nsxList = new ArrayList<NhaSanXuat>();
        nsxList = spArr.getAllNSX();

        tableLoai = spArr.loadAllNSX(tableLoai, modelLoai, nsxList, lbNSX);
        tableLoai = tableLayout(tableLoai, sizeNSX);

    }

    public void loadAllSP(int soLuong, int trang) {
        ArrayList<SanPham> spList = new ArrayList<SanPham>();
        spList = spArr.getSP(soLuong, trang);

        table = spArr.loadAllSP(table, model, spList, ttSP, sizeSP);
        table = tableLayout(table, sizeSP);

    }

    public void resetForm() {
        int ma = spArr.getLastMaSP() + 1;
        txtMaSP.setText("" + ma);
        txtTenSP.setText("");
        txtMaLoai.setText("");
        txtTenLoai.setText("");
        txtMaNSX.setText("");
        txtTenNSX.setText("");
        txtSL.setText("");
        txtDG.setText("");
        cbDVT.setSelectedIndex(0);
        txtPath.setText("");
        lbAnh.setIcon(null);
        taMoTa.setText("");
        txtTenSP.requestFocus();
    }

    public boolean checkEmpty(String tenSP, String maLoai, String maNSX, String soLuong, String donGia) {
        if (tenSP.equals("") || maLoai.equals("") || maNSX.equals("") || soLuong.equals("") || donGia.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Lỗi", 0);
            return false;
        }

        return true;
    }

    class ButtonsPanel extends JPanel {

        public ArrayList<JButton> button = new ArrayList<>();
        JButton b1, b2;

        public ButtonsPanel() {

            super();

            b1 = myButton("Sửa", 60, 20, f2);
            b1.setIcon(icon1);
            b1.setBackground(Color.BLUE);
            b1.setForeground(Color.WHITE);
            b2 = myButton("Xóa", 60, 20, f2);
            b2.setIcon(icon2);
            b2.setBackground(Color.RED);
            b2.setForeground(Color.WHITE);

            button.add(b1);
            button.add(b2);

            this.setForeground(table.getSelectionForeground());
            this.setBackground(table.getSelectionBackground());
            this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            for (JButton b : button) {

                b.setFocusable(false);
                b.setRolloverEnabled(false);
                add(b, gbc);
                gbc.insets = new Insets(0, 10, 0, 0);

            }
        }
    }

    class ButtonRenderer extends ButtonsPanel implements TableCellRenderer {
        public ButtonRenderer() {
            super();

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (isSelected) {
                this.setForeground(table.getSelectionForeground());
                this.setBackground(table.getSelectionBackground());
                this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

            } else {
                this.setForeground(table.getForeground());
                this.setBackground(table.getBackground());
            }
            return this;
        }
    }

    class ButtonEditor extends ButtonsPanel implements TableCellEditor {
        protected transient ChangeEvent changeEvent = null;

        public ButtonEditor(final JTable table) {

            super();

            button.get(0).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = table.getSelectedRow();

                    txtMaSP.setText(table.getModel().getValueAt(i, 2).toString());
                    txtTenSP.setText(table.getModel().getValueAt(i, 3).toString());
                    txtTenLoai.setText(table.getModel().getValueAt(i, 4).toString());
                    txtTenNSX.setText(table.getModel().getValueAt(i, 5).toString());
                    txtSL.setText(table.getModel().getValueAt(i, 6).toString());
                    txtDG.setText(table.getModel().getValueAt(i, 7).toString());
                    cbDVT.setSelectedItem(table.getModel().getValueAt(i, 7).toString());

                    SanPham sp = new SanPham();
                    sp = spArr.getSPByID(Integer.parseInt(txtMaSP.getText()));

                    String img = sp.getanhDaiDien();

                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(path + img).getImage()
                            .getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), Image.SCALE_SMOOTH));
                    lbAnh.setIcon(imageIcon);
                    txtPath.setText(img);
                    filename = img;
                    taMoTa.setText(sp.getMoTa());
                    txtMaLoai.setText("" + sp.getMaLoai());
                    txtMaNSX.setText("" + sp.getMaNSX());

                    card.show(SanPhamPanel.this, "form");
                    btXN.setText("Lưu");

                }
            });

            button.get(1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = table.getSelectedRow();
                    String idSelected = table.getModel().getValueAt(i, 2).toString();

                    int choice = JOptionPane.showConfirmDialog(SanPhamPanel.this,
                            "Bạn có chắc muốn xóa dữ liệu của mã SP: " + idSelected, "Xác nhận",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            int re = spArr.deleteSP(Integer.parseInt(idSelected));
                            loadAllSP(soLuong, Integer.parseInt(lbTrang.getText()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {

            return this;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public boolean isCellEditable(java.util.EventObject e) {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

        @Override
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        @Override
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
            listenerList.add(CellEditorListener.class, l);
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
            listenerList.remove(CellEditorListener.class, l);
        }

        protected void fireEditingStopped() {

            Object[] listeners = listenerList.getListenerList();

            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CellEditorListener.class) {

                    if (changeEvent == null)
                        changeEvent = new ChangeEvent(this);

                    ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
                }
            }
        }

        protected void fireEditingCanceled() {

            Object[] listeners = listenerList.getListenerList();

            for (int i = listeners.length - 2; i >= 0; i -= 2) {

                if (listeners[i] == CellEditorListener.class) {

                    if (changeEvent == null)
                        changeEvent = new ChangeEvent(this);
                    ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
                }

            }
        }

    }

    private static void setCheckboxValue(JCheckBox checkBox, Object value) {
        if (value instanceof Boolean) {

            checkBox.setSelected(((Boolean) value).booleanValue());

        }

        else if (value instanceof String) {

            checkBox.setSelected(value.equals("true"));
        }
    }

    private static void copyAppearanceFrom(JPanel to, Component from) {
        if (from != null) {
            to.setOpaque(true);
            to.setBackground(from.getBackground());
            if (from instanceof JComponent) {

                to.setBorder(((JComponent) from).getBorder());
            }
        } else {
            to.setOpaque(false);
        }
    }

    class CheckBoxEditor extends AbstractCellEditor implements TableCellEditor, ItemListener {

        private static final long serialVersionUID = 1L;
        private final JPanel componentPanel;
        private final JCheckBox checkBox;

        public CheckBoxEditor() {
            componentPanel = new JPanel(new GridBagLayout());
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
            componentPanel.add(checkBox);
            checkBox.addItemListener(this);
        }

        @Override
        public Object getCellEditorValue() {
            return Boolean.valueOf(checkBox.isSelected());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {

            setCheckboxValue(checkBox, value);

            TableCellRenderer renderer = table.getCellRenderer(row, column);

            Component c = renderer.getTableCellRendererComponent(table, value, true, true, row, column);

            copyAppearanceFrom(componentPanel, c);

            return componentPanel;

        }

        @Override
        public void itemStateChanged(ItemEvent e) {

        }

    }

    class CheckBoxRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
        private final JPanel componentPanel;
        private final JCheckBox checkBox;

        public CheckBoxRenderer() {

            componentPanel = new JPanel(new GridBagLayout());
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
            componentPanel.add(checkBox);

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setCheckboxValue(checkBox, value);

            copyAppearanceFrom(componentPanel, this);
            return componentPanel;

        }
    }

    class JTableLabelRenderer extends JLabel implements TableCellRenderer {
        JTableLabelRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            JLabel label = (JLabel) value;

            return label;

        }
    }

    public static void main(String[] args) {
        new SanPhamPanel();
    }
}