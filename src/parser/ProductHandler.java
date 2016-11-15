package parser;

import lexer.Process;
import lexer.TokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ZhangYF on 2016/11/14.
 */
public class ProductHandler {

    private PPTBuilder pptBuilder;
    private Process process;
    private TokenManager tokenManager;
    private List<Integer> token;
    private Stack<Integer> parserStack;
    private ProductionTable productionTable;

    private int currentIndex;

    public ProductHandler() throws Exception {
        pptBuilder = new PPTBuilder();
        process = new Process();
        process.interpretor();
        process.printResult();
        tokenManager = TokenManager.getInstance();
        productionTable = ProductionTable.getInstance();

        token = tokenManager.getTokenList();
        token.add(Symbol.END);
        parserStack = new Stack<>();
        currentIndex = 0;
    }

    public void operate(){

        System.out.println("自顶向下语法分析过程：");
        parserStack.push(Symbol.END);
        parserStack.push(Symbol.STMTS);
        int curSymbol = parserStack.peek();
        int curToken = token.get(currentIndex);
        int nextProduction = pptBuilder.getNext(curSymbol,curToken);
        while(curSymbol != Symbol.END){
            if(nextProduction != PPTBuilder.Fail){
                Production production = productionTable.getProduction(nextProduction);
                production.print();
                parserStack.pop();
                List<Integer> right = production.getRight();
                for(int i=right.size()-1;i>=0;i--){
                    if(right.get(i) != Symbol.EPSILON){
                        parserStack.push(right.get(i));
                    }
                }
                curSymbol = parserStack.peek();
                while(Symbol.isTerminal(curSymbol)){
                    if(curSymbol == curToken && curToken != Symbol.END){
                        parserStack.pop();
                        curSymbol = parserStack.peek();
                        currentIndex++;
                        curToken = token.get(currentIndex);
                    }else if(curSymbol == Symbol.END){
                        break;
                    }

                }
                if(!Symbol.isTerminal(curSymbol)){
                    nextProduction = pptBuilder.getNext(curSymbol,curToken);
                }

            }else {
                System.out.println("语法错误！！！");
                System.out.println(curSymbol+"  "+curToken);
                break;
            }
        }
        if(curSymbol == Symbol.END && curToken == Symbol.END){
            System.out.println("语法分析完成");
        }else{
            System.out.println("语法错误");
            System.out.println(curSymbol+"  "+curToken);
        }

    }
}
