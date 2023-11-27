package com.rockthejvm.part2oop

object AccessModifiers {

  class Person(val name: String, private val age: Int) {
    protected def greet(): String = s"Hi, my name is $name and I am $age years old."
    private def watchNetflix(): String = "I am watching Netflix"
  }
  // a protected method can only be called within the person class or with class that extend the person class
  class kid(name: String, age: Int) extends Person(name, age) {
    def greetKid(): String = greet() + "I love to play" // no modifier = "public"
  }

  val aPerson = new Person("Jose", 30)
  aPerson.name // public
  // aPerson.age // private

  // complication
//  class kidWithParents(override val name: String, age: Int, val parent: String) extends Person(name, age) {
//    // override val name: String = "Daniel"
//    val mom = new Person("Angela", 50)
//    val dad = new Person("Daniel", 50)
//
//    def greetParent(): String = s"Hi, my name is $name and my parent is $parent" + mom.greet() + dad.greet()
//  }

  def main(args: Array[String]): Unit = {

  }

}
