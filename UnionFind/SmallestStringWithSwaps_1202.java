package UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 交换具有传递性
 * 思路：
 *  1.使用并查集将可交换索引对连通，（用并查集中存放索引）
 *  2.对于同一个连通分量内的字符而言，进行排序，（使用优先队列）
 *  3.遍历每个位置对应的根，选出到字典序最小元素，（hashmap）
 */
public class SmallestStringWithSwaps_1202 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len=s.length();
        //1.将任意交换的结点对输入并查集
        UnionFind unionFind = new UnionFind(len);
        for(List<Integer> pair:pairs){
            unionFind.union(pair.get(0),pair.get(1));
        }
        //2.构建映射关系
        //key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap=new HashMap<>();
        char[] charArrayS = s.toCharArray();
        for(int i=0;i<len;i++){
            int root = unionFind.find(i);
            if(hashMap.containsKey(root)){
                hashMap.get(root).offer(charArrayS[i]);
            }else{
                //不存在，新建
                hashMap.computeIfAbsent(root,key->new PriorityQueue<>()).offer(charArrayS[i]);
            }
        }
        //3.重组字符串
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<len;i++){
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }
    static class UnionFind{
        private int[] ids;
        public UnionFind(int n){
            ids=new int[n];
            for(int i=0;i<n;i++){
                ids[i]=i;
            }
        }
        public void union(int v,int w){
            int vRoot = find(v);
            int wRoot = find(w);
            ids[vRoot]=wRoot;
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
    }
}
