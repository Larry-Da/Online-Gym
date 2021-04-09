package org.qmbupt.grp105.UI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
    private HomePanel homePanel;
    private PersonalPanel personalPanel;
    private VirtualClassPanel virtualClassPanel;


    public MainPanel()
    {
        CardLayout cards = new CardLayout();
        this.setLayout(cards);

        homePanel = new HomePanel(cards, this);
        this.add(homePanel, "homePanel");
        //homePanel.setVisible(true);

        personalPanel = new PersonalPanel(cards, this);
        this.add(personalPanel, "personalPanel");
        //personalPanel.setVisible(true);

        virtualClassPanel = new VirtualClassPanel(cards, this);
        this.add(virtualClassPanel, "virtualClassPanel");
        //classPanel.setVisible(true);





    }
}
