/**
 * Created by ZhangYF on 2016/10/24.
 */
public class Token {
    public final int tag;
    public Token(int tag){
        this.tag = tag;
    }
    public String toString(){
        return String.valueOf(tag);
    }
}
