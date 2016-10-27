package lexer;

/**
 * Created by ZhangYF on 2016/10/26.
 */
public class RE {
    private String name;
    private String value;

    public RE(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
