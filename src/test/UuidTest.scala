package test

import test.ObjectDemo.Person

import scala.collection.mutable.{ArrayBuffer, Map}
object UuidTest{
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 100) {
      println(i+":"+UUID.randomUUID())
    }
  }

}
