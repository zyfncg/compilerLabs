/**
 * Created by st0001 on 2016/10/24.
 */
public class Num extends Token {
    public final int value;
    public Num(int value){
        super(Tag.NUM);
        this.value=value;

    }

    @Override
    public String toString() {
        return "NUM: "+ value;
    }
}
