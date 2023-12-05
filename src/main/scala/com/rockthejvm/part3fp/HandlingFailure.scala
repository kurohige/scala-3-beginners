package com.rockthejvm.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure {

  // try = a potentially failing computation
  val aTry: Try[Int] = Try(42)
  val aFailedTry: Try[Int] = Try(throw new RuntimeException("I failed"))

  // alternatives to try
  val aTry_v2: Try[Int] = Success[Int](42)
  val aFailedTry_v2: Try[Int] = Failure(new RuntimeException("I failed"))

  // main API
  val checkSuccess = aTry.isSuccess // true
  val checkFailure = aTry.isFailure // false

  // map, flatMap, filter
  val anIncrementedTry = aTry.map(_ + 1) // Success(43)
  val aFlatMappedTry = aTry.flatMap(mol => Try(s"My meaning of life is $mol")) // Success(420)
  val aFilteredTry = aTry.filter(_ % 2 == 0) // Failure(NoSuchElementException)

  // WHY: avoid unsafe APIs which can THROW EXCEPTIONS
  def unsafeMethod(): String = throw new RuntimeException("No string for you, dear")

  // defesive: try-catch-finally
  val stringLenghtDefensive = try {
    unsafeMethod().length
  } catch {
    case e: RuntimeException => -1
  } finally {
    // side effects
    println("finally")
  }

  // purely functional
  val stringLengthPure = Try(unsafeMethod()).map(_.length).getOrElse(-1)

  // DESIGN
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("No string for you, dear"))
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val stringLeghthPure_v2 = betterUnsafeMethod().map(_.length)
  val aSafeChain = betterUnsafeMethod().orElse(betterBackupMethod()).map(_.length)

  /**
   * Exercises
   * obtain a connection,
   * then fetch the url,
   * then print the resulting HTML
   */

  val myDesiredURL = "rockthejvm.com"

  val host = "localhost"
  val port = "8080"

  class Connection {
    val random = new Random()

    def get(url: String): String = {
      if (random.nextBoolean()) "some data"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    val random = new Random()

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def safeGet(url: String): Try[String] = Try(getConnection(host, port).get(url))
  }

  // defensive style
  val connection = try {
    val conn = HttpService.getConnection(host, port)
    val html = try {
      conn.get(myDesiredURL)
    }
    catch
      case e: RuntimeException => s"Couldn't fetch the URL: $e"
  } catch
    case _: RuntimeException => println("Could not connect to the server")

  // purely functional approach
  val maybeConn = Try(HttpService.getConnection(host, port))
  val maybeHTML = maybeConn.flatMap(conn => Try(conn.get(myDesiredURL)))
  val finalResult = maybeHTML.fold(e => s"Couldn't fetch the URL: $e", html => html)

  // for-comprehensions
  val finalResult_v2 = for {
    conn <- maybeConn
    html <- Try(conn.get(myDesiredURL))
  } yield html

  def main(args: Array[String]): Unit = {

  }

}
