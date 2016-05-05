package patternmatching;

public class ExprVisitor {

    public Expr visit(Num t) {
        return defaultMethod();
    }

    public Expr visit(Mul t) {
        return defaultMethod();
    }

    public Expr visit(Var t) {
        return defaultMethod();
    }

    private Expr defaultMethod() {
        throw new RuntimeException("method not implemented");
    }
}
