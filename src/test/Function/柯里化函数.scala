package test.Function

/**
  * 柯里化函数实战
  */
object CorrificationFunctionTestMain {
  def main(args: Array[String]): Unit = {
    val c = new CorrificationFunctionTest;
    println("-------------函数柯里化的使用：--------------")
    c.Corrification;
  }
}

class CorrificationFunctionTest{
  //函数柯里化
  def Corrification: Unit ={
    //热身
    def higherOrderFunction(factor : Int) = (x : Double) => factor * x;
    println("柯里化实战前热身：" + higherOrderFunction(10)(50));
    println("会返回一个对象：" + higherOrderFunction(10));
    //柯里化函数实战
    def corrification(factor : Int)(x : Double) = factor * x;
    println("柯里化函数实战：" + corrification(10)(50));
    //部分函数应用
    def fun = corrification(10)_;
    println("部分函数应用：" + fun(50));

  }
}

