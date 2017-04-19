package console;

/**
 * Created by mdanko on 2017-04-18.
 */
public class Team {
    String firstName, lastName, nickNmae;
    Player[] players;

    Team(String lastName, int team) {
        this.lastName = lastName;
        players = new Player[9];
        for (int i = 0; i < players.length; ++i)
            players[i] = Player.Players.get(10 * i + team);
    }

    String getName() {
        return lastName;
    }
}
