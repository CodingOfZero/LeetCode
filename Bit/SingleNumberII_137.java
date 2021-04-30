package Bit;

import java.util.HashMap;
import java.util.Map;


public class SingleNumberII_137 {
    /**
     * 时间复杂度为O(n) 空间O(n)
     */
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int res=0;
        for(int key:map.keySet()){
            if(map.get(key)==1){
                res=key;
                break;
            }
        }
        return res;
    }

    /**
     * 时间O(nlogC) 其中C为数据范围 空间O(1)
     * @param nums
     * @return
     */
    public int singleNumberSecond(int[] nums) {
        //结果二进制形式上第i位上的值=所有num第i位上值的和对3取余的结果，因为其余数字出现3次
        int res=0;
        for(int i=0;i<32;i++){
            int total=0;
            for(int num:nums){
                total+=((num>>i)&1);
            }
            if(total%3!=0){
                res|=(1<<i);
            }
        }
        return res;
    }
}
