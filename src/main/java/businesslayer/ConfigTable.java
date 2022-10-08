package businesslayer;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;
import java.awt.Font;

public class ConfigTable {
    public static DefaultTableModel addTableHeader(String[] titles,int edit) {
        int titleLength = titles.length;
        Vector<String> header = new Vector<String>();
        for (int i = 0; i < titleLength; i++) {
            header.add(titles[i]);
        }
        return new DefaultTableModel(header,0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                    switch (edit) {
                        case 0:
                            return column==0;
                        case 1:
                            return column==1;
                        case 2:
                            return column==2;
                        case 3:
                            return column==3;
                        case 4:
                            return column==4;
                        case 5:
                            return column==5;
                        case 6:
                            return column==6;
                        case 7:
                            return column==7;  
                        default:
                            return false;
                    }      
            }
        };
    }
    public static DefaultTableModel addTableHeader(String[] titles) {
        int titleLength = titles.length;
        Vector<String> header = new Vector<String>();
        for (int i = 0; i < titleLength; i++) {
            header.add(titles[i]);
        }
        return new DefaultTableModel(header, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return true;
                }
                if (column == titleLength - 1) {
                    return true;
                }
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }
        };
    }

    public static void decorateTable(JTable tableName, int[] cotDuocCanhGiua, int soCot, String[] kieuSuaDoi) {
        tableName.setRowHeight(25);
        tableName.setBackground(Color.WHITE);
        tableName.setGridColor(Color.WHITE);
        tableName.setSelectionBackground(new Color(32, 136, 203));
        tableName.setSelectionForeground(Color.WHITE);
        tableName.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
        tableName.getTableHeader().setBackground(new Color(0xfc498e));
        tableName.getTableHeader().setForeground(new Color(255, 255, 255));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i : cotDuocCanhGiua) {
            if (i != 0) {
                tableName.getColumnModel().getColumn(i).setCellRenderer(center);
            }
        }
        if (soCot == kieuSuaDoi.length) {
            for (int i = 0; i < soCot; i++) {

                String opt = kieuSuaDoi[i];
                if (opt.substring(0, opt.indexOf("-")).equals("p")) {
                    int width = Integer.parseInt(opt.substring(opt.indexOf("-") + 1));
                    tableName.getColumnModel().getColumn(i).setPreferredWidth(width);
                }
                if (opt.substring(0, opt.indexOf("-")).equals("mi")) {
                    int width = Integer.parseInt(opt.substring(opt.indexOf("-") + 1));
                    tableName.getColumnModel().getColumn(i).setMinWidth(width);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ConfigTable.java -> số lượng của kieuSuaDoi phải bằng soCot", "Lỗi",
                    0);
        }
        tableName.getTableHeader().setReorderingAllowed(false);
    }
}
