package edu.tridenttech.cpt187.lucas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @file: EmployeePayData.java
 * @author: Ben Lucas
 * @purpose:
 */
public class EmployeePayData {
	
	private int itemCount;
	private int searchIndex = -1;
	private int runCountSeq = 0;
	private int runCountBin = 0;
	int[] empNumber = new int[200];
	double[] payRate = new double[200];
	
	public void EmployeePayData(){}
	
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

