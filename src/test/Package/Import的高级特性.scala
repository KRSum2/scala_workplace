package test.Package

import java.util.{HashMap => JavaHashMap}   //将HashMap重命名为JavaHashMap
import scala.collection.mutable.HashMap
import java.util.{ArrayList => _,_}         //将ArrayList类隐藏，其他类导入
/**
  * import的高级特性
  */
object ImportTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------包重命名--------------");
    new JavaTest;
    new ScalaTest;
    println("-------------类隐藏--------------");
  }
}

class JavaTest{
  val javaHashMap = new JavaHashMap[String, Int]()
  javaHashMap.put("Spark", 1);
  javaHashMap.put("Hive", 2);
  for (key <- javaHashMap.keySet().toArray) println(key + ":" + javaHashMap.get(key));
}

class ScalaTest{
  val scalaHashMap = new HashMap[String, Int]();
  scalaHashMap.put("Sqoop", 3);
  scalaHashMap.put("Hadoop", 4);
  scalaHashMap.foreach(e => {
    val (k, v) = e;
    //println(e)
    println(k + ":" + v);
  })
}

