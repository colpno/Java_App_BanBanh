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
import com.itextpdf.text.pdf.PdfWriter;

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

import datalayer.tblPhieuNhapHang;

public class MangPhieuNhapHang {
    private tblPhieuNhapHang DSPNH = new tblPhieuNhapHang();
    private ArrayList<PhieuNhapHang> PNHArr = new ArrayList<PhieuNhapHang>();

    public MangPhieuNhapHang() {
        this.PNHArr = DSPNH.get();
    }

    public void setArr(ArrayList<PhieuNhapHang> arr) {
        this.PNHArr = arr;
    }

    public String[] title() {
        String[] title = { "XÓA", "Mã phiếu", "Mã nhà cung cấp", "Mã nhân viên", "Ngày nhập", "Tổng tiền", "Thao tác" };

        return title;
    }

    public void updateTongTien(int maPhieu, ArrayList<ChiTietPhieuNhapHang> list) {
        int tongTien = 0;
        for (ChiTietPhieuNhapHang ctpnh : list) {
            if (ctpnh.getMaPhieu() == maPhieu) {
                tongTien += ctpnh.getThanhTien();
            }
        }

        ArrayList<PhieuNhapHang> newArr = this.getAll();
        for (PhieuNhapHang pnh : newArr) {
            if (pnh.getMaPhieu() == maPhieu) {
                pnh.setTongTien(tongTien);
            }
        }

        setArr(newArr);
        DSPNH.updateTongTien(maPhieu, tongTien);
    }

    public double getTongSoTrang(int soLuong) {
        return PNHArr.size() > 0 ? Math.ceil((double) PNHArr.size() / soLuong) : 1.0;
    }

    public ArrayList<PhieuNhapHang> get(int soLuongRow, int trang) {
        ArrayList<PhieuNhapHang> newPNHArr = new ArrayList<PhieuNhapHang>();
        ArrayList<PhieuNhapHang> NVarr = DSPNH.get();

        int start = soLuongRow * trang - soLuongRow;
        int length = soLuongRow * trang;

        for (int i = start; i < length && i < NVarr.size(); i++) {
            newPNHArr.add(NVarr.get(i));
        }
        return newPNHArr;
    }

    public ArrayList<PhieuNhapHang> getTuArrayList(int soLuongRow, int trang) {
        ArrayList<PhieuNhapHang> newPNHArr = new ArrayList<PhieuNhapHang>();

        int start = soLuongRow * trang - soLuongRow;
        int length = soLuongRow * trang;

        for (int i = start; i < length && i < PNHArr.size(); i++) {
            newPNHArr.add(PNHArr.get(i));
        }
        return newPNHArr;
    }

    public ArrayList<PhieuNhapHang> getAll() {
        return this.PNHArr;
    }

    public void add(int maNCC, int maNV, String ngayNhap) {
        if (JOptionPane.showConfirmDialog(null, "Thông tin bạn ghi chính xác chưa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            int maPhieu = 1;
            if (PNHArr.size() != 0) {
                maPhieu += PNHArr.get(PNHArr.size() - 1).getMaPhieu();
            }

            if (DSPNH.add(new PhieuNhapHang(maPhieu, maNCC, maNV, ngayNhap, 0))) {
                PNHArr.add(new PhieuNhapHang(maPhieu, maNCC, maNV, ngayNhap, 0));
            }
        }
    }

    public void remove(ArrayList<Integer> maPhieu) {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0
                && JOptionPane.showConfirmDialog(null,
                        "Hành động này sẽ xóa tất cả các chi tiết, bạn có chắc chắn muốn xóa", "Xác nhận",
                        JOptionPane.YES_NO_OPTION) == 0) {

            Integer[] ids = maPhieu.toArray(new Integer[0]);

            MangChiTietPhieuNhapHang ctpnhArr = new MangChiTietPhieuNhapHang();
            ctpnhArr.removeByMaPhieu(maPhieu);

            if (DSPNH.remove(ids)) {
                for (int i = 0; i < PNHArr.size(); i++) {
                    for (Integer id : ids) {
                        if (PNHArr.get(i).getMaPhieu() == id) {
                            PNHArr.remove(i);
                        }
                    }
                }
            }
        }

    }

