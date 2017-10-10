package edu.tridenttech.cpt287.simplegame;

public class Obstacle implements GameEntity {
	final private String name;
	final private int strength;
	final protected int points;
	private int health = 50;

	protected Obstacle(String name, int strength) {
		this(name, strength, 2 * strength);
	}

	protected Obstacle(String name, int strength, int points) {
		this.name = name;
		this.strength = strength;
		this.points = points;
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
}