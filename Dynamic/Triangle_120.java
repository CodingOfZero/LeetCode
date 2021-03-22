package Dynamic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Triangle_120 {
    /**
     * 压缩空间，与01背包压缩方式类似，空间复杂度为O(n)
     */
    public int minimumTotalMinimum(List<List<Integer>> triangle) {
        int rows=triangle.size();
        int[] dp=new int[rows];
        //初始化
        dp[0]=triangle.get(0).get(0);
        for(int i=1;i<rows;i++){
            //处理最后一列特殊情况
            dp[i]=dp[i-1]+triangle.get(i).get(i);
            for(int j=i-1;j>=1;j--){
                dp[j]=Math.min(dp[j],dp[j-1])+triangle.get(i).get(j);
            }
            //处理第一列特殊情况
            dp[0]=dp[0]+triangle.get(i).get(0);
        }
        int ans=Integer.MAX_VALUE;
        for(int j = 0; j< rows; j++){
            ans=Math.min(ans,dp[j]);
        }
        return ans;
    }
    /**
     * 时间，空间O(n*n)
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows=triangle.size();
        int[][] dp=new int[rows][rows];
        //初始化
        dp[0][0]=triangle.get(0).get(0);
        for(int i=1;i<rows;i++){
            //处理第一列特殊情况
            dp[i][0]=dp[i-1][0]+triangle.get(i).get(0);
            for(int j=1;j<i;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i).get(j);
            }
            //处理最后一列特殊情况
            dp[i][i]=dp[i-1][i-1]+triangle.get(i).get(i);
        }
        int ans=Integer.MAX_VALUE;
        for(int j = 0; j< rows; j++){
            ans=Math.min(ans,dp[rows-1][j]);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> t=new LinkedList<List<Integer>>();

        Integer[] i1=new Integer[]{-10};
//        Integer[] i2=new Integer[]{3,4};
//        Integer[] i3=new Integer[]{6,5,7};
//        Integer[] i4=new Integer[]{4,1,8,3};
        List<Integer> list1 = Arrays.asList(i1);
//        List<Integer> list2 = Arrays.asList(i2);
//        List<Integer> list3 = Arrays.asList(i3);
//        List<Integer> list4 = Arrays.asList(i4);
        t.add(list1);
//        t.add(list2);
//        t.add(list3);
//        t.add(list4);
        int i = new Triangle_120().minimumTotal(t);
        System.out.println(i);
    }
}
