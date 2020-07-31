package test.Java与Scala集合互操作

import java.io.{File, IOException}

object Scala捕获Java抛出的异常 extends App {
  val file: File = new File("D://a.txt");
  if (!file.exists()) {
    println("文件不存在");
    try {
      file.createNewFile();
    } catch {
      case e: IOException => println("文件不存在")
    }
  }
}

/**
  * Java中捕获Scala异常
  */
class ScalaThrower {
  //Scala利用注解 @throws声明抛出异常
  @throws(classOf[Exception])
  def exceptionThrower: Unit = {
    println("抛出异常");
    throw new Exception("Exception");
  }
}