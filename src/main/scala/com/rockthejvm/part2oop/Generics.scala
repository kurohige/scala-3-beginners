package com.rockthejvm.part2oop

object Generics {

  // generic classes
  abstract class MyList[A] { // "generic" list - JAVA: abstract class MyList<T>
    // use the type A
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A] {
    // override def head: Int = head
    // override def tail: Int = tail
  }

  val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

  val firstNumber = listOfIntegers.head // Int
  val adding = firstNumber + 1 // Int

  // multiple generic types
  trait MyMap[Key, Value]

  // generic methods
  object MyList {
    def from2Elements[A](a1: A, a2: A): MyList[A] = new NonEmpty[A](a1, new NonEmpty[A](a2, new Empty[A]))
  }

  // calling methods
  val first2Numbers = MyList.from2Elements[Int](1, 2)
  val first2Number_v2 = MyList.from2Elements(1, 2) // type inference
  val first2Numbers_v3 = new NonEmpty(1, new NonEmpty(2, new Empty)) // type inference


  def main(args: Array[String]): Unit = {

  }

}
