
package businesslayer;


public class Loai {
    private int maLoai;
    private String tenLoai;
    
    public Loai(){
        
    }

    public Loai(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }
    
    public int getMaLoai() {
        return maLoai;
    }
    
    public String getTenLoai() {
        return tenLoai;
    }
    
    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return "Loai{" + "maLoai=" + maLoai + ", tenLoai=" + tenLoai + '}';
    }
    
    
}
