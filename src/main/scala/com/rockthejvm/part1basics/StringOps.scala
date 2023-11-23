package com.rockthejvm.part1basics

object StringOps {

  val aString: String = "Hello, I am learning Scala"

  // string functions
  val secondChar = aString.charAt(1) // 'e'
  val aSubstring = aString.substring(7, 11) // "I am"
  val words = aString.split(" ") // Array("Hello", "I", "am", "learning", "Scala")
  val startsWithHello = aString.startsWith("Hello") // true
  val allCaps = aString.toUpperCase() // "HELLO, I AM LEARNING SCALA" also toLowerCase
  val allDashes = aString.replace(" ", "-") // "Hello,-I-am-learning-Scala"
  val nChars = aString.length // 26

  // other functions
  val reversed = aString.reverse // "alacS gninrael ma I ,olleH"
  val aBunchOfChars = aString.take(5) // "Hello"
  // parse to numberic
  val numberAsString = "2"
  val number = numberAsString.toInt // 2

  // interpolation
  val name = "Jose"
  val age = 33
  val greeting = s"Hello, my name is $name and I am $age years old" // "Hello, my name is Jose and I am 33 years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old" // "Hello, my name is Jose and I will be turning 34 years old"

  // f-interpolation
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute" // "Jose can eat 1.20 burgers per minute"

  // raw-interpolation
  val escaped = "This is a \n newline" // "This is a \n newline"
  val raw = raw"This is a \n newline" // "This is a \n newline"

  def main(args: Array[String]): Unit = {

  }
}
