import java.io.*;

class Permutations
{
    //Prints the array
    void printArr(int a[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
 
    //Generating permutation using Heap Algorithm
    void heapPermutation(int a[], int size, int n)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1)
            printArr(a,n);
 
        for (int i=0; i<size; i++)
        {
            heapPermutation(a, size-1, n);
 
            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                int temp = a[0];
                a[0] = a[size-1];
                a[size-1] = temp;
            }
 
            // If size is even, swap ith and last
            // element
            else
            {
                int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;
            }
        }
    }
 
    // Driver code
    public static void main(String args[])
    {
        Permutations obj = new Permutations();
        int a[] = {1,2,3,4,5,6,7,8,9,10};

        long start=System.currentTimeMillis();
        obj.heapPermutation(a, a.length, a.length);
        long end=System.currentTimeMillis();

        System.out.println("Time taken by Java Only(in milis) : "+(end-start));
    }
}
