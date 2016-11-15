package parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangYF on 2016/11/14.
 */
public class Symbol {

    public static final int NO_TERMINAL_START = 300;
    public static final int NO_TERMINAL_NUM = 12;

    public static final int EPSILON = -1;

    public static final int END = 256;
    public static final int IF = 257;
    public static final int ELSE = 258;
    public static final int WHERE = 259;
    public static final int ID = 260;
    public static final int NUM = 261;



    public static final int STMTS = 301;
    public static final int STMT = 302;
    public static final int JUDGE = 303;
    public static final int JUDGE1 = 304;
    public static final int BLOCK = 305;
    public static final int EXPR = 306;
    public static final int EXPR1 = 307;
    public static final int EXPR2 = 308;
    public static final int TERM = 309;
    public static final int TERM1 = 310;
    public static final int TERM2 = 311;
    public static final int FACTOR = 312;


    private Map<Integer,String> symbolMap = new HashMap<>();

    public Symbol() {

        symbolMap.put(EPSILON,"epsilon");
        symbolMap.put(IF,"if");
        symbolMap.put(ELSE,"else");
        symbolMap.put(WHERE,"where");
        symbolMap.put(ID,"id");
        symbolMap.put(NUM,"num");
        symbolMap.put(STMTS,"stmts");
        symbolMap.put(STMT,"stmt");
        symbolMap.put(JUDGE,"judge");
        symbolMap.put(JUDGE1,"judge1");
        symbolMap.put(BLOCK,"block");
        symbolMap.put(EXPR,"expr");
        symbolMap.put(EXPR1,"expr1");
        symbolMap.put(EXPR2,"expr2");
        symbolMap.put(TERM,"TERM");
        symbolMap.put(TERM1,"term1");
        symbolMap.put(TERM2,"term2");
        symbolMap.put(FACTOR,"factor");

    }

    public int getSymbol(String str){
        for (int key: symbolMap.keySet()) {
            String sym = symbolMap.get(key);
            if(sym.equals(str)){
                return key;
            }
        }
        return 0;
    }
    public String getName(int key){
        return symbolMap.get(key);
    }


}
