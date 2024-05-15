public class BestTimeToBuyAndSellStocks {

    public static void main(String[] args){

        int[] arr={7,1,4,6,8,3};
        System.out.println(bestTimeToBuyAndSellStocks(arr));
    }


    public static int bestTimeToBuyAndSellStocks(int[] arr){
        int minimalElement=arr[0];
        int maximumProfit=Integer.MIN_VALUE;
        int currentProfit;
        for(int i=1;i<arr.length;i++){
            currentProfit=arr[i]-minimalElement;
            if(currentProfit>maximumProfit){
                maximumProfit=currentProfit;
            }
            if(arr[i]<minimalElement){
                minimalElement=arr[i];
            }



        }
        return maximumProfit;
    }
}
