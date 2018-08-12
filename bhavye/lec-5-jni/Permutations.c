#include <stdio.h>
#include "Permutations.h"

//function to print the array
static void printarray(int arr[], int size)
{
    int i,j;
    for(i=0; i<size; i++)
    {
        printf("%d\t",arr[i]);
    }
    printf("\n");
}

//function to swap the variables
static void swap(int *a, int *b)
{
    int temp;
    temp = *a;
    *a = *b;
    *b = temp;
}

static void permute(int arr[], int start, int end)
{
    
    if(start == end)
    {
        printarray(arr, end+1);
        return;
    }

    jint i;
    for(i=start;i<=end;i++)
    {
        //swapping numbers
        swap((arr+i), (arr+start));
        //fixing one first digit
        //and calling permutation on
        //the rest of the digits
        permute(arr, start+1, end);
        swap((arr+i), (arr+start));
    }    
}

//permutation function
JNIEXPORT void JNICALL Java_Permutations_permutation(JNIEnv *env, jobject obj, jintArray arr, jint start, jint end)
{
    jint *native_array;
    native_array = (*env)->GetIntArrayElements(env, arr, NULL);
    permute(native_array, start, end);
    (*env)->ReleaseIntArrayElements(env, arr, native_array, 0);
}