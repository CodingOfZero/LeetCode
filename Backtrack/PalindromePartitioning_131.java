package Backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文串
 * 将字符串分割成一些子串，使得每个子串都是回文串
 */
public class PalindromePartitioning_131 {
//    public List<List<String>> partition(String s){
//        List<List<String>> res=new LinkedList<>();
//        if(s==null||s.isEmpty()){
//            return res;
//        }
//        LinkedList<String> cur=new LinkedList<>();
//
//        char[] chars = s.toCharArray();
//        partitionUtilFaster(chars,0,chars.length,res,cur);
//        return res;
//    }
//
//    private void partitionUtilFaster(char[] chars, int start, int end, List<List<String>> res, LinkedList<String> cur) {
//        if(start==end){
//            res.add(new LinkedList<>(cur));
//            return;
//        }
//        for(int i=start;i<end;i++){
//            if(!check(chars,start,i)){
//                continue;
//            }
//            cur.addLast(new String(chars,start,i-start+1));
//            partitionUtilFaster(chars,i+1,end,res,cur);
//            cur.removeLast();
//        }
//    }
//
//    private boolean check(char[] chars, int start, int end) {
//        while (start<end){
//            if(chars[start]!=chars[end]){
//                return false;
//            }
//            start++;
//            end--;
//        }
//        return true;
//    }

    public List<List<String>> partition(String s){
        List<List<String>> res=new LinkedList<>();
        if(s==null||s.isEmpty()){
            return res;
        }
        LinkedList<String> cur=new LinkedList<>();
        //利用动态规划进行预处理，可以O(1)时间判定字符串是否为回文串
        int n = s.length();
        boolean[][] dp=new boolean[n][n];
        for(boolean[] item:dp){
            Arrays.fill(item,true);
        }
        for(int i=n-1;i>=0;i--){
            for(int j=i+1;j<n;j++){
                dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j);
            }
        }

        dfs(s,0,n,res,cur,dp);
        return res;
    }

    private void dfs(String s, int i, int n, List<List<String>> res,
                     LinkedList<String> cur,boolean[][] dp) {
        if(i==n){
            res.add(new LinkedList<>(cur));
            return;
        }
        for(int j=i;j<n;j++){
            if(dp[i][j]){
                cur.add(s.substring(i,j+1));
                dfs(s,j+1,n,res,cur,dp);
                cur.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning_131 test = new PalindromePartitioning_131();
        test.partition("aab");
    }
}
