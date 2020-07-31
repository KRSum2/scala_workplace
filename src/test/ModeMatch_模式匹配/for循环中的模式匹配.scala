package test.ModeMatch_模式匹配

import test.ModeMatch_模式匹配.ConstructorMode.Dog

/**
  * for循环中的模式匹配
  */
object ModeOfForTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------for循环中的模式匹配--------------");
    val m = new ModeOfForTest;
    println("--变量模式匹配--");
    m.variableMode;
    println("--常量模式匹配--");
    m.constantMode;
    println("--变量绑定模式匹配--");
    m.bindMode;
    println("--类型模式匹配--");
    m.typeMode;
    println("--构造函数模式匹配--");
    m.constructorMode;
    println("--序列模式匹配--");
    m.orderMode;
  }
}

class ModeOfForTest{
  //变量模式匹配
  def variableMode: Unit ={
    for((language, framework) <- Map("Java" -> "Hadoop", "Closure" -> "Storm", "Scala" -> "Spark")){
      println(s"$framework is developed by $language language");
    }
  }
  //常量模式匹配
  def constantMode: Unit ={
    for((language, "Spark") <- Map("Java" -> "Hadoop", "Closure" -> "Storm", "Scala" -> "Spark")){
      println(s"Spark is developed by $language language");
    }
  }
  //变量绑定模式匹配
  def bindMode: Unit ={
    for((language, e@"Spark") <- Map("Java" -> "Hadoop", "Closure" -> "Storm", "Scala" -> "Spark")){
      println(s"$e is developed by $language language");
    }
  }
  //类型模式匹配
  def typeMode: Unit ={
    for((language, framework : String) <- Map("Java" -> "Hadoop".length, "Closure" -> "Storm".length, "Scala" -> "Spark")){
      println(s"$framework is developed by $language language");
    }
  }
  //构造函数模式匹配
  def constructorMode: Unit ={
    for (Dog(name, age) <- List(Dog("Pet", 2), Dog("Anny", 3), Dog("Digo", 2))){
      println(s"Dog $name is $age years old");
    }
  }
  //序列模式匹配
  def orderMode: Unit ={
    for (List(first, _*) <- List(List(1, 2, 3), List(4, 5, 6))){
      println(s"the first element is $first");
    }
  }

}