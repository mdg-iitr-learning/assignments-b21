####################### PROCEDURE FOR MAKING A NATIVE FUNCTION ###########################

################### Part one (java program)##############

First, we need to make the java class which uses the native function, following the layout:
```
public class ClassName {

	public native <returntype> <name>(<arguments>);   // this is the native function, e.g.,
							  // public native int factorial(int n);

	static { System.loadLibrary("HelloImpl"); }      // load the native library

	public static void main(String[]  args){
		ClassName obj = new ClassName();
		obj.<name>(<arguments>);
	}
}

```
NOTE:
The library file (compiled C code) will be named libHelloImpl.so (Unix) 
but when loaded in java, it has to be loaded as HelloImpl


################### Part two (header file generation) ####################

Use the JDK javah utility to generate the header file "classname.h" with a function prototype for the native method:
```
~$ javac -d ./classes/ Hello.java
```
The above command generates a class file in a sub directory (of the pwd) named "classes".
Then inside the classes directory, run:
```
~$ javah -jni Hello 
```
to generate the header file, Hello.h in the same directory.


################### Part three (native C file) ###########################

To view the .h file in the terminal, run:
```
~$ cat Hello.h
```
While creating the C file, follow the following layout:
```
#include <stdio.h>
#include "Hello.h"

JNIEXPORT void JNICALL Java_Hello_sayHi(JNIEnv *env, jobject obj, <arg 1>, <arg 2>,...) {
  
  //suppose we had a string (which contains the name of a person) named "who", 
  //to convert it into a native array, do:
  jboolean iscopy;
  const char *name;
  name = (*env)->GetStringUTFChars(env, who, &iscopy);

  // for integer array conversion (suppose the array is names "arr")
  jint *native_array;
  native_array = (*env)->GetIntArrayElements(env, arr, NULL);
  //after processing, add this line:
  (*env)->ReleaseIntArrayElements(env, array, native_array, 0);


  // declare jint i before using in the loop.
  jint i;
  for (i = 0; i < 5; i++) {
    printf("Hello %s\n", name);
  }
}

```
NOTE:
1) If you want to access other functions from the native function in the C file,
   (some non native functions, like maybe void printArray(int[] arr))
   you need to declare all such funtions as static or there will be
   a warning "implicit definition of function" and compilation will terminate
2) The signature for the native function is provided in the header (.h) file.
   View it to check the signature, and the datatypes, to use the 
   correct conversions.


################# part 4 (C file compilation) ##########################

Save the C file in the ./classes directory where the header file and compiled java class file exist. 
For compilation, write:
```
~$ gcc -I/usr/lib/jvm/java-9-oracle/include/ -I/usr/lib/jvm/java-9-oracle/include/linux  -o libHelloImpl.so -shared Permutations.c
```
This created the library file libHelloImpl.so which is to be loaded in the java class.
Then set the LD_LIBRARY_PATH to current working directory:
```
~$ export LD_LIBRARY_PATH=.
```
Finally, run your application in the directory where your compiled classes are stored ("classes" in this case):
```
~$ java ClassName
```
