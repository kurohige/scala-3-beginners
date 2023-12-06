package com.rockthejvm.part4pm

object PatternsEveryWhere {

  // big idea #1
  val potentialFailure = try {
    throw new RuntimeException("I'm innocent, I swear!") // nothing
  } catch {
    case e: RuntimeException => "I caught a Runtime exception"
    case npe: NullPointerException => "I caught a NPE"
    case _ => "I caught something else"
  }

  // big idea #2: for comprehensions (generators) are based on Pattern Matching
  val aList = List(1,2,3,4)
  val evenNumbers = for {
    x <- aList if x % 2 == 0
  } yield 10 * x

  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // big idea #3: new syntax for partial functions (python-like)
  val aTuple= (1,2,3)
  val (a, b, c) = aTuple

  val head::tail = tuples // head = tuples.head, tail = tuples.tail


  def main(args: Array[String]): Unit = {

  }

}
