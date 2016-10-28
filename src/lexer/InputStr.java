package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by ZhangYF on 2016/10/28.
 */
public class InputStr {
    private static String input;
    private static int cuurentIndex;
    private char currrnetChar;
    private boolean isFinish = false;

    private InputStr(){
        input = getInput();
        cuurentIndex = 0;
        currrnetChar = input.charAt(cuurentIndex);
    }
    private static InputStr instance = new InputStr();
    public static InputStr getInstance(){
        return instance;
    }
    public void advance(){
        cuurentIndex++;
        if(cuurentIndex>=input.length()){
            isFinish = true;
            return;
        }
        currrnetChar = input.charAt(cuurentIndex);
    }
    public char getCurrrnetChar(){
        return currrnetChar;
    }
    public boolean isFinish(){
        return isFinish;
    }
    public void printInput(){
        System.out.println(input);
    }

    private String getInput(){
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
