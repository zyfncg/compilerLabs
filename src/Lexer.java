import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by st0001 on 2016/10/24.
 */
public class Lexer {
    public Token scan(){
        String input = getInput();
        System.out.println(input);
        char peek;
        for(int i = 0; i<input.length(); i++){

        }
        return null;
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
}
