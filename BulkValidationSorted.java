/**
 * @author Subhasmita Sethy
 * @Project Validation of UK Post Codes
 *
 * This class is for the bulk validation of post codes with separate files for successful and failed validations. 
 * Takes the input CSV file (import_data.csv), validates the post code for each row and produces the o/p files
 * succeeded_validation.csv and failed_validation.csv for the successful and failed validations respectively.
 * Also the files are sorted in ascending order of the row_id column.
 */
package UKPostCodeVal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkValidationSorted {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// unzip the i/p file and keep in the file path as required
		final String csvInput = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/import_data.csv";

		// provide the o/p file path for the successful validations
		final String csvOPass = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/succeeded_validation.csv";

		// provide the o/p file path for the failed validations
		final String csvOFail = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/failed_validation.csv";

		BufferedReader br = null;
		String line = null;
		String[] columns = null;
		String separator = ",";
		FileWriter fwp = null;
		FileWriter fwf = null;
		String valmsg = null;
		int count = 0;

		List<Integer> lstp = new ArrayList<Integer>(); // stores the list of
														// rows having
														// successful or passed
														// validations

		List<Integer> lstf = new ArrayList<Integer>(); // stores the list of
														// rows having failed
														// validations

		Map<Integer, String> mapkey = new HashMap<Integer, String>(); // stores the key value pair of
																	  // row_id and postcode used for sorting

		try {

			br = new BufferedReader(new FileReader(csvInput));
			fwp = new FileWriter(csvOPass);
			fwf = new FileWriter(csvOFail);

			while ((line = br.readLine()) != null) {

				// use comma as separator to find the post code to be validated
				columns = line.split(separator);

				count++; // increment the count of the lines

				if (count > 1) { // skip the first row for validation as it is
									// the header
					valmsg = PostCodeVal.validate(columns[1].trim()
							.toUpperCase()); // pass the post code
					mapkey.put(Integer.parseInt(columns[0]), line);
				}

				if (valmsg == null | valmsg == "Valid post code") { // successful
																	// validation
					if (count == 1) { 								// by default write the header row
						fwp.append(line);
						fwp.append("\n");
					} else {
						lstp.add(Integer.parseInt(columns[0]));    // add the detail rows to the 
																   //successful validation list
					}

				}
				if (valmsg == null | valmsg != "Valid post code") { // failed
																	// validation
					if (count == 1) {								// by default write the header row
						fwf.append(line);
						fwf.append("\n");
					} else {
						lstf.add(Integer.parseInt(columns[0]));		// add the detail rows to the 
																	// failed validation list
					}
				}
			}

			// sort the successful validation list in ascending order of row_id column value
			Collections.sort(lstp, new Comparator<Integer>() {
				public int compare(Integer num1, Integer num2) {
					return num1.compareTo(num2);
				};
			});

			// sort the failed validation list in ascending order of row_id column value
			Collections.sort(lstf, new Comparator<Integer>() {
				public int compare(Integer num1, Integer num2) {
					return num1.compareTo(num2);
				};
			});

			// get the row from the key value pair based on the sorted list 
			// and write to the o/p file for successful validation
			
			for (int r : lstp) {
				fwp.append(mapkey.get(r));
				fwp.append("\n");

			}

			// get the row from the key value pair based on the sorted list 
			// and write to the o/p file for failed validation
			
			for (int r : lstf) {
				fwf.append(mapkey.get(r));
				fwf.append("\n");
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileReader/Writer !!!");
			System.out.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
					fwp.flush();
					fwp.close();
					fwf.flush();
					fwf.close();
					System.out
						   	.println("Successfully created the files " +
						    "succeeded_validation.csv and failed_validation.csv");
				} catch (IOException e) {
					System.out
							.println("Error while closing filereader/filewriter !!!");
					System.out.println(e.getMessage());
				}
			}
		}

	}

}