package UI;

import javax.swing.*;

public class main
{
    public static void main(String[] args) {
        new UIStyle();
        JPanel mp = new MainPanel();
        JFrame jf = new JFrame();
        jf.add(mp);
        jf.setVisible(true);
        mp.setVisible(true);
        jf.setBounds(0, 0, (int) UIStyle.width, (int) UIStyle.height);


    }
}
