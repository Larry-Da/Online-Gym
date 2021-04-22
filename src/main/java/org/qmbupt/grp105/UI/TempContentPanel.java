package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TempContentPanel extends JPanel
{
    private JPanel content;
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
        content = new VideoDetailPanel();
        content.setBounds(0, UIStyle.barHeight, (int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight ));
        this.add(content);

    }
    public void setContent(JPanel content)
    {
        //this.content = content;
        content.setBounds(0, UIStyle.barHeight, (int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));
        //VideoDetailPanel.mediaPlayerComponent.mediaPlayer().media().startPaused("/Users/daliangrun/Downloads/IMG_0620.MOV");

    }

}
