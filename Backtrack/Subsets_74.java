package Backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 * 不能重复，构建一棵二叉树，左边表示选择该数字，右边表示不选
 */
public class Subsets_74 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list=new LinkedList<>();
        if(nums==null||nums.length==0){return list;}
        List<Integer> temp=new LinkedList<>();
        subsetsHelper(nums,list,temp,0);
        return  list;
    }

    private void subsetsHelper(int[] nums, List<List<Integer>> list, List<Integer> temp, int idx) {
        if(idx==nums.length){
            list.add(new LinkedList<>(temp));
            return;
        }
        subsetsHelper(nums,list,temp,idx+1);
        temp.add(nums[idx]);
        subsetsHelper(nums,list,temp,idx+1);
        temp.remove(temp.size()-1);
    }

}
