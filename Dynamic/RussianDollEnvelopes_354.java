package Dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 题目类型：最长严格递增子序列
 * 对宽度升序，对长度降序，将其二维转化位一维的最长严格递增子序列，在宽度排序完的数组中找递增序列
 * 对长度降序的理由：同一个宽度，不同的信封只能算一个，如果长度递增排序，则计算时会全部算上
 * 递减排序从根本上杜绝了错误的发生
 */
public class RussianDollEnvelopes_354 {
    /**
     *
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if(envelopes==null||envelopes.length==0) return 0;
        int len=envelopes.length;
        Arrays.sort(envelopes,(x,y)->(x[0]==y[0]?y[1]-x[1]:x[0]-y[0]));

        int[] lis=new int[len];
        Arrays.fill(lis,1);
        int ans=1;
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(envelopes[j][1]<envelopes[i][1]&&lis[j]+1>lis[i]){
                    lis[i]=lis[j]+1;
                }
            }
            ans=Math.max(ans,lis[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        int i = maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
        System.out.println(i);
    }
}
