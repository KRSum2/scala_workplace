package test.类型参数

object 泛型 extends App {
  //Person[T]中的[T]为指定的泛型T
  class Person[T](var name : T);
  class Student[T](name : T) extends Person(name)
  //在使用时将泛型参数具体化，这里为String类型
  println(new Student[String]("摇摆少年梦").name);
}
