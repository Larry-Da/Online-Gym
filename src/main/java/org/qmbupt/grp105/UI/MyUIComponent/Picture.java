package org.qmbupt.grp105.UI.MyUIComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.HashMap;

public class Picture extends JLabel
{
    String path;
    int x;
    int y;
    public Picture(String path, int x, int y)
    {
        this.path = path;
        this.x = x;
        this.y = y;

    }
    protected void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>()
        {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        super.paintComponent(g2);

        ImageIcon icon = new ImageIcon(path);
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        int desiredWidth;
        int desiredHeight;
        if(((double)x / y) < ((double)originalWidth / originalHeight))
        {
            desiredHeight = originalHeight;
            desiredWidth = (int)(originalHeight * (double)x / y);
        }
        else
        {
            desiredWidth = originalWidth;
            desiredHeight = (int)(originalWidth * (double)y / x);
        }
        CropImageFilter cropFilter = new CropImageFilter(0, 0, desiredWidth, desiredHeight);

        Image temp =  Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(icon.getImage().getSource(), cropFilter));
        ImageIcon newIcon = new ImageIcon(temp);
        g.drawImage(newIcon.getImage(), 0, 0, getWidth(),getHeight(),
                newIcon.getImageObserver());
    }
}
