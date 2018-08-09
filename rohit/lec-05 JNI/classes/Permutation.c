#include <stdio.h>
#include "com_rohit_jni_Permutation.h"

void permute(int *arr, int idx, int len) {
	if(idx >= len - 1) {
		printf("[");
		for(int i = 0; i < len - 1; i++)
			printf("%d, ", arr[i]);
		if(len > 0)
			printf("%d", arr[len - 1]);
		printf("]\n");
		return;
	}

	for(int i = idx; i < len; i++) {
		int t = arr[idx];
		arr[idx] = arr[i];
		arr[i] = t;

		permute(arr, idx + 1, len);

		t = arr[idx];
		arr[idx] = arr[i];
		arr[i] = t;
	}
}

JNIEXPORT void JNICALL Java_com_rohit_jni_Permutation_permute(JNIEnv *env, jobject obj, jintArray arr, jint idx, jint len) {
	permute((int*) (*env)->GetIntArrayElements(env, arr, 0), (int) idx, (int) len);
}
