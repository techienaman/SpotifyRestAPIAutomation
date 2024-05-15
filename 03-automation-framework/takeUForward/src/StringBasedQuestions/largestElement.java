public class largestElement {

    public static void main(String[] args){
        int[] arr={7,9,2,67,65};
        System.out.println(largestElement(arr,5));

    }

    public static int largestElement(int[]arr,int n){
        int largest=arr[0];
        for(int i=0;i<n;i++){
            if(arr[i]>largest){
                largest=arr[i];
            }

        }
        return largest;
    }

}
