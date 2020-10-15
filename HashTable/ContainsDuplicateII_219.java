package HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整型数组和一个整数，求数组中是否有两个不同的下标i和j，使得nums[i]=nums[j]，并且i和j之间的绝对差最多为k。
 */
public class ContainsDuplicateII_219 {
    /**
     * 使用HashMap来解决该问题
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //map存储值与它的下标
        Map<Integer,Integer> map=new HashMap<>();
        //遍历整个数组
        for(int i=0;i<nums.length;i++){
            //如果map里面包含当前的值，判断存储的索引与现在下标两个的绝对差是否小于k
            if(map.containsKey(nums[i])){
                if((i-map.get(nums[i]))<=k){
                    return true;
                }
            }
            //将当前的值与对应的下标放入到map中
            map.put(nums[i],i);
        }
        return false;
    }
}
