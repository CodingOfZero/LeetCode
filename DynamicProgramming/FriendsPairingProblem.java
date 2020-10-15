package Dynamic;

/**
 * 问题：
 * 给定n个朋友，每个朋友可以保持单身，也可以和其他朋友配对。
 * 每个朋友只能配对一次。求出朋友可以保持单身或可以配对的总次数
 *
 */
public class FriendsPairingProblem {
    /**
     * f(n)：n个人单身或配对的次数
     * 对于第n个人来说，他有两种选择
     * 1）自己保持单身，即f(n-1)
     * 2）第n个人和其他n-1个配对，则有（n-1）*f(n-2)
     * 时间与空间均为O(n)
     */
    public static int countFriendsPairings(int n){
        int[] dp=new int[n+1];

        for(int i=0;i<=n;i++){
            if(i<=2){
                dp[i]=i;
            }else{
                dp[i]=dp[i-1]+(i-1)*dp[i-2];
            }
        }
        return dp[n];
    }
    public static void main(String[] args)
    {
        int n = 4;
        System.out.println(countFriendsPairings(n));
    }
}
