package application;

import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import businesslayer.MangTaiKhoan;
import test.InputTester;

import javax.swing.JButton;

public class DangKyPanel extends JPanel {

    public DangKyPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jlbTenTK = new JLabel();
        jlbMatKhau = new JLabel();
        jlbHo = new JLabel();
        jlbTen = new JLabel();
        jlbSoDienThoai = new JLabel();
        jlbDiaChi = new JLabel();
        jlbNgaySinh = new JLabel();
        jtfTenTK = new JTextField();
        jtfMatKhau = new JTextField();
        jtfHo = new JTextField();
        jtfTen = new JTextField();
        jtfSoDienThoai = new JTextField();
        jtfDiaChi = new JTextField();
        jbtClickDangKy = new JButton();
        jdcNgaySinh = new JDateChooser();

        jdcNgaySinh.setDateFormatString("dd/MM/yyyy");

        setBackground(new java.awt.Color(255, 51, 153));
        setLayout(new GridBagLayout());

        jlbTenTK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbTenTK.setForeground(Color.WHITE); // NOI18N
        jlbTenTK.setText("Tên đăng nhập");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbTenTK, gridBagConstraints);

        jlbMatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbMatKhau.setForeground(Color.WHITE); // NOI18N
        jlbMatKhau.setText("Mật khẩu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbMatKhau, gridBagConstraints);

        jlbHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbHo.setForeground(Color.WHITE); // NOI18N
        jlbHo.setText("Họ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbHo, gridBagConstraints);

        jlbTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbTen.setForeground(Color.WHITE); // NOI18N
        jlbTen.setText("Tên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbTen, gridBagConstraints);

        jlbSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbSoDienThoai.setForeground(Color.WHITE); // NOI18N
        jlbSoDienThoai.setText("Số điện thoại");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbSoDienThoai, gridBagConstraints);

        jlbDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbDiaChi.setForeground(Color.WHITE); // NOI18N
        jlbDiaChi.setText("Địa chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbDiaChi, gridBagConstraints);

        jlbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbNgaySinh.setForeground(Color.WHITE); // NOI18N
        jlbNgaySinh.setText("Ngày sinh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jlbNgaySinh, gridBagConstraints);

        jtfTenTK.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jtfTenTK, gridBagConstraints);

        jtfMatKhau.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(jtfMatKhau, gridBagConstraints);

        jtfHo.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jtfHo, gridBagConstraints);

        jtfTen.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        add(jtfTen, gridBagConstraints);

        jtfSoDienThoai.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jtfSoDienThoai, gridBagConstraints);

        jtfDiaChi.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        add(jtfDiaChi, gridBagConstraints);

        jdcNgaySinh.setPreferredSize(new java.awt.Dimension(250, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jdcNgaySinh, gridBagConstraints);

        jbtClickDangKy.setText("Đăng ký");
        jbtClickDangKy.setPreferredSize(new java.awt.Dimension(150, 25));
        jbtClickDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClickDangKyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(jbtClickDangKy, gridBagConstraints);
    }// </editor-fold>

    private void jbtClickDangKyActionPerformed(java.awt.event.ActionEvent evt) {
        String tenTK = jtfTenTK.getText(), matKhau = jtfMatKhau.getText(), ho = jtfHo.getText(), ten = jtfTen.getText(),
                diaChi = jtfDiaChi.getText(), sdt = jtfSoDienThoai.getText(), ngaySinh = "";
        try {
            String rawDate = jdcNgaySinh.getDate() != null ? DateFormat.getDateInstance().format(jdcNgaySinh.getDate())
                    : "";
            if (!rawDate.equals("")) {
                Date parsed = new SimpleDateFormat("MMM dd, yyyy").parse(rawDate);
                ngaySinh = new SimpleDateFormat("dd/MM/yyyy").format(parsed);
            }
        } catch (ParseException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        int count = 0;
        if (!InputTester.chiSoVaChu(tenTK, "Tên đăng nhập", 255)) {
            count++;
        }
        if (!InputTester.chiChuVaKhoangTrang(ho, "Họ", 30)) {
            count++;
        }
        if (!InputTester.chiChuVaKhoangTrang(ten, "Tên", 15)) {
            count++;
        }
        if (!InputTester.kiemNgay(ngaySinh, "Ngày sinh")) {
            count++;
        }
        if (!InputTester.laDiaChi(diaChi, "Địa chỉ", 255)) {
            count++;
        }
        if (!InputTester.soDienThoaiHopLe(sdt, "Số điện thoại", 10)) {
            count++;
        }
        if (count == 0) {
            MangTaiKhoan TKArr = new MangTaiKhoan();
            // if (TKArr.add(tenTK, matKhau, ho, ten, sdt, diaChi, ngaySinh)) {
            //     // Đăng ký thành công
            // }
        } else
            return;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jbtClickDangKy;
    private javax.swing.JLabel jlbDiaChi;
    private javax.swing.JLabel jlbHo;
    private javax.swing.JLabel jlbMatKhau;
    private javax.swing.JLabel jlbSoDienThoai;
    private javax.swing.JLabel jlbTen;
    private javax.swing.JLabel jlbTenTK;
    private javax.swing.JLabel jlbNgaySinh;
    private javax.swing.JTextField jtfDiaChi;
    private javax.swing.JTextField jtfHo;
    private javax.swing.JTextField jtfMatKhau;
    private javax.swing.JTextField jtfSoDienThoai;
    private javax.swing.JTextField jtfTen;
    private javax.swing.JTextField jtfTenTK;
    private JDateChooser jdcNgaySinh;
    // End of variables declaration
}