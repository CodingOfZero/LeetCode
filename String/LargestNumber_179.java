package String;

import java.util.Arrays;

/**
 * 最大数
 * 自定义排序规则
 *
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数
 */
public class LargestNumber_179 {
    public String largestNumber(int[] nums) {
        int n=nums.length;
        String[] str=new String[n];

        for(int i=0;i<n;i++){
            str[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(str,(a,b)->{
            String s1=a+b;
            String s2=b+a;
            return s2.compareTo(s1);
        });

        StringBuilder sb=new StringBuilder();
        for(String s:str){
            sb.append(s);
        }
        //需要处理前导零的问题
        int len=sb.length();
        int k=0;
        //去除前导零，如果结果就是零，会保留一位
        while(k<len-1&&sb.charAt(k)=='0') k++;
        return sb.substring(k);
    }

    public static void main(String[] args) {
        LargestNumber_179 test = new LargestNumber_179();
        String s = test.largestNumber(new int[]{3, 30, 34, 5, 9});
        System.out.println(s);
    }
}
