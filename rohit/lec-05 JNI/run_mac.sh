javac -h ./classes -d ./classes src/com/rohit/jni/Permutation.java
cd classes
export LD_LIBRARY_PATH=.
gcc -o libPermuteImpl.jnilib -lc -shared -I/Library/Java/JavaVirtualMachines/jdk-10.0.2.jdk/Contents/Home/include -I/Library/Java/JavaVirtualMachines/jdk-10.0.2.jdk/Contents/Home/include/darwin Permutation.c -fPIC
java com.rohit.jni.Permutation
