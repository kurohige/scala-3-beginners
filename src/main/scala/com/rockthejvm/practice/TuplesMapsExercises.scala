package com.rockthejvm.practice

import scala.annotation.tailrec

object TuplesMapsExercises {

  /**
   *  Social network = Map[String, Set[String] ]
   *  Friend relationships are mutual
   *  - add a person to the network
   *  - remove a person from the network
   *  - add a friend relationship
   *  - remove a friend relationship - unfriend
   *
   *  - number of friends of a person
   *  - person with most friends
   *  - how many people have NO friends
   *
   *  + if there is a social connection between two people(direct or not)
   */

  def addPerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def removePerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    (network - person).map(pair => (pair._1, pair._2 - person)) // remove all the friendships of the person

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    if (!network.contains(a) || !network.contains(b)) network
    else {
      val friendsOfA = network(a)
      val friendsOfB = network(b)

      network + (a -> (friendsOfA + b)) + (b -> (friendsOfB + a))
    }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    if (!network.contains(a) || !network.contains(b)) network
    else {
      val friendsOfA = network(a)
      val friendsOfB = network(b)

      network + (a -> (friendsOfA - b)) + (b -> (friendsOfB - a))
    }

    // 2
  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) -1
    else network(person).size

  def mostFriends(network: Map[String, Set[String]]): String =
    if (network.isEmpty) throw new RuntimeException("Network is empty, no-one with most friends")
    else {
      /*
        Map(Bob -> Set(Alice, Charlie), Alice -> Set(Bob), Charlie -> Set(Bob))
        ("", -1)
        ("Bob", 2)
        ("Alice", 1)
        ("Charlie", 1)
       */

      val best = network.foldLeft(("" , -1))((currentBest, newAssociation) => {
        val currentMostPopularPerson = currentBest._1
        val mostFriendsSoFar = currentBest._2

        val newPerson = newAssociation._1
        val newPersonFriends = newAssociation._2.size

        if (newPersonFriends > mostFriendsSoFar) (newPerson, newPersonFriends)
        else currentBest
    })
      best._1
    }

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(pair => pair._2.isEmpty) //another variation
    //network.filter(pair => pair._2.isEmpty).size  // filter all the people with no friends and count them

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {

    @tailrec
    def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        val personsFriends = network(person)

        if (personsFriends.contains(b)) true
        else search(discoveredPeople - person ++ personsFriends -- consideredPeople, consideredPeople + person)
      }
    }
    if(!network.contains(a) || !network.contains(b)) false
    else search(network(a), Set())
  }


  def main(args: Array[String]): Unit = {
    val empty: Map[String, Set[String]] = Map()
    val network = addPerson(addPerson(empty, "Alice"), "Bob")
    println(network)
    println(friend(network, "Bob", "Alice"))
    println(unfriend(friend(network, "Bob", "Alice"), "Bob", "Alice"))

    val people = addPerson(addPerson(addPerson(empty, "Alice"), "Bob"), "Charlie")
    val simpleNet = friend(friend(people, "Bob", "Alice"), "Bob", "Charlie")
    println(simpleNet)
    println(nFriends(simpleNet, "Bob"))
    println(nFriends(simpleNet, "Alice"))

    println(mostFriends(simpleNet))

    println(nPeopleWithNoFriends(addPerson(simpleNet, "Daniel")))

    println(socialConnection(addPerson(simpleNet, "Daniel"), "Bob", "Daniel"))
  }

}
