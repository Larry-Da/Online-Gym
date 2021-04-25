package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LivePanel extends JPanel
{
    private int space;
    private int picHeight;
    private String category;
    private int categoryX;
    private int categoryY;
    private String coachId;
    private int coachIdX;
    private int coachIdY;
    private String buttonText;
    private String startDate;
    private String endDate;

    private int dateX;
    private int dateY;
    private String size;
    private int width;
    private int height;




    public LivePanel(LiveSession live, int x, int y, String size)
    {

        this.setLayout(null);
        coachId = live.getCoach_coachId();
        category = live.getCategory();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        startDate = "Start: " + simpleDateFormat.format(live.getStartTime());
        endDate = "End:  " + simpleDateFormat.format(live.getEndTime());
        this.size = size;
        int buttonWidth;
        int buttonHeight;
        if(size.equals("small")) {
            width = 200;
            height = 100;
            space = width / 10;
            picHeight = height / 3 ;
            buttonText = "Join";
            coachIdX = 2 * space + picHeight;
            coachIdY = (int)(space * 1.5);
            categoryX = 2 *space + picHeight;
            categoryY = (int)(space * 2.5);
            buttonWidth = 65;
            buttonHeight = 20;

        }
        else
        {
            width = (int)UIStyle.width - (int)(UIStyle.width * 0.24);
            height = 150;
            buttonText = "Attend";
            space = 20;
            picHeight = 100;
            dateX = 4 * space + picHeight;
            dateY = (int)(space * 2);
            categoryX = dateX;
            categoryY = dateY + 80;
            coachIdX = categoryX + 200;
            coachIdY = categoryY;
            buttonHeight = 40;
            buttonWidth = 100;
        }
        TextButton button;
        if(size.equals("small"))
            button = new TextButton((int)(width - buttonWidth / 1.5), (int)(height-1.5*buttonHeight), Color.decode("#192D33"), Color.WHITE, buttonText, buttonWidth, buttonHeight, "tiny", true);
        else
            button = new TextButton((int)(width - buttonWidth / 1.5), (int)(height-1.5*buttonHeight), Color.decode("#192D33"), Color.WHITE, buttonText, buttonWidth, buttonHeight, "small", true);

        this.add(button);
        String picPath = UIStyle.class.getClassLoader().getResource(live.getUrl()).getPath();
        JLabel picturePreview = new Picture(picPath, picHeight, picHeight);
        this.add(picturePreview);
        picturePreview.setBounds(space, space, picHeight, picHeight);
        if(size.equals("small"))
            this.setBackground(Color.decode("#1A1C21"));
        else
            this.setBackground(Color.white);
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


        if(size.equals("small")) {
            g2.setFont(UIStyle.TINY_FONT);
            g2.setPaint(Color.lightGray);
        }
        else {
            g2.setPaint(Color.BLACK);
            g2.setFont(UIStyle.SMALL_FONT);
            g2.drawString(startDate, dateX, dateY);
            g2.drawString(endDate, dateX, dateY + 30);
        }
        g2.drawString(coachId, coachIdX, coachIdY);
        g2.drawString(category, categoryX, categoryY);

        if(!size.equals("small"))
        {
            g2.setPaint(Color.lightGray);
            //g2.drawLine(0, height-1, width, height-1);
        }


    }
}
