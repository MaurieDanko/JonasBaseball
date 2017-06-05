package console.jonas;

import java.util.Random;

/**
 * Created by jonas on 2017-05-18.
 */
public class Game {
    Team home, away;
    Game(Team hometeam, Team away){
        this.home = hometeam;
        this.away = away;

    }
    void playgame (){

        // GAME Run Variable
        boolean loop = true;

        // Bases
        String baseOne = null; // The First Base
        String baseTwo = null; // The Second Base
        String baseThree = null; // The Third Base
        String homeplate = null; // Home Variable

        // Players Involved
        String name[] = { "Adam", "Alan", "Andrew", "Arthur", "Antony", "Benny", "Boyd", "Buck", "Bruce", "Chase",
                "Chris", "Cory", "Carol", "Casey", "Don", "Danny", "Derek", "James", "Jacob", "Jamie", "Jeffery",
                "Jarrod", "Spike", "Sidney", "Sean", "Sam", "Reid", "Raheem", "Rene", "Richie", "Tony", "Thomas",
                "Terry", "Teddy", "Trey", "Emmet", "Elmo", "Eben", "Edward" };

        // BaseBall Calls & Probability
        String call[] = { "Homerun", "Single", "Single", "Single", "Single", "Double", "Double", "Triple", "Out", "Out",
                "Out", "Out", "Out", "Out" };

        // Team Score
        int teamScore1 = 0;
        int teamScore2 = 0;

        int outs = 0; // Number of Outs
        GAME: while (loop) {
            for (int inn = 1; inn < 4; inn++) {

                // Variables
                outs = 0;
                // TEAM ONE SIMULATION


                // Team One Variables
                String RandomName = (name[new Random().nextInt(name.length)]);
                String Outcome = (call[new Random().nextInt(call.length)]);

                System.out.println("-----------------SCORE BOARD-----------------");
                System.out.print("| Team Batting: " + home.teamName + " ");
                System.out.println("| Outcome: " + Outcome);
                System.out.println("| Outs: " + outs);
                System.out.println("|\t\t\t\t\t\t\t\t\tInning: " + inn);
                System.out.println("| \t\t\t\t\t\t\t\t\t" + home.teamName + ": " + teamScore1);
                System.out.println("| \t\t\t\t\t\t\t\t\t" + away.teamName + ": " + teamScore2);
                System.out.println("| Player Batting: " + RandomName);
                System.out.println("| First Base: " + baseOne);
                System.out.println("| Second Base: " + baseTwo);
                System.out.println("| Third Base: " + baseThree);

                System.out.println("");

                // IF OutCome is a single
                if (Outcome.equals("Single")) {

                    // Check if Base One is Empty
                    if (baseOne == null) {
                        baseOne = RandomName;
                    }

                    // IF BASE ONE IS FULL
                    else if (baseOne != null) {
                        if (baseTwo != null) {
                            homeplate = baseThree;
                            baseThree = baseTwo;
                            baseTwo = baseOne;
                            baseOne = RandomName;

                            if (homeplate != null) {
                                teamScore1++;
                            }
                        } else if (baseTwo == null) {
                            baseTwo = baseOne;
                            baseOne = RandomName;
                        }
                    }
                }

                // IF OutCome is a Double
                else if (Outcome.equals("Double")) {

                    // Checking if baseTwo and base Three are Full
                    if (baseTwo != null && baseThree != null) {
                        baseThree = baseOne;
                        baseTwo = RandomName;
                        baseOne = null;

                        teamScore1++;
                    }
                    // Checking if Base Two is empty
                    if (baseTwo == null) {
                        baseTwo = RandomName;

                    }

                    // Checking Base Two When full
                    else if (baseTwo != null) {
                        teamScore1++;
                        baseTwo = RandomName;
                    }

                    // Checking Base Three When Full
                    if (baseThree != null) {
                        if (baseOne != null) {
                            homeplate = baseThree;
                            baseThree = baseOne;
                            baseOne = null;

                            teamScore1++;
                        } else if (baseOne == null) {
                            teamScore1++;
                            baseThree = null;
                        }
                    }

                    // Checking Base Three When NOT Full
                    if (baseThree == null) {
                        if (baseOne != null) {
                            baseThree = baseOne;
                            baseOne = null;

                        }

                    }

                }

                // IF OutCome is a Triple
                else if (Outcome.equals("Triple")) {
                    if (baseThree == null) {
                        baseThree = RandomName;
                    }

                    // When All Three Bases Are fULL
                    else if (baseThree != null && baseTwo != null && baseOne != null) {
                        baseThree = RandomName;
                        baseTwo = null;
                        baseOne = null;
                        teamScore1 += 3;
                    }
                    // When Base Three ALONE is full
                    else if (baseThree != null) {
                        baseThree = RandomName;
                        teamScore1++;
                    }

                    // Combinations with 2
                    if ((baseThree != null && baseTwo != null) || (baseOne != null && baseThree != null)
                            || (baseTwo != null && baseOne != null)) {
                        if (baseThree != null && baseTwo != null) {
                            baseThree = RandomName;
                            baseTwo = null;
                        } else if (baseOne != null && baseThree != null) {
                            baseOne = null;
                            baseThree = RandomName;
                        } else if (baseTwo != null && baseOne != null) {
                            baseTwo = null;
                            baseOne = null;
                        }
                        teamScore1 += 2;
                    }

                }

                // IF Outcome is a Homerun
                else if (Outcome.equals("Homerun")) {

                    if (baseOne != null) {
                        baseOne = null;
                        teamScore1++;
                    }
                    if (baseTwo != null) {
                        baseTwo = null;
                        teamScore1++;
                    }
                    if (baseThree != null) {
                        baseThree = null;
                        teamScore1++;
                    }

                }

                else if (Outcome.equals("Out")) {
                    outs++;
                }

            } while (outs <= 2);

        }
    }
}



