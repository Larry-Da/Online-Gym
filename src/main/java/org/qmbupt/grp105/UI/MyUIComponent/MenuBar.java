package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.UI.UIStyle;

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
        int buttonWidth = (int)(UIStyle.width / 2 / 6);
        int mid = (int)(UIStyle.width / 2);
        int barHeight = (int)(UIStyle.height) / 10;
        UIStyle.barHeight = barHeight;
        int buttonHeight = barHeight / 2;


        this.setBounds(0, 0, (int) UIStyle.width, barHeight);
        this.setBackground(UIStyle.COLOR_1);

        HomePageButton = new TextButton(buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "HOMEPAGE",  buttonWidth, buttonHeight, "small", false);
        PersonalButton = new TextButton(2 * buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "PERSONAL",  buttonWidth, buttonHeight, "small", false);
        ClassButton = new TextButton(3 * buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "CLASSES",  buttonWidth, buttonHeight, "small", false);
        NewsButton = new TextButton(4 * buttonWidth + mid, buttonHeight,UIStyle.COLOR_1, UIStyle.COLOR_2, "NEWS",  buttonWidth, buttonHeight, "small", false);
        ContactButton = new TextButton(5 * buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "CONTACTS",  buttonWidth, buttonHeight, "small", false);

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
                cards.show(mainPanel, "virtualClassPanel");
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
