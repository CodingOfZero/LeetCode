package Math;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 找到第n个丑数
 */
public class UglyNumberII_264 {
    /**
     * 动态规划
     *      (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     *      (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     *      (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     *      我们可以发现每个子序列都是丑数序列本身（1，2，3，4，5，......）乘以2，3，5。
     *      然后我们用类似于合并排序的方法，从三个子序列中得到每个丑数。
     *      每一步我们选择最小的一个，然后再移动一步
     */
    public int nthUglyNumber(int n) {
        int[] ugly=new int[n];
        ugly[0]=1;
        //三个子序列的索引初始化均为0
        int i2=0,i3=0,i5=0;
        int nextMultipleOf2 = 2;
        int nextMultipleOf3 = 3;
        int nextMultipleOf5 = 5;
        int nextUglyNo =1;
        for(int i=1;i<n;i++){
            nextUglyNo = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            ugly[i]=nextUglyNo;
            if(nextUglyNo==nextMultipleOf2){
                i2++;
                nextMultipleOf2=ugly[i2]*2;
            }
            if(nextUglyNo==nextMultipleOf3){
                i3++;
                nextMultipleOf3=ugly[i3]*3;
            }
            if(nextUglyNo==nextMultipleOf5){
                i5++;
                nextMultipleOf5=ugly[i5]*5;
            }
        }
        return nextUglyNo;
    }

    /**
     * 使用最小堆，初始时堆为空，首先将最小的丑数加入堆中，每次取出堆顶元素，则x是堆中最小的丑数，由于2x,3x,5x是丑数，因此将其加入堆
     * 上述做法会导致堆内有重复元素，可用哈希去重
    * @param n
     * @return
     */
    public int nthUglyNumberHeap(int n) {
        int[] factors={2,3,5};
        HashSet<Long> hasInclude=new HashSet<>();
        PriorityQueue<Long> heap=new PriorityQueue<>();
        hasInclude.add(1L);
        heap.add(1L);
        int ugly=0;
        for(int i=0;i<n;i++){
            long cur = heap.poll();
            ugly=(int) cur;
            for(int factor:factors){
                long next=cur*factor;
                if(hasInclude.add(next)){
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }
    /**
     * 暴力迭代,超时
     * @param n
     * @return
     */
    public int nthUglyNumber_1(int n) {
        int i=1;
        int count=1;
        while (n>count){
            i++;
            if(isUgly(i)==1)
                count++;
        }
        return  i;
    }
    private int isUgly(int no){
        no=maxDivide(no,2);
        no=maxDivide(no,3);
        no=maxDivide(no,5);
        return no==1?1:0;
    }
    private int maxDivide(int a,int b){
        while (a%b==0) {
            a/=b;
        }
        return a;
    }
}
