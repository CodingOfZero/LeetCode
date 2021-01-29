package Sort;

import java.util.*;

public class MergeIntervals_56 {


    /**
     * 使用它
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length<=1){
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<Integer> list=new ArrayList<>();
        int left=intervals[0][0],right=intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<=right){
                right=Math.max(right,intervals[i][1]);
            }else{
                list.add(left);
                list.add(right);
                left=intervals[i][0];
                right=intervals[i][1];
            }
        }
        list.add(left);
        list.add(right);
        int[][] res=new int[list.size()/2][2];
        Iterator<Integer> iterator = list.iterator();
        for(int i=0;i<res.length;i++){
            for(int j=0;j<2;j++){
                if(iterator.hasNext()){
                    res[i][j]=iterator.next();
                }
            }
        }
        return res;
    }
    public int[][] mergeTwo(int[][] intervals){
        if(intervals.length==0){
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> list=new ArrayList<>();
        for (int[] interval : intervals) {
            int left = interval[0], right = interval[1];
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                list.get(list.size() - 1)[1] = Math.max(right, list.get(list.size() - 1)[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
