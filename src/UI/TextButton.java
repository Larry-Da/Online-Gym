package UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
public class TextButton extends JLabel implements MouseListener
{
    private Color[] colors = {Color.BLACK, Color.WHITE};
    private Color[] originalColors = {Color.BLACK, Color.WHITE};
    private String text;
    private Font font;
    private boolean backgroundChange;


    public TextButton(Color background, Color foreground, String text, int x, int y, int width, int height, String size, boolean backgroundChange)
    {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.backgroundChange = backgroundChange;
        if(size.equals("small"))
        {
            this.font = UIStyle.SMALL_FONT;
        }
        else if(size.equals("normal"))
        {
            this.font = UIStyle.NORMAL_FONT;
        }
        this.text = text;
        colors[0] = background;
        colors[1] = foreground;

        originalColors[0] = background;
        originalColors[1] = foreground;

        setBounds(x, y, width, height);

        addMouseListener(this);

    }
    public void mousePressed(MouseEvent e) {
        //
    }

    public void mouseClicked(MouseEvent e)
    {
        //
    }
    public void mouseReleased(MouseEvent e)
    {
        //
    }
    public void mouseEntered(MouseEvent e)
    {
        if(backgroundChange)
            colors[0] = originalColors[0].darker();
        if(originalColors[1].equals(UIStyle.COLOR_4))
            colors[1] = Color.gray;
        else
            colors[1] = originalColors[1].darker();

        repaint();
    }


    public void mouseExited(MouseEvent e)
    {
        if(backgroundChange)
            colors[0] = originalColors[0];
        colors[1] = originalColors[1];
        repaint();
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

        Insets insets = getInsets();
        g2.setColor(colors[0]);
        g2.fillRoundRect(insets.left, insets.top, getWidth() - insets.left - insets.right, getHeight() - insets.top - insets.bottom, 20, 20);

        FontMetrics metrics = g2.getFontMetrics(font);
        int x2 = (getWidth() - metrics.stringWidth(text)) / 2;
        int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.setPaint(colors[1]);
        g2.setFont(font);

        g2.drawString(text, x2, y2);
    }

}
