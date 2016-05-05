case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice    = Person("Alice",   25, Address("1 Scala Lane",  "Chicago", "USA"))
val bob      = Person("Bob",     29, Address("2 Java Ave",    "Miami",   "USA"))
val charlie  = Person("Charlie", 32, Address("3 Python Ct.",  "Boston",  "USA"))

for (person <- Seq(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice!")
    case Person("Bob", 29, Address("2 Java Ave", "Miami", "USA")) => println("Hi Bob!")
    case Person(name, age, _) => println(s"Who are you, $age year-old person name $name?")
  }
}

/**
 * Scala looks for Person.unapply(...) and Address.unapply(...) and calls them.
 * All unapply method return an Option[TupleN[...]], where N is number of values
 * that can be extracted from the object, three for the Person case class, and the
 * types that parameterize the tuple match the values that can be extracted,
 * String, Int, and Address, in this case. So, the Person companion object that the
 * compiler generates looks like this:
 */

object PersonExtractor {

  def apply(name: String, age: Int, address: Address) = new Person(name, age, address)

//  def unapply(p: Person): Option[Tuple3[String, Int, Address]] = Some((p.name, p.age, p.address))

  def unapply(p: Person): Option[(String, Int, Address)] = Some((p.name, p.age, p.address))
}

