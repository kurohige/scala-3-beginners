package com.rockthejvm.part1basics

object CBNvsCBV {

  // CBV = call by value = arguments are evaluted before function invocation
  def aFunction(arg: Int): Int = arg + 1
  val aComputation: Unit = aFunction(23 + 67)

  // CBN = call by name = arguments are passed literally and evaluated at every reference
  def aByNameFunction(arg: => Int): Int = arg + 1
  def anotherComputation = aByNameFunction(23 + 67)

  def printTwiceByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }

  def printTwiceByName(x: => Long): Unit = {
    println("By name: " + x)
    println("By name: " + x)
  }

  def main(args: Array[String]): Unit = {
    printTwiceByValue(System.nanoTime())
    printTwiceByName(System.nanoTime())

  }

}
