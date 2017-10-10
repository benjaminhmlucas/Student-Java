package edu.tridenttech.cpt287.simplegame;

public interface Warrior extends GameEntity {
	public int getSpeed();

	public boolean canAttack();

	public void attack(GameEntity player);

	public int getStrength();

	public void reduceHealth(int healthLoss);
}
