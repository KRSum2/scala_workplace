package test.类型参数

/**
  * 视图界定符号     <%
  */
object 视图界定 extends App {
  case class Student[T, S <: Comparable[S]](var name : T, var age : S)
  val s1 = Student("john", "170");  //合格，因为String类型实现了Comparable接口
  //val s2 = Student("john", 170.0); //不合法，因为Int类型没有实现Comparable接口
  case class Student1[T, S <% Comparable[S]](var name : T, var age : S)
  val s3 = Student1("john", "170");
  val s4 = Student1("john", 170.0);
}
