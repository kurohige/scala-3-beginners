package com.rockthejvm.part2oop

object Inheritance {

  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nomnom")
  }

class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch crunch")
    }
  }

  val cat = new Cat

  class Person(val name: String, val age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, val idCard: String) extends Person(name, age) // must specify super-constructor on class declration

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType = "domestic"
    override def eat(): Unit = {
      super.eat()
      println("mmm, I like this bone")
    }
    override def toString: String = s"I'm a $creatureType dog"
  }

  // subtype polymorphism
  val dog: Animal = new Dog("K9")

  // overloading vs overriding
  class Crocodile extends Animal {
    override val creatureType = "very wild"
    override def eat(): Unit = println("I can eat anything, I'm a crocodile")

    // overloading: defining a method with the same name but different signatures
    def eat(what: String): Unit = println(s"I'm eating $what")
  }

  def main(args: Array[String]): Unit = {
    println(dog)
    dog.eat()

  }

}
