package edu.lucas.midterm;

import java.util.Scanner;
/**
 * File: TuitionCalculator.java
 * Purpose: Calculates tuition based on residency and credit hours
 * @author Ben Lucas
 *
 */
public class TuitionCalculator 
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		char residency = ' ';
		int creditHours = 0;
		String residencyName = "";
		double creditPrice = 0.0;
		double lotteryTotal = 0.0;
		double tuitionBasic = 0.0;
		double tuitionFinal = 0.0;
		double INCOUNTY = 168.30;
		double OUTCOUNTY = 186.72;
		double OUTSTATE = 318.55;
		double LOTTERY = 100.0;
		
		//welcome screen and input residency
		System.out.print("*****************************************************************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("*******************Welcome To Tuitionizer 1.0********************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("******************Please Enter:Residency Status******************\n");
		System.out.print("****Enter (1) for Charleston, Berkeley or Dorchester Counties****\n");
		System.out.print("********Enter (2) for ANY other county in South Carolina*********\n");
		System.out.print("*******************Enter (3) for Out of State********************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("***********After entering choice please press ENTER**************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("*****************************************************************\n");
		residency = input.next().charAt(0);
		
		//test for correct data entry
		while (residency != '1' && residency != '2' && residency != '3') 
		{
			System.out.print("****************INVALID ENTRY********************\n");	
			System.out.print("1 for Charleston, Berkley, or Dorchester Counties\n");
			System.out.print("2 for other Counties in South Carolina\n");
			System.out.print("3 for out of state (press Enter after your choice):");
			residency = input.next().charAt(0);
		}
		
		//input creditHours
		System.out.print("\nHow many credit hours will you be enjoying this semester?");
		System.out.print("\nYou may not take more than 21 credit hours");
		System.out.print("\nPlease press ENTER after inputing number):"); 
		creditHours = input.nextInt();
		
		//test for correct data entry
		while (creditHours < 0 || creditHours > 21) 
		{
			System.out.print("\n*********************INVALID ENTRY***********************");
			System.out.print("\nHow many credit hours will you be enjoying this semester?");
			System.out.print("\nYou may not take more than 21 credit hours");
			System.out.print("(\nPlease press ENTER after inputing number):"); 
			creditHours = input.nextInt();	
		}
		
		//assign INCOUNTY rate and name
		if (residency == '1')
		{
			tuitionBasic = creditHours * INCOUNTY; 
			creditPrice = INCOUNTY;	
			residencyName = "In State, In Tricounty Area";
		}
		//assign OUTCOUNTY rate and name
		else if (residency == '2')
		{
			tuitionBasic = creditHours * OUTCOUNTY; 
			creditPrice = OUTCOUNTY;
			residencyName = "In State, Out of Tricounty Area";
		}
		//assign OUTSTATE rate and name
		else 
		{
			tuitionBasic = creditHours * OUTSTATE; 
			creditPrice = OUTSTATE;	
			residencyName = "Out of State";
		}
						
		//determine eligibility for lottery assistance and assign lotteryTotal and tuitionFinal 
		if (creditHours >= 6 && (residency == '1' || residency == '2'))
		{
			lotteryTotal = creditHours * LOTTERY; 
			tuitionFinal = tuitionBasic - lotteryTotal;
		}
		else 
			{
			tuitionFinal = tuitionBasic;
			} 
		
	int count = 0;
    while (count < 60) 
    	{
    	System.out.printf("%s", "*");
    	count += 1;
    	}
    //display results
    System.out.printf("\n%10s %49s%n", "Residency:", residencyName);
    System.out.printf("%12s %40d%n", "Credit Hours Taken:", creditHours);
    System.out.printf("%12s %31.2f%n", "Tuition Price per Credit($):", creditPrice);
    System.out.printf("%12s %29.2f%n", "Tuition(Before Assistance)($):", tuitionBasic);
    System.out.printf("%12s %37.2f%n", "Lottery Assistance($):", lotteryTotal );
    System.out.printf("%12s %38.2f%n", "Total Tuition Due($):" , tuitionFinal);
    
    count = 0;
	while (count < 60) 
		{
		System.out.printf("%s", "*");
		count += 1;
		}
	}

}
