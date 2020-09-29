package Tree;

/**
 * n个结点的二叉搜索树数量
 * Catalan数的应用
 */
public class UniqueBinarySearchTrees_96 {
    public int numTrees(int n) {
        //注意括号位置，是将求出的数进行int转型
        return (int)(binomialCoeff(2*n,n)/(n+1));
    }
    //求解C(n,k)
    private long binomialCoeff(int n,int k){
        long res=1;
        //C(n,k)=C(n,n-k)
        if(k>n-k) {
            k=n-k;
        }
        //计算[n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1]
        for(int i=0;i<k;i++){
            res*=(n-i);
            res/=(i+1);
        }
        return res;
    }
}
