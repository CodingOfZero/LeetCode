package UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * 移除同行同列的石头，返回可移除的最大数量
 * 位于一行或一列的石头，可以构成一个连通分量，位于同一连通分量的石头，可以通过遍历将这个连通分量
 * 中的石头移除到只剩1个为止，所以最大可移除数量，就是所有石头的总数-连通分量个数
 *
 * 可以按点合并或者按边合并，按点即石头的个数，按边即石头的横坐标与纵坐标
 * 以下为按边合并，由于要区分开横纵坐标，题目中提到坐标范围为0-10000,
 * 所以可将每个x坐标加上10000或者减去10000
 * 并查集底层使用HashMap实现
 */
public class RemoveStone_947 {
    public int removeStones(int[][] stones) {
        UnionFind unionFind=new UnionFind();
        for(int[] stone:stones){
            int row=stone[0],col=stone[1];
            unionFind.union(row+10000,col);
        }
        return stones.length-unionFind.getCount();
    }
    static class UnionFind{
        Map<Integer,Integer> parent;
        int count;
        UnionFind(){
            parent=new HashMap<>();
            count=0;
        }
        public int find(int v){
            if(!parent.containsKey(v)){
                parent.put(v,v);
                count++;
            }
            if(v!=parent.get(v)){
                parent.put(v,find(parent.get(v)));
            }
            return parent.get(v);
        }
        public void union(int v,int w){
            int vRoot = find(v);
            int wRoot = find(w);
            //相等，直接返回，连通分量不变
            if(vRoot==wRoot) {
                return;
            }
            parent.put(vRoot,wRoot);
            count--;
        }
        public int getCount(){
            return count;
        }
    }
}
