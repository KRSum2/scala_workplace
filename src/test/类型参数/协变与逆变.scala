package test.类型参数

/**
  * 协变与逆变
  */
object 协变与逆变 extends App {

  /**
    * 协变：String是Any的子类，所以Person[String]是Person[Any]的子类
    * @param head
    * @param tail
    * @tparam T
    */
  //用+标识泛型T，表示List类具有协变性
  class List[+T] (val head : T, val tail : List[T])
  //List泛型参数为协变之后，意味着List[String]也是List[Any]的子类
  val list : List[Any] = new List[String]("协变", null)

  /**
    * 逆变：String是Any的子类，所以Person[String]是Person[Any]的父类
    * @tparam A
    */
  class Person[-A]{
    def test(x : A) ={
      println(x);
    }
  }
  val pAny = new Person[Any]
  val pStr : Person[String] = pAny;
  pStr.test("Contravariance test.....");
}

//当List中的泛型参数指定为协变之后，在定义该类的方法时需要特别注意
object 协变_逆变的位置 extends App{
  class List[+T] (val head : T, val tail : List[T]){
    //将函数也用泛型表示，因为是协变，函数参数是逆变点，参数类型U为T的超类
    def prepend[U >: T](newHead : U): List[U] = new List(newHead, this)
    override def toString: String = " " + head
  }
  val list : List[Any] = new List[String]("协变", null);
  println(list.toString);
  println(list.prepend("逆变"));
}