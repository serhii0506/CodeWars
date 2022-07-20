package allCode;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OurGitConflictsTest {
	/**
	 * kyu5 - Not very secure
	 *
	 * @author Dmytro Lutsenko
	 */
	@Test
	@DisplayName("Valid input")
	public void testValidInput() {
		assertTrue(OurGitConflicts.alphanumeric("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
	}

	@DisplayName("Invalid character")
	@ParameterizedTest(name="Should return false for \"{0}\"")
	@ValueSource(strings = {"", "with space", "with_underscore", "punctuation.", "naïve", "１strangedigit", "emoji😊"})
	public void testInvalidChars(String input) {
		assertFalse(OurGitConflicts.alphanumeric(input));
	}


	/**
	 * kyu4 - Most frequently used words in a text
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	 @Test
	  public void top3Test() {
	    assertEquals(Arrays.asList("e", "d", "a"),    OurGitConflicts.top3("a a a  b  c c  d d d d  e e e e e"));
	    assertEquals(Arrays.asList("e", "ddd", "aa"), OurGitConflicts.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
	    assertEquals(Arrays.asList("won't", "wont"),  OurGitConflicts.top3("  //wont won't won't "));
	    assertEquals(Arrays.asList("e"),              OurGitConflicts.top3("  , e   .. "));
	    assertEquals(Arrays.asList(),                 OurGitConflicts.top3("  ...  "));
	    assertEquals(Arrays.asList(),                 OurGitConflicts.top3("  '  "));
	    assertEquals(Arrays.asList(),                 OurGitConflicts.top3("  '''  "));
	    assertEquals(Arrays.asList("a", "of", "on"),  OurGitConflicts.top3(Stream
	                    .of("In a village of La Mancha, the name of which I have no desire to call to",
	                        "mind, there lived not long since one of those gentlemen that keep a lance",
	                        "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
	                        "coursing. An olla of rather more beef than mutton, a salad on most",
	                        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
	                        "on Sundays, made away with three-quarters of his income.")
	                     .collect(Collectors.joining("\n")) )); 
	  }
	
	
	/**
	 * kyu4 - Roman Numerals Helper
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	@Test
	public void testToRomanFromRoman() throws Exception {
		assertThat("1 converts to 'I'", OurGitConflicts.toRoman(1), is("I"));
		assertThat("2 converts to 'II'", OurGitConflicts.toRoman(2), is("II"));
		assertThat("'I' converts to 1", OurGitConflicts.fromRoman("I"), is(1));
		assertThat("'II' converts to 2", OurGitConflicts.fromRoman("II"), is(2));
	}

	/**
	 * kyu4 - MatrixDeterminant
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	@Test
	public void testDeterminant() {
		int[][][] matrix = { { { 1 } }, { { 1, 3 }, { 2, 5 } }, { { 2, 5, 3 }, { 1, -2, -1 }, { 1, 3, 4 } } };

		int[] expected = { 1, -1, -20 };

		String[] msg = { "Determinant of a 1 x 1 matrix yields the value of the one element",
				"Should return 1 * 5 - 3 * 2 == -1 ", "" };
		for (int n = 0; n < expected.length; n++)
			assertEquals(msg[n], expected[n], OurGitConflicts.determinant(matrix[n]));
	}

	/**
	 * kyu4 - Pyramid Slide Down
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	@Test
	public void testLongestSlideDown() {
		int[][] test = new int[][] { { 75 }, { 95, 64 }, { 17, 47, 82 }, { 18, 35, 87, 10 }, { 20, 4, 82, 47, 65 },
				{ 19, 1, 23, 75, 3, 34 }, { 88, 2, 77, 73, 7, 63, 67 }, { 99, 65, 4, 28, 6, 16, 70, 92 },
				{ 41, 41, 26, 56, 83, 40, 80, 70, 33 }, { 41, 48, 72, 33, 47, 32, 37, 16, 94, 29 },
				{ 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14 }, { 70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57 },
				{ 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48 },
				{ 63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31 },
				{ 4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23 }, };
		assertEquals(1074, OurGitConflicts.longestSlideDown(test));
	}

	/**
	 * kyu4 - SumOfDivided
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	@Test
	public void testSumOfDivided() {
		int[] lst = new int[] { 12, 15 };
		assertEquals("(2 12)(3 27)(5 15)", OurGitConflicts.sumOfDivided(lst));
	}

	/**
	 * kyu4 - Twice Linear
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	@Test
	public void testDbLinear() {
		assertEquals(22, OurGitConflicts.dblLinear(10));
		assertEquals(57, OurGitConflicts.dblLinear(20));
		assertEquals(91, OurGitConflicts.dblLinear(30));
		assertEquals(175, OurGitConflicts.dblLinear(50));

	}

	/**
	 * kyu4 - Mixing
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	@Test
	public void testMix() {
		assertEquals("2:eeeee/2:yy/=:hh/=:rr", OurGitConflicts.mix("Are they here", "yes, they are here"));
		assertEquals("1:ooo/1:uuu/2:sss/=:nnn/1:ii/2:aa/2:dd/2:ee/=:gg",
				OurGitConflicts.mix("looping is fun but dangerous", "less dangerous than coding"));
		assertEquals("1:aaa/1:nnn/1:gg/2:ee/2:ff/2:ii/2:oo/2:rr/2:ss/2:tt",
				OurGitConflicts.mix(" In many languages", " there's a pair of functions"));
		assertEquals("1:ee/1:ll/1:oo", OurGitConflicts.mix("Lords of the Fallen", "gamekult"));
		assertEquals("", OurGitConflicts.mix("codewars", "codewars"));
		assertEquals("1:nnnnn/1:ooooo/1:tttt/1:eee/1:gg/1:ii/1:mm/=:rr",
				OurGitConflicts.mix("A generation must confront the looming ", "codewarrs"));
	}

	/**
	 * kyu4 - ObserverPin
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */

	@SuppressWarnings("serial")
	public static HashMap<String, String[]> expectations = new HashMap<String, String[]>() {
		{
			put("8", new String[] { "5", "7", "8", "9", "0" });
			put("11", new String[] { "11", "21", "41", "12", "22", "42", "14", "24", "44" });
			put("369",
					new String[] { "236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299",
							"336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636",
							"638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699" });
		}
	};
	private final static Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);

	@Test
	public void testGetPINs() {
		for (String entered : expectations.keySet()) {
			testForTestGetPINs(entered, Arrays.asList(expectations.get(entered)), OurGitConflicts.getPINs(entered));
		}
	} // testPins

	private static void testForTestGetPINs(String entered, List<String> expected, List<String> result) {
		result.sort(comp);
		expected.sort(comp);
		Assert.assertEquals("For observed PIN " + entered, expected, result);
	}
}
