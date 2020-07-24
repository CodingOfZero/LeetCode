package Bit;

/**
 * 求得某个数中包含几个1
 */
public class NumberofBits_191 {
    public static int hammingWeight(int n) {
        int count=0;
        while (n!=0) {
            ++count;
            n=(n-1)&n;//n减一后的数与n相与会让n最右边的1变为0；
        }
        return count;
    }

    public static void main(String[] args) {
        int n=3;
        System.out.println(hammingWeight(n));
    }
}
