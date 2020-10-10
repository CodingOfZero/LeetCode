package Math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * UglyNumberⅡ的推广
 */
public class SuperUglyNumber_313 {
    /**
     * 动态规划
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n==1) {
            return 1;
        }
        //存储丑数
        int[] ugly=new int[n];
        ugly[0]=1;
        //nextMultiple保存给定素数的倍数
        int[] nextMultiple=Arrays.copyOf(primes,primes.length);
        //存储各自的迭代器
        int[] indexes=new int[primes.length];
        Arrays.fill(indexes,0);
        int preUglyNo=1;
        for(int i=1;i<n;i++){
            //nextUglyNo为下一个丑数
            int nextUglyNo =Integer.MAX_VALUE;
            for(int j=0;j<nextMultiple.length;j++){
                nextUglyNo=Math.min(nextUglyNo,nextMultiple[j]);
            }
            if(preUglyNo!=nextUglyNo){
                //将其存入到丑数中去
                ugly[i]=nextUglyNo;
                preUglyNo=nextUglyNo;
            }
            for(int k=0;k<nextMultiple.length;k++){
                //找到上面存储的丑数是哪一个，将其迭代器加1，更新它的值，值等于素数乘以丑数
                if(nextMultiple[k]==nextUglyNo){
                    indexes[k]++;
                    nextMultiple[k]=ugly[indexes[k]]*primes[k];
                }
            }
        }
        return ugly[n-1];
    }
    /**
     * 使用最小堆，优先队列
     * 首先将质因数组全部加入优先队列，然后从最小堆中取出最小值，
     * 如果此时的堆顶的值与最小值不同（防止重复），增加计数，然后将最小值与质因数数组内的数字分别相乘加入优先队列中，
     * 取出来当作下一个超级丑数
     * Assuming a[] = {2, 3, 5},
     * so at first iteration 1 is top so 1 is popped and 1 * 2, 1 * 3, 1 * 5 is pushed.
     * At second iteration min is 2, so it is popped and 2 * 2, 2 * 3, 2 * 5 is pushed and so on.
     * @param n 序号
     * @param primes 质因数数组
     * @return 第n个超级丑数
     */
    public int nthSuperUglyNumber_1(int n, int[] primes) {
        //n应该为非负数
        if(n<0||primes.length<1) {
            return -1;
        }
        //第一个超级丑数为1
        if(n==1) {
            return 1;
        }
        //声明一个优先队列（最小堆）
        PriorityQueue<Long> pq=new PriorityQueue<>();
        //将质因数数组的全部数字加入优先队列中
        for(long prime:primes) {
            pq.add(prime);
        }
        //no为最后结果，count为次数，初始为1，
        long count=1,no=0;
        while (count<n){
            //最小堆堆顶
            no = pq.peek();
            //删除堆顶元素
            pq.poll();
            //如果pq的顶部是no，那么就不要增加计数。这样做是为了避免重复计算同一编号。
            if(pq.isEmpty()||no!=pq.peek()){
                count++;
                //将所有编号的倍数推入优先级队列。
                for(int prime:primes){
                    pq.add(prime*no);
                }
            }
        }
        return (int)no;
    }
}
