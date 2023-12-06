package com.rockthejvm.part4pm
import com.rockthejvm.part3fp.{Cons, Empty, LList}
object AllThePatterns {

  // 1 - constants
  val someValue: Any = "Scala"
  val constants = someValue match {
    case 42 => "meaning of life"
    case "Scala" => "the scala"
    case true => "the truth"
    case AllThePatterns => "a singleton object"
  }

  // 2 - match anything
  val matchAnythingVar = 2 + 3 match {
    case _ => "I matched anything"
  }

  val matchAnything = someValue match {
    case _ => "I matched anything"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val matchTuple = aTuple match {
    case (1, 1) => "I matched (1,1)"
    case (something, 2) => s"I matched something $something and 2"
  }

  // PMs can be nested
  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"I matched something nested $v"
  }

  // 4 - case classes - constructor pattern
  val aList: LList[Int] = Cons(1, Cons(2, Empty))
  val matchList = aList match {
    case Empty => "I matched an empty list"
    case Cons(head, Cons(subhead, subtail)) => s"I matched a list with head $head and subhead $subhead"
  }

  val anOption: Option[Int] = Some(42)
  val matchOption = anOption match {
    case None => "an empty option"
    case Some(value) => "I matched something else"
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,42)
  val matchStandardList = aStandardList match {
    case List(1, _, _, _) => "I matched a list with 4 elements, starting with 1"
    case List(1, _*) => "I matched a list with 1 as the first element"
    case 1 :: List(_) => "I matched a list with 1 as the first element"
    case List(1,2,3) :+ 42 => "I matched a list with 1,2,3 as the first elements and 42 as the last"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchTyped = unknown match {
    case list: List[Int] => "I matched a list of integers"
    case anInt: Int => "I matched an integer"
    case _ => "I matched something else"
  }

// 7 - name binding
  val matchNameBinding = aList match {
    case nonEmptyList @ Cons(_, _) => s"I matched a non-empty list $nonEmptyList"
    case Cons(1, rest @ Cons(2, _)) => s"I matched a list starting with 1 and then 2 $rest"
  }

  // 8 - chained patterns
  val matchChained = aList match {
    case Empty | Cons(0, _) => "I matched an empty list or a list starting with 0"
  }

  // 9 - if guards
  val matchWithGuards = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => "I matched a list with an even number"
  }

  /*
    Exercise
   */
  val aSimpleInt = 45
  val isEven = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // heavy anti-pattern
  val isEven_bad_v2 = if (aSimpleInt % 2 == 0) true else false
  // better
  val isEven_better = aSimpleInt % 2 == 0

  val numbers: List[Int] = List(1,2,3,4)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfInts: List[Int] => "a list of ints"
    case _ => ""
  }

  def main(args: Array[String]): Unit = {

  }

}
