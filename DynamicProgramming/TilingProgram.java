package Dynamic;

/**
 * 给定一块“ 2 xn”板和尺寸为“ 2 x 1”的砖，计算使用2 x 1砖对给定板进行铺砖的方法数量。
 * 一个图块可以水平放置（即1 x 2图块），也可以垂直放置（例如2 x 1图块）。
 * 令“ count（n）”为在“ 2 xn”网格上放置图块的方式的计数，我们有以下两种方式来放置第一个图块。
 * 1）如果我们将第一个图块垂直放置，则问题将减少为“ count（n-1）”。
 * 2）如果我们将第一个图块水平放置，则必须将第二个图块也水平放置。因此问题减少到“ count（n-2）”
 * 本质上是斐波那契数列
 * 以下进行推广，问用mX1的砖铺mXn的地面，铺的方法有几种
 */
public class TilingProgram {
    /**
     * 用mX1的砖铺mXn的地面，铺的方法有几种
     * @param n 地面的边
     * @param m 砖的边长
     * @return 方式数目
     */
    public static int countWays(int n,int m){
        int[] count=new int[n+1];
        count[0]=0;
        for(int i=1;i<=n;i++){
            if(i<m||i==1) {
                //1 <= n <m
                count[i]=1;
            } else if(i>m){
                //m<n
                count[i]=count[i-1]+count[i-m];
            }else{//m==n
                count[i]=2;
            }
        }
        return count[n];
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 4;
        System.out.println("Number of ways = "
                + countWays(n, m));
    }
}
