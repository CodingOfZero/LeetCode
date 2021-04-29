package Dynamic;

import java.util.*;
import java.util.stream.Collectors;

public class FrogJump_403 {
    /**
     * 记忆化
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        if(stones[1]>1) return false;
        Set<Integer> hashSet= Arrays.stream(stones).boxed().collect(Collectors.toSet());
        Map<String,Boolean> dp=new HashMap<>();
        return canCrossHelper(hashSet,1,stones[stones.length-1],1,dp);
    }

    private boolean canCrossHelper(Set<Integer> hashSet, int cur,int target,int k,Map<String,Boolean> dp) {
        if(cur==target){
            return true;
        }
        if(hashSet.contains(cur+k+1)){
            if(dp.containsKey(cur+"_"+(k+1))){
                return dp.get(cur+"_"+(k+1));
            }
            boolean flag = canCrossHelper(hashSet, cur + k + 1, target, k + 1, dp);
            dp.put(cur+"_"+(k+1),flag);
            if(flag){
                return true;
            }
        }
        if(k==0) return false;
        if(hashSet.contains(cur+k)){
            if(dp.containsKey(cur+"_"+(k))){
                return dp.get(cur+"_"+(k));
            }
            boolean flag = canCrossHelper(hashSet, cur + k, target, k, dp);
            dp.put(cur+"_"+(k),flag);
            if(flag){
                return true;
            }
        }
        if(hashSet.contains(cur+k-1)){
            if(dp.containsKey(cur+"_"+(k-1))){
                return dp.get(cur+"_"+(k-1));
            }
            boolean flag = canCrossHelper(hashSet, cur + k - 1, target, k - 1, dp);
            dp.put(cur+"_"+(k-1),flag);
            if(flag){
                return true;
            }
        }

        return false;
    }

    /**
     * 方程定义：dp[i][k]=dp[j][k-1]||dp[j][k]||dp[j][k+1]
     * 含义：当前在第i个位置，并且是以步长k跳到位置i时，是否到达最后一块石子；
     * 由三种方式转移过来，dp[j][k-1]：可以通过k-1步跳到位置j，然后由j通过步长k+1跳到i;
     *
     * 利用「路径可逆」的性质，将问题进行了「等效对偶」如果存在一条路径从开头跳到末尾，那么一定存在从末尾到开头的路径
     * //没有明白
     * @param stones
     * @return
     */
    public boolean canCrossDP(int[] stones) {
        if(stones[1]>1) return false;
        int n=stones.length;
        boolean[][] dp=new boolean[n][n];
        //给定初始值，推动状态转移
        dp[1][1]=true;

        for(int i=2;i<n;i++){
            for(int j=1;j<i;j++){
                int k=stones[i]-stones[j];
                if(k<=j+1){
                    dp[i][k]=dp[j][k-1]||dp[j][k]||dp[j][k+1];
                }
            }
        }
        for(int i=1;i<n;i++){
            if(dp[n-1][i]) return true;
        }
        return false;
    }
    //DFS,会超时
//    public boolean canCross(int[] stones) {
//        if(stones[1]>1) return false;
//        Set<Integer> hashSet= Arrays.stream(stones).boxed().collect(Collectors.toSet());
//        return canCrossHelper(hashSet,1,stones[stones.length-1],1);
//    }
//
//    private boolean canCrossHelper(Set<Integer> hashSet, int cur,int target,int k) {
//        if(cur==target){
//            return true;
//        }
//
//        if(hashSet.contains(cur+k+1)){
//            if(canCrossHelper(hashSet, cur+k+1, target, k+1)){
//                return true;
//            }
//        }
//        if(k==0) return false;
//        if(hashSet.contains(cur+k)){
//            if(canCrossHelper(hashSet, cur+k, target, k)){
//                return true;
//            }
//        }
//        if(hashSet.contains(cur+k-1)){
//            if(canCrossHelper(hashSet, cur+k-1, target, k-1)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        FrogJump_403 frogJump_403 = new FrogJump_403();
        frogJump_403.canCross(new int[]{
                0,1,3,5,6,8,12,17
        });
    }
}
