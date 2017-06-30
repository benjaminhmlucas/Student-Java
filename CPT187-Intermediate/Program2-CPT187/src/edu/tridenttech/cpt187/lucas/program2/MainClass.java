package edu.tridenttech.cpt187.lucas.program2;
/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose: Simulate a savings account using the class SavingsAccount 
 */
import java.util.Scanner;
public class MainClass 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		//creates myAccount from SavingsAccount class
		SavingsAccount myAccount = new SavingsAccount("8675309", 4000.0, 0.004);
		String name = "";
				
		
		
		System.out.println("*********************************************************");
		System.out.println("********Welcome to Handsome Henry's Money Hider**********");
		System.out.println("****Begin Giving Us Your Money By Entering Your Name*****");
		System.out.println("*********************************************************");

		name = input.nextLine();
		
		System.out.println("*********************************************************");
		System.out.println("Thank You For Trusting Us With Your Currency " + name + "!");
		System.out.println("*********************************************************");
		System.out.println("Your Account's Current State:");
		System.out.printf("%s%41s%n","Account Number: ", myAccount.getAccountNumber());
		System.out.printf("%s%37.2f%n","Account Balance ($):", myAccount.getBalance());
		System.out.printf("%s%30.1f%s%n","Account Interest Rate is: ", (myAccount.getRate()) * 100, "%");
		System.out.println("*********************************************************");
		
		System.out.println("*********************************************************");
		System.out.println("****Did You Want To Hide Any More Money With Us Today?***");
		System.out.print("                         " + name + "\n");
		System.out.println("*********We Will Keep It Quite Safe, We Promise**********");
		System.out.println("*********************************************************");

		myAccount.addToBalance(input.nextDouble());
		
		System.out.println("*********************************************************");
		System.out.println("We Enjoy Looking At Your Money When No One Else Is Around");
		System.out.print("                         " + name + "\n");
		System.out.println("********Before The Money Is All Squirreled Away**********");
		System.out.println("**************Do You Need Any Spending Cash?*************");
		System.out.println("****It's Common Knowledge That Pimps And Illicit Drug****");
		System.out.println("****Dealers Don't Have Squares For Their Smart Phones****");
		System.out.println("*********************...Typically************************");
		System.out.println("*********************************************************");

		input.nextLine();
		myAccount.withdrawFromBalance(input.nextDouble());
		
		System.out.println("***************************************************************");
		System.out.printf("%s%39s%n","Your Account Number is: ", myAccount.getAccountNumber());
		System.out.printf("%s%20.2f%n","The Account Balance After Witdrawal is ($):", myAccount.getBalance());
		System.out.printf("%s%6.1f%s%n","The Rate At Which Your Hidden Money Will Accumulate is: ", (myAccount.getRate()) * 100, "%");
		System.out.println("***************************************************************");
		System.out.println("Farewell, " + name);
		System.out.println("***************************************************************");
		System.out.println("Here Is The Future Of Your Cash/Monthly Interest Accumulation:");
		System.out.println("***************************************************************");
		calculateInterest(myAccount);
		System.out.println("***************************************************************");
		System.out.println("Try a Bankroll Sushi Roll Today! Your Day Will Be Satisfactory.");
		System.out.println("***************************************************************");
		input.close();
	}//END main METHOD
	public static void calculateInterest(SavingsAccount passedInAccount) 
	{
		double balance = passedInAccount.getBalance();
		double interest = balance * passedInAccount.getRate();
		double balanceWithInterest = interest + balance;
		System.out.printf("%2s%14s%14s%13s%n", "Month", "Starting","Interest","Ending");
		System.out.printf("%2s%16s%13s%16s%n", "#", "Balance","Earned","Balance");
		for (int count = 1; count <= 12; count++)
		{
			System.out.printf("%2d%16.2f%12.2f%17.2f%n", count, balance, interest, balanceWithInterest);
			balance = balanceWithInterest;
			interest = balance * passedInAccount.getRate();
			balanceWithInterest = interest + balance;
		}
	}//End
	
}//END MainClass
