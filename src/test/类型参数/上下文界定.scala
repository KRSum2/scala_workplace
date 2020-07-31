package test.类型参数

object 上下文界定 extends App {

  class PersonOrdering extends Ordering[Person]{
    override def compare(x: Person, y: Person): Int = {
      if (x.name < y.name) 1;
      else -1;
    }
  }

  case class Person(val name : String){
    println("正在构造对象：" + name);
  }

  //定义一个上下文界定
  //在对应的作用域中，必须存在一个类型为Ordering[T]是隐式值，该隐式值可以作用于内部的方法
  class Pair[T : Ordering](val first : T, val second : T){
    //smaller方法中有一个隐式参数，该参数的类型为Ordering[T]
    def smaller(implicit ord : Ordering[T]) = {
      if (ord.compare(first, second) > 0)
        first
      else
        second
    }
  }

  //定义一个隐式值，它的类型为Oredering[Person]
  implicit val p1 = new PersonOrdering
  val p = new Pair(Person("123"), Person("456"));
  //不给函数指定参数，此时会查找一个隐式值，该隐式值类型为Oredering[Person]
  //根据上下文界定的要求，p1正好满足要求
  //因此它会作为smaller的隐式参数传入，从而调用ord.compare(first, second)方法进行比较
  println(p.smaller);

}



object 上下文界定简化 extends App {

  class PersonOrdering extends Ordering[Person]{
    override def compare(x: Person, y: Person): Int = {
      if (x.name > y.name) 1;
      else -1;
    }
  }

  case class Person(val name : String){
    println("正在构造对象：" + name);
  }

  //定义一个上下文界定
  class Pair[T : Ordering](val first : T, val second : T){
    //引入ordering到Ordered的隐式转化
    //在查找作用域范围内的Ordering[T]的隐式值
    //本例是implicit val p1 = new PersonOrdering
    //编译器看到比较方式是<的时候，会自动进行
    //隐式转换，转换成Ordered，然后调用其中的<方法进行比较
    import Ordered.orderingToOrdered;
    def smaller = {
      if (first < second)
        first
      else
        second
    }
  }

  //定义一个隐式值，它的类型为Oredering[Person]
  implicit val p1 = new PersonOrdering
  val p = new Pair(Person("123"), Person("456"));
  println(p.smaller);

}