package org.qmbupt.grp105.UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Entity.Coach;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

public class PersonalPanel extends JPanel
{
    public static MyReminder reminder;
    public PersonalPanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel);
        reminder = new MyReminder(menuBar);
        menuBar.setVisible(true);
        this.setLayout(null);
        this.add(menuBar);
        int barHeight = (int)(UIStyle.height) / 10;


        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, barHeight, (int)UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(contentPanel);
        contentPanel.setVisible(true);
        CardLayout loginCards = new CardLayout();
        contentPanel.setLayout(loginCards);
        SignInPanel signInPanel = new SignInPanel(loginCards, contentPanel);
        contentPanel.add(signInPanel, "signInPanel");
        CustomerPanel customerPanel = new CustomerPanel(loginCards, contentPanel, cards, mainPanel);
        contentPanel.add(customerPanel, "customerPanel");
        AdministratorPanel administratorPanel = new AdministratorPanel(loginCards, contentPanel, cards, mainPanel);
        contentPanel.add(administratorPanel, "administratorPanel");
        CoachPanel coachPanel = new CoachPanel(loginCards, contentPanel, cards, mainPanel);
        contentPanel.add(coachPanel, "coachPanel");

    }
}

class CustomerPanel extends JPanel
{
    private PersonalController controller = new PersonalController();
    public CustomerPanel(CardLayout loginCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CardLayout innerCards = new CardLayout();
        CustomerRightPanel personalRightPanel = new CustomerRightPanel(innerCards, controller, mainCards, mainPanel);
        this.add(personalRightPanel);

        CustomerLeftPanel customerLeftPanel = new CustomerLeftPanel(controller, loginCards, contentPanel, innerCards, personalRightPanel);
        this.add(customerLeftPanel);
        customerLeftPanel.setVisible(true);


    }
}

class SignInPanel extends JPanel
{
    public SignInPanel(final CardLayout cards, final JPanel contentPanel) {
        this.setLayout(null);

        JPanel loginPanel = new JPanel();

        DynamicText WelcomeBack = new DynamicText(Color.WHITE, Color.BLACK, "Welcome Back", (int)(0.2 * UIStyle.width),(int) (0.15 * UIStyle.height), (int) (0.25 * UIStyle.width), (int) (0.1 * UIStyle.height), UIStyle.BIG_FONT);
        loginPanel.add(WelcomeBack);

        DynamicText LogInTo = new DynamicText(Color.WHITE, Color.BLACK, "Login To Your Account", (int)(0.2 * UIStyle.width),(int) (0.2 * UIStyle.height), (int) (0.18 * UIStyle.width), (int) (0.1 * UIStyle.height), UIStyle.TINY_ARIAL_BOLD);
        loginPanel.add(LogInTo);

        int accountWidth = 240;
        final InputText account = new InputText((int)accountWidth, (int)(0.05 * UIStyle.height), 15, true, (int)(0.2 * UIStyle.width), (int) (0.3 * UIStyle.height),"Account", false);
        loginPanel.add(account);

        int passwordWidth = 240;
        Password password = new Password((int)(0.2 * UIStyle.width), (int) (0.4 * UIStyle.height),(int)accountWidth, (int)(0.05 * UIStyle.height));

        loginPanel.add(password);

        TextButton Login = new TextButton((int)(0.2 * UIStyle.width), (int) (0.5 * UIStyle.height), Color.decode("#6EE6B1"), Color.black, "Login", 120, (int)(0.05 * UIStyle.height), "normal", true);
        loginPanel.add(Login);


        String LoginPath = UIStyle.SignInPanel_Login;
        Picture LoginPic = new Picture(LoginPath, (int)UIStyle.width / 3, (int)UIStyle.height / 3);
        LoginPic.setBounds((int)(UIStyle.width/2.5), (int)(UIStyle.height/5), (int)(UIStyle.width/3), (int)(UIStyle.height/3));
        loginPanel.add(LoginPic);

        loginPanel.setBounds(100, 100, (int)UIStyle.width, (int)UIStyle.height);
        loginPanel.setLayout(null);
        loginPanel.setVisible(true);
        loginPanel.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        this.add(loginPanel);

        Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //cards.next(mainPanel);
                if(account.getText().equals("1"))
                    cards.show(contentPanel, "administratorPanel");
                else if(account.getText().equals("2"))
                    cards.show(contentPanel, "coachPanel");
                else {
                    cards.show(contentPanel, "customerPanel");
                    PersonalPanel.reminder.OK("Hello, Grey!");
                }

            }
        });


    }
}

class CustomerLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    public CustomerLeftPanel(PersonalController controller, final CardLayout loginCards, final JPanel contentPanel, CardLayout contentCards, JPanel rightPanel)
    {
        panelWidth = (int)(UIStyle.width * 0.24);
        panelHeight = (int)(UIStyle.height - UIStyle.barHeight);
        setBounds(0, 0, panelWidth, panelHeight);
        setBackground(UIStyle.COLOR_3);
        this.setLayout(null);

        int buttonStart = (int)(UIStyle.height / 2.2);
        int buttonHeight = (int)(UIStyle.height * 0.09);
        int buttonWidth = panelWidth;

        TextButton myMembership = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Membership", (int)(0.15 * panelWidth), buttonStart, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myBookedLive = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Booked LiveSession", (int)(0.15 * panelWidth), buttonStart + buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myVideoHistory = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Video History", (int)(0.15 * panelWidth), buttonStart + 2 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myFavorite = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Favorite Video", (int)(0.15 * panelWidth), buttonStart + 3 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");

        myMembership.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Membership");
            }
        });
        myBookedLive.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "BookedLive");
            }
        });
        myVideoHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "VideoHistory");
            }
        });
        myFavorite.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            contentCards.show(rightPanel, "Favorite");
        }
    });


        this.add(myMembership);
        this.add(myBookedLive);
        this.add(myVideoHistory);
        this.add(myFavorite);



        Picture circleIcon = new Picture(UIStyle.CustomerPanel_PersonIcon, (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);

        Customer cus = controller.getCusInfoByCusId("Cs15");
        DynamicText name = new DynamicText(UIStyle.COLOR_3, UIStyle.COLOR_4, cus.getName(), (int)(panelWidth / 2), (int)(panelHeight * 0.30), (int)(panelWidth / 2), (int)(panelHeight * 0.05), UIStyle.NORMAL_ARIAL_BOLD);
        this.add(name);


        int signOutWidth = buttonWidth;
        int signOutHeight = (int)(buttonHeight / 1.5);

        TextButton signOut = new TextButton(Color.decode("#DFDFDF"), Color.decode("#E04147"), "Sign Out",0, (int)(panelHeight - signOutHeight), signOutWidth, signOutHeight, UIStyle.NORMAL_FONT, true, "mid");// must be UIStyle.height -signOutHeight - 2, if UIStyle.height -signOutHeight, it will exceed the screen and whole component will disapper
        this.add(signOut);
        signOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //cards.next(mainPanel);
                loginCards.show(contentPanel, "signInPanel");
            }
        });


    }

}

class CustomerRightPanel extends JPanel
{
    public CustomerRightPanel(CardLayout innerCards, PersonalController controller, CardLayout mainCards, MainPanel mainPanel) {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);
        CustomerMembershipPanel membership = new CustomerMembershipPanel(controller.getCusInfoByCusId("Cs15"));
        this.add(membership, "Membership");
        LiveSession liveSessions[] = {LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample()};
        CustomerBookedLivePanel bookedLivePanel = new CustomerBookedLivePanel(liveSessions);
        this.add(bookedLivePanel, "BookedLive");
        Video videos[] = {Video.getSampleVideo(),Video.getSampleVideo(),Video.getSampleVideo(),Video.getSampleVideo(),Video.getSampleVideo()};
        VideoHistoryPanel videoHistoryPanel = new VideoHistoryPanel(videos, mainCards, mainPanel);
        this.add(videoHistoryPanel, "VideoHistory");
        FavoritePanel favoritePanel = new FavoritePanel(videos, mainCards, mainPanel);
        this.add(favoritePanel, "Favorite");

    }
}

