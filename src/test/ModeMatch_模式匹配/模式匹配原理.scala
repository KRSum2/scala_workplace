package test.ModeMatch_模式匹配

/**
  * 模式匹配原理
  */
object ModeMatchPrincipleTestMain {
  
}

/**
  * 构造函数模式原理（一般使用case class）
  */
object ConstructorModePrinciple extends App{
  //定义普通类Dog
  class Dog(val name : String, val age : Int)
  //Dog类的伴生对象
  object Dog{
    //手动实现unapply方法
    def unapply(dog : Dog): Option[(String, Int)] = {
      if (dog != null) Some(dog.name, dog.age)
      else None;
    }
  }
  //利用构造函数创建对象，因为Dog是普通类，这里必须通过显示地new来创建对象
  val dog = new Dog("Pet", 2);
  def patternMatching(x : AnyRef) = x match {
    case Dog(name, age) => println(s"Dog name=$name, age=$age");
    case _ =>
  }
  patternMatching(dog);
}