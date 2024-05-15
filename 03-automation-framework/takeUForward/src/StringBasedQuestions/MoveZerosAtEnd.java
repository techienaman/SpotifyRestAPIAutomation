public class MoveZerosAtEnd {

    public static void main(String[] args){
        int[] arr={1,0,2,3,2,0,0,4,5,1};
        int n=arr.length;
        //moveZerosAtTheEnd(arr,n);
        moveZeros(arr,arr.length);

    }

    public static void moveZeros(int[] arr,int n){
        int i;
        for(i=0;i<arr.length;i++){
            if(arr[i]==0){
                break;
            }
        }
        for(int j=i+1;j<arr.length;j++){
            if(arr[j]!=0){
                swap(arr,i,j);
                i++;
            }
        }
        for(int k=0;k<arr.length;k++){
            System.out.print(arr[k]+" ");
        }
    }


    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void moveZerosAtTheEnd(int[] arr,int n){
        int i=0;
        int j=arr.length-1;

        while(i<j) {
            if (arr[i] == 0) {
                swap(arr, i, j);
                j--;
            }
            i++;

        }

        for(int m=0;m<arr.length;m++){
            System.out.print(arr[m]+" ");
            }
        }

    }

