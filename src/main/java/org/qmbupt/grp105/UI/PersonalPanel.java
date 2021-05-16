package org.qmbupt.grp105.UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Controller.MailController;
import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.*;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

public class PersonalPanel extends JPanel
{
    public static MyReminder reminder;
    private CustomerPanel customerPanel;
    private CoachPanel coachPanel;
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
        SignInPanel signInPanel = new SignInPanel(loginCards, contentPanel, this);
        contentPanel.add(signInPanel, "signInPanel");
        customerPanel = new CustomerPanel(loginCards, contentPanel, cards, mainPanel);
        contentPanel.add(customerPanel, "customerPanel");
        AdministratorPanel administratorPanel = new AdministratorPanel(loginCards, contentPanel, cards, mainPanel);
        contentPanel.add(administratorPanel, "administratorPanel");
        coachPanel = new CoachPanel(loginCards, contentPanel, cards, mainPanel);
        contentPanel.add(coachPanel, "coachPanel");

    }
    public void updateCus(String id)
    {
        customerPanel.updateCus(id);
    }
    public void updateCoach(String id)
    {
        coachPanel.updateCoach(id);
    }
}

class CustomerPanel extends JPanel
{

    CustomerLeftPanel customerLeftPanel;
    private CustomerRightPanel personalRightPanel;
    public CustomerPanel(CardLayout loginCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CardLayout innerCards = new CardLayout();
        personalRightPanel = new CustomerRightPanel(innerCards, mainCards, mainPanel);
        this.add(personalRightPanel);

        customerLeftPanel = new CustomerLeftPanel(loginCards, contentPanel, innerCards, personalRightPanel);
        this.add(customerLeftPanel);
        customerLeftPanel.setVisible(true);


    }
    public void updateCus(String id)
    {
        personalRightPanel.update(id);
        customerLeftPanel.updateInfo();

    }
}

class SignInPanel extends JPanel
{
    public SignInPanel(final CardLayout cards, final JPanel contentPanel, PersonalPanel personalPanel) {
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

        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginVerify(account, password, cards, contentPanel, personalPanel);
            }
        });

        Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginVerify(account, password, cards, contentPanel, personalPanel);
            }
        });
    }
    public void loginVerify(InputText account, Password password, CardLayout cards, JPanel contentPanel, PersonalPanel personalPanel)
    {
        //cards.next(mainPanel);
        String acc = account.getText();
        String pass = password.getText();
        PersonalController controller = PersonalController.getController();
        int status = controller.check(acc, pass);

        if (acc.equals("root")) {
            cards.show(contentPanel, "administratorPanel");
        } else if (status == 2) {
            String id = controller.getIdByEmail(acc);
            Coach co = controller.getCoachInfoById(id);
            LoginToken.setId(id);
            LoginToken.setType("Coach");
            personalPanel.updateCoach(id);

            cards.show(contentPanel, "coachPanel");
            PersonalPanel.reminder.OK("Hello," + co.getName()+ "!");
        } else if(status == 3)
        {
            PersonalPanel.reminder.WRONG("Email doesn't exist");
        }
        else if(status == 4)
        {
            PersonalPanel.reminder.WRONG("Password Wrong!");
        }
        else {
            String id = controller.getIdByEmail(acc);
            Customer cus = controller.getCusInfoByCusId(id);
            LoginToken.setId(id);
            LoginToken.setType("Customer");
            personalPanel.updateCus(id);
            cards.show(contentPanel, "customerPanel");
            PersonalPanel.reminder.OK("Hello," + cus.getName()+ "!");
        }
    }
}

class CustomerLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    Picture circleIcon;
    DynamicText name;
    public CustomerLeftPanel(final CardLayout loginCards, final JPanel contentPanel, CardLayout contentCards, JPanel rightPanel)
    {
        panelWidth = (int)(UIStyle.width * 0.24);
        panelHeight = (int)(UIStyle.height - UIStyle.barHeight);
        setBounds(0, 0, panelWidth, panelHeight);
        setBackground(UIStyle.COLOR_3);
        this.setLayout(null);

        int buttonStart = (int)(UIStyle.height / 3.0);
        int buttonHeight = (int)(UIStyle.height * 0.09);
        int buttonWidth = panelWidth;

        TextButton myMembership = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Membership", (int)(0.15 * panelWidth), buttonStart, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myBookedLive = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Booked LiveSession", (int)(0.15 * panelWidth), buttonStart + buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myVideoHistory = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Video History", (int)(0.15 * panelWidth), buttonStart + 2 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myFavorite = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Favorite Video", (int)(0.15 * panelWidth), buttonStart + 3 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myEmails = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Emails", (int)(0.15 * panelWidth), buttonStart + 4 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");

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

        myEmails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Emails");
            }
        });

        this.add(myEmails);
        this.add(myMembership);
        this.add(myBookedLive);
        this.add(myVideoHistory);
        this.add(myFavorite);
        PersonalController controller = PersonalController.getController();



        circleIcon = new Picture(UIStyle.CustomerPanel_PersonIcon, (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);

        Customer cus = controller.getCusInfoByCusId("cs1");
        name = new DynamicText(UIStyle.COLOR_3, UIStyle.COLOR_4, cus.getName(), (int)(panelWidth / 2), (int)(panelHeight * 0.30), (int)(panelWidth / 2), (int)(panelHeight * 0.05), UIStyle.NORMAL_ARIAL_BOLD);
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
    public void updateInfo()
    {
        Customer cus = PersonalController.getController().getCusInfoByCusId(LoginToken.getId());
        this.remove(name);
        name = new DynamicText(UIStyle.COLOR_3, UIStyle.COLOR_4, cus.getName(), (int)(panelWidth / 2), (int)(panelHeight * 0.30), (int)(panelWidth / 2), (int)(panelHeight * 0.05), UIStyle.NORMAL_ARIAL_BOLD);
        this.add(name);

        this.remove(circleIcon);

        String picPath = UIStyle.class.getClassLoader().getResource(cus.getCusId() + ".png").getPath();
        circleIcon = new Picture(picPath, (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);

    }

}

class CustomerRightPanel extends JPanel
{
    private CardLayout innerCards;
    private CardLayout mainCards;
    private MainPanel mainPanel;
    CustomerMembershipPanel membership;
    CustomerBookedLivePanel bookedLivePanel;
    VideoHistoryPanel videoHistoryPanel;
    PersonalController controller;
    FavoritePanel favoritePanel;
    EmailPanel emailPanel;
    public CustomerRightPanel(CardLayout innerCards, CardLayout mainCards, MainPanel mainPanel) {
        this.innerCards = innerCards;
        this.mainCards = mainCards;
        this.mainPanel = mainPanel;
        controller = PersonalController.getController();
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);

    }
    public void update(String id)
    {
        if(membership != null)
            this.remove(membership);
        membership = new CustomerMembershipPanel(controller.getCusInfoByCusId(id));
        this.add(membership, "Membership");

        if(bookedLivePanel != null)
            this.remove(bookedLivePanel);
        ArrayList<LiveSession> liveSessions = LiveController.getController().getLiveSessionByCusId(id);
        bookedLivePanel = new CustomerBookedLivePanel(liveSessions);
        this.add(bookedLivePanel, "BookedLive");

        if(videoHistoryPanel != null)
            this.remove(videoHistoryPanel);
        ArrayList<Video> videos = VideoController.getController().getVideosByCusId(id);
        videoHistoryPanel = new VideoHistoryPanel(videos, mainCards, mainPanel);
        this.add(videoHistoryPanel, "VideoHistory");

        if(favoritePanel != null)
            this.remove(favoritePanel);
        ArrayList<Video> favoritesVideos = PersonalController.getController().getFavouriteVideoByCusId(LoginToken.getId());
        favoritePanel = new FavoritePanel(favoritesVideos, mainCards, mainPanel);
        this.add(favoritePanel, "Favorite");

        if(emailPanel != null)
            this.remove(emailPanel);

        ArrayList<Mail> emails = MailController.getController().getMailsById(LoginToken.getId());

        emailPanel = new EmailPanel(emails, mainCards, mainPanel);
        this.add(emailPanel, "Emails");


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

        int stickerWidth = (int)(0.15*UIStyle.width);
        int stickerHeight = (int)(0.116 * UIStyle.height);


        Sticker balance = new Sticker(stickerWidth, stickerHeight
                , "Total Balance", ""+cus.getBalance(), buttonStartX, buttonStartY - 3*buttonHeight, UIStyle.CustomerMember_Money);
        this.add(balance);

        Sticker level = new Sticker(stickerWidth, stickerHeight
                , "User Level", cus.getMembershipLevel(), (int)(buttonStartX + stickerWidth * 1.3), buttonStartY - 3*buttonHeight, UIStyle.CustomerMember_Level);
        this.add(level);

        Sticker expireTime = new Sticker(stickerWidth, stickerHeight
                , "VIP Expire time", cus.getExpiredTime(), (int)(buttonStartX + stickerWidth * 2.6), buttonStartY - 3*buttonHeight, UIStyle.CustomerMember_Time);
        this.add(expireTime);

        TextButton save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX + 1.5 * buttonWidth), buttonStartY + 7 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cus.setName(name_lower.getText());
                cus.setGender(gender_lower.getText().charAt(0));
                cus.setEmail(email_lower.getText());
                cus.setPhoneNo(phone_lower.getText());
                PersonalController.getController().updateCustomer(cus);
                PersonalPanel.reminder.OK("Save Success!");
            }
        });

    }
}
class EmailOnePage extends JPanel
{

