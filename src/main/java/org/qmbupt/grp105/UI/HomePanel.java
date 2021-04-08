package org.qmbupt.grp105.UI;


import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel
{

    public HomePanel(CardLayout cards, JPanel mainPanel)
    {
        MenuBar mb = new MenuBar(cards, mainPanel);
        mb.setVisible(true);
        this.setLayout(null);
        this.add(mb);

        String path = UIStyle.HomePanel_BackGround;
        int barHeight = (int)(UIStyle.height) / 10;
        int picHeight = (int) UIStyle.height - barHeight;
        JLabel picture = new Picture(path, (int) UIStyle.width, picHeight);
        picture.setBounds(0, barHeight, (int) UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(picture);


    }

}
