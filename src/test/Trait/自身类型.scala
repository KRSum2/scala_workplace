package test.Trait

/**
  * 自身类型
  */
object TypeOfSelfTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------自身类型--------------");
    val t = new TypeOfSelfTest;
    t.test;
  }
}

class TypeOfSelfTest {
  tos =>                     //自身类型相当于tos : TypeOfSelfTest =>
  val v1 = "here";
  def test: Unit ={
    new Test;
  }
  class Test{
    println(tos.v1);
  }
}