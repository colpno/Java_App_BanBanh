package businesslayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import datalayer.tblChiTietPhieuNhapHang;

public class MangChiTietPhieuNhapHang {
    private tblChiTietPhieuNhapHang DSCTPNH = new tblChiTietPhieuNhapHang();
    private MangPhieuNhapHang PNHArr = new MangPhieuNhapHang();
    private ArrayList<ChiTietPhieuNhapHang> CTPNHArr = new ArrayList<ChiTietPhieuNhapHang>();
    private ArrayList<ChiTietPhieuNhapHang> CurrentTable;

    public String[] title() {
        String[] title = { "XÓA", "Mã phiếu", "Mã sản phẩm", "Số lượng", "Đơn giá gốc", "Thành tiền", "Thao tác" };

        return title;
    }

    public MangChiTietPhieuNhapHang() {
        this.CTPNHArr = DSCTPNH.get();
    }

    public ArrayList<ChiTietPhieuNhapHang> getAll() {
        return this.CTPNHArr;
    }

    public void setArr(ArrayList<ChiTietPhieuNhapHang> arr) {
        CTPNHArr = arr;
    }

    public double getTongSoTrang(int soLuong) {
        return CurrentTable.size() > 0 ? Math.ceil((double) CurrentTable.size() / soLuong) : 1.0;
    }

    public ArrayList<ChiTietPhieuNhapHang> get(int maPhieu, int soLuongRow, int trang) {
        CurrentTable = new ArrayList<ChiTietPhieuNhapHang>();
        for (int i = 0; i < CTPNHArr.size(); i++) {
            if (CTPNHArr.get(i).getMaPhieu() == maPhieu) {
                CurrentTable.add(CTPNHArr.get(i));
            }
        }

        ArrayList<ChiTietPhieuNhapHang> newCTPNHArr = new ArrayList<ChiTietPhieuNhapHang>();
        int start = soLuongRow * trang - soLuongRow;
        int length = soLuongRow * trang;

        for (int i = start; i < length && i < CurrentTable.size(); i++) {
            newCTPNHArr.add(CurrentTable.get(i));
        }
        return newCTPNHArr;
    }

