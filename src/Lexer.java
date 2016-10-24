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
}
