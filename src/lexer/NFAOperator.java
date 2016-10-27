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
                if (nfaSet.contains(nfa.next) == false)  {
                    nfaStack.push(nfa.next);
                    input.add(nfa.next);
                }
            }

            if (p.next2 != null && p.getEdge() == Nfa.EPSILON) {
                if (input.contains(p.next2) == false) {
                    Nfa next = p.next2;

                    nfaStack.push(p.next2);
                    input.add(p.next2);
                }
            }
        }

        if (input != null && debug) {
            System.out.println("{ " + strFromNfaSet(input) + " }");
        }
        return null;
    }
}
