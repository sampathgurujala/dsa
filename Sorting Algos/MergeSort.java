import java.util.*;

public class MergeSort
{
    public static void mergeSort(int[] arr, int low,int high)
    {
        if(low >= high)
        {
          return;
        }

        int mid = low + (high-low)/2;
        mergeSort(arr,low,mid);
        mergeSort(arr,mid+1,high);
        merge(arr, low, mid, high);
    }
    public static void merge(int[] arr,int low , int mid, int high)
    {
        int[] arrc = new int[high-low+1];
        int k=0, i=low,j=mid+1;
        while(i<=mid && j<=high)
        {
            if(arr[i]<arr[j])
                arrc[k++]= arr[i++];
            else
               arrc[k++]=arr[j++];
        }
        //Here, either of the arrays is filled into arrc completely
        // So, we should fill remaining elements of non-empty array
        while(i<=mid)
        {
            arrc[k++]=arr[i++];
        }
        while(j<=high)
        {
            arrc[k++]=arr[j++];
        }

        for(int x=0; x<high-low+1;x++)
        {
            arr[low+x]= arrc[x];
        }
     return;
    }

    public static void main(String[] args)
    {
        int[] arr= new int[]{11,2,6,5,8,20,10};
        mergeSort(arr,0,arr.length-1);

        for(int ele: arr)
        {
            System.out.print(ele+" ");
        }
    }
 }
 /* Time Complexity: T(n) = 2T(n/2) + O(n) => O(n log n)
 Best Case - O(n log n)
 Average Case - O(n log n)
 Worst Case - O(n log n)

	•	Divide Step: Splits the array into two halves → takes O(log n) levels of recursion.
        •	Merge Step: Merging two sorted halves takes O(n) at each level.
        •	So, total work: O(n) work per level × O(log n) levels = O(n log n).
Space Complexity: O(n) extra space for temporary arrays. */