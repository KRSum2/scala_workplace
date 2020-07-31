package test.类型参数

/**
  * 类与类型的实战
  */

import scala.reflect.runtime.universe._;
object 类与类型 extends App {

  class Test;
  val a = new Test;
  println("获取类型信息：" + typeOf[Test]);
  println("获取类信息：" + classOf[Test]);
  println("getClass方法获取：" + a.getClass)
}
