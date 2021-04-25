package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;
public class ContactPanel extends JLayeredPane
{
    public ContactPanel(CardLayout cards, JPanel mainPanel)
    {
        MenuBar mb = new MenuBar(cards, mainPanel);
        mb.setVisible(true);
        this.setLayout(null);
        this.add(mb);

        String path = UIStyle.Contact_background;
        int barHeight = (int)(UIStyle.height) / 10;
        int picHeight = (int) UIStyle.height - barHeight;
        JLabel picture = new Picture(path, (int) UIStyle.width, picHeight);
        picture.setBounds(0, barHeight, (int) UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(picture);
        int inputWidth = (int)(UIStyle.width / 3);
        int inputHeight = 30;
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.setVisible(true);
//        contentPanel.setBounds(0, 0, (int)UIStyle.width, (int)UIStyle.height);
//

        InputText email = new InputText(inputWidth, inputHeight, 20, true, (int)(UIStyle.width/2 - (inputWidth / 2)) - 10, 300, "Email", false);
        email.setOpaque(false);

        this.add(email);


        InputText subject = new InputText(inputWidth, inputHeight, 20, true, (int)(UIStyle.width/2 + (inputWidth / 2)) + 10, 300, "Subject", false);
        this.add(subject);

        InputArea advice = new InputArea(inputWidth * 2 + 20, inputHeight*5, true, (int)(UIStyle.width / 2), 450, "Input Here...", false);
        this.add(advice);



        TextButton submit = new TextButton((int)(UIStyle.width / 2), (int)(UIStyle.height -  inputHeight * 3),  UIStyle.BLUE_BUTTRESS, java.awt.Color.WHITE, "Submit", (int)(inputWidth / 2), (int)inputHeight, "normal", true);
        this.add(submit);
        setLayer(submit, 0);
        setLayer(advice, 0);
        setLayer(email, 0);
        setLayer(subject, 0);
        setLayer(picture, -1);



    }
}
