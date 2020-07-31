package test.ModeMatch_模式匹配

/**
  * 模式匹配实战
  */
object ModeMatchUseTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------模式匹配--------------");
    val m = new ModeMatchUseTest;
    println("--模式匹配使用--");
    m.use
    println("--模式匹配函数中使用--");
    println(m.functionUse(5));
  }
}


class ModeMatchUseTest{
  //模式匹配的使用
  def use: Unit ={
    for (i <- 1 to 5)
      i match {
        case 1 => println(1);
        case 2 => println(2);
        case 3 => println(3);
        case _ => println("其他");
      }
  }

  //模式匹配在函数中使用，返回值
  def functionUse(x : Int) = x match {
    //常量模式
    case 5 => "能被5整除";
    //变量模式
    case x if (x % 2 == 0) => "能被2整除";
    case _ => "其他整数";
  }

}

