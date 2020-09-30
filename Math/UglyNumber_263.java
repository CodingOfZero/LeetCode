package Math;

/**
 * 判断给定的数是否为丑数，丑数定义为只有2，3，5为质因数的数
 */
public class UglyNumber_263 {
    public boolean isUgly(int num) {
        if(num==1) return true;
        if(num<1) return false;
        while (num%2==0)
            num/=2;
        while (num%3==0)
            num/=3;
        while (num%5==0)
            num/=5;
        if(num==1)
            return true;
        else
            return false;
    }
}
