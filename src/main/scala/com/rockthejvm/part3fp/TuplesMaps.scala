package com.rockthejvm.part3fp

object TuplesMaps {

  // tuples = finite ordered "lists"
  val aTuple = (2, "Rock the JVM") // Tuple2[Int, String] = (Int, String)
  // you can access elements by index
  val firstField = aTuple._1 // 2
  val secondField = aTuple._2 // "Rock the JVM"
  // tuples copy method
  val aCopiedTuple = aTuple.copy(_1 = 3) // (3, "Rock the JVM")

  // tuples of 2 elements
  val aTuple_v2 = 2 -> "Rock the JVM" // sugar for or identical to == (2, "Rock the JVM")

  // maps = finite key-value collections -> keys -> values
  val aMap = Map()

  val phonebook = Map(
    "Jose" -> 123,
    "Daniel" -> 345,
    "Jane" -> 789
  ).withDefaultValue(-1) // default value for non-existent keys

  // core APIs
  val phonebookHasDaniel = phonebook.contains("Daniel") // true
  val marysPhoneNumber = phonebook.apply("Mary") // throws NoSuchElementException

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing // Map("Jose" -> 123, "Daniel" -> 345, "Jane" -> 789, "Mary" -> 678)

  // remove a key
  val phonebookWithoutDaniel = phonebook - "Daniel" // Map("Jose" -> 123, "Jane" -> 789)

  // list -> map
  val phonebookList = List(("Daniel", 345), ("Jane", 789)).toMap // Map("Daniel" -> 345, "Jane" -> 789)

  // map -> linnear collection
  val linearPhonebook = phonebook.toList // List[(String, Int)] = List(("Jose", 123), ("Daniel", 345), ("Jane", 789))

  // map, flatMap, filter
  /*
    - map: applies a function to all the values in the map
    - flatMap: applies a function which returns a key-value pair to all the values in the map
    - filterKeys: removes all the key-value pairs for which the key does not satisfy a predicate
    - mapValues: applies a function to all the values in the map
   */
  val aProcessedPhonebook = phonebook.map(pair => pair._1.toLowerCase -> pair._2 * 10) // Map("jose" -> 1230, "daniel" -> 3450, "jane" -> 7890)

  // filtering keys
  val noJs = phonebook.view.filterKeys(_.startsWith("J")).toMap // Map("Jose" -> 123, "Jane" -> 789)

  // mapping values
  val prefixNumbers = phonebook.view.mapValues(number => s"0255-$number").toMap // Map("Jose" -> "0255-123", "Daniel" -> "0255-345", "Jane" -> "0255-789")

  // other collections can create maps
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // Map("B" -> List("Bob"), "J" -> List("James", "Jim"), "A" -> List("Angela"), "M" -> List("Mary"), "D" -> List("Daniel")


  def main(args: Array[String]): Unit = {
    println(phonebookHasDaniel)
    println(marysPhoneNumber)
    println(phonebook)
    println(nameGroupings)
  }

}
