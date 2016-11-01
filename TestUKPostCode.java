/**
 * @author Subhasmita Sethy
 * @Project Validation of UK Post Codes
 *
 * This class is to test the post code validation as part of Part1 task. It takes an input post code and 
 * tests the validation message for the post code against the given regex pattern.
 */

package UKPostCodeVal;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

public class TestUKPostCode extends TestCase {

	// @ Test the validate method of the PostCodeVal class
	public void testValidate() {

		// assertEquals(String expected, String actual)
		// assertEquals(String message, String expected, String actual)

		assertEquals("Junk", PostCodeVal.validate("$%± ()()"));
		assertEquals("Invalid", PostCodeVal.validate("XX XXX"));
		assertEquals("Incorrect inward code length",
				PostCodeVal.validate("A1 9A"));
		assertEquals("No space", PostCodeVal.validate("LS44PL"));
		assertEquals("'Q' in first position", PostCodeVal.validate("Q1A 9AA"));
		assertEquals("'V' in first position", PostCodeVal.validate("V1A 9AA"));
		assertEquals("'X' in first position", PostCodeVal.validate("X1A 9BB"));
		assertEquals("'I' in second position", PostCodeVal.validate("LI10 3QP"));
		assertEquals("'J' in second position", PostCodeVal.validate("LJ10 3QP"));
		assertEquals("'Z' in second position", PostCodeVal.validate("LZ10 3QP"));
		assertEquals("'Q' in third position with 'A9A' structure",
				PostCodeVal.validate("A9Q 9AA"));
		assertEquals("'C' in fourth position with 'AA9A' structure",
				PostCodeVal.validate("AA9C 9AA"));
		assertEquals("Area with only single digit districts",
				PostCodeVal.validate("FY10 4PL"));
		assertEquals("Area with only double digit districts",
				PostCodeVal.validate("SO1 4QQ"));

		assertEquals("None", "Valid post code",
				PostCodeVal.validate("EC1A 1BB"));
		assertEquals("None", "Valid post code", PostCodeVal.validate("W1A 0AX"));
		assertEquals("None", "Valid post code", PostCodeVal.validate("M1 1AE"));
		assertEquals("None", "Valid post code", PostCodeVal.validate("B33 8TH"));
		assertEquals("None", "Valid post code", PostCodeVal.validate("CR2 6XH"));
		assertEquals("None", "Valid post code",
				PostCodeVal.validate("DN55 1PT"));
		assertEquals("None", "Valid post code", PostCodeVal.validate("GIR 0AA"));
		assertEquals("None", "Valid post code",
				PostCodeVal.validate("SO10 9AA"));
		assertEquals("None", "Valid post code", PostCodeVal.validate("FY9 9AA"));
		assertEquals("None", "Valid post code",
				PostCodeVal.validate("WC1A 9AA"));

	}

	public static Test suite() { // for putting into a test suite
		return new TestSuite(TestUKPostCode.class);
	}

	public static void main(String[] args) { // execute the test suite
		junit.textui.TestRunner.run(TestUKPostCode.class);
	}
}
