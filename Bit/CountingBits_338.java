package Bit;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
 * 计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 要求时间复杂度为O(n)
 * 思路：
 *   1.如果 i 为偶数，那么f(i) = f(i/2) ,因为 i/2 本质上是i的二进制左移一位，低位补零，所以1的数量不变。
 *   2.如果 i 为奇数，那么f(i) = f(i-1)+1
 */
public class CountingBits_338 {
    public static int[] countBits(int num) {
        int[] res=new int[num+1];
        for(int i=1;i<=num;i++){
            //res[i]=i%2==0?res[i/2]:res[i-1]+1;
            //状态转移方程：f(x)=f(x/2)+xmod2
            res[i]=res[i>>1]+(i&1);  // x / 2 is x >> 1 and x % 2 is x & 1
            //状态转移方程：f(x)=f(x&(x-1))+1  将最后设置位设置为0
            //res[i]=res[i&(i-1)]+1;
        }
        return res;
    }

    public static void main(String[] args) {
        countBits(4);
    }
}
