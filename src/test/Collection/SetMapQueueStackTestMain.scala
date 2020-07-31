package test.Collection

object SetMapQueueStackTestMain {
  def main(args: Array[String]): Unit = {
    val s = new SetMapQueueStackTest01;
    println("-------------Set集合测试:-------------");
    s.SetTest;
    println("-------------Map映射测试:-------------");
    s.MapTest;
    println("-------------Queue队列测试:-------------");
    s.QueueTest;
    println("-------------Stack栈测试:-------------");
    s.StackTest;

  }
}
