package Array;

import java.util.*;

public class K_diffPairsinanArray_532 {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer,Integer> pair=new HashMap<>();
        Map<Integer,Integer> res=new HashMap<>();
        int count=0;
        for(int n:nums){
            int m=n+k;
            if(!pair.containsKey(n)){
                pair.put(m,n);
            }else{
                res.put(m,n);
                pair.put(m,n);
            }
        }
        return res.size();
    }

    public int findPairs1(int[] nums, int k) {
        if(k<0 || nums.length<2)
            return 0;

        Arrays.sort(nums);
        int i=0,j=1,result=0;
        while(i<nums.length && j<nums.length)
        {
            if(nums[j]-nums[i]==k)
            {
                result++;
                i++;j++;
                while(j<nums.length && nums[j] == nums[j-1])
                    j++;
            } else if(nums[j]-nums[i]>k){
                i++;
                if(i==j)
                    j++;
            } else
                j++;
        }

        return result;
    }
}
