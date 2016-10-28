package lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYF on 2016/10/28.
 */
public class TokenManager {
    private static List<String> keyList;
    private static List<String> opList;
    private static List<String> delimiterList;

    private List<Token> tokenList = new ArrayList<>();

    private static TokenManager instance = new TokenManager();

    public static TokenManager getInstance(){
        return instance;
    }
    private TokenManager(){
        REHandler reHandler = REHandler.getInstance();
        keyList = reHandler.getKeyList();
        opList = reHandler.getOpList();
        delimiterList = reHandler.getDelimiterList();

        System.out.println(keyList);
        System.out.println(opList);
        System.out.println(delimiterList);

    }
    public void addToken(Token token){
        tokenList.add(token);
    }

    public void printAllToken(){
        for (int i = 0; i <tokenList.size() ; i++) {
            Token token = tokenList.get(i);
            token.printToken();
        }
    }

    public boolean isKey(String id){
        for(String key:keyList){
            if(key.equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean isOperator(String str){
        for(String op:opList){
            if(op.equals(str)){
                return true;
            }
        }
        return false;
    }
    public boolean isDelimiter(String str){
        for(String del:delimiterList){
            if(del.equals(str)){
                return true;
            }
        }
        return false;
    }


}
