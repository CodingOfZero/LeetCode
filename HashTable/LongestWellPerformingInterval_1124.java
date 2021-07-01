package HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 *  表现良好的最长时间段
 *
 */
public class LongestWellPerformingInterval_1124 {

    /**
     1.将大于8视为1，小于8视为-1，即求最长的连续子串和大于0
     2.从前往后遍历，如果和大于0，直接更新结果
     3.如果和小于0，记录首次出现位置，判断是否sum-1是否存在，如果存在，进行更新操作。i对应的前缀和是sum j是sum-1，此时sum-(sum-1)>0
     4.为何不判断sum-2,sum-3等，因为每次都是加1，减1，-2一定是出现在-1右侧，因此如果sum-1存在，那么一定是最左侧即下标最小的
     */
    public int longestWPIFast(int[] hours) {
        int n=hours.length;
        int sum=0;
        int res=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            sum+=(hours[i]>8?1:-1);
            if(sum>0){
                res=i+1;
            }else{
                map.putIfAbsent(sum,i);
                if(map.containsKey(sum-1)){
                    res=Math.max(res,i-map.get(sum-1));
                }
            }
        }
        return res;
    }
    public static int longestWPI(int[] hours) {
        int n=hours.length;
        int[] tire=new int[n];
        if(hours[0]>8) tire[0]=1;

        for(int i=1;i<n;i++){
            if(hours[i]>8){
                tire[i]=tire[i-1]+1;
            }else{
                tire[i]=tire[i-1];
            }
        }
        int maxLen=0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int add=hours[i]>8?1:0;
                int other=tire[j]-tire[i]+add;
                int len=j-i+1;
                int lazy=len-other;
                if(other>lazy){
                    maxLen=Math.max(maxLen,len);
                }
            }
        }
        return maxLen;
    }
    public static void main(String[] args){
        int i = longestWPI(new int[]{6, 6, 6});
        System.out.println(i);
    }
}
