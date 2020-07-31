package test.ObjectDemo

object Person {
  println("Scala");
  private val age: Int = 10;
}

class Person {
  def getAge = Person.age;
  val name: String = "LSH";
  final val sex: String = "male";

}

//继承
class Student extends Person {
  override val name: String = "HYF"
}

//重载构造方法
class Teacher(name: String) {
  println(name);

  def this(name: String, age: Int) {
    this(name);
    println(name + ":" + age);
  }
}

//复写setter方法
class Person1 {
  private var myName = "Flink";

  def name1 = this.myName;

  def name1_=(newName: String): Unit = {
    myName = newName;
    println("Hi:" + myName);
  }
}

//自定义setter方法
class Person2 {
  private var myName = "Tom";

  def name1 = this.myName;

  def update(newName: String) {
    myName = newName;
    println("Hi:" + myName);
  }
}

//抽象类
abstract class Animal(name: String) {
  println(name);
  private val age = 20;
  val sex: String = "male";
}

class Cat extends Animal("咖啡猫");


//抽象类中的抽象方法和抽象变量
abstract class Person3 {
  var myName: String;
  val hisName: String;

  def add(x: Int, y: Int): Int;

  def update(newName: String) {
    myName = newName;
    println("Hi:" + myName);
  }
}

//继承抽象类并实现抽象类中未实现的方法和成员
class Person4 extends Person3 {
  override var myName: String = _;
  override val hisName: String = "赖书恒";

  override def add(x: Int, y: Int): Int = {
    3 + 5
  }
}


//特质
trait Person5 {
  def eat(str: String): Unit = {
    println(str);
  }
}

trait Worker {
  def work: Unit = {
    println("Working.......")
  }
}

class Student1 extends Worker with Person5

//特质二
trait Person6 {
  val name: String
  val age: Int = 20;
}

trait Worker1 {
  val age: Int = 15;
}

class Student2 extends Worker1 with Person6 {
  val name = "勒布朗-詹姆斯"
  override val age: Int = 12;
}