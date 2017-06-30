package edu.tridenttech.cpt187.lucas;

import java.util.Scanner;

/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose:
 */
public class MainClass {

	public static void main(String[] args) {
		
		int searchNumber;
		int	seqIndex;
		int binIndex;
		Scanner input = new Scanner(System.in);
		EmployeePayData payList = new EmployeePayData();
				
		payList.loadArrays("employeePay.dat");
		/*for(int count = 0;count < payList.getItemCount(); count++)
		{
		System.out.print(payList.empNumber[count] + " " + payList.payRate[count] + "\n");
		}*/
		System.out.print("****************************************************************\n");
		System.out.print("    Welcome To Excellent Edward's Employee Pay Rate Database    \n");
		System.out.print("****************************************************************\n");
		System.out.print("   Please enter a new employee number to extrapolate pay rate:  \n");
		System.out.print("                       (Enter 0 to quit)                        \n");
		searchNumber = input.nextInt();
		while(searchNumber != 0)
		{
			seqIndex = payList.seqSearch(payList.empNumber, searchNumber);
			if(seqIndex >= 0)
			{
				System.out.print("****************************************************************\n");
				System.out.printf("Sequential found employee %d, and the pay rate is $%.2f/hour\n", payList.empNumber[seqIndex], payList.payRate[seqIndex]);
				System.out.printf("Search Comparisons made: %d\n", payList.getRunCountSeq());
			}
			else 
			{
				System.out.print("****************************************************************\n");
				System.out.print("Sequential search did not find ID number " + searchNumber + "\n");
				System.out.printf("Search Comparisons made: %d\n", payList.getRunCountSeq());
			}
			binIndex = payList.binSearch(payList.empNumber, searchNumber);
			//System.out.print(binIndex);
			if(binIndex >= 0)
			{
				System.out.printf("Binary found employee %d, and the pay rate is $%.2f/hour\n", payList.empNumber[binIndex], payList.payRate[binIndex]);
				System.out.printf("Search Comparisons made: %d\n", payList.getRunCountBin());
				System.out.print("****************************************************************\n");
			}
			else
			{
				System.out.print("Binary search did not find ID number " + searchNumber + "\n");
				System.out.printf("Search Comparisons made: %d\n", payList.getRunCountBin());
				System.out.print("****************************************************************\n");
			}
			System.out.print("   Please enter a new employee number to extrapolate pay rate:  \n");
			System.out.print("                       (Enter 0 to quit)                        \n");
			searchNumber = input.nextInt();
		}
		System.out.print("****************************************************************\n");
		System.out.print("             Excellent Edward Says Good Day To You!             \n");
		System.out.print("****************************************************************\n");
	}

}
