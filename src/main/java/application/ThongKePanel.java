
package application;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import businesslayer.ThongKeBUS;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ThongKePanel extends JPanel implements ActionListener, MouseListener {
    private JTable table;
    private DefaultTableModel model;
    private JPanel pThKe, pTop, pCenter, pYear;
    private JScrollPane scroll;
    private JComboBox cb;
    private String[] ttThKe = { "", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12", "TC" };
    private int[] size = { 150, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80 };
    private JLabel l, l1;

    private ThongKeBUS tkBus = new ThongKeBUS();

    Font f0 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

    private Color color1 = Color.WHITE;
    private Color color2 = new Color(222, 222, 221);

    public ThongKePanel() {
        initComponents();
        // thongKe();
    }

    public void initComponents() {
        setBackground(color1);
        pThKe = myPanel(new BorderLayout(), color1);
        pThKe.setPreferredSize(new Dimension(1100, 600));

        l = myLabel("THỐNG KÊ THEO NĂM", f0);

        pThKe.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pCenter = new JPanel();
        pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
        pCenter.setBackground(color1);

        pYear = myPanel(new FlowLayout(0, 20, FlowLayout.LEFT), color1);

        l1 = myLabel("Năm:", f1);

        cb = myComboBox(tkBus.getYear(), f2);
        cb.addActionListener(this);

        pYear.add(l1);
        pYear.add(cb);

        table = new JTable() {
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

        model = myModel();

        table.setModel(model);
        table.setRowHeight(30);
        table.setFont(f2);
        table.addMouseListener(this);
        table.setPreferredScrollableViewportSize(new Dimension(1100, 400));
        table.setFillsViewportHeight(true);
        table.setBackground(color1);

        JTableHeader header = table.getTableHeader();
        header.setOpaque(false);
        header.setBackground(color2);
        header.setFont(f1);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        scroll = new JScrollPane(table);
        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        pCenter.add(pYear);
        pCenter.add(scroll);

        pThKe.add(l, BorderLayout.NORTH);
        pThKe.add(pCenter, BorderLayout.CENTER);

        add(pThKe);

    }

    public DefaultTableModel myModel() {
        Vector header = new Vector();
        for (int i = 0; i < ttThKe.length; i++) {
            header.add(ttThKe[i]);
        }

        DefaultTableModel model = new DefaultTableModel(header, 0);
        return model;
    }

    public JPanel myPanel(LayoutManager layout, Color color) {
        JPanel p = new JPanel();
        p.setLayout(layout);
        p.setBackground(color);
        return p;
    }

    public JLabel myLabel(String name, Font f) {
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        label.setFont(f);
        return label;
    }

    public JComboBox myComboBox(Vector name, Font f) {
        JComboBox cb = new JComboBox();
        cb.setFont(f);
        cb.setModel(new DefaultComboBoxModel(name));
        return cb;
    }

    public JTable tableLayout(JTable table) {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(size[i]);
        }
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        table.setColumnSelectionAllowed(false);

        return table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int year = Integer.parseInt(cb.getSelectedItem().toString());
        try {
            table = tkBus.thongKe(table, model, ttThKe, year);
        } catch (Exception ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        table = tableLayout(table);

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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
