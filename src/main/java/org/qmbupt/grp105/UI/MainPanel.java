package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Entity.Mail;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
    private HomePanel homePanel;
    private PersonalPanel personalPanel;
    private VirtualClassPanel virtualClassPanel;
    private LiveClassPanel liveClassPanel;
    private ContactPanel contactPanel;
    private TempContentPanel tempContentPanel;
    public static String currentPanel = "homePanel";


    public MainPanel()
    {
        CardLayout cards = new CardLayout();
        this.setLayout(cards);

        homePanel = new HomePanel(cards, this);
        this.add(homePanel, "homePanel");
        //homePanel.setVisible(true);

        personalPanel = new PersonalPanel(cards, this);
        this.add(personalPanel, "personalPanel");
        //personalPanel.setVisible(true);

        virtualClassPanel = new VirtualClassPanel(cards, this);
        this.add(virtualClassPanel, "virtualClassPanel");
        //classPanel.setVisible(true);

        liveClassPanel = new LiveClassPanel(cards, this);
        this.add(liveClassPanel, "liveSessionPanel");

        contactPanel = new ContactPanel(cards, this);
        this.add(contactPanel, "contactPanel");

        tempContentPanel = new TempContentPanel(cards, this);
        this.add(tempContentPanel, "tempContentPanel");

    }
    public void setTempContent(String content, Mail email)
    {
        tempContentPanel.setContent(content, email);
    }
    public void setTempContent(String content, String id)
    {
        tempContentPanel.setContent(content, id);
    }
    public void updatePersonalInfo()
    {
        if(LoginToken.getId() != null && LoginToken.getType().equals("Customer"))
            personalPanel.updateCus(LoginToken.getId());
        if(LoginToken.getId() != null && LoginToken.getType().equals("Coach"))
            personalPanel.updateCoach(LoginToken.getId());
        if(LoginToken.getId() != null && LoginToken.getType().equals("Admin"))
            personalPanel.updateAdmin();

    }


}
