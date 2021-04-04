package UI;

import javax.swing.*;

public class PersonalLeftPanel extends JPanel
{
    int panelWidth;
    int panelHeight;
    public PersonalLeftPanel()
    {
        panelWidth = (int)(UIStyle.width * 0.24);
        panelHeight = (int)(UIStyle.height - UIStyle.barHeight);
        setBounds(0, UIStyle.barHeight, panelWidth, panelHeight);
        setBackground(UIStyle.COLOR_3);
        this.setLayout(null);

        int buttonStart = (int)(UIStyle.height / 2.2);
        int buttonHeight = (int)(UIStyle.height * 0.0625);
        int buttonWidth = panelWidth;

        TextButton myMembership = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Membership", 0, buttonStart, buttonWidth, buttonHeight, "normal", false);
        TextButton myClasses = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Classes", 0, buttonStart + buttonHeight, buttonWidth, buttonHeight, "normal", false);
        TextButton myExerciseRecord = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Exercise Records", 0, buttonStart + 2 * buttonHeight, buttonWidth, buttonHeight, "normal", false);
        TextButton exerciseJournals = new TextButton(UIStyle.COLOR_3, UIStyle.COLOR_4, "My Classes", 0, buttonStart + 3 * buttonHeight, buttonWidth, buttonHeight, "normal", false);

        this.add(myMembership);
        this.add(myClasses);
        this.add(myExerciseRecord);
        this.add(exerciseJournals);


        Picture circleIcon = new Picture("/Users/daliangrun/Documents/1/src/PersonalIcon.jpg", (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        circleIcon.setBounds((int)(UIStyle.width * 0.0875), (int)(UIStyle.height * 0.12), (int)(panelWidth * 0.26), (int)(panelWidth * 0.26));
        this.add(circleIcon);


    }

}
