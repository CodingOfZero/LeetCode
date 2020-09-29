package Math;


/**
 * 求小于n的质数个数
 */
public class CountPrimes_204 {
    public int countPrimes(int n) {
        int count=0;
        if(n<=2) return 0;
        boolean[] mark=new boolean[n+1]; //默认false
        int limit=(int) Math.sqrt(n);//i*i是否超出范围
        //埃氏筛法
        for(int i=2;i<n;i++){
            if(!mark[i]) {
                count++;
                if(i<=limit)
                    for(int j=i*i;j<n;j+=i)
                        mark[j]=true;
            }
        }
        return count;
    }

}
