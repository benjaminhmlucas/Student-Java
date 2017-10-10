package edu.tridenttech.cpt287.simplegame;

public interface GameEntity {
	public String getName();

	public int getStrength();

	public int getHealth();

	public int getPoints();

	public void reduceHealth(int amount);

	public boolean isAlive();

}
