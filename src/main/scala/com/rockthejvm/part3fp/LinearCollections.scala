package com.rockthejvm.part3fp

object LinearCollections {

  // Seq = well-defined ordering + indexing
  def testSeq(): Unit = {
    val aSequence = Seq(1, 2, 3, 4)
    // main API: index an element
    val thirdElement = aSequence(2) // O(1) complexity
    // map, flatMap, filter, foreach, for-comprehensions
    val anIncrementedSequence = aSequence.map(_ + 1) // O(n) complexity
    val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x + 1)) // O(n) complexity

    val reversed = aSequence.reverse // O(n) complexity
    val concatenation = aSequence ++ Seq(5, 6, 7) // O(n) complexity
    val sortedSequence = aSequence.sorted // O(n log n) complexity
    val sum = aSequence.foldLeft(0)(_ + _) // O(n) complexity - 10
    val stringRep = aSequence.mkString(", ") // "1, 2, 3, 4"

    println(aSequence)
    println(concatenation)
    println(sortedSequence)
  }

  // lists = linear sequences
  def testList(): Unit = {
    val aList = List(1, 2, 3, 4)
    // same API as Seq
    val firstElement = aList.head // O(1) complexity - you can extract the head of a list in constant time
    val rest = aList.tail // O(1) complexity - you can get the tail of a list in constant time
    // appending and prepending
    val aBiggerList = 0 +: aList :+ 5 // O(1) complexity
    val prepending = 0 :: aList // O(1) complexity "::" is pronounced "cons"
    // utility methods
    val scalax5 = List.fill(5)("Scala") // List("Scala", "Scala", "Scala", "Scala", "Scala")
  }

  // ranges
  def testRanges(): Unit = {
    val aRange: Seq[Int] = 1 to 10
    // same Seq API
    (1 to 10).foreach(_ => println("Scala rocks!"))
  }

  // arrays
  // int[] = Array[Int]
  def testArrays(): Unit = {
    val anArray = Array(1, 2, 3, 4)// int[] as in the JVM
    // same Seq API - arrays are not Seqs but i can be converted
    val aSequence: Seq[Int] = anArray.toIndexedSeq
    // an array is mutable, a sequence is not
    anArray.update(2, 0) // anArray(2) = 0 - no new array is allocated
  }

  // vectors = fast Seqs for a large of amount of data
  def testVectors(): Unit = {
    val aVector = Vector(1, 2, 3, 4)
    // same Seq API
    val prepending = 0 +: aVector // O(log n) complexity
    val appending = aVector :+ 5 // O(log n) complexity
    // vectors are fast for large sizes
  }

  def main(args: Array[String]): Unit = {
    testRanges()
  }

}
