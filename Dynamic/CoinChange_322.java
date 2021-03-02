package Dynamic;

import java.util.Arrays;

/**
 * 兑换硬币，找出凑成总金额所需的最少的硬币个数
 */
public class CoinChange_322 {
    /**
     *
     * @param coins 硬币类别数组
     * @param amount 总金额
     * @return 最少硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        if(amount<1){
            return 0;
        }
        return coinChangeHelper(coins,amount,new int[amount]);
    }
    //F(S)=F(S-c)+1   S为金额，c为硬币面额，F为最小硬币个数，c不确定所以需要遍历找
    private int coinChangeHelper(int[] coins, int amount, int[] dp) {
        if(amount<0){
            return -1;
        }
        if(amount==0){
            return 0;
        }
        //amount-1代表amount处已经有了最少硬币个数
        if(dp[amount-1]!=0){
            return dp[amount-1];
        }
        int min=Integer.MAX_VALUE;
        for(int coin:coins){
            int res = coinChangeHelper(coins, amount - coin, dp);
            if(res>=0&&res<min){
                min=res+1;
            }
        }
        dp[amount-1]=(min==Integer.MAX_VALUE)?-1:min;
        return dp[amount-1];
    }

    //自底向上
    public int coinChangeDP(int[] coins, int amount) {
        int max=amount+1;
        int[] fun=new int[amount+1];
        Arrays.fill(fun,max);
        fun[0]=0;

        for(int i=1;i<=amount;i++){
            for(int coin:coins){
                if(coin<=i){
                    fun[i]=Math.min(fun[i],fun[i-coin]+1);
                }
            }
        }
        return fun[amount]>amount?-1:fun[amount];
    }
}
