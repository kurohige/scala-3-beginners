package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// head -> [1] -> [2] -> [3] -> null
abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element: A): LList[A] = Cons(element, this)

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A] // infix means we can use it as a method between two objects

  def map[B](transformer: MyTransformer[A, B]): LList[B]
  def filter(predicate: MyPredicate[A]): LList[A]
  def flatMap[B](transformer: MyTransformer[A, LList[B]]): LList[B]

  }

  case class Empty[A]() extends LList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: LList[A] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def toString: String = "[]"

    override infix def ++(anotherList: LList[A]): LList[A] = anotherList

    override def map[B](transformer: MyTransformer[A, B]): LList[B] = Empty()
    override def filter(predicate: MyPredicate[A]): LList[A] = this
    override def flatMap[B](transformer: MyTransformer[A, LList[B]]): LList[B] = Empty()
  }

  case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A]{ // head and tail are vals, so they are accessible
    override def isEmpty: Boolean = false
    override def toString: String = {
      @tailrec
      def concatenateElements(remainder: LList[A], acc: String): String =
        if (remainder.isEmpty) acc
        else concatenateElements(remainder.tail, s"$acc ${remainder.head}")

      s"[${concatenateElements(this.tail, s"$head")}]" // trim removes the leading space
    }

    /*
      example
       [1,2,3] ++ [4,5]
       = new Cons(1, [2,3] ++ [4,5])
       = new Cons(1, new Cons(2, [3] ++ [4,5]))
       = new Cons(1, new Cons(2, new Cons(3, Empty ++ [4,5])))
       = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
       = [1,2,3,4,5]
     */

    override infix def ++(anotherList: LList[A]): LList[A] = new Cons(head, tail ++ anotherList)

    /*
      example
       [1,2,3].map(n * 2)
       new Cons(2, [2,3].map(n * 2)) =
       new Cons(2, new Cons(4, [3].map(n * 2))) =
       new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2)))) =
       new Cons(2, new Cons(4, new Cons(6, Empty)))) =
       [2,4,6]
     */
    override def map[B](transformer: MyTransformer[A, B]): LList[B] =
      new Cons(transformer.transform(head), tail.map(transformer))

    /*
       example
        [1,2,3].filter(n % 2 == 0) =
        [2,3].filter(n % 2 == 0) =
        new Cons(2, [3].filter(n % 2 == 0)) =
        new Cons(2, Empty.filter(n % 2 == 0)) =
        new Cons(2, Empty) =
        [2]
     */
    override def filter(predicate: MyPredicate[A]): LList[A] =
      if (predicate.test(head)) new Cons(head, tail.filter(predicate))
      else tail.filter(predicate)

    /*
       example
        [1,2,3].flatMap(n => [n, n+1]) =
        [1,2] ++ [2,3].flatMap(n => [n, n+1]) =
        [1,2] ++ [2,3] ++ [3].flatMap(n => [n, n+1]) =
        [1,2] ++ [2,3] ++ [3,4] ++ Empty.flatMap(n => [n, n+1]) =
        [1,2] ++ [2,3] ++ [3,4] ++ Empty =
        [1,2,2,3,3,4]
     */
    override def flatMap[B](transformer: MyTransformer[A, LList[B]]): LList[B] =
      transformer.transform(head) ++ tail.flatMap(transformer)

 }

/**
   Exercise: LList extension

    1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3. MyList:
      - map(transformer) => MyList
      - filter(predicate) => MyList
      - flatMap(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) = [2,4]
    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
 */

trait MyPredicate[T] {
  def test(element: T): Boolean
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(element: Int): Boolean = element % 2 == 0
}

trait MyTransformer[A, B] { // -A means contravariant
  def transform(element: A): B
}

class Doubler extends MyTransformer[Int, Int] {
  override def transform(element: Int): Int = element * 2
}

class DoublerList extends MyTransformer[Int, LList[Int]] {
  override def transform(value: Int) =
    Cons(value, new Cons(value + 1, new Empty))
}

object LListTest {
  def main(args: Array[String]): Unit = {

    val empty= new Empty[Int]
    println(empty.isEmpty)

    val first3Numbers = Cons(1,  Cons(2,  Cons(3, Empty)))
    val first3Numbers_v2 = empty.add(1).add(2).add(3)
    println(first3Numbers.head)
    println(first3Numbers_v2.isEmpty)

    val someStrings = new Cons("Scala", nw Cons("Java",  Cons("Python", Empty)))
    println(someStrings)

    val evenPredicate = new MyPredicate[Int] {
      override def test(element: Int): Boolean = element % 2 == 0
    }

    val doubler = new MyTransformer[Int, Int] {
      override def transform(element: Int): Int = element * 2
    }

    val numbersDoubled = first3Numbers.map(doubler)
    println(numbersDoubled)

    // map testing
    val numbersDoubled_v2 = first3Numbers.map(new Doubler)
    println(numbersDoubled_v2)
    val numberNested = first3Numbers.map(new DoublerList)
    println(numberNested)

    // filter testing
    val evenNumbers = first3Numbers.filter(evenPredicate)
    println(evenNumbers)

    // test concatenation
    val listInBothWays = first3Numbers ++ first3Numbers_v2
    println(listInBothWays)

    // flatMap testing
    val flatMapped = first3Numbers.flatMap(new DoublerList)
    println(flatMapped)
  }
}