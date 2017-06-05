package console.mdanko;

import java.util.Date;

/**
 * Created by jonas on 2017-04-17.
 */
public class Game {
    Date scheduledStart;
    Umpire umpire;
    Team home, away;
    Date firstPitch;

    void start() {
        umpire = new Umpire();
        away = new Team();
        home = new Team();
        umpire.sharesGroundRules();
        firstPitch = umpire.startsGame();
    }
}
