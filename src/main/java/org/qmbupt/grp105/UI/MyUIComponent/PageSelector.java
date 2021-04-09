package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;

public class PageSelector extends JPanel
{
    public PageSelector(int maxPage, Color background, Color foreground, int x, int y) {
        InputText selectPage = new InputText(UIStyle.SMALL_FONT, false, 0, 0, 40, 25, 3, false, "");
        DynamicText max = new DynamicText(25, 0, "mid", background, foreground, " / " + maxPage, 50, 25, UIStyle.SMALL_FONT);
        this.add(selectPage);
        this.add(max);
        this.setLayout(null);
        this.setBounds(x, y, 90, 25);
        this.setBackground(background);
        this.setVisible(true);
    }
}