class CustomerMembershipPanel extends JPanel
{
    public CustomerMembershipPanel(Customer cus)
    {
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.3 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);

        this.setLayout(null);

        DynamicText name_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Name", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText name_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, cus.getName());
        this.add(name_lower);
        this.add(name_upper);

        DynamicText gender_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Gender", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText gender_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, cus.getGender()+"");
        this.add(gender_upper);
        this.add(gender_lower);

        DynamicText email_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Email", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText email_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, cus.getEmail());
        this.add(email_upper);
        this.add(email_lower);

        DynamicText phone_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Phone Number", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText phone_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, cus.getPhoneNo());
        this.add(phone_upper);
        this.add(phone_lower);

        int stikerWidth = (int)(0.15*UIStyle.width);
        int stikerHeight = (int)(0.116 * UIStyle.height);


        Sticker balance = new Sticker(stikerWidth, stikerHeight
                , "Total Balance", ""+cus.getBalance(), buttonStartX, buttonStartY - 3*buttonHeight, UIStyle.CustomerMember_Money);
        this.add(balance);

        Sticker level = new Sticker(stikerWidth, stikerHeight
                , "User Level", cus.getMembershipLevel(), (int)(buttonStartX + stikerWidth * 1.3), buttonStartY - 3*buttonHeight, UIStyle.CustomerMember_Level);
        this.add(level);

        Sticker remainTime = new Sticker(stikerWidth, stikerHeight
                , "VIP remaining time", cus.getRemainTime(), (int)(buttonStartX + stikerWidth * 2.6), buttonStartY - 3*buttonHeight, UIStyle.CustomerMember_Time);
        this.add(remainTime);

        TextButton save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX + 1.5 * buttonWidth), buttonStartY + 7 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);

    }
}
class CustomerBookedLivePanel extends JPanel
{
    private int pageMax;
    public CustomerBookedLivePanel(LiveSession[] liveSessions)
    {
        pageMax = 10;
        String expiredContent[] = {"Expired", "Yes", "No"};
        FilterBox expired = new FilterBox(50, expiredContent, "light");
        this.add(expired);
        String[] categoryFilterString = {"Category", "Bicycle Training", "HITT", "Flexibility", "Yoga", "Strength", "Weight Loss"};
        FilterBox categoryFilter = new FilterBox(10, categoryFilterString, "light");
        this.add(categoryFilter);



        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        TextButton applyChange = new TextButton(panelWidth / 2, 110, UIStyle.BLUE_BUTTRESS, Color.white, "Apply Change", 150, 25, "tiny", true);
        this.add(applyChange);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = 0; i < 3; i++) {
            LivePanel test = new LivePanel(liveSessions[i], 0, 150 * i + 130, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(panelHeight - pagesHeight+7), pagesWidth,pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });

    }

}
class VideoHistoryPanel extends JPanel
{
    private int pageMax;
    public VideoHistoryPanel(Video[] videos, CardLayout cards, MainPanel mainPanel)
    {
        pageMax = 10;

        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);

        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = 0; i < 3; i++) {
            VideoPanel test = new VideoPanel(videos[i], 0, 150 * i, mainPanel, cards, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(panelHeight - pagesHeight+7), pagesWidth,pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });

    }

}
class FavoritePanel extends JPanel
{
    private int pageMax;
    public FavoritePanel(Video[] videos, CardLayout cards, MainPanel mainPanel)
    {
        pageMax = 10;

        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);

        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = 0; i < 3; i++) {
            VideoPanel test = new VideoPanel(videos[i], 0, 150 * i, mainPanel, cards, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(panelHeight - pagesHeight+7), pagesWidth,pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });

    }

}

class AdministratorPanel extends JPanel
{
    PersonalController controller = new PersonalController();
    public AdministratorPanel(CardLayout loginCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CardLayout innerCards = new CardLayout();
        AdministratorRightPanel administratorRightPanel = new AdministratorRightPanel(innerCards, controller, contentPanel, mainCards, mainPanel);
        this.add(administratorRightPanel);
        administratorRightPanel.setVisible(true);
        AdministratorLeftPanel administratorLeftPanel = new AdministratorLeftPanel(controller, loginCards, contentPanel, innerCards, administratorRightPanel);
        this.add(administratorLeftPanel);
        administratorLeftPanel.setVisible(true);


//        CardLayout innerCards = new CardLayout();
//        Personal_RightPanel personalRightPanel = new Personal_RightPanel(innerCards, controller);
//        this.add(personalRightPanel);
    }
}

class AdministratorLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    public AdministratorLeftPanel(PersonalController controller, final CardLayout loginCards, final JPanel contentPanel, CardLayout contentCards, JPanel rightPanel)
    {
        panelWidth = (int)(UIStyle.width * 0.24);
        panelHeight = (int)(UIStyle.height - UIStyle.barHeight);
        setBounds(0, 0, panelWidth, panelHeight);
        setBackground(UIStyle.COLOR_3);
        this.setLayout(null);

        int buttonStart = (int)(UIStyle.height / 2.2);
        int buttonHeight = (int)(UIStyle.height * 0.09);
        int buttonWidth = panelWidth;

        TextButton Membership = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "Membership Management", (int)(0.15 * panelWidth), buttonStart, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton Videos = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "Video Management", (int)(0.15 * panelWidth), buttonStart + buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton LiveSession = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "Live Session Management", (int)(0.15 * panelWidth), buttonStart + 2 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");

        Membership.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Membership");
            }
        });
        Videos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Video");
            }
        });

        this.add(Membership);
        this.add(Videos);
        this.add(LiveSession);



        Picture circleIcon = new Picture(UIStyle.Administrator_PersonIcon, (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);


        DynamicText name = new DynamicText(UIStyle.COLOR_3, UIStyle.COLOR_4, "Kayne", (int)(panelWidth / 2), (int)(panelHeight * 0.30), (int)(panelWidth / 2), (int)(panelHeight * 0.05), UIStyle.NORMAL_ARIAL_BOLD);
        this.add(name);


        int signOutWidth = buttonWidth;
        int signOutHeight = (int)(buttonHeight / 1.5);

        TextButton signOut = new TextButton(Color.decode("#DFDFDF"), Color.decode("#E04147"), "Sign Out",0, (int)(UIStyle.height - 2.6*signOutHeight ), signOutWidth, signOutHeight, UIStyle.NORMAL_FONT, true, "mid");// must be UIStyle.height -signOutHeight - 2, if UIStyle.height -signOutHeight, it will exceed the screen and whole component will disapper
        this.add(signOut);
        signOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //cards.next(mainPanel);
                loginCards.show(contentPanel, "signInPanel");
            }
        });


    }

}

class AdministratorRightPanel extends JPanel
{
    public AdministratorRightPanel(CardLayout innerCards, PersonalController controller, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel) {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);


        AdministratorMembershipPanel membership = new AdministratorMembershipPanel(controller);
        this.add(membership, "Membership");

        Video videos[] = {Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo()};
        AdministratorVideoManagement videoManagement = new AdministratorVideoManagement(videos, mainCards, mainPanel);
        this.add(videoManagement, "Video");
    }
}

class AdministratorMembershipPanel extends JPanel
{

    public AdministratorMembershipPanel(PersonalController controller)
    {

        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);

        this.setLayout(null);

        int itemsPerPage = 12;

        String[] column = {"CusId", "Name", "Age", "Gender", "PhoneNo", "Email", "MembershipLevel", "Balance", "Points", "DateOfBirth", "RemainTime"};
        String[][] values = new String[itemsPerPage][column.length];
        int numOfCustomer = controller.getNumOfAllCustomers();

        //ArrayList<Customer> cusList = new ArrayList<>(controller.getCustomerByPage(0, numOfCustomer - 1));
        Customer[] cusList = {Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample(), Customer.getSample()};

