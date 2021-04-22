package org.qmbupt.grp105.UI;

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

public class VideoDetailPanel extends JPanel
{
    private CallbackMediaPlayerComponent mediaPlayerComponent;
    private TextButton pauseButton;
    private String path = "/Users/daliangrun/Downloads/vue-vr/demos/assets/ClashofClans.mp4";
   // private String path = "/Users/daliangrun/Downloads/corejava/v1ch13fx/fancy/moonlanding.mp4";
    public VideoDetailPanel()
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

        videoPanel.add(controlsPane);
        mediaPlayerComponent.mediaPlayer().media().startPaused(path);
        this.setVisible(true);
        this.add(videoPanel);

        InputText comment = new InputText(20, videoPanelHeight + 20, 800, 30, 100, true, "Comment Here");

        this.add(comment);

    }

}
