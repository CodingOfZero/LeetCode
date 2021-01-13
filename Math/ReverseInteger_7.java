package Math;

/**
 * 数字逆序输出
 */
public class ReverseInteger_7 {
    public int reverse(int x) {
        long n=0;
        while (x!=0){
            n=n*10+x%10;
            x=x/10;
        }
        return (int)n==n?(int)n:0;
    }
}
