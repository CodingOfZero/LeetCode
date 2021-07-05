package HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 求连续子数组满足奇数的和为k的子数组个数
 */
public class CountNumberofNiceSubarrays_1248 {
    public static int numberOfSubarrays(int[] nums, int k) {
        int n=nums.length;
        int[] odd=new int[n+1];
        for(int i=0;i<n;i++){
            //odd[i+1]=odd[i]+(nums[i]%2==0?0:1);
            odd[i+1]=odd[i]+(nums[i]&1);
        }
        int cnt=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:odd){
            if(map.containsKey(num-k)){
                cnt+=map.get(num-k);
            }
            map.put(num,map.getOrDefault(num,0)+1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int i = numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3);
        System.out.println(i);

    }
}
