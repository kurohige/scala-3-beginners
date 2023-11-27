package com.rockthejvm.part2oop

object CaseClasses {

  class Person(name: String, age: Int)

  // case classes as lightweight data structures with little boilerplate
  case class PersonCC(name: String, age: Int) {
    // some logic
  }

  // 1- class arguments are no fields
  val aPerson = new PersonCC("Jose", 25)
  val joseAge = aPerson.age // 25

  // 2- toString, equals, hashCode, copy
  val aPerson2 = aPerson.toString // PersonCC(Jose,25)
  val joseDuped = new PersonCC("Jose", 25)
  val isSameJose = aPerson == joseDuped // true

  // 3- case classes have handy copy methods
  val youngerJose = aPerson.copy(age = 23) // PersonCC("Jose", 23)

  // 4- case classes have companion objects
  val thePersonSingleton = PersonCC // companion object
  val mary = PersonCC("Mary", 23) // PersonCC.apply("Mary", 23)

  // 5- case classes are serializable
  // use-case: Akka

  // 6- case classes have extractor patterns = case classes can be used in PATTERN MATCHING

  // case class require constructor arguments
  // 7 - case classes are by default immutable and do not require the constructor arguments to be val/var
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  case class CCWithARgListNoArgs[A]() // legal

  def main(args: Array[String]): Unit = {
    println(aPerson)
    println(isSameJose)
  }

}
