####################### PROCEDURE FOR MAKING A NATIVE FUNCTION ###########################

################### part one (java program)##############

make java class with the layout:
public class ClassName {
	public native <returntype> <name>(<arguments>);

	static { System.loadLibrary("HelloImpl"); }

	public static void main(String[]  args){
		ClassName obj = new ClassName();
		obj.<name>(<arguments>);
	}	
}

// library filename will be called libHelloImpl.so (Unix) 
// but when loaded in java, it has to be loaded as HelloImpl


################### part two (header file generation) ####################

Use the JDK javah utility to generate the header file "classname.h" with a function prototype for the sayHi method:

~$ javac -d ./classes/ Hello.java

Then in the classes directory run:

~$ javah -jni Hello 

to generate the header file Hello.h


################### part three (C file) ###########################

to view the .h file,
~$ cat Hello.h

in C file, follow the following layout:

#include <stdio.h>
#include "Hello.h"

JNIEXPORT void JNICALL Java_Hello_sayHi(JNIEnv *env, jobject obj, <arg 1>, <arg 2>,...) {
  
  //suppose we had a sting (of name) named "who", to make it a native array, do:
  jboolean iscopy;
  const char *name;
  name = (*env)->GetStringUTFChars(env, who, &iscopy);

  // for ineteger array conversion (suppose the array is names "arr")
  jint *native_array;
  native_array = (*env)->GetIntArrayElements(env, arr, NULL);
  //after processing, add this line:
  (*env)->ReleaseIntArrayElements(env, array, native_array, 0);


  // declare jint i before using in the loop.
  jint i;
  for (i = 0; i < times; i++) {
    printf("Hello %s\n", name);
  }
}


NOTE:
1) If you want to access other functions in the C file,
   (some non native functions like maybe void printArray(int[] arr))
   you need to declare all such funtions as static or there will be
   an "implicit definition of function" and compilation will terminate
2) The signature for the native function is provided in the header file.
   View it to check the signature, and the datatypes, to use the 
   correct conversions.


################# part 4 (C file compilation) ##########################

Save the C file in the ./classes directory with the header file and compiled java class. 
For compilation, write:

~$ gcc -I/usr/lib/jvm/java-9-oracle/include/ -I/usr/lib/jvm/java-9-oracle/include/linux  -o libHelloImpl.so -shared Permutations.c

This created the library file libHelloImpl.so which is to be loaded in the java class.
Then set the LD_LIBRARY_PATH to current working directory:

~$ export LD_LIBRARY_PATH=.

Finally, run your application in the directory where your compiled classes are stored ("classes" in this case):

~$ java ClassName