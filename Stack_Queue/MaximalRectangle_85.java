package Stack_Queue;

import java.util.Arrays;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，
 * 找出只包含 1 的最大矩形，并返回其面积。
 *
 * 先对每一行的每个值求出其对应的高度，即连续1的长度，将每行看做一个柱状图
 * 本质上对每一行执行和84题的算法
 */
public class MaximalRectangle_85 {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null||matrix.length==0){
            return 0;
        }
        int row=matrix.length;
        int col=matrix[0].length;
        int[] height=new int[col];
        int res=0;
        for(int i=0;i<row;i++){
            //求每行的高度，x轴向右，y轴向上
            for(int j=0;j<col;j++){
                if(matrix[i][j]=='0'){
                    height[j]=0;
                }else{
                    height[j]=height[j]+1;
                }
            }
            //求解该行最大矩形
            res=Math.max(res,largestRectangle(height));
        }
        return res;
    }

    private int largestRectangle(int[] height) {
        int len=height.length;
        int[] stack=new int[len];
        int sp=-1;
        //某个点左边第一个小于该点的索引
        int[] left=new int[len];
        //右边
        int[] right=new int[len];
        //那些最后还留在栈中的值，表示它们的最右边最小的是len，len充当哨兵
        Arrays.fill(right,len);
        //stack中存放的索引严格递增，对应的数组中的值也严格递增
        //单调递增栈
        for(int i=0;i<len;i++){
            while (sp!=-1&&height[i]<height[stack[sp]]){
                right[stack[sp]]=i;
                sp--;
            }
            left[i]=sp==-1?-1:stack[sp];
            stack[++sp]=i;
        }

        int res=0;
        for(int i=0;i<len;i++){
            res=Math.max(res,(right[i]-left[i]-1)*height[i]);
        }
        return res;
    }
}
