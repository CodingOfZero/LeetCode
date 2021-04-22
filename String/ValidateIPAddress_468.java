package String;

public class ValidateIPAddress_468 {
    private static final String IPv4="IPv4";
    private static final String IPv6="IPv6";
    private static final String Neither="Neither";

    private boolean validIPv4(String ip) {
        //-1表示如果末尾还有.还会继续分割
        String[] segment = ip.split("\\.",-1);
//        if(segment.length!=4) return false;
        for(String s:segment){
            if(s.length()==0||s.length()>3) return false;
            //前导零处理
            if(s.charAt(0)=='0'&&s.length()!=1){
                return false;
            }
//            int num=0;
//            for(int i=0;i<s.length();i++){
//                if(Character.isDigit(s.charAt(i))){
//                    num=10*num+ s.charAt(i)-'0';
//                }else{
//                    return false;
//                }
//            }
//            if(num>255){
//                return false;
//            }
            for(char ch:s.toCharArray()){
                if(!Character.isDigit(ch)){
                    return false;
                }
            }
            if(Integer.parseInt(s)>255) return false;
        }
        return true;
    }

    private boolean validIPv6(String ip) {
        String[] segments = ip.split(":",-1);
        String hexDigits="0123456789abcdefABCDEF";
        for(String s:segments){
            if(s.length()==0||s.length()>4) return false;
            //使用计算判断
//            int sum=0;
//            for(int i=0;i<s.length();i++){
//                int k=0;
//                if(Character.isDigit(s.charAt(i))){
//                    k=s.charAt(i)-'0';
//                }else{
//                    k=(Character.toUpperCase(s.charAt(i))-'A')+10;
//                    if(k>15) return false;
//                }
//                sum=16*sum+k;
//            }
//            if(sum>65535){
//                return false;
//            }
            //判断字符范围
            for(char ch:s.toCharArray()){
                if(hexDigits.indexOf(ch)==-1){
                    return false;
                }
            }
        }
        return true;
    }

    public String validIPAddress(String IP) {
        //1.判断有3个点，判断是否为IPV4
        if(IP.chars().filter(ch->ch =='.').count()==3){
            return validIPv4(IP)?IPv4:Neither;
        }else if(IP.chars().filter(ch->ch==':').count()==7){
            return validIPv6(IP)?IPv6:Neither;
        }else{
            return Neither;
        }
        //2.判断7个冒号，判断IPv6
        //3.其他返回
    }
    public static void main(String[] args) {
        ValidateIPAddress_468 test = new ValidateIPAddress_468();
        String s = test.validIPAddress("g:f:f:f:f:f:f:g");
        System.out.println(s);


    }
}
