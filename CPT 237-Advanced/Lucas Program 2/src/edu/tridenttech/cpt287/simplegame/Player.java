package edu.tridenttech.cpt287.simplegame;

public class Player implements GameEntity, Warrior, Collector, Comparable<Player> {
	final private String name;
	final private int strength;
	private int health = 50;
	final private int speed;
	private int numVials = 0;
	private int points = 0;
	final private static int HEALTH_MAX = 150;
	final private static int ADD_VIAL = 100;

	public Player(String name, int strength, int speed) {
		this.name = name;
		this.strength = strength;
		this.speed = speed;
	}

	// DO NOT MODIFY
	public void run(GameEntity opponent) {
		// calls the retreat method in Game
		Game.getInstance().retreat(this, opponent);
	}

	// DO NOT MODIFY
	public void attack(GameEntity opponent) {
		// The player calls the 'attack' method in Game.
		Game.getInstance().attack(this, opponent);
	}

	public void drink() {
		if(this.numVials > 0) {
		
			this.numVials--;
			if (this.health + ADD_VIAL > HEALTH_MAX) {
				this.health = HEALTH_MAX;
			} else {
				this.health += ADD_VIAL;
			}
		}
		else {
			System.out.println("You Have No More Vials!");
		
		}
	}

	public void addPoints(int addAmount) {
		this.points += addAmount;

	}

	@Override
	public int getNumVials() {
		return this.numVials;
	}

	@Override
	public void addVials(int amount) {
		this.numVials = this.numVials + amount;

	}

	@Override
	public int relinquishVials() {
		int giveVials = this.numVials;
		this.numVials = 0;
		return giveVials;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public boolean canAttack() {
		return true;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void reduceHealth(int amount) {
		this.health = this.health - amount;
	}

	@Override
	public boolean isAlive() {
		if (this.health > 0) {
			return true;
		}
		return false;
	}

	// used for rankings ArrayList sort
	@Override
	public int compareTo(Player p) {
		int comparePoints = ((Player) p).getPoints();
		/* For descending order */
		return comparePoints - this.points;
	}

}