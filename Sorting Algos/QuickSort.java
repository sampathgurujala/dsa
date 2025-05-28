import java.util.*;
public class QuickSort {
    static int[] arr;
    public static int partition(int low,int high )
    {
        int pivot= arr[high];
        int l_ind=low;
        for(int j=low;j<=high;j++)
        {
            if(arr[j]<pivot)
            {
                int tmp = arr[j];
                arr[j]=arr[l_ind];
                arr[l_ind]=tmp;
                l_ind++;
            }
        }
        int tmp = arr[high];
        arr[high]=arr[l_ind];
        arr[l_ind]=tmp;
        return l_ind;

    }
    public static void quicksort(int low,int high)
    {
        if(low<high)
        {
            int  pivot = partition(low,high);
            quicksort(low,pivot-1);
            quicksort(pivot+1,high);
        }
    }

    public static void main(String args[]) {
        arr = new int[5];
        for(int i=0;i<5;i++)
        {
            arr[i]= new Random().nextInt(10);
            System.out.print(arr[i]+" ");
        }
        quicksort(0,4);
        System.out.println();
        for(int i=0;i<5;i++)
        {
            System.out.print(arr[i]+" ");
        }
        return;
    }
}

/* Time Complexity:
Choosing pivot has diff strategies . here, I have chosen last element.
    Best Case: (Ω(n log n)), Occurs when the pivot element divides the array into two equal halves.
    Average Case (θ(n log n)), On average, the pivot divides the array into two parts, but not necessarily equal.
    Worst Case: (O(n²)), Occurs when the smallest or largest element is always chosen as the pivot (e.g., sorted arrays).
Auxiliary Space: O(n), due to recursive call stack . But, here I have utilized class property variable, So, for this code its O(1). */