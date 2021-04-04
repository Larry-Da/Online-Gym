package UI;

import java.awt.*;

public class UIStyle
{
    public static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 18);
    public static final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Color COLOR_1 = Color.decode("#292F41");
    public static final Color COLOR_2 = Color.WHITE;
    public static final Color COLOR_3 = Color.decode("#F6F6F6");
    public static final Color COLOR_4 = Color.BLACK;
    public static double width;
    public static double height;
    public static int barHeight;


    public UIStyle()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = dim.getWidth();
        height = dim.getHeight();
    }

}

