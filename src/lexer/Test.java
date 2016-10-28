package lexer;

/**
 * Created by ZhangYF on 2016/10/27.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        REHandler reHandler = REHandler.getInstance();
//        Lex lexer = new Lex(reHandler);
//        NFAConstructor nfaConstructor = new NFAConstructor(lexer);
//
//        NFAPair pair = new NFAPair();
//        nfaConstructor.unionRE(pair);
//        NFAPrinter printer = new NFAPrinter();
//        printer.printNfa(pair.startNode);
//        DFAConstructor dfaConstructor = new DFAConstructor(pair.startNode);
//        dfaConstructor.nfa2dfa();
//        dfaConstructor.printDFATable();

        Process process = new Process();

        process.interpretor();
        process.printResult();

    }
}
