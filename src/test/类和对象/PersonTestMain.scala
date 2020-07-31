package test.类和对象

import scala.beans.BeanProperty

object PersonTestMain {
  def main(args: Array[String]): Unit = {
    //类实战
    val p = new PersonUse;
    println("-------------类操作：--------------");
    p.visit;

  }
}

class PersonUse{
  //类成员访问
  def visit {
    var person = new Person;
    println("访问类成员变量：" + person.name);
    person.name = "John";
    println("修改类成员变量：" + person.name);
    println("利用getter方法获取:" + person.getAge);
    person.setAge(12)
    println("利用setter方法修改:" + person.getAge);

  }
}

class Person{
  var name : String = _;
  //加上注解，会使反编译的时候出现getter和setter方法
  @BeanProperty var age : Int = _;
}

