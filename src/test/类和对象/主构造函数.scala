package test.类和对象

import scala.beans.BeanProperty

/**
  * 主构造函数实战
  */
object MainConstructorTestMain {
  def main(args: Array[String]): Unit = {
    val m = new MainConstructorTest;
    println("-------------主构造函数：--------------");
    println("--主构造函数定义：--");
    m.getEmployee;
    println("--默认参数主构造函数：--");
    m.defaultParameters;
  }
}

class MainConstructorTest{
  //主构造函数定义
  def getEmployee: Unit ={
    val p = new Employee("小可爱", 21);
    println("信息为：" + p);
    p.name = "赖书恒";
    println("信息为：" + p);
    println("姓名为：" + p.getName);

  }
  //默认参数的主构造函数
  def defaultParameters: Unit ={
    val p = new Employee1();
    println("不指定参数时，使用默认参数------" + p)
    val p1 = new Employee1("詹姆斯");
    println("指定name，不指定age------" + p1);
  }
  //私有主构造函数
  def privateMainConstructor: Unit ={
    //如果没有辅助构造函数new Employee2("小可爱", 21)会报错，
    //val p = new Employee2("小可爱", 21);
  }

  //主构造函数变量访问权限
  def visitPermission: Unit ={
    val p = new Employee3("小可爱", 21, 1101, "男");
    //p.name;        不能访问name，因为被protected修饰，外部不能访问
    //p.age;         不能访问age，因为被private修饰，外部不能访问
    //p.id;p.sex     不能访问id和sex，因为被private[this]修饰，外部不能访问
  }
}


class Employee(@BeanProperty var name : String, var age : Int){
  override def toString: String = "姓名为：" + name + ",年龄为：" + age;
}
//默认参数
class Employee1(var name : String = "", var age : Int = 18){
  override def toString: String = "姓名为：" + name + ",年龄为：" + age;
}
//私有主构造函数
class Employee2 private (@BeanProperty var name : String, var age : Int){
  override def toString: String = "姓名为：" + name + ",年龄为：" + age;
}
//主构造函数变量访问权限protected、private、private[this]
//没有用var修饰的id和sex变量默认是private[this]权限
class Employee3(protected var name : String, private var age : Int, id : Int, sex : String){
  override def toString: String = "姓名为：" + name + ",年龄为：" + age;
}