package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYF on 2016/10/26.
 */
public class REHandler {

    ArrayList<RE> reList = new ArrayList<RE>();
    private List<String> keyList = new ArrayList<>();
    private List<String> opList = new ArrayList<>();
    private List<String> delimiterList = new ArrayList<>();

    private REHandler(){
        String input = getInput();
        String div[] = input.split("%%%\n");
        String first[] = div[0].split("\n");

        setKeyList(first[0]);
        setOpList(first[1]);
        setDelimiterList(first[2]);
        setREList(div[1]);

    }
    private static REHandler instance = new REHandler();
    public static REHandler getInstance(){
        return instance;
    }

    public int getRECount() {
        return reList.size();
    }

    public RE getRE(int index) {
        if (index < 0 || index >= reList.size()) {
            return null;
        }

        return reList.get(index);
    }

    private void setKeyList(String keyString){
        String str[] = keyString.split(":  ");
        String keys[] = str[1].split(",");
        for(int i =0 ;i<keys.length;i++){
            keyList.add(keys[i]);
        }
    }
    private void setOpList(String opString){
        String str[] = opString.split(":  ");
        String ops[] = str[1].split(",");
        for(int i =0 ;i<ops.length;i++){
            opList.add(ops[i]);
        }
    }
    private void setDelimiterList(String delString){
        String str[] = delString.split(":  ");
        String dels[] = str[1].split(",");
        for(int i =0 ;i<dels.length;i++){
            delimiterList.add(dels[i]);
        }
    }
    private void setREList(String rePartStr){
        String re = "";
        String list[] = rePartStr.split("\n");
        System.out.println(rePartStr);
        for (String str:list) {
            String s[] = str.split(":");
            String reStr = "";
            for(int i=0;i<s[1].length();i++){
                if(s[1].charAt(i)!=' '&& s[1].charAt(i)!='\t'){
                    reStr+=s[1].charAt(i);
                }
            }
            reList.add(new RE(s[0],reStr));
        }

    }


    private String getInput(){
        String filePath = "rule.l";
        File file = new File(filePath);
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s = br.readLine())!=null){
                result += s+'\n';
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public List<String> getOpList() {
        return opList;
    }

    public List<String> getDelimiterList() {
        return delimiterList;
    }
}
