package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回在阈值范围内邻居最少的点，如果有多个，返回编号最大的点
 *  floyd算法，求出每个点之间的最短路径，统计在阈值范围内的点
 */
public class ThresholdDIstance_1334 {
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist=new int[n+1][n+1];
        //初始化
        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==j){
                    dist[i][j]=0;
                }else{
                    dist[i][j]=100000;
                }
            }
        }
        for(int[] edge:edges){
            dist[edge[0]+1][edge[1]+1]=edge[2];
            dist[edge[1]+1][edge[0]+1]=edge[2];
        }
        //Floyd算法
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                }
            }
        }
        List<int[]> ans=new ArrayList<>(n);
        for(int i=1;i<=n;i++){
            int cnt=0;
            for(int j=1;j<=n;j++){
                if(dist[i][j]<=distanceThreshold) cnt++;
            }
            ans.add(new int[]{i-1,cnt});
        }
        ans.sort((o1, o2) -> { return o1[1]!=o2[1] ?o1[1] - o2[1]:o2[0] - o1[0];
//            if (o1[1] == o2[1]) {
//                return o2[0] - o1[0];
//            } else {
//                return o1[1] - o2[1];
//            }
        });
        return ans.get(0)[0];
    }

    public static void main(String[] args) {
        int theCity = findTheCity(5, new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2);
        System.out.println(theCity);
    }
}
