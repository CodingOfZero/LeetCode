package BinarySearch;


import java.util.Arrays;
import java.util.Collections;

/**
 * 使用二分查找的方法寻找最小的存在可行方案的 limit 值。也可用回溯剪枝，具体见Backtrack包下该题
 */
public class FindMinimumTimetoFinishAllJobs_1723 {

    public int minimumTimeRequired(int[] jobs, int k) {
        int n=jobs.length;
        if(n<=k){
            int res=jobs[0];
            for(int job:jobs){
                res=Math.max(res,job);
            }
            return res;
        }
        //优先分配时才较长任务
        Arrays.sort(jobs);
        //逆序排序
        int l=0,r=n-1;
        while (l<r){
            int temp=jobs[r];
            jobs[r]=jobs[l];
            jobs[l]=temp;
            l++;
            r--;
        }
        int low=0,high=Arrays.stream(jobs).sum();
        low=high/n-1;
        while (low<high){
            int mid=low+(high-low)/2;
            if(findSolution(jobs,k,mid)){
                high=mid;
            }else{
                low=mid+1;
            }

        }
        return low;
    }

    private boolean findSolution(int[] jobs, int k, int limit) {
        int[] workLoads=new int[k];
        return dfs(jobs,workLoads,0,limit);
    }

    private boolean dfs(int[] jobs, int[] workLoads, int start, int limit) {
        if(start>=jobs.length){
            return true;
        }
        int cur=jobs[start];
        for(int i=0;i<workLoads.length;i++){
            if(workLoads[i]+cur<=limit){
                workLoads[i]+=cur;
                if(dfs(jobs, workLoads, start+1, limit)){
                    return true;
                }
                workLoads[i]-=cur;
            }
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作,
            // 如果分配给工人 i不能成功完成分配任务，那么分配给工人 i+1 也一样无法完成
            if(workLoads[i]==0){
                break;
            }
        }
        return false;
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
