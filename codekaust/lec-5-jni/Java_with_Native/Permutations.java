public class Permutations{

	public native void printPermutations(int[] arr, int size, int n);

  	static { System.loadLibrary("PrintPermutations"); }

	public static void main(String[] args) {
		Permutations obj = new Permutations();
        int a[] = {1,2,3,4,5,6,7,8,9,10};

        long start=System.currentTimeMillis();
        obj.printPermutations(a, a.length, a.length);
        long end=System.currentTimeMillis();

        System.out.println("Time taken by Java_with_Native(in milis) : "+(end-start));
	}
}