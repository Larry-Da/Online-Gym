package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LiveClassPanel extends JPanel
{
    public static MyReminder reminder;
    public LiveClassPanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel, "Live");
        reminder = new MyReminder(menuBar);

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

    private InputText searchBar;
    private String[] sortString = {"Sort", "Like", "Rating", "View"};

    private CardLayout resultCards;
    private ArrayList<ResultPanel> searchResultPanels = new ArrayList<>();
    private FilterBox categoryFilter;
    private JPanel resultContentPanel;
    private CardLayout mainCards;
    private MainPanel mainPanel;
    private FilterBox sortFilter;

    public ContentPanel(CardLayout cards, JPanel contentPanel)
    {

        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));


        searchBar = new InputText(500, 50, 40, true, (int)(UIStyle.width / 2), 55, "Search", true);
        this.add(searchBar);
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRes();
            }
        });

        int startFilter = 130;
        categoryFilter = new FilterBox(startFilter, UIStyle.categories, "dark");
        this.add(categoryFilter);

        sortFilter = new FilterBox(startFilter + 40, sortString, "dark", true);
        this.add(sortFilter);

        SearchResultPanelHeight = (int)(UIStyle.height - UIStyle.barHeight - startFilter - 90);

        resultCards = new CardLayout();
        resultContentPanel = new JPanel();
        resultContentPanel.setBounds(0, startFilter + 90, (int)(UIStyle.width),SearchResultPanelHeight);
        this.add(resultContentPanel);
        resultContentPanel.setLayout(resultCards);

        updateRes();
        resultContentPanel.setVisible(true);

    }
    public void updateRes()
    {
        String key = searchBar.getText();
        if(key.equals("Search") || key.equals(""))
        {
            key = null;
        }
        ArrayList<LiveSession> sessions = LiveController.getController().getSessionsByCoach(key);
        ArrayList<String> keyCategory = new ArrayList<>();
        boolean[] states = categoryFilter.getStates();
        int cnt = 1;
        for(boolean i : states)
        {
            if(i)
            {
                keyCategory.add(UIStyle.categories[cnt]);
            }
            cnt++;
        }
        ArrayList<LiveSession> sessions1 = LiveController.getController().filterSessionByCategory(sessions, keyCategory);


        pageMax = sessions1.size() / 8;
        for(ResultPanel i : searchResultPanels)
        {
            resultContentPanel.remove(i);
        }
        searchResultPanels.clear();

        for(int i = 0; i <= pageMax; i++)
        {
            ResultPanel rp = new ResultPanel(sessions1, pageMax, resultCards, resultContentPanel, i + 1);
            searchResultPanels.add(i, rp);
            resultContentPanel.add(rp, i + 1 + "");
        }
        resultCards.first(resultContentPanel);// if there is no this statement, updateRes from only 1 page to another only 1 page will show blank

    }
}
class ResultPanel extends JPanel {
    public ResultPanel(ArrayList<LiveSession> liveSessions, int pageMax, CardLayout resultCards, JPanel resultContentPanel, int page) {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));

        int xinterval =(int)((UIStyle.width - 100 - 100 - 200) / 3);
        for(int i = 8 * (page - 1); i < liveSessions.size() && i < 8 * (page -1) + 4; i++)
        {
            LivePanel live = new LivePanel(liveSessions.get(i), 100+ xinterval * (i%4), 40, "small");
            this.add(live);
        }
        for(int i = 8 *(page - 1) + 4; i < liveSessions.size() && i < 8* (page - 1) + 8; i++)
        {
            LivePanel live = new LivePanel(liveSessions.get(i), 100+ xinterval * ((i-4)%4), 40 + 150, "small");
            this.add(live);
        }
        JSlider pages = new JSlider(1, pageMax + 1, page);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int) (UIStyle.width / 2 - pagesWidth / 2), (int) (SearchPanel.SearchResultPanelHeight - 2 * pagesHeight), pagesWidth, pagesHeight);

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
        pageShow.setBounds((int) (UIStyle.width / 2 + pagesWidth / 1.5), (int) (SearchPanel.SearchResultPanelHeight - 2 * pagesHeight + 10), 30, 30);
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
                resultCards.show(resultContentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}
