package Backtrack;

/**
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 *
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 *
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 */
public class PathwithMaximumGold_1219 {
    private int maxValue=0;
    private int[] xMove={0,1,-1,0};
    private int[] yMove={1,0,0,-1};
    public int getMaximumGold2(int[][] grid) {
        int len1=grid.length;
        int len2=grid[0].length;
        for(int i=0;i<len1;i++){
            for(int j=0;j<len2;j++){
                //如果洞内黄金价值为0，不进入
                if(grid[i][j]==0){
                    continue;
                }
                int k=grid[i][j];
                grid[i][j]=0;
                goldHelper(grid,i,j,k);
                grid[i][j]=k;
            }
        }
        return maxValue;
    }
    //判断坐标为x,y的洞周围是否已经全部被访问过了，如果是，则此次终止
    private boolean termination(int[][] grid,int x,int y){
        for(int i=0;i<4;i++){
            int nx=x+xMove[i];
            int ny=y+yMove[i];
            if(isSafe(grid,nx,ny)) {
                if (grid[nx][ny] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    //判断坐标是否合法以及该洞是否被访问过
    private boolean isSafe(int[][] grid,int x,int y){
        int xLen=grid.length;
        int yLen=grid[0].length;
        return x>=0&&x<xLen&&y>=0&&y<yLen&&grid[x][y]!=0;
    }
    private void goldHelper(int[][] grid,int x,int y,int sum){
        //如果全部被访问过了
        if(termination(grid,x,y)){
            this.maxValue= Math.max(sum, maxValue);
            return;
        }
        //四个方向
        for(int i=0;i<4;i++){
            int nx=x+xMove[i];
            int ny=y+yMove[i];
            //判断是否合法
            if(isSafe(grid,nx,ny)){
                int k=grid[nx][ny];
                grid[nx][ny]=0;
                goldHelper(grid,nx,ny,sum+k);
                grid[nx][ny]=k;
            }
        }
    }
/**************************************************************************/
    public int getMaximumGold(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                //如果洞内黄金价值为0，不进入
                if(grid[i][j]==0){
                    continue;
                }
                res=Math.max(maxGoldHelper(grid,i,j),res);
            }
        }
        return res;
    }

    private int maxGoldHelper(int[][] grid, int x, int y) {
        int k=grid[x][y];
        grid[x][y]=0;
        int max=0;
        if(x+1<grid.length&&grid[x+1][y]!=0){
            max=Math.max(max,maxGoldHelper(grid,x+1,y));
        }
        if(y+1<grid[0].length&&grid[x][y+1]!=0){
            max=Math.max(max,maxGoldHelper(grid,x,y+1));
        }
        if(x-1>=0&&grid[x-1][y]!=0){
            max=Math.max(max,maxGoldHelper(grid,x-1,y));
        }
        if(y-1>=0&&grid[x][y-1]!=0){
            max=Math.max(max,maxGoldHelper(grid,x,y-1));
        }
        grid[x][y]=k;
        return max+k;
    }


    public static void main(String[] args) {
        int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
        PathwithMaximumGold_1219 gold = new PathwithMaximumGold_1219();
        int max = gold.getMaximumGold(grid);

        int[][] grid1={{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
        int max2 = gold.getMaximumGold(grid1);
        System.out.println(max2);
    }
}
