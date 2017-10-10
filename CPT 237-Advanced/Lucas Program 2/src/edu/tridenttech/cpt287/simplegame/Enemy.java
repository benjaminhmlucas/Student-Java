package edu.tridenttech.cpt287.simplegame;

public class Enemy extends Obstacle implements Warrior, Collector, GameEntity {
	final private int speed;
	private int numVials;

	/**
	 * Creates an Enemy with the given parameters. The points are calculated by
	 * adding the strength plus half the speed.
	 * 
	 * @param name
	 *            The name of this enemy
	 * @param strength
	 *            Strength used in determining damage done to opponent
	 * @param speed
	 *            Speed used to determine whether opponent is caught when pursued
	 * @param numVials
	 *            The number of vials currently in this Enemy's possession
	 */
	public Enemy(String name, int strength, int speed, int numVials) {
		super(name, strength);
		this.speed = speed;
		this.numVials = numVials;
	}

	/**
	 * Creates an Enemy with the given parameters.
	 * 
	 * @param name
	 *            The name of this enemy
	 * @param strength
	 *            Strength used in determining damage done to opponent
	 * @param speed
	 *            Speed used to determine whether opponent is caught when pursued
	 * @param numVials
	 *            The number of vials currently in this Enemy's possession
	 * @param points
	 *            The number points that this Enemy is worth
	 */
	public Enemy(String name, int strength, int speed, int numVials, int points) {
		super(name, strength, points);
		this.speed = speed;
		this.numVials = numVials;
	}

	// This implements the attack method for the Enemy. Do not modify.
	public void attack(GameEntity player) {
		Game.getInstance().attack(this, player);
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
}
