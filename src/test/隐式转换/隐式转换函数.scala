package test.隐式转换

/**
  * 隐式转换函数(与函数名称无关，只与传入参数和返回类型有关)
  * 不能存在多个隐式转化函数同时可以使某一类型转换成另一类型
  */
object FunctionTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------隐式转换函数--------------");
    val f = new FunctionTest;
    println("--定义--");
    f.define;
    println("--隐式类--");
    f.implicitClass;
  }
}

class FunctionTest{
  //隐式转换函数的定义
  def define: Unit ={
    implicit def floatToInt(x : Float) = x.toInt;   //如果没有定义这个，下面一行会报错
    val int : Int = 2.55f;
    println("隐式转换函数的结果：" + int)
  }

  //隐式类(只允许Dog有一个传入参数，因为隐式转换是将一种类型转换成另一种类型)
  def implicitClass: Unit ={
    implicit class Dog(val name : String){
      def bark = println(s"$name is barking!!!");
    }
    "Nacy".bark;
  }




}

//隐式对象
object ImplicitObject extends App {
  //定义一个trait Multiplicable
  trait Multiplicable[T]{
    def multiply(x : T) : T;
  }
  //定义一个隐式对象MultiplicableInt，用于整数数据的相乘
  implicit object MultiplicableInt extends Multiplicable[Int]{
    def multiply(x : Int)= x * x;
  }
  //定义一个隐式对象MultiplicableString，用于整数数据的相乘
  implicit object MultiplicableString extends Multiplicable[String]{
    def multiply(x : String) = x * 2;
  }
  //定义一个函数，函数具有泛型参数
  def multiply[T : Multiplicable](x : T) = {
    //implicitly方法，访问隐式对象
    val ev = implicitly[Multiplicable[T]]
    //根据具体的类型调用相应的隐式对象中的方法
    ev.multiply(x);
  }
  //隐式参数
  def multiply1[T : Multiplicable](x : T)(implicit ev : Multiplicable[T]) = {
    //根据具体的类型调用相应的隐式对象中的方法
    ev.multiply(x);
  }
  //调用隐式对象MultiplicableInt中的multiply方法
  println(multiply(5));
  //调用隐式对象MultiplicableString中的multiply方法
  println(multiply("5"));
  //调用隐式参数
  println(multiply1(5));
  println(multiply1("5"));


}


//隐式值（不能存在两个类型相同的隐式值）
object ImplicitValue extends App{
  //定义一个trait Multiplicable
  trait Multiplicable[T]{
    def multiply(x : T) : T;
  }
  //定义一个普通类MultiplicableInt，用于整数数据的相乘
  class  MultiplicableInt extends Multiplicable[Int]{
    def multiply(x : Int)= x * x;
  }
  //定义一个普通类MultiplicableString，用于整数数据的相乘
  class  MultiplicableString extends Multiplicable[String]{
    def multiply(x : String) = x * 2;
  }
  //隐式参数
  def multiply[T : Multiplicable](x : T)(implicit ev : Multiplicable[T]) = {
    //根据具体的类型调用相应的隐式对象中的方法
    ev.multiply(x);
  }
  //类型为MultiplicableInt的隐式值mInt
  implicit val mInt = new MultiplicableInt;
  //类型为MultiplicableString的隐式值mStr
  implicit val mStr = new MultiplicableString;
  //隐式值mInt当作参数传入ev，相当于调用multiply(5)(mInt)
  println(multiply(5));
  //隐式值mStr当作参数传入ev，相当于调用multiply(5)(mStr)
  println(multiply("5"));
}