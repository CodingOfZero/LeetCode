package Dynamic;

/**
 * 最小路径和
 * 从左上角道右下角，每次只能向下或向右移动一步
 */
public class MinimumPathSum_64 {

    public int minPathSumDPTwo(int[][] grid){
        if(grid==null||grid.length==0) return 0;
        int row=grid.length,col=grid[0].length;
        int[][] minValue=new int[row][col];
        minValue[0][0]=grid[0][0];
        //填满第一行
        for(int j=1;j<col;j++){
            minValue[0][j]=minValue[0][j-1]+grid[0][j];
        }
        for(int i=1;i<row;i++){
            minValue[i][0]=minValue[i-1][0]+grid[i][0];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                minValue[i][j]=Math.min(minValue[i-1][j],minValue[i][j-1])+grid[i][j];
            }
        }
        return minValue[row-1][col-1];
    }

    public int minPathSumDP(int[][] grid) {
        if(grid==null||grid.length==0) return 0;
        int row=grid.length,col=grid[0].length;
        //grid存储左上角到该行该列最小和
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==0&&j==0) continue;
                if(j-1>=0&&i-1>=0){
                    grid[i][j]+=Math.min(grid[i][j-1],grid[i-1][j]);
                }else if(j-1>=0){
                    grid[i][j]+=grid[i][j-1];
                }else {
                    grid[i][j]+=grid[i-1][j];
                }
            }
        }
        return grid[row-1][col-1];
    }

    public int minPathSum(int[][] grid) {
        if(grid==null||grid.length==0) return 0;
        int[] min=new int[]{Integer.MAX_VALUE};
        minPathSumHelper(grid,0,0,0,grid.length,grid[0].length,min);
        return min[0];
    }

    private void minPathSumHelper(int[][] grid,int x,int y,int sum,int row,int col,int[] min){
        sum+=grid[x][y];
        if(x==row-1&&y==col-1){
            min[0]=Math.min(min[0],sum);
            return;
        }
        if(y+1<col&&sum<min[0]){
            minPathSumHelper(grid,x,y+1,sum,row,col,min);
        }
        if(x+1<row&&sum<min[0]){
            minPathSumHelper(grid,x+1,y,sum,row,col,min);
        }
    }

    public static void main(String[] args) {
        MinimumPathSum_64 sum64 = new MinimumPathSum_64();
        int i = sum64.minPathSumDP(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
        System.out.println(i);
        int sum = sum64.minPathSumDP(new int[][]{{1, 2, 3}, {4, 5, 6}});
        System.out.println(sum);
    }
}
