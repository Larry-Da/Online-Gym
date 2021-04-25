package org.qmbupt.grp105.UI;
import  org.qmbupt.grp105.UI.*;
import javax.swing.*;
public class main
{
    public static void main(String[] args) {
        new UIStyle();
        JPanel mp = new MainPanel();
        JFrame jf = new JFrame();
        jf.setUndecorated(true);
        jf.add(mp);
        jf.setVisible(true);
        mp.setVisible(true);
        jf.setBounds((int)((UIStyle.ScreenWidth - UIStyle.width)/2), 0, (int) UIStyle.width, (int) UIStyle.height);

    }
}
