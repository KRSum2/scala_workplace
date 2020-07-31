package test.Collection

import scala.collection.mutable

class SetMapQueueStackTest01 {
 //Set实战(不存在重复元素的集合)
  def SetTest: Unit ={
    val numsSet = Set(3.0, 5);
    println(numsSet + "----类型为：" + numsSet.getClass.toString);
    val addNumSet = numsSet+2+6+7+3
    println("添加元素（不保证的顺序）：" + addNumSet);

    //若是要对插入的顺序有严格的要求，采用scala.collection.mutable
    val linkedHashSet = scala.collection.mutable.LinkedHashSet(3.0, 5);
    println(linkedHashSet + "----类型为：" + numsSet.getClass.toString);
    val addlLnkedHashSet = linkedHashSet+2+6+7+3;
    println("添加元素（保证的顺序）：" + addlLnkedHashSet);
  }

  //Map（映射）实战
  def MapTest: Unit ={
    //默认是immutable(不可变的)
    val studentInfoImmutable = Map("john" -> 21, "stephen" -> 22, "lucy" -> 20);
    val studentInfoImmutable1 = Map(("john", 21), ("stephen", 22), ("lucy", 20));
    println("immutable初始化方法①类型为" + studentInfoImmutable.getClass.toString + "：" + studentInfoImmutable);
    println("immutable初始化方法②类型为" + studentInfoImmutable.getClass.toString + "：" + studentInfoImmutable1);
    //immutable(可变的，不保证顺序)
    val studentInfoMutable = scala.collection.mutable.Map("john" -> 21, "stephen" -> 22, "lucy" -> 20);
    println("mutable初始化类型为" + studentInfoMutable.getClass.toString + "：" + studentInfoMutable);
    println("清除内容（immutable不具备该方法）：" + studentInfoMutable.clear());
    //遍历操作①
    for (i <- studentInfoImmutable) print(i + " | ");
    println();
    //遍历操作②
    studentInfoImmutable.foreach(e => {val (k, v) = e; print(k + "：" + v + " | ")});
    println();
    //遍历操作③
    studentInfoImmutable.foreach(e => print(e._1 + "：" + e._2 + " | "));
    println();
    //定义一个HashMap
    val xMap = new mutable.HashMap[String, Int]();
    xMap.put("john", 21)
    println("添加元素：" + xMap);
    println("判断是否包含john元素：" + xMap.contains("john"));
    println("获取元素：" + xMap.get("john"));
  }

  //Queue（队列）实战
  def QueueTest: Unit ={
    //不可变immutable
    val queueImmutable = scala.collection.immutable.Queue(1, 2, 3);
    println("immutable初始化队列：" + queueImmutable);
    println("出队：" + queueImmutable.dequeue + "----取出出队的第一个元素：" + queueImmutable.dequeue._1);
    println("入队：" + queueImmutable.enqueue(4));
    //可变mutable
    val queueMutable = scala.collection.mutable.Queue(1, 2, 3, 4, 5);
    println("mutable初始化队列：" + queueMutable);
    println("出队：" + queueMutable.dequeue);
    queueMutable += 5;
    println("入队：" + queueMutable);
    queueMutable ++= List(6, 7, 8);
    println("添加集合的方式：" + queueMutable);
  }

  //Stack（栈）实战
  def StackTest: Unit ={
    //操作mutable栈
    val stack1 = new scala.collection.mutable.Stack[Int];
    println("new的创建方式：" + stack1);
    val stack = scala.collection.mutable.Stack(1, 2, 3);
    println("Apply的创建方式：" + stack);
    println("出栈：" + stack.top);
    println("入栈：" + stack.push(4));
  }
}
