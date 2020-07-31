package test.ModeMatch_模式匹配
/**
  * 模式匹配类型
  */
object ModeMatchTypeTestMain {

}
/**
  * 构造函数模式匹配
  */
object ConstructorMode extends App {
  //定义一个case class
  case class Dog(val name : String, val age : Int)
  case class Cat(val name : String, val age : Int)
  //利用构造函数创建对象
  val dog = Dog("Pet", 2);
  val cat = Cat("House",5);
  def patternMatching(x : AnyRef) = x match {       //AnyRef为所有引用类型的基类
    case Dog(name, age) => println(s"name=$name, age=$age");
    case Cat(_, age) => println(s"age=$age");                     //构取对象的部分成员域
    case _ =>
  }
  patternMatching(dog);
  patternMatching(cat);
}

/**
  * 序列模式匹配
  */
object OrderMode extends App {
  val arrayInt =  Array(1, 2, 3, 4);
  def patternMatching(x : AnyRef) = x match {
    case Array(first, second) => println(s"first=$first, second=$second");
    case Array(first, _, three, _*) => println(s"first=$first, three=$three")
    case _ =>
  }
  patternMatching(arrayInt);
}

/**
  * 元组模式匹配
  */
object TupleMode extends App {
  val tupleInt =  (1, 2, "赖书恒", 4);
  def patternMatching(x : AnyRef) = x match {
    case (first, second) => println(s"first=$first, second=$second");
    case (first, _, three, _) => println(s"first=$first, three=$three")
    //元组模式不允许使用_*
    //case (first, _, three, _*) => println(s"first=$first, three=$three")
    case _ =>
  }
  patternMatching(tupleInt);
}

/**
  * 类型模式匹配
  */
object TypeMode extends App {
  //定义一个元组
  class A;
  class B extends A;
  class C extends A;
  val b = new B;
  val c = new C;
  def patternMatching(x : AnyRef) = x match {
    case x : String => println("字符串类型");
    case x : B => println("B类型");
    case x : A => println("A类型");
    case _ => println("其他类型")
  }
  patternMatching("赖书恒");
  patternMatching(b);
  patternMatching(c);   //类型模式具有多态性
}

/**
  * 变量绑定模式
  */
object BindMode extends App{
  val list = List(List(1, 2, 3, 4), List(4, 5, 6, 7, 8, 9))
  def patternMatching(x : AnyRef) = x match {
    case e1@List(_, e2@List(4, _*)) => println(s"变量e1=$e1, \n变量e2=$e2" );
    case _ =>
  }
  patternMatching(list);
}