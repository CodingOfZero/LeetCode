package Bit;

/**
 * 不用加减实现加法运算
 * 负数在计算机中以补码的形式存在，负数补码为相反数原码取反加1
 * 正数的正反补相同
 */
public class SumofTwoIntegers_371 {
    public int getSum(int a, int b) {
        int sum=0;
        int carry=0;
        do{
            sum=a^b;
            carry=(a&b)<<1;
            a=sum;
            b=carry;
        }while (carry!=0);//直到进位为0
        return sum;
    }

}
