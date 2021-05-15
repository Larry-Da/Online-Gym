package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.backend.dblayer.MailManager;
import org.qmbupt.grp105.backend.model.Mail;

import java.util.ArrayList;

import static org.qmbupt.grp105.backend.dblayer.MailManager.getMailsById;

public class MailController
{
    private static MailController mailController = new MailController();
    private MailController() {}
    public static MailController getController(){
        return mailController;
    }

    public ArrayList<org.qmbupt.grp105.Entity.Mail> getMailsById(String id)
    {
        ArrayList<Mail> mails = null;
        try {
            mails = MailManager.getMailsById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<org.qmbupt.grp105.Entity.Mail> res = new ArrayList<>();
        for(Mail i : mails)
        {
            res.add(i.converter());
        }
        return res;

    }
    public void writeMail(String fromId, String toId, String content)
    {
        try
        {
            MailManager.writeMail(fromId, toId, content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
