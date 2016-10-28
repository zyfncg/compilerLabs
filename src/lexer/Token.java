package lexer;

/**
 * Created by ZhangYF on 2016/10/28.
 */
public class Token {
    public enum TYPE{
        KEYWORD,RE,OP,DELIMITER,
    };
    TYPE type;
    String lexem;

    public Token(TYPE type, String lexem) {
        this.type = type;
        this.lexem = lexem;
    }
    public void printToken(){
        System.out.println("<"+lexem+">");
    }

    public String getLexem(){
        return lexem;
    }
}
