package console.jonas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas on 2017-05-18.
 */
public class Team {
    String teamName;
    List<Player> roster;

    static Team jays() {
        Team jays = new Team();
        jays.teamName = "Toronto Blue Jays";
        jays.roster = new ArrayList<Player>();

        String playerinfo[] = {"Kevin Pillar", "Centre Field", "Josh Donaldson", "Third Base", "Jose Bautista", "Right Field", "Kendrys Morales", "DH",
                "Justin Smoak", "First Base", "Troy Tulowitzki", "Short Stop", "Russell Martin", "Catcher", "Darwin Barney", "Second Base",
                "Ezequil Carerra", "Left Field", "Marcus Stroman", "Pitcher"};
        for (int i = 0; i < playerinfo.length; ++i) {


            if (i % 2 == 0) {
                String playername = playerinfo[i];
                String pos = playerinfo[i + 1];
                Player p = new Player();
                p.name = playername;
                p.position = pos;
                jays.roster.add(p);
            }

        }
        //System.out.println(jays.roster);
        return jays;
    }


    static Team yankees() {
        Team yankees = new Team();
        yankees.teamName = "New York Yankees";
        yankees.roster = new ArrayList<Player>();
        String playerinfo[] = {"Brett Gardner", "Left Field", "Gary Sanchez", "Catcher", "Aaron Judge", "Right Field", "Matt Holiday", "DH",
                "Starlin Castro", "Second Base", "Aaron Hicks", "Centre Field", "Didi Gregorius", "Short Stop", "Chase Hedley", "Third Base",
                "Rob Refsnyder", "First Base", "C.C. Sabathia", "Pitcher"};

        for (int i = 0; i < playerinfo.length; ++i) {
            if (i % 2 == 0) {
                String playername = playerinfo[i];
                String pos = playerinfo[i + 1];
                Player p = new Player();
                p.name = playername;
                p.position = pos;
                yankees.roster.add(p);
            }
        }//System.out.println(yankees.roster);
        return yankees;
    }
}