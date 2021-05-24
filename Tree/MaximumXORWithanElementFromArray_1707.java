package Tree;

import java.util.Arrays;

/**
 * 与数组中元素的最大异或值
 * 与421解法类似，也是前缀树的应用，仅添加一个限制条件需要在不超过查询数的范围内找最大异或值
 */
public class MaximumXORWithanElementFromArray_1707 {
    class Trie{
        //0
        Trie left;
        //1
        Trie right;
    }
    class Query{
        //对应的下标
        int index;
        int xi;
        int mi;
        Query(int index,int xi,int mi){
            this.index=index;
            this.xi=xi;
            this.mi=mi;
        }
    }
    private Trie root;
    private static int HIGH_POS=30;

    /**
     * 加入前缀树
     */
    public void add(int cur){
        Trie p=root;
        for(int i=HIGH_POS;i>=0;i--){
            int bit=(cur>>i)&1;
            if(bit==0){
                if(p.left==null){
                    p.left=new Trie();
                }
                p=p.left;
            }else{
                if(p.right==null){
                    p.right=new Trie();
                }
                p=p.right;
            }
        }
    }

    /**
     * 得到查询结果
     * @param num
     * @return
     */
    public int getCurMax(int num){
        Trie p=root;
        if(root.left==null&&root.right==null) {
            return -1;
        }
        int x=0;
        for(int i=HIGH_POS;i>=0;i--){
            int bit=(num>>i)&1;
            if(bit==0){
                if(p.right!=null){
                    p=p.right;
                    x=x*2+1;
                }else {
                    p=p.left;
                    x=x*2;
                }
            }else{
                if(p.left!=null){
                    p=p.left;
                    x=x*2+1;
                }else{
                    p=p.right;
                    x=x*2;
                }
            }
        }
        return x;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n=queries.length;
        Query[] query=new Query[n];
        for(int i=0;i<n;i++){
            query[i]=new Query(i,queries[i][0],queries[i][1]);
        }
        Arrays.sort(nums);
        Arrays.sort(query,(a,b)->Integer.compare(a.mi,b.mi));
        int[] res=new int[n];

        int index=0;
        root=new Trie();

        for(int i=0;i<n;i++){
            int xi=query[i].xi,mi=query[i].mi;
            while (index<nums.length&&nums[index]<=mi){
                add(nums[index]);
                index++;
            }
            res[query[i].index]=getCurMax(xi);
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumXORWithanElementFromArray_1707 test = new MaximumXORWithanElementFromArray_1707();
        int[] ints = test.maximizeXor(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}});
        for(int x:ints){
            System.out.printf("%d ",x);
        }

    }
}
