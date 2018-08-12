public class Plain {
    public static void permute(int[] arr){
      permuteHelper(arr, 0);
  }

  private static void permuteHelper(int[] arr, int index){
      if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute
          //System.out.println(Arrays.toString(arr));
          //Print the array
          System.out.print("[");
          for(int i = 0; i < arr.length - 1; i++){
              System.out.print(arr[i] + ", ");
          }
          if(arr.length > 0)
              System.out.print(arr[arr.length - 1]);
          System.out.println("]");
          return;
      }

      for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

          //Swap the elements at indices index and i
          int t = arr[index];
          arr[index] = arr[i];
          arr[i] = t;

          //Recurse on the sub array arr[index+1...end]
          permuteHelper(arr, index+1);

          //Swap the elements back
          t = arr[index];
          arr[index] = arr[i];
          arr[i] = t;
      }
  }
  public static void main(String[] args){
    permute(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
  }
}
