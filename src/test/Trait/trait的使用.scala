package test.Trait

import java.io.PrintWriter

/**
  * trait的使用
  */
object TraitUseTestMain {
  def main(args: Array[String]): Unit = {
    val t = new TraitUseTest;
    println("-------------trait的几种使用方法--------------");
    println("--只有抽象方法的trait：--");
    t.traitUseMethod;
    println("--混入trait的类对象构造：--");
    t.traitConstryctor;
    println("--提前定义和懒加载：--");
    t.preDef;
  }
}


class TraitUseTest{
  //trait的几种使用方法
  def traitUseMethod: Unit ={
    //只有抽象方法
    val p : PersonDao = new PersonDaoImpl;
    p.add(Person(1, "Jhon", 18));
  }
  //混入trait的类对象构造
  def traitConstryctor: Unit ={
    //先调用trait Logger中的构造函数，再调用trait FileLogger中的构造函数，最后调用匿名类的构造函数
    new FileLogger {}.log("trait");
  }
  //提前定义和懒加载
  def preDef: Unit ={
    //没有提前定义，因为会先调用Person，Logger的构造函数，没有问题，但是接下来调用FileLogger构造函数时，没有初始化fileName，导致空指针异常。Student是最后调用的。
    //new Student1().log("D://file1.log");
    //提前定义
    val s = new {
      override val fileName : String = "D://file1.log";
    } with Student1;
    s.log("你好啊！");
    //懒加载
    println("------懒加载：")
    val s2 = new Student2;
    s2.log("lazy demo");
  }

}

case class Person(var id : Int, var name : String, var age : Int)

/**
  * 只有抽象方法
  */
//定义只有抽象方法的trait
trait PersonDao{
  def add(p : Person);
  def update(p : Person);
  def delete(p : Person);
  def find(p : Person) : Person;
}
//实现上述的抽象方法
class PersonDaoImpl extends PersonDao{
  override def add(p: Person): Unit = println("实现了添加方法！");
  override def update(p: Person): Unit = println("实现了更新方法！");
  override def delete(p: Person): Unit = println("实现了删除方法！");
  override def find(p: Person): Person = {
    println("实现了查找方法！");
    Person(1, "Jhon", 18);
  }
}

/**
  * 有抽象成员和抽象成员方法,具体成员和具体方法
  */
trait PersonDao1{
  //抽象成员
  var recordNum : Long;
  //具体成员
  var id :Int = _;
  //抽象方法
  def add(p : Person);
  //具体方法
  def update(p : Person) = println("实现了删除方法！");
  def delete(p : Person);
  def find(p : Person) : Person;
}

/**
  * 混入trait的类对象构造
  */
trait Logger{                                                                                 //无参构造
  println("Logger");
  def log(msg : String);
}
trait FileLogger extends Logger{
  println("FileLogger");
  val fileOutput = new PrintWriter("D://file.log");
  fileOutput.println("赖书恒是大坏蛋");

  override def log(msg: String): Unit = {
    fileOutput.print(msg);
    fileOutput.flush();
  }
}


/**
  * 提前定义（推荐懒加载）
  *
  */
trait Logger1{                                                                                 //无参构造
  println("Logger");
  def log(msg : String);
}
trait FileLogger1 extends Logger1{
  println("FileLogger");
  val fileName : String;
  val fileOutput = new PrintWriter(fileName);
  fileOutput.println("赖书恒是大坏蛋");

  override def log(msg: String): Unit = {
    fileOutput.print(msg);
    fileOutput.flush();
  }
}
class Person1;
class Student1 extends Person1 with FileLogger1{
  override val fileName: String = "D://file1.log";
}

/**
  * 懒加载
  *
  */
trait Logger2{                                                                                 //无参构造
  println("Logger2");
  def log(msg : String);
}
trait FileLogger2 extends Logger2{
  println("FileLogger2");
  val fileName : String;
  lazy val fileOutput = new PrintWriter(fileName);   //懒加载，在使用时才执行，创建对象时不执行
  //fileOutput.println("赖书恒是大坏蛋");   //如果在这里使用这句，会导致空指针异常

  override def log(msg: String): Unit = {
    fileOutput.print(msg);
    fileOutput.flush();
  }
}
class Person2;
class Student2 extends Person2 with FileLogger2{
  val fileName: String = "D://file2.log";
}