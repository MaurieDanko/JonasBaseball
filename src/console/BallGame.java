package console;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BallGame {
    public static void main(String args[]) throws InterruptedException {
        new BallGame().play();
    }

    Random random = new Random();
    Scanner input = new Scanner(System.in);
    int gameSpeed;
    String[] teamName = {"blue jays", "yankees"};
    int[] score = new int[2];

    BallGame() {
        System.out.println("Assignment 3: Baseball Simulator \n");
        System.out.println("\t********** BY MOUJTABAH KARIM **********");
        System.out.println();

        input.useDelimiter("\n");
        getTeams();
        setGameSpeed();
    }

    void getTeams() {
        for (; ; ) {
            System.out.println("--------------------------------------------");
            System.out.println("\t" + teamName[AWAY] + " VS " + teamName[HOME]);

            System.out.println(" Would You Like To Change The Teams (y/n)");
            String choice = input.next();
            if (!choice.toUpperCase().equals("Y"))
                break;

            System.out.println("Enter the name of VISITING team: ");
            teamName[AWAY] = input.next();

            System.out.println("Enter the name of HOME Team: ");
            teamName[HOME] = input.next();
        }
    }

    void setGameSpeed() {
        for (; ; ) {
            try {
                System.out.println("How Fast Would You Like The Simulator To Run?");
                System.out.println("1. > Slow");
                System.out.println("2. > Normal");
                System.out.println("3. > Fast");
                System.out.println("4. > Crazy Fast");
                gameSpeed = input.nextInt();
                if (gameSpeed >= 1 && gameSpeed <= 4)
                    return;
            } catch (InputMismatchException x) {
                input.nextLine();
            }
            System.out.println("invalid input. try again!\n\n");
        }
    }

    void play() {
        for (int inning = 1; ; inning++) {
            atBat(AWAY, inning);
            atBat(HOME, inning);

            if (inning >= MAX_INNINGS && score[AWAY] != score[HOME])
                break;

            try {
                Thread.sleep(DELAYS[gameSpeed]);
            } catch (InterruptedException x) {
                // ignore
            }
        }
        String winner = score[AWAY] > score[HOME] ? teamName[AWAY] : teamName[HOME];
        System.out.println("*****  " + winner + " have won!  *****");
    }

    void atBat(int team, int inning) {
        String base1 = null; // 1st Base
        String base2 = null; // 2nd Base
        String base3 = null; // 3rd Base
        int outs = 0; // Number of Outs

        while (outs < 3) {
            if (inning == MAX_INNINGS && team == HOME && score[HOME] > score[AWAY])
                return;

            String batter = (PLAYERS[random.nextInt(PLAYERS.length)]);

            AtBatOutcome outcome;
            float rnd = random.nextFloat();
            if (rnd < .300) { // hit
                if (rnd < .007)
                    outcome = AtBatOutcome.HOMERUN;
                else if (rnd < .03)
                    outcome = AtBatOutcome.TRIPLE;
                else if (rnd < .07)
                    outcome = AtBatOutcome.DOUBLE;
                else
                    outcome = AtBatOutcome.SINGLE;
            } else { // .700 not hit
                if (rnd < .400)
                    outcome = AtBatOutcome.WALK;
                else if (rnd < .500)
                    outcome = AtBatOutcome.STRIKEOUT;
                else
                    outcome = AtBatOutcome.OUT;
            }

            System.out.println("-----------------SCORE BOARD-----------------  ");
            System.out.println("| Outs: " + outs);
            System.out.println("| \t\t\t\t\t\t\t\t\tInning: " + inning + HALF_INNING[team]);
            System.out.println("| \t\t\t\t\t\t\t\t\t" + teamName[AWAY] + ": " + score[AWAY]);
            System.out.println("| \t\t\t\t\t\t\t\t\t" + teamName[HOME] + ": " + score[HOME]);
            System.out.println("| First Base: " + base1);
            System.out.println("| Second Base: " + base2);
            System.out.println("| Third Base: " + base3);
            System.out.println("| " + teamName[1] + " Batter: " + batter + " => " + outcome);

            switch (outcome) {
                case WALK:
                    if (base1 != null) {
                        if (base2 != null) {
                            if (base3 != null)
                                ++score[team];
                            base3 = base2;
                        }
                        base2 = base1;
                    }
                    base1 = batter;
                    break;

                case SINGLE:
                    if (base3 != null)
                        ++score[team];
                    base3 = base2;
                    base2 = base1;
                    base1 = batter;
                    break;

                case DOUBLE:
                    if (base3 != null)
                        ++score[team];
                    if (base2 != null)
                        ++score[team];
                    base3 = base1;
                    base2 = batter;
                    base1 = null;
                    break;

                case TRIPLE:
                    if (base3 != null)
                        ++score[team];
                    if (base2 != null)
                        ++score[team];
                    if (base1 != null)
                        ++score[team];
                    base3 = batter;
                    base2 = base1 = null;
                    break;

                case HOMERUN:
                    if (base3 != null)
                        ++score[team];
                    if (base2 != null)
                        ++score[team];
                    if (base1 != null)
                        ++score[team];
                    ++score[team];
                    base3 = base2 = base1 = null;
                    break;

                case OUT:
                case STRIKEOUT:
                    outs++;
                    break;

                default:
                    throw new RuntimeException("unknown hitting outcome");
            }
        }
    }

    enum AtBatOutcome {
        OUT, SINGLE, DOUBLE, TRIPLE, HOMERUN, WALK, STRIKEOUT;
    }

    static int HOME = 1, AWAY = 0;
    static int MAX_INNINGS = 9;
    static String[] HALF_INNING = {"^", "v"};
    static String[] PLAYERS = {
            "Adam", "Alan", "Andrew", "Arthur", "Antony", "Benny", "Boyd", "Buck", "Bruce", "Chase",
            "Chris", "Cory", "Carol", "Casey", "Don", "Danny", "Derek", "James", "Jacob", "Jamie", "Jeffery",
            "Jarrod", "Spike", "Sidney", "Sean", "Sam", "Reid", "Raheem", "Rene", "Richie", "Tony", "Thomas",
            "Terry", "Teddy", "Trey", "Emmet", "Elmo", "Eben", "Edward"
    };
    static long[] DELAYS = {1000, 700, 400, 100, 0};
}