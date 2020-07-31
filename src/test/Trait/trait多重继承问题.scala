package test.Trait

/**
  * trait多重继承问题
  */
object MultipleInheritanceTestMain {
  def main(args: Array[String]): Unit = {
    val m = new MultipleInheritanceTest;
    println("-------------trait的多重继承--------------");
    println("--多继承问题：--");
    m.multipleInheritance;
  }
}

class MultipleInheritanceTest{
  //多继承问题
  def multipleInheritance: Unit ={
    new C().print("print method in ------ ");
  }
}


trait A{
  val A = "Trait A"
  def print(msg : String) = println(msg + ":" + A);
}

trait B1 extends A {
  var B1 = "Trait B1";
  override def print(msg : String): Unit = super.print(msg + ":" + B1);
}

trait B2 extends A {
  var B2 = "Trait B2";
  override def print(msg : String): Unit = super.print(msg + ":" +  B2);
}

class C extends B1 with B2;