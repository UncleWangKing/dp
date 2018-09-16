package array;

public class LeetCode_122_BestTimetoBuyandSellStockII {
    public static void main(String[] args) {
        int list[] = {7,1,5,3,6,};
        System.out.println(maxProfit(list));
    }

    public static int maxProfit(int[] prices) {
        int index = 0;
        int sum = 0;
        boolean isBuying = false;
        while(index < prices.length - 1){
            //没买 并且 下一天价格更高 就买
            if(prices[index] < prices[index + 1] && ! isBuying) {
                sum -= prices[index];
                isBuying = true;
            }//买了 并且 下一天价格更低 就卖
            else if(prices[index] > prices[index + 1] && isBuying) {
                sum += prices[index];
                isBuying = false;
            }
            index++;
        }
        return isBuying ? sum + prices[prices.length - 1] : sum;
    }
}