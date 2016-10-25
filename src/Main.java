import java.util.List;

/**
 * Created by st0001 on 2016/10/24.
 */
public class Main {

    public static void main(String[] args){
        List<Token> tagList;
        Lexer lex = new Lexer();
        tagList = lex.scan();
        for (Token token:tagList) {
            System.out.println(token.toString());
        }

    }


}