        int cnt = 0;
        for (Customer i : cusList)
        {
            for(int j = 0; j < column.length; j++)
            {
                try {

                    if(column[j].equals("Age") || column[j].equals("Balance") || column[j].equals("Points"))
                        values[cnt][j] = ""+(int)(i.getClass().getMethod("get" + column[j]).invoke(i));
                    else if(column[j].equals("DateOfBirth"))
                        values[cnt][j] = ((Date)(i.getClass().getMethod("get" + column[j]).invoke(i))).toString();
                    else if(column[j].equals("Gender"))
                        values[cnt][j] = ((char)(i.getClass().getMethod("get" + column[j]).invoke(i))) + "";
                    else
                        values[cnt][j] = (String)(i.getClass().getMethod("get" + column[j]).invoke(i));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            cnt++;
        }
        int pagesWidth = 100;
        int pagesHeight = 50;
        TableList customerTable = new TableList(column, values, Color.BLACK, Color.WHITE, UIStyle.BLUE_SHALLOW, 0, UIStyle.barHeight * 3, getWidth(), (int)(UIStyle.height - UIStyle.barHeight - pagesHeight * 5));

        this.add(customerTable);
        int pageMax = 10;

        JSlider pages = new JSlider(1, pageMax, 1);


        pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(panelHeight - pagesHeight+7), pagesWidth,pagesHeight);
        //pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), 0, pagesWidth,pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });

        String[] levelContent = {"Level", "LV1", "LV2", "LV3", "LV4", "LV5"};
        FilterBox level = new FilterBox(20, levelContent, "light");
        this.add(level);

        String[] genderContent = {"Gender", "Male", "Female"};
        FilterBox gender = new FilterBox(60, genderContent, "light");
        this.add(gender);

        String[] sortContent = {"Sort By", "Age", "Level", "Balance", "Credit"};
        FilterBox sort = new FilterBox(100, sortContent, "light");
        this.add(sort);

        TextButton applyChange = new TextButton(panelWidth / 2, 160, UIStyle.BLUE_BUTTRESS, Color.white, "Apply Change", 150, 25, "tiny", true);
        this.add(applyChange);


    }

}
class AdministratorVideoManagement extends JPanel
{
    private int pageMax;
    public AdministratorVideoManagement(Video[] videos, CardLayout cards, MainPanel mainPanel)
    {

        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);

        InputText searchBar = new InputText(500, 50, 40, true, (int)(panelWidth/2), 50, "Search", true);
        this.add(searchBar);

        int startFilter = 85;
        String[] categoryFilterString = {"Category", "Bicycle Training", "HITT", "Flexibility", "Yoga", "Strength", "Weight Loss"};
        FilterBox categoryFilter = new FilterBox(startFilter, categoryFilterString, "light");
        this.add(categoryFilter);

        String[] sortString = {"Sort", "Like", "Rating", "View"};
        FilterBox sortFilter = new FilterBox(startFilter + 40, sortString, "light");
        this.add(sortFilter);

        pageMax = 10;


        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = 0; i < 3; i++) {
            VideoPanel test = new VideoPanel(videos[i], 0, 150 * i + 160, mainPanel, cards, "manage");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(panelHeight - pagesHeight+7), pagesWidth,pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
    }
}
class CoachPanel extends JPanel
{
    private PersonalController controller = new PersonalController();
    public CoachPanel(CardLayout loginCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CardLayout innerCards = new CardLayout();
        CoachRightPanel coachRightPanel = new CoachRightPanel(innerCards, contentPanel, mainCards, mainPanel);

        CoachLeftPanel coachLeftPanel = new CoachLeftPanel(loginCards, contentPanel, innerCards, coachRightPanel);
        this.add(coachLeftPanel);
        this.add(coachRightPanel);
//        CustomerLeftPanel customerLeftPanel = new CustomerLeftPanel(loginCards, contentPanel, innerCards, personalRightPanel);
//        this.add(customerLeftPanel);
        //customerLeftPanel.setVisible(true);


    }
}

class CoachLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    public CoachLeftPanel(final CardLayout loginCards, final JPanel contentPanel, CardLayout contentCards, JPanel rightPanel)
    {
        panelWidth = (int)(UIStyle.width * 0.24);
        panelHeight = (int)(UIStyle.height - UIStyle.barHeight);
        setBounds(0, 0, panelWidth, panelHeight);
        setBackground(UIStyle.COLOR_3);
        this.setLayout(null);

        int buttonStart = (int)(UIStyle.height / 2.2);
        int buttonHeight = (int)(UIStyle.height * 0.09);
        int buttonWidth = panelWidth;

        TextButton myMembership = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Membership", (int)(0.15 * panelWidth), buttonStart, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myLive = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Teaching LiveSession", (int)(0.15 * panelWidth), buttonStart + buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");

        myMembership.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Membership");
            }
        });
        myLive.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Live");
            }
        });




        this.add(myMembership);
        this.add(myLive);
        Coach coach = Coach.getSample();


        Picture circleIcon = new Picture(UIStyle.CustomerPanel_PersonIcon, (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);

        DynamicText name = new DynamicText(UIStyle.COLOR_3, UIStyle.COLOR_4, coach.getName(), (int)(panelWidth / 2), (int)(panelHeight * 0.30), (int)(panelWidth / 2), (int)(panelHeight * 0.05), UIStyle.NORMAL_ARIAL_BOLD);
        this.add(name);


        int signOutWidth = buttonWidth;
        int signOutHeight = (int)(buttonHeight / 1.5);

        TextButton signOut = new TextButton(Color.decode("#DFDFDF"), Color.decode("#E04147"), "Sign Out",0, (int)(panelHeight - signOutHeight), signOutWidth, signOutHeight, UIStyle.NORMAL_FONT, true, "mid");// must be UIStyle.height -signOutHeight - 2, if UIStyle.height -signOutHeight, it will exceed the screen and whole component will disapper
        this.add(signOut);
        signOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //cards.next(mainPanel);
                loginCards.show(contentPanel, "signInPanel");
            }
        });
    }
}
class CoachRightPanel extends JPanel
{
    public CoachRightPanel(CardLayout innerCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel) {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);
        Coach coach = Coach.getSample();
        CoachMembershipPanel membership = new CoachMembershipPanel(coach);
        this.add(membership, "Membership");
        LiveSession liveSession[] = {LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample()};
        CoachLivePanel coachLivePanel = new CoachLivePanel(liveSession);
        this.add(coachLivePanel, "Live");

//        AdministratorMembershipPanel membership = new AdministratorMembershipPanel(controller);
//        this.add(membership, "Membership");
//
//        Video videos[] = {Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo()};
//        AdministratorVideoManagement videoManagement = new AdministratorVideoManagement(videos, mainCards, mainPanel);
//        this.add(videoManagement, "Video");
    }
}
class CoachMembershipPanel extends JPanel
{
    public CoachMembershipPanel(Coach coach)
    {
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.3 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);

        this.setLayout(null);

        DynamicText name_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Name", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText name_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, coach.getName());
        this.add(name_lower);
        this.add(name_upper);

        DynamicText gender_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Gender", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText gender_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, coach.getGender()+"");
        this.add(gender_upper);
        this.add(gender_lower);

        DynamicText email_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Email", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText email_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, coach.getEmail());
        this.add(email_upper);
        this.add(email_lower);

        DynamicText phone_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Phone Number", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText phone_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, coach.getPhoneNo());
        this.add(phone_upper);
        this.add(phone_lower);


        TextButton save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX + 1.5 * buttonWidth), buttonStartY + 7 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);

    }
}
class CoachLivePanel extends JPanel
{
    private int pageMax;
    public CoachLivePanel(LiveSession[] liveSessions)
    {

        pageMax = 10;
        String expiredContent[] = {"Expired", "Yes", "No"};
        FilterBox expired = new FilterBox(50, expiredContent, "light");
        this.add(expired);
        String[] categoryFilterString = {"Category", "Bicycle Training", "HITT", "Flexibility", "Yoga", "Strength", "Weight Loss"};
        FilterBox categoryFilter = new FilterBox(10, categoryFilterString, "light");
        this.add(categoryFilter);



        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        TextButton applyChange = new TextButton(panelWidth / 2, 110, UIStyle.BLUE_BUTTRESS, Color.white, "Apply Change", 150, 25, "tiny", true);
        this.add(applyChange);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = 0; i < 3; i++) {
            LivePanel test = new LivePanel(liveSessions[i], 0, 150 * i + 130, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(panelHeight - pagesHeight+7), pagesWidth,pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });

    }
}



