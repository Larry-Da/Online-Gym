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
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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


class LiveDetailPanel extends JPanel
{
    private LiveSession currentLive;
    private boolean isAdding = false;
    private InputText start_lower;
    private InputText end_lower;
    private InputText category_lower;
    private InputText like_lower;
    private InputText rating_lower;
    private DynamicText file_upper;
    private Picture pic;
    private TextButton save;
    private TextButton delete;
    private JFileChooser jfc;
    private InputText file_lower;
    private MouseListener addingVideo;
    private MouseListener modifyVideo;

    public LiveDetailPanel() {

        this.setBackground(Color.WHITE);
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.1 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        setBounds(0, 0,(int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));

        this.setLayout(null);



        DynamicText start_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Start", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        start_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, "start");
        this.add(start_upper);
        this.add(start_lower);

        DynamicText end_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "End", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        end_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, "end");
        this.add(end_upper);
        this.add(end_lower);

        DynamicText category_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Category", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        category_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(category_upper);
        this.add(category_lower);


        DynamicText like_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Likes", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        like_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(like_upper);
        this.add(like_lower);

        DynamicText rating_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 8 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Rating", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        rating_lower = new InputText(buttonStartX, buttonStartY + 9 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(rating_upper);
        this.add(rating_lower);



        save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX +  2.5* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);

        delete = new TextButton(Color.decode("#E04147"), Color.WHITE, "Delete", (int)(buttonStartX +  1.6* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(delete);

//        delete.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                VideoController.getController().deleteVideo(cur.getVideoId());
//                TempContentPanel.reminder.OK("Delete Success!");
//            }
//        });

        file_upper = new DynamicText((int) (buttonStartX + 1.5 * buttonWidth + 0.02*buttonWidth), buttonStartY , "left", Color.WHITE, Color.BLACK, "Url", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        file_lower = new InputText((int) (buttonStartX + 1.5 * buttonWidth), buttonStartY +buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(file_upper);
        this.add(file_lower);
        file_lower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jfc = new JFileChooser();
                jfc.setBackground(Color.white);
                int returnVal = jfc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file_lower.setText(jfc.getSelectedFile().toString());
                } else {

                }
            }
        });

        modifyVideo = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

                Date start = null;
                Date end = null;
                try {
                    start = new Date(sdf.parse(start_lower.getText()).getTime());
                    end = new Date(sdf.parse(end_lower.getText()).getTime());

                }
                catch(Exception e1)
                {

                }
                LiveSession liveToBeModify = new LiveSession(currentLive.getLiveSessionId(), Double.parseDouble(rating_lower.getText()), category_lower.getText(), start, end, Integer.parseInt(like_lower.getText()),
                        currentLive.getViewCounts(), currentLive.getCustomer_cusId(), currentLive.getCoach_coachId(), currentLive.getAvailableNum(), currentLive.getPrice());

                LiveController.getController().addSession(liveToBeModify);
                TempContentPanel.reminder.OK("Save Success!");
            }
        };

        addingVideo = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                String url = file_lower.getText();
//                String fileName = url.substring(url.lastIndexOf('/') + 1);
//
//
//                Video videoToBeAdd = new Video(null, fileName, title_lower.getText(),
//                        Double.parseDouble(rating_lower.getText()), category_lower.getText(), Integer.parseInt(like_lower.getText()),
//                        0, level_lower.getText());
//                VideoController.getController().AddVideo(videoToBeAdd);
//                TempContentPanel.reminder.OK("Adding Success!");
//
//                String path = UIStyle.class.getClassLoader().getResource("HIIT.jpg").getPath();
//                String toBeMoved = path.substring(0, path.length() - 9);
//                toBeMoved = toBeMoved + "/" + fileName;
//
//                String srcFile = url;
//                String desFile = toBeMoved;
//                try {
//                    // 使用缓冲字节流进行文件复制
//                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
//                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
//                    byte[] b = new byte[1024];
//                    Integer len = 0;
//                    //一次读取1024字节的数据
//                    while ((len = bis.read(b)) != -1) {
//                        bos.write(b, 0, len);
//                    }
//                    bis.close();
//                    bos.close();
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
            }
        };





    }
    public void setAdding(boolean isAdding)
    {
        this.isAdding = isAdding;
        if(isAdding) {
            setCurrentLive(null);
        }
    }

    public void setCurrentLive(LiveSession currentLive) {
        if(!isAdding) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String start = sdf.format(currentLive.getStartTime());
            String end = sdf.format(currentLive.getEndTime());

            this.currentLive = currentLive;
            start_lower.setText(start);
            category_lower.setText(currentLive.getCategory());
            end_lower.setText(end);
            like_lower.setText(currentLive.getLikes() + "");
            rating_lower.setText(currentLive.getRating() + "");
            file_lower.setVisible(false);
            file_upper.setVisible(false);
            save.removeMouseListener(addingVideo);
            save.addMouseListener(modifyVideo);
            delete.setVisible(true);

            int buttonWidth = (int)(0.244 * UIStyle.width);
            int buttonStartY = (int)(0.1 * UIStyle.height);
            int buttonStartX = (int)(0.1 * UIStyle.width);
            int picWidth = 200;
            int picHeight = 200;
            if(pic != null)
                this.remove(pic);
            try {
                String path = UIStyle.class.getClassLoader().getResource(currentLive.getCoach_coachId() + ".png").getPath();
                pic = new Picture(path, picWidth, picHeight);
                this.add(pic);
                pic.setBounds((int) (buttonStartX + 2.5 * buttonWidth - (picWidth - buttonWidth / 2)), buttonStartY, picWidth, picHeight);
            }
            catch (Exception e)
            {
                ;
            }
        }
        else
        {
            this.currentLive = currentLive;
            start_lower.setText("");
            category_lower.setText("");
            end_lower.setText("");
            like_lower.setText("");
            rating_lower.setText("");
            file_upper.setVisible(true);
            file_lower.setVisible(true);
            file_lower.setText("");
            if(pic != null)
                this.remove(pic);
            save.addMouseListener(addingVideo);
            save.removeMouseListener(modifyVideo);
            delete.setVisible(false);


        }



    }

}


