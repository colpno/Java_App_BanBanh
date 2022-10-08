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
import com.itextpdf.text.Element;
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

import datalayer.tblNhanVien;

public class MangNhanVien {
    private tblNhanVien DSNV = new tblNhanVien();
    private ArrayList<NhanVien> NVArr = new ArrayList<NhanVien>();
    private String[] fullTitle = { "XÓA", "Mã NV", "Mã TK", "Họ", "Tên", "Ngày sinh", "Địa chỉ", "Số điện thoại",
            "Lương", "Thao tác" };

    public MangNhanVien() {
        this.NVArr = DSNV.get();
    }

    public String[] title() {
        return fullTitle;
    }

    public void setArr(ArrayList<NhanVien> arr) {
        NVArr = arr;
    }

    public ArrayList<NhanVien> get(int soLuongRow, int trang) {
        ArrayList<NhanVien> newNVArr = new ArrayList<NhanVien>();
        int start = soLuongRow * trang - soLuongRow;
        int length = soLuongRow * trang;
        for (int i = start; i < length && i < NVArr.size(); i++) {
            newNVArr.add(NVArr.get(i));
        }
        return newNVArr;
    }

    public ArrayList<NhanVien> getAll() {
        return this.NVArr;
    }

    public double getTongSoTrang(int soLuong) {
        return NVArr.size() > 0 ? Math.ceil((double) NVArr.size() / soLuong) : 1.0;
    }

