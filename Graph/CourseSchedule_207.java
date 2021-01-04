package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定课程总量以及它们的先决条件，判断是否能够学习完所有课程
 * 有向图判断有没有环
 */
public class CourseSchedule_207 {

    //BFS 时间与空间O(e+v)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites==null||prerequisites.length==0) {
            return true;
        }
        List<List<Integer>> adj=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        int[] indegree=new int[numCourses];
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        //邻接表
        for(int[] cp:prerequisites){
            //记录入度
            indegree[cp[0]]++;
            adj.get(cp[1]).add(cp[0]);
        }
        //BFS
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int pre=queue.poll();
            numCourses--;
            for(int cur:adj.get(pre)){
                if(--indegree[cur]==0){
                    queue.add(cur);
                }
            }
        }
        return numCourses==0;
    }




    //使用深度遍历,时间与空间O(e+v)
    //flag=0：表示没有被访问
    //flag=1：表示本轮DFS已被访问
    //flag=-1：表示其他DFS访问
    public boolean canFinishDepth(int numCourses, int[][] prerequisites) {
        if(prerequisites==null||prerequisites.length==0) {
            return true;
        }
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        int[] flags=new int[numCourses];
        for(int[] cp:prerequisites){
            adj.get(cp[1]).add(cp[0]);
        }
        //DFS
        for(int i=0;i<numCourses;i++){
            if(!dfs(adj,flags,i)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adj, int[] flags, int i) {
        //表示已访问过
        if(flags[i]==-1){
            return true;
        }
        if(flags[i]==1){
            return false;
        }
        flags[i]=1;
        for(Integer j:adj.get(i)){
            if(!dfs(adj, flags, j)){
                return false;
            }
        }
        flags[i]=-1;
        return true;
    }

}
