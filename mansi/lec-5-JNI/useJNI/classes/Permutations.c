#include <stdio.h>
#include "arraypermutations_Permutations.h"

void permute(int *a, int size, int n){
		if (size==1) {
			//int number = sizeof(a)/sizeof(a[0]);
				for (int i=0; i<n; i++) {
					printf("%d ", a[i]);
				}
				printf("\n");
		}
		else{
			for (int i=0; i<size; i++) {
				permute(a, size-1, n);

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

JNIEXPORT void JNICALL Java_arraypermutations_Permutations_call(JNIEnv *env, jobject cl, jintArray a, jint size, jint n) {
	permute((int *) (*env)->GetIntArrayElements(env, a, 0), (int) size, (int) n);
}

