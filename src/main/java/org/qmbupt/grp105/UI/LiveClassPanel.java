package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class LiveClassPanel extends JPanel
{
    public LiveClassPanel(CardLayout cards, JPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel);
        menuBar.setVisible(true);
        this.setLayout(null);
        this.add(menuBar);
        int barHeight = (int)(UIStyle.height) / 10;


        ContentPanel contentPanel = new ContentPanel(cards, mainPanel);
        contentPanel.setBounds(0, barHeight, (int)UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(contentPanel);
        contentPanel.setVisible(true);



//        SignInPanel signInPanel = new SignInPanel(loginCards, contentPanel);
//        contentPanel.add(signInPanel, "signInPanel");
//        CustomerPanel customerPanel = new CustomerPanel(loginCards, contentPanel);
//        contentPanel.add(customerPanel, "customerPanel");
//        AdministratorPanel administratorPanel = new AdministratorPanel(loginCards, contentPanel);
//        contentPanel.add(administratorPanel, "administratorPanel");

    }
}

class ContentPanel extends JPanel
{
    private int pageMax;
    public static int SearchResultPanelHeight;
    public ContentPanel(CardLayout cards, JPanel contentPanel)
    {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));
        InputText searchBar = new InputText(500, 50, 40, true, (int)(UIStyle.width / 2), 80, "Search", true);
        this.add(searchBar);

        int startFilter = 130;
        String[] categoryFilterString = {"Category", "Bicycle Training", "HITT", "Flexibility", "Yoga", "Strength", "Weight Loss"};
        FilterBox categoryFilter = new FilterBox(startFilter, categoryFilterString);
        this.add(categoryFilter);



        String[] sortString = {"Sort", "Like", "Rating", "View"};
        FilterBox sortFilter = new FilterBox(startFilter + 40, sortString);
        this.add(sortFilter);

        pageMax = 10;
        SearchResultPanelHeight = (int)(UIStyle.height - UIStyle.barHeight - UIStyle.height / 4);

        CardLayout resultCards = new CardLayout();
        JPanel resultContentPanel = new JPanel();
        resultContentPanel.setBounds(0, (int)(UIStyle.height / 4), (int)(UIStyle.width),(int)(UIStyle.height));
        this.add(resultContentPanel);
        resultContentPanel.setLayout(resultCards);

        resultContentPanel.setVisible(true);

        ResultPanel searchResultPanels[] = new ResultPanel[pageMax];
        LiveSession[] liveSessions = {LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample(), LiveSession.getSample()};
        searchResultPanels[0] = new ResultPanel(liveSessions, pageMax, resultCards, resultContentPanel);
        resultContentPanel.add(searchResultPanels[0], "0");
    }
}
class ResultPanel extends JPanel {
    public ResultPanel(LiveSession[] liveSessions, int pageMax, CardLayout resultCards, JPanel resultContentPanel) {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));

        int xinterval =(int)((UIStyle.width - 100 - 100 - 200) / 3);
        for(int i = 0; i < liveSessions.length; i++)
        {
            LivePanel live = new LivePanel(liveSessions[i], 100+ xinterval * i, 100, "small");
            this.add(live);
            if(i == 3)
                break;
        }
        for(int i = 4; i < liveSessions.length; i++)
        {
            LivePanel live = new LivePanel(liveSessions[i], 100+ xinterval * (i-4), 250, "small");
            this.add(live);
        }
        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int) (UIStyle.width / 2 - pagesWidth / 2), (int) (SearchPanel.SearchResultPanelHeight - pagesHeight), pagesWidth, pagesHeight);

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
        pageShow.setBounds((int) (UIStyle.width / 2 + pagesWidth / 1.5), (int) (SearchPanel.SearchResultPanelHeight - pagesHeight + 10), 30, 30);
        this.add(pageShow);
        pageShow.setText("1");
        pages.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pageShow.setText(pages.getValue() + "");

            }
        });
        this.setVisible(true);
    }
}
