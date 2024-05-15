public class SecondLargest {
    public static void main(String[] args) {
        int[] arr={5,2,7,6,9,12,15,1};
        int slargest=secondLargest(arr,8);
        int ssmallest=secondSmallest(arr,8);
        System.out.println(slargest);
        System.out.println(ssmallest);
    }

    public static int secondSmallest(int[] arr,int n){
        int smallest=arr[0];
        int ssmallest=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(arr[i]<smallest){
                ssmallest=smallest;
                smallest=arr[i];
            }
            else if(arr[i]>smallest && arr[i]<ssmallest){
                ssmallest=arr[i];
            }
        }
        return ssmallest;
    }

    public static int secondLargest(int[] arr,int n){
        int largest=arr[0];
        int slargest=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            if(arr[i]>largest){
                slargest=largest;
                largest=arr[i];
            }
            else if(arr[i]<largest && arr[i]>slargest){
                slargest=arr[i];
            }
        }
        return slargest;
    }
}

