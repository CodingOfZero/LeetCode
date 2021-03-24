package Array;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 下标为(i,j,k) 其中nums[i]<nums[k]<nums[j]
 * 判断是否有这样的子序列
 *
 */
public class Pattern132_456 {
    /**
     * 枚举j
     * 在j的左侧维护一个最小值，右侧通过有序集合找到大于最小值且小于j下标的值
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if(nums==null||nums.length<3) return false;
        int leftMin=nums[0];
        int len=nums.length;
        //将右侧加入有序集合
        //<nums[j],freq>
        TreeMap<Integer,Integer> rightAll=new TreeMap<>();
        for(int j=2;j<len;j++){
            rightAll.put(nums[j],rightAll.getOrDefault(nums[j],0)+1 );
        }
        for(int j=1;j<len-1;j++){
            if(leftMin<nums[j]){
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if(next!=null&&next<nums[j]){
                    return true;
                }
            }
            leftMin=Math.min(leftMin,nums[j]);
            //将j+1从有序集合中去除
            rightAll.put(nums[j+1],rightAll.get(nums[j+1])-1 );
            if(rightAll.get(nums[j+1])==0){
                rightAll.remove(nums[j+1]);
            }
        }
        return false;
    }
}
