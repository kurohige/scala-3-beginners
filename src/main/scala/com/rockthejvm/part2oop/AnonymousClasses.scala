package com.rockthejvm.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I'm eating")
  }

  val someAnimal: Animal = new SomeAnimal

  val someAnimal_v2: Animal = new Animal {
    override def eat(): Unit = println("I'm eating")
  }

  /*
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat(): Unit = println("I'm eating")
    }

    val someAnimal_v2: Animal = new AnonymousClasses$$anon$1
   */
  
  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name")
  }
  
  val jim = new Person("Jim") {
    override def sayHi(): Unit = println(s"Hi, my name is Jim")
  }

  def main(args: Array[String]): Unit = {

  }

}
