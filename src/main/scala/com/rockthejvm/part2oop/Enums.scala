package com.rockthejvm.part2oop

object Enums {

  // enums
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE
    // enums can have methods and fields
    def openDocument(): Unit =
      if(this == READ) println("opening document...")
      else println("you don't have permission to open this document")

  }

  //val somePermission: Permissions = Permissions.READ

  // constructor args
  enum PermissionsWithBits(val bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
//    def fromBits(bits: Int): PermissionsWithBits = {
//      val permissions = PermissionsWithBits.values.filter(p => (bits & p.bits) != 0)
//      permissions.reduce((a, b) => a | b)
//    }
  }

  // standard API
 //val somePermissionsOrdinal = somePermission.ordinal // 0
  val allPermissions = PermissionsWithBits.values // Array(READ, WRITE, EXECUTE, NONE)
  val readPermission: Permissions = Permissions.valueOf("READ") // READ

  def main(args: Array[String]): Unit = {
    //somePermission.openDocument()
  }

}
