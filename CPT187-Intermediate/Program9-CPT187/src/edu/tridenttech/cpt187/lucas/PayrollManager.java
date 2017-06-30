package edu.tridenttech.cpt187.lucas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PayrollManager 
{
	private String fileName;
	PayrollManager(String name)
	{
		fileName = name;
	}
	public void saveOneRecord(int id, double hr, double totPay)
	{
		try 
		{
			PrintWriter toFile = new PrintWriter(new FileWriter(fileName, true));
			toFile.printf("%s %.1f %.2f\n", id, hr, totPay);
			toFile.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
