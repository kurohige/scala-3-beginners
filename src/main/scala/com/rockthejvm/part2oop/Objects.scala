package com.rockthejvm.part2oop

object Objects {

  object MySingleton { // type + theo nly instance of this type
    val aField = 45
    def aMethod(x: Int) = x + 1

  }
  val theSignleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSignleton == anotherSingleton // true
  // objects can have fields and methods
  val theSingletonField = MySingleton.aField
  val theSingletonMethod = MySingleton.aMethod(43)

  class Person(name: String) {
    def sayHi() = s"Hi, my name is $name"
  }

  // companions = class + object with the same name in the same file
  object Person {
    // companions can access each other's private members
    val N_EYES = 2
    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  // methods and field in classes are used for instance-dependent functionality
  val mary = new Person("Mary")
  val mary_v2 = new Person("Mary")
  val marysGreeting = mary.sayHi()
  mary == mary

  //methods and fields in objects are used for instance-independent functionality - "static"
  val humansCanFly = Person.canFly
  val nEyesHuman = Person.N_EYES

  // equality
  // 1 - referential equality - usually defined as ==
  // eq compares the references in memory
  val sameMary = mary eq mary_v2 // false, different instances
  val sameSingleton = theSignleton eq anotherSingleton // true, same instance
  // 2 - equality of "sameness" - in Java defined as .equals
  val sameMary_v2 = mary equals mary_v2 //
  val sameMary_v3 = mary == mary_v2 // same as equals

  // objects can extend classes
  object BigFoot extends Person("Big Foot")

  // scala application = object + def main(args: Array[String]): Unit
  def main(args: Array[String]): Unit = {

  }

}