    public void add(int maSP, int maPhieu, int soLuong, int donGiaGoc, int thanhTien) {
        if (JOptionPane.showConfirmDialog(null, "Thông tin bạn ghi chính xác chưa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            Boolean isExisted = false;
            for (ChiTietPhieuNhapHang ctpnh : CTPNHArr) {
                if (ctpnh.getMaPhieu() == maPhieu && ctpnh.getMaSP() == maSP && ctpnh.getDonGiaGoc() == donGiaGoc) {
                    isExisted = true;
                    int tongSoLuong = soLuong + ctpnh.getSoLuong(), tongThanhTien = thanhTien + ctpnh.getThanhTien();
                    ctpnh.setSoLuong(tongSoLuong);
                    ctpnh.setThanhTien(tongThanhTien);

                    DSCTPNH.edit(new ChiTietPhieuNhapHang(maPhieu, maSP, tongSoLuong, donGiaGoc, tongThanhTien));
                }
            }
            if (isExisted == false) {
                if (DSCTPNH.add(new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong, donGiaGoc, thanhTien))) {
                    CTPNHArr.add(new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong, donGiaGoc, thanhTien));
                }
            }

            PNHArr.updateTongTien(maPhieu, CTPNHArr);
        }
    }

    public void removeByMaSP(int maPhieu, ArrayList<Integer> maSP, ArrayList<Integer> donGiaGoc) {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            Integer[] ids = maSP.toArray(new Integer[0]);
            Integer[] donGiaGocs = donGiaGoc.toArray(new Integer[0]);

            String removeString = "";
            for (int i = 0; i < ids.length; i++) {
                removeString += " OR (maSP = " + ids[i] + " AND donGiaGoc = " + donGiaGocs[i] + ")";
            }
            removeString = removeString.substring(4);

            if (DSCTPNH.removeByMaSP(maPhieu, removeString)) {

                setArr(DSCTPNH.get());

                PNHArr.updateTongTien(maPhieu, CTPNHArr);
            }
        }
    }

    public void removeByMaPhieu(ArrayList<Integer> maPhieu) {
        Integer[] ids = maPhieu.toArray(new Integer[0]);

        if (DSCTPNH.removeByMaPhieu(ids)) {
            for (int i = 0; i < CTPNHArr.size(); i++) {
                for (Integer id : ids) {
                    if (CTPNHArr.get(i).getMaPhieu() == id) {
                        CTPNHArr.remove(i);
                    }
                }
            }
        }
    }

    public void edit(int maSP, int maPhieu, int soLuong, int donGiaGoc, int thanhTien) {
        ChiTietPhieuNhapHang newCTPNH = new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong, donGiaGoc, thanhTien);
        for (ChiTietPhieuNhapHang ctpnh : CTPNHArr) {
            if (ctpnh.getMaSP() == maSP) {
                ctpnh.setMaSP(maSP);
                ctpnh.setMaPhieu(maPhieu);
                ctpnh.setSoLuong(soLuong);
                ctpnh.setDonGiaGoc(donGiaGoc);
                ctpnh.setThanhTien(thanhTien);
            }
        }

        DSCTPNH.edit(newCTPNH);

        PNHArr.updateTongTien(maPhieu, CTPNHArr);
    }

    public ArrayList<ChiTietPhieuNhapHang> search(String col, String giaTriTim) {
        ArrayList<ChiTietPhieuNhapHang> PNHnhArr = new ArrayList<ChiTietPhieuNhapHang>();
        for (ChiTietPhieuNhapHang ctpnh : CTPNHArr) {
            switch (col) {
                case "Mã sản phẩm": {
                    if (ctpnh.getMaSP() == Integer.parseInt(giaTriTim)) {
                        PNHnhArr.add(ctpnh);
                    }
                    break;
                }
                case "Số lượng": {
                    if (ctpnh.getSoLuong() == Integer.parseInt(giaTriTim)) {
                        PNHnhArr.add(ctpnh);
                    }
                    break;
                }
                case "Đơn giá gốc": {
                    if (ctpnh.getDonGiaGoc() == Integer.parseInt(giaTriTim)) {
                        PNHnhArr.add(ctpnh);
                    }
                    break;
                }
                case "Thành tiền": {
                    if (ctpnh.getThanhTien() == Integer.parseInt(giaTriTim)) {
                        PNHnhArr.add(ctpnh);
                    }
                    break;
                }
            }
        }
        return PNHnhArr;
    }

    public ArrayList<ChiTietPhieuNhapHang> filter(String col, String from, String to) {
        ArrayList<ChiTietPhieuNhapHang> newArr = new ArrayList<ChiTietPhieuNhapHang>();
        for (ChiTietPhieuNhapHang ctpnh : CTPNHArr) {
            switch (col) {
                case "Mã sản phẩm": {
                    if (ctpnh.getMaSP() >= Integer.parseInt(from) && ctpnh.getMaSP() <= Integer.parseInt(to)) {
                        newArr.add(ctpnh);
                    }
                    break;
                }
                case "Số lượng": {
                    if (ctpnh.getSoLuong() >= Integer.parseInt(from) && ctpnh.getSoLuong() <= Integer.parseInt(to)) {
                        newArr.add(ctpnh);
                    }
                    break;
                }
                case "Đơn giá gốc": {
                    if (ctpnh.getDonGiaGoc() >= Integer.parseInt(from)
                            && ctpnh.getDonGiaGoc() <= Integer.parseInt(to)) {
                        newArr.add(ctpnh);
                    }
                    break;
                }
                case "Thành tiền": {
                    if (ctpnh.getThanhTien() >= Integer.parseInt(from)
                            && ctpnh.getThanhTien() <= Integer.parseInt(to)) {
                        newArr.add(ctpnh);
                    }
                    break;
                }
            }
        }
        return newArr;
    }

    public void sort(String col, String orderString) {
        switch (orderString) {
            case "Tăng dần": {
                switch (col) {
                    case "Mã sản phẩm": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s1.getMaSP() - s2.getMaSP());
                        break;
                    }
                    case "Số lượng": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s1.getSoLuong() - s2.getSoLuong());
                        break;
                    }
                    case "Đơn giá gốc": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s1.getDonGiaGoc() - s2.getDonGiaGoc());
                        break;
                    }
                    case "Thành tiền": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s1.getThanhTien() - s2.getThanhTien());
                        break;
                    }
                }
                break;
            }
            case "Giảm dần": {
                switch (col) {
                    case "Mã sản phẩm": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s2.getMaSP() - s1.getMaSP());
                        break;
                    }
                    case "Số lượng": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s2.getSoLuong() - s1.getSoLuong());
                        break;
                    }
                    case "Đơn giá gốc": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s2.getDonGiaGoc() - s1.getDonGiaGoc());
                        break;
                    }
                    case "Thành tiền": {
                        Collections.sort(CTPNHArr, (s1, s2) -> s2.getThanhTien() - s1.getThanhTien());
                        break;
                    }
                }
                break;
            }
        }
    }

    public void ghiFile() {
        if (JOptionPane.showConfirmDialog(null, "Ghi dữ liệu vào excel?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            WriteExcel ghiFile = new WriteExcel();
            try {
                ghiFile.writeExcel();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public ArrayList<ChiTietPhieuNhapHang> docFile() {
        if (JOptionPane.showConfirmDialog(null, "Đọc dữ liệu từ excel?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            ReadExcel docFile = new ReadExcel();
            try {
                return docFile.readExcel();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public class WriteExcel {
        public final int COLUMN_INDEX_MAPHIEU = 0;
        public final int COLUMN_INDEX_MASP = 1;
        public final int COLUMN_INDEX_SOLUONG = 2;
        public final int COLUMN_INDEX_DONGIAGOC = 3;
        public final int COLUMN_INDEX_THANHTIEN = 4;
        public final String excelFilePath = DungChung.excelPath + "phieunhaphang.xlsx";
        private CellStyle cellStyleFormatNumber = null;

        public void writeExcel() throws IOException {
            File dir = new File(DungChung.excelPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Get file
            InputStream inputStream = new FileInputStream(excelFilePath);

            // Get workbook
            Workbook workbook = getWorkbook(inputStream, excelFilePath);

            // Create sheet
            Sheet sheet = workbook.createSheet("ChiTietPhieuNhapHang"); // Create sheet with sheet name

            int rowIndex = 0;

            // Write header
            writeHeader(sheet, rowIndex);

            // Write data
            rowIndex++;
            for (ChiTietPhieuNhapHang ctpnh : CTPNHArr) {
                // Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                writeChiTietPhieuNhapHang(ctpnh, row);
                rowIndex++;
            }

            // Write footer
            writeFooter(sheet, rowIndex);

            // Auto resize column witdth
            int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
            autosizeColumn(sheet, numberOfColumn);

            // Create file excel
            createOutputFile(workbook, excelFilePath);

            inputStream.close();
            JOptionPane.showMessageDialog(null, "Đã ghi vào excel", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        // Create workbook
        private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
            Workbook workbook = null;
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("Không phải là file excel");
            }

            return workbook;
        }

        // Write header with format
        private void writeHeader(Sheet sheet, int rowIndex) {
            // create CellStyle
            CellStyle cellStyle = createStyleForHeader(sheet);

            // Create row
            Row row = sheet.createRow(rowIndex);

            // Create cells
            Cell cell = row.createCell(COLUMN_INDEX_MAPHIEU);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã phiếu");

            cell = row.createCell(COLUMN_INDEX_MASP);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã sản phẩm");

            cell = row.createCell(COLUMN_INDEX_SOLUONG);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Số lượng");

            cell = row.createCell(COLUMN_INDEX_DONGIAGOC);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Đơn giá gốc");

            cell = row.createCell(COLUMN_INDEX_THANHTIEN);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Thành tiền");
        }

        // Write data
        private void writeChiTietPhieuNhapHang(ChiTietPhieuNhapHang ctpnh, Row row) {
            if (cellStyleFormatNumber == null) {
                // Format number
                short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
                // DataFormat df = workbook.createDataFormat();
                // short format = df.getFormat("#,##0");

                // Create CellStyle
                Workbook workbook = row.getSheet().getWorkbook();
                cellStyleFormatNumber = workbook.createCellStyle();
                cellStyleFormatNumber.setDataFormat(format);
            }

            Cell cell = row.createCell(COLUMN_INDEX_MAPHIEU);
            cell.setCellValue(ctpnh.getMaPhieu());

            cell = row.createCell(COLUMN_INDEX_MASP);
            cell.setCellValue(ctpnh.getMaSP());

            cell = row.createCell(COLUMN_INDEX_SOLUONG);
            cell.setCellValue(ctpnh.getSoLuong());
            cell.setCellStyle(cellStyleFormatNumber);

            cell = row.createCell(COLUMN_INDEX_DONGIAGOC);
            cell.setCellValue(ctpnh.getDonGiaGoc());

            cell = row.createCell(COLUMN_INDEX_THANHTIEN);
            cell.setCellValue(ctpnh.getThanhTien());
        }

        // Create CellStyle for header
        private CellStyle createStyleForHeader(Sheet sheet) {
            // Create font
            org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();

            font.setFontName("Times New Roman");
            font.setBold(true);
            font.setFontHeightInPoints((short) 14); // font size
            font.setColor(IndexedColors.WHITE.getIndex()); // text color

            // Create CellStyle
            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            return cellStyle;
        }

        // Write footer
        private void writeFooter(Sheet sheet, int rowIndex) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            int maxRow = CTPNHArr.size() + 1;
            Cell cell = row.createCell(COLUMN_INDEX_SOLUONG, CellType.FORMULA);
            cell.setCellFormula("SUM(C2:C" + maxRow + ")");
            cell = row.createCell(COLUMN_INDEX_DONGIAGOC, CellType.FORMULA);
            cell.setCellFormula("SUM(D2:D" + maxRow + ")");
            cell = row.createCell(COLUMN_INDEX_THANHTIEN, CellType.FORMULA);
            cell.setCellFormula("SUM(E2:E" + maxRow + ")");
        }

        // Auto resize column width
        private void autosizeColumn(Sheet sheet, int lastColumn) {
            for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
                if (columnIndex != lastColumn - 1) {
                    sheet.autoSizeColumn(columnIndex);
                }
            }
            sheet.setColumnWidth(lastColumn - 1, 5000);
        }

        // Create output file
        private void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
            try (OutputStream os = new FileOutputStream(excelFilePath)) {
                workbook.write(os);
            }
        }

    }

    public class ReadExcel {
        public final int COLUMN_INDEX_MAPHIEU = 0;
        public final int COLUMN_INDEX_MASP = 1;
        public final int COLUMN_INDEX_SOLUONG = 2;
        public final int COLUMN_INDEX_DONGIAGOC = 3;
        public final int COLUMN_INDEX_THANHTIEN = 4;
        public final String excelFilePath = DungChung.pdfPath + "phieunhaphang.xlsx";

        public ArrayList<ChiTietPhieuNhapHang> readExcel() throws IOException {
            ArrayList<ChiTietPhieuNhapHang> listChiTietPhieuNhapHangs = new ArrayList<>();

            // Get file
            InputStream inputStream = new FileInputStream(excelFilePath);

            // Get workbook
            Workbook workbook = getWorkbook(inputStream, excelFilePath);

            // Get sheet
            Sheet sheet = workbook.getSheetAt(1);

            // Get all rows
            Iterator<Row> iterator = sheet.iterator();
            String insertString = "";
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Read cells and set value for ctpnh object
                ChiTietPhieuNhapHang ctpnh = new ChiTietPhieuNhapHang();
                while (cellIterator.hasNext()) {
                    // Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for ctpnh object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_MAPHIEU:
                            ctpnh.setMaPhieu(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MASP:
                            ctpnh.setMaSP(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_SOLUONG:
                            ctpnh.setSoLuong(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_DONGIAGOC:
                            ctpnh.setDonGiaGoc(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_THANHTIEN:
                            ctpnh.setThanhTien(new BigDecimal((double) cellValue).intValue());
                            break;
                        default:
                            break;
                    }

                }
                listChiTietPhieuNhapHangs.add(ctpnh);

                if (ctpnh.getMaSP() != 0) {
                    insertString += "(\"" + ctpnh.getMaSP() + "\",\"" + ctpnh.getMaPhieu() + "\",\""
                            + ctpnh.getSoLuong() + "\",\"" + ctpnh.getDonGiaGoc() + "\",\"" + ctpnh.getThanhTien()
                            + "\"),";

                    inputStream.close();
                }
            }

            insertString = insertString.substring(0, insertString.length() - 1) + ";";

            workbook.close();
            inputStream.close();

            if (DSCTPNH.excelToDB(insertString)) {
                return listChiTietPhieuNhapHangs;
            }
            return new ArrayList<ChiTietPhieuNhapHang>();
        }

        // Get Workbook
        private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
            Workbook workbook = null;
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("Không phải là file excel");
            }

            return workbook;
        }

        // Get cell value
        private Object getCellValue(Cell cell) {
            CellType cellType = cell.getCellType();
            Object cellValue = null;
            switch (cellType) {
                case BOOLEAN:
                    cellValue = cell.getBooleanCellValue();
                    break;
                case FORMULA:
                    Workbook workbook = cell.getSheet().getWorkbook();
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    cellValue = evaluator.evaluate(cell).getNumberValue();
                    break;
                case NUMERIC:
                    cellValue = cell.getNumericCellValue();
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case _NONE:
                case BLANK:
                case ERROR:
                    break;
                default:
                    break;
            }

            return cellValue;
        }
    }

    // public void pdf() {
    // if (JOptionPane.showConfirmDialog(null, "Ghi dữ liệu vào PDF?", "Xác nhận",
    // JOptionPane.YES_NO_OPTION) == 0) {
    // ExportPDF ex = new ExportPDF();
    // ex.export();
    // }
    // }

    public class ExportPDF {
        private File fontFile;
        private BaseFont bf;

        public void export(Document document) {
            File dir = new File(DungChung.pdfPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                fontFile = new File(DungChung.pdfPath + "fonts/vuArial.ttf");
                bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font titleFont = new Font(bf, 16, Font.BOLD);
                Paragraph title = new Paragraph("2. Chi tiết phiếu nhập hàng", titleFont);
                title.setSpacingBefore(20);
                document.add(title);

                PdfPTable table = new PdfPTable(5);
                table.setSpacingBefore(10);
                table.setWidthPercentage(100);
                addTableHeader(table);
                addRows(table);
                document.add(table);

            } catch (DocumentException | IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void addTableHeader(PdfPTable table) {
            String[] pdfTableHeader = { "Mã phiếu", "Mã SP", "Số lượng", "Đơn giá gốc", "Thành tiền" };
            Font tableHeaderFont = new Font(bf, 12, Font.BOLDITALIC);
            for (String header : pdfTableHeader) {
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setBorderWidth(2);
                cell.setPhrase(new Phrase(header, tableHeaderFont));
                table.addCell(cell);
            }
        }

        private void addRows(PdfPTable table) {
            Font font = new Font(bf, 12);
            for (ChiTietPhieuNhapHang ctpnh : CTPNHArr) {
                table.addCell(new PdfPCell(new Phrase(Integer.toString(ctpnh.getMaPhieu()), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(ctpnh.getMaSP()), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(ctpnh.getSoLuong()), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(ctpnh.getDonGiaGoc()), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(ctpnh.getThanhTien()), font)));
            }
            try {
                table.setWidths(new int[] { 1, 1, 1, 2, 2 });
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