    public void edit(int maPhieu, int maNCC, int maNV, String ngayNhap) {
        for (PhieuNhapHang pnh : PNHArr) {
            if (pnh.getMaPhieu() == maPhieu) {
                pnh.setMaNCC(maNCC);
                pnh.setMaNV(maNV);
                pnh.setNgayNhap(ngayNhap);
            }
        }

        ngayNhap = DungChung.slashToDash(ngayNhap);
        PhieuNhapHang newPNH = new PhieuNhapHang(maPhieu, maNCC, maNV, ngayNhap);
        DSPNH.edit(newPNH);
    }

    public ArrayList<PhieuNhapHang> search(String col, String giaTriTim) {
        ArrayList<PhieuNhapHang> pnhArr = new ArrayList<PhieuNhapHang>();
        for (PhieuNhapHang pnh : PNHArr) {
            switch (col) {
                case "Mã phiếu": {
                    if (pnh.getMaPhieu() == Integer.parseInt(giaTriTim)) {
                        pnhArr.add(pnh);
                    }
                    break;
                }
                case "Mã nhà cung cấp": {
                    if (pnh.getMaNCC() == Integer.parseInt(giaTriTim)) {
                        pnhArr.add(pnh);
                    }
                    break;
                }
                case "Mã nhân viên": {
                    if (pnh.getMaNV() == Integer.parseInt(giaTriTim)) {
                        pnhArr.add(pnh);
                    }
                    break;
                }
                case "Ngày nhập": {
                    if (pnh.getNgayNhap().contains(giaTriTim)) {
                        pnhArr.add(pnh);
                    }
                    break;
                }
                case "Tổng tiền": {
                    if (pnh.getTongTien() == Integer.parseInt(giaTriTim)) {
                        pnhArr.add(pnh);
                    }
                    break;
                }
            }
        }
        return pnhArr;
    }

