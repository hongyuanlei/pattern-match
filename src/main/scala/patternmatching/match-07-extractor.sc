
class A

class B(val a:A)

object TT {
  def unapply(b: B) = Some(new A)
}

val b = new B(new A)

b match {
  case TT(c) => println(c)
}

/**
 * TT.unapply(b) match { case Some(...) => ...}
 */
