package test

import test.ObjectDemo.Person

import scala.collection.mutable.{ArrayBuffer, Map}
object HelloWord {
  def main(args: Array[String]): Unit = {
    println("Hello World!")

    //可变数组
    val at = new ArrayTest
    at.arrayTest

    //元组
    val tuple1 = (1, "Scala", 2, "Spark")
    println(tuple1)
    println(tuple1._1 + " " + tuple1._2 + " " + tuple1._3 + " " + tuple1._4)

    //List列表
    //定义方式①
    val fruit:List[String] = List("apple", "oranges", "pears")
    println(fruit)
    //定义方式②
    val num = 1::(2::(3::Nil))
    val num1 = 1::2::3::Nil
    print(num1)
    println(num )
    //列的基本操作
    val t = new Test
    t.ops
    //串联操作
    val lct = new ListConcatTest
    lct.concatTest

    //Set集合操作(head, tail, isEmpty方法和List一样)
    val s:Set[Int] = Set(1, 2, 3)
    println(s)
    println(s.apply(1))
    println(s.count(i => i > 1))
    println(s.init)

  }

  //可变数组
  class ArrayTest{
    def arrayTest: Unit ={
      val arrBufInt = ArrayBuffer[Int]()
      arrBufInt += 1
      println(arrBufInt)
      arrBufInt ++= Array(2, 3, 4, 5)
      println(arrBufInt)
      arrBufInt.trimEnd(2)//移除最后两个元素
      println(arrBufInt)
      val bigData = Map("Scala" -> 35, "Hadoop" -> 30, "Spark" -> 50, "Hive" -> 60)
      println(bigData)
      println(bigData("Scala"))  //获取键为“Scala”的值
      println(bigData.contains("Hadoop"))
      println(bigData.getOrElse("Spark", 70))  //若存在key，则返回对应的value，否则返回默认值70
      bigData("Hive") = 100
      println(bigData.getOrElse("Hive", 70))
      println(bigData.mkString("|"))  //添加分隔符
      println(bigData.drop(2))  //获取角标从2开始的所有数
      bigData += ("Storm" -> 150)  //添加键值对
      println(bigData + "------" + bigData.drop(2))
      bigData -= ("Hive")  //删除键值对
      println(bigData)
      for((k,v) <- bigData) println(k + " " + v)
      for (k <- bigData.keySet) println(k)
    }
  }

  //列的基本操作
  class Test{
    def ops: Unit ={
      val fruit = "apple"::("oranges"::("pears"::Nil))
      val num = Nil
      println(fruit.head)  //取List中的第一个值
      println(fruit.tail)  //去除List中的第一个值
      println(fruit.isEmpty)
      println(num.isEmpty)
    }
  }

  //串联列表
  class ListConcatTest{
    def concatTest: Unit ={
      val fruit1 = "apple" :: ("oranges" :: ("pears" :: Nil))
      val fruit2 = "managoes" :: ("banana" :: Nil)
      println(fruit1:::fruit2)             //方法①：用:::方法将多个List串联起来
      println(fruit1.:::(fruit2))          //方法②：用.:::()方法将多个List串联起来
      println(List.concat(fruit1, fruit2)) //方法③：用List.concat()方法将List串联起来
    }
  }


}
