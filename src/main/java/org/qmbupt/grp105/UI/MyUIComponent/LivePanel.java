package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LivePanel extends JPanel
{
    private int space;
    private int picHeight;
    private String category;
    String coachId;

    public LivePanel(LiveSession live, int x, int y)
    {
        int width = 200;
        int height = 100;
        space = width / 10;
        this.setLayout(null);
        picHeight = height / 3 ;

        String picPath = UIStyle.class.getClassLoader().getResource(live.getUrl()).getPath();
        JLabel picturePreview = new Picture(picPath, picHeight, picHeight);
        this.add(picturePreview);
        picturePreview.setBounds(space, space, picHeight, picHeight);
        this.setBackground(Color.decode("#1A1C21"));
        coachId = live.getCoach_coachId();
        category = live.getCategory();

        int buttonWidth = 65;
        int buttonHeight = 20;
        TextButton attend = new TextButton((int)(width - buttonWidth / 1.5), (int)(height-1.5*buttonHeight), Color.decode("#192D33"), Color.WHITE, "Attend", buttonWidth, buttonHeight, "tiny", true);
        this.add(attend);

        this.setVisible(true);
        this.setBounds(x, y, width, height);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>()
        {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        super.paintComponent(g2);

        g2.setPaint(Color.lightGray);
        g2.setFont(UIStyle.TINY_FONT);
        g2.drawString(coachId, 2 * space + picHeight, (int)(space * 1.5));

        g2.drawString(category, 2 *space + picHeight, (int)(space * 2.5));



    }
}
