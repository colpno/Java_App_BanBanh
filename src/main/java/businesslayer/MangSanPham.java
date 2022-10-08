
package businesslayer;

import datalayer.tblSanPham;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MangSanPham {

    private String path = "image/SanPham/";

    private tblSanPham tblSP = new tblSanPham();
    private MangLoai loaiArr = new MangLoai();
    private ArrayList<SanPham> spList = getAllSP();

    public MangSanPham() {

    }

    public ArrayList<SanPham> getAllSP() {
        return tblSP.getAllSP(0, 0);
    }

    public ArrayList<SanPham> getSP(int soLuong, int trang) {
        return tblSP.getAllSP(soLuong, trang);

    }

    public SanPham getSPByID(int id) {
        SanPham kq = new SanPham();
        spList = getAllSP();

        for (SanPham sp : spList) {
            if (sp.getMaSP() == id)
                kq = sp;
        }

        return kq;
    }

    public ArrayList<SanPham> getSPByTen(String ten) {
        ArrayList<SanPham> kq = new ArrayList<SanPham>();

        String s1 = ten.toLowerCase();
        for (SanPham sp : spList) {
            String s2 = sp.getTenSP().toLowerCase();

            if (s2.indexOf(s1) >= 0) {
                kq.add(sp);
            }
        }
        return kq;

    }

    public int updateSP(SanPham sp, int id) {
        return tblSP.updateSP(sp, id);
    }

    public int deleteSP(int id) {
        return tblSP.deleteSP(id);
    }

    public int insertSP(SanPham sp) {
        return tblSP.insertSP(sp);
    }

    public ArrayList<NhaSanXuat> getAllNSX() {
        return tblSP.getAllNSX();
    }

    public double getTongSoTrang(int soLuong) {
        return Math.ceil((double) spList.size() / soLuong);
    }

    public String getNameNSX(int ma) {
        ArrayList<NhaSanXuat> nsxList = getAllNSX();
        String name = "";
        for (NhaSanXuat n : nsxList) {
            if (ma == n.getMaNSX()) {
                name = n.getTenNSX();
            }
        }
        return name;
    }

    public ArrayList<NhaSanXuat> getNSXByTen(String ten) {
        ArrayList<NhaSanXuat> nsxList = new ArrayList<NhaSanXuat>();
        ArrayList<NhaSanXuat> kq = new ArrayList<NhaSanXuat>();
        nsxList = getAllNSX();
        String s1 = ten.toLowerCase();

        for (NhaSanXuat nsx : nsxList) {
            String s2 = nsx.getTenNSX().toLowerCase();
            if (s2.indexOf(s1) >= 0) {
                kq.add(nsx);
                break;
            }
        }
        return kq;
    }

    public ArrayList<SanPham> timNangCao(int min, int max, ArrayList<JCheckBox> loai, ArrayList<JCheckBox> nsx,
            ArrayList<Loai> loaiList, ArrayList<NhaSanXuat> nsxList) {
        spList = getAllSP();
        ArrayList<Integer> idloai = new ArrayList<Integer>();
        ArrayList<Integer> idnsx = new ArrayList<Integer>();

        for (JCheckBox cb : loai) {
            if (cb.isSelected()) {
                for (Loai l : loaiList) {
                    if (l.getTenLoai() == cb.getText()) {
                        idloai.add(l.getMaLoai());
                        break;
                    }
                }
            }
        }

        for (JCheckBox cb : nsx) {
            if (cb.isSelected()) {
                for (NhaSanXuat n : nsxList) {
                    if (n.getTenNSX() == cb.getText()) {
                        idnsx.add(n.getMaNSX());
                        break;
                    }
                }
            }
        }

        ArrayList<SanPham> kq = new ArrayList<SanPham>();

        for (SanPham sp : spList) {
            int gia = (int) sp.getDonGia();

            if (gia >= min && gia <= max) {
                for (Integer i : idloai) {

                    if (sp.getMaLoai() == i) {
                        for (Integer j : idnsx) {

                            if (sp.getMaNSX() == j) {
                                kq.add(sp);
                                break;
                            }
                        }
                        break;
                    }
                }

            }
        }

        return kq;
    }

    public DefaultTableModel modelHeader(DefaultTableModel model, String[] name) {
        Vector header = new Vector();
        for (String s : name) {
            header.add(s);
        }
        model = new DefaultTableModel(header, 0);
        return model;
    }

    public JTable loadAllSP(JTable table, DefaultTableModel model, ArrayList<SanPham> spList, String[] name,
            int[] size) {

        model = modelHeader(model, name);

        ArrayList<NhaSanXuat> nsxList = getAllNSX();

        for (SanPham sp : spList) {
            Vector record = new Vector();

            String img = sp.getanhDaiDien();
            JLabel lb = new JLabel();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(path + img).getImage().getScaledInstance(size[1],
                    table.getRowHeight(), Image.SCALE_SMOOTH));
            lb.setIcon(imageIcon);

            float ptkm = getPTKM(sp.getMaSP());
            float dongia = sp.getDonGia();
            record.add(false);
            record.add(lb);
            record.add(sp.getMaSP());
            record.add(sp.getTenSP());
            record.add(loaiArr.getLoaiByID(sp.getMaLoai()).getTenLoai());
            record.add(getNameNSX(sp.getMaNSX()));
            record.add(sp.getSoLuong());
            record.add(dongia);
            record.add(ptkm);
            record.add(getGiaKM(dongia, ptkm));
            record.add(sp.getDonViTinh());

            model.addRow(record);
        }
        table.setModel(model);

        return table;
    }

    public JTable loadAllLoai(JTable table, DefaultTableModel model, ArrayList<Loai> loaiList, String[] name) {

        model = modelHeader(model, name);

        for (Loai l : loaiList) {
            Vector record = new Vector();

            record.add(l.getMaLoai());
            record.add(l.getTenLoai());

            model.addRow(record);
        }
        table.setModel(model);

        return table;
    }

    public JTable loadAllNSX(JTable table, DefaultTableModel model, ArrayList<NhaSanXuat> nsxList, String[] name) {

        model = modelHeader(model, name);

        for (NhaSanXuat nsx : nsxList) {
            Vector record = new Vector();

            record.add(nsx.getMaNSX());
            record.add(nsx.getTenNSX());
            record.add(nsx.getDiaChi());
            record.add(nsx.getSoDienThoai());

            model.addRow(record);
        }
        table.setModel(model);

        return table;
    }

    public String fileName(String filename) {

        JFileChooser chooser = new JFileChooser(path);
        chooser.setDialogTitle("Select Image File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGE FILES", "png", "jpg", "jpeg");
        chooser.setFileFilter(fnef);

        int imageChooser = chooser.showOpenDialog(null);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            String absolutePath = f.getAbsolutePath();
            filename = absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1);

        }
        return filename;

    }

    public JLabel setImage(JLabel lb, String filename) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path + filename).getImage().getScaledInstance(lb.getWidth(),
                lb.getHeight(), Image.SCALE_SMOOTH));
        lb.setIcon(imageIcon);
        return lb;
    }

    public ArrayList<KhuyenMai> getALLKM() {
        return tblSP.getALLKM();
    }

    public ChiTietKhuyenMai getCTKM(int maKM, int maSP) {
        return tblSP.getCTKM(maKM, maSP);
    }

    public float getPTKM(int maSP) {
        ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai();
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateTime = new Date();
            Date today = formatter.parse(formatter.format(dateTime));

            ArrayList<KhuyenMai> kmList = getALLKM();

            for (KhuyenMai km : kmList) {
                Date dateFrom = formatter.parse(km.getNgayBatDau());
                Date dateTo = formatter.parse(km.getNgayKetThuc());

                if (today.after(dateFrom) && today.before(dateTo)) {
                    ctkm = getCTKM(km.getMaKM(), maSP);
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(MangSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctkm.getPhanTramKhuyenMai();
    }

    public float getGiaKM(float donGia, float ptkm) {
        return donGia - (donGia * ptkm / 100);
    }

    public int getLastMaSP() {
        return tblSP.getLastMaSP();
    }

    public SanPham CheckSP(SanPham sp) {
        spList = getAllSP();
        SanPham kq = null;
        for (SanPham s : spList) {
            String tenSPMoi = sp.getTenSP().toLowerCase();
            String tenSPCu = s.getTenSP().toLowerCase();
            if (tenSPMoi.equals(tenSPCu) && s.getMaLoai() == sp.getMaLoai() && s.getMaNSX() == sp.getMaNSX()
                    && s.getDonGia() == sp.getDonGia() && s.getMaSP() != sp.getMaSP()) {
                kq = s;
            }
        }
        return kq;
    }

    public int updateQty(int ma, int qty) {
        return tblSP.updateQty(ma, qty);
    }

    public ArrayList<SanPham> searchMul(String col, String giaTriTim) {
        ArrayList<SanPham> spArr = new ArrayList<SanPham>();
        for (SanPham sp : spList) {
            switch (col) {
                case "Mã SP": {
                    if (sp.getMaSP() == Integer.parseInt(giaTriTim)) {
                        spArr.add(sp);
                    }
                    break;
                }
                case "Mã loại": {
                    if (sp.getMaLoai() == Integer.parseInt(giaTriTim)) {
                        spArr.add(sp);
                    }
                    break;
                }
                case "Mã NSX": {
                    if (sp.getMaNSX() == Integer.parseInt(giaTriTim)) {
                        spArr.add(sp);
                    }
                    break;
                }
                case "Tên sản phẩm": {
                    if (sp.getTenSP().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        spArr.add(sp);
                    }
                    break;
                }
                case "Đơn giá": {
                    if (sp.getDonGia() == Integer.parseInt(giaTriTim)) {
                        spArr.add(sp);
                    }
                    break;
                }
                case "Đơn vị tính": {
                    if (sp.getDonViTinh().toLowerCase().contains(giaTriTim.toLowerCase())) {
                        spArr.add(sp);
                    }
                    break;
                }
                case "Số lượng": {
                    if (sp.getSoLuong() == Integer.parseInt(giaTriTim)) {
                        spArr.add(sp);
                    }
                    break;
                }
            }
        }
        return spArr;
    }
}
