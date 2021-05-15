package org.qmbupt.grp105.backend.dblayer;

import org.qmbupt.grp105.backend.model.Mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MailManager {

    public static ArrayList<Mail> getMailsById(String id) throws IOException {
        ArrayList<Mail> ret = new ArrayList<>();
        ArrayList<Mail> mails = DataManager.getInstance().mails;

        for (Mail mail : mails) {
            if (mail.to.equals(id)) {
                ret.add(mail);
            }
        }

        return ret;
    }

    public static void writeMail(String fromId, String toId, String content) throws IOException {

        ArrayList<Mail> mails = DataManager.getInstance().mails;

        Mail mail = new Mail();
        mail.from    = fromId;
        mail.to      = toId;
        mail.content = content;
        mail.time    = new Date();

        mails.add(mail);

        DataManager.getInstance().commit();
    }

    public static void main(String[] args) throws IOException {
        writeMail("co1", "cs1", "Hello, world!");
    }
}