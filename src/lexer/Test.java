package lexer;

/**
 * Created by ZhangYF on 2016/10/27.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        REHandler reHandler = new REHandler();
        Lex lexer = new Lex(reHandler);
        NFAConstructor nfaConstructor = new NFAConstructor(lexer);

        NFAPair pair = new NFAPair();
        nfaConstructor.unionRE(pair);
        NFAPrinter printer = new NFAPrinter();
        printer.printNfa(pair.startNode);

    }
}
