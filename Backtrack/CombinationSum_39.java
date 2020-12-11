package Backtrack;

import java.util.*;

/**
 * 定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以无限制重复被选取。
 *所有数字（包括 target）都是正整数。
 *解集不能包含重复的组合。
 * 构建二叉树，左边表示选择该结点选=，右边表示不选
 */
public class CombinationSum_39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new LinkedList<>();
        List<Integer> rep=new LinkedList<>();

        combinationSumHelper(candidates,target,rep,res,0);

        return res;
    }

    private void combinationSumHelper(int[] candidates, int target, List<Integer> rep, List<List<Integer>> res,int index) {
        if(target!=0&&index==candidates.length){return;}
        if(target==0){
            res.add(new LinkedList<>(rep));
            return;
        }
        //不选择当前数字
        combinationSumHelper(candidates,target,rep,res,index+1);
        if(target-candidates[index]>=0){
            rep.add(candidates[index]);
            //index保持原样是因为数组数字可重复取
            combinationSumHelper(candidates, target-candidates[index], rep, res, index);
            rep.remove(rep.size()-1);
        }

    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        List<List<Integer>> lists = new CombinationSum_39().combinationSum(candidates, 7);
        System.out.println();
    }
}
