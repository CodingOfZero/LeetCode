package BinarySearch;

/**
 * 制作 m 束花所需的最少天数
 * 辅助函数用于判断在给定的天数内能否制作出指定数量的花束
 * 当 days 很小的时候，辅助函数总是返回 false，因为天数太少不能收齐 m 个花束；
 * 当 days 很大的时候，辅助函数总是返回 true，如果给定序列可以制作出 m 个花束。
 * 在 days 慢慢变大的过程中，辅助函数的返回值会从 false 变成 true，
 * 所以我们可以认为这个辅助函数是关于 days 递增的，于是可以通过二分查找得到最少天数
 *
 */
public class MinimumNumberofDaystoMakemBouquets_1482 {

    public int minDays(int[] bloomDay, int m, int k) {
        int len=bloomDay.length;
        if(m*k>len){
            return -1;
        }
        //当m*k小于或等于len，一定能制作m束花，[1,数组最大值]区间进行二分
        //当天数越小，值为false,越大,值为true，
        int low=1,high=1;
        for(int day:bloomDay){
            high=Math.max(high,day);
        }
        while(low<high){
            int mid=low+(high-low)/2;
            if(canMake(bloomDay,mid,m,k)){
                high=mid;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    //O(n)
    //长度为k且最大元素不超过day不重合的连续子数组的个数要大于或等于m
    private boolean canMake(int[] bloomDay,int day,int m,int k){
        int count=0;
        //标记是否连续
        int flower=0;
        for(int i=0;i<bloomDay.length;i++){
            if(bloomDay[i]<=day){
                flower++;
                if(flower==k){
                    count++;
                    flower=0;
                }
            }else{
                flower=0;
            }
        }
        return count>=m;
    }
}
