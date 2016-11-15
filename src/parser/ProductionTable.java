package parser;

import java.util.*;

/**
 * Created by ZhangYF on 2016/11/14.
 */
public class ProductionTable {
    private List<Production> pList = new ArrayList<>();

    private Map<Integer,List<Integer>> firstSet = new HashMap<>();
    private Map<Integer,List<Integer>> followSet = new HashMap<>();

    private ProductionTable() {
        initPlist();
        initSet();
        calFirstSet();
        calFollowSet();
    }
    private static ProductionTable instance = new ProductionTable();
    public static ProductionTable getInstance(){
        return instance;
    }
    private void initPlist(){

        pList.add(new Production( Symbol.STMTS, Arrays.asList(Symbol.STMT,Symbol.STMTS)));
        pList.add(new Production( Symbol.STMTS, Arrays.asList(Symbol.EPSILON)));

        pList.add(new Production( Symbol.STMT, Arrays.asList(Symbol.ID,(int)('='),Symbol.JUDGE)));
        pList.add(new Production( Symbol.STMT, Arrays.asList(Symbol.IF,(int)('('),Symbol.JUDGE,(int)(')'),Symbol.BLOCK,Symbol.ELSE,Symbol.BLOCK)));
        pList.add(new Production( Symbol.STMT, Arrays.asList(Symbol.WHERE,(int)('('),Symbol.JUDGE,(int)(')'),Symbol.BLOCK)));

        pList.add(new Production( Symbol.BLOCK, Arrays.asList((int)('{'),Symbol.STMTS,(int)'}')));

        pList.add(new Production( Symbol.JUDGE, Arrays.asList(Symbol.EXPR,Symbol.JUDGE1)));
        pList.add(new Production( Symbol.JUDGE1, Arrays.asList((int)('<'),Symbol.EXPR)));
        pList.add(new Production( Symbol.JUDGE1, Arrays.asList((int)('>'),Symbol.EXPR)));
        pList.add(new Production( Symbol.JUDGE1, Arrays.asList(Symbol.EPSILON)));

        pList.add(new Production( Symbol.EXPR, Arrays.asList(Symbol.TERM,Symbol.EXPR1)));
        pList.add(new Production( Symbol.EXPR1, Arrays.asList(Symbol.EXPR2,Symbol.EXPR1)));
        pList.add(new Production( Symbol.EXPR1, Arrays.asList(Symbol.EPSILON)));
        pList.add(new Production( Symbol.EXPR2, Arrays.asList((int)'+',Symbol.TERM)));
        pList.add(new Production( Symbol.EXPR2, Arrays.asList((int)'-',Symbol.TERM)));

        pList.add(new Production( Symbol.TERM, Arrays.asList(Symbol.FACTOR,Symbol.TERM1)));
        pList.add(new Production( Symbol.TERM1, Arrays.asList(Symbol.TERM2,Symbol.TERM1)));
        pList.add(new Production( Symbol.TERM1, Arrays.asList(Symbol.EPSILON)));
        pList.add(new Production( Symbol.TERM2, Arrays.asList((int)'*',Symbol.FACTOR)));
        pList.add(new Production( Symbol.TERM2, Arrays.asList((int)'/',Symbol.FACTOR)));

        pList.add(new Production( Symbol.FACTOR, Arrays.asList(Symbol.NUM)));
        pList.add(new Production( Symbol.FACTOR, Arrays.asList(Symbol.ID)));

    }

    private void initSet(){
        for(int i = Symbol.NO_TERMINAL_START+1;i<=Symbol.NO_TERMINAL_START+Symbol.NO_TERMINAL_NUM;i++){
            List<Integer> first = new ArrayList<>();
            firstSet.put(i,first);
            List<Integer> follow = new ArrayList<>();
            followSet.put(i,follow);
        }
        List<Integer> SFollow = new ArrayList<>();
        SFollow.add(Symbol.END);
        followSet.put(Symbol.STMTS,SFollow);
    }

