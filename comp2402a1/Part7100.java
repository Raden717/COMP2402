package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.*;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class Part7 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {

		//List Interface to sort
		TreeMap<String,Integer> Upper = new TreeMap<String, Integer>();
		TreeMap<String,Integer> Lower = new TreeMap<String, Integer>();
		String highestString = "upper";
		String lowestString = "lower";
		int upper1 = 0;
		int lower1 = 0;
		boolean check = true;
		int counter = 0;
		boolean updateUpper = true;
		boolean updateLower = true;

		for(String line = r.readLine(); line != null; line = r.readLine()) {
			check = true;
			if(counter < 1000){
				if(Upper.containsKey(line)){		//Checks if Map has key value
					Upper.put(line, (Upper.get(line))+1);	//If yes, adds to the counter of how many times line showed up
				} else {
					Upper.put(line, 1);			//If not, puts key value in
				}
				if(Lower.containsKey(line)){
					Lower.put(line, (Lower.get(line))+1);
				} else {
					Lower.put(line, 1);
				}
				counter++;
			}else{


				if(updateUpper){ // Looks to see if I need to find the new highest String if list updated
				Set<String> UpKeys = Upper.keySet();
				int highCheck = 0;
				for(String UpKey : UpKeys){
					highCheck = highCheck + Upper.get(UpKey);
					if(highCheck >= 1000){
						highestString = UpKey;
						break;
					}
				}
			}
				if(updateLower){ // Looks to see if I need to find the new lowest STring if list updated
					ArrayList<String> LowKeys = new ArrayList<>(Lower.keySet());
					Collections.reverse(LowKeys);
					int lowCheck = 0;
					for(String LowKey : LowKeys){
						lowCheck = lowCheck + Lower.get(LowKey);
						if(lowCheck >= 1000){
							lowestString = LowKey;
							break;
						}
					}
				}

				//Checks if the line is greater than 1000
				if(line.compareTo(highestString) <= 0){
					updateUpper = true;
					check = false;
					if(Upper.containsKey(line)){		//Checks if Map has key value
						Upper.put(line, (Upper.get(line))+1);	//If yes, adds to the counter of how many times line showed up
					} else {
						Upper.put(line, 1);			//If not, puts key value in
					}
				} else {
					updateUpper = false;
				}

				//Checks if the line is less than 1000
				if(line.compareTo(lowestString) >= 0){
					updateLower = true;
					check = false;
					if(Lower.containsKey(line)){
						Lower.put(line, (Lower.get(line))+1);
					} else {
						Lower.put(line, 1);
					}
				} else {
					updateLower = false;
				}



			if(check){	//If passes both checks, prints the line
				w.println(line);
			}

			}

			}



		}


		// Your code goes here - see Part0 for an example


	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 10e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
