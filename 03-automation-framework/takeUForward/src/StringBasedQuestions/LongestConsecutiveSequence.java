import java.util.*;

public class LongestConsecutiveSequence {


    public static void main(String[] args){
        int[] arr={100,102,100,101,101,103,104,105,4,3,2,3,2,1,1,1,2,5};
        //System.out.println(findLongest(arr));
        System.out.println(findLongestConsecutiveSequence(arr));



    }
    //Optimal Solution


    public static int findLongestConsecutiveSequence(int[] arr){
       Set<Integer> set=new HashSet<>();
       int currentLongest=1;
       int longest=0;

       for(int i=0;i<arr.length;i++){
           set.add(arr[i]);

       }
        Iterator<Integer> it= set.iterator();
       while(it.hasNext()){
           int element=it.next();

           if (set.contains(element - 1)) {

               continue;
           }
           else{
               int i=1;
               while (set.contains(element + i)) {
                   currentLongest=currentLongest+1;
                   i++;

               }
           }
           if(currentLongest>longest){
               longest=currentLongest;
               return longest;
           }


       }
       return 1;
    }

    //Better Solution
    public static int findLongest(int[] arr){
        //[1,1,1,2,3,4,100,100,101,101,102]
        Arrays.sort(arr);

        int longest=1;
        int currentLongest=0;
        int previousSmallerElement=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i]-1==previousSmallerElement){
                currentLongest=currentLongest+1;
                previousSmallerElement=arr[i];

            }
            else if(arr[i]>previousSmallerElement+1){
                currentLongest=1;
                previousSmallerElement=arr[i];
            }

            if(currentLongest>longest){
                longest=currentLongest;
            }
        }
        return longest;

    }
}
