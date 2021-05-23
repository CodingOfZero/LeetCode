package Tree;

/**
 * 421. 数组中两个数的最大异或值
 *
 * 利用前缀树求解
 * 对于数组中任意两个数的异或结果，
 * 数的组合有：
 *          a0^a1
 *          a0^a2,a1^a2
 *          ...
 *          a0^a(n-1),a1^a(n-1) ... a(n-2)^a(n-1)
 *  将a0..a(n-2)加入前缀树，a1..a(n-1)分别在前缀树中进行查询，得到最大的异或值
 *  如：当用a2进行查询时，前缀树中包含a0,a1, 当a2二进制表示的某一个位为1，由异或性质相同为0，不同为1，选择前缀树值为0的节点，如果
 *  没有才选择值为1的节点。
 *  在此做个规定，左子树为0，右子树为1，方便实现；由于数据小于2^31，所有前缀的L为31
 */
public class MaximumXORofTwoNumbersinanArray_421 {
    class Trie{
        //0
        Trie left;
        //1
        Trie right;
    }
    private Trie root;
    private static int HIGH_TRIE=30;
    /**
     * 将num加入到前缀树中
     * @param num
     */
    public void add(int num){
        Trie cur=root;
        for(int i=HIGH_TRIE;i>=0;i--){
            int bit=(num>>i)&1;
            if(bit==0){
                if(cur.left==null){
                    cur.left=new Trie();
                }
                cur=cur.left;
            }else{
                if(cur.right==null){
                    cur.right=new Trie();
                }
                cur= cur.right;
            }
        }
    }

    public int getCurMax(int num){
        Trie cur=root;
        int x=0;
        for(int i=HIGH_TRIE;i>=0;i--){
            int bit=(num>>i)&1;
            if(bit==0){
                if(cur.right!=null){
                    cur= cur.right;
                    x=x*2+1;
                }else{
                    cur=cur.left;
                    x=x*2;
                }
            }else{
                if(cur.left!=null){
                    cur= cur.left;
                    x=x*2+1;
                }else{
                    cur=cur.right;
                    x=x*2;
                }
            }
        }
        return x;
    }


    /**
     * 时间复杂度为 O(nlogC) C 为前缀树的高度L
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        root=new Trie();
        int n=nums.length;
        int res=0;
        //用a1...a(n-1)在字典树中遍历，
        for(int i=1;i<n;i++){
            add(nums[i-1]);
            res=Math.max(res,getCurMax(nums[i]));
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumXORofTwoNumbersinanArray_421 test = new MaximumXORofTwoNumbersinanArray_421();
        int maximumXOR = test.findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70});
        System.out.println(maximumXOR);
    }
}
