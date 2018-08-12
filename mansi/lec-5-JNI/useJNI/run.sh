javac -d ./classes Permutations.java
cd classes
javah -jni arraypermutations.Permutations
gcc -o libPermutationsImpl.so -lc -shared -I/usr/lib/jvm/java-9-openjdk-amd64/include -I/usr/lib/jvm/java-9-openjdk-amd64/include/linux Permutations.c -fPIC
export LD_LIBRARY_PATH=.
java arraypermutations.Permutations
