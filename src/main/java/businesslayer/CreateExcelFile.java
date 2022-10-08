package businesslayer;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class CreateExcelFile {
  public static void fileNSX(ArrayList<NhaSanXuat> dsNSX) {
    JPanel x=new JPanel();
    JFileChooser chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Chọn nơi lưu trữ");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(x) == JFileChooser.APPROVE_OPTION) { 
         try {
            //tạo một đối tượng của lớp HSSFWorkbook
            Workbook workbook = new HSSFWorkbook();
            //gọi phương thức creatSheet() và truyền tên file muốn tạo
            Sheet sheet = workbook.createSheet("Nhà sản xuất");
            //tạo hàng thứ 0 sử dụng phương thức createRow()
            
            Font font = sheet.getWorkbook().createFont();
            font.setFontName("Times New Roman");
            font.setBold(true);
            font.setFontHeightInPoints((short) 24); // font size
            font.setColor(IndexedColors.BLACK.getIndex()); // text color
            
            Font font1 = sheet.getWorkbook().createFont();
            font1.setFontName("Times New Roman");
            font1.setFontHeightInPoints((short) 14); // font size
            font1.setColor(IndexedColors.BLACK.getIndex()); // text color
            
            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
            cellStyle1.setFont(font1);
            cellStyle1.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellStyle1.setBorderRight(BorderStyle.MEDIUM);
            cellStyle1.setBorderLeft(BorderStyle.MEDIUM);
            
            Row heading = sheet.createRow((short) 0);
            heading.createCell(0).setCellValue("Nhà sản xuất");
            heading.getCell(0).setCellStyle(cellStyle);
            
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
            Row rowhead = sheet.createRow((short) 1);
            //tạo ô bằng cách sử dụng phương thức createCell() và thiết lập giá trị cho ô bằng cách sử dụng phương thức setCellValue()
            rowhead.createCell(0).setCellValue("STT");
            rowhead.createCell(1).setCellValue("Mã NSX");
            rowhead.createCell(2).setCellValue("Tên nhà sản xuất");
            rowhead.createCell(3).setCellValue("Địa chỉ");
            rowhead.createCell(4).setCellValue("Số điện thoại");
            
            rowhead.getCell(0).setCellStyle(cellStyle1);
            rowhead.getCell(1).setCellStyle(cellStyle1);
            rowhead.getCell(2).setCellStyle(cellStyle1);
            rowhead.getCell(3).setCellStyle(cellStyle1);
            rowhead.getCell(4).setCellStyle(cellStyle1);
            
            //tạo hàng thứ 1
            int i=2;
            for (NhaSanXuat nsx : dsNSX) {
                Row row = sheet.createRow((short) i);
                //chèn dữ liệu vào hàng thứ 1
                row.createCell(0).setCellValue(i-1);
                row.createCell(1).setCellValue(nsx.getMaNSX());
                row.createCell(2).setCellValue(nsx.getTenNSX());
                row.createCell(3).setCellValue(nsx.getDiaChi());
                row.createCell(4).setCellValue(nsx.getSoDienThoai());
                
                row.getCell(0).setCellStyle(cellStyle1);
                row.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                row.getCell(1).setCellStyle(cellStyle1);
                row.getCell(1).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                row.getCell(2).setCellStyle(cellStyle1);
                row.getCell(3).setCellStyle(cellStyle1);
                row.getCell(4).setCellStyle(cellStyle1);
                i++;
            }
            for (int j=0;j<5;j++) {
                 sheet.autoSizeColumn(j);
            }
           

            FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+"\\nhasanxuat.xls");
            workbook.write(fileOut);
            //đóng stream
            fileOut.close();
            //đóng workbook
            workbook.close();
            //in thông báo tạo thành công
            JOptionPane.showMessageDialog(null,"Tạo file excel thành công","Excel",JOptionPane.INFORMATION_MESSAGE);
          } catch (Exception e) {
            e.printStackTrace();
          }
      }
     }
