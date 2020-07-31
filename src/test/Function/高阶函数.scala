package test.Function

import scala.collection.mutable.ArrayBuffer

/**
  * 高阶函数实战
  */
object HighOrderFunctionTestMain {
  def main(args: Array[String]): Unit = {
    val h = new HighOrderFunctionTest;
    println("-------------高阶函数的定义：--------------")
    h.HighOrderDef;
    println("-------------高阶函数的使用：--------------")
    h.HighOrderUse;

  }
}

class HighOrderFunctionTest{
  //高阶函数的定义
  def HighOrderDef: Unit ={
    //定义一个高阶函数，该函数的输入参数为函数类型(Double) => Double
    def higherOrderFunction(f : Double => Double) = f(100);
    //定义一个函数，该函数的输入参数的Double，返回值类型是Double
    def sqrt(x : Double) = Math.sqrt(x);
    val res = higherOrderFunction(sqrt);
    println("开平方根的高阶函数的测试：" + res);

    //函数类型推导,返回的函数类型为Double=>Double
    def higherOrderFunction1(factor : Int) = (x : Double) => factor * x;
    //生成新的函数，函数参数类型为Double，返回值类型为Double
    val multiply = higherOrderFunction1(100);
    val res1 = multiply(10);
    println("函数类型推导的测试：" + res1);
  }

  //高阶函数的使用
  def HighOrderUse: Unit ={
    println("-----------map函数----------")
    println("Array/ArrayBuffer类型的map函数使用：" + ArrayBuffer("Spark", "Hive", "Hadoop").map(x=>x*2).toString);
    println("Array/ArrayBuffer类型的map函数使用：" + ArrayBuffer("Spark", "Hive", "Hadoop").map(_*2).toString);
    println("List类型的map函数使用：" + List("Spark" -> 1, "Hive" -> 2, "Hadoop" -> 3).map(x => x._1));
    println("List类型的map函数使用：" + List("Spark" -> 1, "Hive" -> 2, "Hadoop" -> 3).map(_._2));
    println("Map类型的map函数使用：" + Map("Spark" -> 1, "Hive" -> 2, "Hadoop" -> 3).map(x => x._1));
    println("Map类型的map函数使用：" + Map("Spark" -> 1, "Hive" -> 2, "Hadoop" -> 3).map(_._2));

    println("-----------flatMap函数----------")
    val listInt = List(1, 2, 3);
    val listFlatMap = listInt.flatMap((x => x match {
      case 1 => List(1);
      case _ => List(x*2, x*3, x*4);
    }))
    println("flatMap函数的使用：" + listFlatMap);
    val listMap = listInt.map(x => x match {
      case 1 => List(1);
      case _ => List(x*2, x*3, x*4);
    })
    println("map函数的使用：" + listMap);

    println("-----------filter函数----------");
    println("返回listFlatMap中所有大于6的元素所构成的集合：" + listFlatMap.filter(x => x > 6));
    println("返回listFlatMap中所有大于6的元素所构成的集合：" + listFlatMap.filter(_ > 6));

    println("-----------reduce函数----------");
    println("reduce函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).reduce((x : Int, y : Int) => {print(x, y);x+y;}));
    println("reduce函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).reduce(_+_));
    println("reduceLeft函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).reduceLeft((x : Int, y : Int) => {print(x, y);x+y;}));
    println("reduceRight函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).reduceRight((x : Int, y : Int) => {print(x, y);x+y;}));

    println("-----------fold函数----------");
    println("fold函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).fold(6)((x : Int, y : Int) => {print(x, y);x+y;}));
    println("fold函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).fold(6)(_+_));
    println("foldLeft函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).foldLeft(6)((x : Int, y : Int) => {print(x, y);x+y;}));
    println("foldRight函数的使用：" + ArrayBuffer(1, 2, 3, 4, 5).foldRight(6)((x : Int, y : Int) => {print(x, y);x+y;}));
  }

}