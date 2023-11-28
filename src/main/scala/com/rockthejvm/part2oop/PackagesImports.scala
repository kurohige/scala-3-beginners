package com.rockthejvm.part2oop

object PackagesImports {

  // packages = groups of definitions ~ "Folders"

  // fully qualified name
  val aList: com.rockthejvm.practice.LList[Int] = ??? // throws non implemented error

  // import the package
  import com.rockthejvm.practice.LList
  val anotherList : LList[Int] = ???

  // importing under an alias
  import java.util.{List as JList}
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: MyPredicate[Int] = ???

  /// import multiple symbols
  import PhysicsConstant.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything but something
  object PlayingPhysics{
    import PhysicsConstant.{PLACK_CONSTANT as _, *}
    val c = SPEED_OF_LIGHT
    // val pank = PLACK_CONSTANT // error
  }

  def main(args: Array[String]): Unit = {

  }
}

object PhysicsConstant{
  // constants
  val SPEED_OF_LIGHT = 299792458 // m/s
  val PLACK_CONSTANT = 6.62607015e-34 // J * s
  val EARTH_GRAVITY = 9.8 // m/s^2
}