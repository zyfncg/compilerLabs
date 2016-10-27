import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by st0001 on 2016/10/24.
 */
public class Lexer {

    public List<Token> scan(){
        List<Token> tokenList = new ArrayList<Token>();
        String input = getInput();
        System.out.println(input);
        char cur,peek;
        for(int i = 0; i<input.length(); i++){
            cur = input.charAt(i);
            if(cur == ' '|| cur == '\t'){
                continue;
            }
            if(isNum(cur)){
                int v = 0;
                do{
                    v = 10*v + cur-'0';
                    i++;
                    if(i>=input.length()){
                        break;
                    }
                    cur = input.charAt(i);
                }while(isNum(cur));

                tokenList.add(new Num(v));
                i--;
            }else if(isLetter(cur)){
                String id ="";
                do{
                    id+=cur;
                    i++;
                    if(i>=input.length()){
                        break;
                    }
                    cur = input.charAt(i);

                }while(isNum(cur)||isLetter(cur));
                if(Tag.isKeyword(id)!= -1){
                    tokenList.add(new Word(Tag.isKeyword(id),id));
                }else{
                    tokenList.add(new Word(Tag.ID,id));
                }
                i--;
            }else if(isOperator(cur)){
                String op ="";
                op+=cur;
                i++;
                if(i>=input.length()){
                    break;
                }
                cur = input.charAt(i);
                if(isOperator(cur)){
                    op+=cur;
                }else{
                    i--;
                }
                tokenList.add(new Word(Tag.OP,op));
            }else if(cur=='('||cur==')'||cur=='{'||cur=='}'||cur==';'){
                tokenList.add(new Token(cur));
            }
        }
        return tokenList;
    }

    public String getInput(){
        String filePath = "source.txt";
        File file = new File(filePath);
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s = br.readLine())!=null){
                result += s;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断字符是否为[a-zA-Z]
     * @param c 输入字符
     * @return 如果是返回true
     */
    public static boolean isLetter (char c){
        if (('a'<=c && c<='z')||('A'<= c && c<='Z')){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNum (char c){
        if ('0'<=c && c<='9'){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isOperator (char c){
        final String operator="+-*/%=<>!|&";
        for (int i = 0; i < operator.length(); i++) {
            if(c==operator.charAt(i)){
                return true;
            }
        }
        return false;

    }
}
