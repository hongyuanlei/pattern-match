def checkY(y: Int): Unit = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case `y` => "found y!"
      case _: Int => "int:" + x
    }

    println(str)
  }
}

checkY(100)