package test.Java与Scala集合互操作

import java.util


object Scala调用Java集合 extends App {
  def getList = {
    val list = new util.ArrayList[String]()
    list.add("Hadoop");
    list.add("Hive");
    list
  }
  val list = getList;
  import scala.collection.JavaConversions._
  //list.forEach(println);
  val list2 = list.map(x => x * 2);
  println(list2);
}
