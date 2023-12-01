package com.rockthejvm.part3fp

object WhatsAFunction {

  // FP: functions are first-class citizens
  // JVM
  // 1 - pass functions as arguments
  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  val doubler: MyFunction[Int, Int] = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  val meaningOfLife = 42
  val meaningOfLifeDoubled: Int = doubler(meaningOfLife) // doubler.apply(meaningOfLife)

  // functions types = Function1[A, B]
  val doublerStandard: Function1[Int, Int] = new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  val meaningDoubled_v2: Int = doublerStandard(meaningOfLife)

  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  val anAddition = adder(4, 5) // adder.apply(4, 5)
  
  val athreeArgFunction = new Function4[Int, String, Double, Boolean, Int] {
    override def apply(a: Int, b: String, c: Double, d: Boolean): Int = ???
  }
  // all functions are instances of FunctionX with apply methods

  /**
   * Ecercises
   * 1. a function which takes 2 strings and concatenates them
   * 2. transform the MyPredicate and MyTransformer into function types
   * 3. define a function which takes an int and returns another function which takes an int and returns an int 
   */
  
  // 1
  val stringConcatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  // 3
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  val superAdder_v2 = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }


  val adder2 = superAdder(2)
  val anAddition_v2 = adder2(67)// 69
  // currying
  val anAddition_v2_curried = superAdder(2)(67)// 69

  val adderv2= superAdder_v2(2)
  val anAddition_v3 = adderv2(67)// 69
  // currying
  val anAddition_v3_curried = superAdder_v2(2)(67)// 69

  def main(args: Array[String]): Unit = {
    println(stringConcatenator("I love ", "Scala"))
  }
}
