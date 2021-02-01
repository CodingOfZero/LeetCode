package HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数字之和问题
 * res{x,y}
 * 由题意可得 sumA-x+y=sumB+x-y
 * 即 x=y+(A-B)/2
 * 对于B数组的元素，如果A数组中还有元素x则表明存在结果
 */
public class FairCandySwap_888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA= Arrays.stream(A).sum();
        int sumB= Arrays.stream(B).sum();
        int z=(sumA-sumB)/2;
        Set<Integer> hashSet=new HashSet<>();
        Arrays.stream(A).forEach(hashSet::add);
        int[] res=new int[2];
        for(int y:B){
            int x=y+z;
            if(hashSet.contains(x)){
                res[0]=x;
                res[1]=y;
            }
        }
        return res;
    }
}
