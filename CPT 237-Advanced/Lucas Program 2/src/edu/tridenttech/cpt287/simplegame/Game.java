package edu.tridenttech.cpt287.simplegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
	final public int ATTACK_LOSS_FACTOR = 5;
	final public int DEFEND_LOSS_FACTOR = 6;
	final public int MAX_STRENGTH = 10;
	final public int DEFAULT_STRENGTH = 6;
	final public int MAX_ABILITIES = 15;

	private static Game instance;

	Scanner console = new Scanner(System.in);
	Random rand = new Random();

	private ArrayList<Obstacle> opponents = new ArrayList<>();
	// list of players to cycle through
	private ArrayList<Player> players = new ArrayList<>();
	// list to rank players when they die or the game is complete
	private ArrayList<Player> rankings = new ArrayList<>();

	private Game() {
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public void loadOpponents(String fileName) {
		// lineCount tracks how many lines in the opponents file have
		// been read to report which line improper data appeared on
		int lineCount = 1;

		// Scanner set to NULL to check for proper file input
		Scanner fileInput = null;
		while (fileInput == null) {

			// If file is not found, the program gives the user a
			// chance to enter a new file path or quit
			try {
				fileInput = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				System.out.println("................................");
				System.out.println("Incorrect File Name or File Path");
				System.out.println(" Please Enter Another File Path ");
				System.out.println(" OR Type 'quit' To End Program  ");
				System.out.println("................................");

				fileName = console.nextLine();
				if (fileName.toLowerCase().equals("quit")) {
					System.out.println("................................");
					System.out.println("       See You Next Time!       ");
					System.out.println("................................");
					System.exit(1);
				}
			}
		}
		// reads file and creates Obstacle objects according to file content
		while (fileInput.hasNextLine()) {

			String line = fileInput.nextLine();
			String[] fields = line.split(",");

			String name = fields[0];
			switch (name) {
			case "Wall":
				try {
					Wall wall = new Wall();
					opponents.add(wall);
				} catch (Exception e1) {
					invalidMonsterMessage(line, lineCount);
					break;
				}
				break;

			case "Troll":
				try {
					Troll troll = new Troll(Integer.parseInt(fields[1]));
					opponents.add(troll);
				} catch (NumberFormatException e1) {
					invalidMonsterMessage(line, lineCount);
					break;
				}
				break;

			case "Goblin":
				try {
					Goblin goblin = new Goblin(Integer.parseInt(fields[1]));
					opponents.add(goblin);
				} catch (NumberFormatException e1) {
					invalidMonsterMessage(line, lineCount);
					break;
				}
				break;

			case "Jinn":
				try {
					Jinn jinn = new Jinn(Integer.parseInt(fields[1]));
					opponents.add(jinn);
				} catch (NumberFormatException e1) {
					invalidMonsterMessage(line, lineCount);
					break;
				}
				break;

			// creates new enemy if type class doesn't exist, handles improper line
			// formatting, prints
			// lone number and line contents of error line
			default:

				// uses regex to check for letters only in monster name
				if (!name.matches(".*[a-z].*")) {
					invalidMonsterMessage(line, lineCount);
					break;
				}
				try {
					Enemy enemy = new Enemy(name, Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),
							Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
					opponents.add(enemy);
					break;
				} catch (ArrayIndexOutOfBoundsException e) {
					invalidMonsterMessage(line, lineCount);
					break;
				}

				catch (NumberFormatException e) {
					invalidMonsterMessage(line, lineCount);
					break;
				}

				catch (InputMismatchException e) {
					invalidMonsterMessage(line, lineCount);
					break;
				}
			}
			lineCount++;
		}

		// manually load opponent list
		/*
		 * opponents.add(new Troll(1,15)); opponents.add(new Jinn(1)); opponents.add(new
		 * Wall()); opponents.add(new Troll(0,15)); opponents.add(new Goblin(1));
		 * opponents.add(new Troll(1,15)); opponents.add(new Wall()); opponents.add(new
		 * Troll(0,15)); opponents.add(new Goblin(1)); opponents.add(new Jinn(0));
		 */
	}

	private void invalidMonsterMessage(String line, int lncnt) {
		System.out.println("................................");
		System.out.println("     Invalid Monster Entry!     ");
		System.out.printf("     Contents: %s%n", line);
		System.out.printf("     Line: %s%n", lncnt);
		
	}

	public void play() {
		Player playerObject;
		String name;
		int strength = 0;
		Obstacle badGuy = null;
		String morePlayers = "";
		
		
		// have we loaded the opponents?
		if (opponents.size() == 0) {
			System.err.println("No opponents have been loaded");
			return;
		}

		System.out.println("....................................");
		System.out.println(" Welcome To Grognor's Room Of Doom!");
		System.out.println("...........xxxxxxxxxxxxxx...........");
		System.out.println("...........x............x...........");
		System.out.println("...........x...0....0...x...........");
		System.out.println("...........x.....xx.....x...........");
		System.out.println("............x...vvvv...x............");
		System.out.println(".............x..^^^^..x.............");
		System.out.println("..............xxxxxxxx..............");
		System.out.println("....................................");

		// loop to add players
		while (!morePlayers.equals("begin")) {

			System.out.print("Please Enter A Name For Your Player:\n");
			name = console.nextLine();

			System.out.println(".....................................");
			System.out.printf("Please Enter The Strength For %s.[1-10]%n", name);
			System.out.println(".....................................");

			// checks for input to be numbers
			while (console.hasNext()) {
				if (console.hasNextInt()) {
					strength = console.nextInt();
					console.nextLine();
					break;
				} else {
					System.err.println("************************************");
					System.err.println("Invalid input: " + console.next() + "!");
					System.err.println("Please Input A Number Between (1-9):");
					System.err.println("************************************");
				}
			}

			if (strength > MAX_STRENGTH || strength < 1) {
				System.err.println("***************************************");
				System.err.printf("invalid strength, setting to default(%d)%n", DEFAULT_STRENGTH);
				System.err.println("***************************************");
				strength = DEFAULT_STRENGTH;
			}

			playerObject = new Player(name, strength, MAX_ABILITIES - strength);
			players.add(playerObject);

			System.out.println(".......................................");
			System.out.println("  Hit (Enter) To Create Another Player ");
			System.out.println("         Type 'begin' To Play!         ");
			System.out.println(".......................................");
			morePlayers = console.nextLine();
		}

		// print every opponent in opponent list
		System.out.println("..................................");
		System.out.println("  There Are Many Obstacles Ahead: ");
		System.out.println("..................................");

		/*
		 * opponents is reversed so the print out list will reflect the proper order of
		 * Obstacles. It is then reversed again to go back to proper call order.
		 */

		Collections.reverse(opponents);

		for (Obstacle ob : opponents) {
			System.out.println(ob.getName());
		}

		Collections.reverse(opponents);

		// while loop to play game only runs while there are objects in ArrayList
		// players
		while (players.size() > 0) {
			while (opponents.size() > 0) {
				for (int i = 0; i < players.size(); i++) {
					Player player = players.get(i);
					int selection;
					// get the opponent at the top of the list
					if (badGuy == null || !badGuy.isAlive()) {
						badGuy = opponents.remove(opponents.size() - 1);
					}
					// display information to the user
					show(player);
					show(badGuy);
					selection = getMenuSelection();
					switch (selection) {
					case 'R':
						System.out.println("RETREAT!");
						retreat(player, badGuy);
						if (!badGuy.isAlive()) {
							// announce the defeat and award points
							System.out.printf("Congratulations!  You have defeated %s%n", badGuy.getName());
							player.addPoints(badGuy.getPoints());
						} else {
							// return the opponent to the list
							opponents.add(rand.nextInt(opponents.size() - 1), badGuy);
							badGuy = null;
						}
						break;
					case 'A':
						System.out.println("ATTACK!");
						player.attack(badGuy);
						if (!badGuy.isAlive()) {
							System.out.printf("Congratulations!  You have defeated %s%n", badGuy.getName());
							player.addPoints(badGuy.getPoints());
						}
						break;
					case 'D':
						System.out.println("DRINK!");
						drink(player);
						break;
					case 'Q':
						System.out.println("QUIT!");
						rankings.add(player);
						players.remove(player);
						i--;
						break;
					}

					if (!player.isAlive()) {
						System.out.printf("Sorry, %s! You have been killed %n", player.getName());
						rankings.add(player);
						players.remove(player);
						i--;
					}

					// happens if all obstacles are defeated
					if (opponents.size() == 0) {

						// adds remaining players to rankings list
						rankings.addAll(players);
						System.out.println(".....................................");
						System.out.println("      All Obstacles Defeated!!!      ");
						System.out.println(".....................................");
						System.out.println("Rankings:");
						int c = 1;

						// sorts rankings in descending order by player points
						Collections.sort(rankings);
						for (Player p : rankings) {
							System.out.printf("%d.%s Points: %d%n", c, p.getName(), p.getPoints());
							c++;
						}
						return;
					}

					// happens if all players die, sorts rankings list and displays
					if (players.size() == 0) {

						System.out.println(".....................................");
						System.out.println("          No One Survived!!!         ");
						System.out.println(".....................................");
						System.out.println("Rankings:");

						int c = 1;
						Collections.sort(rankings);
						for (Player p : rankings) {
							System.out.printf("%d.%s Points: %d%n", c, p.getName(), p.getPoints());
							c++;
						}
						return;
					}
				}

			}
		}
	}

	private int getMenuSelection() {
		char selection = 'x';

		System.out.println("What would you like to do?");
		System.out.println("(R)etreat");
		System.out.println("(A)ttack");
		System.out.println("(D)rink vial");
		System.out.println("(Q)uit");
		selection = console.next().charAt(0);
		return Character.toUpperCase(selection);
	}

	private void show(GameEntity o) {
		System.out.println("-----------------------------");
		System.out.printf("Name:     %10s%n", o.getName());
		System.out.printf("Strength: %10d%n", o.getStrength());
		System.out.printf("Health:   %10d%n", o.getHealth());
		System.out.printf("Points:   %10d%n", o.getPoints());
		if (o instanceof Collector) {
			System.out.printf("Vials:   %11d%n", ((Collector) o).getNumVials());
		}
	}

	// Note: may be called from player (attack) or enemy (retreat)
	public void attack(Warrior attacker, GameEntity defender) {
		GameEntity winner = null; //
		GameEntity loser = null;

		// determine losses based on random number
		// the plus one assures that some damage is always inflicted
		int defenderLoss = (rand.nextInt(attacker.getStrength()) + 1) * ATTACK_LOSS_FACTOR;

		// defender losses
		System.err.printf("Defender looses %d%n", defenderLoss);
		defender.reduceHealth(defenderLoss);

		// if defender is killed on first strike, attacker has no loss
		// otherwise, defender strikes back and attacker also loses health
		if (defender.isAlive()) {
			int attackerLoss = rand.nextInt(defender.getStrength()) * DEFEND_LOSS_FACTOR;
			System.err.printf("Attacker looses %d%n", attackerLoss);
			attacker.reduceHealth(attackerLoss);
			// if the attacker was killed, make the defender the winner
			if (!attacker.isAlive()) {
				winner = defender;
				loser = attacker;
			}
		} else {
			winner = attacker;
			loser = defender;
		}

		// If we have a winner (still alive) and they collects vials, move them to the
		// winner
		if (winner != null && winner.isAlive() && winner instanceof Collector && loser instanceof Collector)
			((Collector) winner).addVials(((Collector) loser).relinquishVials());
	}

	// called from player class
	public void retreat(Player player, GameEntity defender) {
		// can defender attack?
		if (defender instanceof Warrior) {
			Warrior pursuer = (Warrior) defender;
			// chase attacker
			// the player with the greater speed has an advantage, but doesn't necessarily
			// win
			if (player.getSpeed() * rand.nextDouble() < pursuer.getSpeed() * rand.nextDouble()) {
				pursuer.attack(player);
			}
		}
	}

	
	private void drink(Player player) {
		player.drink();
	}
}
