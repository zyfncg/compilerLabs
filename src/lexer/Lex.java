package lexer;

/**
 * Created by ZhangYF on 2016/10/26.
 */
public class Lex {
    public enum Mark {
        EOS, //正则表达式末尾
        ANY,     // . 通配符
        AT_BOL,  //^ 开头匹配符
        AT_EOL,  //$ 末尾匹配符
        CCL_END,  //字符集类结尾括号 ]
        CCL_START,  //字符集类开始括号 [
        CLOSE_CURLY, // }
        CLOSE_PAREN,  //)
        CLOSURE,      //*
        DASH,       // -
        END_OF_INPUT,  //输入流结束
        L,        //字符常量
        OPEN_CURLY, // {
        OPEN_PAREN, // (
        OR,       // |
    };
    private final int ASCII_COUNT = 128;
    private Mark[] markMap = new Mark[ASCII_COUNT];
    private Mark currentMark = Mark.EOS;
    private String currentName="";


    REHandler reHandler = null;
    private int exprCount = 0;
    private String curExpr = "";
    private int charIndex = 0;

    private int lexeme ;

    public Lex(REHandler reHandler) {
        this.reHandler = reHandler;
        initMarkMap();

    }


    private void initMarkMap() {
        for (int i = 0; i < ASCII_COUNT; i++) {
            markMap[i] = Mark.L;
        }

        markMap['.'] = Mark.ANY;
        markMap['^'] = Mark.AT_BOL;
        markMap[']'] = Mark.CCL_END;
        markMap['['] = Mark.CCL_START;
        markMap['}'] = Mark.CLOSE_CURLY;
        markMap[')'] = Mark.CLOSE_PAREN;
        markMap['*'] = Mark.CLOSURE;
        markMap['-'] = Mark.DASH;
        markMap['{'] = Mark.OPEN_CURLY;
        markMap['('] = Mark.OPEN_PAREN;
        markMap['|'] = Mark.OR;
    }

    public Mark advance() {
        if (currentMark == Mark.EOS) {
            //一个正则表达式解析结束后读入下一个表达式
            if (exprCount >= reHandler.getRECount()) {
                //所有正则表达式都解析完毕
                currentMark = Mark.END_OF_INPUT;
                return currentMark;
            }
            else {
                RE re = reHandler.getRE(exprCount);
                curExpr = re.getValue();
                currentName = re.getName();
                exprCount++;
            }
        }

        if (charIndex >= curExpr.length()) {
            currentMark = Mark.EOS;
            charIndex = 0;
            return currentMark;
        }

        lexeme = curExpr.charAt(charIndex);
        charIndex++;

        currentMark = markMap[lexeme];

        return currentMark;
    }

    public String getReName() {
        return currentName;
    }
    public boolean MatchMark(Mark t) {
        return currentMark == t;
    }

    public int getLexeme() {
        return lexeme;
    }

    public String getCurExpr() {
        return curExpr;
    }

    public Mark getCurrentMark() {
        return currentMark;
    }
}