    public ArrayList<PhieuNhapHang> filter(String col, String from, String to) {
        ArrayList<PhieuNhapHang> newArr = new ArrayList<PhieuNhapHang>();
        for (PhieuNhapHang pnh : PNHArr) {
            switch (col) {
                case "Mã phiếu": {
                    if (pnh.getMaPhieu() >= Integer.parseInt(from) && pnh.getMaPhieu() <= Integer.parseInt(to)) {
                        newArr.add(pnh);
                    }
                    break;
                }
                case "Mã nhà cung cấp": {
                    if (pnh.getMaNCC() >= Integer.parseInt(from) && pnh.getMaNCC() <= Integer.parseInt(to)) {
                        newArr.add(pnh);
                    }
                    break;
                }
                case "Mã nhân viên": {
                    if (pnh.getMaNV() >= Integer.parseInt(from) && pnh.getMaNV() <= Integer.parseInt(to)) {
                        newArr.add(pnh);
                    }
                    break;
                }
                case "Ngày nhập": {
                    if (pnh.getNgayNhap().compareTo(from) >= 0 && pnh.getNgayNhap().compareTo(to) <= 0) {
                        newArr.add(pnh);
                    }
                    break;
                }
                case "Tổng tiền": {
                    if (pnh.getTongTien() >= Integer.parseInt(from) && pnh.getTongTien() <= Integer.parseInt(to)) {
                        newArr.add(pnh);
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
                    case "Mã phiếu": {
                        Collections.sort(PNHArr, (s1, s2) -> s1.getMaPhieu() - s2.getMaPhieu());
                        break;
                    }
                    case "Mã nhà cung cấp": {
                        Collections.sort(PNHArr, (s1, s2) -> s1.getMaNCC() - s2.getMaNCC());
                        break;
                    }
                    case "Mã nhân viên": {
                        Collections.sort(PNHArr, (s1, s2) -> s1.getMaNV() - s2.getMaNV());
                        break;
                    }
                    case "Ngày nhập": {
                        Collections.sort(PNHArr, (s1, s2) -> s1.getNgayNhap().compareTo(s2.getNgayNhap()));
                        break;
                    }
                    case "Tổng tiền": {
                        Collections.sort(PNHArr, (s1, s2) -> s1.getTongTien() - s2.getTongTien());
                        break;
                    }
                }
                break;
            }
            case "Giảm dần": {
                switch (col) {
                    case "Mã phiếu": {
                        Collections.sort(PNHArr, (s1, s2) -> s2.getMaPhieu() - s1.getMaPhieu());
                        break;
                    }
                    case "Mã nhà cung cấp": {
                        Collections.sort(PNHArr, (s1, s2) -> s2.getMaNCC() - s1.getMaNCC());
                        break;
                    }
                    case "Mã nhân viên": {
                        Collections.sort(PNHArr, (s1, s2) -> s2.getMaNV() - s1.getMaNV());
                        break;
                    }
                    case "Ngày nhập": {
                        Collections.sort(PNHArr, (s1, s2) -> s2.getNgayNhap().compareTo(s1.getNgayNhap()));
                        break;
                    }
                    case "Tổng tiền": {
                        Collections.sort(PNHArr, (s1, s2) -> s2.getTongTien() - s1.getTongTien());
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

    public ArrayList<PhieuNhapHang> docFile() {
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
        public final int COLUMN_INDEX_MANCC = 1;
        public final int COLUMN_INDEX_MANV = 2;
        public final int COLUMN_INDEX_NGAYNHAP = 3;
        public final int COLUMN_INDEX_TONGTIEN = 4;
        public final String excelFilePath = DungChung.excelPath + "phieunhaphang.xlsx";
        private CellStyle cellStyleFormatNumber = null;

        public void writeExcel() throws IOException {
            File dir = new File(DungChung.excelPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Create Workbook
            Workbook workbook = getWorkbook(excelFilePath);

            // Create sheet
            Sheet sheet = workbook.createSheet("PhieuNhapHang"); // Create sheet with sheet name

            int rowIndex = 0;

            // Write header
            writeHeader(sheet, rowIndex);

            // Write data
            rowIndex++;
            for (PhieuNhapHang pnh : PNHArr) {
                // Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                writePhieuNhapHang(pnh, row);
                rowIndex++;
            }

            // Write footer
            writeFooter(sheet, rowIndex);

            // Auto resize column witdth
            int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
            autosizeColumn(sheet, numberOfColumn);

            // Create file excel
            createOutputFile(workbook, excelFilePath);
            JOptionPane.showMessageDialog(null, "Đã ghi vào excel", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        // Create workbook
        private Workbook getWorkbook(String excelFilePath) throws IOException {
            Workbook workbook = null;

            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook();
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook();
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

            cell = row.createCell(COLUMN_INDEX_MANCC);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã nhà cung cấp");

            cell = row.createCell(COLUMN_INDEX_MANV);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã nhân viên");

            cell = row.createCell(COLUMN_INDEX_NGAYNHAP);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Ngày nhập");

            cell = row.createCell(COLUMN_INDEX_TONGTIEN);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tổng tiền");

        }

        // Write data
        private void writePhieuNhapHang(PhieuNhapHang pnh, Row row) {
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
            cell.setCellValue(pnh.getMaPhieu());

            cell = row.createCell(COLUMN_INDEX_MANCC);
            cell.setCellValue(pnh.getMaNCC());

            cell = row.createCell(COLUMN_INDEX_MANV);
            cell.setCellValue(pnh.getMaNV());
            cell.setCellStyle(cellStyleFormatNumber);

            cell = row.createCell(COLUMN_INDEX_NGAYNHAP);
            cell.setCellValue(pnh.getNgayNhap());

            cell = row.createCell(COLUMN_INDEX_TONGTIEN);
            cell.setCellValue(pnh.getTongTien());
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
            Cell cell = row.createCell(COLUMN_INDEX_TONGTIEN, CellType.FORMULA);
            cell.setCellFormula("SUM(E2:E" + (PNHArr.size() + 1) + ")");
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
        public final int COLUMN_INDEX_MANCC = 1;
        public final int COLUMN_INDEX_MANV = 2;
        public final int COLUMN_INDEX_NGAYNHAP = 3;
        public final int COLUMN_INDEX_TONGTIEN = 4;
        public final String excelFilePath = DungChung.excelPath + "phieunhaphang.xlsx";

        public ArrayList<PhieuNhapHang> readExcel() throws IOException {
            ArrayList<PhieuNhapHang> listPhieuNhapHangs = new ArrayList<>();

            // Get file
            InputStream inputStream = new FileInputStream(excelFilePath);

            // Get workbook
            Workbook workbook = getWorkbook(inputStream, excelFilePath);

            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);

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

                // Read cells and set value for pnh object
                PhieuNhapHang pnh = new PhieuNhapHang();
                while (cellIterator.hasNext()) {
                    // Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for pnh object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_MAPHIEU:
                            pnh.setMaPhieu(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MANCC:
                            pnh.setMaNCC(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MANV:
                            pnh.setMaNV(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_NGAYNHAP:
                            pnh.setNgayNhap((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_TONGTIEN:
                            pnh.setTongTien(new BigDecimal((double) cellValue).intValue());
                            break;
                        default:
                            break;
                    }

                }
                listPhieuNhapHangs.add(pnh);

                if (pnh.getNgayNhap() != null) {
                    String ngayMoi = DungChung.slashToDash(pnh.getNgayNhap());
                    insertString += "(\"" + pnh.getMaNCC() + "\",\"" + pnh.getMaNV() + "\",\"" + ngayMoi + "\",\""
                            + pnh.getTongTien() + "\"),";
                }

                inputStream.close();
            }

            insertString = insertString.substring(0, insertString.length() - 1) + ";";

            workbook.close();
            inputStream.close();

            if (DSPNH.excelToDB(insertString)) {
                return listPhieuNhapHangs;
            }
            return new ArrayList<PhieuNhapHang>();
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

    public void pdf() {
        if (JOptionPane.showConfirmDialog(null, "Ghi dữ liệu vào PDF?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            ExportPDF ex = new ExportPDF();
            ex.export();
        }
    }

    public class ExportPDF {
        private File fontFile;
        private BaseFont bf;

        public void export() {
            File dir = new File("pdf/");
            if (!dir.exists()) {
                dir.mkdir();
            }

            Document document = new Document();

            try {
                fontFile = new File(DungChung.pdfPath + "fonts/vuArial.ttf");
                bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                PdfWriter.getInstance(document, new FileOutputStream(DungChung.pdfPath + "PhieuNhapHang.pdf"));
                document.open();

                Font chapterFont = new Font(bf, 24, Font.BOLD);
                Paragraph chapter = new Paragraph("Nhập hàng", chapterFont);
                chapter.setAlignment(Paragraph.ALIGN_CENTER);
                chapter.setSpacingBefore(20);
                document.add(chapter);

                Font titleFont = new Font(bf, 16, Font.BOLD);
                Paragraph title = new Paragraph("1. Phiếu nhập hàng", titleFont);
                document.add(title);

                PdfPTable table = new PdfPTable(5);
                table.setSpacingBefore(10);
                table.setWidthPercentage(100);
                addTableHeader(table);
                addRows(table);
                document.add(table);

                MangChiTietPhieuNhapHang.ExportPDF ctpnhPDF = new MangChiTietPhieuNhapHang().new ExportPDF();
                ctpnhPDF.export(document);

                document.close();

                JOptionPane.showMessageDialog(null, "Export PDF thành công", "Error", JOptionPane.INFORMATION_MESSAGE);
            } catch (DocumentException | IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void addTableHeader(PdfPTable table) {
            String[] pdfTableHeader = { "Mã phiếu", "Mã NCC", "Mã NV", "Ngày nhập", "Tổng tiền" };
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
            for (PhieuNhapHang pnh : PNHArr) {
                table.addCell(new PdfPCell(new Phrase(Integer.toString(pnh.getMaPhieu()), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(pnh.getMaNCC()), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(pnh.getMaNV()), font)));
                table.addCell(new PdfPCell(new Phrase(pnh.getNgayNhap(), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(pnh.getTongTien()), font)));
            }
            try {
                table.setWidths(new int[] { 1, 1, 1, 2, 2 });
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
