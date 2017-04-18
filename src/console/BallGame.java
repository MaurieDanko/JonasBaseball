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

            String outcome;
            float rnd = random.nextFloat();
            if (rnd < .300) { // hit
                if (rnd < .05)
                    outcome = "Homerun";
                else if (rnd < .100)
                    outcome = "Triple";
                else if (rnd < .200)
                    outcome = "Double";
                else
                    outcome = "Single";
            } else { // .700 not hit
                outcome = "out";
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
                case "Single":
                    if (base3 != null)
                        ++score[team];
                    base3 = base2;
                    base2 = base1;
                    base1 = batter;
                    break;

                case "Double":
                    if (base3 != null)
                        ++score[team];
                    if (base2 != null)
                        ++score[team];
                    base3 = base1;
                    base2 = batter;
                    base1 = null;
                    break;

                case "Triple":
                    if (base3 != null)
                        ++score[team];
                    if (base2 != null)
                        ++score[team];
                    if (base1 != null)
                        ++score[team];
                    base3 = batter;
                    base2 = base1 = null;
                    break;

                case "Homerun":
                    if (base3 != null)
                        ++score[team];
                    if (base2 != null)
                        ++score[team];
                    if (base1 != null)
                        ++score[team];
                    ++score[team];
                    base3 = base2 = base1 = null;
                    break;

                default:
                    outs++;
                    break;
            }
        }
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