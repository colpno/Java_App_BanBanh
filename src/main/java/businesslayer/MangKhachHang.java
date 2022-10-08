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

import datalayer.tblKhachHang;

public class MangKhachHang {
    private tblKhachHang DSKH = new tblKhachHang();
    private ArrayList<KhachHang> KHArr = new ArrayList<KhachHang>();

    public MangKhachHang() {
        this.KHArr = DSKH.get();
    }

    public void setArr(ArrayList<KhachHang> arr) {
        KHArr = arr;
    }

    public String[] title() {
        String[] title = { "XÓA", "Mã KH", "Mã TK", "Họ", "Tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Thao tác" };

        return title;
    }

    public ArrayList<KhachHang> getAll() {
        return this.KHArr;
    }

    public double getTongSoTrang(int soLuong) {
        return KHArr.size() > 0 ? Math.ceil((double) KHArr.size() / soLuong) : 1.0;
    }

    public ArrayList<KhachHang> get(int soLuongRow, int trang) {
        ArrayList<KhachHang> newKHArr = new ArrayList<KhachHang>();
        int start = soLuongRow * trang - soLuongRow;
        int length = soLuongRow * trang;
        for (int i = start; i < length && i < KHArr.size(); i++) {
            newKHArr.add(KHArr.get(i));
        }
        return newKHArr;
    }

