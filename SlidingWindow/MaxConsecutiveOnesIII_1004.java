package SlidingWindow;

/**
 * 求最大连续1的个数，有k次将0翻转为1的机会，利用这些机会求最大连续1
 */
public class MaxConsecutiveOnesIII_1004 {

    public int longestOnes(int[] A, int K) {
        int left=0,right=0;
        //窗口只增不减
        while(right<A.length){
            if(A[right++]==0) K--;
            //仅在K小于0时才进行后面条件的判断
            if(K<0&&A[left++]==0) K++;
        }
        return right-left;
    }
}
