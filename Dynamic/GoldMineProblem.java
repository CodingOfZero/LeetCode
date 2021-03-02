package Dynamic;

import java.util.Arrays;

/**
 * 给定一个n * m尺寸的金矿。该矿场中的每个字段都包含一个正整数，该整数是黄金的吨数。
 * 最初，矿工位于第一列，但可以位于任何行。他只能从给定单元移动（右->，右向上/，右向下\），
 * 矿工可以向右或向右斜向右或向右对角向下移至该单元。找出他可以收集的最大数量的黄金。
 * example
 * Input : mat[][] = {{1, 3, 3},
 *                    {2, 1, 4},
 *                   {0, 6, 4}};
 * Output : 12
 * {(1,0)->(2,1)->(2,2)}
 *
 * 注意：如果我们位于第一行或最后一列，不能向右上方移动，如果位于最后一行或最后一列，不能向右下方移动
 * 思路：创建与该二维数据对应二维数组goldTable，从后往前，计算每个位置所能取最大值，最后找到所有行的第一列中最大值
 * 即为最大价值
 */
public class GoldMineProblem {
    /**
     * @param gold  金矿
     * @param m 行
     * @param n 列
     * @return 所能取到金矿最大价值
     */
    public static int getMaxGold(int[][] gold,int m,int n){
        //goldTable存储从该位置出发所能得到的最大价值
        int[][] goldTable=new int[m][n];
        for(int[] golds:goldTable) {
            Arrays.fill(golds,0);
        }
        //从后往前，计算每个位置所能取最大值,right rightUp rightDown取最大的
        for(int col=n-1;col>=0;col--){
            for(int row=0;row<m;row++){
                int right = (col==n-1)? 0 :goldTable[row][col+1];
                int rightUp = (row==0||col==n-1)?0:goldTable[row-1][col+1];
                int rightDown=(row==m-1||col==n-1)?0:goldTable[row+1][col+1];

                goldTable[row][col]=gold[row][col]+Math.max(right,Math.max(rightUp,rightDown));
            }
        }
        //找到所有行的第一列中最大值
        int res=goldTable[0][0];
        for(int i=1;i<m;i++){
            res=Math.max(res,goldTable[i][0]);
        }
        return res;
    }

    public static void main(String[] args) {
        int gold[][]= { {1, 3, 1, 5},
                {2, 2, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2} };

        int m = 4, n = 4;

        System.out.print(getMaxGold(gold, m, n));
    }
}
