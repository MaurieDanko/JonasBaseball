package console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mdanko on 2017-04-18.
 */
public class Player {
    int id;
    String lastName, firstName;
    String pos;
    int gp, ab, runs, hits, singles, doubles, triples, homers, rbis, walks, strikeouts, steals, caughts;
    float avg, obp, slg, ops;

    private Player(Scanner input) {
        id = input.nextInt();
        lastName = input.next();
        while (!lastName.endsWith(","))
            lastName += " " + input.next();
        firstName = input.next();
        pos = input.next();

        gp = input.nextInt();
        ab = input.nextInt();
        runs = input.nextInt();
        hits = input.nextInt();
        doubles = input.nextInt();
        triples = input.nextInt();
        homers = input.nextInt();
        rbis = input.nextInt();
        walks = input.nextInt();
        strikeouts = input.nextInt();
        steals = input.nextInt();
        caughts = input.nextInt();

        avg = input.nextFloat();
        obp = input.nextFloat();
        slg = input.nextFloat();
        ops = input.nextFloat();

        lastName = lastName.substring(0, lastName.length() - 1); // trim ','
        singles = hits - doubles - triples - homers;
    }

    BallGame.AtBatOutcome swing() {
        float rnd = BallGame.random.nextFloat();
        float prob = (float)homers / ab;
        if (rnd < prob)
            return BallGame.AtBatOutcome.HOMERUN;

        prob += (float)triples / ab;
        if (rnd < prob)
            return BallGame.AtBatOutcome.TRIPLE;

        prob += (float)doubles / ab;
        if (rnd < prob)
            return BallGame.AtBatOutcome.DOUBLE;

        prob += (float)singles / ab;
        if (rnd < prob)
            return BallGame.AtBatOutcome.SINGLE;

        prob += (float)walks / ab;
        if (rnd < prob)
            return BallGame.AtBatOutcome.WALK;

        prob += (float)strikeouts / ab;
        if (rnd < prob)
            return BallGame.AtBatOutcome.STRIKEOUT;

        return BallGame.AtBatOutcome.OUT;
    }


    @Override
    public String toString() {
        return getName();
    }

    String getName() {
        return firstName + " " + lastName;
    }

    static List<Player> Players = new ArrayList<>();

    static {
        try {
            Scanner input = new Scanner(new File("data/hitter_stats"));
            for (; ; ) {
                if (!input.hasNextLine())
                    break;

                Players.add(new Player(input));
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
