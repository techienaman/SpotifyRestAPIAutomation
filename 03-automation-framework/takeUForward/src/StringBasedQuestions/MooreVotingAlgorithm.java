public class MooreVotingAlgorithm {

    public static void main(String[] args){
        int[] arr={7,7,5,7,5,1,5,7,5,5,7,7,5,5,5,5};
        System.out.println(findMajorityElements(arr));

    }

    /*
    We could have solve the problem with HashMap also but In HashMap we may end up in more Space complexity so to avoid this we are using
    Moore Voting Algorithm

     */
    public static int findMajorityElements(int[] arr){
        int element=Integer.MAX_VALUE;
        int count=0;
        int finalCount=0;

        for(int i=0;i<arr.length;i++){
            if(count==0){
                element=arr[i];
            }
            else if(element==arr[i]){
                count=count+1;
            }
            else if(element!=arr[i]){
                count=count-1;
            }

        }

        for(int y=0;y<arr.length;y++){
            if(arr[y]==element){
                finalCount=finalCount+1;
            }

        }
        if(finalCount>arr.length/2){
            return element;
        }
        return -1;
    }
}
