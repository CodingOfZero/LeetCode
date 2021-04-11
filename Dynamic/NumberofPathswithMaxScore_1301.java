package Dynamic;

import java.util.Arrays;
import java.util.List;

public class NumberofPathswithMaxScore_1301 {
    static class Path{
        int sum;
        int num;
        Path(){}
        Path(int sum,int num){
            this.sum=sum;
            this.num=num;
        }
    }
    private static final int MOD =(int)1e9+7;
    public int[] pathsWithMaxScore(List<String> board) {
        int n=board.size();
        Path[][] paths=new Path[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                paths[i][j]=new Path(-1,0);
            }
        }
        paths[n-1][n-1]=new Path(0,1);

        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(board.get(i).charAt(j)!='X'&&!(i==n-1&&j==n-1)){
                    update(paths,n,i,j,i+1,j);
                    update(paths,n,i,j,i,j+1);
                    update(paths,n,i,j,i+1,j+1);
                    if(paths[i][j].sum!=-1){
                        paths[i][j].sum+=(board.get(i).charAt(j)=='E'?0:board.get(i).charAt(j)-'0');
                    }
                }
            }
        }
        return paths[0][0].sum==-1?new int[]{0,0}:new int[]{paths[0][0].sum,paths[0][0].num};
    }
    /**
     * 转移函数，用[u,v]转移[x,y]
     */
    private void update(Path[][] paths,int n,int x,int y,int u,int v){
        if(u>=n||v>=n||paths[u][v].sum==-1){
            return;
        }
        if(paths[u][v].sum>paths[x][y].sum){
            //不能直接赋值，每个数组内的元素都应当是独立的，而非其他的引用，否则修改会影响其他元素
            paths[x][y].sum=paths[u][v].sum;
            paths[x][y].num=paths[u][v].num;
        }else if(paths[u][v].sum==paths[x][y].sum){
            //最大值可能不止由一个方向转移过来，如果相等，表明有其他方向，加上其他方向的方案数
            paths[x][y].num+=paths[u][v].num;
            if(paths[x][y].num>=MOD){
                paths[x][y].num-=MOD;
            }
        }
    }

    public static void main(String[] args) {
        NumberofPathswithMaxScore_1301 test = new NumberofPathswithMaxScore_1301();
        List<String> e23 = Arrays.asList("E23", "2X2", "12S");
        int[] ints = test.pathsWithMaxScore(e23);
        System.out.println(ints[0]+" "+ints[1]);
    }
}
