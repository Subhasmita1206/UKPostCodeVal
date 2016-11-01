/**
 * @author Subhasmita Sethy
 * @Project Validation of UK Post Codes
 *
 * This class is written to improve the performance of the bulk validation process 
 * Takes the input CSV file (import_data.csv), validates the post code for each row and produces the o/p files
 * succeeded_validation.csv and failed_validation.csv for the successful and failed validations respectively.
 * Also the files are sorted in ascending order of the row_id column.
 * The Thread concept has been implemented to improve the performance of the processing 
 */

package UKPostCodeVal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ImprovedBulkValidation {

	/**
	 * @param args
	 */

	// unzip the i/p file and keep in the file path as required
	final static String csvInput = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/import_data1.csv";

	// provide the o/p file path for the successful validations
	final static String csvOPass = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/succeeded_validation3.csv";

	// provide the o/p file path for the failed validations
	final static String csvOFail = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/failed_validation3.csv";

	static BufferedReader br = null;
	static BufferedWriter bfp = null;
	static BufferedWriter bff = null;
	static String line = null;
	static String[] columns = null;
	static String separator = ",";
	static String valmsg = null;
	static int count = 0;

	static List<Integer> lstp = new ArrayList<Integer>(); // stores the list of
	// rows having
	// successful or passed
	// validations

	static List<Integer> lstf = new ArrayList<Integer>(); // stores the list of
	// rows having failed
	// validations

	static Map<Integer, String> mapkey = new HashMap<Integer, String>(); // stores
																			// the
																			// key
																			// value
																			// pair
																			// of

	// row_id and postcode used for sorting

	/**
	 * Constructor to accept thread for ThreadPool
	 * 
	 * @param numberOfThread
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(5);
		try {

			bfp = new BufferedWriter(new FileWriter(csvOPass));
			bff = new BufferedWriter(new FileWriter(csvOFail));
			br = new BufferedReader(new FileReader(csvInput));

			while ((line = br.readLine()) != null) {
				ReadFile rdinp = new ReadFile();
				executor.execute(rdinp);
				try {
					Thread.sleep(2); // milliseconds
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}

			// sort the successful validation list in ascending order of row_id
			// column value
			Collections.sort(lstp, new Comparator<Integer>() {
				public int compare(Integer num1, Integer num2) {
					return num1.compareTo(num2);
				};
			});

			// sort the failed validation list in ascending order of row_id
			// column value
			Collections.sort(lstf, new Comparator<Integer>() {
				public int compare(Integer num1, Integer num2) {
					return num1.compareTo(num2);
				};
			});

			// get the row from the key value pair based on the sorted list
			// and write to the o/p file for successful validation

			for (int r : lstp) {
				bfp.append(mapkey.get(r));
				bfp.append("\n");
			}

			// get the row from the key value pair based on the sorted list
			// and write to the o/p file for failed validation
			for (int r : lstf) {
				bff.append(mapkey.get(r));
				bff.append("\n");
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader/Writer !!!");
			System.out.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					if (bfp != null)
						bfp.close();
					if (bff != null)
						bff.close();
					br.close();
					executor.shutdown();
					System.out
							.println("Successfully created the files "
									+ "succeeded_validation.csv and failed_validation.csv");
				} catch (IOException e) {
					System.out
							.println("Error while closing filereader/filewriter !!!");
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private static class ReadFile implements Runnable {

		public ReadFile() {
		}

		public void run() {
			// Handle record here to validate and store.

			count++; // increment the count of the lines

			// use comma as separator to find the post code to be validated
			columns = line.split(separator);

			if (count > 1) { // skip the first row for validation as it is
								// the header
				valmsg = PostCodeVal.validate(columns[1].trim().toUpperCase()); // pass
																				// the
																				// post
																				// code
				try {
					Thread.sleep(1); // milliseconds
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				mapkey.put(Integer.parseInt(columns[0]), line);
			}
			if (valmsg == null | valmsg == "Valid post code") { // successful
																// validation
				if (count == 1) { // by default write the header row
					try {
						bfp.write(line);
						bfp.append("\n");
					} catch (IOException e) {
						System.out.println("Error in CsvFilWriter1 !!!");
						System.out.println(e.getMessage());
					}
				} else {
					lstp.add(Integer.parseInt(columns[0])); // add the detail
															// rows to the
															// successful
															// validation list
				}

			}
			if (valmsg == null | valmsg != "Valid post code") { // failed
																// validation
				if (count == 1) { // by default write the header row
					try {
						bff.write(line);
						bff.append("\n");
					} catch (IOException e) {
						System.out.println("Error in CsvFilWriter2 !!!");
						System.out.println(e.getMessage());
					}

				} else {
					lstf.add(Integer.parseInt(columns[0])); // add the detail
															// rows to the
															// failed validation
															// list
				}
			}
		}
	}

}