package kyu4;

public class LongestSlideDown {
	    public static int longestSlideDown(int[][] pyramid) {
	        int[][] max = pyramid;

	        for (int y = pyramid.length-2; y >= 0; y--) {
	            for (int x = 0; x < pyramid[y].length; x++) {
	                max[y][x] = pyramid[y][x] + Math.max(pyramid[y + 1][x], pyramid[y + 1][x + 1]);
	            }
	        }

	        return max[0][0];
	    }
}