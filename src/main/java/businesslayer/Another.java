package businesslayer;

import javax.swing.JButton;
import java.awt.Dimension;

public class Another {
    public static String thaySlashNgay(String str) {
        if (str.contains("/")) {
            String[] splitedFrom = str.split("/");
            return splitedFrom[2] + "-" + splitedFrom[1] + "-" + splitedFrom[0];
        }
        return null;
    }

    public static void setSizeOfButton(int width, int height, JButton... arg) {
        for (JButton btn : arg) {
            btn.setPreferredSize(new Dimension(width, height));
        }
        return;
    }
}
