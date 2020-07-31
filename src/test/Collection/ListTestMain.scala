package test.Collection

object ListTestMain {
  def main(args: Array[String]): Unit = {

    val list1 = new ListTest01;
    println("-----------------List创建列表---------------------");
    list1.createListTest;
    println("-----------------List常用函数---------------------");
    list1.useFunction;
    println("-----------------List伴生对象方法---------------------");
    list1.companionObject;
  }
}
