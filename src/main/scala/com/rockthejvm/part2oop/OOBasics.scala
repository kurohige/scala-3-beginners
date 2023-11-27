package com.rockthejvm.part2oop

object OOBasics {

  // class definition
  class Person(val name: String, age: Int) { // constructor signature
    // class fields
    val allCaps = name.toUpperCase()


    // class methods
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name") // this.name refers to the current instance
    // of Person class while as plain name refers to the parameter of the method

    // signature differs
    // overloading
    def greet(): String = s"Hello, my name is $name and I am $age years old."

    // auxiliary constructor
    def this(name: String) = this(name, 0) // calls the primary constructor

    def this() = this("John Doe") // calls the auxiliary constructor with one parameter

  }


  // class instantiation
  val aPerson:Person = new Person("Jose", 33)
  val jose = aPerson.name // class parameter != class field
  val joseSayHiToDaniel = aPerson.greet("Daniel")

  /**
   * Exercise: imagene we're creating a backend for a book publihsihing house.
   * Create a novel and a writer class.
   *
   * writer: first name, surname, year
   * - method fullname
   *
   * novel: name, year of release, author
   * - authorAge
   * - isWrittenBy(author)
   * - copy (new year of release) = new instance of Novel with new year of release
   * */

  class Writer(val firstName: String, val surname: String, val yearOfBirth: Int) {
    def fullName: String = s"$firstName $surname"
  }

  class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
    def authorAge: Int = yearOfRelease - author.yearOfBirth
    def isWrittenBy(author: Writer): Boolean = author == this.author
    def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
  }

  /**
   * Exercise #2: an immutable counter class
   * - constructed with an initial count
   * - increment/decrement => NEW instance of counter
   * - increment(n)/decrement(n) => NEW instance of counter
   * - print
   *
   * benefit of immutability: thread-safe
   * + well in distributed environments
   * + easier to read and understand code
   */

  class immutableCounter(val count: Int = 0) {
    def increment: immutableCounter = new immutableCounter(count + 1) // immutability
    def decrement: immutableCounter = new immutableCounter(count - 1)

    def increment(n: Int): immutableCounter = {
      if (n <= 0) this
      else increment.increment(n - 1)
    }

    def decrement(n: Int): immutableCounter = {
      if (n <= 0) this
      else decrement.decrement(n - 1)
    }

    def print: Unit = println(count)
  }

  def main(args: Array[String]): Unit = {
     val charlesDickens = new Writer("Charles", "Dickens", 1812)
     val charlesDickensImpostor = new Writer("Charles", "Dickens", 2021)
     val novel = new Novel("Great Expectations", 1861, charlesDickens)

     println(charlesDickens.fullName)
    println(novel.authorAge)

    println(novel.isWrittenBy(charlesDickens))
    println(novel.isWrittenBy(charlesDickensImpostor))
   }



}
