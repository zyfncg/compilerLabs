package parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2016/11/15.
 */
public class SymbolHandler {
    private Map<Integer,List<Integer>> firstSet = new HashMap<>();
    private Map<Integer,List<Integer>> followSet = new HashMap<>();
    private Map<Integer,List<Integer>> selectSet = new HashMap<>();

    private void calFirstSet(){
        ProductionTable productionTable = ProductionTable.getInstance();

    }


}
