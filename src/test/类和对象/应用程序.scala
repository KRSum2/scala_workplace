package test.类和对象

/**
  * 1.应用程序实战（继承App）
  * 2.伴生类和伴生对象
  */
object 应用程序 extends App {

  //伴生类Student
  class Student{
    private var name : String = "小可爱";
    //供无new创建使用的属性
    var age : Int = _;
    //伴生类函数
    def getStudentNo={
      Student.uniqueStudentNo()
      //伴生类Student中可以直接访问伴生对象Student的私有成员
      Student.studentNo; //最后一行为返回值
    }
  }
  //伴生对象Student
  object Student{
    private var studentNo : Int = 0;
    def uniqueStudentNo() = {
      studentNo += 1;
      studentNo;
    }
    //直接访问伴生类的私有成员
    var printStudentName =  new Student().name;
    //apply方法，供非显式new创建对象时调用
    def apply() = new Student();
  }

  println("应用程序测试单例对象：" + Student.uniqueStudentNo());
  //外部不允许访问伴生类和伴生对象的私有成员
  //println(Student.studentNo);
  //println(new Student().name);
  println("输出伴生类访问的伴生对象的私有成员：" + new Student().getStudentNo);
  println("输出伴生对象访问的伴生类的私有成员：" + Student.printStudentName);

  println("------------------无new创建对象--------------------");
  val s = Student();
  s.age = 10;
  println("年龄是：" + s.age);

}