public static void fileNCC(ArrayList<NhaCungCap> dsNCC) {
    JPanel x=new JPanel();
    JFileChooser chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Chọn nơi lưu trữ");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(x) == JFileChooser.APPROVE_OPTION) { 
         try {
            //tạo một đối tượng của lớp HSSFWorkbook
            Workbook workbook = new HSSFWorkbook();
            //gọi phương thức creatSheet() và truyền tên file muốn tạo
            Sheet sheet = workbook.createSheet("Nhà cung cấp");
            //tạo hàng thứ 0 sử dụng phương thức createRow()
            
            Font font = sheet.getWorkbook().createFont();
            font.setFontName("Times New Roman");
            font.setBold(true);
            font.setFontHeightInPoints((short) 24); // font size
            font.setColor(IndexedColors.BLACK.getIndex()); // text color
            
            Font font1 = sheet.getWorkbook().createFont();
            font1.setFontName("Times New Roman");
            font1.setFontHeightInPoints((short) 14); // font size
            font1.setColor(IndexedColors.BLACK.getIndex()); // text color
            
            Font font2 = sheet.getWorkbook().createFont();
            font2.setBold(true);
            font2.setFontName("Times New Roman");
            font2.setFontHeightInPoints((short) 14); // font size
            font2.setColor(IndexedColors.BLACK.getIndex()); // text color
            
            
            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
            cellStyle1.setFont(font1);
            cellStyle1.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellStyle1.setBorderRight(BorderStyle.MEDIUM);
            cellStyle1.setBorderLeft(BorderStyle.MEDIUM);
            
            CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
            cellStyle2.setFont(font2);
            cellStyle2.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle2.setBorderTop(BorderStyle.MEDIUM);
            cellStyle2.setBorderRight(BorderStyle.MEDIUM);
            cellStyle2.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            
            Row heading = sheet.createRow((short) 0);
            heading.createCell(0).setCellValue("Nhà cung cấp");
            heading.getCell(0).setCellStyle(cellStyle);
            
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
            Row rowhead = sheet.createRow((short) 1);
            //tạo ô bằng cách sử dụng phương thức createCell() và thiết lập giá trị cho ô bằng cách sử dụng phương thức setCellValue()
            rowhead.createCell(0).setCellValue("STT");
            rowhead.createCell(1).setCellValue("Mã NCC");
            rowhead.createCell(2).setCellValue("Tên nhà cung cấp");
            rowhead.createCell(3).setCellValue("Địa chỉ");
            rowhead.createCell(4).setCellValue("Số điện thoại");
            
            rowhead.getCell(0).setCellStyle(cellStyle2);
            rowhead.getCell(1).setCellStyle(cellStyle2);
            rowhead.getCell(2).setCellStyle(cellStyle2);
            rowhead.getCell(3).setCellStyle(cellStyle2);
            rowhead.getCell(4).setCellStyle(cellStyle2);
            
            
            //tạo hàng thứ 1
            int i=2;
            for (NhaCungCap nsx : dsNCC) {
                Row row = sheet.createRow((short) i);
                //chèn dữ liệu vào hàng thứ 1
                row.createCell(0).setCellValue(i-1);
                row.createCell(1).setCellValue(nsx.getMaNCC());
                row.createCell(2).setCellValue(nsx.getTenNCC());
                row.createCell(3).setCellValue(nsx.getDiaChi());
                row.createCell(4).setCellValue(nsx.getSoDienThoai());
                
                row.getCell(0).setCellStyle(cellStyle1);
                row.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                row.getCell(1).setCellStyle(cellStyle1);
                row.getCell(1).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                row.getCell(2).setCellStyle(cellStyle1);
                row.getCell(3).setCellStyle(cellStyle1);
                row.getCell(4).setCellStyle(cellStyle1);
                i++;
            }
            for (int j=0;j<5;j++) {
                 sheet.autoSizeColumn(j);
            }
           

            FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+"\\nhacungcap.xls");
            workbook.write(fileOut);
            //đóng stream
            fileOut.close();
            //đóng workbook
            workbook.close();
            //in thông báo tạo thành công
            JOptionPane.showMessageDialog(null,"Tạo file excel thành công","Excel",JOptionPane.INFORMATION_MESSAGE);
          } catch (Exception e) {
            e.printStackTrace();
          }
      }
     }
     public static void fileKhuyenMai(ArrayList<KhuyenMai> dsKM,ArrayList<ChiTietKhuyenMai> dsCTKM) {
         JPanel x=new JPanel();
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Chọn nơi lưu trữ");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(x) == JFileChooser.APPROVE_OPTION) { 
             try {
                //tạo một đối tượng của lớp HSSFWorkbook
                Workbook workbook = new HSSFWorkbook();
                //gọi phương thức creatSheet() và truyền tên file muốn tạo
                Sheet sheet = workbook.createSheet("Khuyến mãi");
                Sheet sheet1 = workbook.createSheet("Chi Tiết Khuyến mãi");
                //tạo hàng thứ 0 sử dụng phương thức createRow()

                Font font = sheet.getWorkbook().createFont();
                font.setFontName("Times New Roman");
                font.setBold(true);
                font.setFontHeightInPoints((short) 24); // font size
                font.setColor(IndexedColors.BLACK.getIndex()); // text color

                Font font1 = sheet.getWorkbook().createFont();
                font1.setFontName("Times New Roman");
                font1.setFontHeightInPoints((short) 14); // font size
                font1.setColor(IndexedColors.BLACK.getIndex()); // text color

                Font font2 = sheet.getWorkbook().createFont();
                font2.setBold(true);
                font2.setFontName("Times New Roman");
                font2.setFontHeightInPoints((short) 14); // font size
                font2.setColor(IndexedColors.BLACK.getIndex()); // text color


                CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                cellStyle.setFont(font);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);

                CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
                cellStyle1.setFont(font1);
                cellStyle1.setBorderBottom(BorderStyle.MEDIUM);
                cellStyle1.setBorderTop(BorderStyle.MEDIUM);
                cellStyle1.setBorderRight(BorderStyle.MEDIUM);
                cellStyle1.setBorderLeft(BorderStyle.MEDIUM);

                CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
                cellStyle2.setFont(font2);
                cellStyle2.setBorderBottom(BorderStyle.MEDIUM);
                cellStyle2.setBorderTop(BorderStyle.MEDIUM);
                cellStyle2.setBorderRight(BorderStyle.MEDIUM);
                cellStyle2.setBorderLeft(BorderStyle.MEDIUM);
                cellStyle2.setAlignment(HorizontalAlignment.CENTER);

                Row heading1 = sheet.createRow((short) 0);
                heading1.createCell(0).setCellValue("Khuyến mãi");
                heading1.getCell(0).setCellStyle(cellStyle);

                sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
                Row rowhead = sheet.createRow((short) 1);
                //tạo ô bằng cách sử dụng phương thức createCell() và thiết lập giá trị cho ô bằng cách sử dụng phương thức setCellValue()
                rowhead.createCell(0).setCellValue("STT");
                rowhead.createCell(1).setCellValue("Mã khuyến mãi");
                rowhead.createCell(2).setCellValue("Tên khuyến mãi");
                rowhead.createCell(3).setCellValue("Ngày bắt đầu");
                rowhead.createCell(4).setCellValue("Ngày kết thúc");

                rowhead.getCell(0).setCellStyle(cellStyle2);
                rowhead.getCell(1).setCellStyle(cellStyle2);
                rowhead.getCell(2).setCellStyle(cellStyle2);
                rowhead.getCell(3).setCellStyle(cellStyle2);
                rowhead.getCell(4).setCellStyle(cellStyle2);


                //tạo hàng thứ 1
                int i=2;
                for (KhuyenMai km : dsKM) {
                    Row row = sheet.createRow((short) i);
                    //chèn dữ liệu vào hàng thứ 1
                    row.createCell(0).setCellValue(i-1);
                    row.createCell(1).setCellValue(km.getMaKM());
                    row.createCell(2).setCellValue(km.getTenKM());
                    row.createCell(3).setCellValue(km.getNgayBatDau());
                    row.createCell(4).setCellValue(km.getNgayKetThuc());

                    row.getCell(0).setCellStyle(cellStyle1);
                    row.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    row.getCell(1).setCellStyle(cellStyle1);
                    row.getCell(1).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    row.getCell(2).setCellStyle(cellStyle1);
                    row.getCell(3).setCellStyle(cellStyle1);
                    row.getCell(4).setCellStyle(cellStyle1);
                    i++;
                }
                // bảng mới
                
                Row heading2 = sheet1.createRow((short) 0);
                heading2.createCell(0).setCellValue("Chi Tiết Khuyến mãi");
                heading2.getCell(0).setCellStyle(cellStyle);
                sheet1.addMergedRegion(new CellRangeAddress(0,0,0,3));
                
                Row rowhead2 = sheet1.createRow((short) 1);
                //tạo ô bằng cách sử dụng phương thức createCell() và thiết lập giá trị cho ô bằng cách sử dụng phương thức setCellValue()
                rowhead2.createCell(0).setCellValue("STT");
                rowhead2.createCell(1).setCellValue("Mã khuyến mãi");
                rowhead2.createCell(2).setCellValue("Mã sản phẩm");
                rowhead2.createCell(3).setCellValue("Phần trăm");

                rowhead2.getCell(0).setCellStyle(cellStyle2);
                rowhead2.getCell(1).setCellStyle(cellStyle2);
                rowhead2.getCell(2).setCellStyle(cellStyle2);
                rowhead2.getCell(3).setCellStyle(cellStyle2);


                //tạo hàng thứ 1
                i=2;
                for (ChiTietKhuyenMai ctkm : dsCTKM) {
                    Row row = sheet1.createRow((short) i);
                    //chèn dữ liệu vào hàng thứ 1
                    row.createCell(0).setCellValue(i-1);
                    row.createCell(1).setCellValue(ctkm.getMaKM());
                    row.createCell(2).setCellValue(ctkm.getMaSP());
                    row.createCell(3).setCellValue(ctkm.getPhanTramKhuyenMai());

                    row.getCell(0).setCellStyle(cellStyle1);
                    row.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    row.getCell(1).setCellStyle(cellStyle1);
                    row.getCell(1).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    row.getCell(2).setCellStyle(cellStyle1);
                    row.getCell(2).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    row.getCell(3).setCellStyle(cellStyle1);
                    row.getCell(3).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    i++;
                }
                
                for (int k=0;k<5;k++) {
                     sheet.autoSizeColumn(k);
                }
                for (int k=0;k<4;k++) {
                     sheet1.autoSizeColumn(k);
                }
                

                FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+"\\khuyenmai.xls");
                workbook.write(fileOut);
                //đóng stream
                fileOut.close();
                //đóng workbook
                workbook.close();
                //in thông báo tạo thành công
                JOptionPane.showMessageDialog(null,"Tạo file excel thành công","Excel",JOptionPane.INFORMATION_MESSAGE);
              } catch (Exception e) {
                e.printStackTrace();
              }
          }
     }
}