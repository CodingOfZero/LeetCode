package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedinanArray_448 {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res=new ArrayList<>();
        Arrays.sort(nums);
        int k=1;
        for(int i=0;i<nums.length;){
            if(nums[i]==k){
                k++;
                i++;
            }else if(nums[i]>k){
                res.add(k);
                k++;
            }else{
                i++;
            }
        }
        if(k<=nums.length)
            for(int j=k;j<=nums.length;j++)
                res.add(j);
        return res;
    }

    public static List<Integer> findDisappearedNumbers1(int[] nums){
        int flag[]=new int[nums.length];
        int i;
        List<Integer> ans=new ArrayList<>();
        for(i=0;i<nums.length;i++)
        {
            flag[i]=0;
        }
        for(i=0;i<nums.length;i++)
        {
            flag[nums[i]-1]=1;
        }
        for(i=0;i<nums.length;i++)
        {
            if(flag[i]!=1)
            {
                ans.add(i+1);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums={4,3,2,7,8,2,3,1};
        List<Integer> li = FindAllNumbersDisappearedinanArray_448.findDisappearedNumbers(nums);
        System.out.println(li);
    }
}
