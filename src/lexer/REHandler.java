package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by ZhangYF on 2016/10/26.
 */
public class REHandler {

    ArrayList<RE> reList = new ArrayList<RE>();

    public REHandler(){
        getREList(getInput());
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

    private void getREList(String rePartStr){
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
}
