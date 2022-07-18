package kyu4;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SumOfDivided {

	public static String sumOfDivided(int[] l) {
		int max = Arrays.stream(l).map(e -> Math.abs(e)).max().orElse(-1);
		List<Integer> simpleDeviders = new ArrayList<>();
		for (int i = 2; i <= max; i++) {
			if (checkSimple(i)) simpleDeviders.add(i);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < simpleDeviders.size(); i++) {
			int devider = simpleDeviders.get(i);
			int[] elements = Arrays.stream(l).filter(e -> e % devider == 0).toArray();
			int sum = Arrays.stream(elements).sum();
			if (sum != 0 || elements.length > 0) sb.append("(" + devider + " " + sum + ")");
		}
		return sb.toString();
	}

	public static boolean checkSimple(int i) {
		if (i <= 1)	return false;
		else if (i <= 3) return true;
		else if (i % 2 == 0 || i % 3 == 0) return false;
		int n = 5;
		while (n * n <= i) {
			if (i % n == 0 || i % (n + 2) == 0)
				return false;
			n = n + 6;
		}
		return true;
	}
}