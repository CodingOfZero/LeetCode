package Backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 假定给定数组不包含负数且唯一
 * 求和为K的子集
 */
public class SubsetSum {


    public static void subsetSumHelper(int[] set, int l, int r, int k, LinkedList<Integer> list){
        if(k==0){
            list.forEach(e-> System.out.printf("%-2d ",e));
            System.out.println();
        }
        for(int i=l;i<r;i++){
            if(isSafe(set,i,k)){
                list.add(set[i]);
                subsetSumHelper(set,i+1,r,k-set[i],list);
                list.removeLast();
            }
        }
    }

    private static boolean isSafe(int[] set,int i,int k) {
        return (k-set[i])>=0;
    }

    public static void findSubsetSum(int[] set,int k){
        LinkedList<Integer> list=new LinkedList<>();
        subsetSumHelper(set, 0, set.length, k, list);
    }

    public static void main(String[] args) {
        int[] weights = {10, 7, 5, 18, 12, 20, 15};
        Arrays.sort(weights);
        findSubsetSum(weights,35);
    }
}
