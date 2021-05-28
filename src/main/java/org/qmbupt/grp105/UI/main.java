package org.qmbupt.grp105.UI;
import  org.qmbupt.grp105.UI.*;
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main
{
    public static void main(String[] args) {
        new UIStyle();
        UIStyle.updateSetting();
        JPanel mp = new MainPanel();
        JFrame jf = new JFrame();
        jf.setUndecorated(true);

        jf.add(mp);
        jf.setVisible(true);
        mp.setVisible(true);
        jf.setBounds((int)((UIStyle.ScreenWidth - UIStyle.width)/2), (int)(UIStyle.ScreenHeight - UIStyle.height)/2, (int) UIStyle.width, (int) UIStyle.height);


    }
}
