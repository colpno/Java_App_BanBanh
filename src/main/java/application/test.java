package application;

import java.awt.*;

import javax.swing.*;

public class test extends Canvas {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel label = new JLabel();
        label.setSize(50, 50);
        ImageIcon ii = new ImageIcon(new ImageIcon("image/TaiKhoan/TK-1.jpg").getImage()
                .getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        label.setIcon(ii);
        f.add(label);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        test m = new test();
    }

}