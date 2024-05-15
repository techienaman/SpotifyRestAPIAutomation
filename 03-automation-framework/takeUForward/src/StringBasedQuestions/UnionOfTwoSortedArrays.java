import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnionOfTwoSortedArrays {
    public static void main(String[] args){
        int[] arr1={2,5,7,8,9,10};
        int[] arr2={1,2,3,4,5,6,7};
        unionOfTwoSortedArrays(arr1,arr2);
    }

    public static void unionOfTwoSortedArrays(int[] arr1,int[] arr2){
        int i=0;
        int j=0;
        List<Integer> arr3=new LinkedList<>();

        while(i< arr1.length && j<arr2.length){
            if(arr1[i]<arr2[j]){
                arr3.add(arr1[i]);
                i++;
            }
            else if(arr1[i]>arr2[j]){
                arr3.add(arr2[j]);
                j++;
            }
            else if(arr1[i]==arr2[j]){
                arr3.add(arr1[i]);
                i++;
                j++;
            }

        }

        if(i< arr1.length) {
            for (int m = i+1; m < arr1.length; m++) {
                arr3.add(arr1[m]);
            }
        }
            if(j< arr2.length){
                for(int n=i+1;n<arr1.length;n++){
                    arr3.add(arr2[n]);
                }
        }
        System.out.println(arr3);


    }
}
