package test.Collection

/**
  * List测试
  */
class ListTest01 {

  //列表的创建
  def createListTest: Unit ={
    val listStr1 = List("Spark", "Hive", "HBase");
    println(listStr1);
    val listStr2 = List.apply("Spark1", "Hive1", "HBase1");
    println(listStr2);
    val listStr3 = 1::2::3::4::Nil;
    println(listStr3);
    val multiList = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9));
    println(multiList);
    println("List遍历：")
    for (i <- multiList) print(i);
    println();
  }

  //List的常用函数
  def useFunction: Unit ={
    val nums = 1::2::3::4::Nil;
    val num = 5::6::Nil;
    val numCon = nums:::num;

    println("nums集合：" + nums);
    println("num集合：" + num);
    println("nums集合是否为空？：" + nums.isEmpty);
    println("取nums集合的第一个元素：" + nums.head);
    println("去掉nums集合的第一个元素。返回剩余元素：" + nums.tail);
    println("nums集合取列表的第二个元素：" + nums.tail.head);
    println("nums和num连接操作：" + numCon);
    println("去除nums集合的最后一个元素，返回剩余元素：" + nums.init);
    println("取nums集合的最后一个元素：" + nums.last);
    println("nums集合倒置：" + nums.reverse);
    println("nums集合先倒置，再去除最后一个元素：" + nums.reverse.init);
    println("nums集合先去除第一个元素，再倒置：" + nums.tail.reverse)
    println("nums集合丢弃前3个元素：" + nums.drop(3));
    println("nums集合获取前3个元素：" + nums.take(3));
    println("将nums集合进行分割:" + nums.splitAt(1));
    println("toString方法：" + nums.toString());
    println("添加分隔符mkString方法：" + nums.mkString(","));
    println("转换成数组：" + nums.toArray.toString);
    //Zip操作
    val numChar = '1'::'2'::'3'::Nil;
    println("Zip操作：" + nums.zip(numChar) + "-----类型：" + nums.zip(numChar).getClass);
  }

  //伴生对象方法
  def companionObject: Unit ={
    println("apply方法：" + List.apply(1, 2, 3));
    println("range方法[x,y），构建[2, 6）范围内的List:" + List.range(2, 6));
    println("[2, 6）步长为2：" + List.range(2, 6, 2));
    println("[2, 6）步长为-1：" + List.range(2, 6, -1));
    println("[6, 2）步长为-1：" + List.range(6, 2, -1));
    println("构建相同元素的List：" + List.make(5, "hey"))
    val nums = 1::2::3::4::Nil;
    val num = 5::6::Nil;
    println("列表连接：" + List.concat(nums, num));

  }


}
