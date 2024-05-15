import java.util.HashMap;
public class LongestSubArrayWithprefixprefixSumK {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        int k = 3;
        //System.out.println(findLongestSubArrayWithprefixSumK(arr,3));
        System.out.println(longestSubArrayForPositivesAndZeroesOnly(arr,3));

    }
    /*[1,2,3,1,1,1,1,4,2,3]
       ---x-----
       (prefix prefixSum-k)=left prefixSum    

    */

    public static int findMax(int y, int z) {
        if (y >= z) {
            return y;
        }
        return z;
    }

    public static int findLongestSubArrayWithprefixSumK(int[] arr, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum = prefixSum + arr[i];
            if(!hashMap.containsKey(prefixSum)){
                hashMap.put(prefixSum, i);
            }
            if (prefixSum == k) {
                maxLen = findMax(maxLen, i + 1);
            } else {
                int leftOverSum = prefixSum - k;
                if(hashMap.containsKey(leftOverSum)){

                    int digit = hashMap.get(leftOverSum);
                    maxLen = findMax(maxLen, (i - digit));
                }

            }

        }
        return maxLen;
    }



    public static int longestSubArrayForPositivesAndZeroesOnly(int[] arr,int k) {
        int len = 0;
        int left = 0;
        int right = 0;
        int sum = arr[0];

        while (right < arr.length) {
            while (left < right && sum > k) {
                sum = sum - arr[left];
                left = left + 1;
            }
            if (sum == k) {
               len= findMax(len, (right - left) + 1);
            }
            right++;
            if(right<arr.length){
                sum=sum+arr[right];
            }
        }
        return len;


    }


}
