package Math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列，给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class PermutationsII_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums==null||nums.length==0) return new LinkedList<>();
        List<List<Integer>> res=new LinkedList<>();
        boolean[] visited=new boolean[nums.length];
        Arrays.sort(nums);
        perHelper(res,new LinkedList<Integer>(),nums,visited);
        return res;
    }

    private void perHelper(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] visited) {
        if(tmp.size()==nums.length){
            res.add(new LinkedList<>(tmp));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(!visited[i]){
                if(i>0&&nums[i]==nums[i-1]&&!visited[i-1]){
                    continue;
                }
                tmp.add(nums[i]);
                visited[i]=true;
                perHelper(res, tmp, nums, visited);
                visited[i]=false;
                tmp.remove(tmp.size()-1);
            }
        }
    }

}
