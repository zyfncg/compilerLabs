import java.util.HashMap;
import java.util.Map;

/**
 * Created by st0001 on 2016/10/24.
 */
public class Tag {
    public final static int NUM = 256, ID = 257, ASSIGN = 258, COND = 259,
                            REL = 260, NOT = 261, OP = 262, IF = 263, ELSE = 264,
                            WHILE = 265, FOR = 266, INT = 267, DOUBLE = 268,
                            CHAR = 269, RETURN = 270, BREAK = 271;
    public static int isKeyword(String str){
        String[] tagList = {"if","else","while","for","int","double","char","return","break"};
        for (int i = 0; i <tagList.length ; i++) {
            if(str.equals(tagList[i])) {
                return i+263;
            }
        }
        return -1;
    }
    public static Map<String,Integer> TagMap = new HashMap<>();
    public Tag(){

    }
}