    public Boolean add(int maTK, String ho, String ten, String ngaySinh, String diaChi, String soDienThoai, int luong) {
        if (JOptionPane.showConfirmDialog(null, "Thông tin bạn ghi chính xác chưa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            int maNV = 1;
            if (NVArr.size() != 0) {
                maNV += NVArr.get(NVArr.size() - 1).getMaNV();
            }
            NhanVien newNV = new NhanVien(maNV, maTK, ho, ten, ngaySinh, diaChi, soDienThoai, luong);
            int check = kiemDuyNhat(newNV);
            System.out.println(check);
            if (check == 1) {
                JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên vì trùng số điện thoại.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (check == 2) {
                JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên vì trùng họ, tên, địa chỉ.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (check == 0) {
                if (DSNV.add(newNV)) {
                    NVArr.add(newNV);
                    return true;
                }
            }
        }
        return false;
    }

    public void remove(ArrayList<Integer> maNV) {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION) == 0) {

            Integer[] ids = maNV.toArray(new Integer[0]);
            if (DSNV.remove(ids)) {
                for (int i = 0; i < NVArr.size(); i++) {
                    for (Integer id : ids) {
                        if (NVArr.get(i).getMaNV() == id) {
                            NVArr.remove(i);
                        }
                    }
                }
            }
        }
    }

    public void edit(int maNV, int maTK, String ho, String ten, String ngaySinh, String diaChi, String soDienThoai,
            int luong) {

        ngaySinh = DungChung.slashToDash(ngaySinh);
        NhanVien newNV = new NhanVien(maNV, maTK, ho, ten, ngaySinh, diaChi, soDienThoai, luong);
        int check = kiemDuyNhat(newNV);
        if (check == 1) {
            JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên vì trùng số điện thoại.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (check == 2) {
            JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên vì trùng họ, tên, địa chỉ.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (check == 0) {
            if (DSNV.edit(newNV)) {
                for (NhanVien nv : NVArr) {
                    if (nv.getMaNV() == maNV) {
                        nv.setMaTK(maTK);
                        nv.setHo(ho);
                        nv.setTen(ten);
                        nv.setNgaySinh(ngaySinh);
                        nv.setDiaChi(diaChi);
                        nv.setSdt(soDienThoai);
                        nv.setLuong(luong);
                    }
                }
            }
        }
    }

    public ArrayList<NhanVien> search(String col, String giaTriTim) {
        ArrayList<NhanVien> nvArr = new ArrayList<NhanVien>();
        for (NhanVien nv : NVArr) {
            switch (col) {
                case "Mã NV": {
                    if (nv.getMaNV() == Integer.parseInt(giaTriTim)) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Mã TK": {
                    if (nv.getMaTK() == Integer.parseInt(giaTriTim)) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Họ": {
                    if (nv.getHo().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Tên": {
                    if (nv.getTen().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Ngày sinh": {
                    if (nv.getNgaySinh().contains(giaTriTim)) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Địa chỉ": {
                    if (nv.getDiaChi().contains(giaTriTim)) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Số điện thoại": {
                    if (nv.getSdt().contains(giaTriTim)) {
                        nvArr.add(nv);
                    }
                    break;
                }
                case "Lương": {
                    if (nv.getLuong() == Integer.parseInt(giaTriTim)) {
                        nvArr.add(nv);
                    }
                    break;
                }
            }
        }
        return nvArr;

    }

    public ArrayList<NhanVien> filter(String col, String from, String to) {
        ArrayList<NhanVien> newArr = new ArrayList<NhanVien>();
        for (NhanVien nv : NVArr) {
            switch (col) {
                case "maNV": {
                    if (nv.getMaNV() >= Integer.parseInt(from) && nv.getMaNV() <= Integer.parseInt(to)) {
                        newArr.add(nv);
                    }
                    break;
                }
                case "maTK": {
                    if (nv.getMaTK() >= Integer.parseInt(from) && nv.getMaTK() <= Integer.parseInt(to)) {
                        newArr.add(nv);
                    }
                    break;
                }
                case "luong": {
                    if (nv.getLuong() >= Integer.parseInt(from) && nv.getLuong() <= Integer.parseInt(to)) {
                        newArr.add(nv);
                    }
                    break;
                }
                case "ngaySinh": {
                    if (nv.getNgaySinh().compareTo(from) >= 0 && nv.getNgaySinh().compareTo(to) <= 0) {
                        newArr.add(nv);
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
                    case "Mã NV": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getMaNV() - s2.getMaNV());
                        break;
                    }
                    case "Mã TK": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getMaTK() - s2.getMaTK());
                        break;
                    }
                    case "Họ": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getHo().compareTo(s2.getHo()));
                        break;
                    }
                    case "Tên": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getTen().compareTo(s2.getTen()));
                        break;
                    }
                    case "Ngày sinh": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getNgaySinh().compareTo(s2.getNgaySinh()));
                        break;
                    }
                    case "Địa chỉ": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getDiaChi().compareTo(s2.getDiaChi()));
                        break;
                    }
                    case "Số điện thoại": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getSdt().compareTo(s2.getSdt()));
                        break;
                    }
                    case "Lương": {
                        Collections.sort(NVArr, (s1, s2) -> s1.getLuong() - s2.getLuong());
                        break;
                    }
                }
                break;
            }
            case "Giảm dần": {
                switch (col) {
                    case "Mã NV": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getMaNV() - s1.getMaNV());
                        break;
                    }
                    case "Mã TK": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getMaTK() - s1.getMaTK());
                        break;
                    }
                    case "Họ": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getHo().compareTo(s1.getHo()));
                        break;
                    }
                    case "Tên": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getTen().compareTo(s1.getTen()));
                        break;
                    }
                    case "Ngày sinh": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getNgaySinh().compareTo(s1.getNgaySinh()));
                        break;
                    }
                    case "Địa chỉ": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getDiaChi().compareTo(s1.getDiaChi()));
                        break;
                    }
                    case "Số điện thoại": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getSdt().compareTo(s1.getSdt()));
                        break;
                    }
                    case "Lương": {
                        Collections.sort(NVArr, (s1, s2) -> s2.getLuong() - s2.getLuong());
                        break;
                    }
                }
                break;
            }
        }
    }

    public int kiemDuyNhat(NhanVien nv) {
        for (NhanVien NVien : NVArr) {
            System.out.println(NVien.getSdt() + "   " + nv.getSdt());
            if (NVien.getSdt().equals(nv.getSdt())) {
                return 1;
            }
            if (NVien.getHo().equals(nv.getHo()) && NVien.getTen().equals(nv.getTen())
                    && NVien.getDiaChi().equals(nv.getDiaChi())) {
                return 2;
            }
        }
        return 0;
    }

    public void ghiVaoFile() {
        if (JOptionPane.showConfirmDialog(null, "Ghi dữ liệu vào excel?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            WriteExcel ghiFile = new WriteExcel();
            try {
                ghiFile.writeExcel();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public ArrayList<NhanVien> docFile() {
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
        public final int COLUMN_INDEX_MANV = 0;
        public final int COLUMN_INDEX_MATK = 1;
        public final int COLUMN_INDEX_HO = 2;
        public final int COLUMN_INDEX_TEN = 3;
        public final int COLUMN_INDEX_NGAYSINH = 4;
        public final int COLUMN_INDEX_DIACHI = 5;
        public final int COLUMN_INDEX_SODIENTHOAI = 6;
        public final int COLUMN_INDEX_LUONG = 7;
        public final String excelFilePath = DungChung.excelPath + "nhanvien.xlsx";
        private CellStyle cellStyleFormatNumber = null;

        public void writeExcel() throws IOException {
            File dir = new File(DungChung.excelPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Create Workbook
            Workbook workbook = getWorkbook(excelFilePath);

            // Create sheet
            Sheet sheet = workbook.createSheet("NhanVien"); // Create sheet with sheet name

            int rowIndex = 0;

            // Write header
            writeHeader(sheet, rowIndex);

            // Write data
            rowIndex++;
            for (NhanVien nv : NVArr) {
                // Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                writeNhanVien(nv, row);
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
            Cell cell = row.createCell(COLUMN_INDEX_MANV);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Mã nhân viên");

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

            cell = row.createCell(COLUMN_INDEX_LUONG);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Lương");
        }

        // Write data
        private void writeNhanVien(NhanVien nv, Row row) {
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

            Cell cell = row.createCell(COLUMN_INDEX_MANV);
            cell.setCellValue(nv.getMaNV());

            cell = row.createCell(COLUMN_INDEX_MATK);
            cell.setCellValue(nv.getMaTK());

            cell = row.createCell(COLUMN_INDEX_HO);
            cell.setCellValue(nv.getHo());
            cell.setCellStyle(cellStyleFormatNumber);

            cell = row.createCell(COLUMN_INDEX_TEN);
            cell.setCellValue(nv.getTen());

            cell = row.createCell(COLUMN_INDEX_NGAYSINH);
            cell.setCellValue(nv.getNgaySinh());

            cell = row.createCell(COLUMN_INDEX_DIACHI);
            cell.setCellValue(nv.getDiaChi());

            cell = row.createCell(COLUMN_INDEX_SODIENTHOAI);
            cell.setCellValue(nv.getSdt());

            cell = row.createCell(COLUMN_INDEX_LUONG);
            cell.setCellValue(nv.getLuong());
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
            Cell cell = row.createCell(COLUMN_INDEX_LUONG, CellType.FORMULA);
            cell.setCellFormula("SUM(H2:H" + (NVArr.size() + 1) + ")");
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
        public final int COLUMN_INDEX_MANV = 0;
        public final int COLUMN_INDEX_MATK = 1;
        public final int COLUMN_INDEX_HO = 2;
        public final int COLUMN_INDEX_TEN = 3;
        public final int COLUMN_INDEX_NGAYSINH = 4;
        public final int COLUMN_INDEX_DIACHI = 5;
        public final int COLUMN_INDEX_SODIENTHOAI = 6;
        public final int COLUMN_INDEX_LUONG = 7;
        public final String excelFilePath = DungChung.excelPath + "nhanvien.xlsx";

        public ArrayList<NhanVien> readExcel() throws IOException {
            ArrayList<NhanVien> listNhanViens = new ArrayList<>();

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

                // Read cells and set value for nv object
                NhanVien nv = new NhanVien();
                while (cellIterator.hasNext()) {
                    // Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for nv object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_MANV:
                            nv.setMaNV(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MATK:
                            nv.setMaTK(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_HO:
                            nv.setHo((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_TEN:
                            nv.setTen((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_NGAYSINH:
                            nv.setNgaySinh((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIACHI:
                            nv.setDiaChi((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_SODIENTHOAI:
                            nv.setSdt((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_LUONG:
                            nv.setLuong(new BigDecimal((double) cellValue).intValue());
                            break;
                        default:
                            break;
                    }

                }
                listNhanViens.add(nv);

                if (nv.getNgaySinh() != null) {
                    insertString += "(\"" + nv.getMaNV() + "\",\"" + nv.getMaTK() + "\",\"" + nv.getHo() + "\",\""
                            + nv.getTen() + "\",\"" + nv.getDiaChi() + "\",\"" + nv.getNgaySinh() + "\",\""
                            + nv.getSdt() + "\",\"" + nv.getLuong() + "\"),";

                    inputStream.close();
                }
            }

            insertString = insertString.substring(0, insertString.length() - 1) + ";";

            workbook.close();
            inputStream.close();

            if (DSNV.excelToDB(insertString)) {
                return listNhanViens;
            }
            return new ArrayList<NhanVien>();
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
            File dir = new File(DungChung.pdfPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            Document document = new Document();

            try {
                fontFile = new File(DungChung.pdfPath + "fonts/vuArial.ttf");
                bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                PdfWriter.getInstance(document, new FileOutputStream(DungChung.pdfPath + "NhanVien.pdf"));
                document.open();

                Font titleFont = new Font(bf, 24, Font.BOLD);
                Paragraph title = new Paragraph("Thông tin nhân viên", titleFont);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(title);

                PdfPTable table = new PdfPTable(7);
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
            String[] pdfTableHeader = { "Mã NV", "Họ", "Tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Lương" };
            Font tableHeaderFont = new Font(bf, 12, Font.BOLDITALIC);
            for (String header : pdfTableHeader) {
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setBorderWidth(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPhrase(new Phrase(header, tableHeaderFont));
                table.addCell(cell);
            }
        }

        private void addRows(PdfPTable table) {
            Font font = new Font(bf, 12);
            for (NhanVien nv : NVArr) {
                table.addCell(new PdfPCell(new Phrase(Integer.toString(nv.getMaNV()), font)));
                table.addCell(new PdfPCell(new Phrase(nv.getHo(), font)));
                table.addCell(new PdfPCell(new Phrase(nv.getTen(), font)));
                table.addCell(new PdfPCell(new Phrase(nv.getNgaySinh(), font)));
                table.addCell(new PdfPCell(new Phrase(nv.getDiaChi(), font)));
                table.addCell(new PdfPCell(new Phrase(nv.getSdt(), font)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(nv.getLuong()), font)));
            }
            try {
                table.setWidths(new int[] { 1, 3, 2, 3, 5, 2, 2 });
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
