package com.rockthejvm.part3fp

import scala.annotation.tailrec

object HOFsCurrying {

  // higher order functions (HOFs) - can use other functions as args or result
  val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1

  val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

  // quick exercise
  val superFunction:(Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x, func) => (y => x + y)

  // map, filter, flatMap are examples of HOFs
  // more examples: fold, reduce, scan
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if(n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne: Int => Int = _ + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  def nTimes_v2(f: Int => Int, n: Int): (Int => Int) =
    if(n <= 0) (x => x)
    else (x => nTimes_v2(f, n - 1)(f(x)))

  //val plusTenThousand = nTimes_v2(plusOne, 10000)
  //val tenThousand_v2 = plusTenThousand(0)

  // curried functions = HOFs returning function instances
  val superAdder: Int => Int => Int = x => y => x + y
  val add3 = superAdder(3)
 // val invokeSuperAdder = add3(10)(100)

  // curried methods = methods with multiple parameter/argument lists
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f") // (x: Double) => "%4.2f".format(x)
  val preciseFormat: Double => String = curriedFormatter("%10.8f") // (x: Double) => "%10.8f".format(x)

  /**
   * 1. LList exercises
   *   - foreach method A => Unit
   *     [1,2,3].foreach(x => println(x))
   *
   *   - sort function ((A, A) => Int) => LList[A]
   *     [1,2,3].sort((x, y) => y - x) => [3,2,1]
   *     (hint: use insertion sort)
   *
   *   - zipWith function (LList[A], LList[B], (A, B) => C) => LList[C]
   *     [1,2,3].zipWith([4,5,6], (x, y) => x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]
   *
   *   - foldLeft function (LList[A], B, (B, A) => B) => B
   *     [1,2,3].foldLeft[Int](0)((x, y) => x + y) = 6
   *     0 + 1
   *     1 + 2
   *     3 + 3
   *
   * 2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
   *   fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
   *
   * 3. compose(f, g) => x => f(g(x))
   */

  //2
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x, y)

  val superAdder_v2: Int => Int => Int = toCurry(_ + _)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = (x, y) => f(x)(y)

  val simpleAdder = fromCurry(superAdder)
  // 3
  //def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))
  def compose[A, B, C](f: A => B, g: C => A): C => B = x => f(g(x))
  //def andThen(f: Int => Int, g: Int => Int): Int => Int = x => g(f(x))
  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

  val incrementer =(x: Int) => x + 1
  val doubler = (x: Int) => x * 2
  val composedApplication = compose(incrementer, doubler)
  val aSequencedApplication = andThen(incrementer, doubler)

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
    println(simpleAdder(2, 78))//80
    println(composedApplication(14))//29
    println(aSequencedApplication(14))//30
  }

}
