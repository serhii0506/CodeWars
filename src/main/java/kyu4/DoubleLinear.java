package kyu4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoubleLinear {

	public static int dblLinear(int n) {
		if (n == 0) return 1;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		recursion(list, list, n);
		return list.stream().distinct().toList().get(n);
	}

	private static void recursion( List<Integer> before, List<Integer> list, int n) {
		List<Integer> next = new ArrayList<>();
		for(int i : before) {
			next.add(i * 2 + 1);
			next.add(i * 3 + 1);
		}
		list.addAll(next);
		Collections.sort(list);
		if (list.size() < n*6) recursion(next, list, n);
	}
}