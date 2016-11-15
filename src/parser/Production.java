package parser;

import java.util.List;

/**
 * Created by ZhangYF on 2016/11/14.
 */
public class Production {
    private int left;
    private List<Integer> right;


    public Production(int left, List<Integer> right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public List<Integer> getRight() {
        return right;
    }

    public void print(){
        System.out.print(Symbol.getName(left)+"->");
        for (int i = 0; i <right.size() ; i++) {
            System.out.print(Symbol.getName(right.get(i))+" ");
        }
        System.out.println();
    }
}
