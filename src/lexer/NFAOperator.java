package lexer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Created by st0001 on 2016/10/27.
 */
public class NFAOperator {


    public Set<NFANode> e_closure(Set<NFANode> nfaSet){
        Set<NFANode> result = new HashSet<>();
        Stack<NFANode> nfaStack = new Stack<>();
        if(nfaSet == null && nfaSet.isEmpty()){
            return null;
        }
        Iterator<NFANode> it = nfaSet.iterator();
        while (it.hasNext()){
            NFANode nfa = it.next();
            nfaStack.push(nfa);
            result.add(nfa);
        }

        while (!nfaStack.isEmpty()){

            NFANode nfa = nfaStack.pop();
            if (nfa.next != null && nfa.getEdge() == NFANode.EPSILON) {
                if (result.contains(nfa.next) == false)  {
                    nfaStack.push(nfa.next);
                    result.add(nfa.next);
                }
            }

            if (nfa.next2 != null && nfa.getEdge() == NFANode.EPSILON) {
                if (result.contains(nfa.next2) == false) {
                    nfaStack.push(nfa.next2);
                    result.add(nfa.next2);
                }
            }
        }

//        if (result != null) {
//            System.out.println("{ " + strFromNfaSet(input) + " }");
//        }
        return result;
    }

    public Set<NFANode> move(Set<NFANode> nfaSet,char c){
        Set<NFANode> result = new HashSet<>();
        if(nfaSet == null && nfaSet.isEmpty()){
            return null;
        }
        Iterator<NFANode> it = nfaSet.iterator();
        while (it.hasNext()){
            NFANode nfa = it.next();
            if((nfa.getEdge() == NFANode.CCL && nfa.inputSet.contains((byte)c))|| nfa.getEdge() == c){
                result.add(nfa.next);
            }
        }

        return result;
    }

}
