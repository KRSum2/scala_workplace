package test.类和对象

/**
  * 继承和多态实战
  */
object InheritAndPolymorphismTestMain {
  def main(args: Array[String]): Unit = {
    val i = new InheritAndPolymorphismTest;
    println("-------------继承和多态：--------------");
    println("--类的继承：--");
    i.classInherit;
    println("--多态：--");
    i.polymorphism;
  }
}
//继承和多态
class InheritAndPolymorphismTest{
  //类的继承、执行顺序
  def classInherit: Unit ={
    println("父类：" + new Animal("John", 18));
    println("-------------------------------------------------------")
    println("子类：" + new Cat("Nancy", 19, 140116));
  }
  //多态操作
  def polymorphism: Unit ={
    val p1 : People = new Author("小猛", 21);
    val p2 : People = new Painter("小关", 20);
    p1.walk();
    p1.talkTo(p2);
    p2.walk();
    p2.talkTo(p1);
  }
}

/**
  * 继承
  * @param name
  * @param age
  */
class Animal(var name : String, var age : Int){
  //方法的重写
  override def toString: String = "name=" + name + " age=" + age;
  println("调用了父类主构造函数");
}
class Cat(name: String, age : Int, var id : Int) extends Animal(name, age) {
  //方法的重写
  override def toString: String = "name=" + name + " age=" + age + " id=" + id;
  println("调用了子类主构造函数");
}

/**
  * 多态
  */
class People(var name : String, var age : Int){
  def walk() = println("walk()方法在People里面");
  def talkTo(people: People) = {
    println("talkTo()方法在People里面");
  }
}

class Author(name : String, age : Int) extends People (name, age){
  private var authorNo : Int = 0;

  override def walk(): Unit = println("walk()方法在Author里面");
  override def talkTo(people: People): Unit = {
    println("talkTo()方法在Author里面");
    println(this.name + "正在和" + people.name + "谈话");
  }
}

class Painter(name : String, age : Int) extends People (name, age){
  private var PainterNo : Int = 0;

  override def walk(): Unit = println("walk()方法在Painter里面");
  override def talkTo(people: People): Unit = {
    println("talkTo()方法在Painter里面");
    println(this.name + "正在和" + people.name + "谈话");
  }
}