package Backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * 正则表达式
 * 方法一：非确定性有限状态机
 */
public class RegularExpressionMatching_10 {
    //匹配转换
    private char[] re;
    //epsilon转换
    private DirectedGraph graph;
    //状态数量
    private int staNum;

    public boolean isMatch(String s, String p) {
        ArrayList<Integer> pc=new ArrayList<>();
        NFA(p);

        //初始化，从状态0通过epsilon转换可达的状态
        graph.dfs(0);
        for(int v=0;v<graph.pointNum();v++){
            if(graph.isMarked(v)){
                pc.add(v);
            }
        }
        char[] chars=s.toCharArray();
        for(int i=0;i<s.length();i++){
            ArrayList<Integer> match=new ArrayList<>();
            for(int v:pc){
                if(v<staNum){
                    if(re[v]==chars[i]||re[v]=='.'){
                        //将下一个状态放入匹配集中
                        match.add(v+1);
                    }
                }
            }
            pc=new ArrayList<>();
            //重置
            graph.reset();
            //多点可达
            graph.dfs(match);
            for(int v=0;v<graph.pointNum();v++){
                if(graph.isMarked(v)){
                    pc.add(v);
                }
            }
        }
        for(int v:pc){
            if(v==staNum){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据正则表达式字符串构造NFA
     * @param regexp 正则表达式
     */
    public void NFA(String regexp){
        re=regexp.toCharArray();
        staNum=re.length;
        graph=new DirectedGraph(staNum+1);
        for(int i=0;i<staNum;i++){
            if(i<staNum-1&&re[i+1]=='*'){
                graph.addEdge(i,i+1);
                graph.addEdge(i+1,i);
            }
            if(re[i]=='*'){
                graph.addEdge(i,i+1);
            }
        }
    }
    class DirectedGraph{
        private final int v;
        private boolean[] marked;
        private ArrayList<ArrayList<Integer>> adj;

        public DirectedGraph(int v){
            this.v=v;
            marked=new boolean[v];
            Arrays.fill(marked,false);
            adj=new ArrayList<>();
            for(int i=0;i<v;i++){
                adj.add(new ArrayList<>());
            }
        }
        public int pointNum(){
            return v;
        }
        public void addEdge(int v,int w){
            adj.get(v).add(w);
        }
        public void dfs(int v){
            marked[v]=true;
            for(int w:adj.get(v)){
                if(!isMarked(w)){
                    dfs(w);
                }
            }
        }
        public void dfs(Iterable<Integer> sources){
            for(Integer v:sources){
                if(!isMarked(v)){
                    dfs(v);
                }
            }
        }
        public boolean isMarked(int v){
            return marked[v];
        }
        public void reset(){
            Arrays.fill(marked,false);
        }
    }


    public static void main(String[] args) {
        RegularExpressionMatching_10 match = new RegularExpressionMatching_10();
        boolean res = match.isMatch("aa", "a*");
        System.out.println(res);
    }
}
