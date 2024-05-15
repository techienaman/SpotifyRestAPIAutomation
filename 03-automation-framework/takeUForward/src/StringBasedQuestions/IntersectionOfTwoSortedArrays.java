import java.util.LinkedList;
import java.util.List;

public class IntersectionOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] arr1={2,5,7,8,9,10};
        int[] arr2={1,2,3,4,5,6,7};
        intersectionOfTwoSortedArrays(arr1,arr2);

    }

    public static void intersectionOfTwoSortedArrays(int[] arr1,int[] arr2){
        int i=0;
        int j=0;
        List<Integer> arr3=new LinkedList<Integer>();
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<arr2[j]){
                i++;
            }
            else if(arr1[i]>arr2[j]){
                j++;
            }
            if(arr1[i]==arr2[j]){
                arr3.add(arr1[i]);
                i++;
                j++;

            }
        }
        System.out.println(arr3);

    }
}
