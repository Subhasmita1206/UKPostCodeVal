/**
 * @author Subhasmita Sethy
 * @Project Validation of UK Post Codes
 *
 * This class takes the post code from the user as input and validates against the regex pattern 
 * mentioned in the PostCodeVal class method validate and returns the validation message back to the user
 */
package UKPostCodeVal;

import java.util.Scanner;

public class AcceptPostCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// get the input from the user from console
		Scanner postcode = new Scanner(System.in);
		String postcode_val = null; // validation message to be displayed back
									// to the user

		while (postcode_val == null || postcode_val.isEmpty()) {
			// prompt the user until valid input has been entered

			System.out.println("Enter the UK Postcode to validate: ");

			postcode_val = postcode.nextLine(); // get the input from user

		}

		System.out.println("Postcode entered for validation: " + postcode_val);

		// call the validate method from the PostCodeVal class
		String valmsg = PostCodeVal.validate(postcode_val.trim().toUpperCase());

		System.out.println(valmsg); // display the validation message to user

	}

}
