import java.util.ArrayList;
import java.util.List;

public class LeaderInArray {

    public static void main(String[] args){
        int[] arr={6,8};
        System.out.println(findLeaderInArray(arr));

    }

    public static List<Integer> findLeaderInArray(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int maxElement = arr[arr.length - 1];


        for(int i=arr.length-2;i>=0;i--){
            if(arr[i]>maxElement){
                maxElement=arr[i];
                list.add(arr[i]);
            }
            else{
                continue;

            }

        }
        list.add(arr[arr.length-1]);
        return list;
    }

}
