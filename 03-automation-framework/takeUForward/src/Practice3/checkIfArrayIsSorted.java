public class checkIfArrayIsSorted {
    public static void main(String[] args) {
        int arr[] ={1,2,2,3,3,4};
        boolean issorted=checkSorting(arr,6);
        System.out.println(issorted);
    }


    public static boolean checkSorting2(int[] arr,int n){
        for(int i=1;i<n;i++){
            if(arr[i-1]<arr[i]){

            }
            else{
                return false;
            }
        }
        return true;
    }

    public static boolean checkSorting(int[] arr,int n){
        boolean status=false;
        for(int i=0;i<n-1;i++){
            if(arr[i+1]>=arr[i]){
                status=true;
                continue;
            }
            else{
                status=false;
                break;
            }

        }
        return status;
    }
}
