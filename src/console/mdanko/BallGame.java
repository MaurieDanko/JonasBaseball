package console.mdanko;

import java.util.Random;
import java.util.Scanner;

public class BallGame {
    public static void main(String args[]) throws InterruptedException {
        new BallGame().play();
    }

    static Random random = new Random();
    Scanner input = new Scanner(System.in);
    int[] score = new int[2];
    int[] batter = new int[2];
    Team[] team = {
            new Team("blue jays", AWAY),
            new Team("yankees", HOME)
    };

    BallGame() {
        System.out.println("Assignment 3: Baseball Simulator \n");
        System.out.println("\t********** BY MOUJTABAH KARIM **********");
        System.out.println();

        input.useDelimiter("\n");
        getTeams();


        System.out.println(String.format("%25s %25s", team[AWAY].getName(), team[HOME].getName()));
        for (int i = 0; i < 9; ++i)
            System.out.println(String.format("%25s %25s", team[AWAY].players[i], team[HOME].players[i]));
    }

    void getTeams() {
        for (; ; ) {
            System.out.println("--------------------------------------------");
            System.out.println("\t" + team[AWAY].getName() + " VS " + team[HOME].getName());

            System.out.println(" Would You Like To Change The Teams (y/n)");
//            String choice = input.next();
            String choice = "n";
            if (!choice.toUpperCase().equals("Y"))
                break;

            System.out.println("Enter the name of VISITING team: ");
            team[AWAY].lastName = input.next();

            System.out.println("Enter the name of HOME Team: ");
            team[HOME].lastName = input.next();
        }
    }

    void play() {
        for (int inning = 1; ; ++inning) {
            atBat(AWAY, inning);
            atBat(HOME, inning);

            if (inning >= MAX_INNINGS && score[AWAY] != score[HOME])
                break;
        }
        System.out.println("=======================================");
        int winner = score[AWAY] > score[HOME] ? AWAY : HOME;
        System.out.println("\n<<<<<  "
                + team[winner].getName() + " WIN " + score[winner] + "-" + score[1-winner]
                + " >>>>>"
        );
    }

    void atBat(int teamAtBat, int inning) {
        Player base1 = null; // 1st Base
        Player base2 = null; // 2nd Base
        Player base3 = null; // 3rd Base
        int outs = 0; // Number of Outs

        while (outs < 3) {
            if (inning == MAX_INNINGS && teamAtBat == HOME && score[HOME] > score[AWAY])
                return;

            Player pBatter = team[teamAtBat].players[batter[teamAtBat]];
            AtBatOutcome outcome = pBatter.swing();

            System.out.println("-----------------SCORE BOARD-----------------  ");
            System.out.println("| Outs: " + outs);
            System.out.println("| \t\t\t\t\t\t\t\t\tInning: " + inning + HALF_INNING[teamAtBat]);
            System.out.println("| \t\t\t\t\t\t\t\t\t" + team[AWAY].lastName + ": " + score[AWAY]);
            System.out.println("| \t\t\t\t\t\t\t\t\t" + team[HOME].lastName + ": " + score[HOME]);
            System.out.println("| First Base: " + base1);
            System.out.println("| Second Base: " + base2);
            System.out.println("| Third Base: " + base3);
            System.out.println("| " + team[teamAtBat].lastName
                    + " Batter #" + batter[teamAtBat] + " "
                    + pBatter.getName() + " " + String.format("%3.3f", pBatter.avg)
                    + " => " + outcome);

            switch (outcome) {
                case WALK:
                    if (base1 != null) {
                        if (base2 != null) {
                            if (base3 != null)
                                ++score[teamAtBat];
                            base3 = base2;
                        }
                        base2 = base1;
                    }
                    base1 = pBatter;
                    break;

                case SINGLE:
                    if (base3 != null)
                        ++score[teamAtBat];
                    base3 = base2;
                    base2 = base1;
                    base1 = pBatter;
                    break;

                case DOUBLE:
                    if (base3 != null)
                        ++score[teamAtBat];
                    if (base2 != null)
                        ++score[teamAtBat];
                    base3 = base1;
                    base2 = pBatter;
                    base1 = null;
                    break;

                case TRIPLE:
                    if (base3 != null)
                        ++score[teamAtBat];
                    if (base2 != null)
                        ++score[teamAtBat];
                    if (base1 != null)
                        ++score[teamAtBat];
                    base3 = pBatter;
                    base2 = base1 = null;
                    break;

                case HOMERUN:
                    if (base3 != null)
                        ++score[teamAtBat];
                    if (base2 != null)
                        ++score[teamAtBat];
                    if (base1 != null)
                        ++score[teamAtBat];
                    ++score[teamAtBat];
                    base3 = base2 = base1 = null;
                    break;

                case OUT:
                case STRIKEOUT:
                    outs++;
                    break;

                default:
                    throw new RuntimeException("unknown hitting outcome");
            }
            batter[teamAtBat] = (1 + batter[teamAtBat]) % team[teamAtBat].players.length;
        }
    }

    enum AtBatOutcome {
        OUT, SINGLE, DOUBLE, TRIPLE, HOMERUN, WALK, STRIKEOUT;
    }

    static int HOME = 1, AWAY = 0;
    static int MAX_INNINGS = 9;
    static String[] HALF_INNING = {"^", "v"};
}