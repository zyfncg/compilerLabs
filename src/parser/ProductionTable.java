package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ZhangYF on 2016/11/14.
 */
public class ProductionTable {
    private List<Production> pList = new ArrayList<>();

    private ProductionTable() {
        initPlist();
    }
    private static ProductionTable instance = new ProductionTable();
    public static ProductionTable getInstance(){
        return instance;
    }
    private void initPlist(){

        pList.add(new Production( Symbol.STMTS, Arrays.asList(Symbol.STMT,Symbol.STMTS)));
        pList.add(new Production( Symbol.STMTS, Arrays.asList(Symbol.EPSILON)));

        pList.add(new Production( Symbol.STMT, Arrays.asList(Symbol.ID,(int)('='),Symbol.JUDGE)));
        pList.add(new Production( Symbol.STMT, Arrays.asList(Symbol.IF,(int)('('),Symbol.JUDGE,(int)(')'),Symbol.BLOCK,Symbol.ELSE,Symbol.BLOCK)));
        pList.add(new Production( Symbol.STMT, Arrays.asList(Symbol.WHERE,(int)('('),Symbol.JUDGE,(int)(')'),Symbol.BLOCK)));

        pList.add(new Production( Symbol.BLOCK, Arrays.asList((int)('{'),Symbol.STMTS,(int)'}')));

        pList.add(new Production( Symbol.JUDGE, Arrays.asList(Symbol.EXPR,Symbol.JUDGE1)));
        pList.add(new Production( Symbol.JUDGE1, Arrays.asList((int)('<'),Symbol.EXPR)));
        pList.add(new Production( Symbol.JUDGE1, Arrays.asList((int)('>'),Symbol.EXPR)));
        pList.add(new Production( Symbol.JUDGE1, Arrays.asList(Symbol.EPSILON)));

        pList.add(new Production( Symbol.EXPR, Arrays.asList(Symbol.TERM,Symbol.EXPR1)));
        pList.add(new Production( Symbol.EXPR1, Arrays.asList(Symbol.EXPR2,Symbol.EXPR1)));
        pList.add(new Production( Symbol.EXPR1, Arrays.asList(Symbol.EPSILON)));
        pList.add(new Production( Symbol.EXPR2, Arrays.asList((int)'+',Symbol.TERM)));
        pList.add(new Production( Symbol.EXPR2, Arrays.asList((int)'-',Symbol.TERM)));

        pList.add(new Production( Symbol.TERM, Arrays.asList(Symbol.FACTOR,Symbol.TERM1)));
        pList.add(new Production( Symbol.TERM1, Arrays.asList(Symbol.TERM2,Symbol.TERM1)));
        pList.add(new Production( Symbol.TERM1, Arrays.asList(Symbol.EPSILON)));
        pList.add(new Production( Symbol.TERM2, Arrays.asList((int)'*',Symbol.FACTOR)));
        pList.add(new Production( Symbol.TERM2, Arrays.asList((int)'/',Symbol.FACTOR)));

        pList.add(new Production( Symbol.FACTOR, Arrays.asList(Symbol.NUM)));
        pList.add(new Production( Symbol.FACTOR, Arrays.asList(Symbol.ID)));

    }
}
