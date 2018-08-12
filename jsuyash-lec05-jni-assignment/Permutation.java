public class Permutation{
	public native void printPermutation(String array, int length); 
	static{ System.loadLibrary("PermutationImpl");	}    
	public static void permut(int arr[],int start, int end){
		// Backtracking Algorithm
		if(start==end){
			for(int i = 0; i<end; i++){
				System.out.print("" + arr[i]);
			}
			System.out.println();
		}
		else{
			for(int i = start; i<end; i++){
				int temp = arr[start];
				arr[start] = arr[i];
				arr[i] = temp;
				permut(arr,start+1,end);
				temp = arr[start];
				arr[start] = arr[i];
				arr[i] = temp;
			}
		}
	} 
	public static void main(String[] args){

		final long cstartTime = System.nanoTime();
		System.out.println("Permutation using C:");
		Permutation permutation = new Permutation();
		String str = args[0];
		int length = str.length();
		permutation.printPermutation(str,length);
		final double cduration = (double)(System.nanoTime() - cstartTime)/1000000000;
		System.out.println("Execution time using C : " + cduration);

		System.out.println("\n\n**********************************\n\n");

		final long jstartTime = System.nanoTime();
		System.out.println("Permutation using JAVA:");
		int arr[] = new int[length] ;
		for(int i = 0; i<length; i++){
			arr[i] = Integer.parseInt(Character.toString(str.charAt(i)));
		}
		permut(arr,0,length);
		final float jduration = (float)(System.nanoTime() - jstartTime)/1000000000;
		System.out.println("Execution time using JAVA : " + jduration);
	}
}