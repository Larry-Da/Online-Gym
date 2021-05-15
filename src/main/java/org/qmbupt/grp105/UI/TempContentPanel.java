package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Mail;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.MyReminder;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;
import org.qmbupt.grp105.UI.MyUIComponent.VideoModifyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class TempContentPanel extends JPanel
{
    private JPanel contentContainer;
    private CardLayout innerCards = new CardLayout();
    private String currentContent;
    private VideoDetailPanel videoPanel;
    private VideoModifyPanel videoModifyPanel;
    private EmailDetailPanel emailPanel;
    public static MyReminder reminder;
    public TempContentPanel(CardLayout cards, JPanel mainPanel)
    {
        this.setLayout(null);
        this.setBounds(0, 0, (int)UIStyle.width, (int)UIStyle.height);
        JPanel backBar = new JPanel();
        backBar.setBounds(0, 0, (int) UIStyle.width, UIStyle.barHeight);
        backBar.setBackground(UIStyle.COLOR_1);
        reminder = new MyReminder(backBar);
        this.add(backBar);
        backBar.setLayout(null);

        TextButton back = new TextButton(100, UIStyle.barHeight / 2, UIStyle.COLOR_1.brighter().brighter(), Color.white, "Back", 100, (int)(UIStyle.barHeight / 2), "normal", true);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cards.show(mainPanel, MainPanel.currentPanel);
            }
        });
        backBar.add(back);
        this.setVisible(true);
        contentContainer = new JPanel();
        contentContainer.setBounds(0, UIStyle.barHeight, (int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight ));
        this.add(contentContainer);
        contentContainer.setLayout(innerCards);
        videoPanel = new VideoDetailPanel();

        contentContainer.add(videoPanel, "video");
        currentContent = "video";
        videoModifyPanel = new VideoModifyPanel();
        contentContainer.add(videoModifyPanel, "videoModify");
        emailPanel = new EmailDetailPanel();
        contentContainer.add(emailPanel, "email");


    }
    public void setContent(String type, Mail email)
    {
        if(type.equals("emailRead"))
        {
            if(currentContent.equals("emailRead"))
            {
                emailPanel.setEmail(email, false);
            }
            else
            {
                currentContent = "emailRead";
                emailPanel.setEmail(email, false);
                innerCards.show(contentContainer, "email");
            }
        }
        else if(type.equals("emailWrite"))
        {
            if(currentContent.equals("emailWrite"))
            {
                emailPanel.setEmail(email, true);
            }
            else
            {
                currentContent = "emailWrite";
                emailPanel.setEmail(email, true);
                innerCards.show(contentContainer, "email");
            }
        }
    }
    public void setContent(String type, String id)
    {
        updateUI();
        if(type.equals("video"))
        {
            if(currentContent.equals("video"))
            {
                videoPanel.setCurrentVideo(VideoController.getController().getVideoByVideoId(id));
            }
            else {
                currentContent = "video";
                videoPanel.setCurrentVideo(VideoController.getController().getVideoByVideoId(id));
                innerCards.show(contentContainer, "video");
            }
        }
        else if(type.equals("videoModify"))
        {
            if(currentContent.equals("videoModify"))
            {

            }
            else
            {
                currentContent = "videoModify";
                innerCards.show(contentContainer, "videoModify");
            }
        }

    }

}
