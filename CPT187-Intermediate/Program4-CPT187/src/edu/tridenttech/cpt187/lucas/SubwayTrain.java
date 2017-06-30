package edu.tridenttech.cpt187.lucas;
/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose: SubwayTrain class methods that creates subway trains and interacts with them 
 */
import java.util.Random;
public class SubwayTrain
{
	private int passengerAmount;
	private int maxCapacity;
	private int currentStation;
	private int destStation;
	private int maxStation;
	Random myRandom = new Random();
	

	public SubwayTrain(int maxCp,int currentSt, int maxSt)
	{
		maxCapacity = maxCp;
		currentStation = currentSt;
		maxStation = maxSt;
		passengerAmount = 0;
		destStation = currentStation;
	}
	public int getPassengerAmount()
	{
		return passengerAmount;
	}
	public int getMaxCapacity()
	{
		return maxCapacity;
	}
	public int getMaxStation()
	{
		return maxStation;
	}
	public int getCurrentStation()
	{
		return currentStation;
	}
	public int getNewStation()
	{
		return destStation;
	}
	public int getRanNum(int randomNumRange)
	{
		return myRandom.nextInt(randomNumRange);
	}
	public void checkSettings()
	{
		System.out.print("*************************************************************\n");
		System.out.print("Checking Train Settings.............\n");
		System.out.print("Checking Maximum Safe Amount Of Passengers:..." + getMaxCapacity() + "\n");
		System.out.print("Checking Passenger Amount:..." +getPassengerAmount() +"\n");
		System.out.print("Checking Maximum Stations:..." + getMaxStation() + "\n");
		System.out.print("Checking Current Station:..." +getCurrentStation() + "\n");
		System.out.print("Checking Destination Station:..." + getNewStation() + "\n");
		System.out.print("*************************************************************\n");
	}
	public void onPassenger()
	{	
		int onPass =  getRanNum(300);
		int totalPass = passengerAmount + onPass;
		if (totalPass > maxCapacity)
		{
			System.out.print("*************************************************************\n");
			System.out.print("    Warning! Exceeding Maximum Safe Number Of Passengers!    \n");
			System.out.print("       In An Attempt To Save Us All From Suffocation,        \n");
			System.out.printf("%s%-2d%s%n","      Riders May Board The Train Up To: ",getMaxCapacity()," Passengers!      ");
			System.out.printf("                  Boarding: %d Passengers!\n",((getMaxCapacity()) - passengerAmount));
			System.out.print("    The Rest Of You Will Have To Wait For The Next Train!    \n");
			System.out.print("*************************************************************\n");
			passengerAmount = getMaxCapacity();
			System.out.printf("%s%3d%n","Current Passenger(s) Aboard:", getPassengerAmount());
			System.out.print("*************************************************************\n");
			return;
		}
		else 
		{
			passengerAmount = totalPass;
			
			System.out.print("*************************************************************\n");
			System.out.printf("Loading %d Passenger(s)\n", onPass);
			System.out.printf("Current Passenger(s) Aboard: %d\n", getPassengerAmount());
			System.out.print("*************************************************************\n");
		}
	}
	public void offPassenger()
	{
		int offPass = getRanNum(201);
		int totalPass = passengerAmount - offPass;
		if (totalPass < 0)
		{
			System.out.print("*************************************************************\n");
			System.out.print("    Warning! Exceeding Minimum Safe Number Of Passengers!    \n");
			System.out.print("   In An Attempt To Save Us All From Temporal Distortions,   \n");
			System.out.print("        Train Passenger Amount Cannot Fall Below Zero!       \n");
			System.out.printf("                 %d Passengers Disembarking!\n", passengerAmount);
			System.out.print("*************************************************************\n");
			
			
			passengerAmount = 0;
			
			System.out.printf("%s%3d%n","Current Passenger(s) Aboard: ", getPassengerAmount());
			System.out.print("*************************************************************\n");
			return;
		}
		else 
		{
			passengerAmount = totalPass;
			System.out.print("*************************************************************\n");
			System.out.printf("%d Passenger(s) Disembarking\n", offPass);
			System.out.printf("Current Passenger(s) Aboard: %d\n", getPassengerAmount());
			System.out.print("*************************************************************\n");
		}
	}
	public void onPassenger(double percentPassengerIncrease)
	{
		int passengerIncrease = (int)(percentPassengerIncrease * passengerAmount);
		if(passengerAmount + passengerIncrease > maxCapacity)
		{
			System.out.print("*************************************************************\n");
			System.out.printf("Loading: %d Passengers\n", maxCapacity - passengerAmount);
			System.out.print("*************************************************************\n");
			passengerAmount = maxCapacity;
			return;
		}
		System.out.print("*************************************************************\n");
		System.out.printf("Loading: %d Passengers\n",passengerIncrease);
		System.out.print("*************************************************************\n");
		passengerAmount += passengerIncrease; 
	}
	public void moveTrain(int destSt)
	{
		destStation = destSt;
		if (destSt > maxStation)
		{
			System.out.print("*************************************************************\n");
			System.out.print("                    Invalid Station Number!                  \n");
			System.out.printf("           Station Stations Numbers Do Not Exceed %d\n", getMaxStation());              
			System.out.printf("         Unloading The Current %d Passenger(s) Aboard\n", getPassengerAmount());
			System.out.print("*************************************************************\n");
			return;
		} 
		while(currentStation < destSt)
		{
			System.out.print("*************************************************************\n");
			System.out.printf("LEAVING STATION: %d\n", currentStation++);
			System.out.printf("Current Passenger(s) Aboard: %d\n", getPassengerAmount());
			System.out.print("*************************************************************\n");
			System.out.printf("ARRIVING AT STATION: %d\n", currentStation);
			offPassenger();
			onPassenger();
			
			int repairChance = getRanNum(10);
			
			if(repairChance == 9)
			{
				System.out.print("(As The Train Starts To Move Forward There is a Sudden Crash!)\n");
				System.out.print("*************************************************************\n");
				System.out.print("           Danger! Train Is Going Into Standby Mode!         \n");
				System.out.print("            Sensors Indicate Broken Train Component          \n");
				System.out.print("      Please Wait While Repairs Are Made By NanoBot2000!     \n");
				System.out.print("        Allow Approaching Passengers Onboard Until Max       \n");
				System.out.print("                      Capacity is Reached                    \n");
				System.out.print("*************************************************************\n");
				onPassenger(0.15);
				System.out.print("     Repairs Completed! Initializing Settings Diagnostics    \n");
				checkSettings();
				System.out.print("              Commencing Designated Train Path!              \n");
			}
		}
		System.out.print("*************************************************************\n");
		System.out.print("              We Have Reached The End Of The Line            \n");
		System.out.print("                   Returning To Home Station                 \n");
		System.out.printf("               Current Passenger(s) Aboard: %d\n", getPassengerAmount());
		System.out.print("*************************************************************\n");
		currentStation = 1;
		destStation = 1;
		System.out.print("*************************************************************\n");
		System.out.print("                 We Have Reached Home Station!               \n");
		System.out.printf("             Disembarking Remaining %d Passengers            \n", passengerAmount);
		passengerAmount = 0;
		System.out.printf("               Current Passenger(s) Aboard: %d\n", getPassengerAmount());
		System.out.print("*************************************************************\n");
	}
}//END Class
	
	
	
	
	
	