package console.jonas;

import java.util.ArrayList;
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
        jays.roster = new ArrayList<Player>() ;

        String name[] = { "Jose Bautista", "Alan", "Andrew", "Arthur", "Antony", "Benny", "Boyd", "Buck", "Bruce", "Chase",
                "Chris", "Cory", "Carol", "Casey", "Don", "Danny", "Derek", "James", "Jacob", "Jamie", "Jeffery",
                "Jarrod", "Spike", "Sidney", "Sean", "Sam", "Reid", "Raheem", "Rene", "Richie", "Tony", "Thomas",
                "Terry", "Teddy", "Trey", "Emmet", "Elmo", "Eben", "Edward" };
        for (int i=0;i<name.length;++i){

            String playername = name[i];
            Player p = new Player();
            p.name = playername;

        }

        return jays;
    }

    static Team yankees(){
        Team yankees = new Team();
        yankees.teamName = "New York Yankees";
        return yankees;

    }
}
