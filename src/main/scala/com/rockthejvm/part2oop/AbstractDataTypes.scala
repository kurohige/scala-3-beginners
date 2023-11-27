package com.rockthejvm.part2oop

object AbstractDataTypes {

  // abstract classes
  abstract class Animal {
    val creatureType: String = "wild"
    def eat(): Unit
  }

  // non-abstract classes must implement all abstract fields/methods
  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat(): Unit = println("crunch crunch")
  }

  val aDog: Animal = new Dog

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println(s"I'm a T-Rex and I'm eating ${animal.creatureType}")
  }

  // practical
  // one class inherits from one abstract class, but can mix in multiple traits
  // multiple traits
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat(): Unit = println("nomnomnom")
    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  /*
    philosophical differences between abstract classes and traits
    1 - traits do not have constructor parameters
    2 - multiple traits may be inherited by the same class
    3 - traits = behavior, abstract class = "thing"
   */

  def main(args: Array[String]): Unit = {

  }

}