    public EmailOnePage(ArrayList<Mail> emails, CardLayout cards, MainPanel mainPanel, CardLayout innerCards, JPanel contentPanel, int page, int pageMax)
    {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight - 160);

        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = (page - 1) * 3; i < emails.size() && i < page * 3; i++) {
            EmailEntry test = new EmailEntry(emails.get(i), 0, 150 * (i % 3), cards, mainPanel);
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax + 1, page);

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
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}
class EmailPanel extends JPanel
{
    private int pageMax;
    ArrayList<EmailOnePage> resultPanels = new ArrayList<>();
    public EmailPanel(ArrayList<Mail> emails, CardLayout cards, MainPanel mainPanel)
    {
        pageMax = (emails.size() -1) / 3;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        CardLayout innerCards = new CardLayout();
        this.setLayout(null);
        this.setBackground(Color.white);

        JPanel contentPanel = new JPanel();
        contentPanel.setVisible(true);
        contentPanel.setBounds(0, 160, panelWidth, panelHeight - 160);
        contentPanel.setLayout(innerCards);
        this.add(contentPanel);

        for(EmailOnePage i : resultPanels)
        {
            contentPanel.remove(i);
        }
        resultPanels.clear();

        for(int i = 0; i <= pageMax; i++)
        {
            resultPanels.add(new EmailOnePage(emails, cards, mainPanel, innerCards, contentPanel, i + 1, pageMax));
            contentPanel.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(contentPanel);


        TextButton send = new TextButton((int)(panelWidth / 2 + 200), 100, UIStyle.BLUE_BUTTRESS, Color.white, "Send Email",  200, 40, "normal",true);
        this.add(send);
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cards.show(mainPanel, "tempContentPanel");
                mainPanel.setTempContent("emailWrite", new Mail("", "", new Date(), ""));
            }
        });
    }

}
class CustomerBookedLivePanel extends JPanel
{
    private int pageMax;
    private ArrayList<CustomerBookedLiveOnePage> resultPanels = new ArrayList<>();
    public CustomerBookedLivePanel(ArrayList<LiveSession> liveSessions)
    {
        pageMax = liveSessions.size() / 4;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        CardLayout innerCards = new CardLayout();
        this.setLayout(innerCards);

        for(CustomerBookedLiveOnePage i : resultPanels)
        {
            this.remove(i);
        }
        resultPanels.clear();
        for(int i = 0; i <= pageMax; i++)
        {
            resultPanels.add(new CustomerBookedLiveOnePage(liveSessions, innerCards, this, i + 1, pageMax));
            this.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(this);


    }
}
class CustomerBookedLiveOnePage extends JPanel
{
    public CustomerBookedLiveOnePage(ArrayList<LiveSession> liveSessions, CardLayout innerCards, JPanel contentPanel, int page, int pageMax)
    {
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

        for(int i = (page - 1) * 3; i < liveSessions.size() && i < page * 3; i++) {
            LivePanel test = new LivePanel(liveSessions.get(i), 0, 150 * (i % 3) + 130, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax + 1, page);

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
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}
class VideoHistoryPanel extends JPanel
{
    private int pageMax;
    ArrayList<VideoHistoryOnePagePanel> resultPanels = new ArrayList<>();

    public VideoHistoryPanel(ArrayList<Video> videos, CardLayout cards, MainPanel mainPanel)
    {
        pageMax = (videos.size() -1) / 4;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        CardLayout innerCards = new CardLayout();
        this.setLayout(innerCards);

        for(VideoHistoryOnePagePanel i : resultPanels)
        {
            this.remove(i);
        }
        resultPanels.clear();
        for(int i = 0; i <= pageMax; i++)
        {
            resultPanels.add(new VideoHistoryOnePagePanel(videos, cards, mainPanel, innerCards, this, i + 1, pageMax));
            this.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(this);

    }
}
class VideoHistoryOnePagePanel extends JPanel
{
    public VideoHistoryOnePagePanel(ArrayList<Video> videos, CardLayout cards, MainPanel mainPanel, CardLayout innerCards, JPanel contentPanel, int page, int pageMax)
    {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);

        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = (page - 1) * 4; i < videos.size() && i < page * 4; i++) {
            VideoPanel test = new VideoPanel(videos.get(i), 0, 150 * (i % 4), mainPanel, cards, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax + 1, page);

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
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}
class FavoritePanel extends JPanel
{
    private int pageMax;
    ArrayList<FavoriteOnePage> resultPanels = new ArrayList<>();

    public FavoritePanel(ArrayList<Video> videos, CardLayout cards, MainPanel mainPanel)
    {
        pageMax = videos.size() / 4;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        CardLayout innerCards = new CardLayout();
        this.setLayout(innerCards);

        for(FavoriteOnePage i : resultPanels)
        {
            this.remove(i);
        }
        resultPanels.clear();
        for(int i = 0; i <= pageMax; i++)
        {
            resultPanels.add(new FavoriteOnePage(videos, cards, mainPanel, innerCards, this, i + 1, pageMax));
            this.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(this);



    }

}
class FavoriteOnePage extends JPanel
{
    public FavoriteOnePage(ArrayList<Video> videos, CardLayout cards, MainPanel mainPanel, CardLayout innerCards, JPanel contentPanel, int page, int pageMax)
    {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);

        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = (page - 1) * 4; i < videos.size() && i < page * 4; i++) {
            VideoPanel test = new VideoPanel(videos.get(i), 0, 150 * (i % 4), mainPanel, cards, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax + 1, page);

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
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}

class AdministratorPanel extends JPanel
{

    public AdministratorPanel(CardLayout loginCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CardLayout innerCards = new CardLayout();
        AdministratorRightPanel administratorRightPanel = new AdministratorRightPanel(innerCards, contentPanel, mainCards, mainPanel);
        this.add(administratorRightPanel);
        administratorRightPanel.setVisible(true);
        AdministratorLeftPanel administratorLeftPanel = new AdministratorLeftPanel(loginCards, contentPanel, innerCards, administratorRightPanel);
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
    public AdministratorLeftPanel(final CardLayout loginCards, final JPanel contentPanel, CardLayout contentCards, JPanel rightPanel)
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
    public AdministratorRightPanel(CardLayout innerCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel) {
        PersonalController controller = PersonalController.getController();
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);



        AdministratorMembershipPanel membership = new AdministratorMembershipPanel();
        this.add(membership, "Membership");

        ArrayList<Video> videos = VideoController.getController().getAllVideos();
        AdministratorVideoManagement videoManagement = new AdministratorVideoManagement(videos, mainCards, mainPanel);
        this.add(videoManagement, "Video");
    }
}

class AdministratorMembershipPanel extends JPanel
{

    private int pageMax;
    private ArrayList<AdministratorMembershipOnePage> resultPanels = new ArrayList<>();
    private ArrayList<Customer> customersData = new ArrayList<>();
    private JPanel contentPanel = new JPanel();
    private CardLayout innerCards;
    private FilterBox level;
    private FilterBox gender;
    private FilterBox sort;
    private String[] levelContent = {"Level", "LV1", "LV2", "LV3", "LV4", "LV5"};
    private String[] genderContent = {"Gender", "Male", "Female"};
    private String[] sortContent = {"Sort By", "Age", "Level", "Balance", "Credit"};
    private InputText search;


    public AdministratorMembershipPanel()
    {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        this.setLayout(null);

        setBounds(0, 0, panelWidth, panelHeight);

        this.setBackground(Color.white);


        level = new FilterBox(20, levelContent, "light");
        this.add(level);

        gender = new FilterBox(60, genderContent, "light", true);
        this.add(gender);

        sort = new FilterBox(100, sortContent, "light", true);
        this.add(sort);

        search = new InputText(300, 25, 30, true, panelWidth / 2 - 200, 160, "Search", true);
        this.add(search);
        TextButton applyChange = new TextButton(panelWidth / 2 + 200, 160, UIStyle.BLUE_BUTTRESS, Color.white, "Apply Change", 150, 25, "tiny", true);
        this.add(applyChange);
        applyChange.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                updateRes();
            }
        });

        int contentStart = 185;

        innerCards = new CardLayout();
        contentPanel.setLayout(innerCards);
        contentPanel.setBounds(0, contentStart, panelWidth, (int)(UIStyle.height - UIStyle.barHeight - contentStart));
        contentPanel.setVisible(true);
        this.add(contentPanel);
        updateRes();

    }
    public void updateRes()
    {
        customersData = PersonalController.getController().getAllCustomer();
        String keyword = search.getText();
        if(keyword.equals("") || keyword.equals("Search"))
            keyword = null;
        customersData = PersonalController.getController().filterByKeyword(customersData, keyword);
        boolean states[] = level.getStates();
        ArrayList<String> levelKey = new ArrayList<>();
        int cnt = 1;
        for(boolean i : states)
        {
            if(i)
            {
                levelKey.add(levelContent[cnt].substring(2));
            }
            cnt++;
        }
        customersData = PersonalController.getController().filterByCusLevel(customersData, levelKey);
        char genderKey = 'n';
        states = gender.getStates();
        cnt = 1;
        for(boolean i : states)
        {
            if(i)
            {
                genderKey = genderContent[cnt].charAt(0);
            }
            cnt++;
        }
        customersData = PersonalController.getController().filterByGender(customersData, genderKey);
        for(AdministratorMembershipOnePage i : resultPanels)
        {
            contentPanel.remove(i);
        }
        resultPanels.clear();
        pageMax = customersData.size() / 12;

        for(int i = 0; i <= pageMax; i++)
        {
            resultPanels.add(new AdministratorMembershipOnePage(customersData, innerCards, contentPanel, i + 1, pageMax));
            contentPanel.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(contentPanel);
    }

}
class AdministratorMembershipOnePage extends JPanel
{
    public AdministratorMembershipOnePage(ArrayList<Customer> cusList, CardLayout innerCards, JPanel contentPanel, int page, int pageMax)
    {
        int contentStart = 185;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int)(UIStyle.height - UIStyle.barHeight - contentStart);
        setBounds(0, 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);

        this.setLayout(null);

        int itemsPerPage = 12;

        String[] column = {"CusId", "Name", "Age", "Gender", "PhoneNo", "Email", "MembershipLevel", "Balance", "Points", "DateOfBirth", "ExpiredTime"};
        String[][] values = new String[itemsPerPage][column.length];
        String[] titles = {"ID", "Name", "Age", "Gender", "Phone", "Email", "Level", "Balance", "Points", "DoB", "Expire"};

        //int numOfCustomer = controller.getNumOfAllCustomers();
        int index = 0;
        int titleWidth = panelWidth / 11;
        for(String i : titles)
        {
            this.add(new DynamicText(index * titleWidth, 10, "mid", Color.white, UIStyle.GRAY_BUTTRESS, i, titleWidth, 20, UIStyle.SMALL_FONT));
            index++;
        }

        //ArrayList<Customer> cusList = new ArrayList<>(controller.getCustomerByPage(0, numOfCustomer - 1));

        int cnt = 0;
        for (int i = (page - 1) * itemsPerPage ; i < page * itemsPerPage && i < cusList.size(); i++)
        {
            for(int j = 0; j < column.length; j++)
            {
                try {
                    if(column[j].equals("Age") || column[j].equals("Balance") || column[j].equals("Points"))
                        values[cnt][j] = ""+(int)(cusList.get(i).getClass().getMethod("get" + column[j]).invoke(cusList.get(i)));
                    else if(column[j].equals("DateOfBirth"))
                        values[cnt][j] = ((Date)(cusList.get(i).getClass().getMethod("get" + column[j]).invoke(cusList.get(i)))).toString();
                    else if(column[j].equals("Gender"))
                        values[cnt][j] = ((char)(cusList.get(i).getClass().getMethod("get" + column[j]).invoke(cusList.get(i)))) + "";
                    else
                        values[cnt][j] = (String)(cusList.get(i).getClass().getMethod("get" + column[j]).invoke(cusList.get(i)));
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
        TableList customerTable = new TableList(column, values, Color.BLACK, Color.WHITE, UIStyle.BLUE_SHALLOW, 0, 35, getWidth(), panelHeight - pagesHeight - 15);

        this.add(customerTable);

        JSlider pages = new JSlider(1, pageMax + 1, page);


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
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });

    }
}
class AdministratorVideoManagement extends JPanel
{
    private int pageMax;
    ArrayList<AdministratorVideoOnePage> resultPanels = new ArrayList<>();

    public AdministratorVideoManagement(ArrayList<Video> videos, CardLayout cards, MainPanel mainPanel)
    {
        pageMax = (videos.size() -1) / 3;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        CardLayout innerCards = new CardLayout();
        this.setLayout(null);
        this.setBackground(Color.white);

        JPanel contentPanel = new JPanel();
        contentPanel.setVisible(true);
        contentPanel.setBounds(0, 160, panelWidth, panelHeight - 160);
        contentPanel.setLayout(innerCards);
        this.add(contentPanel);

        for(AdministratorVideoOnePage i : resultPanels)
        {
            contentPanel.remove(i);
        }
        resultPanels.clear();

        for(int i = 0; i <= pageMax; i++)
        {
            resultPanels.add(new AdministratorVideoOnePage(videos, cards, mainPanel, innerCards, contentPanel, i + 1, pageMax));
            contentPanel.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(contentPanel);


        InputText searchBar = new InputText(500, 50, 40, true, (int)(panelWidth/2), 40, "Search", true);
        this.add(searchBar);

        int startFilter = 75;
        String[] categoryFilterString = {"Category", "Bicycle Training", "HITT", "Flexibility", "Yoga", "Strength", "Weight Loss"};
        FilterBox categoryFilter = new FilterBox(startFilter, categoryFilterString, "light");
        this.add(categoryFilter);

        String[] sortString = {"Sort", "Like", "Rating", "View"};
        FilterBox sortFilter = new FilterBox(startFilter + 40, sortString, "light", true);
        this.add(sortFilter);

        TextButton send = new TextButton((int)(panelWidth / 2 + 320), 40, UIStyle.BLUE_BUTTRESS, Color.white, "Add Video",  100, 40, "normal",true);
        this.add(send);
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cards.show(mainPanel, "tempContentPanel");
                mainPanel.setTempContent("videoAdd", "nullVideo");
            }
        });

    }
}
class AdministratorVideoOnePage extends JPanel
{
    public AdministratorVideoOnePage(ArrayList<Video> videos, CardLayout cards, MainPanel mainPanel, CardLayout innerCards, JPanel contentPanel, int page, int pageMax)
    {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight - 160);

        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(null);
        for(int i = (page - 1) * 3; i < videos.size() && i < page * 3; i++) {
            VideoPanel test = new VideoPanel(videos.get(i), 0, 150 * (i % 3), mainPanel, cards, "manage");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax + 1, page);

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
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}

class CoachPanel extends JPanel
{
    private CoachRightPanel coachRightPanel;
    private CoachLeftPanel coachLeftPanel;
    public CoachPanel(CardLayout loginCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CardLayout innerCards = new CardLayout();
        coachRightPanel = new CoachRightPanel(innerCards, contentPanel, mainCards, mainPanel);
        coachLeftPanel = new CoachLeftPanel(loginCards, contentPanel, innerCards, coachRightPanel);
        this.add(coachLeftPanel);
        this.add(coachRightPanel);
//        CustomerLeftPanel customerLeftPanel = new CustomerLeftPanel(loginCards, contentPanel, innerCards, personalRightPanel);
//        this.add(customerLeftPanel);
        //customerLeftPanel.setVisible(true);
    }
    public void updateCoach(String id)
    {
        coachRightPanel.update(id);
        coachLeftPanel.updateInfo();

    }
}

class CoachLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    private DynamicText name;
    private Picture circleIcon;
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
        TextButton myEmails = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Emails", (int)(0.15 * panelWidth), buttonStart + 2* buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");


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
        myEmails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                contentCards.show(rightPanel, "Email");

            }
        });


        this.add(myMembership);
        this.add(myLive);
        this.add(myEmails);


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
    public void updateInfo()
    {
        Coach coach = PersonalController.getController().getCoachInfoById(LoginToken.getId());
        if(name != null)
            this.remove(name);
        name = new DynamicText(UIStyle.COLOR_3, UIStyle.COLOR_4, coach.getName(), (int)(panelWidth / 2), (int)(panelHeight * 0.30), (int)(panelWidth / 2), (int)(panelHeight * 0.05), UIStyle.NORMAL_ARIAL_BOLD);
        this.add(name);

        if(circleIcon != null)
            this.remove(circleIcon);

        String picPath = UIStyle.class.getClassLoader().getResource(coach.getCoachId() + ".png").getPath();
        circleIcon = new Picture(picPath, (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);

    }
}
class CoachRightPanel extends JPanel
{
    private CoachMembershipPanel membership;
    private CoachLivePanel coachLivePanel;
    private EmailPanel emailPanel;
    private CardLayout mainCards;
    private MainPanel mainPanel;
    public CoachRightPanel(CardLayout innerCards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel) {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        this.mainCards = mainCards;
        this.mainPanel = mainPanel;
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);

    }
    public void update(String id)
    {
        PersonalController controller =PersonalController.getController();
        if(membership != null)
            this.remove(membership);
        membership = new CoachMembershipPanel(controller.getCoachInfoById(id));
        this.add(membership, "Membership");

        if(coachLivePanel != null)
            this.remove(coachLivePanel);
        ArrayList<LiveSession> liveSessions = LiveController.getController().getLiveSessionByCoachId(id);
        coachLivePanel = new CoachLivePanel(liveSessions);
        this.add(coachLivePanel, "Live");

        if(emailPanel != null)
            this.remove(emailPanel);
        ArrayList<Mail> emails = MailController.getController().getMailsById(LoginToken.getId());
        emailPanel = new EmailPanel(emails, mainCards, mainPanel);
        this.add(emailPanel, "Email");



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
class CoachLivePanel extends JPanel {
    private int pageMax;
    private ArrayList<CoachLiveOnePage> resultPanels = new ArrayList<>();

    public CoachLivePanel(ArrayList<LiveSession> liveSessions) {
        pageMax = liveSessions.size() / 4;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);
        CardLayout innerCards = new CardLayout();
        this.setLayout(innerCards);

        for (CoachLiveOnePage i : resultPanels) {
            this.remove(i);
        }
        resultPanels.clear();
        for (int i = 0; i <= pageMax; i++) {
            resultPanels.add(new CoachLiveOnePage(liveSessions, innerCards, this, i + 1, pageMax));
            this.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(this);
    }
}
class CoachLiveOnePage extends JPanel {
    public CoachLiveOnePage(ArrayList<LiveSession> liveSessions, CardLayout innerCards, JPanel contentPanel, int page, int pageMax) {
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

        for (int i = (page - 1) * 3; i < liveSessions.size() && i < page * 3; i++) {
            LivePanel test = new LivePanel(liveSessions.get(i), 0, 150 * (i % 3) + 130, "large");
            this.add(test);
        }

        JSlider pages = new JSlider(1, pageMax + 1, page);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int) (panelWidth / 2 - pagesWidth / 2), (int) (panelHeight - pagesHeight + 7), pagesWidth, pagesHeight);

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
        pageShow.setBounds((int) (panelWidth / 2 + pagesWidth / 1.5), (int) (panelHeight - pagesHeight + 17), 30, 30);
        this.add(pageShow);
        pageShow.setText(page + "");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");
            }
        });
        pageShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                innerCards.show(contentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}



