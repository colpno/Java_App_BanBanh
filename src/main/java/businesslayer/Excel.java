
package businesslayer;


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


import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Excel {
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
    public void exportToExcel(JTable table, String path){

        try {
            TableModel model = table.getModel();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            Row row;
            Cell cell;
            
            
            row = sheet.createRow(0);
            for (int c=1;c<model.getColumnCount()-1; c++) {
                cell=row.createCell(c);
                cell.setCellValue(model.getColumnName(c));
            }
            
            // write the data rows
            for (int r=0; r<model.getRowCount(); r++) {
                row = sheet.createRow(r+1);
                for (int c=1;c<model.getColumnCount()-1; c++) {
                    cell=row.createCell(c);
                    Object value = model.getValueAt(r, c);

                    if (value instanceof String) {
                        cell.setCellValue((String)value);
                    }
                    else if (value instanceof Integer) {
                        cell.setCellValue((Integer)value);
                    }
                    else if (value instanceof Float) {
                        cell.setCellValue((Float)value);
                    }
                }
            }
            
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.close();
            workbook.close();
            JOptionPane.showMessageDialog(null, "Exported Successfully !!.....");
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultTableModel modelHeader(DefaultTableModel model,String[] name){
        Vector header=new Vector();
        for(String s: name){
            header.add(s);
        }
        model=new DefaultTableModel(header,0);
        return model;
    }
    
    public JTable importExcel(JTable table,String path,String[] name) {
        
        DefaultTableModel model=new DefaultTableModel();
        model=modelHeader(model,name);
    
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        
        JFileChooser excelFileChooser = new JFileChooser(path);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        
        int excelChooser = excelFileChooser.showOpenDialog(null);
        
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
 
                for (int row=1;row<excelSheet.getLastRowNum(); row++){
                    XSSFRow excelRow=excelSheet.getRow(row);
                    
                    Vector record=new Vector();
                    
                    for(int cell=0;cell<excelRow.getLastCellNum();cell++){

                        record.add(excelRow.getCell(cell));
                    }
                    
                    model.addRow(record);
                }
                table.setModel(model);
                
                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
        return table;
    }  
    
    public void exportPDF(JTable jtable,String path,int begin){
        
        File fontFile=new File("pdf/vuArial.ttf");
        Font font=new Font();
        try {
            BaseFont bf=BaseFont.createFont(fontFile.getAbsolutePath(),BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            font=new Font(bf,15);
        } catch (DocumentException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            PdfPTable table = new PdfPTable(jtable.getModel().getColumnCount()-1-begin);
            
            for(int i=begin;i<jtable.getModel().getColumnCount()-1;i++){
                String name=jtable.getModel().getColumnName(i);
                PdfPCell c1 = new PdfPCell(new Phrase(name,font));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }

            for(int i=0;i<jtable.getModel().getRowCount();i++){
                for(int j=begin;j<jtable.getModel().getColumnCount()-1;j++){
                    table.addCell(new PdfPCell(new Phrase(jtable.getModel().getValueAt(i,j).toString(),font)));
                }

            }

            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Exported Successfully !!.....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
