package UnionFind;

/**
 * 计算机网络的连通性问题
 *
 */
public class NumberofOperationstoMakeNetworkConnected_1319 {
    private int[] id;//分量id，以触点为索引
    private int count;//分量数量
    public  void init(int N){
        id=new int[N];
        count=N;
        for(int i=0;i<N;i++)
            id[i]=i;
    }
    public int makeConnected(int n, int[][] connections) {
        if(connections.length<n-1) return -1;
        init(n);
        int p=0,q=0;
        for(int i=0;i<connections.length;i++){
            p=connections[i][0];
            q=connections[i][1];
            union(p,q);
        }
        return count-1;
    }
    public  int find(int p){//检查节点的同时将它们直接链接到根节点
        int root=p;
        int temp=0;
        while (root!=id[root]) root=id[root];
        //将从p到根节点的路径上的每个触点都连接到根节点
        while(p!=root){
            temp=id[p];
            id[p]=root;
            p=temp;
        }
        return root;
    }
    public  void union(int p,int q){
        int pRoot=find(p);
        int qRoot=find(q);
        if(pRoot==qRoot) return;
        id[pRoot]=qRoot;
        count--;
    }
}
