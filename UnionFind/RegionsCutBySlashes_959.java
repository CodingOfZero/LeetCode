package UnionFind;

/**
 * 沿着一个网格的两条对角线，能够将正方形切分成四个三角形。如果网格上的字符为 /，
 * 则右下角的两个三角形会与左上角的两个三角形分隔开；
 * 同理，如果字符为 \，则右上角的两个三角形会和左下角的两个三角形分隔开。
 * 如果将每个三角形看作为一张图上的节点，则网格中的一个共边区域，就相当于图中的一个连通分量。
 * 因此，利用并查集求解连通分量的数目。
 * 设网格为 n×n 大小，则图中有 4*n^2个节点，每个格子对应其中的 4个节点。
 * 对于每个格子而言，考虑当前位置的字符：
 *      如果为空格，则该格子对应的 4个节点应当同属于同一区域，因此在它们之间各连接一条边；
 *      如果为字符 /，则将左上角的两个格子连接一条边，并将右下角的两个格子连接一条边；
 *      如果为字符 \，则将右上角的两个格子连接一条边，并将左下角的两个格子连接一条边。
 * 到目前位置，我们只考虑了一个格子内部的情况。但同时，不难观察到下面两点：
 *      一个格子中最下方的三角形，必然和下面的格子（如果存在）中最上方的三角形连通；
 *      一个格子中最右方的三角形，必然和右边的格子（如果存在）中最左方的三角形连通。
 * 因此，我们还需要根据上面两条规则，在相邻格子的相应三角形中间，再连接边。
 * 将一个格子的四个三角形标记为 0,1,2,3
 *
 * 将三角形视为并查集的一个点
 */
public class RegionsCutBySlashes_959 {
    /**
     * 时间复杂度为 O(n^2logn)  空间为O(n*n)
     */
    public int regionsBySlashes(String[] grid) {
        int len=grid.length;
        UnionFind uf=new UnionFind(4*len*len);
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                int idx=i*len+j;
                //合并单元格最下方三角形与下面单元格最上方三角形
                if(i<len-1){
                    //当前格子为第i个，最下方格子下标为idx+n
                    int bottom=idx+len;
                    uf.merge(idx*4+2,bottom*4);
                }
                //合并单元格最右边三角形与右边单元格最左边三角形
                if(j<len-1){
                    int right=idx+1;
                    uf.merge(idx*4+1,right*4+3);
                }
                if('/'==grid[i].charAt(j)){
                    //分别合并右下角与左上角各两个三角形
                    uf.merge(idx*4+1,idx*4+2);
                    uf.merge(idx*4,idx*4+3);
                }else if('\\'==grid[i].charAt(j)){
                    //分别合并左下角与右上角各两个三角形
                    uf.merge(idx*4+2,idx*4+3);
                    uf.merge(idx*4,idx*4+1);
                }else{
                    //如果为空格，则四个三角形连通合并为一个连通分量
                    uf.merge(idx*4,idx*4+1);
                    uf.merge(idx*4+1,idx*4+2);
                    uf.merge(idx*4+2,idx*4+3);
                }
            }
        }
        return uf.getCount();
    }
    static class UnionFind{
        private int[] ids;
        private int count;
        UnionFind(int n){
            ids=new int[n];
            count=n;
            for(int i=0;i<n;i++){
                ids[i]=i;
            }
        }
        public int find(int v){
            int root=v;
            while (root!=ids[root]){
                root=ids[root];
            }
            while (v!=root){
                int temp=ids[v];
                ids[v]=root;
                v=temp;
            }
            return root;
        }
        public void merge(int v,int w){
            int vRoot=find(v);
            int wRoot=find(w);
            if(vRoot==wRoot) return;
            ids[vRoot]=wRoot;
            count--;
        }
        public int getCount(){return count;}
    }

}
