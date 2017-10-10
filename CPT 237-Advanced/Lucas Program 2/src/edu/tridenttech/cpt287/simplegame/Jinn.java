package edu.tridenttech.cpt287.simplegame;

public class Jinn extends Enemy {
	private final static int JINN_STRENGTH = 7;
	private final static int JINN_SPEED = 8;
	private final static int JINN_POINTS = 11;

	public Jinn(int numVials) {
		super("Jinn", JINN_STRENGTH, JINN_SPEED, numVials, JINN_POINTS);
	}
}
