package Math;

/**
 * 实现幂函数，n范围为[−2^31, 2^31 − 1] x的范围为-100.0 < x < 100.0
 * 使用分治方法
 */
public class Pow_50 {
    public static double myPow(double x, int n) {
        if(n==0)
            return 1;
        if(n==1)
            return x;
        if (n == -1) {//在递归里面变为倒数，如果求得乘积后在直接变为倒数可能会stackOverFlowError
            return 1 / x;
        }
        double result=myPow(x,n>>1);
        result*=result;
        if((n & 0x1)==1)
            result*=x;
        return result;
//        double m=myPow(x,n>>1);
//        double r=myPow(x,n%2);
//        return m*m*r;
    }

    public static void main(String[] args) {
        double x=2; int n=-2147483648;
        System.out.println(myPow(x,n));
    }
}
