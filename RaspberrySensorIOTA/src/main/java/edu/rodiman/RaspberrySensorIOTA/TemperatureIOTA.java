package edu.rodiman.RaspberrySensorIOTA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TemperatureIOTA {

	public static void main(String[] args) {

		
		// Execute the Python temperature check 
		String command = "./AdafruitDHT.py 2302 4"; // My current sensor type
		Runtime run = Runtime.getRuntime();
		Process pr;
		try {
			pr = run.exec(command);
			pr.waitFor();
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String resultText = "";
			
			//Get the lines from the console
			resultText = buf.readLine(); //There is only one line, so no need of doing while ((resultText=buf.readLine())!=null) {
			
			System.out.println("Temperature in the living room is: " + resultText);
			System.out.println("Sending to the tangle...");
		
			
			ServiceIOTA iota = ServiceIOTA.getInstance();
			
			iota.send(resultText);
			System.out.println("Sent to the tangle? Check your wallet!!!");
			
			
			System.out.println("====== Finished! ======");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
