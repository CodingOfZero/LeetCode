package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个背包容量以及若干个物体的重量和它们对应的价值，求背包中存放的最大价值
 * 在0-1背包问题中，我们不能将物体拆分，在该问题中可以进行拆分
 * 思路：每次选性价比高的，value/weight
 */
public class FractionalKnapsackProblem {
    static class ItemValue{
        Double cost;
        double wt, val, ind;

        // item value function
        public ItemValue(int wt, int val, int ind)
        {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = (double) val / (double) wt;
        }
    }
    public static double getMaxValue(int[] wt, int[] val, int capacity){
        int len=wt.length;
        ItemValue[] rate=new ItemValue[len];

        for(int i=0;i<len;i++){
            rate[i]=new ItemValue(wt[i],val[i],i);
        }
        //降序方式
        Arrays.sort(rate, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });
        double maxValue=0d;
        for(ItemValue i:rate){
            int curWt=(int)i.wt;
            int curVal=(int)i.val;

            if(capacity-curWt>=0){
                capacity-=curWt;
                maxValue+=curVal;
            }else{
                double fraction=(double)capacity/(double)curWt;
                maxValue+=fraction*curVal;
                break;
            }
        }
        return maxValue;
    }
    public static void main(String[] args)
    {
        int[] wt = { 10, 40, 20, 30 };
        int[] val = { 60, 40, 100, 120 };
        int capacity = 50;

        double maxValue = getMaxValue(wt, val, capacity);

        // Function call
        System.out.println("Maximum value we can obtain = "
                + maxValue);
    }
}
