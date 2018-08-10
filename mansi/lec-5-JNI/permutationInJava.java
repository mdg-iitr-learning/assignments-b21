import java.util.Scanner;
public class permutationInJava{
	static void print(int a[]){
		for (int i=0; i<a.length ; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println("");
	}

	static void permute(int a[], int size){
		if (size==1) {
				for (int i=0; i<a.length ; i++) {
					System.out.print(a[i]+" ");
				}
				System.out.println("");
		}
		else{
			for (int i=0; i<size; i++) {
				permute(a, size-1);

				if (size%2==0) {
					int temp = a[i];
					a[i] = a[size-1];
					a[size-1] = temp;
				}
				else{
					int temp = a[0];
					a[0] = a[size-1];
					a[size-1] = temp;
				}
			}
		}
	}

	public static void main(String args[]){
		long startTime = System.nanoTime();
		//Scanner in = new Scanner(System.in);
		int n = 8;
		int[] arr = new int[n];
		for (int j=0; j<n ;j++ ) {
			arr[j] = j;
		}
		permutationInJava.permute(arr, n);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000000;
		System.out.println("execution time: "+totalTime+" ms");
	}
}