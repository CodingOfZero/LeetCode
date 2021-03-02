package Dynamic;

/**
 * 给一个m面的n个骰子，求获得总和X的方法数量，X为所有骰子的和
 * 递归
 *      sum(m,n,X)=sum(m,n-1,X-1)+sum(m,n-1,X-2)+...+sum(m,n-1,X-m)
 */
public class FindWays {
    /**
     * 时间复杂度为 O（m * n * x）
     * @param m 骰子面数
     * @param n 骰子个数
     * @param x 目标和
     * @return 方法数量
     */
    public static int findWays(int m,int n,int x){
        //n为骰子个数，x为目标和，table里面存放的是和为X的方法数量
        int[][] table=new int[n+1][x+1];
        for(int j=1;j<=m&&j<=x;j++){
            //当只有一个骰子时，和在m之内的只有一种方式
            table[1][j]=1;
        }
        for(int i=2;i<=n;i++){
            //和从1到x
            for(int j=1;j<=x;j++){
                //sum(m,n,X)=sum(m,n-1,X-1)+sum(m,n-1,X-2)+...+sum(m,n-1,X-m)
                for(int k=1;k<j && k<=m;k++){
                    table[i][j]+=table[i-1][j-k];
                }
            }
        }
        return table[n][x];
    }
    /**
     * 时间复杂度为 O（n * x）
     * 当X太大时
     * if (m*n <= x)
     *     return (m*n == x);
     * 当X太小时
     * if (n >= x)
     *     return (n == x);
    * */
    public static int findWaysFast(int m,int n,int x){
        //n为骰子个数，x为目标和，table里面存放的是和为X的方法数量
        int[][] table=new int[n+1][x+1];
        table[0][0]=1;
        for(int i=1;i<=n;i++){
            //和从1到x
            for(int j=i;j<=x;j++){
                //sum(m,n,X)=sum(m,n-1,X-1)+sum(m,n-1,X-2)+...+sum(m,n-1,X-m)
                //所以table[i][j]=table[i-1][j-1]+table[i-1][j-2]+...+table[i-1][j-m]
                //同理table[i][j-1]=table[i-1][(j-1)-1]+table[i-1][(j-1)-2]+...+table[i-1][(j-1)-m]

                //则table[i][j]=table[i][j-1]+table[i-1][j-1]-table[i-1][(j-1)-m]  如果j-m-1>=0的话需要减去
                table[i][j]=table[i][j-1]+table[i-1][j-1];
                if(j-m-1>=0){
                    table[i][j]=table[i][j]-table[i][j-m-1];
                }
            }
        }
        return table[n][x];
    }
    public static void main (String[] args) {
        System.out.println(findWaysFast(4, 2, 1));
        System.out.println(findWaysFast(2, 2, 3));
        System.out.println(findWaysFast(6, 3, 8));
        System.out.println(findWaysFast(4, 2, 5));
        System.out.println(findWaysFast(4, 3, 5));
    }
}
