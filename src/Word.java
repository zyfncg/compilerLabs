/**
 * Created by st0001 on 2016/10/24.
 */
public class Word extends Token {
    public final String lexeme;
    public Word(int tag, String s){
        super(tag);
        lexeme = s;
    }

    @Override
    public String toString() {
        return tag + ":" + lexeme;
    }
}
