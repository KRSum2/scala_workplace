package test.Trait

/**
  * 特质实战
  */
object TraitTestMain {
  def main(args: Array[String]): Unit = {
    var t = new TraitTest;
    println("-------------特质trait：--------------");
    println("--trait创建：--");
    t.traitCreate;
  }
}

class TraitTest{
  //创建特质
  def traitCreate{
    //实现一个特质
    new File("config.txt").close();
    //混入多个特质用(with关键字)
    new Processable("log.txt").close();
  }

}

/**
  * 特质trait
  */
trait Closable{
  def close();
}

class File(var name : String) extends Closable{
  override def close(): Unit = println(s"File $name has been closed");
}

//混入多个trait
class Processable(var name : String) extends Closable with Cloneable {
  override def close(): Unit = println(s"File $name has been closed");
}