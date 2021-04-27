package org.qmbupt.grp105.backend.dblayer;

import org.qmbupt.grp105.backend.model.Coach;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;

public class CoachManager {

    public static Coach getCoachById(String coachId) throws IOException {
        ArrayList<Coach> coaches = DataManager.getInstance().coaches;
        for (Coach coach : coaches) {
            if (coach.coachId.equals(coachId)) {
                return coach;
            }
        }
        return null;
    }

    public static ArrayList<Coach> getAllCoaches() throws IOException {
        return DataManager.getInstance().coaches;
    }

    public static void writeCoachInfo(Coach coach) throws IOException {
        ArrayList<Coach> coaches = DataManager.getInstance().coaches;
        boolean isDuplicated = false;
        for (int i = 0; i < coaches.size(); i++) {
            if (coaches.get(i).coachId.equals(coach.coachId)) {
                coaches.remove(i);
                coaches.add(coach);
                DataManager.getInstance().commit();
                return;
            }
        }
        coaches.add(coach);
        DataManager.getInstance().commit();
    }

    public static void main(String[] args) throws Exception {
        Coach a = getCoachById("co9");
        a.age++;
        DataManager.getInstance().commit();
    }
}
