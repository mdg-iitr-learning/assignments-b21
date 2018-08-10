#include<stdio.h>
#include<time.h>
#include "Permutation.h"
void permut(jint arr[],jint start, jint end){
	// Backtracking Algorithm
	if(start==end){
		for(int i = 0; i<end; i++){
			printf("%d", arr[i]);
		}
		printf("\n");
	}
	else{
		for(int i = start; i<end; i++){
			jint temp = arr[start];
			arr[start] = arr[i];
			arr[i] = temp;
			permut(arr,start+1,end);
			temp = arr[start];
			arr[start] = arr[i];
			arr[i] = temp;
		}
	}
}

JNIEXPORT void JNICALL Java_Permutation_printPermutation(JNIEnv *env, jobject obj, jstring arr, jint length){
	 const char*ptr;
	 jint i;
	 jboolean iscopy;
	ptr = (*env)->GetStringUTFChars(env,arr, &iscopy);
	jint intArr[length];
	for(i = 0; i<length; i++){
		intArr[i] = (*ptr)-48 ;
		ptr++;
	}
	permut(intArr,0,length);
}
