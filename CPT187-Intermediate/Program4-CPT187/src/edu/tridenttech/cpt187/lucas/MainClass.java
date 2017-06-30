package edu.tridenttech.cpt187.lucas;
/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose: Driver for SubwayTrain class 
 */
public class MainClass {

	public static void main(String[] args) {
		SubwayTrain myTrain = new SubwayTrain(200,1,30);
		System.out.print("*************************************************************\n");
		System.out.print("Starting Systems..........\n");
		System.out.print("(You hear an electric motor whirring under your feet)\n");
		System.out.print("*************************************************************\n");
		System.out.print("Initializing Train Settings.............\n");
		System.out.print("Initializing Maximum Safe Amount Of Passengers:..." + myTrain.getMaxCapacity() + "\n");
		System.out.print("Initializing Passenger Amount:..." + myTrain.getPassengerAmount() +"\n");
		System.out.print("InitialIzing Number of Stations:..." + myTrain.getMaxStation() + "\n");
		System.out.print("InitialIzing Current Station:..." + myTrain.getCurrentStation() + "\n");
		System.out.print("InitialIzing Destination Station:..." + myTrain.getNewStation() + "\n");
		System.out.print("*************************************************************\n");
		System.out.print("Initialization Complete..........\n");
		System.out.print("(The panel lights all turn green signaling: Systems Ready!)\n");
		System.out.print("*************************************************************\n");
		System.out.print("*      Welcome To Solicitous Sal's Substandard Subway!      *\n");
		System.out.print("*               You May Now Commence Boarding!              *\n");
		System.out.print("*************************************************************\n");
		myTrain.onPassenger();
		myTrain.moveTrain(myTrain.getMaxStation());
		myTrain.checkSettings();
		
		System.out.print("      Solicitous Sal Bids You Faerwell And Safe Travels      \n");
		System.out.print("*************************************************************\n");
		System.out.print("Initiating Systems Shutdown...........\n");
		System.out.print("*************************************************************\n");
		System.out.print("       (The Panel Lights Slowly Blink Out One By One)\n");
		System.out.print("       (Leaving You Feeling Sad And Slightly Hungry)\n");
	}

}
