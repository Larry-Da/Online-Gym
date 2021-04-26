package org.qmbupt.grp105.UI;

public class LoginToken
{
    private static String type;
    private static String id;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        LoginToken.type = type;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        LoginToken.id = id;
    }
}
