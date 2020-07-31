package test.隐式转换

/**
  * 隐式转换规则与问题
  */
object RuleAndProblemTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------隐式转换规则和问题--------------");
    val r = new RuleTest;
    println("--规则①：显示定义规则--");
    r.ShowDef;
    println("--规则②：作用域规则--");
    println("--规则③：无歧义规则--");
    r.noAmbiguity;
    println("--规则④：一次转换规则--");
    r.oneConvert;
  }
}

class RuleTest{
  //显示定义
  def ShowDef: Unit ={
    def product(implicit x : Double, y : Double) = x * y;
    implicit val x : Int = 5;
    //println(product); 当不指定参数时，编译器会在当前作用域内查找类型为Double的隐式值，
                      // 虽然上面定义了Int类型的隐式变量x，但不会通过隐式转换成Double类型来匹配，所以报错
    println(product(5,5)); //显示定义参数时，类型不匹配，则会自动进行隐式转换，将Int类型转换成Double类型来匹配函数的参数类型。
    implicit val y : Double = 6;
    println(product); //存在Double类型的隐式值，能够进行转换
  }

  //无歧义规则
  def noAmbiguity: Unit ={
    //不能存在多个隐式转化函数同时可以使某一类型转换成另一类型
    println("a.不能存在多个隐式转化函数同时可以使某一类型转换成另一类型");
    implicit def doubleToInt(x : Double) = x.toInt;
    implicit def dTi(x : Double) = x.toInt;
    //val x : Int = 2.55;  有歧义，存在两个隐式转换函数可以使Int转换成Double

    //不能同时存在两个相同类型的隐式值
    println("b.不能同时存在两个相同类型的隐式值")
    def sum(implicit x : Int, y : Int) = x + y;
    implicit val x : Int = 5;
    implicit val y : Int = 6;
    //println(sum);  存在两个隐式值，所以报错

    //不能同时存在两个主构造函数参数类型及成员方法相同的隐式类
    println("c.不能同时存在两个主构造函数参数类型及成员方法相同的隐式类");
  }

  //一次转换规则
  implicit class Dog(name : String){
    def getName = name;
    def bark = println(s"$name is barking!!!");
  }
  implicit class SpecialDog(dog: Dog){
    def specialBark = println(dog.getName + " is barking Specially")
  }
  def oneConvert: Unit ={
    "Nacy".bark;   //合法
    //"Nacy".specialBark;  不合法，不能通过String类转换成Dog类，再通过Dog类转换成SpecialDog类。
                          //从源类型到目标类型的隐式转换最多只允许一次
  }
}

/**
  * 隐式转换的问题
  */
object ProblemTest extends App {
  class TestA{
    override def toString: String = "This is TestA";
    def printA = println(this);
  }
  class TestB{
    override def toString: String = "This is TestB";
    def printB(x : TestC) = println(x);
  }
  class TestC{
    override def toString: String = "This is TestC";
    def printC = println(this);
  }
  //TestA到TestB的隐式转换函数
  implicit def A2B(x : TestA): Unit ={
    println("TestA is being converted to TestB");
    new TestB;
  }
  //TestB到TestC的隐式转换函数
  implicit def B2C(x : TestB): Unit ={
    println("TestB is being converted to TestC");
    new TestC;
  }
  val a = new TestA;
  //a.printB(new TestB)

}