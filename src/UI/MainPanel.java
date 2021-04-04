package UI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
    HomePanel homePanel;
    PersonalPanel personalPanel;
    ClassPanel classPanel;

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

        classPanel = new ClassPanel(cards, this);
        this.add(classPanel, "classPanel");
        //classPanel.setVisible(true);





    }
}
