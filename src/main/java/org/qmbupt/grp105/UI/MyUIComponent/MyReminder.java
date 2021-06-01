package org.qmbupt.grp105.UI.MyUIComponent;


import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p>It is used to give a reminder in the top of the page.
 * It is implemented by multi thread (one thread changing position and another
 * present the UI)</p>
 * @author daliangrun
 * @version 5.3
 */
public class MyReminder
{
    private JPanel bar;
    public MyReminder(JPanel bar)
    {
        this.bar = bar;
    }

    /**
     * Showing Green message
     * @param message message to be shown
     */
    public void OK(String message)
    {
        popup(message, UIStyle.GREEN_OK);
    }

    /**
     * Showing red alert message
     * @param message message to be shown
     */
    public void WRONG(String message)
    {
        popup(message, Color.decode("#E04147"));

    }

    /**
     * choose different color message
     * @param message text
     * @param background color to be set
     */
    public void popup(String message, Color background)
    {
        int space = 10;
        int width = 0;
        int height = (int)(UIStyle.barHeight/1.5);
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(UIStyle.NORMAL_FONT);
        int stringWidth = fm.stringWidth(message);

        if(2* space + stringWidth < 150)
            width = 150;
        else
            width = 2* space + stringWidth;
        ReminderPanel content = new ReminderPanel(background, message, width, height);

        Thread show = new Thread(new Runnable(){
            public void run() {
                bar.add(content, 0);
                bar.repaint();
                try {
                    Thread.sleep(5000);
                    bar.remove(content);
                    bar.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        show.start();

    }

}

/**
 * <p>The actual panel to be shown in the top menubar</p>
 * @author daliangrun
 * @version 5.3
 */
class ReminderPanel extends JPanel
{
    private Color background;
    private String message;
    private int messageX;
    private int messageY;
    public ReminderPanel(Color background, String message, int width, int height)
    {
        this.background = background;
        this.message = message;
        messageY = 0;
        messageX = 0;
        int startX = (int)(UIStyle.width/2 - width / 2);
        setBounds(startX, -UIStyle.barHeight, width, height);
        setOpaque(false);

        repaint();
        Thread changePosition = new Thread(new Runnable() {
            @Override
            public void run() {
                while(getBounds().y < (UIStyle.barHeight - height) / 2)
                {
                    setBounds(startX, getBounds().y + 1, width, height);
                    repaint();
                    try
                    {
                        Thread.sleep(5);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        changePosition.start();

    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);


        g2.setColor(background);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);

        g2.setFont(UIStyle.NORMAL_FONT);
        g2.setColor(Color.white);
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(UIStyle.NORMAL_FONT);
        int stringWidth = fm.stringWidth(message);
        g2.drawString(message, (int)(getWidth() - stringWidth)/2, getHeight() / 2 + 5);
    }
}
