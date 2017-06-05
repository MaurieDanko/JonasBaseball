package console.mdanko;

/**
 * Created by mdanko on 2017-04-18.
 */
public class Team {
    String firstName, lastName, nickName;
    Player[] players;

    Team(String name, int team) {
        nickName = name;
        players = new Player[9];
        for (int i = 0; i < players.length; ++i)
            players[i] = Player.Players.get(10 * i + team);
    }

    String getName() {
        return lastName;
    }
}
