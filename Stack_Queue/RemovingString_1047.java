package Stack_Queue;


/**
 * 删除字符串中所有相邻重复项，利用StringBuilder模拟栈
 */
public class RemovingString_1047 {
    public static String removeDuplicates(String S) {
        char[] str = S.toCharArray();
        StringBuilder sb=new StringBuilder();
        int top=-1;
        for(char c:str){
            if(top>=0&&c==sb.charAt(top)){
                sb.deleteCharAt(top);
                top--;
            }else {
                sb.append(c);
                top++;
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String abbaca = removeDuplicates("abbaca");
        System.out.println(abbaca);
    }
}
