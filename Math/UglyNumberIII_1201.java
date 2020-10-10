package Math;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 求第n个丑数,这里丑数定义为可以整除 a,b,c的数，而非质因数
 * 注意：要使用long，防止溢出
 */
public class UglyNumberIII_1201 {
    /**
     *该题定义的丑数与前面的丑数定义不同，假设a=2,b=11,c=13  每次选最小的，
     *      (1) 1×2,   2×2,   3×2,   4×2,   5×2, …
     *      (2) 1×11,  2×11,  3×11,  4×11,  5×11, …
     *      (3) 1×13,  2×13,  3×13,  4×13,  5×13, …
     * @param n 序号
     * @param a 除数
     * @param b 除数
     * @param c 除数
     * @return 第n个丑数
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        long nextMultipleOfa = a;
        long nextMultipleOfb = b;
        long nextMultipleOfc = c;
        //a,b,c的乘数
        long ia=1,ib=1,ic=1;
        long nextUglyNo=0;
        long count=0,preUglyNo=0;
        while (count<n){
            //选出最小的
            nextUglyNo = Math.min(nextMultipleOfa, Math.min(nextMultipleOfb, nextMultipleOfc));
            //如果不同，加入到计数当中去
            if(preUglyNo!=nextUglyNo){
                count++;
                preUglyNo=nextUglyNo;
            }
            //如果是a这个数组移动了，将乘数加1，计算下一个a的倍数的值
            if(nextUglyNo==nextMultipleOfa){
                ia++;
                nextMultipleOfa=ia*a;
            }
            if(nextUglyNo==nextMultipleOfb){
                ib++;
                nextMultipleOfb=ib*b;
            }
            if(nextUglyNo==nextMultipleOfc){
                ic++;
                nextMultipleOfc=ic*c;
            }
        }
        return (int)nextUglyNo;
    }
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        UglyNumberIII_1201 u=new UglyNumberIII_1201();
        System.out.println(u.nthUglyNumber(5,2,11,13));
        System.out.println(u.nthUglyNumber(4,2,3,4));
        System.out.println(u.nthUglyNumber(1000000000,2,168079517,403313907));

    }
}

