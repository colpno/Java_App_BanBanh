package test;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class InputTester {
    public static Boolean chiChu(String str, String label, int lessThanOrEqual) {
        String pattern = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]+";
        String notMatchMessage = "chỉ gồm chữ";
        if (check(pattern, str, label, notMatchMessage, lessThanOrEqual)) {
            return false;
        }
        return true;
    }

    public static Boolean chiChuVaKhoangTrang(String str, String label, int lessThanOrEqual) {
        String pattern = "([aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+)|"
                +"([aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]+)";
        String notMatchMessage = "chỉ bao gồm chữ và khoảng trắng";
        if (!check(pattern, str, label, notMatchMessage, lessThanOrEqual)) {
            return false;
        }
        if (!kiemKhoangTrang(str, label)) {
            return false;
        }
        return true;
    }

    public static Boolean chiSo(String str, String label, int lessThanOrEqual) {
        String pattern = "[0-9]+";
        String notMatchMessage = "chỉ bao gồm số";
        if (!check(pattern, str, label, notMatchMessage, lessThanOrEqual)) {
            return false;
        }
        return true;
    }

    public static Boolean chiSoVaKhoangTrang(String str, String label, int lessThanOrEqual) {
        String pattern = "[0-9 ]+|[0-9]+";
        String notMatchMessage = "chỉ bao gồm chữ và khoảng trắng";
        if (!check(pattern, str, label, notMatchMessage, lessThanOrEqual)) {
            return false;
        }
        if (!kiemKhoangTrang(str, label)) {
            return false;
        }
        return true;
    }

    public static Boolean chiSoVaChu(String str, String label, int lessThanOrEqual) {
        String pattern = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]|[0-9]+";
        String notMatchMessage = "chỉ bao gồm số và chữ";
        if (!check(pattern, str, label, notMatchMessage, lessThanOrEqual)) {
            return false;
        }
        return true;
    }

    public static Boolean chiSoChuKhoangTrang(String str, String label, int lessThanOrEqual) {
        String pattern = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 ]+|"
                + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9]+";
        String notMatchMessage = "chỉ bao gồm chữ, số và khoảng trắng";
        if (!check(pattern, str, label, notMatchMessage, lessThanOrEqual)) {
            return false;
        }
        if (!kiemKhoangTrang(str, label)) {
            return false;
        }
        return true;
    }

    public static Boolean soDienThoaiHopLe(String str, String label, int lessThanOrEqual) {
        String firstPattern = "^0[3789][0-9]{8}";
        String firstNotMatchMessage = "gồm 10 số";
        if (!check(firstPattern, str, label, firstNotMatchMessage, lessThanOrEqual)) {
            return false;
        }
        String sencondPattern = "^0[3789][1-9]{8}";
        String sencondNotMatchMessage = "các số sau 2 ký tự đầu phải khác 0";
        if (!check(sencondPattern, str, label, sencondNotMatchMessage, lessThanOrEqual)) {
            return false;
        }
        return true;
    }

    public static Boolean kiemNgay(String str, String label) {
        String pattern = "[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}";
        if (chuoiNull(str, "")) {
            showDialog("Vui lòng nhập vào " + label, "Lỗi", 0);
            return false;
        }
        if (!str.matches(pattern)) {
            showDialog("Định dạng ngày phải là: dd/mm/yyyy", "Lỗi", 0);
            return false;
        }

        String[] date = str.split("/");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        int defaultDay = 0;

        if (month < 1 || month > 12) {
            showDialog("Tháng phải trong khoảng 1 - 12", "Lỗi", 0);
            return false;
        }

        if (year < 1950 || year > currentYear) {
            showDialog("Năm phải trong khoảng 1950 -> " + currentYear, "Lỗi", 0);
            return false;
        }

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                defaultDay = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                defaultDay = 30;
                break;
            case 2: {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    defaultDay = 28;
                } else
                    defaultDay = 29;
                break;
            }
        }
        if (day < 1 || day > defaultDay) {
            showDialog("Ngày phải trong khoảng 1 -> " + defaultDay, "Lỗi", 0);
            return false;
        }
        return true;
    }

    public static Boolean laDiaChi(String str, String label, int lessThanOrEqual) {
        String pattern = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 /,.]+";
        if (check(pattern, str, label, "Địa chỉ không hợp lệ", lessThanOrEqual)) {
            return true;
        }
        return false;
    }

    public static Boolean chuoiNull(String str, String label) {
        if (str.equals("") && label.equals("")) {
            return true;
        }
        if (str.equals("") && !label.equals("")) {
            showDialog("Vui lòng nhập vào " + label, "Lỗi", 0);
            return true;
        }
        return false;
    }

    private static void showDialog(String notMatchMessage, String title, int notMatchMessageType) {
        JOptionPane.showMessageDialog(null, notMatchMessage, title, notMatchMessageType);
    }

    private static Boolean check(String pattern, String str, String label, String notMatchMessage,
            int lessThanOrEqual) {
        if (chuoiNull(str, "")) {
            showDialog("Vui lòng nhập vào " + label, "Lỗi", 0);
            return false;
        }
        if (!str.matches(pattern)) {
            showDialog(label + " " + notMatchMessage, "Lỗi", 0);
            return false;
        }
        if (lessThanOrEqual != 0 && str.length() > lessThanOrEqual) {
            showDialog(label + " phải nhỏ hơn hoặc bằng " + lessThanOrEqual + " ký tự", "Lỗi", 0);
            return false;
        }
        return true;
    }

    private static Boolean kiemKhoangTrang(String str, String label) {
        String pattern = "^ | {2,999999999}| $";
        Pattern pa = Pattern.compile(pattern);
        Matcher ma = pa.matcher(str);
        if (ma.find()) {
            showDialog(label + " có khoảng trắng thừa", "Lỗi", 0);
            return false;
        }
        return true;
    }
}
