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
import java.util.TreeMap;s2
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
		ArrayList<String> Upper = new ArrayList<String>();
		ArrayList<String> Lower = new ArrayList<String>();
		String highestString = "upper";
		String lowestString = "lower";
		boolean check = true;
		int counter = 0;
		int counter2 = 0;

		for(String line = r.readLine(); line != null; line = r.readLine()) {
			check = true;
			if(counter < 1000){ //Adds first 1000 lines
				Upper.add(line);
				Lower.add(line);
				counter++;
			}else{
			if(counter == 1000){	//Sorts after adding the 1000 lines
				Collections.sort(Upper);
				Collections.sort(Lower);
				counter++;
			}
			if(line.compareTo(Upper.get(Upper.size()-1)) <= 0){ // Checks if line is greater than 1000
				check = false;
				int index1 = Collections.binarySearch(Upper, line);
				if(index1 >= 0){
					Upper.add(index1, line);				//Acts like a queue since only need 1000 in the list
					Upper.remove(Upper.get(Upper.size()-1));
				} else {
					index1 = (index1*(-1))-1;
					Upper.add(index1, line);		//Acts like a queue since only need 1000 in the list
					Upper.remove(Upper.get(Upper.size()-1));
				}
			}
			if(line.compareTo(Lower.get(0)) >= 0){	//Checks if line is less than 1000
				check = false;
				int index2 = Collections.binarySearch(Lower, line);
				if(index2 >= 0){
					Lower.add(index2, line);		//Acts like a queue since only need 1000 in the list
					Lower.remove(Lower.get(0));
				} else {
					index2 = (index2*(-1))-1;
					Lower.add(index2, line);		//Acts like a queue since only need 1000 in the list
					Lower.remove(Lower.get(0));
				}
			}



			if(check){	//If passes both checks, prints the line
				w.println(line);
				counter2++;
			}




			}

			}
			w.println(counter2);



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
