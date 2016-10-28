package lexer;

/**
 * Created by ZhangYF on 2016/10/28.
 */
public class Process {
    private InputStr input;
    private DFAMatch dfaMatch;
    private TokenManager tokenManager;

    public Process() throws Exception{
        REHandler reHandler = REHandler.getInstance();
        Lex lexer = new Lex(reHandler);
        NFAConstructor nfaConstructor = new NFAConstructor(lexer);

        NFAPair pair = new NFAPair();
        nfaConstructor.unionRE(pair);
        NFAPrinter printer = new NFAPrinter();
        printer.printNfa(pair.startNode);
        DFAConstructor dfaConstructor = new DFAConstructor(pair.startNode);
        dfaConstructor.nfa2dfa();
        dfaConstructor.printDFATable();

        input = InputStr.getInstance();
        dfaMatch = new DFAMatch(dfaConstructor.getDfaList(),dfaConstructor.getDfaStateTable());
        tokenManager = TokenManager.getInstance();
    }

    public void interpretor(){
        input.printInput();
        char c;
        String tokenStr = "";
        Token token;
        while(input.isFinish() == false){
            c = input.getCurrrnetChar();
//            System.out.print(c);
            tokenStr += c;
            token = dfaMatch.DFAInterpreter(input);
            //正则表达式可以匹配
            if(token != null){
                String reStr = token.getLexem();
                if(tokenManager.isKey(reStr)){
                    token = new Token(Token.TYPE.KEYWORD,reStr);
                }
                tokenManager.addToken(token);
//                token.printToken();
            }else{
                if(tokenManager.isDelimiter(tokenStr)){
                    token = new Token(Token.TYPE.DELIMITER,tokenStr);
                    tokenManager.addToken(token);
//                    System.out.println("delimiter");
//                    token.printToken();
                    input.advance();
                }else {
                    if(tokenManager.isOperator(tokenStr)){
                        input.advance();
                        if(input.isFinish()){
                            token = new Token(Token.TYPE.OP,tokenStr);
                            tokenManager.addToken(token);
                            return;
                        }
                        c = input.getCurrrnetChar();
                        String doubleOP = tokenStr+c;
                        if(tokenManager.isOperator(doubleOP)){
                            token = new Token(Token.TYPE.OP,doubleOP);
                            input.advance();
                        }else{
                            token = new Token(Token.TYPE.OP,tokenStr);
                        }
                        tokenManager.addToken(token);
//                        System.out.println("operator");
//                        token.printToken();
                    }else{
                        input.advance();
                    }
                }
            }
            tokenStr = "";
        }

    }

    public void printResult(){
        tokenManager.printAllToken();
    }
}
