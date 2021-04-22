package org.qmbupt.grp105.UI;

import org.w3c.dom.ls.LSParserFilter;

import java.awt.*;

public class UIStyle
{
    public static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 18);
    public static final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font TINY_FONT = new Font("Arial", Font.PLAIN, 10);
    public static final Font BIG_FONT = new Font("Arial", Font.PLAIN, 36);


    public static final Font SMALL_ARIAL_BOLD = new Font("Arial", Font.BOLD, 14);
    public static final Font TINY_ARIAL_BOLD = new Font("Arial", Font.BOLD, 10);

    public static final Font NORMAL_ARIAL_BOLD = new Font("Arial", Font.BOLD, 18);

    public static final Color COLOR_1 = Color.decode("#292F41");
    public static final Color COLOR_2 = Color.WHITE;
    public static final Color COLOR_3 = Color.decode("#F6F6F6");
    public static final Color COLOR_4 = Color.BLACK;

    public static final Color BLUE_BUTTRESS = Color.decode("#1253CE");
    public static final Color BLUE_SHALLOW = Color.decode("#ACC7F1");
    public static final Color GRAY_BUTTRESS = Color.decode("#58606A");
    public static final Color GRAY_SHALLOW = Color.decode("#F8FAFB");

    public static final Color GREEN_OK = Color.decode("#28943D");

    public static double width;
    public static double height;
    public static int barHeight;
    public static int ScreenWidth;
    public static int ScreenHeight;

    public static final String HomePanel_BackGround = UIStyle.class.getClassLoader().getResource("Fitness-for-10-Home-Licensing-Information.jpg").getPath();
    public static final String SignInPanel_Login = UIStyle.class.getClassLoader().getResource("LoginPic.jpg").getPath();
    public static final String CustomerPanel_PersonIcon = UIStyle.class.getClassLoader().getResource("PersonalIcon.jpg").getPath();
    public static final String CustomerMember_Money = UIStyle.class.getClassLoader().getResource("Money.png").getPath();
    public static final String CustomerMember_Level = UIStyle.class.getClassLoader().getResource("level-icon.png").getPath();
    public static final String CustomerMember_Time = UIStyle.class.getClassLoader().getResource("remainTime.png").getPath();
    public static final String Administrator_PersonIcon = UIStyle.class.getClassLoader().getResource("adminstrator.png").getPath();
    public static final String VirtualClass_bicycle = UIStyle.class.getClassLoader().getResource("bicycle.jpg").getPath();
    public static final String VirtualClass_HITT = UIStyle.class.getClassLoader().getResource("HIIT.jpg").getPath();
    public static final String VirtualClass_flexibility =  UIStyle.class.getClassLoader().getResource("FLEXIBILITY.jpg").getPath();
    public static final String VirtualClass_yoga = UIStyle.class.getClassLoader().getResource("yoga.jpg").getPath();
    public static final String VirtualClass_strength = UIStyle.class.getClassLoader().getResource("Strength.jpg").getPath();
    public static final String VirtualClass_loseWeight =  UIStyle.class.getClassLoader().getResource("loseWeight.jpg").getPath();
    public static final String VirtualClass_thumbUp = UIStyle.class.getClassLoader().getResource("thumbs-up-512.png").getPath();
    public static final String VirtualClass_play = UIStyle.class.getClassLoader().getResource("media_play.png").getPath();
    public static final String VirtualClass_rate = UIStyle.class.getClassLoader().getResource("rating.png").getPath();
    public static final String VirtualClass_category = UIStyle.class.getClassLoader().getResource("category.png").getPath();
    public static final String Contact_background = UIStyle.class.getClassLoader().getResource("Contact.jpeg").getPath();
    public static final String VirtualClass_pause = UIStyle.class.getClassLoader().getResource("pause.png").getPath();
    public static final String VirtualClass_favorite = UIStyle.class.getClassLoader().getResource("favorite.png").getPath();


    public UIStyle()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth =(int)dim.getWidth();
        ScreenHeight = (int)dim.getHeight();
        width = 1120;
        height = 700;
    }

}

