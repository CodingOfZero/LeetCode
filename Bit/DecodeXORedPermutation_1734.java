package Bit;

import java.util.LinkedList;
import java.util.List;

/**
 * 解码异或后的排列
 *
 * 如果能求得开头或末尾元素，则与1720题目相同
 * 利用n是奇数求解
 * encode= ans[0]^ans[1], ans[1]^ans[2],ans[2]^ans[3],,,ans[n-2]^ans[n-1]
 * 隔一位进行异或，可求得  ans[0]^ans[1]^ans[2]
 * 通过对1-n进行异或，ans[0]^ans[1]^ans[2]^ans[3]
 * 两者进行异或可得最后一位元素
 *
 */
public class DecodeXORedPermutation_1734 {
    /**
     * 超时
     */
    public int[] decode(int[] encoded) {
        int n=encoded.length+1;
        int[] number=new int[n];
        for(int i=0;i<n;i++){
            number[i]=i+1;
        }
        boolean[] visited=new boolean[n];
        return dfs(encoded,number,visited,new LinkedList<>());
    }
    private int[] dfs(int[] encoded, int[] number, boolean[] visited, List<Integer> cache) {
        if(cache.size()==number.length){
            int[] res=new int[number.length];
            for(int i=0;i<number.length;i++){
                res[i]=cache.get(i);
            }
            return res;
        }
        if(cache.size()>1){
            int index=cache.size()-1;
            if(encoded[index-1]!=(cache.get(index)^cache.get(index-1))){
                return new int[0];
            }
        }
        int[] ans;
        for(int i=0;i<number.length;i++){
            if(visited[i]){
                continue;
            }
            visited[i]=true;
            cache.add(number[i]);
            ans=dfs(encoded, number, visited, cache);
            if(ans.length==number.length){
                return ans;
            }
            cache.remove(cache.size()-1);
            visited[i]=false;
        }
        return new int[0];
    }

    public int[] decodeAccept(int[] encoded) {
        int n=encoded.length+1;
        int[] res=new int[n];

        int first=0;
        for(int i=0;i<encoded.length;i+=2){
            first^=encoded[i];
        }
        int second=0;
        for(int i=1;i<=n;i++){
            second^=i;
        }
        int last=first^second;
        res[n-1]=last;
        for(int i=n-2;i>=0;i--){
            res[i]=encoded[i]^res[i+1];
        }
        return res;
    }

}
