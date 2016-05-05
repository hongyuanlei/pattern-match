package patternmatching;

public interface Expr {

    Expr matchWith(ExprVisitor v);
}
