package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Entity.Video;
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
    public TempContentPanel(CardLayout cards, JPanel mainPanel)
    {
        this.setLayout(null);
        this.setBounds(0, 0, (int)UIStyle.width, (int)UIStyle.height);
        JPanel backBar = new JPanel();
        backBar.setBounds(0, 0, (int) UIStyle.width, UIStyle.barHeight);
        backBar.setBackground(UIStyle.COLOR_1);
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


    }
    public void setContent(String type, String name)
    {
        updateUI();
        if(type.equals("video"))
        {
            if(currentContent.equals("video"))
            {
                videoPanel.setCurrentVideo(Video.getSampleVideo());
            }
            else {
                currentContent = "video";
                videoPanel.setCurrentVideo(Video.getSampleVideo());
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
