package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;

import org.qmbupt.grp105.backend.model.Setting;

public class SettingsManager {
    public static ArrayList<String> getSetting(String setting_name) throws IOException {
        ArrayList<Setting> settings =  DataManager.getInstance().settings;
        for (Setting setting : settings) {
            if (setting.setting_name.equals(setting_name)) {
                return setting.setting_value;
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) throws IOException {
        System.out.println(getSetting("category").get(0));
    }
}
