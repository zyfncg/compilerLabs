/**
 * Created by st0001 on 2016/10/24.
 */
public class Main {

    public static void main(String[] args){
//        Lexer lex = new Lexer();
//        lex.scan();
//        char c = '9';
//        if (isNum(c)){
//            System.out.println("yes");
//        }else {
//            System.out.println("no");
//        }
    }
    public static boolean isOperator (char c){
        if ('0'<=c && c<='9'){
            return true;
        }else{
            return false;
        }
    }

}