    public Boolean add(int maTK, String ho, String ten, String ngaySinh, String diaChi, String soDienThoai) {
        if (JOptionPane.showConfirmDialog(null, "Thông tin bạn ghi chính xác chưa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            int maKH = 1;
            if (KHArr.size() != 0) {
                maKH += KHArr.get(KHArr.size() - 1).getMaKH();
            }
            KhachHang newKH = new KhachHang(maKH, maTK, ho, ten, ngaySinh, diaChi, soDienThoai);
            int check = kiemDuyNhat(newKH);
            if (check == 1) {
                JOptionPane.showMessageDialog(null, "Không thể thêm khách hàng vì trùng số điện thoại.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (check == 2) {
                JOptionPane.showMessageDialog(null, "Không thể thêm khách hàng vì trùng họ, tên, địa chỉ.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (check == 0) {
                if (DSKH.add(newKH)) {
                    KHArr.add(newKH);
                    return true;
                }
            }
        }
        return false;
    }

    public void remove(ArrayList<Integer> maKH) {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            Integer[] ids = maKH.toArray(new Integer[0]);
            if (DSKH.remove(ids)) {
                for (int i = 0; i < KHArr.size(); i++) {
                    for (Integer id : ids) {
                        if (KHArr.get(i).getMaKH() == id) {
                            KHArr.remove(i);
                        }
                    }
                }
            }
        }
    }

    public void edit(int maKH, int maTK, String ho, String ten, String ngaySinh, String diaChi, String soDienThoai) {
        ngaySinh = DungChung.slashToDash(ngaySinh);
        KhachHang newKH = new KhachHang(maKH, maTK, ho, ten, ngaySinh, diaChi, soDienThoai);
        int check = kiemDuyNhat(newKH);
        if (check == 1) {
            JOptionPane.showMessageDialog(null, "Không thể thêm khách hàng vì trùng số điện thoại.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (check == 2) {
            JOptionPane.showMessageDialog(null, "Không thể thêm khách hàng vì trùng họ, tên, địa chỉ.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (check == 0) {
            DSKH.edit(newKH);
            for (KhachHang kh : KHArr) {
                if (kh.getMaKH() == maKH) {
                    kh.setMaTK(maTK);
                    kh.setHo(ho);
                    kh.setTen(ten);
                    kh.setNgaySinh(ngaySinh);
                    kh.setDiaChi(diaChi);
                    kh.setSdt(soDienThoai);
                }
            }
        }
    }

    public ArrayList<KhachHang> search(String col, String giaTriTim) {
        ArrayList<KhachHang> nvArr = new ArrayList<KhachHang>();
        for (KhachHang kh : KHArr) {
            switch (col) {
                case "Mã KH": {
                    if (kh.getMaKH() == Integer.parseInt(giaTriTim)) {
                        nvArr.add(kh);
                    }
                    break;
                }
                case "Mã TK": {
                    if (kh.getMaTK() == Integer.parseInt(giaTriTim)) {
                        nvArr.add(kh);
                    }
                    break;
                }
                case "Họ": {
                    if (kh.getHo().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        nvArr.add(kh);
                    }
                    break;
                }
                case "Tên": {
                    if (kh.getTen().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        nvArr.add(kh);
                    }
                    break;
                }
                case "Ngày sinh": {
                    if (kh.getNgaySinh().contains(giaTriTim)) {
                        nvArr.add(kh);
                    }
                    break;
                }
                case "Địa chỉ": {
                    if (kh.getDiaChi().contains(giaTriTim)) {
                        nvArr.add(kh);
                    }
                    break;
                }
                case "Số điện thoại": {
                    if (kh.getSdt().contains(giaTriTim)) {
                        nvArr.add(kh);
                    }
                    break;
                }
            }
        }
        return nvArr;
    }

    public ArrayList<KhachHang> filter(String col, String from, String to) {
        ArrayList<KhachHang> newArr = new ArrayList<KhachHang>();
        for (KhachHang kh : KHArr) {
            switch (col) {
                case "Mã KH": {
                    if (kh.getMaKH() >= Integer.parseInt(from) && kh.getMaKH() <= Integer.parseInt(to)) {
                        newArr.add(kh);
                    }
                    break;
                }
                case "Mã TK": {
                    if (kh.getMaTK() >= Integer.parseInt(from) && kh.getMaTK() <= Integer.parseInt(to)) {
                        newArr.add(kh);
                    }
                    break;
                }
                case "Ngày sinh": {
                    if (kh.getNgaySinh().compareTo(from) >= 0 && kh.getNgaySinh().compareTo(to) <= 0) {
                        newArr.add(kh);
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
                    case "Mã KH": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getMaKH() - s2.getMaKH());
                        break;
                    }
                    case "Mã TK": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getMaTK() - s2.getMaTK());
                        break;
                    }
                    case "Họ": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getHo().compareTo(s2.getHo()));
                        break;
                    }
                    case "Tên": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getTen().compareTo(s2.getTen()));
                        break;
                    }
                    case "Ngày sinh": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getNgaySinh().compareTo(s2.getNgaySinh()));
                        break;
                    }
                    case "Địa chỉ": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getDiaChi().compareTo(s2.getDiaChi()));
                        break;
                    }
                    case "Số điện thoại": {
                        Collections.sort(KHArr, (s1, s2) -> s1.getSdt().compareTo(s2.getSdt()));
                        break;
                    }
                }
                break;
            }
            case "Giảm dần": {
                switch (col) {
                    case "Mã KH": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getMaKH() - s1.getMaKH());
                        break;
                    }
                    case "Mã TK": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getMaTK() - s1.getMaTK());
                        break;
                    }
                    case "Họ": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getHo().compareTo(s1.getHo()));
                        break;
                    }
                    case "Tên": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getTen().compareTo(s1.getTen()));
                        break;
                    }
                    case "Ngày sinh": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getNgaySinh().compareTo(s1.getNgaySinh()));
                        break;
                    }
                    case "Địa chỉ": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getDiaChi().compareTo(s1.getDiaChi()));
                        break;
                    }
                    case "Số điện thoại": {
                        Collections.sort(KHArr, (s1, s2) -> s2.getSdt().compareTo(s1.getSdt()));
                        break;
                    }
                }
                break;
            }
        }
    }

    public int kiemDuyNhat(KhachHang kh) {
        for (KhachHang KHang : KHArr) {
            if (KHang.getSdt().equals(kh.getSdt())) {
                return 1;
            }
            if (KHang.getHo().equals(kh.getHo()) && KHang.getTen().equals(kh.getTen())
                    && KHang.getDiaChi().equals(kh.getDiaChi())) {
                return 2;
            }
        }
        return 0;
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

    public ArrayList<KhachHang> docFile() {
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
        public final int COLUMN_INDEX_MAKH = 0;
        public final int COLUMN_INDEX_MATK = 1;
        public final int COLUMN_INDEX_HO = 2;
        public final int COLUMN_INDEX_TEN = 3;
        public final int COLUMN_INDEX_NGAYSINH = 4;
        public final int COLUMN_INDEX_DIACHI = 5;
        public final int COLUMN_INDEX_SODIENTHOAI = 6;
        public final String excelFilePath = DungChung.excelPath + "khachhang.xlsx";
        private CellStyle cellStyleFormatNumber = null;

        public void writeExcel() throws IOException {
            File dir = new File(DungChung.excelPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Create Workbook
            Workbook workbook = getWorkbook(excelFilePath);

            // Create sheet
            Sheet sheet = workbook.createSheet("KhachHang"); // Create sheet with sheet name

            int rowIndex = 0;

            // Write header
            writeHeader(sheet, rowIndex);

            // Write data
            rowIndex++;
            for (KhachHang kh : KHArr) {
                // Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                writeKhachHang(kh, row);
                rowIndex++;
            }

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
            Cell cell = row.createCell(COLUMN_INDEX_MAKH);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã khách hàng");

            cell = row.createCell(COLUMN_INDEX_MATK);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã tài khoản");

            cell = row.createCell(COLUMN_INDEX_HO);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Họ");

            cell = row.createCell(COLUMN_INDEX_TEN);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tên");

            cell = row.createCell(COLUMN_INDEX_NGAYSINH);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(COLUMN_INDEX_DIACHI);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(COLUMN_INDEX_SODIENTHOAI);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Số điện thoại");
        }

        // Write data
        private void writeKhachHang(KhachHang kh, Row row) {
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

            Cell cell = row.createCell(COLUMN_INDEX_MAKH);
            cell.setCellValue(kh.getMaKH());

            cell = row.createCell(COLUMN_INDEX_MATK);
            cell.setCellValue(kh.getMaTK());

            cell = row.createCell(COLUMN_INDEX_HO);
            cell.setCellValue(kh.getHo());
            cell.setCellStyle(cellStyleFormatNumber);

            cell = row.createCell(COLUMN_INDEX_TEN);
            cell.setCellValue(kh.getTen());

            cell = row.createCell(COLUMN_INDEX_NGAYSINH);
            cell.setCellValue(kh.getNgaySinh());

            cell = row.createCell(COLUMN_INDEX_DIACHI);
            cell.setCellValue(kh.getDiaChi());

            cell = row.createCell(COLUMN_INDEX_SODIENTHOAI);
            cell.setCellValue(kh.getSdt());
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

        // Auto resize column width
        private void autosizeColumn(Sheet sheet, int lastColumn) {
            for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
                sheet.autoSizeColumn(columnIndex);
            }
        }

        // Create output file
        private void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
            try (OutputStream os = new FileOutputStream(excelFilePath)) {
                workbook.write(os);
            }
        }

    }

    public class ReadExcel {
        public final int COLUMN_INDEX_MAKH = 0;
        public final int COLUMN_INDEX_MATK = 1;
        public final int COLUMN_INDEX_HO = 2;
        public final int COLUMN_INDEX_TEN = 3;
        public final int COLUMN_INDEX_NGAYSINH = 4;
        public final int COLUMN_INDEX_DIACHI = 5;
        public final int COLUMN_INDEX_SODIENTHOAI = 6;
        public final String excelFilePath = DungChung.pdfPath + "khachhang.xlsx";

        public ArrayList<KhachHang> readExcel() throws IOException {
            ArrayList<KhachHang> listKhachHangs = new ArrayList<>();

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

                // Read cells and set value for kh object
                KhachHang kh = new KhachHang();
                while (cellIterator.hasNext()) {
                    // Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for kh object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_MAKH:
                            kh.setMaKH(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MATK:
                            kh.setMaTK(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_HO:
                            kh.setHo((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_TEN:
                            kh.setTen((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_NGAYSINH:
                            kh.setNgaySinh((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIACHI:
                            kh.setDiaChi((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_SODIENTHOAI:
                            kh.setSdt((String) getCellValue(cell));
                            break;
                        default:
                            break;
                    }

                }
                listKhachHangs.add(kh);

                if (kh.getNgaySinh() != null) {
                    insertString += "(\"" + kh.getMaTK() + "\",\"" + kh.getHo() + "\",\"" + kh.getTen() + "\",\""
                            + kh.getDiaChi() + "\",\"" + kh.getNgaySinh() + "\",\"" + kh.getSdt() + "\"),";

                    inputStream.close();
                }
            }

            insertString = insertString.substring(0, insertString.length() - 1) + ";";

            workbook.close();
            inputStream.close();

            if (DSKH.excelToDB(insertString)) {
                return listKhachHangs;
            }
            return new ArrayList<KhachHang>();
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

    private class ExportPDF {
        private File fontFile;
        private BaseFont bf;

        protected void export() {
            File dir = new File(DungChung.pdfPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            Document document = new Document();

            try {
                fontFile = new File(DungChung.pdfPath + "fonts/vuArial.ttf");
                bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                PdfWriter.getInstance(document, new FileOutputStream(DungChung.pdfPath + "KhachHang.pdf"));
                document.open();

                Font titleFont = new Font(bf, 24, Font.BOLD);
                Paragraph title = new Paragraph("Thông tin khách hàng", titleFont);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(title);

                PdfPTable table = new PdfPTable(6);
                table.setSpacingBefore(10);
                table.setWidthPercentage(100);
                addTableHeader(table);
                addRows(table);
                document.add(table);

                document.close();

                JOptionPane.showMessageDialog(null, "Export PDF thành công", "Error", JOptionPane.INFORMATION_MESSAGE);
            } catch (DocumentException | IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void addTableHeader(PdfPTable table) {
            String[] pdfTableHeader = { "Mã KH", "Họ", "Tên", "Ngày sinh", "Địa chỉ", "Số điện thoại" };
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
            for (KhachHang kh : KHArr) {
                table.addCell(new PdfPCell(new Phrase(Integer.toString(kh.getMaKH()), font)));
                table.addCell(new PdfPCell(new Phrase(kh.getHo(), font)));
                table.addCell(new PdfPCell(new Phrase(kh.getTen(), font)));
                table.addCell(new PdfPCell(new Phrase(kh.getNgaySinh(), font)));
                table.addCell(new PdfPCell(new Phrase(kh.getDiaChi(), font)));
                table.addCell(new PdfPCell(new Phrase(kh.getSdt(), font)));
            }
            try {
                table.setWidths(new int[] { 1, 3, 2, 3, 5, 3 });
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
