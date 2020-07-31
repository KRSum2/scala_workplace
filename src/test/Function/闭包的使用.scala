package test.Function

object ClosureFunctionTestMain {
  def main(args: Array[String]): Unit = {
    val c = new ClosureFunctionTest;
    println("-------------闭包的使用：--------------")
    c.Closure;
  }
}

/**
  * 函数的闭包：代码val f = (x: Int) => x + i定义了一个函数字面量，函数中使用了自由变量i，变量i在程序的运行过程中会发生变化，
  *             在函数执行时如调用f（10）时会根据运行时变量i的值不同，得到不同的运行结果。自由变量i在运行过程中会不断地发生
  *             变化，它处于一种开放状态，而当函数执行时，自由变量i的值已经被确定下来，此时可以认为在运行时它暂时处于封闭状
  *             态，这种存在从开放到封闭过程的函数被称为闭包。函数字面量val f = (x: Int) => x + i中便是函数（f）+上下文
  *             （自由变量i）的结合。
  */
class ClosureFunctionTest {
  //闭包测试
  def Closure: Unit = {
    var i = 15;
    //定义一个函数字面量f，函数中使用了前面定义的变量i
    val f = (x: Int) => x + i;
    println("执行函数：" + f(10))
    i = 20;
    println("执行函数：" + f(10));    //闭包测试结束

    //高阶函数a，函数有两个参数：函数类型Double=>Double的参数q、Double=>Unit的参数p
    def a(q: Double => Double, p: Double => Unit) = p(q(10));
    //定义一个输入参数Double,返回值类型为Double的函数，
    val q = (x: Double) => x * 2;
    def qcopy(x: Double) = x * 2

    def qcopycopy = (x: Double) => x * 2;

    val q1 = (x: Double) => x * x;
    def q1copy(x: Double) = x * x

    def q1copycopy = (x: Double) => x * x;
    //定义一个输入参数Double,返回值类型为Unit的函数，
    val p = (x: Double) => println("函数的运算结果为：" + x);
    a(q, p);
    a(qcopy, p);
    a(qcopycopy, p);
    a(q1, p);
    a(q1copy, p);
    a(q1copycopy, p);
  }
}