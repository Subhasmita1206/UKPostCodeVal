/**
 * @author Subhasmita Sethy
 * @Project Validation of UK Post Codes
 *
 * This class takes the post code and validates against the regex pattern 
 * in the validate method and returns the validation message
 */

package UKPostCodeVal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PostCodeVal {

	public static String validate(String pstcd) {
		// This method takes the post code and validates against the various
		// regex patterns defined below
		// and passes the validation message as output

		String valmsg = null; // validation message to passed as o/p of this
								// method

		// Regex pattern and matcher for all sorts of post codes
		Pattern p1, p2, p3, p4, p5, p6 = null;
		Matcher m1, m2, m3, m4, m5, m6 = null;

		try {
			/*
			 * UK's modern post code system format which has the most of the
			 * post codes in UK
			 */

			p1 = Pattern
					.compile("(([A-PR-UWYZ][0-9][0-9]?)|(([A-PR-UWYZ][A-HK-Y][0-9](?<!(BR|FY|HA|HD|HG|HR|HS|HX|JE|LD|SM|SR|WC|WN|ZE)[0-9])[0-9])|([A-PR-UWYZ][A-HK-Y](?<!AB|LL|SO)[0-9])|(WC[0-9][A-Z])|(([A-PR-UWYZ][0-9][A-HJKPSTUW])|([A-PR-UWYZ][A-HK-Y][0-9][ABEHMNPRVWXY]))))\\s[0-9][ABD-HJLNP-UW-Z]{2}");
			m1 = p1.matcher(pstcd);

			/*
			 * UK's overseas territories
			 * 
			 * Postcode Location AI-2640 Anguilla
			 * 
			 * GIR 0AA Girobank's headquarters in Bootle XM4 5HQ Special post
			 * code for letters to Santa/Father Christmas
			 * 
			 * ASCN 1ZZ Ascension Island STHL 1ZZ Saint Helena TDCU 1ZZ Tristan
			 * da Cunha
			 * 
			 * BBND 1ZZ British Indian Ocean Territory BIQQ 1ZZ British
			 * Antarctic Territory FIQQ 1ZZ Falkland Islands
			 * 
			 * PCRN 1ZZ Pitcairn Islands SIQQ 1ZZ South Georgia and the South
			 * Sandwich Islands TKCA 1ZZ Turks and Caicos Islands
			 * 
			 * British Virgin Islands:- VG1110 Tortola Central VG1120 Tortola
			 * East VG1130 Tortola West VG1140 Anegada VG1150 Virgin Gorda
			 * VG1160 Jost Van Dyke
			 */

			p2 = Pattern
					.compile("(AI\\-2640)|(GIR\\s0AA)|(XM4\\s5HQ)|((ASCN|STHL|TDCU|BBND|BIQQ|FIQQ|PCRN|SIQQ|TKCA)\\s1ZZ)|(VG11[1-6]0)");
			m2 = p2.matcher(pstcd);
			/*
			 * Bermuda Post Codes CR 01,CR 02,CR 03,CR 04,DD 01,DD 02,DD 03,DV
			 * 01,DV 02,DV 03,DV 04,DV 05,DV 06,DV 07,DV 08, FL 01,FL 02,FL
			 * 03,FL 04,FL 05,FL 06,FL 07,FL 08,GE 01,GE 02,GE 03,GE 04,GE 05,GE
			 * CX, HM 01,HM 02,HM 03,HM 04,HM 05,HM 06,HM 07,HM 08,HM 09,HM
			 * 10,HM 11,HM 12,HM 13,HM 14,HM 15,HM 16,HM 17,HM 18,HM 19,HM 20,
			 * HS 01,HS 02,MA 01,MA 02,MA 03,MA 04,MA 05,MA 06,PG 01,PG 02,PG
			 * 03,PG 04,PG 05,PG 06,SB 01,SB 02,SB 03,SB 04, SN 01,SN 02,SN
			 * 03,SN 04,WK 01,WK 02,WK 03,WK 04,WK 05,WK 06,WK 07,WK 08,WK 09,WK
			 * 10
			 */

			p3 = Pattern
					.compile("((CR|SB|SN)\\s0[1-4])|(DD\\s0[1-3])|((DV|FL)\\s0[1-8])|(GE\\s(0[1-5])|(CX))|(HM\\s0[1-20])|(HS\\s0[1-2])|((MA|PG)\\s0[1-6])|(WK\\s0[1-10])");
			m3 = p3.matcher(pstcd);
			/*
			 * Cayman Islands (KY1,KY2,KY3)-nnnn (post codes)
			 */

			p4 = Pattern
					.compile("(KY1\\-000[1-2])|(KY1\\-10(0[0-9])|(1[0-1]))|(KY1\\-11(0[0-9])|(1[0-2]))|(KY1\\-120[0-9])|(KY1\\-130[0-3])|(KY1\\-140[0-1])|(KY1\\-150[0-8])|(KY1\\-160[0-3])|(KY1\\-170[0-2])|(KY1\\-180[0-1])|(KY2\\-2(00[0-2])|([1-4]0[0-1]))|(KY3\\-250[0-1])");
			m4 = p4.matcher(pstcd);
			/*
			 * British Forest Post Office BFPO 9999 (post codes) This is a
			 * generic format for BFPO , could not find the list in google
			 */

			p5 = Pattern.compile("BFPO\\s[0-9]{1,4}");
			m5 = p5.matcher(pstcd);
			/*
			 * NHS and Public Pseudocode post codes
			 * 
			 * ZZ99 3CZ England (not otherwise stated) ZZ99 3VZ No fixed abode
			 * ZZ99 3WZ Address not known ZZ99 2WZ Northern Ireland (not
			 * otherwise stated) ZZ99 1WZ Scotland (not otherwise stated) ZZ99
			 * 3GZ Wales (not otherwise stated) ZZ99 NNN normally resident
			 * overseas (where NNN is the country code listed in the NHS
			 * postcode directory)
			 */

			p6 = Pattern.compile("ZZ99\\s(3CZ|3VZ|3WZ|2WZ|1WZ|3GZ|NNN)");
			m6 = p6.matcher(pstcd);

			// check if the post code is matching any of the above 6 patterns
			// defined, if yes then it is a valid post code
			if (m1.matches() | m2.matches() | m3.matches() | m4.matches()
					| m5.matches() | m6.matches())
				valmsg = "Valid post code";
			else {
				// Mismatch check only for pattern1 i.e UK modern post code
				// system format

				String mistxt = misMatch(p1, pstcd); // find the mismatch point
														// by calling method
														// misMatch

				if (mistxt != null) {

					String s = mistxt.substring(mistxt.length() - 2,
							mistxt.length()); // find the last 2 characters for
												// mismtach

					if (s.equalsIgnoreCase("Q1"))
						valmsg = "'Q' in first position";
					else if (s.equalsIgnoreCase("V1"))
						valmsg = "'V' in first position";
					else if (s.equalsIgnoreCase("X1"))
						valmsg = "'X' in first position";
					else if (s.equalsIgnoreCase("I2"))
						valmsg = "'I' in second position";
					else if (s.equalsIgnoreCase("J2"))
						valmsg = "'J' in second position";
					else if (s.equalsIgnoreCase("Z2"))
						valmsg = "'Z' in second position";
					else if (s.equalsIgnoreCase("Q3"))
						valmsg = "'Q' in third position with 'A9A' structure";
					else if (s.equalsIgnoreCase("C4"))
						valmsg = "'C' in fourth position with 'AA9A' structure";
					else if (s.equalsIgnoreCase("04"))
						valmsg = "Area with only single digit districts";
					else if (s.equalsIgnoreCase(" 4"))
						valmsg = "Area with only double digit districts";
					else
						valmsg = mistxt;
				}

			}

		} catch (PatternSyntaxException ex) {
			System.out.println("Error in Regex Pattern!!");
			System.out.println(ex.getMessage());
		}

		return valmsg; // returns the validation message
	}

	public static String misMatch(Pattern p, String pstcd) {
		// This method finds the mismatch text for the pattern p1 i.e of the UK
		// modern post code system

		String[] cditems = null; // post code to be split by spaces which will
									// be used for inward code validation

		Pattern p1, p2 = null;
		Matcher m1, m2 = null;

		try {
			// First find out the basic issues with the mismatches

			// Post code should be alphanumeric with digits
			p1 = Pattern.compile("[A-Z0-9\\s]+");
			p2 = Pattern.compile("[0-9]+");
			m1 = p1.matcher(pstcd);
			m2 = p2.matcher(pstcd);
			if (m1.matches() && m2.find()) {
				if (pstcd.contains(" ") == false)
					return "No space"; // no space eg:- LS44PL
				else {
					p1 = Pattern.compile("\\s{2,}");
					if (p1.matcher(pstcd).find())
						return "Invalid space"; // should contain only one space
				}
			} else {
				if (m1.matches())
					return "Invalid"; // should have a digit eg:- XX XXX
				else
					return "Junk"; // should be alphanumeric eg:- $%± ()()

			}
			// the inward code (part after the space) should be 3 characters
			// long
			// and should start with a digit followed by 2 alphabets and can't
			// contain the
			// characters C,I,K,M,O and V

			cditems = pstcd.split("\\s+"); // split the post code to find the
											// first and last past

			if (cditems[1] != null && cditems[1] != "") {
				// check the 9AA pattern for inward code
				if (!cditems[1].matches("[0-9][ABD-HJLNP-UW-Z]{2}"))
					return "Incorrect inward code length"; // eg:- A1 9A
			}

			// Find out the special position mismatches for different patterns
			for (int i = 1; i <= pstcd.length(); i++) {
				m1 = p.matcher(pstcd.substring(0, i));
				if (!m1.matches() && !m1.hitEnd()) {
					return pstcd.substring(0, i) + (i); // return the mismatch
														// point along with the
														// position

				}
			}
			return pstcd + pstcd.length(); // return the entire string if a
											// total mismatch

		} catch (PatternSyntaxException ex) {
			System.out.println("Error in Regex Pattern!!");
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
