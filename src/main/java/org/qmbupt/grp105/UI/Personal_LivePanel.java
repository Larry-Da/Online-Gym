package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.*;
import org.qmbupt.grp105.Entity.LiveSession;

import java.util.ArrayList;

public class Personal_LivePanel
{
    public Personal_LivePanel(PersonalController controller)
    {
        ArrayList<LiveSession> sessions = controller.getSession("");


    }
}
