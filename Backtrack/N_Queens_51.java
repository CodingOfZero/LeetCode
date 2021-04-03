package Backtrack;

import java.util.*;

/**
 * N皇后问题
 */
public class N_Queens_51 {
    /**
     * 判断两个点不能在同一行，同一列，同一条斜线上
     * 因为这里每行只能放置一个皇后，而所有行中皇后的列号正好构成一个 1 到 n 的排列，
     * 所以我们可以把问题转化为一个排列枚举，规模是 n!。
     * 声明一个列数组，它的下标对应行标
     * 判断是否在同一条斜线上
     *      1.从左上到右下，规律 行标与列标的差相同
     *      2.从右上到左下，规律 行标和列标的和相同
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        int[] queen=new int[n];
        Arrays.fill(queen,-1);
        List<List<String>> res=new LinkedList<>();
        //存放已放置皇后的列
        Set<Integer> col=new HashSet<>();
        //左上到右下斜线
        Set<Integer> dig1=new HashSet<>();
        Set<Integer> dig2=new HashSet<>();
        solution(res,queen,n,0,col,dig1,dig2);
        return res;
    }

    private void solution(List<List<String>> res, int[] queen, int n,int row, Set<Integer> col, Set<Integer> dig1, Set<Integer> dig2) {
        if(row==queen.length){
            res.add(printSolution(queen,n));
            return;
        }
        for(int i=0;i<n;i++){
            if(col.contains(i)){
                continue;
            }
            int d1=row-i;
            if(dig1.contains(d1)){
                continue;
            }
            int d2=row+i;
            if(dig2.contains(d2)){
                continue;
            }
            queen[row]=i;
            col.add(i);
            dig1.add(d1);
            dig2.add(d2);
            solution(res,queen,n,row+1,col,dig1,dig2);
            queen[row]=-1;
            col.remove(i);
            dig1.remove(d1);
            dig2.remove(d2);
        }
    }

    private List<String> printSolution(int[] queen,int n){
        List<String> res=new LinkedList<>();
        for(int i=0;i<n;i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<n;j++){
                if(j==queen[i]){
                    sb.append("Q");
                    continue;
                }
                sb.append(".");
            }
            res.add(sb.toString());
        }
        return res;
    }
}
