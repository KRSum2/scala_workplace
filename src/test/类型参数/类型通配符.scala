package test.类型参数

object 类型通配符 extends App {
  val arrInt : Array[Int] = Array(1, 2, 3);
  val arrStr : Array[String] = Array("Hadoop", "Hive", "Spark");
  printAll(arrStr);
  printAll(arrInt);
  printAllSimplify(arrStr);
  printAllSimplify(arrInt);
  //存在类型Array[T] forSome{type T}
  def printAll(x: Array[T] forSome {type T}) = {
    for (i <- x){
      print(i + " ");
    }
    println();
  }
  //存在类型简化,Array[_]与Array[T] forSome{type T}等价
  def printAllSimplify(x : Array[_]): Unit ={
    for (i <- x){
      print(i + " ");
    }
    println();
  }


  /**
    * 多个类型参数
    */
  val strMap = Map("Spark" -> 1);
  val IntMap = Map(2 -> 1);
  printAllMulti(strMap);
  printAllMulti(IntMap);
  printAllMultiSimplify(strMap);
  printAllMultiSimplify(IntMap);
  def printAllMulti(x: Map[T, U] forSome {type T; type U}) = {
    for (i <- x){
      print(i + " ");
    }
    println();
  }
  def printAllMultiSimplify(x: Map[_, _]) = {
    for (i <- x){
      print(i + " ");
    }
    println();
  }
}
