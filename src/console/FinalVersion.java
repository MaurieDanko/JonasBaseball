package console;

import java.util.Random;
import java.util.Scanner;

public class FinalVersion {
	public static void main(String args[]) throws InterruptedException {
		// SCANNER VARIABLE
		Scanner input = new Scanner(System.in); // This is the Scanner variable


		//

		// GAME Run Variable
		boolean loop = true;

		// Game Speed
		int gameSpeed;

		//jonas was here

		// Team Name Variables
		String teamName1; // First Team Name
		String teamName2; // Second Team Name

		// Bases
		String baseOne = null; // The First Base
		String baseTwo = null; // The Second Base
		String baseThree = null; // The Third Base
		String home = null; // Home Variable

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
		String choice; // User Choice

		// INTRODUCTORY
		System.out.println("Assignment 3: BaseballGame Simulator \n");
		System.out.println("\t********** BY MOUJTABAH KARIM **********");
		System.out.println("Note: Be sure to replace a space with a underscore when naming your team; Example:");
		System.out.println("      Blue Jays ----> Blue_Jays");
		System.out.println();

		// Getting User Input For Team Names + Validation
		TEAM:
		do {

			// Getting First Team Name
			System.out.println("Enter The Name Of The First Team: ");
			teamName1 = input.next();

			// Getting Second Team Name
			System.out.println("Enter The Name Of The Second Team: ");
			teamName2 = input.next();

			// Validation
			System.out.println();
			System.out.println("--------------------------------------------");
			System.out.println("\t" + teamName1 + " VS " + teamName2);

			System.out.println(" Would You Like To Change The Teams (y/n)");
			System.out.println("--------------------------------------------");
			choice = input.next();

		} while (choice.equals("Y") || choice.equals("y")); // Condition based
														

		System.out.println("How Fast Would You Like The Simulator To Run?");
		System.out.println("1. > Slow");
		System.out.println("2. > Normal");
		System.out.println("3. > Fast");
		System.out.println("4. > Crazy Fast");
		gameSpeed = input.nextInt();

		// Fake Loading screen
		System.out.println("*******************************");
		System.out.println("* LOADING GAME... PLEASE WAIT *");
		System.out.println("*******************************");

		// GAME LOOP
		GAME: while (loop) {
			for (int inn = 1; inn < 4; inn++) {

				// Variables
				outs = 0;

				// TEAM ONE SIMULATION
				do {
					if (gameSpeed == 1) {
						Thread.sleep(1000);
					} else if (gameSpeed == 2) {
						Thread.sleep(700);
					} else if (gameSpeed == 3) {
						Thread.sleep(400);
					} else if (gameSpeed == 3) {
						Thread.sleep(100);
					}

					// Team One Variables
					String RandomName = (name[new Random().nextInt(name.length)]);
					String Outcome = (call[new Random().nextInt(call.length)]);

					System.out.println("-----------------SCORE BOARD-----------------");
					System.out.print("| Team Batting: " + teamName1 + " ");
					System.out.println("| Outcome: " + Outcome);
					System.out.println("| Outs: " + outs);
					System.out.println("|\t\t\t\t\t\t\t\t\tInning: " + inn);
					System.out.println("| \t\t\t\t\t\t\t\t\t" + teamName1 + ": " + teamScore1);
					System.out.println("| \t\t\t\t\t\t\t\t\t" + teamName2 + ": " + teamScore2);
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
								home = baseThree;
								baseThree = baseTwo;
								baseTwo = baseOne;
								baseOne = RandomName;

								if (home != null) {
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
								home = baseThree;
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

				// TEAM TWO SIMULATION
				outs = 0;

				do {// Team Two Variables
					String RandomName = (name[new Random().nextInt(name.length)]);
					String Outcome = (call[new Random().nextInt(call.length)]);

					System.out.println("-----------------SCORE BOARD-----------------  ");
					System.out.println();
					System.out.print("| Team Batting: " + teamName2 + " ");
					System.out.println("| Outcome: " + Outcome);
					System.out.println("| Outs: " + outs);
					System.out.println("|\t\t\t\t\t\t\t\t\tInning: " + inn);
					System.out.println("| \t\t\t\t\t\t\t\t\t" + teamName1 + ": " + teamScore1);
					System.out.println("| \t\t\t\t\t\t\t\t\t" + teamName2 + ": " + teamScore2);
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
								home = baseThree;
								baseThree = baseTwo;
								baseTwo = baseOne;
								baseOne = RandomName;

								if (home != null) {
									teamScore2++;
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

							teamScore2++;
						}
						// Checking if Base Two is empty
						if (baseTwo == null) {
							baseTwo = RandomName;
						}

						// Checking Base Two When full
						else if (baseTwo != null) {
							teamScore2++;
							baseTwo = RandomName;
						}

						// Checking Base Three When Full
						if (baseThree != null) {
							if (baseOne != null) {
								home = baseThree;
								baseThree = baseOne;
								baseOne = null;

								teamScore2++;
							} else if (baseOne == null) {
								teamScore2++;
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
							teamScore2 += 3;
						}
						// When Base Three ALONE is full
						else if (baseThree != null) {
							baseThree = RandomName;
							teamScore2++;
						}

						// combinations with two
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
							teamScore2 += 2;
						}

					}

					// IF Outcome is a Homerun
					else if (Outcome.equals("Homerun")) {

						// Checks if baseOne has someone
						if (baseOne != null) {
							baseOne = null;
							teamScore2++;
						}
						// Checks if base 2 has someone
						if (baseTwo != null) {
							baseTwo = null;
							teamScore2++;
						}
						// Checks if base 3 has someone
						if (baseThree != null) {
							baseThree = null;
							teamScore2++;
						}

					}
					// Counts the outs
					else if (Outcome.equals("Out")) {
						outs++;
					}

					// Will keep running until the number of outs is 2 (because
					// it is a do-while loop)
				} while (outs <= 2);

			}
			// If it's a tie
			if (teamScore1 == teamScore2) {
				continue GAME; // Continues back to the loop
			}

			// Otherwise name the winner and break
			else {
				if (teamScore1 > teamScore2) {
					System.out.println("*****  " + teamName1 + " have won!  *****");
				} else if (teamScore2 > teamScore1) {
					System.out.println("*****  " + teamName2 + " have won!  *****");
				}

				break; // Code used to break out of the loop
			}
		}
	}
}
