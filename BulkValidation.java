/**
 * @author Subhasmita Sethy
 * @Project Validation of UK Post Codes
 *
 * This class is for the bulk validation of post codes. 
 * Takes the input CSV file (import_data.csv), validates the post code for each row and produces the o/p file
 * failed_validation.csv for the failed validations  
 */

package UKPostCodeVal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class BulkValidation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// unzip the i/p file and keep in the file path as required
		final String csvInput = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/import_data.csv";

		// provide the o/p file path for the failed validations
		final String csvOutput = "C:/Users/user/Pictures/Desktop/UK Postcode Validation/failed_validation.csv";

		BufferedReader br = null;
		String line = null;
		String [] columns = null;
		String separator = ",";
		FileWriter fw = null;
		String valmsg = null;
		int count = 0;
		try {

			br = new BufferedReader(new FileReader(csvInput));
			fw = new FileWriter(csvOutput);

			while ((line = br.readLine()) != null) {

				// use comma as separator to find the post code to be validated
				columns = line.split(separator);

				count++; // increment the count of the lines

				if (count > 1) { // skip the first row for validation as it is
									// the header
					valmsg = PostCodeVal.validate(columns[1].trim()
							.toUpperCase()); // pass the post code
				}
				if (valmsg != "Valid post code" | valmsg == null) {
					System.out.println(valmsg);
					// write the record to the o/p file if the validation is not
					// successful,
					// by default add the header row too
					fw.append(columns[0]);
					fw.append(",");
					fw.append(columns[1]);
					fw.append("\n");
				}
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileReader/Writer !!!");
			System.out.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
					fw.flush();
					fw.close();
					System.out
							.println("Successfully created the file failed_validation.csv");
				} catch (IOException e) {
					System.out
							.println("Error while closing filereader/filewriter !!!");
					System.out.println(e.getMessage());
				}
			}
		}

	}

}