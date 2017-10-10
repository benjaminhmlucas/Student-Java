package edu.tridenttech.cpt287.simplegame;

public class Goblin extends Enemy {

	private final static int GOBLIN_STRENGTH = 7;
	private final static int GOBLIN_SPEED = 8;

	public Goblin(int numVials) {
		super("Goblin", GOBLIN_STRENGTH, GOBLIN_SPEED, numVials);
	}
}
