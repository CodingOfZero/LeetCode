package String;

/**
 * 两个字符串相加
 */
public class AddString_415 {
    public static String addStrings(String num1, String num2) {
        int add =0;
        int i=num1.length()-1,j=num2.length()-1;
        StringBuilder sb=new StringBuilder();
        while(i>=0||j>=0||add!=0){
            int x=i>=0?num1.charAt(i)-'0':0;
            int y=j>=0?num2.charAt(j)-'0':0;
            int result=x+y+add;
            sb.append(result%10);
            add=result/10;
            i--;
            j--;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = addStrings("1234", "1236");
        System.out.println(s);
    }
}
