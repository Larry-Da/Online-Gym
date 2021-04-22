package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class FilterBox extends JPanel
{
    private Font font;
    private FontMetrics mt;
    private String color;

    public FilterBox (int y, String contents[], String color)
    {
        setLayout(null);
        this.color = color;
        font = UIStyle.SMALL_FONT;
        if(color.equals("dark"))
            this.setBackground(Color.decode("#14151A"));
        else
            this.setBackground(Color.white);
        mt = Toolkit.getDefaultToolkit().getFontMetrics(font);



        JLabel head = new JLabel(contents[0]);
        head.setFont(font);
        if(color.equals("dark"))
            head.setForeground(Color.white);
        else
            head.setForeground(Color.black);
        int posision = 200;
        int space = 25;

        head.setBounds(30, 0, mt.stringWidth(contents[0]) + space * 3, 40);
        this.add(head);
        this.setVisible(true);
        this.setBounds(0, y, (int)UIStyle.width, 40);



        for(int i = 1; i < contents.length; i++)
        {
            JCheckBox option = new JCheckBox(contents[i]);
            option.setFont(font);
            if(color.equals("dark"))
                option.setForeground(Color.white);
            else
                option.setForeground(Color.BLACK);
            int StringWidth = mt.stringWidth(contents[i]);
            option.setBounds(posision, 0, StringWidth+space*2, 40);
            posision += StringWidth + space*2;
            this.add(option);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        if(color.equals("dark"))
            g2.setPaint(Color.decode("#EC9730"));
        else
            g2.setPaint(UIStyle.BLUE_SHALLOW);
        g2.drawLine(0, 39, (int)UIStyle.width, 39);
    }
}
