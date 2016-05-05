def isIntIntMap(x: Any) = x match {
  case m: Map[Int, Int] => true
  case _ => false
}

isIntIntMap(Map(1 -> 1))

isIntIntMap(Map("abc" -> "abc"))


def isStringArray(x: Any) = {
  x match {
    case a: Array[String] => "yes"
    case _ => "no"
  }
}

val as = Array("abc")
isStringArray(as)

val ai = Array(1, 2, 3)
isStringArray(ai)