javac -d ./classes src/com/rohit/jni/Permutation.java
cd classes
javah -jni com.rohit.jni.Permutation
export LD_LIBRARY_PATH=.
gcc -o libPermuteImpl.so -lc -shared -I/usr/lib/jvm/java-8-openjdk/include -I/usr/lib/jvm/java-8-openjdk/include/linux Permutation.c -fPIC
java com.rohit.jni.Permutation
