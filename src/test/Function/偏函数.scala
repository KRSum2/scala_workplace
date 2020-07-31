package test.Function

/**
  * 偏函数实战
  */
object PartialFunctionTestMain {
  def main(args: Array[String]): Unit = {
    val p = new PartialFunctionTest;
    println("-------------偏函数的使用：--------------")
    p.partial;
  }
}
//偏函数的使用
class PartialFunctionTest{

  def partial: Unit ={
    val sample = 1 to 10;
    //奇偶类型的判断
    val isEven : PartialFunction[Int, String] = {  //Int为输入类型，String为输出类型
      case x if x % 2 == 0 => x + "是偶数";
      case _ => "不是偶数";
    }
    println("判断是否为偶数？" + isEven(8));
    println("判断是否为偶数？" + isEven(7));
    println(sample.collect(isEven));

    //判断参数类型
    val receive : PartialFunction[Any, Unit]={
      case x : Int => println("Int Type");
      case x : String => println("String Type");
      case _ => println("Other Type");
    }
    receive(10);
    receive("10");
  }
}