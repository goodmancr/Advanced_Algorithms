import java.util.*;
import java.util.Random;
public class SelectionSort {
    public static void main(String[] args) {
        //mark start time of method
        long start = System.currentTimeMillis();

        // generate list of random numbers of chosen list length
        Random rand = new Random();
        int[] arr1 = new int[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = rand.nextInt();
        }

        System.out.println("Length: " + arr1.length);
        //find lowest number in the array
        for (int i = 0; i < arr1.length - 1; i++)  
        {          
            int index = i;  
            for (int j = i + 1; j < arr1.length; j++){  
                if (arr1[j] < arr1[index]){  
                    index = j;// found lowest number
                }  
            }  
            // smallerNumber set to the lowest number
            int smallerNumber = arr1[index];   
            // lowest number is swapped with where we are in the array
            arr1[index] = arr1[i];  
            arr1[i] = smallerNumber; 
        }  
        System.out.println("Array after Selection Sort: "  + Arrays.toString(arr1));
        //mark end time
        long end = System.currentTimeMillis();
        //calculating runtime
        long run_time = end - start;
        System.out.println("Runtime: " + run_time + " milliseconds");
    }
}






