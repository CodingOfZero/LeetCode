package Array;

import java.util.*;

/**
 * 数字相加
 */
public class ArrayIntegerAdd {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res=new LinkedList<>();
        for(int i=A.length-1;i>=0;i--){
            int sum=K%10+A[i];
            K=K/10;
            if(sum>=10){
                K++;
                sum-=10;
            }
            res.add(sum);
        }
        for(;K>0;K/=10){
            res.add(K%10);
        }
        Collections.reverse(res);
        return res;
    }
}
