package test.Java与Scala集合互操作

import java.util.Comparator

object Scala中使用Java泛型 extends App {
  case class Person(val name : String, val age : Int)
  class PersonComparator extends Comparator[Person]{
    override def compare(o1: Person, o2: Person): Int = {
      if (o1.age > o2.age)
        1
      else
        -1;
    }
  }
  val p1 = Person("摇摆少年梦", 27);
  val p2 = Person("李四", 29)
  val personComparator = new PersonComparator();
  if (personComparator.compare(p1, p2) > 0)
    println(p1)
  else
    println(p2)
}
