package application;

import businesslayer.ChiTietHoaDon;
import businesslayer.HoaDon;
import businesslayer.KhachHang;
import businesslayer.MangCTHD;
import businesslayer.MangHoaDon;
import businesslayer.NhanVien;
import businesslayer.SanPham;
import businesslayer.MangSanPham;
import businesslayer.Excel;

import com.toedter.calendar.JDateChooser;
import datalayer.tblCTHD;
import test.InputTester;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HoaDonPanel extends JPanel implements ActionListener, MouseListener, DocumentListener {

    private String path = "excel/hoadon.xlsx";
    private String pathPDF = "pdf/hoadon.pdf";

    private ImageIcon icon1 = new ImageIcon("image/Editor_16.png");
    private ImageIcon icon2 = new ImageIcon("image/Delete_16.png");

    private int soLuong = 7;
    private JTable tableHD, tableCTHD, tableHT;
    public DefaultTableModel model, model1, modelHT, modelHT1, modelCTHD, modelCTHD1;
    private JScrollPane scrollHD, scrollCTHD, scrollHT;
    private JPanel pMain, pHD, pCenter, pTop, pForm, pMainForm, pFormAll, pPT, pPTTop, pHT, pHTSearch, pHTTop, pTopLeft,
            pTopRight, pBottomLeft, pBottomRight, pBottom, pFormButton, pFormCTHD, pFormBtCTHD, pMainCTHD, pNCForm, pNC,
            pNCTop;
    private String[] lbSP = { "M?? s???n ph???m", "T??n s???n ph???m", "M?? lo???i", "M?? NSX", "S??? l?????ng", "????n gi??", "PTKM",
            "Gi?? KM", "????n v??? t??nh" };
    private int[] sizeSP = { 80, 220, 80, 80, 100, 80, 120, 80, 100 };
    private String[] ttHD = { "", "M?? HD", "Kh??ch H??ng", "Nh??n Vi??n", "Ng??y L???p", "T???ng Ti???n", "Ch???c N??ng" };
    private int[] sizeHD = { 50, 100, 250, 200, 200, 150, 150 };
    private String[] ttCTHD = { "", "M?? HD", "M?? SP", "S???n Ph???m", "S??? L?????ng", "????n Gi??", "Th??nh Ti???n", "Ch???c N??ng" };
    private int[] sizeCTHD = { 50, 100, 100, 200, 150, 150, 150, 150 };
    private String[] lbHD = { "M?? H??a ????n", "M?? KH", "T??n KH", "M?? NV", "T??n NV", "Ng??y L???p", "T???ng Ti???n" };
    private String[] lbCTHD = { "M?? H??a ????n", "M?? SP", "T??n SP", "S??? L?????ng", "????n Gi??", "Th??nh Ti???n" };

    private String[] lbKH = { "M?? KH", "M?? TK", "H???", "T??n", "Ng??y Sinh", "?????a Ch???", "S??T" };
    private int[] sizeKH = { 100, 100, 200, 100, 150, 250, 100 };
    private String[] lbNV = { "M?? NV", "M?? TK", "H???", "T??n", "Ng??y Sinh", "?????a Ch???", "S??T", "L????ng" };
    private int[] sizeNV = { 100, 100, 200, 100, 150, 250, 100, 100 };
    private JLabel[] l = new JLabel[lbHD.length];
    private JLabel[] lc = new JLabel[lbCTHD.length];
    private JLabel lbTrangTop, lbTT, lbNL, lbMax, lbMin, lbMax2, lbMin2;
    private JTextField txtMaHD, txtMaKH, txtTenKH, txtMaNV, txtTenNV, txtTT, txtMaSP, txtTenSP, txtSL, txtDG, txtThT,
            txtTKTop, txtTKBottom, txtHTTK, txtTKCT, txtMaCT, txtMin, txtMax;
    private JButton btThemTop, btAllTop, btTKTop, btHD, btSua, btXN, btHTTK, btHTSP, btHTKH, btHTNV, btLoad, btReturn,
            btThemBottom, btAllBottom, btTKBottom, btNextTop, btPrevTop, btXNCTHD, btCTHD, btThemCTHD, btTKNC, btDS,
            btTim, btExExcel, btImExcel, btExPDF;

    private JDateChooser dateChooser, dateFrom, dateTo;
    private DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat dbfmt = new SimpleDateFormat("yyyy-MM-dd");

    private MangHoaDon hdArr = new MangHoaDon();
    private MangCTHD cthdArr = new MangCTHD();
    private MangSanPham spArr = new MangSanPham();
    private Excel excel = new Excel();

    private ArrayList<ChiTietHoaDon> cthdList = new ArrayList<ChiTietHoaDon>();
    private ArrayList<KhachHang> khList = new ArrayList<KhachHang>();
    private ArrayList<NhanVien> nvList = new ArrayList<NhanVien>();
    private ArrayList<SanPham> spList = new ArrayList<SanPham>();

    private float tongTien = 0;
    private CardLayout card;

    Font f0 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    Font f1 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

    private Color color1 = Color.WHITE;
    private Color color2 = new Color(222, 222, 221);

    public HoaDonPanel() {

        initComponents();
        loadAllHD(soLuong, 1);

    }

    public void initComponents() {
        setBackground(color1);
        card = new CardLayout();
        pMain = myPanel(card, color1);

        pMain.setPreferredSize(new Dimension(1080, 600));

        pHD = myPanel(new BorderLayout(), color1);

        pTop = myPanel(new BorderLayout(), color1);
        pTop.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        pTopLeft = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);
        pTopRight = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);

        pCenter = new JPanel();
        pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
        pCenter.setBackground(color1);
        pCenter.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        btThemTop = myButton("Th??m", 60, 30, f1);
        btAllTop = myButton("Hi???n th??? t???t c???", 120, 30, f1);
        btTKTop = myButton("T??m ki???m", 80, 30, f1);
        btTKNC = myButton("T??m ki???m n??ng cao", 150, 30, f1);
        btImExcel = myButton("Import Excel", 100, 30, f1);
        btExExcel = myButton("Export Excel", 100, 30, f1);
        btExPDF = myButton("Export PDF", 100, 30, f1);

        txtTKTop = myTextField(15, f2);
        txtTKTop.setBackground(color1);

        pTopLeft.add(btThemTop);
        pTopLeft.add(btAllTop);
        pTopLeft.add(btTKNC);
        pTopLeft.add(btImExcel);
        pTopLeft.add(btExExcel);
        pTopLeft.add(btExPDF);

        pTopRight.add(txtTKTop);
        pTopRight.add(btTKTop);

        pTop.add(pTopLeft, BorderLayout.LINE_START);
        pTop.add(pTopRight, BorderLayout.LINE_END);

        tableHD = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent) comp;
                jc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
                if (!comp.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? color1 : color2);
                    comp.setBackground(c);
                    c = null;
                }
                return comp;
            }
        };

        tableHD.setRowHeight(30);
        tableHD.setFont(f2);
        tableHD.addMouseListener(this);
        tableHD.setPreferredScrollableViewportSize(new Dimension(1100, 400));
        tableHD.setFillsViewportHeight(true);
        tableHD.setBackground(color2);

        JTableHeader header = tableHD.getTableHeader();
        header.setOpaque(false);
        header.setBackground(color2);
        header.setFont(f1);

        ((DefaultTableCellRenderer) tableHD.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        scrollHD = new JScrollPane(tableHD);
        pCenter.add(scrollHD);

        lbTrangTop = myLabel("1", f2);
        btNextTop = new JButton("Next");
        btPrevTop = new JButton("Prev");
        btNextTop.addActionListener(this);
        btPrevTop.addActionListener(this);

        pPTTop = myPanel(new FlowLayout(), color1);

        pPTTop.add(btPrevTop);
        pPTTop.add(lbTrangTop);
        pPTTop.add(btNextTop);

        pCenter.add(pPTTop);

        pBottom = myPanel(new BorderLayout(), color1);

        pBottomLeft = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);
        pBottomRight = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);

        btThemBottom = myButton("Th??m Chi Ti???t M???i", 150, 30, f1);
        btAllBottom = myButton("Hi???n th??? t???t c???", 120, 30, f1);
        btTKBottom = myButton("T??m ki???m", 80, 30, f1);

        txtTKBottom = myTextField(15, f2);
        txtTKBottom.setBackground(color1);

        pBottomLeft.add(btThemBottom);
        pBottomLeft.add(btAllBottom);

        pBottomRight.add(txtTKBottom);
        pBottomRight.add(btTKBottom);

        pBottom.add(pBottomLeft, BorderLayout.LINE_START);
        pBottom.add(pBottomRight, BorderLayout.LINE_END);

        pCenter.add(pBottom);

        tableCTHD = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent) comp;
                jc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

                if (!comp.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? color1 : color2);
                    comp.setBackground(c);
                    c = null;
                }
                return comp;
            }
        };

        tableCTHD.setRowHeight(30);
        tableCTHD.setFont(f2);
        tableCTHD.setPreferredScrollableViewportSize(new Dimension(1100, 400));
        tableCTHD.setFillsViewportHeight(true);
        tableCTHD.setBackground(color2);

        JTableHeader header2 = tableCTHD.getTableHeader();
        header2.setOpaque(false);
        header2.setBackground(color2);
        header2.setFont(f1);

        ((DefaultTableCellRenderer) tableCTHD.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        scrollCTHD = new JScrollPane(tableCTHD);

        pCenter.add(scrollCTHD);

        pHD.add(pTop, BorderLayout.NORTH);
        pHD.add(pCenter, BorderLayout.CENTER);

        formHD();

        formHT();

        formCTHD();

        formNC();

        pMain.add(pHD, "hd");
        pMain.add(pMainForm, "form");
        pMain.add(pHT, "ht");
        pMain.add(pMainCTHD, "cthd");
        pMain.add(pNC, "nc");

        add(pMain);

    }

    public void formHD() {

        pMainForm = myPanel(new BorderLayout(), color1);
        pMainForm.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

        pFormButton = myPanel(new FlowLayout(FlowLayout.LEFT), color1);

        btHD = myButton("Danh S??ch H??", 150, 30, f1);
        btXN = myButton("X??c Nh???n", 100, 30, f1);
        btThemCTHD = myButton("Th??m Chi Ti???t", 150, 30, f1);

        pFormButton.add(btHD);
        pFormButton.add(btThemCTHD);

        GridBagLayout layout = new GridBagLayout();
        pForm = myPanel(layout, color2);

        for (int i = 0; i < l.length; i++)
            l[i] = myLabel(lbHD[i], f1);

        txtMaHD = myTextField(6, f2);
        txtMaKH = myTextField(6, f2);
        txtTenKH = myTextField(12, f2);
        txtMaNV = myTextField(6, f2);
        txtTenNV = myTextField(12, f2);
        txtTT = myTextField(8, f2);

        dateChooser = myDate(120, 20);

        txtMaHD.setEditable(false);
        txtMaKH.setEditable(false);
        txtTenKH.setEditable(false);
        txtMaNV.setEditable(false);
        txtTenNV.setEditable(false);
        txtTT.setEditable(false);

        btHTKH = myButton("...", 30, 20, f0);
        btHTNV = myButton("...", 30, 20, f0);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        pForm.add(l[0], gbc);

        gbc.gridy++;
        gbc.insets = new Insets(40, 0, 0, 0);
        pForm.add(l[1], gbc);

        gbc.insets = new Insets(40, 40, 0, 0);
        gbc.gridx += 3;
        pForm.add(l[2], gbc);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pForm.add(l[3], gbc);

        gbc.insets = new Insets(40, 40, 0, 0);
        gbc.gridx += 3;
        pForm.add(l[4], gbc);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pForm.add(l[5], gbc);

        gbc.insets = new Insets(40, 40, 0, 0);
        gbc.gridx += 3;
        pForm.add(l[6], gbc);

        gbc.insets = new Insets(0, 40, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pForm.add(txtMaHD, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(40, 30, 0, 0);
        pForm.add(txtMaKH, gbc);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx++;
        pForm.add(btHTKH, gbc);

        gbc.insets = new Insets(40, 30, 0, 0);
        gbc.gridx += 2;
        pForm.add(txtTenKH, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.insets = new Insets(40, 30, 0, 0);
        pForm.add(txtMaNV, gbc);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx++;
        pForm.add(btHTNV, gbc);

        gbc.insets = new Insets(40, 30, 0, 0);
        gbc.gridx += 2;
        pForm.add(txtTenNV, gbc);

        gbc.insets = new Insets(40, 30, 0, 0);
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        pForm.add(dateChooser, gbc);

        gbc.gridx += 3;
        pForm.add(txtTT, gbc);

        gbc.gridx = 5;
        gbc.gridy = 4;
        pForm.add(btXN, gbc);

        TitledBorder title = new TitledBorder("TH??NG TIN H??A ????N");
        title.setTitleFont(f0);

        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.TOP);
        pForm.setBorder(BorderFactory.createTitledBorder(title));
        // pForm.setBorder(new CompoundBorder(new EmptyBorder(60, 80, 60, 80),
        // BorderFactory.createTitledBorder(title)));

        pMainForm.add(pFormButton, BorderLayout.NORTH);
        pMainForm.add(pForm, BorderLayout.CENTER);

    }

    public void formHT() {

        pHT = myPanel(new BorderLayout(), color1);
        pHT.setBorder(BorderFactory.createEmptyBorder(60, 40, 40, 60));

        pHTTop = myPanel(new BorderLayout(), color1);
        pHTTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        pHTSearch = myPanel(new FlowLayout(FlowLayout.TRAILING), color1);

        txtHTTK = myTextField(16, f2);
        txtHTTK.setBackground(color1);
        btLoad = myButton("Hi???n th??? t???t c??? KH", 180, 20, f1);
        btHTTK = myButton("T??m KH", 80, 30, f1);

        pHTSearch.add(txtHTTK);
        pHTSearch.add(btHTTK);

        pHTTop.add(btLoad, BorderLayout.LINE_START);
        pHTTop.add(pHTSearch, BorderLayout.LINE_END);

        tableHT = new JTable(modelHT) {

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

        tableHT.setRowHeight(30);
        tableHT.addMouseListener(this);
        tableHT.setFont(f2);
        tableHT.setBackground(color2);

        JTableHeader header = tableHT.getTableHeader();
        header.setOpaque(false);
        header.setBackground(color2);
        header.setFont(f1);

        ((DefaultTableCellRenderer) tableHT.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        tableHT.setPreferredScrollableViewportSize(new Dimension(1100, 400));
        scrollHT = new JScrollPane(tableHT);
        tableHT.setFillsViewportHeight(true);

        pHT.add(pHTTop, BorderLayout.NORTH);
        pHT.add(scrollHT, BorderLayout.CENTER);

    }

    public void formCTHD() {
        pMainCTHD = myPanel(new BorderLayout(), color1);
        pMainCTHD.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

        pFormBtCTHD = myPanel(new FlowLayout(FlowLayout.LEFT), color1);

        btCTHD = myButton("H??a ????n", 120, 30, f1);
        btXNCTHD = myButton("X??c Nh???n CT", 150, 30, f1);

        pFormBtCTHD.add(btCTHD);
        pFormBtCTHD.add(btXNCTHD);

        GridBagLayout layout = new GridBagLayout();
        pFormCTHD = myPanel(layout, color2);

        for (int i = 0; i < lc.length; i++)
            lc[i] = myLabel(lbCTHD[i], f1);

        txtMaCT = myTextField(6, f2);
        txtMaSP = myTextField(6, f2);
        txtTenSP = myTextField(12, f2);
        txtSL = myTextField(8, f2);
        txtDG = myTextField(12, f2);
        txtThT = myTextField(12, f2);

        txtMaCT.setEditable(false);
        txtMaSP.setEditable(false);
        txtTenSP.setEditable(false);
        txtDG.setEditable(false);
        txtThT.setEditable(false);

        txtSL.getDocument().addDocumentListener(this);

        btHTSP = myButton("...", 30, 20, f0);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        pFormCTHD.add(lc[0], gbc);

        gbc.gridy++;
        gbc.insets = new Insets(40, 0, 0, 0);
        pFormCTHD.add(lc[1], gbc);

        gbc.insets = new Insets(40, 40, 0, 0);
        gbc.gridx += 3;
        pFormCTHD.add(lc[2], gbc);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pFormCTHD.add(lc[3], gbc);

        gbc.insets = new Insets(40, 40, 0, 0);
        gbc.gridx += 3;
        pFormCTHD.add(lc[4], gbc);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pFormCTHD.add(lc[5], gbc);

        gbc.insets = new Insets(0, 40, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pFormCTHD.add(txtMaCT, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(40, 30, 0, 0);
        pFormCTHD.add(txtMaSP, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(40, 0, 0, 0);
        pFormCTHD.add(btHTSP, gbc);

        gbc.insets = new Insets(40, 30, 0, 0);
        gbc.gridx += 2;
        gbc.gridwidth = 2;
        pFormCTHD.add(txtTenSP, gbc);

        gbc.gridy++;
        gbc.gridx = 1;

        gbc.insets = new Insets(40, 30, 0, 0);
        pFormCTHD.add(txtSL, gbc);

        gbc.insets = new Insets(40, 30, 0, 0);
        gbc.gridx += 3;
        pFormCTHD.add(txtDG, gbc);

        gbc.insets = new Insets(40, 30, 0, 0);
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        pFormCTHD.add(txtThT, gbc);

        gbc.gridx = 6;
        gbc.gridy = 4;
        pFormCTHD.add(btXNCTHD, gbc);

        TitledBorder title = new TitledBorder("CHI TI???T H??A ????N");
        title.setTitleFont(f0);

        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.TOP);
        pFormCTHD.setBorder(BorderFactory.createTitledBorder(title));

        pMainCTHD.add(pFormBtCTHD, BorderLayout.NORTH);
        pMainCTHD.add(pFormCTHD, BorderLayout.CENTER);
    }

    public void formNC() {

        pNC = myPanel(new BorderLayout(), color1);
        pNC.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        GridBagLayout layout = new GridBagLayout();
        pNCForm = myPanel(layout, color2);
        pNCTop = myPanel(new FlowLayout(FlowLayout.LEFT), color1);

        btDS = myButton("DS H??a ????n", 120, 30, f1);
        btTim = myButton("T??m", 120, 30, f1);

        lbMin = myLabel("T???", f2);
        lbMax = myLabel("?????n", f2);
        lbMin2 = myLabel("T???", f2);
        lbMax2 = myLabel("?????n", f2);
        lbTT = myLabel("T???ng ti???n", f1);
        lbNL = myLabel("Th???i gian", f1);

        txtMin = myTextField(8, f2);
        txtMax = myTextField(8, f2);
        txtMin.setText("0");
        txtMax.setText("0");
        dateFrom = myDate(140, 20);
        dateTo = myDate(140, 20);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pNCForm.add(lbNL, gbc);

        gbc.insets = new Insets(0, 30, 0, 0);
        gbc.gridx++;
        pNCForm.add(lbMin, gbc);

        gbc.gridx++;
        pNCForm.add(dateFrom, gbc);

        gbc.gridx++;
        pNCForm.add(lbMax, gbc);

        gbc.gridx++;
        pNCForm.add(dateTo, gbc);

        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        pNCForm.add(lbTT, gbc);

        gbc.insets = new Insets(30, 30, 0, 0);
        gbc.gridx++;
        pNCForm.add(lbMin2, gbc);

        gbc.gridx++;
        pNCForm.add(txtMin, gbc);

        gbc.gridx++;
        pNCForm.add(lbMax2, gbc);

        gbc.gridx++;
        pNCForm.add(txtMax, gbc);

        gbc.gridx++;
        gbc.gridy++;
        pNCForm.add(btTim, gbc);

        pNCTop.add(btDS);
        pNC.add(pNCTop, BorderLayout.NORTH);
        pNC.add(pNCForm, BorderLayout.CENTER);
        TitledBorder title = new TitledBorder("T??M N??NG CAO");
        title.setTitleFont(f0);

        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.TOP);
        pNCForm.setBorder(BorderFactory.createTitledBorder(title));
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

    public JDateChooser myDate(int width, int height) {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(width, height));
        dateChooser.setFont(f2);
        return dateChooser;
    }

    public DefaultTableModel myModel() {
        Vector header = new Vector();
        for (int i = 0; i < ttCTHD.length; i++) {
            header.add(ttCTHD[i]);
        }

        DefaultTableModel model = new DefaultTableModel(header, 0);
        return model;
    }

    public JTable tableLayout(JTable table, int[] size) {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        if (table.getColumnName(0) == "") {
            TableCellRenderer checkBoxRenderer = new CheckBoxRenderer();
            table.getColumn("").setCellRenderer(checkBoxRenderer);
            table.getColumn("").setCellEditor(new CheckBoxEditor());

        }

        if (table.getColumnName(table.getColumnCount() - 1) == "Ch???c N??ng") {
            TableCellRenderer btRenderer = new ButtonRenderer();
            table.getColumn("Ch???c N??ng").setCellRenderer(btRenderer);
            table.getColumn("Ch???c N??ng").setCellEditor(new ButtonEditor(table));
        }

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(size[i]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

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
        if (m.getSource() == btThemTop) {
            btThemTop = btHoverExit(btThemTop);
        }
        if (m.getSource() == btAllTop) {
            btAllTop = btHoverExit(btAllTop);
        }
        if (m.getSource() == btTKTop) {
            btTKTop = btHoverExit(btTKTop);
        }
        if (m.getSource() == btHD) {
            btHD = btHoverExit(btHD);
        }
        if (m.getSource() == btXN) {
            btXN = btHoverExit(btXN);
        }
        if (m.getSource() == btThemBottom) {
            btThemBottom = btHoverExit(btThemBottom);
        }
        if (m.getSource() == btAllBottom) {
            btAllBottom = btHoverExit(btAllBottom);
        }
        if (m.getSource() == btTKBottom) {
            btTKBottom = btHoverExit(btTKBottom);
        }
        if (m.getSource() == btCTHD) {
            btCTHD = btHoverExit(btCTHD);
        }
        if (m.getSource() == btXNCTHD) {
            btXNCTHD = btHoverExit(btXNCTHD);
        }
        if (m.getSource() == btThemCTHD) {
            btThemCTHD = btHoverExit(btThemCTHD);
        }
        if (m.getSource() == btLoad) {
            btLoad = btHoverExit(btLoad);
        }
        if (m.getSource() == btHTTK) {
            btHTTK = btHoverExit(btHTTK);
        }
        if (m.getSource() == btDS) {
            btDS = btHoverExit(btDS);
        }
        if (m.getSource() == btTim) {
            btTim = btHoverExit(btTim);
        }
        if (m.getSource() == btTKNC) {
            btTKNC = btHoverExit(btTKNC);
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

        if (m.getSource() == btHTSP) {
            btHTSP = btHoverExit(btHTSP);
        }
        if (m.getSource() == btHTKH) {
            btHTKH = btHoverExit(btHTKH);
        }
        if (m.getSource() == btHTNV) {
            btHTNV = btHoverExit(btHTNV);
        }
    }

    public void mouseEntered(MouseEvent m) {
        if (m.getSource() == btThemTop) {
            btThemTop = btHoverEnter(btThemTop);
        }

        if (m.getSource() == btAllTop) {
            btAllTop = btHoverEnter(btAllTop);
        }
        if (m.getSource() == btTKTop) {
            btTKTop = btHoverEnter(btTKTop);
        }
        if (m.getSource() == btHD) {
            btHD = btHoverEnter(btHD);
        }
        if (m.getSource() == btXN) {
            btXN = btHoverEnter(btXN);
        }
        if (m.getSource() == btThemBottom) {
            btThemBottom = btHoverEnter(btThemBottom);
        }

        if (m.getSource() == btAllBottom) {
            btAllBottom = btHoverEnter(btAllBottom);
        }
        if (m.getSource() == btTKBottom) {
            btTKBottom = btHoverEnter(btTKBottom);
        }
        if (m.getSource() == btCTHD) {
            btCTHD = btHoverEnter(btCTHD);
        }
        if (m.getSource() == btXNCTHD) {
            btXNCTHD = btHoverEnter(btXNCTHD);
        }
        if (m.getSource() == btThemCTHD) {
            btThemCTHD = btHoverEnter(btThemCTHD);
        }
        if (m.getSource() == btLoad) {
            btLoad = btHoverEnter(btLoad);
        }
        if (m.getSource() == btHTTK) {
            btHTTK = btHoverEnter(btHTTK);
        }
        if (m.getSource() == btDS) {
            btDS = btHoverEnter(btDS);
        }
        if (m.getSource() == btTim) {
            btTim = btHoverEnter(btTim);
        }
        if (m.getSource() == btTKNC) {
            btTKNC = btHoverEnter(btTKNC);
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
        if (m.getSource() == btHTSP) {
            btHTSP = btHoverEnter(btHTSP);
        }
        if (m.getSource() == btHTKH) {
            btHTKH = btHoverEnter(btHTKH);
        }
        if (m.getSource() == btHTNV) {
            btHTNV = btHoverEnter(btHTNV);
        }
    }

    public void mouseReleased(MouseEvent m) {

    }

    public void mousePressed(MouseEvent m) {

    }

    public void mouseClicked(MouseEvent m) {
        int i = tableHT.getSelectedRow();
        if (m.getSource() == tableHT && btHTTK.getText() == "T??m KH") {
            if (i != -1) {
                txtMaKH.setText(tableHT.getModel().getValueAt(i, 0).toString());
                String name = tableHT.getModel().getValueAt(i, 2).toString() + " "
                        + tableHT.getModel().getValueAt(i, 3).toString();
                txtTenKH.setText(name);

            }
            card.show(pMain, "form");
        }
        if (m.getSource() == tableHT && btHTTK.getText() == "T??m NV") {
            if (i != -1) {
                txtMaNV.setText(tableHT.getModel().getValueAt(i, 0).toString());
                String name = tableHT.getModel().getValueAt(i, 2).toString() + " "
                        + tableHT.getModel().getValueAt(i, 3).toString();
                txtTenNV.setText(name);

            }
            card.show(pMain, "form");
        }
        if (m.getSource() == tableHT && btHTTK.getText() == "T??m SP") {
            if (i != -1) {
                txtMaSP.setText(tableHT.getModel().getValueAt(i, 0).toString());
                txtTenSP.setText(tableHT.getModel().getValueAt(i, 1).toString());
                txtDG.setText(tableHT.getModel().getValueAt(i, 7).toString());
                txtSL.setText("");
                txtThT.setText("");
            }
            card.show(pMain, "cthd");
        }
        if (m.getSource() == tableHD) {
            int j = tableHD.getSelectedRow();
            int maHD = Integer.parseInt(tableHD.getValueAt(j, 1).toString());

            loadAllCTHD(maHD);

        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        float sl = Float.parseFloat(txtSL.getText());
        float dg = Float.parseFloat(txtDG.getText());
        float tt = sl * dg;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                txtThT.setText("" + tt);
            }
        });
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Th??m") {
            resetFormHD();
            btThemCTHD.setEnabled(true);

            btXN.setText("X??c Nh???n");
            btXN.setEnabled(false);

            modelCTHD = cthdArr.modelHeader(modelCTHD, ttCTHD);
            tableCTHD.setModel(modelCTHD);

            card.show(pMain, "form");

        }
        if (e.getActionCommand() == "Danh S??ch H??") {
            cthdList.removeAll(cthdList);
            card.show(pMain, "hd");
        }

        if (e.getSource() == btDS) {
            card.show(pMain, "hd");
        }

        if (e.getActionCommand() == "Th??m Chi Ti???t") {
            btCTHD.setText("H??a ????n");
            btXNCTHD.setText("X??c Nh???n CT");
            txtMaCT.setText(txtMaHD.getText());
            resetFormCTHD();

            card.show(pMain, "cthd");

        }
        if (e.getActionCommand() == "Th??m Chi Ti???t M???i") {

            if (tableCTHD.getModel().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n tr?????c", "L???i", 0);
            } else {
                String maCT = tableCTHD.getModel().getValueAt(0, 1).toString();
                txtMaCT.setText(maCT);
                resetFormCTHD();
                btCTHD.setText("Danh S??ch H??");
                btXNCTHD.setText("X??c Nh???n CT M???i");
                card.show(pMain, "cthd");
            }
        }
        if (e.getActionCommand() == "H??a ????n") {
            card.show(pMain, "form");

        }
        if (e.getActionCommand() == "Danh S??ch H??") {
            int ma = Integer.parseInt(txtMaCT.getText());
            loadAllHD(soLuong, Integer.parseInt(lbTrangTop.getText()));
            loadAllCTHD(ma);
            card.show(pMain, "hd");
        }

        if (e.getActionCommand() == "L??u") {
            if (hdArr.checkEmptyHD(txtMaKH.getText(), txtMaNV.getText())) {
                int id = Integer.parseInt(tableHD.getValueAt(tableHD.getSelectedRow(), 1).toString());
                String date = dbfmt.format(dateChooser.getDate());

                HoaDon hd = new HoaDon(Integer.parseInt(txtMaHD.getText()), Integer.parseInt(txtMaKH.getText()),
                        Integer.parseInt(txtMaNV.getText()), date, Float.parseFloat(txtTT.getText()));
                int re = hdArr.updateHD(hd, id);

                if (re != 0) {
                    JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th??nh c??ng!", "Th??ng b??o",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadAllHD(soLuong, Integer.parseInt(lbTrangTop.getText()));

                    card.show(pMain, "hd");

                } else {
                    JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th???t b???i!", "Th??ng b??o",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (e.getActionCommand() == "X??c Nh???n") {
            if (hdArr.checkEmptyHD(txtMaKH.getText(), txtMaNV.getText())) {
                String date = dbfmt.format(dateChooser.getDate());

                HoaDon hd = new HoaDon(Integer.parseInt(txtMaHD.getText()), Integer.parseInt(txtMaNV.getText()),
                        Integer.parseInt(txtMaKH.getText()), date, Float.parseFloat(txtTT.getText()));

                int re = hdArr.insertHD(hd);
                if (re != 0) {
                    for (ChiTietHoaDon cthd : cthdList) {
                        int r = cthdArr.insertCTHD(cthd);
                        cthdArr.updateQty(cthd.getMaSP(), 0, cthd.getSoLuong());
                        cthdArr.updateTht(hd.getMaHD(), cthd.getMaSP(), cthd.getThanhTien());
                    }
                    cthdList.removeAll(cthdList);

                    JOptionPane.showMessageDialog(this, "Th??m d??? li???u th??nh c??ng!", "Th??ng b??o",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadAllHD(soLuong, 1);
                    lbTrangTop.setText("1");
                    myModel();
                    resetFormHD();
                } else
                    JOptionPane.showMessageDialog(this, "Th??m d??? li???u th???t b???i!", "Th??ng b??o",
                            JOptionPane.INFORMATION_MESSAGE);

            }

        }
        if (e.getActionCommand() == "X??c Nh???n CT") {

            int count = 0;
            if (!cthdArr.checkEmptyCTHD(txtMaSP.getText())) {
                count++;
            }

            if (!InputTester.chiSo(txtSL.getText(), "S??? L?????ng", 5)) {
                count++;
                txtSL.setText("");

            } else {
                int bQty = cthdArr.getSL(Integer.parseInt(txtMaSP.getText()));
                if (!cthdArr.checkQty(bQty, Integer.parseInt(txtSL.getText()))) {
                    count++;
                }
            }

            if (count == 0) {
                ChiTietHoaDon cthd = new ChiTietHoaDon(Integer.parseInt(txtMaCT.getText()),
                        Integer.parseInt(txtMaSP.getText()), Integer.parseInt(txtSL.getText()),
                        Float.parseFloat(txtDG.getText()), Float.parseFloat(txtThT.getText()));

                float tongtien = Float.parseFloat(txtTT.getText());
                tongtien += Float.parseFloat(txtThT.getText());

                txtTT.setText("" + tongtien);
                resetFormCTHD();
                cthdList = cthdArr.KTCTHD(cthdList, cthd);
                JOptionPane.showMessageDialog(this, "Th??m d??? li???u th??nh c??ng!", "Th??ng b??o",
                        JOptionPane.INFORMATION_MESSAGE);
                btXN.setEnabled(true);
            }

        }
        if (e.getActionCommand() == "X??c Nh???n CT M???i") {

            int count = 0;
            if (!cthdArr.checkEmptyCTHD(txtMaSP.getText())) {
                count++;
            }
            if (!InputTester.chiSo(txtSL.getText(), "S??? L?????ng", 5)) {
                count++;
                txtSL.setText("");

            } else {
                int bQty = cthdArr.getSL(Integer.parseInt(txtMaSP.getText()));
                if (!cthdArr.checkQty(bQty, Integer.parseInt(txtSL.getText()))) {
                    count++;
                }
            }
            if (count == 0) {
                int id = Integer.parseInt(txtMaCT.getText());
                ChiTietHoaDon cthd = new ChiTietHoaDon(Integer.parseInt(txtMaCT.getText()),
                        Integer.parseInt(txtMaSP.getText()), Integer.parseInt(txtSL.getText()),
                        Float.parseFloat(txtDG.getText()), Float.parseFloat(txtThT.getText()));

                int re = 0;

                ChiTietHoaDon kq = cthdArr.KTCTHD(id, 0, cthd);
                if (kq != null) {
                    int soLuongMoi = cthd.getSoLuong() + kq.getSoLuong();
                    re = cthdArr.updateQtyCT(id, Integer.parseInt(txtMaSP.getText()), soLuongMoi);
                    float thtNew = soLuongMoi * kq.getDonGia();

                    cthdArr.updateTht(id, Integer.parseInt(txtMaSP.getText()), thtNew);
                    hdArr.updateTT(id, kq.getThanhTien(), thtNew);

                    if (re != 0) {

                        cthdArr.updateQty(Integer.parseInt(txtMaSP.getText()), 0, Integer.parseInt(txtSL.getText()));

                        JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th??nh c??ng!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);

                        resetFormCTHD();

                    } else
                        JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th???t b???i!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);
                } else {

                    int maHD = Integer.parseInt(tableCTHD.getModel().getValueAt(0, 1).toString());

                    int maSPNew = Integer.parseInt(txtMaSP.getText());
                    int qtyNew = Integer.parseInt(txtSL.getText());

                    re = cthdArr.insertCTHD(cthd);

                    if (re != 0) {

                        hdArr.updateTT(maHD, 0, Float.parseFloat(txtThT.getText()));

                        cthdArr.updateQty(maSPNew, 0, qtyNew);

                        JOptionPane.showMessageDialog(this, "Th??m d??? li???u th??nh c??ng!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);

                        resetFormCTHD();

                    } else
                        JOptionPane.showMessageDialog(this, "Th??m d??? li???u th???t b???i!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);

                }

            }

        }
        if (e.getActionCommand() == "L??u CT") {
            int count = 0;
            if (!cthdArr.checkEmptyCTHD(txtMaSP.getText())) {
                count++;
            } else if (!InputTester.chiSo(txtSL.getText(), "S??? L?????ng", 5)) {
                count++;
                txtSL.setText("");

            }

            if (count == 0) {
                int id = Integer.parseInt(tableCTHD.getValueAt(tableCTHD.getSelectedRow(), 1).toString());
                ChiTietHoaDon cthd = new ChiTietHoaDon(Integer.parseInt(txtMaCT.getText()),
                        Integer.parseInt(txtMaSP.getText()), Integer.parseInt(txtSL.getText()),
                        Float.parseFloat(txtDG.getText()), Float.parseFloat(txtThT.getText()));

                int re = 0;
                int maSP = Integer.parseInt(tableCTHD.getModel().getValueAt(tableCTHD.getSelectedRow(), 2).toString());
                int qtyOld = Integer
                        .parseInt(tableCTHD.getModel().getValueAt(tableCTHD.getSelectedRow(), 4).toString());
                float thtOld = Float.parseFloat(tableCTHD.getValueAt(tableCTHD.getSelectedRow(), 6).toString());
                ChiTietHoaDon kq = cthdArr.KTCTHD(id, maSP, cthd);
                if (kq != null) {
                    int soLuongMoi = cthd.getSoLuong() + kq.getSoLuong();
                    re = cthdArr.updateQtyCT(id, Integer.parseInt(txtMaSP.getText()), soLuongMoi);
                    float thtNew = soLuongMoi * kq.getDonGia();

                    cthdArr.updateTht(id, Integer.parseInt(txtMaSP.getText()), thtNew);
                    hdArr.updateTT(id, kq.getThanhTien(), thtNew);

                    cthdArr.deleteCTHD(id, maSP);
                    cthdArr.updateQty(maSP, qtyOld, 0);
                    hdArr.updateTT(id, thtOld, 0);

                    if (re != 0) {

                        cthdArr.updateQty(Integer.parseInt(txtMaSP.getText()), 0, Integer.parseInt(txtSL.getText()));

                        JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th??nh c??ng!!!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);

                        loadAllHD(soLuong, Integer.parseInt(lbTrangTop.getText()));
                        loadAllCTHD(id);
                        card.show(pMain, "hd");

                    } else
                        JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th???t b???i!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);
                }

                else {

                    re = cthdArr.updateCTHD(cthd, id, maSP);
                    if (re != 0) {

                        thtOld = Float.parseFloat(tableCTHD.getValueAt(tableCTHD.getSelectedRow(), 6).toString());
                        hdArr.updateTT(id, thtOld, Float.parseFloat(txtThT.getText()));

                        cthdArr.updateQty(maSP, qtyOld, Integer.parseInt(txtSL.getText()));

                        JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th??nh c??ng!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);

                        loadAllHD(soLuong, Integer.parseInt(lbTrangTop.getText()));
                        loadAllCTHD(id);
                        card.show(pMain, "hd");

                    } else
                        JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th???t b???i!", "Th??ng b??o",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
        if (e.getSource() == btTKTop) {
            String ten = txtTKTop.getText();
            txtTKTop.setText("");

            ArrayList<HoaDon> kq = new ArrayList<HoaDon>();
            kq = hdArr.getHDByTen(ten);

            tableHD = hdArr.loadAllHD(tableHD, model1, kq, ttHD);
            tableHD = tableLayout(tableHD, sizeHD);
            tableCTHD.setModel(myModel());
        }

        if (e.getSource() == btTKBottom) {
            if (tableCTHD.getModel().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n tr?????c", "L???i", 0);
            } else {
                String ten = txtTKBottom.getText();
                txtTKBottom.setText("");

                int id = Integer.parseInt(tableHD.getModel().getValueAt(tableHD.getSelectedRow(), 1).toString());

                ArrayList<ChiTietHoaDon> kq = new ArrayList<ChiTietHoaDon>();
                kq = cthdArr.getCTHDByTen(id, ten);

                tableCTHD = cthdArr.loadAllCTHD(tableCTHD, modelCTHD, kq, ttCTHD);
                tableCTHD = tableLayout(tableCTHD, sizeCTHD);
            }
        }

        if (e.getSource() == btAllTop) {
            btNextTop.setEnabled(true);
            btPrevTop.setEnabled(true);
            loadAllHD(soLuong, 1);
        }

        if (e.getSource() == btAllBottom) {
            if (tableCTHD.getModel().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n tr?????c", "L???i", 0);
            } else {
                int id = Integer.parseInt(tableHD.getModel().getValueAt(tableHD.getSelectedRow(), 1).toString());
                loadAllCTHD(id);
            }
        }

        if (e.getActionCommand() == "T??m ki???m n??ng cao") {
            dateFrom.setDate(new Date());
            dateTo.setDate(new Date());
            txtMin.setText("0");
            txtMax.setText("0");
            card.show(pMain, "nc");

        }

        if (e.getActionCommand() == "T??m") {
            ArrayList<HoaDon> kq = new ArrayList<HoaDon>();

            float min = Float.parseFloat(txtMin.getText());
            float max = Float.parseFloat(txtMax.getText());

            kq = hdArr.timNangCao(dateFrom.getDate(), dateTo.getDate(), min, max);

            tableHD = hdArr.loadAllHD(tableHD, model1, kq, ttHD);
            float total = hdArr.getTotal(kq);
            DefaultTableModel model = (DefaultTableModel) tableHD.getModel();
            model.addRow(new Object[] { "", "T???ng Ti???n", "", "", "", total });
            tableHD.setModel(model);

            tableHD = tableLayout(tableHD, sizeHD);
            tableCTHD.setModel(myModel());
            card.show(pMain, "hd");
            btNextTop.setEnabled(false);
            btPrevTop.setEnabled(false);
        }

        if (e.getSource() == btNextTop) {
            int trangCuoi = (int) hdArr.getTongSoTrang(soLuong);
            if (!lbTrangTop.getText().equals(Integer.toString(trangCuoi))) {
                int trangSau = Integer.parseInt(lbTrangTop.getText()) + 1;
                lbTrangTop.setText(Integer.toString(trangSau));
                loadAllHD(soLuong, trangSau);
            } else {
                JOptionPane.showMessageDialog(null, "???? ?????n trang cu???i, kh??ng th??? chuy???n trang k??? ti???p", "L???i", 0);
            }
        }
        if (e.getSource() == btPrevTop) {
            if (!lbTrangTop.getText().equals("1")) {
                int trangTruoc = Integer.parseInt(lbTrangTop.getText()) - 1;
                lbTrangTop.setText(Integer.toString(trangTruoc));
                loadAllHD(soLuong, trangTruoc);
            } else {
                JOptionPane.showMessageDialog(null, "???? ?????n trang ?????u, kh??ng th??? chuy???n trang tr?????c n???a", "L???i", 0);
            }
        }

        if (e.getSource() == btReturn) {
            card.show(pMain, "sp");
        }

        if (e.getSource() == btHTKH) {
            btLoad.setText("Hi???n th??? t???t c??? kh??ch h??ng");
            btHTTK.setText("T??m KH");
            card.show(pMain, "ht");

            getKH();
        }
        if (e.getActionCommand() == "T??m KH") {
            ArrayList<KhachHang> kq = new ArrayList<KhachHang>();

            kq = hdArr.getKHByTen(txtHTTK.getText());

            tableHT = hdArr.loadAllKH(tableHT, modelHT, kq, lbKH);
            tableHT = tableLayout(tableHT, sizeKH);
            txtHTTK.setText("");
        }
        if (e.getActionCommand() == "T??m NV") {

            ArrayList<NhanVien> kq = new ArrayList<NhanVien>();

            kq = hdArr.getNVByTen(txtHTTK.getText());

            tableHT = hdArr.loadAllNV(tableHT, modelHT, kq, lbNV);
            tableHT = tableLayout(tableHT, sizeNV);
            txtHTTK.setText("");
        }
        if (e.getActionCommand() == "Hi???n th??? t???t c??? KH") {
            getKH();

        }
        if (e.getActionCommand() == "Hi???n th??? t???t c??? NV") {
            getNV();
        }
        if (e.getSource() == btHTNV) {
            btLoad.setText("Hi???n th??? t???t c??? NV");
            btHTTK.setText("T??m NV");
            getNV();
            card.show(pMain, "ht");
        }
        if (e.getSource() == btHTSP) {
            btLoad.setText("Hi???n th??? t???t c??? SP");
            btHTTK.setText("T??m SP");
            getSP();
            card.show(pMain, "ht");
        }

        if (e.getActionCommand() == "T??m SP") {

            ArrayList<SanPham> kq = new ArrayList<SanPham>();
            kq = spArr.getSPByTen(txtHTTK.getText());

            tableHT = cthdArr.loadAllSP(tableHT, modelHT1, kq, lbSP);
            tableHT = tableLayout(tableHT, sizeSP);
            txtHTTK.setText("");

        }
        if (e.getActionCommand() == "Hi???n th??? t???t c??? SP") {

            getSP();
        }
        if (e.getActionCommand() == "Export Excel") {

            excel.exportToExcel(tableHD, path);

        }
        if (e.getActionCommand() == "Import Excel") {
            tableHD = excel.importExcel(tableHD, path, ttHD);
            tableHD = tableLayout(tableHD, sizeHD);
        }

        if (e.getActionCommand() == "Export PDF") {
            excel.exportPDF(tableHD, pathPDF, 1);
        }

    }

    public void resetFormHD() {
        int ma = hdArr.getLastMaHD() + 1;
        txtMaHD.setText("" + ma);
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtMaNV.setText("");
        txtTenNV.setText("");
        dateChooser.setDate(new Date());
        txtTT.setText("0");
    }

    public void resetFormCTHD() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSL.setText("");
        txtDG.setText("");
        txtThT.setText("");
    }

    public void getKH() {

        ArrayList<KhachHang> khList = new ArrayList<KhachHang>();
        khList = hdArr.getALLKH();

        tableHT = hdArr.loadAllKH(tableHT, modelHT, khList, lbKH);
        tableHT = tableLayout(tableHT, sizeKH);

    }

    public void getNV() {

        ArrayList<NhanVien> nvList = new ArrayList<NhanVien>();
        nvList = hdArr.getALLNV();

        tableHT = hdArr.loadAllNV(tableHT, modelHT, nvList, lbNV);
        tableHT = tableLayout(tableHT, sizeNV);

    }

    public void getSP() {
        spList = spArr.getAllSP();
        tableHT = cthdArr.loadAllSP(tableHT, modelHT, spList, lbSP);
        tableHT = tableLayout(tableHT, sizeSP);

    }

    public void loadAllHD(int soLuong, int trang) {
        ArrayList<HoaDon> hdList = new ArrayList<HoaDon>();
        hdList = hdArr.getALLHD(soLuong, trang);

        tableHD = hdArr.loadAllHD(tableHD, model, hdList, ttHD);

        tableHD = tableLayout(tableHD, sizeHD);

    }

    public void loadAllCTHD(int ma) {

        ArrayList<ChiTietHoaDon> cthdList = new ArrayList<ChiTietHoaDon>();
        cthdList = cthdArr.getALLCTHD(ma);

        tableCTHD = cthdArr.loadAllCTHD(tableCTHD, modelCTHD, cthdList, ttCTHD);

        tableCTHD = tableLayout(tableCTHD, sizeCTHD);

    }

    class ButtonsPanel extends JPanel {

        public ArrayList<JButton> button = new ArrayList<>();
        JButton b1, b2;

        public ButtonsPanel() {
            super();

            b1 = myButton("S???a", 60, 20, f2);
            b1.setIcon(icon1);
            b1.setBackground(Color.BLUE);
            b1.setForeground(Color.white);
            b2 = myButton("X??a", 60, 20, f2);
            b2.setIcon(icon2);
            b2.setBackground(Color.RED);
            b2.setForeground(Color.white);

            button.add(b1);
            button.add(b2);

            this.setForeground(tableHD.getSelectionForeground());
            this.setBackground(tableHD.getSelectionBackground());
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
                    if (table.getColumnName(2) == "Kh??ch H??ng") {
                        int id = Integer.parseInt(table.getModel().getValueAt(i, 1).toString());
                        loadAllCTHD(id);
                        String sDate = table.getModel().getValueAt(i, 4).toString();

                        try {
                            Date date = fmt.parse(sDate);
                            dateChooser.setDate(date);
                        } catch (ParseException ex) {
                            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        txtMaHD.setText(table.getModel().getValueAt(i, 1).toString());
                        txtTenKH.setText(table.getModel().getValueAt(i, 2).toString());
                        txtTenNV.setText(table.getModel().getValueAt(i, 3).toString());

                        txtTT.setText(table.getModel().getValueAt(i, 5).toString());

                        HoaDon hd = new HoaDon();

                        hd = hdArr.getHDByID(Integer.parseInt(txtMaHD.getText()));

                        txtMaKH.setText("" + hd.getMaKH());
                        txtMaNV.setText("" + hd.getMaNV());
                        btThemCTHD.setEnabled(false);
                        card.show(pMain, "form");
                        btXN.setText("L??u");
                    }
                    if (table.getColumnName(3) == "S???n Ph???m") {

                        txtMaCT.setText(table.getModel().getValueAt(i, 1).toString());
                        txtMaSP.setText(table.getModel().getValueAt(i, 2).toString());
                        txtTenSP.setText(table.getModel().getValueAt(i, 3).toString());

                        txtSL.getDocument().removeDocumentListener(HoaDonPanel.this);

                        txtSL.setText(table.getModel().getValueAt(i, 4).toString());
                        txtDG.setText(table.getModel().getValueAt(i, 5).toString());
                        txtThT.setText(table.getModel().getValueAt(i, 6).toString());

                        txtSL.getDocument().addDocumentListener(HoaDonPanel.this);

                        card.show(pMain, "cthd");
                        btCTHD.setText("Danh S??ch H??");
                        btXNCTHD.setText("L??u CT");
                    }

                }
            });

            button.get(1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (table.getColumnName(2) == "Kh??ch H??ng") {
                        int i = table.getSelectedRow();
                        int idSelected = Integer.parseInt(table.getModel().getValueAt(i, 1).toString());
                        int choice = JOptionPane.showConfirmDialog(pMain,
                                "B???n c?? ch???c mu???n x??a h??a ????n c?? m??: " + idSelected, "X??c nh???n",
                                JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {

                            int maSP = 0;
                            int re = hdArr.deleteHD(idSelected);
                            for (int j = 0; j < tableCTHD.getRowCount(); j++) {
                                cthdArr.updateQty(Integer.parseInt(tableCTHD.getModel().getValueAt(j, 2).toString()),
                                        Integer.parseInt(tableCTHD.getModel().getValueAt(j, 4).toString()), 0);
                            }
                            int r = cthdArr.deleteCTHD(idSelected);
                            loadAllHD(soLuong, Integer.parseInt(lbTrangTop.getText()));
                            loadAllCTHD(idSelected);

                        }
                    }
                    if (table.getColumnName(3) == "S???n Ph???m") {
                        int i = table.getSelectedRow();
                        int id = Integer.parseInt(table.getModel().getValueAt(i, 1).toString());

                        spList = spArr.getAllSP();
                        int maSP = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());

                        int choice = JOptionPane.showConfirmDialog(pMain,
                                "B???n c?? ch???c mu???n x??a chi ti???t c?? m?? SP: " + maSP, "X??c nh???n",
                                JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {

                            float tht = Float.parseFloat(table.getModel().getValueAt(i, 6).toString());
                            int qty = Integer.parseInt(table.getModel().getValueAt(i, 4).toString());
                            int re = cthdArr.deleteCTHD(id, maSP);
                            if (re != 0) {

                                int r = hdArr.updateTT(id, tht, 0);
                                cthdArr.updateQty(maSP, qty, 0);
                                loadAllHD(soLuong, Integer.parseInt(lbTrangTop.getText()));
                                loadAllCTHD(id);
                            }

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

}