package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 考虑到只能使用1到9之间的数字，并且找到每个数字应该是一组唯一的数字，找到加总为n的k个数字的所有可能组合。
 * 注意：
 * 所有数字均为正整数。
 * 解决方案集不得包含重复的组合
 * 思路：使用递归
 */
public class CombinationSumIII {
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum3(int k,int n){
        res=new ArrayList<List<Integer>>();
        findRes(1,0,n,k,new ArrayList<Integer>());
        return res;
    }

    private void findRes(int cur, int curSum, int targetSum, int targetSize, ArrayList<Integer> curSet) {
        if(cur>=10||curSum>=targetSum||curSet.size()>=targetSize){
            if(curSet.size()==targetSize&&targetSum==curSum)
                res.add(new ArrayList<Integer>(curSet));
        }else{
            //不包含当前数
            findRes(cur+1,curSum,targetSum,targetSize,curSet);
            //包含当前数
            curSet.add(cur);
            findRes(cur+1,curSum+cur,targetSum,targetSize,curSet);
            //回溯
            curSet.remove(curSet.size()-1);
        }
    }

    public static void main(String[] args) {
        CombinationSumIII c=new CombinationSumIII();
        c.combinationSum3(3,7);
    }
}
