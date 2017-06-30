package edu.tridenttech.cpt187.lucas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @file: ListOfInts.java
 * @author: Ben Lucas
 * @purpose: Contains methods to load an array, displays it's values, 
 * calculates the average of the values and number of values in 
 * the list above the average as well as display this information. 
 */
public class ListOfInts {
	private int valCount = 0;
	private int valTotal = 0;
	private int valAverage = 0;	
	private int aboveAverage= 0;
	private int minInt = 0;
	int[] intList = new int[100];
	
	public void ListOfInts(){}
	
	public void loadArray(String fileName)
	{
		valCount = 0;
		try
		{
			Scanner fileIn = new Scanner(new FileInputStream(fileName));
			for(valCount = 0;fileIn.hasNext();valCount++)
			{
				intList[valCount] = fileIn.nextInt();
				valTotal += intList[valCount];
			}
			fileIn.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	public void displayArray(int[] arrayIn)
	{
		for(int count = 0;count < valCount;count++)
		{
			System.out.printf("%d\n", arrayIn[count]);
		}
	}
	/*Converts valTotal and valCount to doubles for accurate division, then
	 * rounds the result and casts it into an integer type.  Returns integer 
	 * valAverage.
	 */
	public int calcAverage()
	{
		double vt = valTotal;
		double vc = valCount;
		valAverage = (int) (Math.round(vt/vc));
		return valAverage;
	}
	public int numAboveAverage(int[] arrayIn)
	{
		for(int count = 0;count < valCount;count++)
		{
			if(arrayIn[count] > valAverage)
			{
				aboveAverage += 1;
			}
		}
		return aboveAverage;
	}
	public int findMinInt(int[] arrayIn)
	{
		int minInt = arrayIn[0];
		for(int count = 1;count < valCount;count++)
		{
			if(arrayIn[count] < minInt)
			{
				minInt = arrayIn[count];
			}
		}
		return minInt;
	}
}
