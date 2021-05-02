package Dynamic;

import java.util.Arrays;

/**
 * 完全平方数
 * 给定n,求最少由完全平方数组成的个数。完全平放数为1，4，9，16等
 */
public class PerfectSquares_279 {

    public int numSquares(int n) {
        int[] res=new int[n+1];
        Arrays.fill(res,Integer.MAX_VALUE);
        res[0]=0;

        int maxSquareIndex=(int)Math.sqrt(n)+1;
        int[] squareNums=new int[maxSquareIndex];
        for(int i=1;i<maxSquareIndex;i++){
            squareNums[i]=i*i;
        }

        //res[i]=min(res[i-k])+1 k属于平方数
        for(int i=1;i<=n;i++){
            for (int j=1;j<maxSquareIndex;j++){
                if(i<squareNums[j]){
                    break;
                }
                res[i]=Math.min(res[i],res[i-squareNums[j]]+1);
            }
        }

        return res[n];
    }

    /**
     * 贪心+DFS
     */
//    private int res;
//    public int numSquares(int n) {
//        int end=1;
//        while((end+1)*(end+1)<=n){
//            end++;
//        }
//        int sum=0;
//        res=Integer.MAX_VALUE;
//        dfs(end,sum,n,0);
//        return res;
//    }
//    private void dfs(int end,int sum,int target,int level){
//        if(level>=res){
//            return;
//        }
//        if(sum==target){
//            res=level;
//            return;
//        }
//        if(sum>target){
//            return;
//        }
//        for(int i=end;i>=1;i--){
//            dfs(end,sum+i*i,target,level+1);
//        }
//    }
}
