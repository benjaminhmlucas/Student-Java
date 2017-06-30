package edu.tridenttech.cpt187.lucas.program3;
/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose: Tests Elevator class and object 
 */
public class MainClass
{

	public static void main(String[] args) 
	{
		Elevator myElevator = new Elevator(18,1,99);
		System.out.print("***************************************************************\n");
		System.out.print("Initiating Elevator Settings..........\n");
		System.out.print("Bleep Bleep Blorp.........\n");
		System.out.print("***************************************************************\n");
		System.out.print("Initializing Elevator Settings.............\n");
		System.out.print("Initializing Maximum Safe Amount Of Passengers:..." + myElevator.getMaxCapacity() + "\n");
		System.out.print("Initializing Passenger Amount:..." + myElevator.getPassenger() +"\n");
		System.out.print("InitialIzing Bottom Floor:..." + myElevator.getMinFloor() + "\n");
		System.out.print("InitialIzing Top Floor:..." + myElevator.getMaxFloor() + "\n");
		System.out.print("InitialIzing Current Floor:..." + myElevator.getCurrentFloor() + "\n");
		System.out.print("InitialIzing New Floor:..." + myElevator.getNewFloor() + "\n");
		System.out.print("***************************************************************\n");
		System.out.print("Initialization Complete..........\n");
		System.out.print("Beep Boop Beep.........\n");
		System.out.print("***************************************************************\n");
		System.out.print("*       Welcome To Elliot's Elaborate Elevation Station!      *\n");
		System.out.print("*                You May Now Commence Boarding!               *\n");
		System.out.print("***************************************************************\n");
		
		myElevator.onPassenger(20);
		myElevator.moveElevator(101);
		myElevator.moveElevator(42);
		myElevator.offPassenger(10);
		myElevator.checkAllSettings();
		myElevator.onPassenger(5);
		myElevator.moveElevator(0);
		myElevator.moveElevator(27);
		myElevator.offPassenger(8);
		myElevator.onPassenger(5);
		myElevator.moveElevator(27);
		myElevator.moveElevator(68);
		myElevator.offPassenger(22);
		myElevator.checkAllSettings();
		System.out.print("***************************************************************\n");
		System.out.print("*        Elliot's Elaborate Elevation Station Is Sleepy       *\n");
		System.out.print("*                    Commencining Shutdown                    *\n");
		System.out.print("*                Pleasant Days And Long Nights                *\n");
		System.out.print("*                           Goodbye                           *\n");
		System.out.print("***************************************************************\n");
		
	}

}
