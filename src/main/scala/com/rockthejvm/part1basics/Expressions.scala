package com.rockthejvm.part1basics

object Expressions {
  def main(args: Array[String]): Unit = {
    // expressions vs instructions
    val meaningOfLife = 42 // an expression
    val theUnit = println("Hello, Scala") // Unit = "no meaningful value" == void in other languages

    // instructions are executed (Java, Python, C, C++, ...)
    // expressions are evaluated (Scala, Haskell, ...)

    // expressions are structures that can be evaluated to a value
    val anExpression = 40 + 2

    // mathematical expressions: -, +, *, /, &, |, ^, <<, >>, >>> (right shift with zero extension)
    val mathExpression = 2 + 3 * 4

    // comparison expressions: ==, !=, >, >=, <, <=
    val equalityTest = 2 == 3

    // boolean expressions: !, &&, ||
    val negation = !equalityTest

    // if-expression
    val ifExpression = if (meaningOfLife > 43) 56 else 999 // if-expression
    val chainedIfExpression =
      if (meaningOfLife > 43) 56
      else if (meaningOfLife < 0) -2
      else if (meaningOfLife > 999) 999
      else 0

    // code blocks
    val aCodeBlock = {
      // definitions
      val aLocalValue = 67

      // value of the block is the value of the last expression
      aLocalValue + 3
    }

    // instructions vs expressions
    val theUnit2 = println("Hello, Scala") // Unit = "no meaningful value" == void in other languages
    val someValue = {
      2 < 3
    }

    val someOtherValue = {
      if (someValue) 239 else 986
      42
    }
  }

}
