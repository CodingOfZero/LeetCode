package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 确定有限状态机
 */
public class StringToInt_8 {
    class AutoState{
        public int sign=1;
        public long ans=0;
        private String state="start";
        private Map<String,String[]> stateTable=new HashMap<String,String[]>(){
            {
                put("start",new String[]{"start","sign","in_number","end"});
                put("sign",new String[]{"end","end","in_number","end"});
                put("in_number",new String[]{"end","end","in_number","end"});
                put("end",new String[]{"end","end","end","end"});
            }
        };

        public int get(char c){
            state=stateTable.get(state)[getCol(c)];
            if("in_number".equals(state)){
                ans=10*ans+c-'0';
                ans=sign==1? Math.min(ans,(long)Integer.MAX_VALUE):Math.min(ans,-(long)Integer.MIN_VALUE);
            }else if("sign".equals(state)){
                sign=c=='+'?1:-1;
            }
            return "end".equals(state)?-1:0;
        }

        private int getCol(char c){
            if(c==' '){
                return 0;
            }else if(c=='+'||c=='-'){
                return 1;
            }else if(Character.isDigit(c)){
                return 2;
            }else{
                return 3;
            }
        }

    }
    public int myAtoi(String s) {
        AutoState autoState=new AutoState();
        if(s==null||s.length()==0) return 0;
        char[] str = s.toCharArray();
        for(int i=0;i<s.length();i++){
            int i1 = autoState.get(str[i]);
            if(i1==-1) break;
        }
        return (int)(autoState.sign* autoState.ans);
    }
}
