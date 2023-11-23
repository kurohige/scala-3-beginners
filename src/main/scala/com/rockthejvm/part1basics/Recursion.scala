package com.rockthejvm.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion {

  // "repetition = recursion" - Martin Odersky
  def sumUntil(n: Int): Int = {
    if (n <= 0) 0
    else n + sumUntil(n - 1)
  }

  def sumUntil_v2(n: Int): Int = {
    def sumUntilTailrec(x: Int, accumulator: Int): Int = {
      if (x <= 0) accumulator
      else sumUntilTailrec(x - 1, accumulator + x)
    }

    sumUntilTailrec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int = {
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)
  }

  def sumNumbersBetween_v2(a: Int, b:Int) : Int = {
    @tailrec
    def sumTailrec(currentNumber: Int, accumulator: Int): Int =
      if (currentNumber > b) accumulator
      else sumTailrec(currentNumber + 1, currentNumber + accumulator)

    sumTailrec(a, 0)
  }

  /**
   * Exercises
   * 1. Concatenate a string n times
   * 2. Fibonacci function, tail recursive
   * 3. Is isPrime function tail recursive or not?
   */

  def concatString(wordString: String, repTimes: Int): String = {

    @tailrec
    def concatTailrec(accumulator: Int, wordAccumulator: String): String =
      if (accumulator > repTimes) wordAccumulator
      else concatTailrec(accumulator + 1, (wordString + wordAccumulator))

    concatTailrec(0, "")
  }

  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last:Int, previous: Int) : Int =
      if(i >= n) last
      else fiboTailrec(i+1, last + previous, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  def main(args: Array[String]): Unit = {
    println(concatString("Scala ", 5))
  }

}
