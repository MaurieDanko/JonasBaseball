package console.jonas;

/**
 * Created by jonas on 2017-05-18.
 */
public class Main {
    public static void main(String args[]){
        Team team1 = Team.jays();
        Team team2 = Team.yankees();

        Game game = new Game(team1, team2);
        game.playgame();
    }

}
