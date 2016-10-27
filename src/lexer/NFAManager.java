package lexer;

import java.util.Stack;

/**
 * Created by ZhangYF on 2016/10/26.
 */
public class NFAManager {

        private final int NFA_MAX = 256; //最多运行分配256个NFA节点
        private NFANode[] nfaStatesArr = null;
        private Stack<NFANode> nfaStack = null;
        private int nextAlloc = 0; //nfa数组下标
        private int nfaStates = 0; //分配的nfa编号

        public NFAManager() throws Exception {
            nfaStatesArr = new NFANode[NFA_MAX];
            for (int i = 0; i < NFA_MAX; i++) {
                nfaStatesArr[i] = new NFANode();
            }

            nfaStack = new Stack<NFANode>();

//            if (nfaStatesArr == null || nfaStack == null) {
//                ErrorHandler.parseErr(ErrorHandler.Error.E_MEM);
//            }
        }

        public NFANode newNFANode() throws Exception {

            if (++nfaStates >= NFA_MAX) {
                System.out.println("too many states!!!!");
            }

            NFANode nfa = null;
            if (nfaStack.size() > 0) {
                nfa = nfaStack.pop();
            }
            else {
                nfa = nfaStatesArr[nextAlloc];
                nextAlloc++;
            }

            nfa.clearState();
            nfa.setStateNum(nfaStates);
            nfa.setEdge(NFANode.EPSILON);

            return nfa;
        }

        public void discardNFANode(NFANode nfaDiscarded) {
            --nfaStates;
            nfaDiscarded.clearState();
            nfaStack.push(nfaDiscarded);
        }

}
