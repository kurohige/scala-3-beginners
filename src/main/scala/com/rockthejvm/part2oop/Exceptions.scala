package com.rockthejvm.part2oop

object Exceptions {

  val aString: String = null
  // aString.length // NullPointerException crash

  // 1 - throwing exceptions
  //val aWeirdValue = throw new NullPointerException // type Nothing

  // type Throwable is the superclass of all exceptions and errors
  // Error, e.g. StackOverflowError, out of memory error
  // Exception, e.g. NullPointerException, IllegalArgumentException, NumberFormatException

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 43
    case e: NullPointerException => 44
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  // 3 - define your own exceptions
  class MyException extends RuntimeException{
    // fields or methods
    override def getMessage: String = "My exception!"
  }

  val exception = new MyException

  /**
   * Exercises
   * 1. Crash your program with an OutOfMemoryError
   * 2. Crash with StackOverflowError
   * 3. find an elem
   */
  // 1
  def soCrash(): Unit = {
    def infinite(): Int = 1 + infinite()

    infinite()
  }

  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String =
      if (n <= 0) acc
      else bigString(n-1, acc + acc)

    bigString(1000000, "Scala")
  }


  def main(args: Array[String]): Unit = {
    //soCrash()

    oomCrash()
  }

}
