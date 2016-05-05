
val booleans = Seq(true, false)

for (bool <- booleans) {
  bool match {
    case true => println("Got heads")
    case false => println("Got tails")
  }
}