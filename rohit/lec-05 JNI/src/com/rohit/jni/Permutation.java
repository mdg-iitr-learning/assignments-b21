package com.rohit.jni;
import java.util.Scanner;

public class Permutation {
	public native void permute(int[] arr, int idx, int len);
	static { System.loadLibrary("PermuteImpl"); }

	public void permutate(int[] arr, int idx) {
		if(idx >= arr.length -1) {
			System.out.print("[");
			for(int i = 0; i < arr.length - 1; i++)
				System.out.print(arr[i] + ", ");
			if(arr.length > 0)
				System.out.print(arr[arr.length - 1]);
			System.out.println("]");
			return;
		}

		for(int i = idx; i < arr.length; i++) {
			int t = arr[idx];
			arr[idx] = arr[i];
			arr[i] = t;

			permutate(arr, idx + 1);

			t = arr[idx];
			arr[idx] = arr[i];
			arr[i] = t;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of elements: ");

		final int n = in.nextInt();
		int a[] = new int[n];

		in.close();

		for(int i = 0; i < n; i++)
			a[i] = i;

		Permutation p = new Permutation();

		System.out.println("Java Array Permutation:");
		long jStartTime = System.nanoTime();
		p.permutate(a, 0);
		long jEndTime = System.nanoTime();
		System.out.println("C Array Permutation:");
		long cStartTime = System.nanoTime();
		p.permute(a, 0, n);
		long cEndTime = System.nanoTime();
		System.out.println("Java execution time: " + (jEndTime - jStartTime) / 1000000 + "ms");
		System.out.println("C execution time: " + (cEndTime - cStartTime) / 1000000 + "ms");
	}
}
