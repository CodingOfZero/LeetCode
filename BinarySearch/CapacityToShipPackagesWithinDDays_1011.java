package BinarySearch;

import java.util.Arrays;

/**
 * 在D天运送包裹的能力
 * 知识点：二分查找，  查找的数据是船的运载能力，由于不能拆分包裹，船的运载能力区间为[最重的包裹，总包裹之和]
 * 在此区间进行查找，找到最小运载能力（如果存在最小运载能力下D天可以运输完，那么当船大于或等于最小运载能力时，一定能，小于时，无法成功）
 * 需要判定最小运载能力，D天是否能够运输完
 */
public class CapacityToShipPackagesWithinDDays_1011 {
    public int shipWithinDays(int[] weights, int D) {
        int left= Arrays.stream(weights).max().getAsInt(),right=Arrays.stream(weights).sum();
        while (left<right){
            int mid=(left+right)/2;
            //need: day
            //cur: sumOfWeight
            int need=1,cur=0;
            for(int weight:weights){
                if(cur+weight>mid){
                    need++;
                    cur=0;
                }
                cur+=weight;
            }
            if(need<=D){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}
