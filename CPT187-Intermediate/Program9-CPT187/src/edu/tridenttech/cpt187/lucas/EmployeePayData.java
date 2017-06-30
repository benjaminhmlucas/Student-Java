package edu.tridenttech.cpt187.lucas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @file: PayrollManager.java
 * @author: Ben Lucas
 * @purpose: assigns a file output name to an instance 
 * with the constructor then uses the saveOneRecord method 
 * to write one record to the output file.
 */
public class EmployeePayData {
	
	private int itemCount;
	private int searchIndex = -1;
	private int runCountSeq = 0;
	private int runCountBin = 0;
	int[] empNumber = new int[200];
	double[] payRate = new double[200];
	
	public EmployeePayData(){}
	
	public void loadArrays(String fileName)
	{
		
		try
		{
			Scanner fileIn = new Scanner(new FileInputStream(fileName));
			for(itemCount = 0;fileIn.hasNext();itemCount++)
			{
				empNumber[itemCount] = fileIn.nextInt();
				payRate[itemCount] = fileIn.nextDouble();
				
			}
			fileIn.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	public int getItemCount()
	{
		return itemCount;
	}
	public int getSearchIndex()
	{
		return searchIndex;
	}
	public int getRunCountSeq()
	{
		return runCountSeq;
	}
	public int getRunCountBin()
	{
		return runCountBin;
	}
	public void bubbleSort(int[] arrayIn1,double[] arrayIn2)
	{
		int high = itemCount - 1;
		while(high > 0)
		{
			int index = 0;
			int swaps = 0;
			while(index < high)
			{
				if (arrayIn1[index] > arrayIn1[index + 1])
				{
					swapper(empNumber,index, index + 1);
					swapper(payRate,index,index + 1);
					swaps++;
				}
				index++;
			}
			if(swaps == 0)
			{
				high = 0;
			}
			else
			{
				high = high - 1;
			}
		}
	}
	public void swapper(int[] arrayIn, int a,int b)
	{
		int temp = arrayIn[a];
		arrayIn[a] = arrayIn[b];
		arrayIn[b] = temp;
	}
	public void swapper(double[] arrayIn,int a,int b)
	{
		double temp = arrayIn[a];
		arrayIn[a] = arrayIn[b];
		arrayIn[b] = temp;
	}
	public int seqSearch(int[] arrayIn, int searchKey)
	{
		searchIndex = -1;
		runCountSeq = 0;
		for(int count = 0; count < itemCount; count++)
		{
			if(arrayIn[count] == searchKey)
			{
				searchIndex = count;				
				return searchIndex;
			}
			runCountSeq++;
		}
		return searchIndex;
	}
	public int binSearch(int arrayIn[], int searchKey)
	{
		int high = itemCount - 1;
		int low = 0;
		searchIndex = -1;
		runCountBin = 0;
		while(high >= low)
		{
			int mid = (high + low)/2;
			if(searchKey == arrayIn[mid])
			{
				searchIndex = mid;
				return searchIndex;
			}
			else if(searchKey < arrayIn[mid])
			{
				high = mid - 1;
			}
			else
			{
			low = mid + 1;
			}
			runCountBin++;
		}
		return searchIndex;
	}
}

