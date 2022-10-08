
package application;

import businesslayer.Loai;
import businesslayer.MangLoai;
import test.InputTester;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class LoaiPanel extends JPanel implements ActionListener, MouseListener {

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JPanel pCenter, pTop, pForm, pTK, pBtn;
    private String[] lbLoai = { "Mã Loại", "Tên Loại" };

    private JLabel l1, l2;
    private JTextField txtMaLoai, txtTenLoai;

    private JButton btTK, btThem, btSua, btXoa, btAll;

    private int[] size = { 300, 700 };

    private JTextField txtTK;
    Font f0 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    Font f1 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    private Color color1 = Color.WHITE;
    private Color color2 = new Color(222, 222, 221);

    MangLoai loaiArr = new MangLoai();

    public LoaiPanel() {

        initComponents();
        loadAllLoai();
    }

    public void initComponents() {

        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 600));
        pTop = new JPanel();
        pTop.setBackground(color1);
        pTop.setPreferredSize(new Dimension(0, 250));
        pTop.setLayout(null);

        pCenter = new JPanel();
        pCenter.setBackground(color1);
        pCenter.setLayout(new BorderLayout());

        pForm = new JPanel();
        pForm.setBackground(Color.white);
        pForm.setPreferredSize(new Dimension(700, 200));
        pForm.setBackground(color2);
        pForm.setBounds(100, 20, 900, 200);

        GridBagLayout layout = new GridBagLayout();
        pForm.setLayout(layout);

        l1 = myLabel("Mã Loại", f1);
        l2 = myLabel("Tên Loại", f1);

        txtMaLoai = myTextField(6, f2);
        txtTenLoai = myTextField(18, f2);
        txtMaLoai.setEditable(false);
        txtTenLoai.setEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.insets = new Insets(20, 20, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pForm.add(l1, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(20, 40, 0, 0);
        pForm.add(l2, gbc);

        gbc.insets = new Insets(20, 20, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;

        pForm.add(txtMaLoai, gbc);

        gbc.gridx = 3;
        gbc.insets = new Insets(20, 20, 0, 0);
        pForm.add(txtTenLoai, gbc);

        pBtn = new JPanel(new FlowLayout(0, 50, FlowLayout.TRAILING));
        pBtn.setBackground(color2);

        btThem = myButton("Thêm loại", 100, 30, f2);
        btSua = myButton("Sửa loại", 100, 30, f2);
        btXoa = myButton("Xóa loại", 100, 30, f2);
        btAll = myButton("Hiển thị tất cả", 160, 30, f2);
        pBtn.add(btThem);
        pBtn.add(btSua);
        pBtn.add(btXoa);
        pBtn.add(btAll);

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(30, 0, 0, 0);
        pForm.add(pBtn, gbc);

        pTop.add(pForm);

        table = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent) comp;
                jc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.white));

                if (!comp.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? color1 : color2);
                    comp.setBackground(c);
                    c = null;
                }
                return comp;
            }

        };

        table.setRowHeight(30);
        table.addMouseListener(this);

        table.setFont(f2);
        table.setBackground(color2);

        JTableHeader header = table.getTableHeader();
        header.setOpaque(false);
        header.setBackground(color2);
        header.setFont(f1);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        table.setPreferredScrollableViewportSize(new Dimension(1190, 400));
        scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        pTK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pTK.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pTK.setBackground(color1);
        txtTK = myTextField(18, f2);
        txtTK.setBackground(color1);
        btTK = myButton("Tìm Kiếm", 100, 30, f2);
        pTK.add(txtTK);
        pTK.add(btTK);

        pCenter.add(pTK, BorderLayout.NORTH);
        pCenter.add(scroll, BorderLayout.CENTER);

        add(pTop, BorderLayout.NORTH);
        add(pCenter, BorderLayout.CENTER);

        TitledBorder title = new TitledBorder("THÔNG TIN LOẠI");
        title.setTitleFont(f1);
        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.TOP);

        pForm.setBorder(BorderFactory.createTitledBorder(title));

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

    public JPanel myPanel(LayoutManager layout, Color color) {
        JPanel p = new JPanel();
        p.setLayout(layout);
        p.setBackground(color);
        return p;
    }

    public JTable tableLayout(JTable table) {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(size[i]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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
        if (m.getSource() == btSua) {
            btSua = btHoverExit(btSua);
        }
        if (m.getSource() == btXoa) {
            btXoa = btHoverExit(btXoa);
        }
        if (m.getSource() == btAll) {
            btAll = btHoverExit(btAll);
        }
        if (m.getSource() == btTK) {
            btTK = btHoverExit(btTK);
        }

    }

    public void mouseEntered(MouseEvent m) {
        if (m.getSource() == btThem) {
            btThem = btHoverEnter(btThem);
        }
        if (m.getSource() == btSua) {
            btSua = btHoverEnter(btSua);
        }
        if (m.getSource() == btXoa) {
            btXoa = btHoverEnter(btXoa);
        }
        if (m.getSource() == btAll) {
            btAll = btHoverEnter(btAll);
        }
        if (m.getSource() == btTK) {
            btTK = btHoverEnter(btTK);
        }

    }

    public void mouseReleased(MouseEvent m) {

    }

    public void mousePressed(MouseEvent m) {

    }

    public void mouseClicked(MouseEvent m) {
        if (m.getSource() == table) {
            int i = table.getSelectedRow();
            if (i != -1) {
                txtMaLoai.setText(table.getModel().getValueAt(i, 0).toString());
                txtTenLoai.setText(table.getModel().getValueAt(i, 1).toString());
            }
            btThem.setText("Thêm loại");
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Thêm loại") {
            int ma = loaiArr.getLastMaLoai() + 1;
            txtMaLoai.setText("" + ma);
            resetForm();
            btThem.setText("Lưu");

        }

        else if (e.getActionCommand() == "Lưu") {

            if (InputTester.chiChuVaKhoangTrang(txtTenLoai.getText(), "Tên Loại", 40)) {
                String tenLoai = txtTenLoai.getText();
                if (loaiArr.ktLoai(tenLoai)) {
                    Loai l = new Loai(Integer.parseInt(txtMaLoai.getText()), tenLoai);

                    int re = loaiArr.insertLoai(l);
                    if (re != 0) {
                        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);

                    }
                    loadAllLoai();
                } else {
                    JOptionPane.showMessageDialog(null, "Loại này đã tồn tại", "Lỗi", 0);
                }
                resetForm();
            }
        }

        else if (e.getActionCommand() == "Sửa loại") {
            int i = table.getSelectedRow();
            if (i != -1) {
                if (InputTester.chiChuVaKhoangTrang(txtTenLoai.getText(), "Tên Loại", 40)) {
                    String tenLoai = txtTenLoai.getText();
                    if (loaiArr.ktLoai(tenLoai)) {
                        int id = Integer.parseInt(table.getModel().getValueAt(i, 0).toString());
                        Loai l = new Loai(Integer.parseInt(txtMaLoai.getText()), tenLoai);

                        int re = loaiArr.updateLoai(l, id);
                        if (re != 0) {
                            JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!", "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);

                        }
                        loadAllLoai();
                    } else {
                        JOptionPane.showMessageDialog(null, "Loại này đã tồn tại", "Lỗi", 0);
                    }
                }
            }

            resetForm();
        }

        else if (e.getActionCommand() == "Xóa loại") {
            int i = table.getSelectedRow();
            if (i != -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(i, 0).toString());
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dữ liệu của !id=" + id,
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    int re = loaiArr.deleteLoai(id);
                    resetForm();
                    loadAllLoai();

                }
            }
        }

        else if (e.getActionCommand() == "Tìm Kiếm") {

            String name = txtTK.getText();
            txtTK.setText("");

            ArrayList<Loai> loaiList = loaiArr.getLoaiByTen(name);
            table = loaiArr.loadAllLoai(table, model, loaiList, lbLoai);
            table = tableLayout(table);
        } else if (e.getActionCommand() == "Hiển thị tất cả") {

            loadAllLoai();

        }
    }

    public void loadAllLoai() {

        ArrayList<Loai> loaiList = loaiArr.getAllLoai();
        table = loaiArr.loadAllLoai(table, model, loaiList, lbLoai);
        table = tableLayout(table);

    }

    public void resetForm() {
        txtTenLoai.setText("");
        txtTenLoai.setEditable(true);
        txtTenLoai.requestFocus();
    }

}
