package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class Part1 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {

		List<String> Lines = new ArrayList<String>();
		String line = r.readLine();
		int TotalN = 0;

		for(int i = 0; line != null; i++) {
			Lines.add(line);
			TotalN = i;
			line = r.readLine();
		}


		//System.out.println(TotalN);
		double Total = (TotalN/2);
		int first = (int) Math.floor(Total)+1;

		List<String> FirstHalf = Lines.subList(0, first);
		List<String> SecondHalf = Lines.subList(first, TotalN+1);


		for(String Second : SecondHalf){
			w.println(Second);
		}
		for(String First : FirstHalf){
			w.println(First);
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
