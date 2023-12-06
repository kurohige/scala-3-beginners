package com.rockthejvm.part4pm

import scala.util.Random

object PatternMatching {

  // switch on steroids
  val random = new Random()
  val aValue = random.nextInt(100)

  val description = aValue match {
    case 1 => "the first"
    case 2 => "the second"
    case 3 => "the third"
    case _ => s"something else: $aValue" // default case
  }

  // decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am $a years old, but I can't drink in the US."
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "I don't know who I am."
  }

  /*
    Patterns are matched in order: put the most specific patterns first.
    what if no cases match? MatchError
    what's the type returned? the lowest common ancestor of all the types on the RHS of each branch
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")
  val animalPM = anAnimal match {
    case Dog(breed) => s"Got a dog of breed $breed"
    case Cat(meowStyle) => s"Got a cat with meow style $meowStyle"
  }

  /**
   * Exercises
   *  show(Sum(Number(2), Number(3))) => "2 + 3"
   *  show(sum(sum(Number(2), Number3)))
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Number(n) => s"$n"
    case Sum(left, right) => s"${show(left)} + ${show(right)}"
    case Prod(left, right) => {
      def maybeShowParentheses(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => s"(${show(expr)})"
      }
      s"${maybeShowParentheses(left)} * ${maybeShowParentheses(right)}"
    }
  }

  def main(args: Array[String]): Unit = {
    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
    println(show(Prod(Sum(Number(2), Number(3)), Number(4))))
    println(show(Prod(Number(2), Sum(Number(3), Number(4)))))
  }

}
