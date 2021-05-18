package Greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 洛谷1809过河问题，先排个序
 * 分类讨论
 *      1.当n=2时，花费的时间为两个人中的最长时间
 *      2.当n=3时，方案为最快的人先带最慢的人过河，最快的人返回，然后在与剩下的人一同过河，时间为a3+a1+a2
 *      3.当n>3时，最优方案有两种,每次都会减少2个人
 *              3.1最快的人带最慢的人先过河，最快的人返回，接着带次慢的人过河，然后最快的人返回；时间为An+A1+A(n-1)+A1
 *              3.2最快的人带次快的人过河，最快返回，最慢带次慢的人过河，然后次快的人返回；时间为A2+An+A1+A2
 *
 * 更简洁的思路, f(i) 代表i个人过河所需的最短时间,当增加第i个人时
 *  1.让最小的带最大过去，最小的回来， fi=f(i-1)+ai+a1
 *  2.让最小的带次小的过去，最小的回来，最大的两个过去，先前的次小回来 fi=f(i-2) + ai+a1+a2*2
 *  两者取最小
 */
public class RiverCrossing_P1809 {
    public int minTime(int[] times){
        Arrays.sort(times);
        int[] dp=new int[times.length];
        dp[0]=times[0];
        dp[1]=times[1];
        dp[2]=times[0]+times[1]+times[2];

        for(int i=3;i<dp.length;i++){
            dp[i]=dp[i-2]+Math.min(times[i]+times[i-1]+2*times[0],times[0]+2*times[1]+times[i]);
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] times=new int[n];
        for(int i=0;i<n;i++){
            times[i]=in.nextInt();
        }

        RiverCrossing_P1809 river = new RiverCrossing_P1809();
        System.out.println(river.minTime(times));
    }
}
