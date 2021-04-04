package UI;

import javax.swing.*;
import java.awt.*;

public class ClassPanel extends JPanel
{
    public ClassPanel(CardLayout cards, JPanel mainPanel)
    {
        MenuBar mb = new MenuBar(cards, mainPanel);
        mb.setVisible(true);
        this.setLayout(null);
        this.add(mb);
    }
}
