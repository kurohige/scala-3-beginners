package com.rockthejvm.part3fp

object AnonymousFunctions {

  // instances of FunctionN
  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // Lambdas = anonymous functions instances
  val doubler_v2: Int => Int = (x: Int) => x * 2 // identical

  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b // new Function2[Int, Int, Int] { ... }

  // zero-arg lambdas
  val justDoSomething: () => Int = () => 3
  val anInvocation = justDoSomething() // 3

  // alt syntax with curly braces
  val stringToInt = { (str: String) =>
    // implementation: code block
    str.toInt
  }

  val stringToIntBoring = (str: String) => str.toInt

  // type inference
  val doubler_v3: Int => Int = x => x * 2 // type inferred by compiler
  val adder_v2: (Int, Int) => Int = (a, b) => a + b // type inferred by compiler

  // shortest possible lambda
  val doubler_v4: Int => Int = _ * 2 // equivalent to x => x * 2
  val adder_v3: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /**
   * Exercises
   * 1. Replace all FunctionN Instantiations with lambdas
   * 2. Rewrite the "special" adder as an anonymous function
   */
  def main(args: Array[String]): Unit = {

  }

}
