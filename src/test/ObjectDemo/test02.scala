package test.ObjectDemo
/**
  * for循环
  */

import util.control.Breaks._

//循环操作
object test02 {
  def main(args: Array[String]): Unit = {
    BreakTest;
    println("")
    ContinueTest;
    println("")
    YieldTest;

  }

  //break的测试
  def BreakTest: Unit ={
    breakable{
      for (i <- 1 to 5){
        if (i > 3)
          break()
        print(i + " ")
      }
    }
  }

  //continue测试
  def ContinueTest: Unit ={
    for(i <- 1 to 5)
      breakable{
        if (i == 3)
          break();
        print(i + " ");
      }
  }

  //yield测试
  def YieldTest: Unit ={
    var x = for (i <- 1 to 5) yield i%2 == 0;
    var y = for (i <- 1 to 5) yield i/2;
    println("x=" + x);
    println("y=" + y );
  }
}
