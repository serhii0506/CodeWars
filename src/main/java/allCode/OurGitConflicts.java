package allCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OurGitConflicts {

	/**
	 * kyu0 .!.
	 *
	 * @author Ihor Polishchuk
	 */
	private static void printKyu() {
		System.out.println(".!.");
	}

	/**
	 * kyu5 - Not very secure
	 *
	 * @author Dmytro Lutsenko
	 */
	public static boolean alphanumeric(String s){
		return s.matches("^([a-zA-Z\\d]+)$");
	}


	/**
	 * kyu4 - Most frequently used words in a text
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static List<String> top3(String s) {
		String[] array = s.toLowerCase().replaceAll("(?i)[^А-ЯЁA-Z']+", " ").split(" ");
		Map<String, Integer> words = new TreeMap<>();
		for (String str : array) {
			if (str.matches("\\w+('\\w*)*")) {
				if (words.containsKey(str)) {
					words.put(str, words.get(str) + 1);
				} else {
					words.put(str, 1);
				}
			}
		}
		return words.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3)
				.map(e -> e.getKey()).collect(Collectors.toList());
	}

	/**
	 * kyu4 - Roman Numerals Helper
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	private static int numberRomanNumeralsHelper;

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
		numberRomanNumeralsHelper = 0;
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
		return numberRomanNumeralsHelper;
	}

	private static int converter(String[] elements, int i, String a, String b, String c, int multiply) {
		int count = 0;
		String element = elements[i];
		if (element.equals(a)) {
			if (i < elements.length - 1 && elements[i + 1].equals(b)) {
				numberRomanNumeralsHelper += 4 * multiply;
				count++;
			} else if (i < elements.length - 1 && elements[i + 1].equals(c)) {
				numberRomanNumeralsHelper += 9 * multiply;
				count++;
			} else {
				for (int x = i; x < elements.length; x++) {
					if (elements[x].equals(a)) {
						numberRomanNumeralsHelper += 1 * multiply;
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
						numberRomanNumeralsHelper += 1 * multiply;
						count++;
					} else {
						break;
					}
				}
			}
			numberRomanNumeralsHelper += 5 * multiply;
		}
		return count;
	}

	/**
	 * kyu4 - MatrixDeterminant
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static int determinant(int[][] matrix) {
		int det = 0;
		if (matrix.length == 1)
			return matrix[0][0];
		if (matrix.length == 2)
			return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
		else {
			for (int j = 0; j < matrix.length; j++) {
				int[][] newMatrix = new int[matrix.length - 1][matrix.length - 1];
				for (int y = 0; y < newMatrix.length; y++) {
					int plus = 0;
					for (int x = 0; x < newMatrix.length; x++) {
						if (x == j)
							plus++;
						newMatrix[y][x] = matrix[y + 1][x + plus];
					}
				}
				det = det + (int) (Math.pow(-1, j + 2)) * matrix[0][j] * determinant(newMatrix);
			}

		}

		return det;
	}

	/**
	 * kyu4 - Pyramid Slide Down
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static int longestSlideDown(int[][] pyramid) {
		int[][] max = pyramid;

		for (int y = pyramid.length - 2; y >= 0; y--) {
			for (int x = 0; x < pyramid[y].length; x++) {
				max[y][x] = pyramid[y][x] + Math.max(pyramid[y + 1][x], pyramid[y + 1][x + 1]);
			}
		}

		return max[0][0];
	}

	/**
	 * kyu4 - SumOfDivided
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static String sumOfDivided(int[] l) {
		int max = Arrays.stream(l).map(e -> Math.abs(e)).max().orElse(-1);
		List<Integer> simpleDeviders = new ArrayList<>();
		for (int i = 2; i <= max; i++) {
			if (checkSimple(i))
				simpleDeviders.add(i);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < simpleDeviders.size(); i++) {
			int devider = simpleDeviders.get(i);
			int[] elements = Arrays.stream(l).filter(e -> e % devider == 0).toArray();
			int sum = Arrays.stream(elements).sum();
			if (sum != 0 || elements.length > 0)
				sb.append("(" + devider + " " + sum + ")");
		}
		return sb.toString();
	}

	public static boolean checkSimple(int i) {
		if (i <= 1)
			return false;
		else if (i <= 3)
			return true;
		else if (i % 2 == 0 || i % 3 == 0)
			return false;
		int n = 5;
		while (n * n <= i) {
			if (i % n == 0 || i % (n + 2) == 0)
				return false;
			n = n + 6;
		}
		return true;
	}

	/**
	 * kyu4 - Twice Linear
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static int dblLinear(int n) {
		if (n == 0)
			return 1;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		recursion(list, list, n);
		return list.stream().distinct().toList().get(n);
	}

	private static void recursion(List<Integer> before, List<Integer> list, int n) {
		List<Integer> next = new ArrayList<>();
		for (int i : before) {
			next.add(i * 2 + 1);
			next.add(i * 3 + 1);
		}
		list.addAll(next);
		Collections.sort(list);
		if (list.size() < n * 6)
			recursion(next, list, n);
	}

	/**
	 * kyu4 - Mixing
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static String mix(String s1, String s2) {
		int[] arr1 = new int[26];
		s1.replaceAll("[^a-z]", "").chars().forEach(c -> arr1[c - 97]++);
		int[] arr2 = new int[26];
		s2.replaceAll("[^a-z]", "").chars().forEach(c -> arr2[c - 97]++);
		int max1 = Arrays.stream(arr1).max().orElse(-1);
		int max2 = Arrays.stream(arr2).max().orElse(-1);
		int max = Math.max(max1, max2);
		List<String> list = new ArrayList<>();
		for (int i = max; i > 1; i--) {
			for (int j = 0; j < arr1.length; j++) {
				if (arr1[j] == i) {
					char letter = (char) (j + 97);
					if (arr2[j] < arr1[j]) {
						list.add("1:" + String.valueOf(letter).repeat(arr1[j]));
					} else if (arr2[j] > arr1[j]) {
						list.add("2:" + String.valueOf(letter).repeat(arr2[j]));
					} else if (arr2[j] == arr1[j]) {
						list.add("=:" + String.valueOf(letter).repeat(i));

					}
					arr1[j] = arr2[j] = 0;
				} else if (arr2[j] == i) {
					char letter = (char) (j + 97);
					list.add("2:" + String.valueOf(letter).repeat(arr2[j]));
					arr1[j] = arr2[j] = 0;
				}
			}
		}
		List<String> list2 = new ArrayList<>();
		for (int i = max + 2; i > 3; i--) {
			int length = i;
			List<String> before = list.stream().filter(e -> e.length() == length).collect(Collectors.toList());
			Collections.sort(before);
			before.forEach(e -> list2.add(e));
		}
		return String.join("/", list2);
	}

	/**
	 * kyu4 - ObserverPin
	 * 
	 * @author Serhii Yakovenko.
	 * 
	 */
	public static List<String> getPINs(String observed) {
		int[][] arr = new int[][] { { 0, 8 }, { 1, 2, 4 }, { 1, 2, 3, 5 }, { 2, 3, 6 }, { 1, 4, 5, 7 },
				{ 2, 4, 5, 6, 8 }, { 3, 5, 6, 9 }, { 4, 7, 8 }, { 5, 7, 8, 9, 0 }, { 6, 8, 9 } };
		int el = Integer.parseInt(String.valueOf(observed.charAt(0)));
		if (observed.length() == 1)
			return Arrays.stream(arr[el]).boxed().map(e -> String.valueOf(e)).collect(Collectors.toList());
		List<String> result = new ArrayList<String>();
		for (int i1 : arr[el]) {
			String combo = i1 + observed.substring(1);
			result.add(combo);
			rec(combo, 1, result, arr);
		}
		return result.stream().distinct().collect(Collectors.toList());
	}

	private static void rec(String newString, int index, List<String> list, int[][] arr) {
		int el = Integer.parseInt(String.valueOf(newString.charAt(index)));
		for (int i1 : arr[el]) {
			String before = newString.substring(0, index);
			String after = "";
			if (newString.length() - index >= 2)
				after = newString.substring(index + 1);
			String combo = before + i1 + after;
			list.add(combo);
			if (index < newString.length() - 1)
				rec(combo, index + 1, list, arr);
		}
	}

}
