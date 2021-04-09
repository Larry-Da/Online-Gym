package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;

public class VirtualClassPanel extends JPanel
{
    public VirtualClassPanel(CardLayout cards, JPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel);
        menuBar.setVisible(true);
        this.setLayout(null);
        this.add(menuBar);
        int barHeight = (int)(UIStyle.height) / 10;


        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, barHeight, (int)UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(contentPanel);
        contentPanel.setVisible(true);
        CardLayout loginCards = new CardLayout();
        contentPanel.setLayout(loginCards);
        CategoryPanel categoryPanel = new CategoryPanel(loginCards, contentPanel);
        contentPanel.add(categoryPanel, "categoryPanel");
//        SignInPanel signInPanel = new SignInPanel(loginCards, contentPanel);
//        contentPanel.add(signInPanel, "signInPanel");
//        CustomerPanel customerPanel = new CustomerPanel(loginCards, contentPanel);
//        contentPanel.add(customerPanel, "customerPanel");
//        AdministratorPanel administratorPanel = new AdministratorPanel(loginCards, contentPanel);
//        contentPanel.add(administratorPanel, "administratorPanel");

    }
}

class CategoryPanel extends JPanel
{
    public CategoryPanel(CardLayout cards, JPanel contentPanel) {
        this.setLayout(null);
        this.setBackground(Color.black);
        int buttonWidth = (int)(UIStyle.width / 3);
        int buttonHeight = (int)(UIStyle.height * 0.39);

        CategoryButton bicycle = new CategoryButton(UIStyle.VirtualClass_bicycle, buttonWidth, buttonHeight, "Bicycle Training");
        bicycle.setBounds(0, 0, buttonWidth, buttonHeight);
        this.add(bicycle);

        CategoryButton HITT = new CategoryButton(UIStyle.VirtualClass_HITT, buttonWidth, buttonHeight, "HITT");
        HITT.setBounds(buttonWidth, 0, buttonWidth, buttonHeight);
        this.add(HITT);

        CategoryButton flexbility = new CategoryButton(UIStyle.VirtualClass_flexibility, buttonWidth, buttonHeight, "Flexibility");
        flexbility.setBounds(buttonWidth * 2, 0, buttonWidth, buttonHeight);
        this.add(flexbility);

        CategoryButton yoga = new CategoryButton(UIStyle.VirtualClass_yoga, buttonWidth, buttonHeight, "Yoga");
        yoga.setBounds(0, buttonHeight, buttonWidth, buttonHeight);
        this.add(yoga);

        CategoryButton strength = new CategoryButton(UIStyle.VirtualClass_strength, buttonWidth, buttonHeight, "Strength");
        strength.setBounds(buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.add(strength);


        CategoryButton weightLoss = new CategoryButton(UIStyle.VirtualClass_loseWeight, buttonWidth, buttonHeight, "Weight Loss");
        weightLoss.setBounds(2 * buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.add(weightLoss);




    }
}