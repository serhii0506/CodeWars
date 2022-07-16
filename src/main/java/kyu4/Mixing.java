package kyu4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mixing {

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
					arr1[j]=arr2[j]=0;
				} else if (arr2[j] == i) {
					char letter = (char) (j + 97);
					list.add("2:" + String.valueOf(letter).repeat(arr2[j]));
					arr1[j]=arr2[j]=0;
				}
			}
		}
		List<String> list2 = new ArrayList<>();
		for(int i = max+2; i>3; i--) {
			int length = i;
			List<String> before = list.stream().filter(e -> e.length() == length).collect(Collectors.toList());
			Collections.sort(before);
			before.forEach(e->list2.add(e));
		}
		return String.join("/", list2);
	}

}