package patternmatching;

public class Num implements Expr {
    private Integer value;
    public Num(Integer value) {
        this.value = value;
    }

    @Override
    public Expr matchWith(ExprVisitor v) {
        return v.visit(this);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Num{" +
                "value=" + value +
                '}';
    }
}
