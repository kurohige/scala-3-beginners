package com.rockthejvm.part4pm

object BracelessSyntax {

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 = if (2 > 3) {
    "bigger"
  } else {
    "smaller"
  }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then "bigger"
    else "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 - one liner version
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for - comprehensions
  val aForComprehension = for {
    n <- List(1,2,3)
    c <- List('a', 'b', 'c')
  } yield s"$n$c"

  // java-style
  val aForComprehension_v2 = for {
    n <- List(1,2,3)
    c <- List('a', 'b', 'c')
  } yield {
    s"$n$c"
  }

  // compact
  val aForComprehension_v3 =
    for {
      n <- List(1,2,3)
      c <- List('a', 'b', 'c')
    } yield s"$n$c"

  // scala 3
  val aForComprehension_v4 =
    for
      n <- List(1,2,3)
      c <- List('a', 'b', 'c')
    yield s"$n$c"

  // scala 3 - one liner version
  val aForComprehension_v5 = for n <- List(1,2,3); c <- List('a', 'b', 'c') yield s"$n$c"

  // pattern matching
  val meaningOfLife = 42
  val pm = meaningOfLife match {
    case 42 => "the meaning of life"
    case _ => "something else"
  }

  //scala 3
  val pm_v2 = meaningOfLife match
    case 42 => "the meaning of life"
    case _ => "something else"

  // class definitions with significant indentation(same for traits, objects, case classes)
  class Animal: // compiler expects the body of Animal
    def eat() = println("I'm eating")
    def grow() = println("I'm growing")

    // no need for curly braces
  end Animal // end keyword to close the class, it can be used for if, for, methods, classes, traits, objects, etc.

  // anonymous classes
  val anAnimal = new Animal:
    override def eat() = println("I'm eating")

  // indentation = strictly larger indetations
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // indent with spaces and not tabs.
  
  def main(args: Array[String]): Unit = {

  }

}
