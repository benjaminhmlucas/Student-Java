package edu.tridenttech.cpt237.lucas;
/**
 * @file: Main.java
 * @author: Ben Lucas
 * @purpose: Uses input to create a Stock object and to create and modify a 
 * Stock Recommender Object. Program will continue to create new stock objects until 
 * user quits program.  Upon user exit program outputs stock recommendations and displays 
 * an exit screen. 
 */
import java.util.Scanner;

public class Main {
	
	static Scanner myScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int recommended;
		String quit = "c";
		//Welcome Screen
		System.out.println("*******************************************************");
		System.out.println("**********************Welcome To***********************");
		System.out.println("***********Slippery Sal's Stock Recommender,***********");
		System.out.println("*************Sal Is Absolutely Trustworthy!************");
		System.out.println("*****************Press Enter To Begin!*****************");
		System.out.println("*******************************************************");
		//Holds welcome screen 
		myScanner.nextLine();
		
		StockRecommender currentSession = new StockRecommender();
		
		//Main Program Loop
		while(quit.equals("c"))
		{
			
			Stock myStock = getStock();
			System.out.println("*******************************************************");
			System.out.printf("Stock Symbol: %41s%n",myStock.getSymbol().toUpperCase());
			System.out.printf("Purchase Price: $%38.2f%n",myStock.getPurchasePrice());
			System.out.println("*******************************************************");
			System.out.printf("Please Enter Newest Price: %n");
			myStock.currentPrice = myScanner.nextDouble();
			myScanner.nextLine();
			
			//Uses getRecommendation() to assign value from StockRecommender to determine recommendation message
			recommended = currentSession.getRecommendation(myStock);
			
			System.out.println("*******************************************************");
			System.out.printf("Stock Symbol: %41s%n",myStock.getSymbol().toUpperCase());
			System.out.printf("Price Difference($): %34.2f%n", (myStock.getPurchasePrice() - myStock.getCurrentPrice()));
			System.out.println("*******************************************************");
			
			//Displays recommendation
			if(recommended == StockRecommender.SELL)
			{
				System.out.println("Recommended:                               Sell It All!");
			}
			else if(recommended == StockRecommender.BUY)
			{
				System.out.println("Recommended:                    Now Is The Time To Buy!");
			}
			else
			{
				System.out.println("Recommended: Hold It Bucko! This One Needs More Time...");
			}
			
			//Allows user to quit or continue main program loop
			System.out.println("*******************************************************");
			System.out.println("Would You Like To Check Another Stock?");
			System.out.println("Press (c) Then (Enter) To Continue Recommendations,");
			System.out.println("Press (Any Other Key) Then (Enter) To Quit:");
			quit = myScanner.nextLine();			
		}
		//Displays recommendation totals stored in StockRecommender	
		System.out.println("*******************************************************");
		System.out.printf("Buys Recommended: %37s%n", currentSession.numBuys);
		System.out.printf("Holds Recommended: %36s%n", currentSession.numHolds);
		System.out.printf("Sells Recommended: %36s%n", currentSession.numSells);
		
		//Exit screen
		System.out.println("*******************************************************");		
		System.out.println("*********Slippery Sal Would Like To Remind You:********");
		System.out.println("******All Stock Sales Are Subject To 95% Sal's Tax*****");
		System.out.println("*******************Have A Great Day!*******************");
		System.out.println("*******************************************************");		
	}
	
	//Takes user input and assigns it to a new stock object
	public static Stock getStock()
	{		
		String symbol;
		double purchasePrice;
				
		System.out.println("Please Enter Stock Symbol:");
		symbol = myScanner.nextLine();
		
		System.out.println("Please Enter Current Price:");
		purchasePrice = myScanner.nextDouble();
		
		Stock myStock = new Stock(symbol, purchasePrice);
		
		return myStock;
	}

	
}
