package com.rockthejvm.part3fp

object MapFlapMapFilterFor {

  // standard list
  val aList = List(1,2,3) // [1] -> [2] -> [3] -> Nil // [1,2,3]
  val firstElement = aList.head // 1
  val rest = aList.tail // [2,3]

  // map
  val anIncrementedList = aList.map(_ + 1) // [2,3,4]

  // filter
  val onlyOddNumbers = aList.filter(_ % 2 != 0) // [1,3]

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  val aFlatMappedList = aList.flatMap(toPair) // [1,2,2,3,3,4]

  // All the possible combinations of all the elements of those list in the format "1a - black"
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red", "blue")

  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map(color => s"$n$c - $color")))

  /*
    lambda = num => chars.map(char => s"$num$char")
    [1,2,3,4].flatMap(lambda)
    [1a,1b,1c,1d,2a,2b,2c,2d,3a,3b,3c,3d,4a,4b,4c,4d]
    lambda(1) = chars.map(char => s"1$char") = [1a,1b,1c,1d, "2a", ...]
   */

  // for-comprehensions
  val combinationsFor = for {
    n <- numbers if n % 2 == 0 // generator
    c <- chars
    color <- colors
  } yield s"$n$c - $color" // an expression

  // for-comprehensions with Unit
  // if foreach

  /**
   * Exercises
   * 1. LList supports for comprehensions?
   * 2. A small collection of at most ONE element - Maybe[A]
   *   - map, flatMap, filter
   */

  import com.rockthejvm.practice.*
  val lSimpleNumber: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  // map, flatmap, filter
  val incrementedNumbers = lSimpleNumber.map(_ + 1)
  val filteredNumbers = lSimpleNumber.filter(_ % 2 == 0)
  val toPairLList = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = lSimpleNumber.flatMap(toPairLList)


  // map + flatMap = for-comprehensions
  val combinationNumbers = for {
    n <- lSimpleNumber
    c <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$n-$c"

  def main(args: Array[String]): Unit = {
    println(combinations)
    println(combinationsFor)
    for {
      n <- numbers
    } println(n)

    println(combinationNumbers)
  }

}
