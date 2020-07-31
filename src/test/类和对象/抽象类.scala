package test.类和对象

/**
  * 抽象类实战
  */
object AbstractTestMain {
  def main(args: Array[String]): Unit = {
    val a = new AbstractTest;
    println("-------------匿名类：--------------");
    println("--匿名类使用：--");
    a.anonymous;
  }
}


class AbstractTest{
  //抽象类的使用
  def abstractUse: Unit ={

  }

  //匿名类的使用
  def anonymous{
    val p = new Person6("John", 18){
      override def print: Unit = println(s"Person6($name, $age)");   //匿名类的使用
    }
    p.print
  }
}

/**
  * 抽象类定义
  */
//定义为抽象类可以不初始化成员变量
abstract class Person5{
  var name : String;
}
//继承抽象类要初始化父类的成员变量
class Student5 extends Person5 {
  var name : String = _;
}

/**
  * 匿名类定义
  */
abstract class Person6(var name : String, var age : Int){
  def print;
}