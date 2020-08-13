package Array;

import java.util.HashMap;

/**
 * 求连续子数组和为k的个数，不是排序数组，有正有负
 */
public class SubarraySumEqualsK_560 {
    //利用哈希表
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int count=0;
        int sum=0;
        map.put(0,1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else
                map.put(sum, 1);
        }
        return count;
    }

    public int subarraySum_1(int[] nums, int k) {
        int count=0;
        for(int i=0;i<nums.length;i++){
            int temp=nums[i];
            if(temp==k) count++;
            for(int j=i+1;j<nums.length;j++){
                temp+=nums[j];
                if(temp==k) count++;
            }
        }
        return count;
    }
}
