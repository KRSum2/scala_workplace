package test.类和对象

/**
  * 单例对象实战
  */
object SingleCaseObjectTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------单例对象操作：--------------");
    var s = Student.uniqueStudentNo().toString;
    println("单例对象实战：" + s);
  }

}


//单例对象实战(相当于java的静态)
object Student {

  private var studentNo = 0;

  def uniqueStudentNo() = {
    studentNo += 1;
    studentNo;
  }

}
