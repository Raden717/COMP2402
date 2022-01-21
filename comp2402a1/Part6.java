package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.HashMap;

public class Part6 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		//List for obtaining objects
		TreeSet<String> Lines = new TreeSet<String>() ;
		TreeSet<String> tailSet;
		outerloop:
		for(String line = r.readLine(); line != null; line = r.readLine()) {

			StringBuilder sb = new StringBuilder(line);
			String reverse = sb.reverse().toString();
			tailSet = (TreeSet<String>)Lines.tailSet(reverse);
			if(tailSet.isEmpty() != true){
			if(tailSet.ceiling(reverse).startsWith(reverse)){ //Checks the highest word in the tailset to see if it has the same prefix (which means same suffix)
				continue outerloop;	//prevents it from continuing past and printing the line
			}
			}
				w.println(line);
				Lines.add(reverse);
		}



		// Your code goes here - see Part0 for an example
	}

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
