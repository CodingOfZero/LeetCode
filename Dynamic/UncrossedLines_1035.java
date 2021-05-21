package Dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 求不相交的线的最多个数
 * 题目本质为最长公共子序列
 */
public class UncrossedLines_1035 {
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        int[][] cache=new int[m+1][n+1];
        //0表示长度为0
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                //当某个数组长度为0时，LCS为0
                if(i==0||j==0){
                    cache[i][j]=0;
                }else if(nums1[i-1]==nums2[j-1]){
                    cache[i][j]=1+cache[i-1][j-1];
                }else{
                    cache[i][j]=Math.max(cache[i][j-1],cache[i-1][j]);
                }
            }
        }
        return cache[m][n];
    }




    /*错误答案
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> numToIndex1=new HashMap<>();
        Map<Integer,List<Integer>> numToIndex2=new HashMap<>();

        if(nums2.length<nums1.length){
            int[] temp=nums2;
            nums2=nums1;
            nums1=temp;
        }
        int n1=nums1.length;
        int n2=nums2.length;
        for(int i=0;i<n1;i++){
            List<Integer> temp = numToIndex1.getOrDefault(nums1[i], new LinkedList<>());
            temp.add(i);
            numToIndex1.put(nums1[i],temp);
        }
        for(int i=0;i<n2;i++){
            List<Integer> temp = numToIndex2.getOrDefault(nums2[i], new LinkedList<>());
            temp.add(i);
            numToIndex2.put(nums2[i],temp );
        }
        int res=0;
        for(int i=0;i<n1;i++){
            int index=-1;
            int resTemp=0;
            for(int j=i;j<n1;j++){
                if(numToIndex2.containsKey(nums1[j])){
                    List<Integer> indexList = numToIndex2.get(nums1[j]);
                    for(int x:indexList){
                        if(x>index){
                            index=x;
                            resTemp++;
                            break;
                        }
                    }
                }

            }
            res=Math.max(res,resTemp);
        }

        return res;
    }*/

    public static void main(String[] args) {
        int i = maxUncrossedLines(new int[]{3,1,4,1,1,3,5,1,2,2}, new int[]{4,1,5,2,1,1,1,5,3,1,1,1,2,3,1,4,3,5,5,3,1,2,3,2,4,1,1,1,5,3});
        System.out.println(i);
    }


}
