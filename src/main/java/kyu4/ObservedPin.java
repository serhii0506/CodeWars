package kyu4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ObservedPin {
	public static List<String> getPINs(String observed) {
		int[][] arr = new int[][] { { 0, 8 }, { 1, 2, 4 }, { 1, 2, 3, 5 }, { 2, 3, 6 },
			{ 1, 4, 5, 7 }, { 2, 4, 5, 6, 8 }, { 3, 5, 6, 9 }, { 4, 7, 8 }, { 5, 7, 8, 9, 0 }, { 6, 8, 9 } };
		int el = Integer.parseInt(String.valueOf(observed.charAt(0)));
		if(observed.length() == 1) return Arrays.stream(arr[el]).boxed().map(e->String.valueOf(e)).collect(Collectors.toList());
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
					after = newString.substring(index+1);
				String combo = before + i1 + after;
				list.add(combo);
				if(index < newString.length()-1) rec(combo, index+1, list, arr);
			}		
	}

}
