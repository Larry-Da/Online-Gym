package org.qmbupt.grp105.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

public class PersonalPanel extends JPanel
{

    public PersonalPanel(CardLayout cards, JPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel);
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
        CustomerPanel customerPanel = new CustomerPanel(loginCards, contentPanel);
        contentPanel.add(customerPanel, "customerPanel");
        AdministratorPanel administratorPanel = new AdministratorPanel(loginCards, contentPanel);
        contentPanel.add(administratorPanel, "administratorPanel");

    }
}

class CustomerPanel extends JPanel
{
    PersonalController controller = new PersonalController();
    public CustomerPanel(CardLayout loginCards, JPanel contentPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        CustomerLeftPanel customerLeftPanel = new CustomerLeftPanel(controller, loginCards, contentPanel);
        this.add(customerLeftPanel);
        customerLeftPanel.setVisible(true);

        CardLayout innerCards = new CardLayout();
        CustomerRightPanel personalRightPanel = new CustomerRightPanel(innerCards, controller);
        this.add(personalRightPanel);
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
                else
                    cards.show(contentPanel, "customerPanel");
            }
        });


    }
}

class CustomerLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    public CustomerLeftPanel(PersonalController controller, final CardLayout loginCards, final JPanel contentPanel)
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
        TextButton myClasses = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Classes", (int)(0.15 * panelWidth), buttonStart + buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton myExerciseRecord = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Exercise Records", (int)(0.15 * panelWidth), buttonStart + 2 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");
        TextButton exerciseJournals = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Classes", (int)(0.15 * panelWidth), buttonStart + 3 * buttonHeight, buttonWidth, buttonHeight, UIStyle.NORMAL_FONT, false, "left");

        this.add(myMembership);
        this.add(myClasses);
        this.add(myExerciseRecord);
        this.add(exerciseJournals);



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
    public CustomerRightPanel(CardLayout innerCards, PersonalController controller) {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);
        CustomerMembershipPanel membership = new CustomerMembershipPanel(controller.getCusInfoByCusId("Cs15"));
        this.add(membership);
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

class AdministratorPanel extends JPanel
{
    PersonalController controller = new PersonalController();
    public AdministratorPanel(CardLayout loginCards, JPanel contentPanel)
    {
        this.setLayout(null);
        int barHeight = (int)(UIStyle.height) / 10;
        this.setBounds(0, 0, (int)(UIStyle.width), (int)(UIStyle.height - barHeight));
        AdministratorLeftPanel administratorLeftPanel = new AdministratorLeftPanel(controller, loginCards, contentPanel);
        this.add(administratorLeftPanel);
        administratorLeftPanel.setVisible(true);
        AdministratorRightPanel administratorRightPanel = new AdministratorRightPanel(loginCards, controller
                , contentPanel);
        this.add(administratorRightPanel);
        administratorRightPanel.setVisible(true);

//        CardLayout innerCards = new CardLayout();
//        Personal_RightPanel personalRightPanel = new Personal_RightPanel(innerCards, controller);
//        this.add(personalRightPanel);
    }
}

class AdministratorLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    public AdministratorLeftPanel(PersonalController controller, final CardLayout loginCards, final JPanel contentPanel)
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
    public AdministratorRightPanel(CardLayout innerCards, PersonalController controller, JPanel contentPanel) {
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), 0, panelWidth, panelHeight);
        setBackground(Color.WHITE);
        this.setLayout(innerCards);



        AdministratorMembershipPanel membership = new AdministratorMembershipPanel(controller);
        this.add(membership);
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

        int itemsPerPage = 20;

        String[] column = {"CusId", "Name", "Age", "Gender", "PhoneNo", "Email", "MembershipLevel", "Balance", "Points", "DateOfBirth", "RemainTime"};
        String[][] values = new String[itemsPerPage][column.length];
        int numOfCustomer = controller.getNumOfAllCustomers();

        ArrayList<Customer> cusList = new ArrayList<>(controller.getCustomerByPage(0, numOfCustomer - 1));
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
        TableList customerTable = new TableList(column, values, Color.BLACK, Color.WHITE, UIStyle.BLUE_SHALLOW, 0, UIStyle.barHeight, getWidth(), getHeight() - UIStyle.barHeight);

        this.add(customerTable);

        PageSelector pages = new PageSelector(10, Color.WHITE, Color.black, (int)(panelWidth - 100), (int)(50));
        this.add(pages);



    }

}



