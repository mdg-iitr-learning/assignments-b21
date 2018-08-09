package arraypermutations;

public class Permutations{
	
	public native void call(int a[], int size, int n);
	
	static { System.loadLibrary("PermutationsImpl");}
	  
	  public static void main (String[] args) {
	    Permutations p = new Permutations();
	    long startTime = System.nanoTime();
		int n = 8;
		int[] arr = new int[n];
		int k=1;
		int l = 2;
		for (int j=0; j<n ;j++ ) {
			arr[j] = j;
		}
		p.call(arr, n, n);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000000;
		System.out.println("execution time: "+totalTime+" ms");
  }
}
