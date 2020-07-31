package test.Package

/**
  * 包的使用和作用域
  */


object PackageTestMain {
  def main(args: Array[String]): Unit = {
    import test.Package.cn.scala._
    import test.Package.cn.scala.chapter._
    println("-------------包的使用--------------");
    Util.toString(new Teacher("john").name);
    new Teacher("john").printName();
    println("-------------包的权限--------------");
    //UtilAuthority.toString(new Teacher("john").name);
    new Teacher1("权限访问").printName();
  }
}

package cn{
  //定义权限访问类
  class UtilTest{
    //编译通不过，因为UtilAuthority利用private [scala]修饰，只能在scala及其子包中使用
    //UtilAuthority.toString();
  }
  package scala{
    //单例对象
    object Util{
      def toString(x : String) = {
        println(x);
      }
      //外层包无法访问内层包，需要引入包
      //def getTeacher() : Teacher = new Teacher("john");
      import test.Package.cn.scala.chapter._
      def getTeacher() : Teacher = new Teacher("john");
    }

    //private [scala]权限测试类
    private [scala] object UtilAuthority{
      def toString(x : String) = {
        println(x);
      }
      import test.Package.cn.scala.chapter._
      def getTeacher() : Teacher1 = new Teacher1("权限访问");
    }

    package chapter{
      class Teacher(var name : String){
        def printName() = Util.toString(name);
      }
      class Teacher1(var name : String){
        def printName() = UtilAuthority.toString(name);
      }
    }
  }
}


