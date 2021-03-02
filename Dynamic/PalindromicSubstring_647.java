package Dynamic;

/**
 * 求回文子串数
 * 只要起始位置与结束位置不同，即使由相同字符组成，也算一个回文子串
 */
public class PalindromicSubstring_647 {
    public static int countSubstrings(String s) {
        int len = s.length();
        int[][] results=new int[len][len];
        for(int i=0;i<len;i++){
            results[i][i]=1;
        }
        char[] charArray = s.toCharArray();
        //填表时要无后效性，不能要查的数还没算出来
        for(int j=1;j<len;j++){
            for(int i=0;i<j;i++){
                if(charArray[i]!=charArray[j]){
                    results[i][j]=0;
                }else{
                    //当不够成区间时，单个字符也是回文
                    if(j-i<3){
                        results[i][j]=1;
                    }else{
                        results[i][j]=results[i+1][j-1];
                    }
                }
            }
        }
        int count=0;
        for(int[] result: results){
            for(int item: result){
                if(item==1){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int aaa = countSubstrings("aaa");
        System.out.println(aaa);
    }
}
