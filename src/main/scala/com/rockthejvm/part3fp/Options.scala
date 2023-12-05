package com.rockthejvm.part3fp

import scala.util.Random

object Options {

  // options = "collections" which contain at most one element
  val anOption: Option[Int] = Option(2)
  val anEmptyOption: Option[Int] = Option.empty[Int]

  // alt version
  val aPresentValue: Option[Int] = Some(42)
  val anEmptyOption_v2: Option[Int] = None

  val isEmpty: Boolean = anOption.isEmpty
  val innerValue:Int = anOption.getOrElse(0)

  // map, flatMap, filter
  val anIncrementedOption = anOption.map(_ + 1) // Some(3)
  val aFilteredOption = anOption.filter(_ % 2 == 0) // None
  val aFlatMappedOption = anOption.flatMap(x => Option(x * 10)) // Some(20)

  // work with unsafe API
  def unsafeMethod(): String = null
  def fallbackMethod(): String = "A valid result"

  // defensive programming
  val stringLength = Option(unsafeMethod()).map(_.length)

  // use-case for orElse
  val someResult = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // DESIGN SAFE API
  def betterUnsafeMethod(): Option[String] = None
  def betterFallbackMethod(): Option[String] = Some("A valid result")
  val betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  // example: Map.get
  val phonebook = Map(
    "Daniel" -> 123,
    "John" -> 456
  )
  val marysPhoneNumber = phonebook.get("Mary") // Option[Int]

  /*
    Exercises
    get the host and port from the config map
      try to open a connection,
      print "Connection successful"
      otherwise print "Connection failed"
   */

  val config: Map[String, String] = Map(
    "host" -> "176.45.32.1",
    "port" -> "8081"
  )

  class Connection {
    def connect = "Connection successful" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(_.connect)

  // compact
  val connectionStatus_v2 = config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p))
      .map(_.connect)
    )

  // for-comprehension
  val connectionStatus_v3 = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect


  def main(args: Array[String]): Unit = {
    println(connectionStatus.getOrElse("Connection failed"))
  }

}
