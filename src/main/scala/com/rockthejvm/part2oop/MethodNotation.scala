package com.rockthejvm.part2oop

object MethodNotation {

  class Person(val name: String, val age: Int, favoriteMovie: String){
    infix def likes(movie: String): Boolean = movie == favoriteMovie
    infix def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    infix def !! (progLanguage: String): String = s"$name is learning $progLanguage"

    infix def +(nickname: String): Person = new Person(s"$name ($nickname)", age, favoriteMovie)

    // prefix notation
    // unary ops: + - ! ~
    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, age + 1, favoriteMovie)
    def unary_- : String = s"$name's alter ego!"

    def isAlive: Boolean = true
    def learns(thing: String): String = s"$name learns $thing"
    def learnsScala: String = this learns "Scala"

    // apply
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", 33, "Inception")
  val john = new Person("John", 35, "Fight Club")

  /**
   * Exercises
   * - a + operator on the person class tha returns a person with a nickname
   *   mary + "the rockstar" => new person "Mary (the rockstar, _, _)"
   * - an unary + operator that increments the age of the person
   *  +mary => new person with the age incremented
   *  - an apply method with an int arg
   *  mary.apply(2) => "Mary watched Inception 2 times"
   */

  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))
    // infix notation - for methods with one argument
    println(mary likes "Fight Club") // equivalent to mary.likes("Fight Club")

    // "operator"
    println(mary + john)
    println(mary.+(john))
    println(1 + 2)
    println(1.+(2))
    println(mary !! "Scala")

    // prefix notation
    println(-mary)

    // postfix notation
    println(mary.isAlive)
    //println(mary isAlive) // discouraged
    
  }

}
