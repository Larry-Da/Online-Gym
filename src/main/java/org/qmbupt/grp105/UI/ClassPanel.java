package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;

public class ClassPanel extends JPanel
{
    public ClassPanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar mb = new MenuBar(cards, mainPanel);
        mb.setVisible(true);
        this.setLayout(null);
        this.add(mb);
    }
}
