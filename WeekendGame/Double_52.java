package WeekendGame;

import java.util.*;

public class Double_52 {
    /**
     * 5742. 将句子排序
     */
    public String sortSentence(String s) {
        String[] s1 = s.split(" ");
        //Arrays.sort(s1, (o1, o2) -> o1.charAt(o1.length()-1)-o2.charAt(o2.length()-1));
        Arrays.sort(s1,Comparator.comparingInt(o->o.charAt(o.length()-1)));
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<s1.length;i++){
            String item=s1[i];
            ans.append(item, 0, item.length()-1);
            if(i!=s1.length-1){
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    /**
     * 5743. 增长的内存泄露
     * 时间复杂度为 O(根号下 memory1+memory2)
     *
     */
    public int[] memLeak(int memory1, int memory2) {
        int i=1;
        while (memory1>=i||memory2>=i){
            if(memory1>=memory2){
                memory1-=i;
            }else{
                memory2-=i;
            }
            i++;
        }
        return new int[]{i,memory1,memory2};
    }

    /**
     * 5744. 旋转盒子
     * @param box
     * @return
     */
    public char[][] rotateTheBox(char[][] box) {
        int m=box.length,n=box[0].length;
        char[][] res=new char[n][m];
        for(int i=0;i<m;i++){
            ArrayList<Integer> tmp=new ArrayList<>();
            tmp.add(-1);
            for(int j=0;j<n;j++){
                if(box[i][j]=='*'){
                    tmp.add(j);
                }
            }
            tmp.add(n);
            for(int j=1;j<tmp.size();j++){
                int cur=tmp.get(j),pre=tmp.get(j-1);
                if(cur-pre>1){
                    helper(box,i,pre+1,cur);
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[j][m-1-i]=box[i][j];
            }
        }

        return res;
    }
    //[start,end)
    private void helper(char[][] box,int row,int start,int end){
        int tone=0;
        for(int i=start;i<end;i++){
            if(box[row][i]=='#'){
                tone++;
            }
        }
        int index=end-1;
        for(int i=tone;i>0;i--){
            box[row][index]='#';
            index--;
        }
        while (index>=start){
            box[row][index]='.';
            index--;
        }
    }

    /**
     * 5212. 向下取整数对和
     * 超时TLE
     */
    public int sumOfFlooredPairsTLE(int[] nums) {
        return dfsTLE(nums,new LinkedList<>(),new HashMap<>());
    }
    private int  dfsTLE(int[] nums,  List<Integer> cache,Map<Integer,Integer> tmp){
        if(cache.size()==2){
            return cache.get(0)/cache.get(1);
        }
        if(cache.size()==1){
            if(tmp.containsKey(cache.get(0))){
                return tmp.get(cache.get(0));
            }
        }

        int res=0;
        for (int i=0;i<nums.length;i++) {
            if(cache.size()==1&&cache.get(0)<nums[i]){
                continue;
            }
            cache.add(nums[i]);
            res += dfsTLE(nums,cache,tmp);
            cache.remove(cache.size() - 1);
        }
        if(cache.size()==1){
            tmp.put(cache.get(0),res);
        }
        return res;
    }

    /**
     * 计算取整之和，之前的思路是分别求x和y,求解x/y结果，换个思路
     * 固定y
     * 对于floor函数
     *      当x处于[y,2y-1]时，得到的结果均为1
     *      当x处于[2y,3y-1]时，结果均为2；
     * 因此，统计每个元素的个数，对于每个元素，计算区间内nums的元素，总和=每个元素的个数*区间内nums的元素*区间次序
     */
    public int sumOfFlooredPairs(int[] nums) {
        int MOD=(int)1e9+7;
        //1.计算最大值
        int maxNum=nums[0];
        for(int num:nums){
            if(num>maxNum){
                maxNum=num;
            }
        }
        //2.计数数组
        int[] cnt=new int[maxNum+1];
        for(int num:nums){
            cnt[num]++;
        }
        //3.前缀
        for(int i=1;i<cnt.length;i++){
            cnt[i]+=cnt[i-1];
        }
        //极限运算100000*100000会溢出，所以运算期间使用long
        long ans=0;
        //4.计算区间
        for(int i=1;i<=maxNum;i++){
            long cur=cnt[i]-cnt[i-1];
            if(cur==0){
                continue;
            }
            for(int j=i;j<=maxNum;j+=i){
                long sectionCnt=cnt[Math.min(j+i-1,maxNum)]-cnt[j-1];
                ans=(ans+cur*sectionCnt*(j/i))%MOD;
            }
        }
        return (int)ans;
    }



    public static void main(String[] args) {
        Double_52 test4 = new Double_52();
        String res = test4.sortSentence("sentence4 a3 is2 This1");
        System.out.println(res);

        int[] ints = test4.memLeak(8, 11);
        for(int i:ints){
            System.out.println(i);
        }

        char[][] chars = test4.rotateTheBox(new char[][]{{'#', '.', '*', '.'},
                {'#', '#', '*', '.'}});

        for(char[] t:chars){
            for(char s:t){
                System.out.printf("%c ",s);
            }
            System.out.println();
        }

        int i = test4.sumOfFlooredPairs(new int[]{
                2, 5, 9
        });
        int i1 = test4.sumOfFlooredPairs(new int[]{7, 7, 7, 7, 7, 7, 7});
        System.out.println(i);
        System.out.println(i1);
    }
}
