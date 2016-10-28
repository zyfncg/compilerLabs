package lexer;

import java.util.List;

/**
 * Created by ZhangYF on 2016/10/28.
 */
public class DFAMatch {
    private int DFAStateTable[][];
    private List<DFA> dfaList;
    private int currentDFAIndex = 0;

    public DFAMatch(List<DFA> dfaList, int[][] DFAStateTable) {
        this.dfaList = dfaList;
        this.DFAStateTable = DFAStateTable;
    }

    public Token DFAInterpreter(InputStr input){
        String match="";
        char c;
        String expr = "";
        int currentState = 0,nextState;
        boolean isWrong = false;
        while(input.isFinish() == false && currentState != DFAConstructor.STATE_FAILURE){
            c = input.getCurrrnetChar();
            nextState = DFAStateTable[currentState][c];
//            System.out.print(c+":"+nextState+" ");
            if(nextState == DFAConstructor.STATE_FAILURE){
                DFA dfa = dfaList.get(currentState);
                if(dfa.accept){
                    match = dfa.getDfaName();
                    break;
                }else{
                    return null;
                }
            }else{
                expr += c;
                input.advance();
                currentState = nextState;
            }
        }
        Token token = new REToken(match,expr);


        return token;
    }
}
