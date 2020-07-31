package test.Function

import scala.collection.mutable.ArrayBuffer

object ValueFunctionTestMain {
  def main(args: Array[String]): Unit = {
    val value = new ValueFunctionTest;
    println("-------------值函数定义：--------------")
    value.valueDef;
    println("-------------值函数简化：--------------")
    value.valueSimplify;
  }
}

class ValueFunctionTest{
  //值函数定义
  def valueDef: Unit ={
    val sum = (x:Int, y:Int) => x*y;
    println("值函数定义：" + sum(2, 3));
  }
  //值函数简化
  def valueSimplify: Unit ={
    val arrInt = ArrayBuffer(1, 2, 3, 4, 5);
    val increment = (x : Int) => x + 1;  //值函数
    val increment1 = (_ : Int) + 1;
    val increment2 : (Int) => Int=_ + 1;
    println("------Array/ArrayBuffer提供的map方法，对每个元素进行+1操作------");
    println("操作前：" + arrInt.toString);
    println("操作后：" + arrInt.map(increment));
    println("操作后：" + arrInt.map(x=>x+1));
    //println("操作后：" + arrInt.map(_+1);
    println("操作后：" + arrInt.map(increment1));
    println("操作后：" + arrInt.map(increment2));
  }
}