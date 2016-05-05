package patternmatching;


public class Client {

   public static void main(String[] args) {
       Expr expr01 = new Mul(new Num(100), new Num(1));

       Expr result01 = expr01.matchWith(new ExprVisitor() {
           @Override
           public Expr visit(Mul m) {

              return m.getRight().matchWith(new ExprVisitor() {
                   @Override
                   public Expr visit(Num t) {
                       if(t.getValue() == 1) {
                           return m.getRight();
                       }
                       return expr01;
                   }
               });
           }
       });
       System.out.println(result01);


       Expr expr02 = new Mul(new Mul(new Num(10), new Num(20)), new Num(30));
       Expr result02 = expr02.matchWith(new ExprVisitor() {
           @Override
           public Expr visit(Mul m) {
                return m.getLeft().matchWith(new ExprVisitor() {
                    @Override
                    public Expr visit(Mul m2) {
                        return m2.getRight().matchWith(new ExprVisitor() {
                            @Override
                            public Expr visit(Num t) {
                                if(t.getValue() == 20) {
                                    return m2.getRight();
                                }
                                return expr02;
                            }
                        });
                    }
                });
           }
       });
       System.out.println(result02);

   }
}
