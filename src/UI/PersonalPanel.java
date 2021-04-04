package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuBar;

public class PersonalPanel extends JPanel
{

    public PersonalPanel(CardLayout cards, JPanel mainPanel)
    {
        java.awt.MenuBar menuBar = new MenuBar(cards, mainPanel);
        menuBar.setVisible(true);
        this.setLayout(null);
        this.add(menuBar);

        PersonalLeftPanel personalLeftPanel = new PersonalLeftPanel();
        this.add(personalLeftPanel);


    }


}
