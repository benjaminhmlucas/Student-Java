package edu.tridenttech.cpt287.simplegame;

public interface Collector {
	public int getNumVials();

	public void addVials(int amount);

	public int relinquishVials();
}
