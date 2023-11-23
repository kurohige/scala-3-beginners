package com.rockthejvm.part1basics

object ValuesAndTypes {

  def main(args: Array[String]): Unit = {
    // values
    val meaningOfLife: Int = 42 // const
    val aBoolean = false
    val aChar = 'a'
    val aShort: Short = 4613
    val aLong: Long = 5273985273895L
    val aFloat: Float = 2.0f
    val aDouble: Double = 3.14

    // variables
    var aVariable: Int = 4
    aVariable = 5 // side-effects

    // side-effects: println(), whiles, reassigning

    // code blocks
    val aCodeBlock = {
      // definitions
      val aLocalValue = 67

      // value of the block is the value of the last expression
      aLocalValue + 3
    }

    // instructions vs expressions
    // instructions: do something (Java)
    // expressions: give me the value of something (Scala)

    // types
    // type inference
    val anotherInt = 45
    val anotherString = "hello, Scala"

    // compiler can infer types, but we can also specify them
    val aComposedString: String = "hello, Scala"

    // expressions vs instructions
    val theUnit = println("hello, Scala") // Unit = void
    println(theUnit)

    // functions
    def myFunction(x: Int, y: String): String = y + " " + x

    // recursion: stack and tail
    def factorial(n: Int): Int =
      if (n <= 1) 1
      else n * factorial(n - 1)

    // OOP
    class Animal
    class Dog extends Animal
    val aDog: Animal = new Dog // subtyping polymorphism

    // abstract data types
    trait Carnivore {
      def eat(animal: Animal): Unit
    }

    class Crocodile extends Animal with Carnivore {
      override def eat(animal: Animal): Unit = println("crunch!")
    }

    // method notations
    val aCroc = new Crocodile
    aCroc.eat(aDog)
    aCroc eat aDog // infix notation = object method argument

    // anonymous classes
    val aCarnivore = new Carnivore {
      override def eat(animal: Animal): Unit = println("roar roar")
    }
  }

}
