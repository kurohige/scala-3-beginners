package com.rockthejvm.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntilTailrec(x: Int, accumulator: Int = 0): Int =
    if (x <= 0) accumulator
    else sumUntilTailrec(x - 1, accumulator + x)

  def savePicture(dirPath: String, format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit =
    println(s"Saving picture to $dirPath with format $format and dimensions $width x $height")

  def main(args: Array[String]): Unit = {

  }

}
