package patternmatching;

public class Mul implements Expr {

    private Expr left;
    private Expr right;

    public Mul(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expr matchWith(ExprVisitor v) {
        return v.visit(this);
    }

    public Expr getLeft() {
        return left;
    }

    public Expr getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Mul{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
