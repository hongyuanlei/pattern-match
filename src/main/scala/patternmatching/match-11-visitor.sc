trait Visitor[T] {

  def caseMul(t: Mul): T = otherwise(t)

  def caseNum(t: Num): T = otherwise(t)

  def caseVar(t: Var): T = otherwise(t)

  def otherwise(t: Expr): T = throw new MatchError(t)
}

trait Expr {
  def matchWith[T](v: Visitor[T]): T
}

class Var(val name: String) extends Expr {
  def matchWith[T](v: Visitor[T]): T = {
    v.caseVar(this)
  }
}
class Num(val value: Double) extends Expr {
  def matchWith[T](v: Visitor[T]): T = {
    v.caseNum(this)
  }
}

class Mul(val left: Expr, val right: Expr) extends Expr  {
  def matchWith[T](v: Visitor[T]): T = {
    v.caseMul(this)
  }
}

val e = new Mul(new Num(1), new Num(10))

e.matchWith({
  new Visitor[Expr] {
    override def caseMul(m: Mul) = {
      m.right.matchWith {
        new Visitor[Expr] {
          override def caseNum(n: Num) = {
            if(n.value == 1) {
              m.left
            } else {
              e
            }
          }

          override def otherwise(e: Expr) = e
        }
      }
    }
  }
})

