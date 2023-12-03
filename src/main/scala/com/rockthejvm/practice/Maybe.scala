package com.rockthejvm.practice

abstract class Maybe[A] {
  def map[B](f: A => B): Maybe[B]
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
  def filter(p: A => Boolean): Maybe[A]

}

case class MaybeNot[A]() extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = MaybeNot[B]()
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = MaybeNot[B]()
  override def filter(p: A => Boolean): Maybe[A] = this
}

case class Just[A](val value: A) extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = Just(f(value))
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(value)
  override def filter(p: A => Boolean): Maybe[A] = if (p(value)) this else MaybeNot[A]()
}

object MaybeTest {

  def main(args: Array[String]): Unit = {
    val just3 = Just(3)
    println(just3)
    println(just3.map(_ * 2))
    println(just3.flatMap(x => Just(x % 2 == 0)))
    println(just3.filter(_ % 2 == 0))
  }

}