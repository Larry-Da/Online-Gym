package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBar extends JPanel
{

    TextButton HomePageButton;
    TextButton PersonalButton;
    TextButton ClassButton;
    TextButton NewsButton;
    TextButton ContactButton;
    public MenuBar(CardLayout cards, JPanel mainPanel)
    {
        int buttonWidth = (int)(UIStyle.width / 2 / 7);
        int mid = (int)(UIStyle.width / 2);
        int barHeight = (int)(UIStyle.height) / 10;
        UIStyle.barHeight = barHeight;
        int buttonHeight = barHeight / 2;


        this.setBounds(0, 0, (int) UIStyle.width, barHeight);
        this.setBackground(UIStyle.COLOR_1);

        HomePageButton = new TextButton(UIStyle.COLOR_1, UIStyle.COLOR_2, "HOMEPAGE", buttonWidth + mid, buttonHeight / 2, buttonWidth, buttonHeight, "small", false);
        PersonalButton = new TextButton(UIStyle.COLOR_1, UIStyle.COLOR_2, "PERSONAL", 2 * buttonWidth + mid, buttonHeight / 2, buttonWidth, buttonHeight, "small", false);
        ClassButton = new TextButton(UIStyle.COLOR_1, UIStyle.COLOR_2, "CLASSES", 3 * buttonWidth + mid, buttonHeight / 2, buttonWidth, buttonHeight, "small", false);
        NewsButton = new TextButton(UIStyle.COLOR_1, UIStyle.COLOR_2, "NEWS", 4 * buttonWidth + mid, buttonHeight / 2, buttonWidth, buttonHeight, "small", false);
        ContactButton = new TextButton(UIStyle.COLOR_1, UIStyle.COLOR_2, "CONTACTS", 5 * buttonWidth + mid, buttonHeight / 2, buttonWidth, buttonHeight, "small", false);

        PersonalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //cards.next(mainPanel);
                cards.show(mainPanel, "personalPanel");
            }
        });
        HomePageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cards.show(mainPanel, "homePanel");
            }
        });
        ClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cards.show(mainPanel, "classPanel");
            }
        });
        this.setLayout(null);
        this.add(HomePageButton);
        this.add(PersonalButton);
        this.add(ClassButton);
        this.add(NewsButton);
        this.add(ContactButton);



    }

}
