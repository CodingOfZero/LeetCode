package Dynamic;

import java.util.Arrays;

/**
 * 给定两个字符串str1和str2以及以下可以在str1上执行的操作。查找将“ str1”转换为“ str2”所需的最少操作次数
 *          插入
 *          去掉
 *          更换
 *示例：
 * 输入：str1 =“ sunday”，str2 =“ saturday”
 * 输出：3
 * 最后三个和第一个字符相同。我们基本上
 * 需要将“ un”转换为“ atur”。可以使用
 * 以下三个操作完成此操作。
 * 将'n'替换为'r'，插入t，插入a
 *
 * 如果两个字符串的最后一个字符相同。忽略最后一个字符并获得剩余字符串的计数。因此我们递归长度为m-1和n-1。
 * 否则（如果最后一个字符不同），我们考虑对“ str1”的所有操作，考虑对第一个字符串的最后一个字符的所有三个操作，
 * 递归计算这三个操作的最小开销，并取三个值中的最小值。
 *          插入：重复出现m和n-1
 *          删除：重复执行m-1和n
 *          替换：重复执行m-1和n-1
 *
 * 应用：比如显示词典中与给定单词拼写错误的单词接近的所有单词。
 */
public class EditDistance_72 {
    /**
     * Recursive solution
     * @param word1 str1
     * @param word2 str2
     * @return min edit distance
     */
    public int minDistance(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        return minDistanceHelper(chars1,chars2,word1.length(),word2.length());
    }
    private int minDistanceHelper(char[] word1,char[] word2,int m,int n){
        //if the first string is empty, the only option is to
        //insert all characters of second string into first
        if(m==0) {
            return n;
        }
        // If second string is empty, the only option is to
        // remove all characters of first string
        if(n==0) {
            return m;
        }
        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if(word1[m-1]==word2[n-1]) {
            return minDistanceHelper(word1,word2,m-1,n-1);
        } else{
            return 1+min(
                    //remove
              minDistanceHelper(word1, word2, m-1, n)
                    //insert
                    ,minDistanceHelper(word1, word2, m, n-1)
                    //replace
                    ,minDistanceHelper(word1, word2, m-1, n-1)
            );
        }
    }
    private int min(int x,int y,int z){
        return Math.min(x,Math.min(y,z));
    }

    /**
     * 时间：O(m*n) 空间 O(m*n)
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceDp(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int[][] table=new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0){
                    table[i][j]=j;
                }else if (j==0){
                    table[i][j]=i;
                }else if(word1.charAt(i-1)==word2.charAt(j-1)){
                    table[i][j]=table[i-1][j-1];
                }else{
                    table[i][j]=1+min(table[i-1][j],table[i][j-1],table[i-1][j-1]);
                }

            }
        }
        return table[m][n];
    }
    //先转char数组，再进行处理，Leetcode速度会比直接用charAt上快一点
//    private int minDistanceHelper(char[] word1,char[] word2,int m,int n){
//        int[][] table=new int[m+1][n+1];
//        for(int i=0;i<=m;i++){
//            for(int j=0;j<=n;j++){
//                if(i==0){
//                    table[i][j]=j;
//                }else if (j==0){
//                    table[i][j]=i;
//                }else if(word1[i-1]==word2[j-1]){
//                    table[i][j]=table[i-1][j-1];
//                }else{
//                    table[i][j]=1+min(table[i-1][j],table[i][j-1],table[i-1][j-1]);
//                }
//
//            }
//        }
//        return table[m][n];
//    }

    /**
     * 对上述分析，可以发现，实际上我们只要前一行的信息，因此可以对空间进行优化
     * 时间O(m*n) 空间O(m) 只需创建一个长度为2 x word1的DP数组
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceDpOptimize(String word1, String word2) {
        int len1=word1.length();
        int len2=word2.length();
        int[][] table=new int[2][len1+1];
        //初始化
        for(int[] t:table) {
            Arrays.fill(t,0);
        }
        //当第二个字符串为空时，需要移除第一个字符串，距离为word1的长度
        for(int i=0;i<=len1;i++){
            table[0][i]=i;
        }
        //word2
        for(int i=1;i<=len2;i++){
            //word1
            for(int j=0;j<=len1;j++){
                if(j==0){
                    table[i%2][j]=i;
                }else if(word2.charAt(i-1)==word1.charAt(j-1)){
                    table[i%2][j]=table[(i-1)%2][j-1];
                }else{
                    table[i%2][j]=1+min(table[(i-1)%2][j],table[i%2][j-1],table[(i-1)%2][j-1]);
                }

            }
        }
        return table[len2%2][len1];
    }
}
