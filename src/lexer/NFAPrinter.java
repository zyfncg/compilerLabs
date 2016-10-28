package lexer;

import java.util.Set;

/**
 * Created by ZhangYF on 2016/10/27.
 */
public class NFAPrinter {
    private static final int ASCII_NUM = 128;
    private boolean start = true;

    private void printCCL(Set<Byte> set) {
        System.out.print("[ ");
        for (int i = 0; i < ASCII_NUM; i++) {
            if (set.contains((byte)i)) {
                if (i < ' ') {
                    System.out.print("^" + (char)(i + '@'));
                }
                else {
                    System.out.print((char)i);
                }
            }
        }

        System.out.print(" ]");
    }

    public void printNfa(NFANode startNfa) {
        if (startNfa == null || startNfa.isVisited()) {
            return;
        }

        if (start) {
            System.out.println("--------NFA--------");
        }

        startNfa.setVisited();

        printNfaNode(startNfa);

        if (start) {
            System.out.print("  (START STATE)");
            start = false;
        }

        System.out.print("\n");

        printNfa(startNfa.next);
        printNfa(startNfa.next2);
    }

    private void printNfaNode(NFANode node) {
        if (node.next == null) {
            System.out.print("TERMINAL  ");
        }
        if(node.isTerminal()){
            System.out.println(node.getNodeName());
        }
        else {
            System.out.print("NFA state: " + node.getStateNum());
            System.out.print("--> " + node.next.getStateNum());
            if (node.next2 != null) {
                System.out.print(" " + node.next2.getStateNum() );
            }

            System.out.print(" on:");
            switch (node.getEdge()) {
                case NFANode.CCL:
                    printCCL(node.inputSet);
                    break;
                case NFANode.EPSILON:
                    System.out.print("EPSILON ");
                    break;
                default:
                    System.out.print((char)node.getEdge());
                    break;
            }
        }
    }
}
