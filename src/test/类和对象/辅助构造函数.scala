package test.类和对象



/**
  * 辅助构造函数
  */
object AuxiliaryConstryctorTestMain {
  def main(args: Array[String]): Unit = {
    val a = new AuxiliaryConstryctorTest;
    println("-------------辅助构造函数：--------------");
    println("--辅助构造函数定义：--");
    a.defAuxiliary;
    println("--辅助构造函数默认参数：--");
    a.defaultAuxiliary;
  }
}

class AuxiliaryConstryctorTest{
  //辅助构造函数的定义
  def defAuxiliary: Unit ={
    println("调用1个参数的辅助构造函数：" + new Teacher1("John"));
    println("调用2个参数的辅助构造函数：" + new Teacher1("John", 19));
    println("调用3个参数的辅助构造函数：" + new Teacher1("John", 20, 1));
  }
  //辅助构造函数默认参数
  def defaultAuxiliary: Unit ={
    println("调用无参数的辅助构造函数：" + new Teacher2);
    println("调用1个指定参数的辅助构造函数：" + new Teacher2("John"));
  }
}

//辅助构造函数定义
class Teacher1{
  //类成员
  private var name : String = null;
  private var age : Int = 18;
  private var sex : Int = 0;
  //辅助构造函数
  def this(name : String){
    //this()调用无参的默认主构造函数(一定要先调用主构造函数)
    this();
    this.name = name;
  }
  def this(name : String, age : Int){
    //this(name)调用的是前面定义的辅助构造函数def this(name : String)
    this(name);
    this.age = age;
  }

  def this(name : String, age : Int, sex : Int){
    //this(name, age)调用的是前面定义的辅助构造函数def this(name : String, age : Int)
    this(name, age);
    this.sex = sex;
  }

  //重写toString方法
  override def toString: String = {
    val sexStr = if (sex == 1) "男" else "女";
    s"name = $name, age = $age, sex = $sexStr";
  }
}

//辅助构造函数默认参数
class Teacher2 private{  //private可有可无，如果不存在，则new Teacher2调用的是主构造函数，不调用辅助构造函数；
                                                //存在，则new Teacher2调用的是辅助构造函数，不能调用主构造函数。
  //类成员
  private var name : String = null;
  private var age : Int = 18;
  private var sex : Int = 0;
  //辅助构造函数
  def this(name : String = "", age : Int = 18, sex : Int = 0){
    //this(name, age)调用的是前面定义的辅助构造函数def this(name : String, age : Int)
    this();
    this.name = name;
    this.age = age;
    this.sex = sex;
  }
  //重写toString方法
  override def toString: String = {
    val sexStr = if (sex == 1) "男" else "女";
    s"name = $name, age = $age, sex = $sexStr";
  }
}