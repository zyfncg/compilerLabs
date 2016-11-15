package parser;

import lexer.Process;
import lexer.TokenManager;

import java.util.List;

/**
 * Created by ZhangYF on 2016/11/13.
 */
public class Main {
    public static void main(String[] arge) throws Exception {
//        PPTBuilder pptBuilder = new PPTBuilder();
//        pptBuilder.printPPT();
        ProductHandler handler = new ProductHandler();
        handler.operate();
    }
}
