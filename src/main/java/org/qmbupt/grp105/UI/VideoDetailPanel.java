package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.Toolbox;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.DynamicText;
import org.qmbupt.grp105.UI.MyUIComponent.InputText;
import org.qmbupt.grp105.UI.MyUIComponent.Picture;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoDetailPanel extends JPanel
{
    private Video currentVideo = Video.getSampleVideo();
    private boolean isAdding = false;
    private InputText title_lower;
    private InputText level_lower;
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

    public VideoDetailPanel() {
        this.setBackground(Color.WHITE);
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.1 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        setBounds(0, 0,(int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));

        this.setLayout(null);

        DynamicText title_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Title", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        title_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getName());
        this.add(title_lower);
        this.add(title_upper);

        DynamicText category_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Category", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        category_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getCategory());
        this.add(category_upper);
        this.add(category_lower);

        DynamicText level_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Level", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        level_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getLevel());
        this.add(level_upper);
        this.add(level_lower);

        DynamicText like_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Likes", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        like_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getLikes() + "");
        this.add(like_upper);
        this.add(like_lower);

        DynamicText rating_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 8 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Rating", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        rating_lower = new InputText(buttonStartX, buttonStartY + 9 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getRating() + "");
        this.add(rating_upper);
        this.add(rating_lower);

        save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX +  2.5* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);

        delete = new TextButton(Color.decode("#E04147"), Color.WHITE, "Delete", (int)(buttonStartX +  1.6* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(delete);

        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                VideoController.getController().deleteVideo(currentVideo.getVideoId());
                TempContentPanel.reminder.OK("Delete Success!");
            }
        });

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
                boolean checkPass = true;
                if(!Toolbox.isCategory(category_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Category not exist");
                    checkPass = false;
                }
                else if(!Toolbox.isMembership(level_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Level not exist");
                    checkPass = false;
                }
                else if(title_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("Title cannot be empty");
                    checkPass = false;
                }


                if(checkPass) {
                    try {
                        Video videoToBeModify = new Video(currentVideo.getVideoId(), currentVideo.getUrl(), title_lower.getText(),
                                Double.parseDouble(rating_lower.getText()), category_lower.getText(), Integer.parseInt(like_lower.getText()),
                                currentVideo.getViewsCount(), level_lower.getText());
                        VideoController.getController().modifyVideo(videoToBeModify);
                        TempContentPanel.reminder.OK("Save Success!");
                    } catch (Exception e1) {
                        TempContentPanel.reminder.WRONG("Number format error");
                    }
                }

            }
        };

        addingVideo = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                boolean checkPass = true;
                if(!Toolbox.isCategory(category_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Category not exist");
                    checkPass = false;
                }
                else if(!Toolbox.isMembership(level_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Level not exist");
                    checkPass = false;
                }
                else if(!Toolbox.isPicture(file_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Not a picture");
                    System.out.println(file_lower.getText());
                    System.out.println(Toolbox.isPicture(file_lower.getText()));
                    checkPass = false;
                }
                else if(title_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("Title cannot be empty");
                    checkPass = false;
                }
                else if(file_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("URL cannot be empty");
                    checkPass = false;
                }

                if(checkPass) {

                    String url = file_lower.getText();
                    String fileName = url.substring(url.lastIndexOf('/') + 1);

                    try{
                        Video videoToBeAdd = new Video(null, fileName, title_lower.getText(),
                                Double.parseDouble(rating_lower.getText()), category_lower.getText(), Integer.parseInt(like_lower.getText()),
                                0, level_lower.getText());
                        VideoController.getController().AddVideo(videoToBeAdd);

                        String path = UIStyle.class.getClassLoader().getResource("HIIT.jpg").getPath();
                        String toBeMoved = path.substring(0, path.length() - 9);
                        toBeMoved = toBeMoved + "/" + fileName;

                        String srcFile = url;
                        String desFile = toBeMoved;
                        try {
                            // 使用缓冲字节流进行文件复制
                            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
                            byte[] b = new byte[1024];
                            Integer len = 0;
                            //一次读取1024字节的数据
                            while ((len = bis.read(b)) != -1) {
                                bos.write(b, 0, len);
                            }
                            bis.close();
                            bos.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        TempContentPanel.reminder.OK("Adding Success!");
                    }
                    catch(Exception e2)
                    {
                        TempContentPanel.reminder.WRONG("Number Format Error");
                    }



                }
            }
        };





    }
    public void setAdding(boolean isAdding)
    {
        this.isAdding = isAdding;
        if(isAdding) {
            setCurrentVideo(null);

        }


    }
    public void setCurrentVideo(Video currentVideo) {
        if(!isAdding) {
            this.currentVideo = currentVideo;
            title_lower.setText(currentVideo.getName());
            category_lower.setText(currentVideo.getCategory());
            level_lower.setText(currentVideo.getLevel());
            like_lower.setText(currentVideo.getLikes() + "");
            rating_lower.setText(currentVideo.getRating() + "");
            file_lower.setVisible(false);
            file_upper.setVisible(false);
            save.removeMouseListener(addingVideo);
            save.addMouseListener(modifyVideo);
            delete.setVisible(true);

            int buttonWidth = (int)(0.244 * UIStyle.width);
            int buttonStartY = (int)(0.1 * UIStyle.height);
            int buttonStartX = (int)(0.1 * UIStyle.width);
            int picWidth = 350;
            int picHeight = 350;
            if(pic != null)
                this.remove(pic);
            try {
                String path = UIStyle.class.getClassLoader().getResource(currentVideo.getUrl()).getPath();
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
            this.currentVideo = currentVideo;
            title_lower.setText("");
            category_lower.setText("");
            level_lower.setText("");
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
