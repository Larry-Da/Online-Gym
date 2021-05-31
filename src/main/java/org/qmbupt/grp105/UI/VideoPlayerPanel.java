package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.InputArea;
import org.qmbupt.grp105.UI.MyUIComponent.InputText;
import org.qmbupt.grp105.UI.MyUIComponent.PicButton;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * It is used to play the video.
 * @author daliangrun
 * @version 5.3
 */
public class VideoPlayerPanel extends JPanel
{
    private CallbackMediaPlayerComponent mediaPlayerComponent;
    private TextButton pauseButton;
    private String path = "/Users/daliangrun/Downloads/vue-vr/demos/assets/ClashofClans.mp4";
    private Video currentVideo;
    private ArrayList<InputArea> commentList = new ArrayList<>();
    private JPanel commentPanel;
    private CardLayout cards = new CardLayout();
    private JPanel rightPanels = new JPanel();
    private TextButton delete;


    public VideoPlayerPanel()
    {
        this.setLayout(null);
        this.setBackground(Color.white);
        JPanel videoPanel = new JPanel();
        int videoPanelHeight = (int)((UIStyle.height-UIStyle.barHeight) / 10 * 9);
        int videoPanelWidth = (int)(videoPanelHeight / 9.0 * 16 /10.0 * 9);
        videoPanel.setBounds(10, 10,videoPanelWidth, videoPanelHeight);
        videoPanel.setLayout(null);

        mediaPlayerComponent = new CallbackMediaPlayerComponent();
        videoPanel.add(mediaPlayerComponent);
        mediaPlayerComponent.setBounds(0, 0, videoPanelWidth, (int)(videoPanelHeight / 10.0 * 9));
        JPanel controlsPane = new JPanel();
        controlsPane.setBounds(0, (int)(videoPanelHeight / 10.0 * 9), videoPanelWidth, (int)(videoPanelHeight / 10.0));
        controlsPane.setLayout(null);
        //public TextButton(int centerx, int centery, Color background, Color foreground, String text, int width, int height, String size, boolean backgroundChange)

        controlsPane.setBackground(Color.WHITE);
        int buttonWidth = 30;
        int buttonHeight = 30;
        PicButton playButton = new PicButton(UIStyle.VirtualClass_play,buttonWidth, (int)(videoPanelHeight / 10.0 /2), buttonWidth, buttonHeight);
        controlsPane.add(playButton);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mediaPlayerComponent.mediaPlayer().controls().play();
            }
        });
        PicButton pauseButton = new PicButton(UIStyle.VirtualClass_pause,(int)(buttonWidth * 2.5), (int)(videoPanelHeight / 10.0 /2), buttonWidth, buttonHeight);
        controlsPane.add(pauseButton);
        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(mediaPlayerComponent.mediaPlayer().status().isPlaying())
                    mediaPlayerComponent.mediaPlayer().controls().pause();
            }
        });

        JLabel like = new JLabel("Like", JLabel.CENTER);
        like.setFont(UIStyle.SMALL_FONT);
        int labelX = videoPanelWidth - 2 * buttonWidth - buttonWidth / 2;
        int labelY = (int)(videoPanelHeight / 10.0 /2) - buttonHeight /3;
        like.setBounds(labelX, labelY, 40, 20);
        controlsPane.add(like);


        PicButton thumbUp = new PicButton(UIStyle.VirtualClass_thumbUp, videoPanelWidth - 3*buttonWidth, (int)(videoPanelHeight / 10.0 /2), buttonWidth, buttonHeight);
        controlsPane.add(thumbUp);

        JLabel addToFavorite = new JLabel("Favorite", JLabel.LEFT);
        addToFavorite.setFont(UIStyle.SMALL_FONT);
        addToFavorite.setBounds(labelX - 5 * buttonWidth, labelY, 100, 20);
        controlsPane.add(addToFavorite);


        PicButton favorite = new PicButton(UIStyle.VirtualClass_favorite, videoPanelWidth - 8 *buttonWidth, (int)(videoPanelHeight / 10.0 /2), buttonWidth, buttonHeight);
        controlsPane.add(favorite);
        favorite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(LoginToken.getId() != null && LoginToken.getType().equals("Customer")) {
                    PersonalController.getController().addToFavourite(LoginToken.getId(), currentVideo.getVideoId());
                    TempContentPanel.reminder.OK("Add to favorite successfully!");
                }
            }
        });

        videoPanel.add(controlsPane);
        mediaPlayerComponent.mediaPlayer().media().startPaused(path);
        this.setVisible(true);
        this.add(videoPanel);

        InputText comment = new InputText(20, videoPanelHeight + 20, 800, 30, 100, true, "Comment Here");


        this.add(comment);
        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comment.getText().length() > 40)
                {
                    TempContentPanel.reminder.WRONG("Comment length should be less than 40 letters");
                }
                else if(!comment.getText().equals(""))
                {
                    ArrayList<String> commentList = currentVideo.getComments();
                    commentList.add(0, comment.getText());
                    VideoController.getController().modifyVideo(currentVideo);
                    TempContentPanel.reminder.OK("Comment successfully");
                    comment.setText("");
                    updateComment();

                }
                else
                {
                    TempContentPanel.reminder.WRONG("Comment can't be empty!");
                }
            }
        });
        rightPanels.setLayout(cards);
        rightPanels.setBounds((int)(UIStyle.width - 200), 0, 200, (int)(UIStyle.height - UIStyle.barHeight));
        rightPanels.setVisible(true);
        rightPanels.setBackground(Color.white);


    }

    /**
     * inject a video here
     * @param currentVideo video injected
     */
    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

    /**
     * <p>since the customer can comment, and it should be presented immediately, this method
     * is used to update the comment.</p>
     */
    public void updateComment()
    {
        if(commentPanel != null)
            rightPanels.remove(commentPanel);
        commentPanel = new JPanel();
        commentPanel.setVisible(true);
        commentPanel.setLayout(null);
        commentPanel.setBackground(Color.white);
        commentPanel.setBounds(0, 0, 200, (int)(UIStyle.height - UIStyle.barHeight));

        ArrayList<String> comments = currentVideo.getComments();
        for(int i = 0; i < 10 && i < comments.size(); i++)
        {

            InputArea temp = new InputArea(180, 50, false,100, 50 + i * 50, comments.get(i), false);
            temp.setEditable(false);
            temp.setLineWrap(true);

            commentPanel.add(temp);
        }
        rightPanels.add(commentPanel);
        if(delete != null)
        {
            commentPanel.remove(delete);
        }
        if("Admin".equals(LoginToken.getType()))
        {
            delete = new TextButton(Color.decode("#E04147"), Color.WHITE, "Delete Comment", 0, 570, 200, 40, UIStyle.NORMAL_FONT, true, "mid");
            commentPanel.add(delete);
            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    currentVideo.setComments(new ArrayList<String>());
                    VideoController.getController().modifyVideo(currentVideo);
                    TempContentPanel.reminder.OK("Delete successfully");
                    updateComment();
                }
            });
        }

        this.add(rightPanels);
        cards.first(rightPanels);

    }
}
