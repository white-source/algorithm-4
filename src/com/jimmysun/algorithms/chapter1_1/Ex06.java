package com.jimmysun.algorithms.chapter1_1;

import edu.princeton.cs.algs4.StdOut;

public class Ex06 {
	public static void main(String[] args) {
		int f = 0;
		int g = 1;
		for (int i = 0; i <= 15; i++) {
			StdOut.println(f);
			f = f + g;
			g = f - g;
		}
	}
	/*f = 1 g = 0;
	* f =1  g = 1
	* f = 2  g=1
	* f = 3  g =2
	* f =5  g = 3
	* f = 8  g = 5
	*
	* */

}
