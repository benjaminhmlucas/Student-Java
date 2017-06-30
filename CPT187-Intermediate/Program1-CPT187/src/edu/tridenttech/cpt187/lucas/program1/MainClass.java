package edu.tridenttech.cpt187.lucas.program1;
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
		
		SavingsAccount myAccount = new SavingsAccount();
		double amount;
		String name = "";
				
		amount = myAccount.getBalance();
		
		System.out.println("*********************************************************");
		System.out.println("********Welcome to Handsome Henry's Money Hider**********");
		System.out.println("****Begin Giving Us Your Money By Entering Your Name*****");
		System.out.println("*********************************************************");

		name = input.nextLine();
		
		System.out.println("*********************************************************");
		System.out.println("Thank You For Choosing Our Service " + name + "!");
		System.out.println("*********************************************************");
		System.out.println("How Much Of Your Hard Earned Cash Will You Be Hiding?****");
		System.out.println("*********************************************************");
		
		amount = input.nextDouble();
		myAccount.addToBalance(amount);
		
		System.out.println("*********************************************************");
		System.out.println("**************Your Money Is Very Delicious***************");
		System.out.print("                       " + name + "                      \n");
		System.out.println("********Before The Money Is All Squirreled Away**********");
		System.out.println("**************Do You Need Any Spending Cash?*************");
		System.out.println("****It's Common Knowledge That Pimps And Illicit Drug****");
		System.out.println("****Dealers Don't Have Squares For Their Smart Phones****");
		System.out.println("*********************...Typically************************");
		System.out.println("*********************************************************");

		input.nextLine();
		amount = input.nextDouble();
		
		myAccount.withdrawFromBalance(amount);
		
		System.out.println("************************************************************");
		System.out.printf("%s%36s%n","Your Account Number is: ", myAccount.getAccountNumber());
		System.out.printf("%s%17.2f%n","The Account Balance After Witdrawal is ($):", myAccount.getBalance());
		System.out.printf("%s%3.1f%s%n","The Rate At Which Your Hidden Money Will Accumulate is: ", (myAccount.getRate()) * 100, "%");
		System.out.println("************************************************************");
		System.out.println("Farewell, " + name + ", Your Day Will Be Good.");
		System.out.println("************************************************************");

		input.close();
	}

}//END MainClass
