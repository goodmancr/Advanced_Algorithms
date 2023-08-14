import java.util.*;
import java.util.Random;
public class BubbleSort {
    public static void main(String[] args) {
        // mark start time of method
        long start = System.currentTimeMillis();

        // generate list of random numbers
        Random rand = new Random();
        int[] arr1 = new int[100000];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = rand.nextInt();
        }
        
        System.out.println("Length: " + arr1.length);

        // for each number in arr1
        for (int i = 0; i < arr1.length - 1; i++) {
            // for each index i, look to number before it
            for (int j = 0; j < arr1.length - i - 1; j++) {
                if (arr1[j] > arr1[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr1[j];
                    arr1[j] = arr1[j + 1];
                    arr1[j + 1] = temp;
                }
            }
        }
        //mark end time 
        long end = System.currentTimeMillis();
        //calculate full runtime
        long run_time = end - start;
        System.out.println("Array after Bubble Sort: "  + Arrays.toString(arr1));
        System.out.println("Bubble Sort Runtime: " + run_time);
    }
}


