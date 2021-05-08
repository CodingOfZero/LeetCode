package Backtrack;


import java.util.Arrays;

public class FindMinimumTimetoFinishAllJobs_1723 {
    private int maxWorkingTime;
    public int minimumTimeRequired(int[] jobs, int k) {
        int n=jobs.length;
        if(n<=k){
            return Arrays.stream(jobs).max().getAsInt();
        }
        maxWorkingTime=Integer.MAX_VALUE;
        dfsAccept(jobs,0,new int[k],0,k,0);
        return maxWorkingTime;
    }

    /**
     * 错误写法，外层for不需要，递归已经满足含义
     */
    private void dfsError(int[] jobs, int start,int[] time) {
        if(start==jobs.length){
            int maxTime= Arrays.stream(time).max().getAsInt();
            if(maxTime<maxWorkingTime){
                maxWorkingTime=maxTime;
            }
            return;
        }
        //error
        for(int i=start;i<jobs.length;i++){
            int job=jobs[i];
            for(int j=0;j<time.length;j++){
                time[j]+=job;
                dfsError(jobs,i+1,time);
                time[j]-=job;
            }
        }
    }

    /**
     * 时间复杂度为 k^n, 题目数据为12，则范围为12^12,会超10^8,会超时
     */
    private void dfs(int[] jobs, int start,int[] time,int maxTime) {
        if(maxTime>=maxWorkingTime) return;
        if(start==jobs.length){
            if(maxTime<maxWorkingTime){
                maxWorkingTime=maxTime;
            }
            return;
        }
        int job=jobs[start];
        for(int j=0;j<time.length;j++){
            time[j]+=job;
            dfs(jobs,start+1,time,Math.max(maxTime,time[j]));
            time[j]-=job;
        }
    }

    /**
     * 由于递归时第一种情况我们将所有任务都分配给第0个工人，所以第一次更新的maxTime是最差情况
     * 所以maxTime>=maxWorkingTime剪枝被弱化
     * 我们可以改变搜索策略，当有空闲工人时优先将工作分配给它们，可以增强剪枝效果
     * 注意递归树还是一样的，仅仅改变了搜索顺序
     */
    private void dfsAccept(int[] jobs, int start,int[] time,int maxTime,int k,int workerIndex) {
        if(maxTime>=maxWorkingTime) return;
        if(start==jobs.length){
            maxWorkingTime=maxTime;
            return;
        }

        int job=jobs[start];
        //有未分配任务的工人
        if(workerIndex<k){
            time[workerIndex]=job;
            dfsAccept(jobs, start+1, time, Math.max(maxTime,time[workerIndex]), k, workerIndex+1);
            //复原
            time[workerIndex]=0;
        }
        for(int j=0;j<workerIndex;j++){
            time[j]+=job;
            dfsAccept(jobs,start+1,time,Math.max(maxTime,time[j]),k,workerIndex);
            time[j]-=job;
        }
    }
    public static void main(String[] args) {
        FindMinimumTimetoFinishAllJobs_1723 jobs = new FindMinimumTimetoFinishAllJobs_1723();
        long start = System.currentTimeMillis();
        int i = jobs.minimumTimeRequired(new int[]{254,256,256,254,251,256,254,253,255,251,251,255}, 10);
        //int i = jobs.minimumTimeRequired(new int[]{3,2,3}, 3);
        long end = System.currentTimeMillis();
        System.out.println(i);
        System.out.println("time: "+(end-start)+"ms");
    }
}
