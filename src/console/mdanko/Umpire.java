package console.mdanko;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jonas on 2017-04-17.
 */
public class Umpire {

    void sharesGroundRules() {
        System.out.print("UMPIRE:\n"
                + "\there are the game rules:\n"
                + "\tno biting\n"
                + "\tno swearing\n"
                + "\tif ball goes out of play then base runner is awarded base approaching PLUS 1 more\n"
                + "\tplease shake hands and good luck to both teams\n"
        );
    }

    Date startsGame() {
        System.out.println("PLAY BALL !");
        return Calendar.getInstance().getTime(); // now
    }
}