    private void calFirstSet(){
        boolean isFinish;
        do{
            isFinish = true;
            for(int no_terminal:firstSet.keySet()){
                List<Integer> first = firstSet.get(no_terminal);

                for (Production production: pList) {//遍历所有表达式
                    int left = production.getLeft();
                    if(left == no_terminal){//找出推导式
                        List<Integer> right = production.getRight();
                        for (int i = 0; i < right.size(); i++) {
                            int beta = right.get(i);
                            if(beta < Symbol.NO_TERMINAL_START){//为终结符时
                                if(!first.contains(beta)){
                                    first.add(beta);
                                    isFinish = false;
                                }
                                break;
                            }else{//为非终结符时
                                List<Integer> betaFirst = firstSet.get(beta);
                                for(int bf:betaFirst){
                                    if(!first.contains(bf) && bf != Symbol .EPSILON){
                                        first.add(bf);
                                        isFinish = false;
                                    }
                                }
                                if(!betaFirst.contains(Symbol.EPSILON)){//beta不为空
                                    break;
                                }else if(i == right.size()-1){//推导式可以为空
                                    first.add(Symbol.EPSILON);
                                    isFinish = false;
                                }

                            }
                        }
                    }

                }
            }

        }while (!isFinish);
    }


    private void calFollowSet(){
        boolean isFinish;
        do{
            isFinish = true;
            for(int no_terminal:followSet.keySet()){
                List<Integer> follow = followSet.get(no_terminal);
                for (Production production: pList) {//遍历所有表达式
                    List<Integer> right = production.getRight();
                    if(right.contains(no_terminal)){//右部包含此非终结符
                        int left = production.getLeft();
                        for(int i = 0;i<right.size();i++){
                            int beta = right.get(i);
                            if(beta == no_terminal){
                                if(i<right.size()-1){
                                    for(int j=i+1;j<right.size();j++){//从此处开始往后扫描
                                        int beta1 = right.get(j);
                                        if(beta1 < Symbol.NO_TERMINAL_START){//为终结符时
                                            if(!follow.contains(beta1)){
                                                follow.add(beta1);
                                                isFinish = false;
                                            }
                                            break;
                                        }else{//为非终结符时
                                            List<Integer> betaFollow = firstSet.get(beta1);
                                            for(int bf:betaFollow){
                                                if(!follow.contains(bf) && bf != Symbol .EPSILON){
                                                    follow.add(bf);
                                                    isFinish = false;
                                                }
                                            }
                                            if(!betaFollow.contains(Symbol.EPSILON)){//beta1不为空
                                                break;
                                            }else if(j == right.size()-1){//后面式子可以为空
                                                List<Integer> leftFollow = followSet.get(left);
                                                for(int lf : leftFollow){
                                                    if(!follow.contains(lf)){
                                                        follow.add(lf);
                                                        isFinish = false;
                                                    }
                                                }
                                                break;
                                            }

                                        }

                                    }
                                }else if(i == right.size()-1){
                                    List<Integer> leftFollow = followSet.get(left);
                                    for(int lf : leftFollow){
                                        if(!follow.contains(lf)){
                                            follow.add(lf);
                                            isFinish = false;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }

        }while(!isFinish);
    }

    public Production getProduction(int index){
        return pList.get(index);
    }
    public int getProductionNum(){
        return pList.size();
    }

    public List<Integer> getFirst(List<Integer> ab){
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<ab.size();i++){
            int beta = ab.get(i);
            if(beta < Symbol.NO_TERMINAL_START){//为终结符时
                if(!result.contains(beta)){
                    result.add(beta);
                }
                break;
            }else{//为非终结符时
                List<Integer> betaFirst = firstSet.get(beta);
                for(int bf:betaFirst){
                    if(!result.contains(bf) && bf != Symbol .EPSILON){
                        result.add(bf);
                    }
                }
                if(!betaFirst.contains(Symbol.EPSILON)){//beta不为空
                    break;
                }else if(i == ab.size()-1){//推导式可以为空
                    result.add(Symbol.EPSILON);
                }

            }
        }
        return result;
    }

    public List<Integer> getFirstSet(int key) {
        return firstSet.get(key);
    }

    public List<Integer> getFollowSet(int key) {
        return followSet.get(key);
    }
}
