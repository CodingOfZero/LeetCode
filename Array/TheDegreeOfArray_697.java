package Array;

import java.util.*;

/**
 *
 */
public class TheDegreeOfArray_697 {
    public static int findShortestSubArray(int[] nums) {
        Map<Integer,int[]> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.get(nums[i])[0]++;
                //更新最后出现位置
                map.get(nums[i])[2]=i;
            }else{
                map.put(nums[i],new int[]{1,i,i} );
            }
        }
        int maxNum=0,minLen=0;
        for(Integer key:map.keySet()){
            int[] arr = map.get(key);
            if(maxNum<arr[0]){
                maxNum=arr[0];
                minLen=arr[2]-arr[1]+1;
            }else if(maxNum==arr[0]){
                if(minLen>arr[2]-arr[1]+1){
                    minLen=arr[2]-arr[1]+1;
                }
            }
        }
        return minLen;
    }

    public static int findShortestSubArrayBF(int[] nums) {
        int[] store=new int[50000];
        for(int num : nums){
            store[num]++;
        }
        int maxNum=0;
        for(int item:store){
            maxNum=Math.max(maxNum,item);
        }
        List<Integer> index=new LinkedList<>();
        for(int i=0;i<store.length;i++){
            if(store[i]==maxNum) index.add(i);
        }
        int dis=50001;
        if(maxNum==1) return 1;
        for(int item:index){
            int begin=0,end=nums.length-1;
            while (nums[begin]!=item){begin++;}
            while (nums[end]!=item){end--;}
            dis=Math.min(dis,end-begin+1);
        }
        return dis;
    }

    public static void main(String[] args) {
        findShortestSubArray(new int[]{1,2,2,3,1,4,2});
    }
}
