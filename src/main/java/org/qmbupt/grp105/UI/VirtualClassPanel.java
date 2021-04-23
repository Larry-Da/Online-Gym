package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

public class VirtualClassPanel extends JPanel
{
    public VirtualClassPanel(CardLayout cards, MainPanel mainPanel)
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
        CardLayout searchCards = new CardLayout();
        contentPanel.setLayout(searchCards);

        CategoryPanel categoryPanel = new CategoryPanel(searchCards, contentPanel);
        contentPanel.add(categoryPanel, "categoryPanel");

        SearchPanel searchPanel = new SearchPanel(searchCards, contentPanel, cards, mainPanel);
        contentPanel.add(searchPanel, "searchPanel");


//        SignInPanel signInPanel = new SignInPanel(loginCards, contentPanel);
//        contentPanel.add(signInPanel, "signInPanel");
//        CustomerPanel customerPanel = new CustomerPanel(loginCards, contentPanel);
//        contentPanel.add(customerPanel, "customerPanel");
//        AdministratorPanel administratorPanel = new AdministratorPanel(loginCards, contentPanel);
//        contentPanel.add(administratorPanel, "administratorPanel");

    }
}

class CategoryPanel extends JPanel
{
    public CategoryPanel(CardLayout cards, JPanel contentPanel) {
        this.setLayout(null);
        this.setBackground(Color.black);
        int buttonWidth = (int) (UIStyle.width / 3);
        int buttonHeight = (int) ((UIStyle.height  - UIStyle.barHeight) / 2);

        CategoryButton bicycle = new CategoryButton(UIStyle.VirtualClass_bicycle, buttonWidth, buttonHeight, "Bicycle Training");
        bicycle.setBounds(0, 0, buttonWidth, buttonHeight);
        this.add(bicycle);
        bicycle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cards.show(contentPanel, "searchPanel");
            }
        });

        CategoryButton HITT = new CategoryButton(UIStyle.VirtualClass_HITT, buttonWidth, buttonHeight, "HITT");
        HITT.setBounds(buttonWidth, 0, buttonWidth, buttonHeight);
        this.add(HITT);

        CategoryButton flexbility = new CategoryButton(UIStyle.VirtualClass_flexibility, buttonWidth, buttonHeight, "Flexibility");
        flexbility.setBounds(buttonWidth * 2, 0, buttonWidth, buttonHeight);
        this.add(flexbility);

        CategoryButton yoga = new CategoryButton(UIStyle.VirtualClass_yoga, buttonWidth, buttonHeight, "Yoga");
        yoga.setBounds(0, buttonHeight, buttonWidth, buttonHeight);
        this.add(yoga);

        CategoryButton strength = new CategoryButton(UIStyle.VirtualClass_strength, buttonWidth, buttonHeight, "Strength");
        strength.setBounds(buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.add(strength);

        CategoryButton weightLoss = new CategoryButton(UIStyle.VirtualClass_loseWeight, buttonWidth, buttonHeight, "Weight Loss");
        weightLoss.setBounds(2 * buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.add(weightLoss);



    }
}
class SearchPanel extends JPanel
{
    private int pageMax;
    public static int SearchResultPanelHeight;
    public SearchPanel(CardLayout cards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));
        InputText searchBar = new InputText(500, 50, 40, true, (int)(UIStyle.width / 2), 80, "Search", true);
        this.add(searchBar);

        int startFilter = 130;
        String[] categoryFilterString = {"Category", "Bicycle Training", "HITT", "Flexibility", "Yoga", "Strength", "Weight Loss"};
        FilterBox categoryFilter = new FilterBox(startFilter, categoryFilterString, "dark");
        this.add(categoryFilter);

        String[] sortString = {"Sort", "Like", "Rating", "View"};
        FilterBox sortFilter = new FilterBox(startFilter + 40, sortString, "dark");
        this.add(sortFilter);

        pageMax = 10;
        SearchResultPanelHeight = (int)(UIStyle.height - UIStyle.barHeight - UIStyle.height / 4);

        CardLayout resultCards = new CardLayout();
        JPanel resultContentPanel = new JPanel();
        resultContentPanel.setBounds(0, (int)(UIStyle.height / 4), (int)(UIStyle.width),(int)(UIStyle.height));
        this.add(resultContentPanel);
        resultContentPanel.setLayout(resultCards);

        resultContentPanel.setVisible(true);

        SearchResultPanel searchResultPanels[] = new SearchResultPanel[pageMax];
        Video[] videos = {Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo(), Video.getSampleVideo()};
        searchResultPanels[0] = new SearchResultPanel(videos, pageMax, resultCards, resultContentPanel, mainCards, mainPanel);
        resultContentPanel.add(searchResultPanels[0], "0");
    }
}
class SearchResultPanel extends JPanel
{
    public SearchResultPanel(Video[] videos, int pageMax, CardLayout resultCards, JPanel resultContentPanel, CardLayout cards, MainPanel mainPanel)
    {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));

        int xinterval =(int)((UIStyle.width - 100 - 100 - 200) / 3);
        for(int i = 0; i < videos.length; i++)
        {
            VideoPanel video = new VideoPanel(videos[i], 100+ xinterval * i, 100, mainPanel, cards, "small");
            this.add(video);
        }
        JSlider pages = new JSlider(1, pageMax, 1);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(UIStyle.width / 2 - pagesWidth / 2), (int)(SearchPanel.SearchResultPanelHeight - pagesHeight), pagesWidth,pagesHeight);

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
        pageShow.setBounds((int)(UIStyle.width / 2 + pagesWidth / 1.5), (int)(SearchPanel.SearchResultPanelHeight - pagesHeight + 10), 30, 30);
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