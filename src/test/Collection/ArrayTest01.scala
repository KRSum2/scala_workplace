package test.Collection

import scala.collection.mutable.ArrayBuffer

class ArrayTest01 {

  //可变集合
  def mutableTest: Unit ={
    val map = scala.collection.mutable.Map(1 -> "x", 2 -> "y")
    println(map);
    map += (3 -> "y");
    //map = scala.collection.mutable.Map(1 -> "x", 2 -> "y")//不允许map重新指定对象，因为是用val定义
    println(map);
  }

  //不可变集合操作
  def immutableTest: Unit ={
    val map = Map(1 -> "x", 2 -> "y")
   // map += (3 -> "y"); //不允许改变内容，因为immutable是不可改变你数组
  }

  //val,var定义的immutable之间的赋值
  def comparison: Unit ={
    var map = Map(1 -> "x", 2 -> "y")  //默认是immutable
    val original_map = map;
    println(original_map eq map)
    map += (3 -> "y");  //虽然是immutable的不可变集合，但是用var定义可以改变内容，但此时的map是新的对象，不是原来的map
    println(original_map eq map);
    println("original_map=" + original_map);
    println("map=" + map);
  }

  //数组
  def ArrayTest: Unit ={
    //定长数组Array
    val numberArray = new Array[Int](10);
    println(numberArray);
    //对定长数组赋值
    numberArray(0) = 2;
    println(numberArray);
    //变长数组ArrayBuffer
    val StrArray = ArrayBuffer[String]();
    StrArray += "Hello";   //+=在尾部增加元素
    println(StrArray);
    StrArray ++= Array("World", "Programmer");  //在尾部增加任何的集合，如（Array，list等）
    println(StrArray);
    StrArray ++= List("Welecome", "to", "Scala World");
    println(StrArray);
    val IntArray = ArrayBuffer(1, 2, 3);
    println(IntArray);
    IntArray.insert(0, 6, 7);  //在数组下标为0的位置插入6，7
    println(IntArray);
    IntArray.remove(0, 2); //从索引0开始，删除2个元素
    println(IntArray);
    IntArray.toArray;                //变长数组转换成定长数组
    IntArray.toArray.toBuffer;       //定长数组转换成变长数组

    //数组的遍历
    //to方式
    for (i <- 0 to IntArray.length-1) print(IntArray(i) + " ");
    println("---------------to方式");
    //until方式
    for (i <- 0 until IntArray.length) print(IntArray(i) + " ");
    println("---------------until方式");
    //加上判断条件(步长为2)
    for (i <- 0 until (IntArray.length, 2)) print(IntArray(i) + " ");
    println("-----------------步长为2方式");
    //倒序输出
    for (i <- (0 until IntArray.length).reverse) print(IntArray(i) + " ");
    println("---------------倒序输出方式");
    //推荐使用的数组遍历方式
    for (i <- IntArray) print(i + " ");
    println("---------------推荐使用的数组遍历方式");

    //遍历生成新的数组
    var IntArray2 = for (i <- IntArray) yield i * 2;
    println(IntArray2);
    //加入过滤条件
    var IntArray3 = for (i <- IntArray if  i >= 2) yield  i * 2;
    println(IntArray3);

    //常用函数
    println("求和：" + IntArray.sum);
    println("求最大值：" + IntArray.max);
    println("求最小值：" + IntArray.min);
    println("添加分隔符：" + IntArray.mkString(","));
    println("添加<>在前后，中间添加分隔符：" + IntArray.mkString("<",",",">"));

    //二维数组
    var multiDimArr = Array(Array(1, 2, 3), Array(4, 5, 6));
    for (i <- multiDimArr)
      for (j <- i) print(j + ",");
    println("---------------二维数组遍历方式");






  }

}
