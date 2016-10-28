package lexer;

import java.util.*;

/**
 * Created by st0001 on 2016/10/27.
 */
public class DFAConstructor {
    private NFANode nfaStartNode ;
    private NFAOperator nfaOperator = new NFAOperator();
    private List<DFA> dfaList = new ArrayList<>();

    private static final int MAX_DFA_STATE_COUNT = 256;

    private static final int ASCII_COUNT = 128;

    public static final int STATE_FAILURE = -1;

    //使用二维数组表示DFA有限状态自动机
    private int[][] dfaStateTable = new int[MAX_DFA_STATE_COUNT][ASCII_COUNT + 1];


    public DFAConstructor(NFANode nfaStartNode) {
        this.nfaStartNode = nfaStartNode;
        initDFAStateTable();
    }
    private void initDFAStateTable() {
        for (int i = 0; i < MAX_DFA_STATE_COUNT; i++)
            for (int j = 0; j <= ASCII_COUNT; j++) {
                dfaStateTable[i][j] = STATE_FAILURE;
            }
    }
    public void nfa2dfa(){
        Set<NFANode> nfaSet = new HashSet<>();
        nfaSet.add(nfaStartNode);
        nfaSet = nfaOperator.e_closure(nfaSet);
        DFA start = DFA.getDFAFromNFASet(nfaSet);
        dfaList.add(start);

        int nextState = STATE_FAILURE;
        int currentDfaIndex = 0;
        while (currentDfaIndex<dfaList.size()){
            DFA currentDFA = dfaList.get(currentDfaIndex);

//            System.out.println(currentDfaIndex);
            for (char i = 0; i <= ASCII_COUNT; i++) {
                Set<NFANode> targetNFA = nfaOperator.move(currentDFA.getNfaStates(), i);
                if(targetNFA == null||targetNFA.isEmpty()){
                    nextState = STATE_FAILURE;
                }else {
                    targetNFA = nfaOperator.e_closure(targetNFA);

                    //调试代码

//                    Iterator<NFANode> it1 = targetNFA.iterator();
//                    while (it1.hasNext()){
//                        NFANode nfa = it1.next();
//                        System.out.print(i+":"+nfa.getStateNum());
//                    }
//                    System.out.println();

                    DFA targetDFA = hasDFA(targetNFA);
                    if(targetDFA == null){
                        DFA dfa = DFA.getDFAFromNFASet(targetNFA);
                        dfaList.add(dfa);
                        nextState = dfa.getStateID();
                    }else {
                        nextState = targetDFA.getStateID();
                    }
                }
                dfaStateTable[currentDFA.getStateID()][i] = nextState;
            }
            currentDfaIndex++;
        }

    }

    private DFA hasDFA(Set<NFANode> targetNFA){
        for (DFA dfa:dfaList) {
            if(dfa.hasNFAStates(targetNFA)){
                return dfa;
            }
        }
        return null;
    }

    private void printDFA(DFA dfa){
        if(dfa.accept==true){
            System.out.print("Dfa(accepted):" + dfa.getStateID() + "{");
        }else {
            System.out.print("Dfa:" + dfa.getStateID() + "{");
        }
        Iterator<NFANode> it = dfa.getNfaStates().iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getStateNum());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }

        System.out.println("}");
        for(char i = 0;i<ASCII_COUNT;i++){
            if(dfaStateTable[dfa.getStateID()][i]!=STATE_FAILURE){
                System.out.print("-"+i+"->("+dfaStateTable[dfa.getStateID()][i]+");");
            }
        }
        System.out.println();
    }
    public void printDFATable(){
        for (DFA dfa:dfaList) {
            printDFA(dfa);
        }

    }

    public List<DFA> getDfaList() {
        return dfaList;
    }

    public int[][] getDfaStateTable() {
        return dfaStateTable;
    }
}
