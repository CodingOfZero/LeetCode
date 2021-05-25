package Dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaketheXORofAllSegmentsEqualtoZero_1787 {
    /**
     f[i][xor]:前i列且首行前i列异或结果为xor的最小更改元素数
     将一维输入视为二维
     345217347-->345
     217
     347
     问题等价于保证每列相同且首行异或结果为0(有可能最后一行不够k)
     */
    public int minChanges(int[] nums, int k) {
        int n=nums.length;
        int max=1024;
        int[][] f=new int[k][max];
        int[] g=new int[k];
        for(int i=0;i<k;i++){
            // 极大值，为了防止整数溢出选择 INT_MAX / 2
            Arrays.fill(f[i],0x3f3f3f3f);
            g[i]=0x3f3f3f3f;
        }

        for(int i=0;i<k;i++){
            int cnt=0;
            Map<Integer,Integer> map=new HashMap<>();
            for(int j=i;j<n;j+=k){
                map.put(nums[j],map.getOrDefault(nums[j],0)+1);
                cnt++;
            }
            if(i==0){
                for(int xor=0;xor<max;xor++){
                    f[0][xor]=cnt-map.getOrDefault(xor,0);
                    g[0]=Math.min(g[0],f[0][xor]);
                }
            }else{

                for(int xor=0;xor<1024;xor++){
                    //整行替换
                    f[i][xor]=cnt+g[i-1];
                    //部分值替换
                    for(int cur:map.keySet()){
                        f[i][xor]=Math.min(f[i][xor],f[i-1][xor^cur]+cnt-map.get(cur));
                    }
                    g[i]=Math.min(g[i],f[i][xor]);
                }

            }
        }
        return f[k-1][0];
    }
}
