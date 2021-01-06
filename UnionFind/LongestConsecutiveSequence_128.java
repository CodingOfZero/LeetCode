package UnionFind;

import java.util.*;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 比如 [100,4,200,1,3,2]   最长序列为1,2,3,4
 */
public class LongestConsecutiveSequence_128 {

    //如果已知有一个 x, x+1, x+2,⋯,x+y 的连续序列，
    //而我们却重新从 x+1 x+2 或者是 x+y 处开始尝试匹配，
    //那么得到的结果肯定不会优于枚举 x 为起点的答案因此我们在外层循环的时候碰到这种情况跳过即可
    //由于我们要枚举的数 x 一定是在数组中不存在前驱数 x−1 的
    //因此我们每次在哈希表中检查是否存在 x-1 即能判断是否需要跳过了
    //时间空间均为O(n)
    public int longestConsecutiveOne(int[] nums){
        Set<Integer> hashSet=new HashSet<>();
        for(int num:nums){
            hashSet.add(num);
        }
        int res=0;
        for(int num:nums){
            if(!hashSet.contains(num-1)){
                int curNum=num;
                int curLen=1;
                while (hashSet.contains(curNum+1)){
                    curNum++;
                    curLen++;
                }
                res=Math.max(res,curLen);
            }
        }
        return res;
    }


    /**
     * 使用路径压缩的并查集，用Map保存结点，如果它和它的邻居都存在，构造一条边连通
     * 时间空间复杂度为O(n)
     * @param nums
     * @return
     */
    public int longestConsecutiveUF(int[] nums){
        if (nums.length == 0) {
            return 0;
        }
        UnionFind unionFind=new UnionFind(nums);
        //如果邻居存在，构造与邻居的边，在union里面判断是否存在
        for(int num:nums){
            unionFind.union(num,num+1);
        }
        int res=1;
        //求出根结点距离自己的位置
        for(int num:nums){
            res=Math.max(res,unionFind.find(num)-num+1);
        }
        return res;

    }
    static class UnionFind{
        Map<Integer,Integer> ids;
        UnionFind(int[] nums){
            ids=new HashMap<>();
            for(int num:nums){
                ids.put(num,num);
            }
        }
        public void union(int v,int w){
            Integer vRoot = find(v);
            Integer wRoot = find(w);
            if(vRoot==null||wRoot==null){
                return;
            }
            if(!vRoot.equals(wRoot)) {
                ids.put(vRoot,wRoot);
            }
        }
        public Integer find(int v){
            //如果v不在Map内，则直接返回null
            if(!ids.containsKey(v)){
                return null;
            }
            int root=v;
            while (root!=ids.get(root)){
                root=ids.get(root);
            }
            while (v!=ids.get(v)){
                Integer temp=ids.get(v);
                ids.put(v,root);
                v=temp;
            }
            return root;
        }
    }

    public int longestConsecutive(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        Arrays.sort(nums);
        int res=1;
        int len=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                continue;
            }
            if(nums[i]==nums[i-1]+1){
                len++;
                res=Math.max(res,len);
            }else{
                len=1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = new LongestConsecutiveSequence_128().longestConsecutiveUF(new int[]{100,1,2,0,1});
        System.out.println(i);
    }
}
