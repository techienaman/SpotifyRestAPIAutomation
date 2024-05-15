//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class TwoSumProblem {
//
//    public static void main(String[] args){
//        int[] arr={2,6,5,8,11};
//        int target=14;
//        //System.out.println(ifPresentTwoSum(arr,14));
//        List<Integer> list=returnIndexOfTwoSum(arr,14);
//        System.out.println(list);
//    }
//
//    public static boolean ifPresentTwoSum(int[] arr,int target){
//        List<Integer> list=new ArrayList<>();
//        HashMap<Integer,Integer> hashMap=new HashMap<>();
//        for(int i=0;i<arr.length;i++){
//            if(hashMap.containsKey(target-arr[i])){
//                return true;
//            }
//            else{
//                hashMap.put(arr[i],i);
//
//            }
//        }
//        return false;
//    }
//
//    public static List<List<Integer>> returnIndexOfTwoSum(int[] arr,int target){
//        List<Integer> list=new ArrayList<>();
//        List<List<Integer>> finalList=new ArrayList<>();
//    HashMap<Integer,Integer> hashMap=new HashMap<>();
//    for(int i=0;i<arr.length;i++){
//        if(hashMap.containsKey(target-arr[i])){
//            list.add(hashMap.get(target-arr[i]));
//            list.add(i);
//            finalList.add(list);
//
//        }
//        else{
//            hashMap.put(arr[i],i);
//
//        }
//    }
//    return list;
//    }
//}
