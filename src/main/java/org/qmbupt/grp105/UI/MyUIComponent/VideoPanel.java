package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VideoPanel extends JPanel
{
    private int space;
    private String title;
    private int likes;
    private int picHeight;
    private int lineHeight;
    private int viewCount;
    private double rate;
    private String category;
    public VideoPanel(Video video, int x, int y)
    {
        int width = 200;
        int height = 300;
        space = width / 10;
        this.setLayout(null);
        picHeight = height / 3 * 2;
        lineHeight  = height / 3 / 3;
        String picPath = UIStyle.class.getClassLoader().getResource(video.getUrl()).getPath();
        JLabel picturePreview = new Picture(picPath, width, picHeight);
        this.add(picturePreview);
        picturePreview.setBounds(0, 0, width, picHeight);


        title = video.getName();
        likes = video.getLikes();
        viewCount = video.getViewsCount();
        rate = video.getRating();
        category = video.getCategory();


        setBackground(Color.WHITE);
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

        g2.setPaint(Color.BLACK);
        g2.setFont(UIStyle.TINY_FONT);
        g2.drawString(title, space, picHeight + space);

        g2.setPaint(Color.gray);
        g2.drawString(likes + "", (int)(2.3 * space), (int)(picHeight + space / 1.40 + lineHeight));


        g2.drawString(viewCount + "", getWidth() /2 + (int)(2.3 * space), (int)(picHeight + space / 1.40 + lineHeight));

        g2.drawString(rate + "", (int)(2.3 * space), (int)(picHeight + space / 1.40 + 2* lineHeight));

        g2.drawString(category,getWidth() /2 +  (int)(2.3 * space), (int)(picHeight + space / 1.40 + 2* lineHeight) );


        Image img1 = new ImageIcon(UIStyle.VirtualClass_thumbUp).getImage();
        g2.drawImage(img1, space, picHeight +  lineHeight, space, space,null);// must set width andd height, otherwise it will not display it

        Image img2 = new ImageIcon(UIStyle.VirtualClass_play).getImage();
        g2.drawImage(img2, getWidth() / 2 + space, picHeight +   lineHeight, space, space,null);// must set width andd height, otherwise it will not display it

        Image img3 = new ImageIcon(UIStyle.VirtualClass_rate).getImage();
        g2.drawImage(img3, space, picHeight +  2 * lineHeight, space, space,null);// must set width andd height, otherwise it will not display it

        Image img4 = new ImageIcon(UIStyle.VirtualClass_category).getImage();
        g2.drawImage(img4, getWidth() / 2 +  space, picHeight +  2 * lineHeight, space, space,null);// must set width andd height, otherwise it will not display it


    }
}
