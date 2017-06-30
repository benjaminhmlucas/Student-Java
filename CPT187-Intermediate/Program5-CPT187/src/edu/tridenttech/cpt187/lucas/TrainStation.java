package edu.tridenttech.cpt187.lucas;
/**
 * @file: TrainStationClass.java
 * @author: Ben Lucas
 * @purpose: Class that sets number of passengers waiting 
 * at 2 stations and loads them on and off 
 */
public class TrainStation {
	private int passengersWaiting;
	private int passengersOnTrain;	
	public TrainStation(int passWaiting)
	{
		passengersWaiting = passWaiting;
		passengersOnTrain = 0;
	} 
	public int getPassengersWaiting()
	{
		return passengersWaiting;
	}
	public int getPassengersOnTrain()
	{
		return passengersOnTrain;
	}
	public void reducePassengersWaiting(int reduceAmount)
	{
		if((passengersWaiting - reduceAmount) > 0)
		{	
			passengersWaiting -= reduceAmount;			
			passengersOnTrain = reduceAmount;
			System.out.print("*************************************************************\n");
			System.out.print("              We Have Reached Maximum Capacity!              \n");
			System.out.printf("              Current Passenger(s) Aboard: %d\n", reduceAmount);
			System.out.printf("         Current Passenger(s) Waiting at Station: %d\n", passengersWaiting);
			System.out.print("*************************************************************\n");
		}
		else if(passengersWaiting > 0)
		{
			reduceAmount = passengersWaiting;
			passengersWaiting -= reduceAmount;
			passengersOnTrain = reduceAmount;
			System.out.print("*************************************************************\n");
			System.out.print("                    All Passengers Loaded!                   \n");
			System.out.printf("              Current Passenger(s) Aboard: %d\n", reduceAmount);
			System.out.printf("         Current Passenger(s) Waiting at Station: %d\n", passengersWaiting);
			System.out.print("*************************************************************\n");
		}
		else if(passengersWaiting == 0)
		{
			reduceAmount = 0;
			passengersOnTrain = reduceAmount;
			System.out.print("*************************************************************\n");
			System.out.print("  No More Passengers Passengers To Load At Current Station!  \n");
			System.out.printf("              Current Passenger(s) Aboard: %d\n", reduceAmount);
			System.out.printf("         Current Passenger(s) Waiting at Station: %d\n", passengersWaiting);
			System.out.print("*************************************************************\n");
		}
	}
}
