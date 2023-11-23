package com.rockthejvm.part1basics

object Functions {
  def main(args: Array[String]): Unit = {
    // defining a function
    def aFunction(a: String, b: Int): String = {
      a + " " + b
    }

    // calling a function
    val aFunctionResult = aFunction("hello", 3)
    println(aFunctionResult)

    // parameterless function
    def aParameterlessFunction: Int = 42
    println(aParameterlessFunction)

    def aNoArgFunction(): Int = 42
    println(aNoArgFunction())

    // recursive function
    def aRepeatedFunction(aString: String, n: Int): String = {
      if (n == 1) aString
      else aString + aRepeatedFunction(aString, n - 1)
    }
    println(aRepeatedFunction("hello", 3))

    // when you need loops, use recursion

    // function with side effects
    def aFunctionWithSideEffects(aString: String): Unit = println(aString)

    // code blocks
    val aCodeBlock = {
      val x = 2
      val y = x + 1
      if (y > 3) "hello" else "goodbye"
    }
    println(aCodeBlock)

    // exercise
    def greetingForKids(name: String, age: Int): String = {
      s"Hi, my name is $name and I am $age years old."
    }

    def factorial(n: Int): Int = {
      if (n <= 0) 0
      else if (n == 1) 1
      else n * factorial(n - 1)
    }
    println(factorial(5))

    def fibonacci(n: Int): Int = {
      if (n <= 2) 1
      else fibonacci(n - 1) + fibonacci(n - 2)
    }
    println(fibonacci(8))

    def isPrime(n: Int): Boolean = {
      def isPrimeUntil(t: Int): Boolean = {
        if (t <= 1) true
        else n % t != 0 && isPrimeUntil(t - 1)
      }
      isPrimeUntil(n / 2)
    }

    println(isPrime(37))
    println(isPrime(2003))
    println(isPrime(37 * 17))
  }

}
