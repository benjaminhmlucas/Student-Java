package edu.tridenttech.cpt187.lucas;

import java.util.Scanner;

/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose: Calls EmployeePayData methods to load, sort, 
 * and search an array. The program then asks for hours 
 * worked and calculates and displays employee information.
 * Then main adds the employeeID number of hours, and total 
 * pay for the period to an output file called 
 * payrollProcessed.dat. After searching is complete user 
 * can quit which will make the program display the number of 
 * searches, number of found ID's and number of not foundID's.
 */
public class MainClass {

	public static void main(String[] args) {
		
		int searchNumber;
		int	seqIndex;
		int binIndex;
		double hours;
		double pay;
		int empSearched = 0;
		int empFound = 0;
		int empNotFound = 0;
		Scanner input = new Scanner(System.in);
		EmployeePayData payList = new EmployeePayData();
		PayrollManager payMan = new PayrollManager("payrollProcessed.dat");		
		
		payList.loadArrays("masterEmpPay.dat");
		payList.bubbleSort(payList.empNumber,payList.payRate);
		System.out.print("****************************************************************\n");
		System.out.print("    Welcome To Excellent Edward's Employee Pay Rate Database    \n");
		System.out.print("****************************************************************\n");
		System.out.print("   Please enter a new employee number to extrapolate pay rate:  \n");
		System.out.print("                       (Enter 0 to quit)                        \n");
		searchNumber = input.nextInt();
		while(searchNumber != 0)
		{
			empSearched++;
			binIndex = payList.binSearch(payList.empNumber, searchNumber);
			//System.out.print(binIndex);
			if(binIndex >= 0)
			{
				System.out.printf("Binary found employee %d, and the pay rate is $%.2f/hour\n", payList.empNumber[binIndex], payList.payRate[binIndex]);
				System.out.printf("Search Comparisons made: %d\n", payList.getRunCountBin());
				System.out.print("****************************************************************\n");
				empFound++;
				System.out.print("Please Enter number of hours for employee this week:\n");
				System.out.print("****************************************************************\n");
				hours = input.nextDouble();
				pay = hours * payList.payRate[binIndex];
				System.out.print("****************************************************************\n");
				System.out.printf("%s%6s%8s%5s\n","EMP ID","RATE","HOURS","PAY");
				System.out.printf("%d%8.2f%7.2f%8.2f\n",payList.empNumber[binIndex],payList.payRate[binIndex],hours,pay);
				System.out.print("****************************************************************\n");
				payMan.saveOneRecord(searchNumber, hours, pay);
			}
			else
			{
				System.out.print("Binary search did not find ID number " + searchNumber + "\n");
				System.out.printf("Search Comparisons made: %d\n", payList.getRunCountBin());
				System.out.print("****************************************************************\n");
				empNotFound++;
			}
			System.out.print("   Please enter a new employee number to extrapolate pay rate:  \n");
			System.out.print("                       (Enter 0 to quit)                        \n");
			searchNumber = input.nextInt();
		}
		System.out.print("****************************************************************\n");
		System.out.printf("Number Of Employee Numbers Searched: %d\n", empSearched);
		System.out.printf("Number Of Employee Numbers Found: %d\n", empFound);
		System.out.printf("Number Of Employee Numbers Not Found: %d\n", empNotFound);
		System.out.print("****************************************************************\n");
		System.out.print("             Excellent Edward Says Good Day To You!             \n");
		System.out.print("****************************************************************\n");
		input.close();
	}

}
