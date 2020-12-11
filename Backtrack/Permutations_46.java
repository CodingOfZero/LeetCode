package Backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 */
public class Permutations_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new LinkedList<>();
        if(nums==null||nums.length==0){
            return res;
        }
        List<Integer> temp=new LinkedList<>();
        boolean[] visited=new boolean[nums.length];
        Arrays.fill(visited,false);
        permuteHelper(nums,res,temp,visited,0);
        return res;
    }
    private void permuteHelper(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] visited, int idx){
        if(idx==nums.length){
            res.add(new LinkedList<>(temp));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(!visited[i]){
                visited[i]=true;
                temp.add(nums[i]);
                permuteHelper(nums,res, temp,visited,idx+1);
                temp.remove(temp.size()-1);
                visited[i]=false;
            }
        }
    }
}
