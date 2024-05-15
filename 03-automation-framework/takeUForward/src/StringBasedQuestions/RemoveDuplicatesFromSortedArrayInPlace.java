import org.w3c.dom.ls.LSOutput;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatesFromSortedArrayInPlace {

    public static void main(String[] args){
        int[] arr={1,1,2,2,2,3,3};
        removeDuplicatesFromSortedArray(arr,7);

    }

    public static void removeDuplicatesFromSortedArray(int[] arr,int n){
        Set<Integer> set= new LinkedHashSet<>();
        for(int i=0;i<n;i++){
            set.add(arr[i]);
        }
        System.out.println(set);
    }

}
