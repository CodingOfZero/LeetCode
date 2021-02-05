package SlidingWindow;

/**
 * 为了求最大遵循原则
 *      当left固定时，right尽可能地大
 *      当right固定时，left尽可能地小
 */
public class GetEqualSubstringsWithinBudget_1208 {
    public static int equalSubstring(String s, String t, int maxCost) {
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int len=source.length;
        int[] diff=new int[len];
        //两个字符串之间的间距
        for(int i=0;i<len;i++){
            diff[i]=Math.abs(source[i]-target[i]);
        }
        int sum=0;
        int left=0,right=0;
        int maxLen=0;
        for(;right<source.length;right++){
            //开销之和
            sum+=diff[right];
            //当开销大于最大预算时，进行左移
            while (sum>maxCost){
                sum-=diff[left];
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);

        }
        return maxLen;
    }

    public static void main(String[] args) {
        int i = equalSubstring("abcd", "cdef", 3);
        System.out.println(i);
    }
}
