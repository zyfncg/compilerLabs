package lexer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by st0001 on 2016/10/27.
 */
public class DFA {
    private static int STATE_NUM = 0;
    private int stateID = 0;
    private Set<NFANode> nfaStates = new HashSet<>();
    private String dfaName;
    boolean accept = false;

    private DFA(){
        stateID = STATE_NUM;
        STATE_NUM++;
    }

    public static DFA getDFAFromNFASet(Set<NFANode> nfaSet){
        DFA dfa = new DFA();
        Iterator<NFANode> it = nfaSet.iterator();
        while (it.hasNext()){
            NFANode nfa = it.next();
            dfa.nfaStates.add(nfa);
            if(nfa.isTerminal()){
                dfa.accept = true;
                dfa.dfaName = nfa.getNodeName();
            }
        }
        return dfa;
    }

    public Set<NFANode> getNfaStates() {
        return nfaStates;
    }

    public boolean hasNFAStates(Set<NFANode> nfaSet){
        if(nfaStates.equals(nfaSet)){
            return true;
        }
        return false;
    }

    public int getStateID() {
        return stateID;
    }

    public String getDfaName() {
        return dfaName;
    }

    public void setDfaName(String dfaName) {
        this.dfaName = dfaName;
    }
}
