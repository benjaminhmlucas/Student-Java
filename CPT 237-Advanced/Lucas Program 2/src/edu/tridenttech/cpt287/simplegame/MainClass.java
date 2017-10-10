package edu.tridenttech.cpt287.simplegame;

public class MainClass {
	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.loadOpponents("opponents.txt");
		game.play();
		System.out.println(".....................................");
		System.out.println("           Game Over!!!!             ");
		System.out.println(".....................................");
	}

}
