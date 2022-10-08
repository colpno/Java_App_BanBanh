package businesslayer;

import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class DungChung {
    public static String pdfPath = "pdf/";
    public static String excelPath = "excel/";

    public static String slashToDash(String str) {
        if (str.contains("/")) {
            String[] splitedFrom = str.split("/");
            return splitedFrom[2] + "-" + splitedFrom[1] + "-" + splitedFrom[0];
        }
        return null;
    }

    public static String dashToSlash(String str) {
        if (str.contains("-")) {
            String[] splitedFrom = str.split("-");
            return splitedFrom[2] + "/" + splitedFrom[1] + "/" + splitedFrom[0];
        }
        return null;
    }

    public static String formatJDateChoose(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = format.format(date);
        return formattedDate;
    }

    public static void setSizeOfButton(int width, int height, JButton... arg) {
        for (JButton btn : arg) {
            btn.setPreferredSize(new Dimension(width, height));
        }
        return;
    }

    public static void resetTextField(JTextField... arg) {
        for (JTextField jtf : arg) {
            jtf.setText("");
        }
    }

    public static class CellRenderer implements TableCellRenderer {
        private int columnIndex;
        private JTable tableName;

        public CellRenderer(int columnIndex, JTable tableName) {
            this.columnIndex = columnIndex;
            this.tableName = tableName;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            TableColumn tb = tableName.getColumnModel().getColumn(columnIndex);
            tb.setMaxWidth(59);
            tb.setMinWidth(59);

            tableName.setRowHeight(59);

            return (Component) value;
        }
    }
}
