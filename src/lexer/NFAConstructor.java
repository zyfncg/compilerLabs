package lexer;

import java.util.Set;

/**
 * Created by ZhangYF on 2016/10/26.
 */
public class NFAConstructor {
    private Lex lexer;
    private NFAManager nfaManager = null;

    public NFAConstructor(Lex lexer) throws Exception {
        this.lexer = lexer;
        nfaManager = new NFAManager();

        while (lexer.MatchMark(Lex.Mark.EOS)) {
            lexer.advance();
        }
    }
    public void unionRE(NFAPair start) throws Exception{

        NFAPrinter printer = new NFAPrinter();
        NFAPair pair = new NFAPair();
        expr(pair);
        pair.endNode.setTerminal(true);
        pair.endNode.setNodeName(lexer.getReName());
        start.startNode = pair.startNode;
        int count = 0;
        lexer.advance();
        while (!lexer.MatchMark(Lex.Mark.END_OF_INPUT)){
//            System.out.println(++count);
//            printer.printNfa(start.startNode);
            NFAPair pair1 = new NFAPair();
            expr(pair1);
            pair1.endNode.setTerminal(true);
            pair1.endNode.setNodeName(lexer.getReName());
            NFANode startNode = nfaManager.newNFANode();
            startNode.next = start.startNode;
            startNode.next2 = pair1.startNode;
            start.startNode=startNode;
            lexer.advance();
        }
    }
    public void expr(NFAPair pairOut) throws Exception {

        cat_expr(pairOut);
        NFAPair localPair = new NFAPair();

        while (lexer.MatchMark(Lex.Mark.OR)) {
            lexer.advance();
            cat_expr(localPair);

            NFANode startNode = nfaManager.newNFANode();
            startNode.next2 = localPair.startNode;
            startNode.next = pairOut.startNode;
            pairOut.startNode = startNode;

            NFANode endNode = nfaManager.newNFANode();
            pairOut.endNode.next = endNode;
            localPair.endNode.next = endNode;
            pairOut.endNode = endNode;
        }

    }
    public void cat_expr(NFAPair pairOut) throws Exception
    {
    	/*
    	 * cat_expr -> factor factor .....
    	 * 由于多个factor 前后结合就是一个cat_expr所以
    	 * cat_expr-> factor cat_expr
    	 */

        if (first_in_cat(lexer.getCurrentMark())) {
            factor(pairOut);
        }

        while (first_in_cat(lexer.getCurrentMark()) ){
            NFAPair pairLocal = new NFAPair();
            factor(pairLocal);

            pairOut.endNode.next = pairLocal.startNode;

            pairOut.endNode = pairLocal.endNode;
        }


    }
    private boolean first_in_cat(Lex.Mark tok) throws Exception {
        switch (tok) {
            //正确的表达式不会以 ) $ 开头,如果遇到EOS表示正则表达式解析完毕，那么就不应该执行该函数
            case CLOSE_PAREN:
            case AT_EOL:
            case OR:
            case END_OF_INPUT:
            case EOS:
                return false;
            case CLOSURE:
                return false;
            case CCL_END:
                return false;
            case AT_BOL:
                return false;
        }
        return true;
    }
    public void factor(NFAPair pairOut) throws Exception {
        term(pairOut);
        NFANode start, end;

        if (lexer.MatchMark(Lex.Mark.CLOSURE) == true) {
            start = nfaManager.newNFANode();
            end = nfaManager.newNFANode();

            start.next = pairOut.startNode;
            pairOut.endNode.next = pairOut.startNode;

            start.next2 = end;
            pairOut.endNode.next2 = end;

            pairOut.startNode = start;
            pairOut.endNode = end;

            lexer.advance();
        }

    }



    public void term(NFAPair pairOut)throws Exception {
        /*
         * term ->  character | [...] | [character-charcter] | (expr)
         *
         */

        boolean handled = constructExprInParen(pairOut);
        if (handled == false) {
            handled = constructNfaForSingleCharacter(pairOut);
        }

        if (handled == false) {
            constructNFAForCharacterSet(pairOut);
        }


    }

    private boolean constructExprInParen(NFAPair pairOut) throws Exception {
        if (lexer.MatchMark(Lex.Mark.OPEN_PAREN)) {
            lexer.advance();
            expr(pairOut);
            if (lexer.MatchMark(Lex.Mark.CLOSE_PAREN)) {
                lexer.advance();
            }
            else {
                System.out.println("error without ')'");
            }

            return true;
        }

        return false;
    }

    public boolean constructNfaForSingleCharacter(NFAPair pairOut) throws Exception {
        if (lexer.MatchMark(Lex.Mark.L) == false) {
            return false;
        }

        NFANode start = null;
        start = pairOut.startNode = nfaManager.newNFANode();
        pairOut.endNode = pairOut.startNode.next = nfaManager.newNFANode();

        start.setEdge(lexer.getLexeme());

        lexer.advance();

        return true;
    }

    public boolean constructNFAForCharacterSet(NFAPair pairOut) throws Exception {

        if (lexer.MatchMark(Lex.Mark.CCL_START) == false) {
            return false;
        }

        lexer.advance();

        NFANode start = null;
        start = pairOut.startNode = nfaManager.newNFANode();
        pairOut.endNode = pairOut.startNode.next = nfaManager.newNFANode();
        start.setEdge(NFANode.CCL);

        if (lexer.MatchMark(Lex.Mark.CCL_END) == false) {
            dodash(start.inputSet);
        }

        if (lexer.MatchMark(Lex.Mark.CCL_END) == false) {
            System.out.println("没有边界 ]");
        }
        lexer.advance();

        return true;
    }
    private void dodash(Set<Byte> set) {
        int first = 0;

        while (lexer.MatchMark(Lex.Mark.EOS) == false &&
                lexer.MatchMark(Lex.Mark.CCL_END) == false) {

            if (lexer.MatchMark(Lex.Mark.DASH) == false) {
                first = lexer.getLexeme();
                set.add((byte)first);
            }
            else {
                lexer.advance(); //越过 -
                for (; first <= lexer.getLexeme(); first++) {
                    set.add((byte)first);
                }
            }

            lexer.advance();
        }


    }
}
