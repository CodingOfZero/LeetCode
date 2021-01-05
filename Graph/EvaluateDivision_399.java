package Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision_399 {
    /**
     * floyd算法
     * @param equations  给定的算术除法
     * @param values    算术除法对应的值
     * @param queries   要求的算术除法
     * @return 结果数组
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //1.为除法算式里面每个不同的除数与被除数赋唯一下标
        int len=0;
        Map<String,Integer> hashmap=new HashMap<>();
        for(List<String> equation:equations){
            String a=equation.get(0);
            String b=equation.get(1);
            if(!hashmap.containsKey(a)){
                hashmap.put(a,len++);
            }
            if(!hashmap.containsKey(b)){
                hashmap.put(b,len++);
            }
        }
        //2.构造邻接矩阵
        double[][] matrix=new double[len][len];
        for(double[] item:matrix){
            Arrays.fill(item,-1.0);
        }
        for(int i=0;i<len;i++){
            matrix[i][i]=1.0;
        }
        int index=0;
        for(List<String> equation:equations){
            String a=equation.get(0);
            String b=equation.get(1);
            matrix[hashmap.get(a)][hashmap.get(b)]=values[index];
            matrix[hashmap.get(b)][hashmap.get(a)]=1.0/values[index];
            index++;
        }
        //3.Floyd算法
        for(int k=0;k<len;k++){
            //k为每次新加入的中间点
            //对矩阵中符合条件的每个元素尝试更新
            for(int i=0;i<len;i++){
                for(int j=0;j<len;j++){
                    if(matrix[i][k]>=0&&matrix[k][j]>=0){
                        matrix[i][j]=matrix[i][k]*matrix[k][j];
                    }
                }
            }
        }
        //4.查询表格，输出除法算式的结果
        double[] res=new double[queries.size()];
        index=0;
        for(List<String> query:queries){
            String a=query.get(0);
            String b=query.get(1);
            if(!hashmap.containsKey(a)
                    ||!hashmap.containsKey(b)
                    ||matrix[hashmap.get(a)][hashmap.get(b)]==-1){
                res[index++]=-1.0;
            }else{
                res[index++]=matrix[hashmap.get(a)][hashmap.get(b)];
            }

        }
        return res;
    }

    /**
     *并查集解法
     *      1.维护两个hash表， parent 表记录每个节点的祖先节点，dist 表记录该节点到祖先节点（root）的距离（dist[i] = i / root）
     *      2.初始化每个节点为一个集合 parent[i] = i，dist[i] = 1
     *      3.遍历 equations、values 数组，每次将两个不同集合合并
     *      4.遍历 queries 数组，如果查询的两个节点（a、b）都存在且处于同一个集合中则向结果数组（res）添加dist[a] / dist[b]，否则添加 -1.0
     *集合合并
     *      //假设  a-> ra   b->rb 要将a/b=v这条关系加入到集合中，可以给ra->b连一条线
     *      //假设 t = ra / b
     *      a / b = (a / ra) * (ra / b) = v
     *      又因为 a / ra = x1, ra / b = t
     *      可得 t = v / x1
     *
     */
    private Map<String,String> parent=new HashMap<>();
    //dist[a]=a/root
    private Map<String,Double> dist=new HashMap<>();
    private String find(String s){
        //如果s的根结点不是自身，不断找下去，找到这个集合的根结点
        if(!parent.get(s).equals(s)){
            //递归查找集合根结点
            String root=find(parent.get(s));
            //递归返回时，更新距离dist
            // a-->ra-->raa    a/raa= (a/ra)*(ra/raa)  其中a/ra=dist.get(a)  ra/raa=dist.get(ra)
            //double res=dist.get(s)*dist.get(parent.get(s));
            dist.replace(s,dist.get(s)*dist.get(parent.get(s)));
            //进行路径压缩
            parent.replace(s,root);
        }
        return parent.get(s);
    }
    public double[] calcEquationUF(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //1.维护两个hash表,然后进行初始化
        for(List<String> equation:equations){
            String a=equation.get(0);
            String b=equation.get(1);
            parent.put(a,a);
            parent.put(b,b);
            dist.put(a,1.0);
            dist.put(b,1.0);
        }
        //2.集合合并
        int index=0;
        for(List<String> equation:equations){
            String a=equation.get(0);
            String b=equation.get(1);
            String ra = find(a);
            //更新 ra 节点
            parent.replace(ra,b);
            double v=values[index++];
            dist.put(ra,v/dist.get(a));
        }
        //3.查询并输出结果
        double[] res=new double[queries.size()];
        index=0;
        for(List<String> query:queries){
            String a=query.get(0);
            String b=query.get(1);
            //如果没有这个结点或者不在一个集合
            if(!parent.containsKey(a)||!parent.containsKey(b)||!find(a).equals(find(b))){
                res[index++]=-1.0;
            }else{
                res[index++]=dist.get(a)/dist.get(b);
            }
        }
        return res;
    }
}
