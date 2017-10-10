package edu.tridenttech.cpt287.simplegame;

public class Troll extends Enemy {

	private final static int TROLL_STRENGTH = 7;
	private final static int TROLL_SPEED = 8;
	private final static int TROLL_POINTS = 11;

	public Troll(int numVials) {
		super("Troll", TROLL_STRENGTH, TROLL_SPEED, numVials, TROLL_POINTS);

	}
}
