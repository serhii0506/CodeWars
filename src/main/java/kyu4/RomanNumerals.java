package kyu4;

/*Create a RomanNumerals class that can convert a roman numeral to and from an integer value.
It should follow the API demonstrated in the examples below. Multiple roman numeral values 
will be tested for each helper method.

Modern Roman numerals are written by expressing each digit separately starting with 
the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 
is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; 
or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Input range : 1 <= n < 4000

==help==
I	1
V	5
X	10
L	50
C	100
D	500
M	1000

In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").*/

public class RomanNumerals {
	private static int number;

	public static String toRoman(int n) {
		String[] roman = new String[] { "I", "V", "X", "L", "C", "D", "M" };
		String[] elements = String.valueOf(n).split("");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < elements.length; i++) {
			int number = Integer.parseInt(elements[i]);
			if (number < 4 && number > 0)
				sb.append(roman[(elements.length - i - 1) * 2].repeat(number));
			else if (number == 4)
				sb.append(roman[(elements.length - i - 1) * 2] + roman[(elements.length - i - 1) * 2 + 1]);
			else if (number < 9 && number > 4) {
				sb.append(roman[(elements.length - i - 1) * 2 + 1]);
				if (number > 5) {
					sb.append(roman[(elements.length - i - 1) * 2].repeat(number - 5));
				}
			} else if (number == 9)
				sb.append(roman[(elements.length - i - 1) * 2] + roman[(elements.length - i - 1) * 2 + 2]);
		}
		return sb.toString();
	}

	public static int fromRoman(String romanNumeral) {
		number = 0;
		String[] elements = String.valueOf(romanNumeral).split("");
		for (int i = 0; i < elements.length; i++) {
			String element = elements[i];
			if (element.equals("M") && i == 0) { // 4х
				i += converter(elements, i, "M", "", "", 1000);
			} else if (element.equals("C") || element.equals("D")) { // 3x
				i += converter(elements, i, "C", "D", "M", 100);
			} else if (element.equals("X") || element.equals("L")) { // 2x
				i += converter(elements, i, "X", "L", "C", 10);
			} else if (element.equals("I") || element.equals("V")) {// 1x
				i += converter(elements, i, "I", "V", "X", 1);
			}
		}
		return number;
	}

	private static int converter(String[] elements, int i, String a, String b, String c, int multiply) {
		int count = 0;
		String element = elements[i];
		if (element.equals(a)) {
			if (i < elements.length - 1 && elements[i + 1].equals(b)) {
				number += 4 * multiply;
				count++;
			} else if (i < elements.length - 1 && elements[i + 1].equals(c)) {
				number += 9 * multiply;
				count++;
			} else {
				for (int x = i; x < elements.length; x++) {
					if (elements[x].equals(a)) {
						number += 1 * multiply;
						count++;
					} else {
						count--;
						break;
					}
				}

			}
		} else {
			if (i < elements.length - 1 && elements[i + 1].equals(a)) {
				for (int x = i + 1; x < elements.length; x++) {
					if (elements[x].equals(a)) {
						number += 1 * multiply;
						count++;
					} else {
						break;
					}
				}
			}
			number += 5 * multiply;
		}
		return count;
	}
}