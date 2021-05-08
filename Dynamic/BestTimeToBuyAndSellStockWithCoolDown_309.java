package Dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 最佳股票卖出含冷冻期
 */
public class BestTimeToBuyAndSellStockWithCoolDown_309 {
    public int maxProfit(int[] prices) {
        return maxHelperByRemember(prices, 0,new HashMap<>());
        //return maxHelper(prices,0);
    }

    /**
     * 超时
     * @param prices 股票数组
     * @param start：买入的时机
     * @return 最大利润
     */
    private int maxHelper(int[] prices,int start){
        if(start>=prices.length-1){
            return 0;
        }
        int maxValue=0;
        //每一天都有可能成为购买股票日
        for(int j=start;j<prices.length-1;j++){
            int buy=prices[j];
            //之后的每一天都可能卖出股票
            for(int i=j+1;i<prices.length;i++){
                //第i天卖出
                int value=prices[i]-buy;
                //冷冻期隔一天再买
                int max=maxHelper(prices,i+2);
                maxValue=Math.max(maxValue,max+value);
            }
        }
        return maxValue;
    }

    /**
     * 超时
     * @param prices
     * @param start
     * @param cache
     * @return
     */
    private int maxHelperByRemember(int[] prices, int start, Map<Integer,Integer> cache){
        if(start>=prices.length-1){
            return 0;
        }
        if(cache.containsKey(start)){
            return cache.get(start);
        }
        int maxValue=0;
        //每一天都有可能成为购买股票日
        for(int j=start;j<prices.length-1;j++){
            int buy=prices[j];
            //之后的每一天都可能卖出股票
            for(int i=j+1;i<prices.length;i++){
                //第i天卖出
                int value=prices[i]-buy;
                //冷冻期隔一天再买
                int max=maxHelperByRemember(prices,i+2,cache);
                maxValue=Math.max(maxValue,max+value);
            }
        }
        cache.put(start,maxValue);
        return maxValue;
    }

    /**
     * 回溯+记忆，通过
     * @param prices
     * @param start
     * @param cache
     * @return
     */
    private int maxHelperByMemory(int[] prices, int start, Map<Integer,Integer> cache){
        if(start>=prices.length-1){
            return 0;
        }
        if(cache.containsKey(start)){
            return cache.get(start);
        }
        int maxValue=0;
        int buy=prices[start];
        //之后的每一天都可能卖出股票
        for(int i=start+1;i<prices.length;i++){
            //第i天卖出
            int value=prices[i]-buy;
            //冷冻期隔一天再买
            int max=maxHelperByMemory(prices,i+2,cache);
            maxValue=Math.max(maxValue,max+value);
            //使用最低价格购入，区间[start,i]
            buy=Math.min(buy,prices[i]);
        }

        cache.put(start,maxValue);
        return maxValue;
    }

    /**
     * 买入，是负收益，卖出是正收益
     * f[i] 表示第i天结束之后最大收益
     * f[i]有三种状态
     *      状态1：持有股票 ---> 1) i-1持有; 2)i-1处于状态3，i时买入;  f(i)=Math.max(f(i-1)(1),f(i-1)(3)-prices(i))
     *      状态2：i天之后处于冷冻期  1)i-1必须持有一只股票; f(i)=f(i-1)(0)+prices[i]
     *      状态3：不持有也不处于冷冻期 1)i-1位于状态2，2）i-1位于状态3; f(i)=Math.max(f(i-1)(3),f(i-1)(2))
     * @param prices 股票价格数组
     * @return 最大利润
     */
    public int maxProfitDP(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int len=prices.length;
        int[][] profit=new int[len][3];
        //初始化
        profit[0][0]=-prices[0];
        for(int i=1;i<len;i++){
            profit[i][0]=Math.max(profit[i-1][0],profit[i-1][2]-prices[i]);
            profit[i][1]=profit[i-1][0]+prices[i];
            profit[i][2]=Math.max(profit[i-1][2],profit[i-1][1]);
        }
        return Math.max(profit[len-1][1],profit[len-1][2]);
    }

    public int maxProfitDPLessSpace(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int len=prices.length;
        //初始化
        int own=-prices[0];
        int sell=0;
        int nothing=0;

        for(int i=1;i<len;i++){
            int preOwn=own,preSell=sell,preNothing=nothing;
            own=Math.max(preOwn,preNothing-prices[i]);
            sell=preOwn+prices[i];
            nothing=Math.max(preNothing,preSell);
        }
        return Math.max(sell,nothing);
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCoolDown_309 test = new BestTimeToBuyAndSellStockWithCoolDown_309();
        long start = System.currentTimeMillis();
        int i = test.maxProfit(new int[]{6,1,6,4,3,0,2});
        //int i = test.maxProfit(new int[]{2,1,4});
        long end = System.currentTimeMillis();
        System.out.println(i);
        System.out.println("time: "+(start-end)+"ms");

    }
}
