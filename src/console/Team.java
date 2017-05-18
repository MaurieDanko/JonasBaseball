package console;

import java.util.List;

/**
 * Created by jonas on 2017-05-18.
 */
public class Team {
    String teamName;
    List<Player> roster;

    static Team jays(){
        Team jays = new Team();
        jays.teamName = "Toronto Blue Jays";
        return jays;
    }

    static Team yankees(){
        Team yankees = new Team();
        yankees.teamName = "New York Yankees";
        return yankees;

    }
}
