package com.rockthejvm.part4pm

object ImperativeProgramming {

  val meaningOfLife: Int = 42

  var aVariable = 99
  aVariable = 100 // side effects - vars can be reassigned

  aVariable += 10 // also works with +=, -=, *=, /=, ...


  // increment/decrement operators don't exist in Scala
  // aVariable++ // not possible
  aVariable += 1 // works, but not the same thing

  // loops
  def testLoop(): Unit =
    var i = 0
    while (i < 10) {
      println(i)
      i += 1
    }



  def main(args: Array[String]): Unit = {

  }

}
