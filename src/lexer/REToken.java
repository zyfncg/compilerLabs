package lexer;

import parser.Symbol;

/**
 * Created by ZhangYF on 2016/10/28.
 */
public class REToken extends Token {
    private String reName;
    public REToken(String reName,String lexem){
        super(TYPE.RE,lexem);
        this.reName = reName;
    }

    @Override
    public void printToken() {
        System.out.println("<"+reName+":"+lexem+">");
    }
    @Override
    public String getLexem(){
        return lexem;
    }

    @Override
    public int getToken() {
        int result = Symbol.getSymbol(reName);
        return result;
    }
}
