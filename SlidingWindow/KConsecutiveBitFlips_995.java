package SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用差分数组
 * A[i] 翻转偶数次的结果是 A[i]；翻转奇数次的结果是 A[i] ^ 1
 */
public class KConsecutiveBitFlips_995 {

    public int minKBitFlips(int[] A, int K) {
        int n=A.length;
        //翻转次数
        int[] diff=new int[n+1];
        int ans=0,revCnt=0;
        for(int i=0;i<n;i++){
            //累加差分数组
            revCnt+=diff[i];
            //判断A[i]+revCnt是否为偶数
            if((A[i]+revCnt)%2==0){
                //表明为0，需要翻转
                if(i+K>n){
                    return -1;
                }
                ans++;
                //[i,i+K-1]差分数组只有边界处有变化 i加1,i+K减1
                revCnt++;
                diff[i+K]--;
            }
        }
        return ans;
    }
    /**
     * 使用队列模拟滑动窗口，该滑动窗口的含义是前面 K - 1个元素中，
     * 以哪些位置起始的 子区间 进行了翻转。
     * 该滑动窗口从左向右滑动，如果当前位置 i 需要翻转，则把该位置存储到队列中。
     * 遍历到新位置 j (j < i + K) 时，队列中元素的个数代表了 i 被前面 K - 1个元素翻转的次数。
     * 当 A[i]为 0，如果 i位置被翻转了偶数次，那么翻转后仍是 0，当前元素需要翻转；
     * 当 A[i]为 1，如果 i位置被翻转了奇数次，那么翻转后变成 0，当前元素需要翻转。
     * 综合上面两点，我们得到一个结论，如果 len(que) % 2 == A[i] 时，当前元素需要翻转。
     *
     * 当 i + K > N时，说明需要翻转大小为 K 的子区间，但是后面剩余的元素不到 K 个了，所以返回 -1。
     * 时间复杂度为O(N)  空间O(K)
     */
    public int minKBitFlipsSW(int[] A, int K) {
        int res=0;
        Queue<Integer> flapPos=new LinkedList<>();
        for(int i=0;i<A.length;i++){
            //队列中的元素已不在滑动窗口内
            if(flapPos.size()>0&&i>flapPos.peek()+K-1){
                flapPos.remove();
            }
            //当 A[i]为 0，如果 i位置被翻转了偶数次，那么翻转后仍是 0，当前元素需要翻转；
            //当 A[i]为 1，如果 i位置被翻转了奇数次，那么翻转后变成 0，当前元素需要翻转。
            if(flapPos.size()%2==A[i]){
                if(i+K>A.length) return -1;
                //需要翻转
                flapPos.add(i);
                res++;
            }
        }
        return res;
    }

    /**
     * 暴力迭代，超时
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlipsBF(int[] A, int K) {
        int count=0;
        boolean flag=true;
        for(int i=0;i<A.length-K+1;i++){
            if(A[i]==0){
                count++;
                for(int j=i;j<i+K;j++){
                    A[j]=A[j]==0?1:0;
                }
            }
        }
        for(int j=A.length-K+1;j<A.length;j++){
            if(A[j]==0) flag=false;
        }
        return flag?count:-1;
    }
}
