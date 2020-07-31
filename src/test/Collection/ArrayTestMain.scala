package test.Collection

object ArrayTestMain {
  def main(args: Array[String]): Unit = {
    val t1 = new ArrayTest01;
    //可变集合
    println("-------------可变集合操作：--------------")
    t1.mutableTest;
    println("-------------val,var定义的immutable之间的赋值:-------------");
    t1.comparison;
    println("-------------数组：-------------")
    t1.ArrayTest;
  }
}
