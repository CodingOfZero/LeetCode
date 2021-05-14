package Backtrack;


import java.util.HashMap;
import java.util.Map;

/**
 * 停在原地的方案数
 */
public class NumberOfWaysToStayInTheSamePlace_1269 {


    public int numWays(int steps, int arrLen) {
        Map<String,Integer> memo=new HashMap<>();
        return dfs(arrLen,0,steps,memo);
    }
    private int MOD=1000000007;
    private int dfs(int arrLen, int cur, int step,Map<String,Integer> memo) {
        String key=cur+"_"+step;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        if(step==0){
            if(cur==0){
                return 1;
            }
            memo.put(key,0);
            return 0;
        }
        int ans=0;
        for(int i=-1;i<=1;i++){
            if(cur+i<0||cur+i>=arrLen){
                continue;
            }
            ans=(ans+dfs(arrLen, cur+i, step-1,memo)%MOD)%MOD;
        }
        memo.put(key,ans);
        return ans;
    }

    /**
     * dp[i][j]：表示经过i步后指针移动到下标j的方案数
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWaysDP(int steps, int arrLen) {
        int MOD=1000000007;
        //int index=Math.min(arrLen-1,steps);
        int index=Math.min(arrLen-1,steps/2);
        int[][] solution=new int[steps+1][index+1];
        solution[0][0]=1;
        for(int i=1;i<solution.length;i++){
            for(int j=0;j<solution[0].length;j++){
                solution[i][j]=solution[i-1][j];
                if(j-1>=0){
                    solution[i][j]=(solution[i][j]+solution[i-1][j-1])%MOD;
                }
                if(j+1< solution[0].length){
                    solution[i][j]=(solution[i][j]+solution[i-1][j+1])%MOD;
                }
            }
        }
        return solution[steps][0];
    }


    /**
     * 想法错误，想表达10的9次方，直接将10^9粘了过来，程序中10^9表示异或，同时还要注意它的优先级很低
     * 加减乘除的优先级远远大于异或
     */
//    public static int get1(){
//        return (50+1)%1000000007;
//    }
//    private static final int MOD=10^9 + 7;
//    public static int get2(){
//        return (50+1)%MOD;
//    }

    public static void main(String[] args) {
        NumberOfWaysToStayInTheSamePlace_1269 test = new NumberOfWaysToStayInTheSamePlace_1269();
        int i = test.numWays(3, 2);
        int i1 = test.numWays(2, 4);
        int i2 = test.numWaysDP(27, 7);
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);




    }
}
