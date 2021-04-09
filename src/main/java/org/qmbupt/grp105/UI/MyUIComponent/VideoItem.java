package org.qmbupt.grp105.UI.MyUIComponent;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VideoItem extends JPanel
{
    int width;
    int height;
    int whiteSpace;
    Video video;


    public VideoItem(int width, int height, int x, int y, Video video)
    {
        this.width = width;
        this.height = height;
        int whiteSpace = this.width / 15;
        this.whiteSpace = whiteSpace;
        this.video = video;

        this.setBounds(x, y, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>()
        {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        super.paintComponent(g2);
        int titleStartY = this.height / 2;
        int titleStartX = this.whiteSpace;
        String title = video.getName();
        g2.setFont(UIStyle.NORMAL_FONT);
        g2.setPaint(UIStyle.COLOR_4);
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        if(titleWidth + 2 * whiteSpace < width)
        {
            g2.drawString(title, titleStartX, titleStartY);
        }

        int playStartY = this.height * 3 / 4;
        int playStartX = this.whiteSpace;
        String videoPath = "";
        Picture preview = new Picture(videoPath, width, height / 2);




//        g2.setPaint(Color.decode("#6C7089"));
//        g2.setFont(UIStyle.TINY_FONT);
//        g2.drawString(title, (int)(0.067 * width), (int)(0.1 * width));
//
//        g2.setPaint(Color.decode("#F6F6F8"));
//        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
//
//        g2.setPaint(Color.BLACK);
//        g2.setFont(UIStyle.NORMAL_FONT);
//        g2.drawString(contents, (int)(0.067 * width), (int)(0.6 * height));
//
//        Image img = new ImageIcon(imagePath).getImage();
//        g.drawImage(img, width / 2, (int) (0.1 * width), (int)(getHeight() / 1.5), (int)(getHeight() / 1.5),null);// must set width andd height, otherwise it will not display it
    }
    protected void paintBorder(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setPaint(Color.decode("#F6F6F8"));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
}
