package Dynamic;

public class PaintHouseIII_1473 {
    /**
     * 编码均从1开始
     * dp[i][j][k]：前i个房子且第i个房子颜色为j，街区为k的最小涂色总花费
     * 1.houses[i]!=0
     *      1.已涂色，不能覆盖，当j!=houses[i]时是无效的
     *      2.j==houses[i] 最小花费分为两种情况中的最小值
     *             1.f[i-1][p][k-1] p!=j  当p!=j时，会形成新的分区
     *             2.f[i-1][j][k]
     * 2.houses[i]==0
     *      1.没有上述无效状态
     *      2.min(f[i-1][p][k-1],f[i-1][j][k])+cost[i][j-1] p!=j
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] minCost=new int[m+1][n+1][target+1];
        //防止累加溢出变为负数
        int inf=Integer.MAX_VALUE/2;
        //初始化
        //街区不可能为0
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                minCost[i][j][0]=inf;
            }
        }

        for(int i=1;i<=m;i++){
            int colorOfHouse=houses[i-1];
            for(int j=1;j<=n;j++){
                for(int k=1;k<=target;k++){
                    //街区数大于房子数，属于无效状态
                    if(k>i){
                        minCost[i][j][k]=inf;
                        continue;
                    }
                    //第i间房间已上色
                    if(colorOfHouse!=0){
                        if(j==colorOfHouse){
                            int tmp=inf;
                            //先从所有第i间房形成新分区方案中选最优（即与上一间房子颜色不同）
                            for(int p=1;p<=n;p++){
                                if(p!=j){
                                    tmp=Math.min(tmp,minCost[i-1][p][k-1]);
                                }
                            }
                            //再结合第i间房子不形成新分区方案选最优
                            minCost[i][j][k]=Math.min(tmp,minCost[i-1][j][k]);
                        }else{
                            minCost[i][j][k]=inf;
                        }
                    }else{
                        int tmp=inf;
                        //先从所有第i间房形成新分区方案中选最优（即与上一间房子颜色不同）
                        for(int p=1;p<=n;p++){
                            if(p!=j){
                                tmp=Math.min(tmp,minCost[i-1][p][k-1]);
                            }
                        }
                        //再结合第i间房子不形成新分区方案选最优,同时将上色成本考虑进去
                        minCost[i][j][k]=Math.min(tmp,minCost[i-1][j][k])+cost[i-1][j-1];
                    }
                }
            }
        }
        //从考虑所有房间，并且形成分区数量为target的所有方案中找答案
        int ans=inf;
        for(int i=1;i<=n;i++){
            ans=Math.min(ans,minCost[m][i][target]);
        }
        return ans==inf?-1:ans;
    }
}
