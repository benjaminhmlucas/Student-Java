package edu.tridenttech.cpt187.lucas.program3;
/**
 * @file: Elevator.java
 * @author: Ben Lucas
 * @purpose: contains class to interact with Elevator objects 
 */
public class Elevator 
{
	
	private int passengerAmount;
	private int maxCapacity;
	private int minFloor;
	private int maxFloor;
	private int currentFloor;
	private int newFloor;
	
	public Elevator(int maxCp,int minFl,int maxFl)
	{
		passengerAmount = 0;
		maxCapacity = maxCp;
		minFloor = minFl;
		maxFloor = maxFl;
		currentFloor = minFl;
		newFloor = currentFloor;
	}
	
	public int getPassenger()
	{
		return passengerAmount;
	}
	
	public int getMaxCapacity()
	{
		return maxCapacity;
	}  		

	public int getCurrentFloor()
	{
		return currentFloor;
	}  		

	public int getNewFloor()
	{
		return newFloor;
	}  		
	
	public int getMaxFloor()
	{
		return maxFloor;
	}	   

	public int getMinFloor()
	{
		return minFloor;
	}  	
	public void checkAllSettings()
	{
		newFloor = currentFloor;
		System.out.print("***************************************************************\n");
		System.out.print("Checking Elevator Settings..........\n");
		System.out.print("Beep Czchck Boop.........\n");
		System.out.print("***************************************************************\n");
		System.out.print("Checking Elevator Settings.............\n");
		System.out.print("Checking Maximum Safe Amount Of Passengers:..." + getMaxCapacity() + "\n");
		System.out.print("Checking Passenger Amount:..." + getPassenger() +"\n");
		System.out.print("Checking Bottom Floor:..." + getMinFloor() + "\n");
		System.out.print("Checking Top Floor:..." + getMaxFloor() + "\n");
		System.out.print("Checking Current Floor:..." + getCurrentFloor() + "\n");
		System.out.print("Checking New Floor:..." + getNewFloor() + "\n");
		System.out.print("***************************************************************\n");
		System.out.print("Checking Sequence Complete..........\n");
		System.out.print("Blop Boop Blorp.........\n");
	}
	public void onPassenger(int passOn)
	{
		if((passOn + passengerAmount) > 18)
		{
			System.out.print("***************************************************************\n");
			System.out.print("*     Warning! Exceeding Maximum Safe Number Of Passengers!   *\n");
			System.out.print("*       In An Attempt To Save Us All From Becoming Jelly,     *\n");
			System.out.printf("%s%-2d%s%n","*            ",getMaxCapacity()," Passengers May Board The Elevator             *");
			System.out.print("*  Why Not Stand Here And Stare At Your Phone While You Wait? *\n");
			System.out.print("***************************************************************\n");
			passengerAmount = getMaxCapacity();
			System.out.printf("%s%3d%s%n","Current Passenger Amount:", getPassenger(),"*");
			System.out.print("***************************************************************\n");
			return;
		}
		else
		{
			passengerAmount += passOn;
			System.out.print("***************************************************************\n");
			System.out.printf("%-2d%s%13d%s%n",passOn," Passengers Boarding. Current Passenger Amount:", getPassenger(),"*");
			System.out.print("***************************************************************\n");
	
		}	       
	}					
	public void offPassenger(int passOff)
	{
		if((passengerAmount - passOff) < 0)
		{
			System.out.print("***************************************************************\n");
			System.out.print("*  Warning! Exceeding Minimum Logical Number Of Passengers!   *\n");
			System.out.print("*     In An Attempt To Save Us All From Unrealistic Math      *\n");
			System.out.print("*         & Dimensional Worm Holes, I Will Allow The          *\n");
			System.out.print("*    Remaining Passengers To Leave, But No More Than That!    *\n");
			System.out.print("***************************************************************\n");
			passengerAmount = 0;
			System.out.printf("%s%37d%s%n","Current Passenger Amount:", getPassenger(),"*");
			System.out.print("***************************************************************\n");
			return;
		}
		passengerAmount -= passOff;
		System.out.print("***************************************************************\n");
		System.out.printf("%-2d%s%7d%s%n",passOff," Passengers Disembarking! Remaining Passenger Amount:", getPassenger(),"*");
		System.out.print("***************************************************************\n");
		
	}	  

	public void moveElevator(int newFl)
	{
		if(newFl < minFloor || newFl > maxFloor|| newFl == currentFloor)
		{
			System.out.print("***************************************************************\n");
			System.out.printf("%s%3d%1s%3d%s%n","*         Warning! Your Floor Number Is Not ",getMinFloor(), " - ",getMaxFloor(), "!        *");
			System.out.print("*        To Get There You Will Have To Take The Stairs        *\n");
			System.out.print("*       But You Will Then Have To Face The Stair People       *\n");
			System.out.print("*     Take This Map And The Knife Of Accounts Payable +2!     *\n");
			System.out.print("*                          Good Luck!                         *\n");
			System.out.print("***************************************************************\n");
			System.out.printf("%s%42d%s%n","Remaining on Floor: ", getCurrentFloor(), "*");
			System.out.print("***************************************************************\n");
			return;
		}
		System.out.print("***************************************************************\n");
		System.out.printf("%s%19d%s%n","Valid Floor Entered! Departing From Floor: ", getCurrentFloor(),"*");
		System.out.print("***************************************************************\n");
		while(currentFloor < newFl)
		{
			currentFloor++;
			if(currentFloor == newFl)
			{
				currentFloor = newFl;
				System.out.print("***************************************************************\n");
				System.out.printf("%s%43d%s%n","Arriving At Floor: ", getCurrentFloor(),"*");
				System.out.print("***************************************************************\n");
				return;
			}
			System.out.println("Passing Floor: " + currentFloor);
		}
		while(currentFloor > newFl)
		{
			currentFloor--;
			if(currentFloor == newFl)
			{
				currentFloor = newFl;
				System.out.print("***************************************************************\n");
				System.out.printf("%s%43d%s%n","Arriving At Floor: ", getCurrentFloor(),"*");
				System.out.print("***************************************************************\n");
				return;
			}
			System.out.println("Passing Floor: " + currentFloor);
		}
		
	}
	
}
