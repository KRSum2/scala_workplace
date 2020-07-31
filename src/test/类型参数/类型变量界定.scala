package test.类型参数

/**
  * 类型变量界定符号      <:
  */
object 类型变量界定 extends App {

  class TypeVariableBound{
    //采用<:进行类型变量界定，该语法的意思是泛型T必须是实现了Comparable接口的类型
    def compare[T <: Comparable[T]](first : T, second : T) = {
      if (first .compareTo(second) > 0)
        first
      else second
    }
  }
  val tvb = new TypeVariableBound
  println(tvb.compare("A", "B"));


  /**
    * 自己定义的Person类
    */
  case class Person(var name : String, var age : Int) extends Comparable[Person]{
    override def compareTo(o: Person): Int = {
      if (this.age > o.age) 1;
      else if (this.age == o.age) 0;
      else -1;
    }
  }
  println(tvb.compare(Person("stephen", 19), Person("john", 20)));

  //作用于类型参数
  case class Student[S, T <: AnyVal](var name : S, var hight : T)
  //val s1 = Student("john", "170");  不合法，因为String类型不属于AnyVal类层次结构
  val s2 = Student("john", 170.0);
  val s3 = Student("john", 170L);



}


