package parser;

import java.util.List;

/**
 * Created by ZhangYF on 2016/11/14.
 */
public class PPTBuilder {

    private final static int Fail = -1;
    private ProductionTable productionTable = ProductionTable.getInstance();
    private int ppt[][] = new int[Symbol.NO_TERMINAL_NUM][Symbol.TERMINAL_MAX];

    public PPTBuilder(){
        initPPT();
    }

    private void initPPT(){
        for(int i = 0;i<Symbol.NO_TERMINAL_NUM;i++){
            for (int j = 0; j < Symbol.TERMINAL_MAX; j++) {
                ppt[i][j] = Fail;
            }
        }
        for(int i = 0;i<productionTable.getProductionNum();i++){
            Production production = productionTable.getProduction(i);
            int left = production.getLeft();
            List<Integer> select = productionTable.getFirst(production.getRight());
            if(select.contains(Symbol.EPSILON)){
                select = combineList(select,productionTable.getFollowSet(left));
            }
            for(int num : select){
                if(num != Symbol.EPSILON){
                    ppt[left - Symbol.NO_TERMINAL_START - 1][num] = i;
                }

            }
        }
    }

    private List<Integer> combineList(List<Integer> list1,List<Integer> list2){
        for(int num : list2){
            if(!list1.contains(num) && num != Symbol.EPSILON){
                list1.add(num);
            }
        }
        return list1;
    }

    public void printPPT(){
        for(int i = 0;i<Symbol.NO_TERMINAL_NUM;i++){
            for (int j = 0; j < Symbol.TERMINAL_MAX; j++) {
                if(ppt[i][j] != -1){
                    System.out.print(i+"->"+Symbol.getName(j)+":"+ppt[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
