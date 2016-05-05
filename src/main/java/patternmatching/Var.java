package patternmatching;

public class Var implements Expr {

    private String name;
    public Var(String name) {
        this.name = name;
    }

    @Override
    public Expr matchWith(ExprVisitor v) {
        return v.visit(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Var{" +
                "name='" + name + '\'' +
                '}';
    }
}
