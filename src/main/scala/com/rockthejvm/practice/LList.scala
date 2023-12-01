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

  def map[B](transformer: A=> B): LList[B]
  def filter(predicate: A=> Boolean): LList[A]
  def flatMap[B](transformer: A=>LList[B]): LList[B]

  // HOFs and curries exercises
  def foreach(f: A => Unit): Unit = {
    @tailrec
    def forEachTailrec(remainder: LList[A], acc: Unit): Unit =
      if (remainder.isEmpty) acc
      else forEachTailrec(remainder.tail, f.apply(remainder.head))

    forEachTailrec(this, ())
  }

  def sort(compare: (A, A) => Int): LList[A] = {
    def insert(x: A, sortedList: LList[A]): LList[A] =
      if (sortedList.isEmpty) Cons(x, Empty())
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))

    def insertSort(remainder: LList[A], acc: LList[A]): LList[A] =
      if (remainder.isEmpty) acc
      else insertSort(remainder.tail, insert(remainder.head, acc))

    insertSort(this, Empty())
  }

  def zipWith[B, C](anotherList: LList[B], zip: (A, B) => C): LList[C] =
    if (this.isEmpty || anotherList.isEmpty) Empty()
    else Cons(zip(this.head, anotherList.head), this.tail.zipWith(anotherList.tail, zip))

  def foldLeft[B](start: B)(operator: (B, A) => B): B =
    if (this.isEmpty) start
    else this.tail.foldLeft(operator(start, this.head))(operator)
}

  case class Empty[A]() extends LList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: LList[A] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def toString: String = "[]"

    override infix def ++(anotherList: LList[A]): LList[A] = anotherList

    override def map[B](transformer: A => B): LList[B] = Empty()
    override def filter(predicate: A => Boolean): LList[A] = this
    override def flatMap[B](transformer: A=> LList[B]): LList[B] = Empty()

    // HOFs and curries exercises
    override def foreach(f: A => Unit): Unit = ()
    override def sort(compare: (A, A) => Int): LList[A] = this
    override def zipWith[B, C](anotherList: LList[B], zip: (A, B) => C): LList[C] =
      if (!anotherList.isEmpty) throw new IllegalArgumentException("Zipping lists of non-equal length")
      else Empty()

    override def foldLeft[B](start: B)(operator: (B, A) => B): B = start

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

    override def foreach(f: A => Unit): Unit = {
      f(head)
      tail.foreach(f)
    }

    override def sort(compare: (A, A) => Int): LList[A] = {
      // insertion sort, 0(n^2), stack safe recursive
      def insert(x: A, sortedList: LList[A]): LList[A] =
        if (sortedList.isEmpty) Cons(x, Empty())
        else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
        else Cons(sortedList.head, insert(x, sortedList.tail))

      val sortedTail = tail.sort(compare)
      insert(head, sortedTail)
    }

    override def zipWith[B, C](anotherList: LList[B], zip: (A, B) => C): LList[C] =
      if (anotherList.isEmpty) throw new IllegalArgumentException("Zipping lists of non-equal length")
      else Cons(zip(head, anotherList.head), tail.zipWith(anotherList.tail, zip))

    override def foldLeft[B](start: B)(operator: (B, A) => B): B = {
      tail.foldLeft(operator(start, head))(operator)
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
    override def map[B](transformer: A => B): LList[B] =
      new Cons(transformer.apply(head), tail.map(transformer))

    /*
       example
        [1,2,3].filter(n % 2 == 0) =
        [2,3].filter(n % 2 == 0) =
        new Cons(2, [3].filter(n % 2 == 0)) =
        new Cons(2, Empty.filter(n % 2 == 0)) =
        new Cons(2, Empty) =
        [2]
     */
    override def filter(predicate: A => Boolean): LList[A] =
      if (predicate.apply(head)) new Cons(head, tail.filter(predicate))
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
    override def flatMap[B](transformer: A=> LList[B]): LList[B] =
      transformer.apply(head) ++ tail.flatMap(transformer)

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

//trait MyPredicate[T] {
//  def test(element: T): Boolean
//}
//
//class EvenPredicate extends MyPredicate[Int] {
//  override def test(element: Int): Boolean = element % 2 == 0
//}
//
////trait MyTransformer[A, B] { // -A means contravariant
////  def transform(element: A): B
////}
//
//class Doubler extends MyTransformer[Int, Int] {
//  override def transform(element: Int): Int = element * 2
//}
//
//class DoublerList extends MyTransformer[Int, LList[Int]] {
//  override def transform(value: Int) =
//    Cons(value, new Cons(value + 1, new Empty))
//}

// find test
object LList{
  def find[A](list: LList[A], predicate: A => Boolean): A =
    if (list.isEmpty) throw new NoSuchElementException
    else if (predicate.apply(list.head)) list.head
    else find(list.tail, predicate)

}

object LListTest {
  def main(args: Array[String]): Unit = {

    val empty= new Empty[Int]
    println(empty.isEmpty)

    val first3Numbers = Cons(1,  Cons(2,  Cons(3, Empty())))
    val first3Numbers_v2 = empty.add(1).add(2).add(3)
    println(first3Numbers.head)
    println(first3Numbers_v2.isEmpty)

    val someStrings = new Cons("Scala", Cons("Java",  Cons("Python", Empty())))
    println(someStrings)

    val evenPredicate = new Function[Int, Boolean]{
      override def apply(element: Int): Boolean = element % 2 == 0
    }

    val doubler = new (Int => Int){
      override def apply(element: Int): Int = element * 2
    }

    val doublerList = new ((Int) => LList[Int]) {
      override def apply(element: Int): LList[Int] = Cons(element, Cons(element + 1, Empty()))
    }


    val numbersDoubled = first3Numbers.map(doubler)
    val numbersDoubled_v2 = first3Numbers.map(x=> x * 2)
    val numbersDoubled_v3 = first3Numbers.map(_ * 2)
    println(numbersDoubled)

    // map testing)
    val numberNested = first3Numbers.map(doublerList)
    val numberNested_v2 = first3Numbers.map(value => Cons(value, Cons(value + 1, Empty())))
    println(numberNested)

    // filter testing
    val evenNumbers = first3Numbers.filter(evenPredicate)
    val evenNumbers_v2 = first3Numbers.filter(elem => elem % 2 == 0)
    val evenNumbers_v3 = first3Numbers.filter(_ % 2 == 0)
    println(evenNumbers)

    // test concatenation
    val listInBothWays = first3Numbers ++ first3Numbers_v2
    println(listInBothWays)

    // flatMap testing
    val flatMapped = first3Numbers.flatMap(doublerList)
    val flatMapped_v2 = first3Numbers.flatMap(value => Cons(value, Cons(value + 1, Empty())))
    println(flatMapped)

    // find test
    println(LList.find[Int](first3Numbers, _ % 2 == 0))//2
//    println(LList.find[Int](first3Numbers, new MyPredicate[Int] {
//      override def test(element: Int): Boolean = element > 5
//    }))//1

    // foreach test
    first3Numbers.foreach(println)

    // sort test
    println(first3Numbers_v2.sort(_ - _))

    // zipWith test
    val someStrings_v2 = new Cons("I", Cons("love", Cons("Scala", Empty())))
    val zippedList = first3Numbers.zipWith(someStrings_v2, (number, string) => s"$number=$string")
    println(zippedList)

    // foldLeft test
    println(first3Numbers.foldLeft(0)(_ + _))
  }
}