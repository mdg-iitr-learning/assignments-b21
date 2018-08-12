#include <stdio.h>
#include "Permutations.h"

//Prints the array
static void printArr(int a[],int n)
{
    for (int i=0; i<n; i++)
    	printf("%d, ",a[i]);
        
    printf("\n");
}
 
// Generating permutation using Heap Algorithm
static void heapPermutation(int a[], int size, int n)
{
    // if size becomes 1 then prints the obtained
    // permutation
    if (size == 1)
    {
        printArr(a, n);
        return;
    }
 
    for (jint i=0; i<size; i++)
    {
        heapPermutation(a,size-1,n);
 
        // if size is odd, swap first and last
        // element
        if (size%2==1){
            int temp = a[0];
            a[0] = a[size-1];
            a[size-1] = temp;
        }
 
        // If size is even, swap ith and last
        // element
        else{
				int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;        }
    }
}


JNIEXPORT void JNICALL Java_Permutations_printPermutations
  (JNIEnv *env, jobject obj, jintArray array, jint size, jint n){

  	jint *native_array;
  	native_array = (*env)->GetIntArrayElements(env, array, NULL);

  	heapPermutation(native_array,size,n);
  }