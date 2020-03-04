import java.util.*;

public class Shift2DGrid_1260 {
    public static void main(String[] args){
        int[][] grid={{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}};
        int k=4;
        List<List<Integer>> e=shiftGrid(grid,k);
        System.out.println(e);
    }
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> list=new LinkedList<>();
        int len=grid[0].length*grid.length;
        int[] temp=new int[len];
        int m=k%len;
        int n=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++)
                temp[n++]=grid[i][j];
        }
        System.out.println(Arrays.toString(temp));
        //循环右移
        int lo=0,hi=len-m-1;
        int tempnum=0;
        while(lo<hi){
            tempnum=temp[lo];
            temp[lo]=temp[hi];
            temp[hi]=tempnum;
            lo++;
            hi--;
        }
        lo=len-m;
        hi=len-1;
        while(lo<hi){
            tempnum=temp[lo];
            temp[lo]=temp[hi];
            temp[hi]=tempnum;
            lo++;
            hi--;
        }
        lo=0;
        hi=len-1;
        while(lo<hi){
            tempnum=temp[lo];
            temp[lo]=temp[hi];
            temp[hi]=tempnum;
            lo++;
            hi--;
        }
        int temp_k=0;
        for(int i=0;i<grid.length;i++){
            List<Integer> t=new LinkedList<>();
            for(int j=0;j<grid[0].length;j++){
                t.add(temp[temp_k++]);
            }
            list.add(t);
        }
        return list;
    }

}
