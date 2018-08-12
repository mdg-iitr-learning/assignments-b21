public class Permutations {
	
	public native void permutation(int[] arr, int start, int end);

	static { System.loadLibrary("PermuteImpl"); }

	public static void main(String[] args) {
		Permutations perm = new Permutations();
		int[] num = {1,2,3,4,5,6,7,8,9,10};

		long start = System.currentTimeMillis();
		perm.permutation(num, 0, (num.length-1));
		long end = System.currentTimeMillis();

		System.out.println("execution time = " + ((end-start)/1000.0) + " s");
	}

}