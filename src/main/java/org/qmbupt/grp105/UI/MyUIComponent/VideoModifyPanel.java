package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;

public class VideoModifyPanel extends JPanel
{
    private Video currentVideo = Video.getSampleVideo();

    public VideoModifyPanel() {
        this.setBackground(Color.WHITE);
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.1 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        setBounds(0, 0,(int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));

        this.setLayout(null);

        DynamicText title_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Title", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText title_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getName());
        this.add(title_lower);
        this.add(title_upper);

        DynamicText category_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Category", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText category_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getCategory());
        this.add(category_upper);
        this.add(category_lower);

        DynamicText level_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Level", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText level_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getLevel());
        this.add(level_upper);
        this.add(level_lower);

        DynamicText like_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Likes", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText like_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getLikes() + "");
        this.add(like_upper);
        this.add(like_lower);

        DynamicText rating_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 8 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Rating", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        InputText rating_lower = new InputText(buttonStartX, buttonStartY + 9 * buttonHeight, buttonWidth, buttonHeight , 15, false, currentVideo.getRating() + "");
        this.add(rating_upper);
        this.add(rating_lower);

        String path = UIStyle.class.getClassLoader().getResource(currentVideo.getUrl()).getPath();

        int picWidth = 350;
        int picHeight = 350;
        Picture pic = new Picture(path, picWidth, picHeight);
        this.add(pic);
        pic.setBounds((int)(buttonStartX + 2.5 * buttonWidth - (picWidth - buttonWidth / 2)), buttonStartY, picWidth, picHeight);




        TextButton save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX +  2.5* buttonWidth), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);
    }
    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

}
