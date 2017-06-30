	 
package edu.lucas;

import java.util.Scanner;

/**
 * File: BookstoreCalculator.java
 * Purpose: Calculates sale cost for bookstore purchase
 * @author Ben Lucas
 *
 */
public class BookstoreCalculator 
{
		
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		String userName = "";
		int yearsAttended = 0;
		String itemName = "";
		double origPrice = 0.0;
		int howMany = 0;
		double discountPrice = 0.0;
		double totalCost = 0.0;
	    
		System.out.println("Welcome to the Bookstore.  It's a good day to shop, because everything is on sale today!");
		System.out.println("Let me know what you are looking for and I'll help you find it."); 
		
		System.out.println("Please enter your first name");
		userName = input.nextLine();
		
		System.out.println("Nice to meet you " + userName);
		System.out.println("How many years have you been attending this school?");
		yearsAttended = input.nextInt();
				
		System.out.println("What item are you wanting to purchase today?");
		itemName = input.nextLine();
		System.out.println("What is its listed price?");
		origPrice = input.nextDouble();
		
		discountPrice = origPrice - origPrice * 0.37;
		
		System.out.println("The price for that item would be this for today only: $" + discountPrice + ".");
		System.out.println("So, how many of them would you want to buy?");
		howMany = input.nextInt();

		
		totalCost = howMany * discountPrice;
		
		System.out.println("Ok, " + userName +", here's what is all comes to:");
		System.out.println("You are buying " + howMany + " " + itemName + "s.");
		System.out.println("The original price was $" + origPrice + ".");
		System.out.println("Since all items are 37% off today, the new price will be " + discountPrice + ".");
		System.out.println("Your total comes to $" + totalCost + ".");
		System.out.println("You've done well over the last " + yearsAttended + " years. Good luck in your future studies!");
		
		 input.close();
	}

}
