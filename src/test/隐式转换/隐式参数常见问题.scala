package test.隐式转换

/**
  * 隐式参数常见问题
  */
object FrequentlyProblemTestMain {
  def main(args: Array[String]): Unit = {
    println("-------------常见问题--------------");
    val f = new FrequentlyProblem;
    println("--问题①：当函数没有柯里化，implicit关键字会作用于函数参数列表中的所有参数--");
    f.test01;
    println("--问题②：隐式参数使用时要么全部指定，要么全部不指定，不能部分指定--");
    f.test02;
    println("--问题③：同类型的隐式值在当前作用域中只能出现一次--");
    println("--问题④：在定义隐式参数时，implicit关键字只能出现在参数开头--");
    println("--问题⑤：进行函数柯里化--");
    f.test03;
    println("--问题⑥：柯里化参数implicit顺序--");
    f.test04;
    println("--问题⑥：implicit关键字在隐式参数中只能出现一次，对柯里化函数也不例外--");
    println("--问题⑦：值函数不能使用隐式参数--");
    println("--问题⑧：柯里化函数如果有隐式参数，则不能使用其偏应用函数--");
    f.test05;
  }
}

class FrequentlyProblem{
  //当函数没有柯里化，implicit关键字会作用于函数参数列表中的所有参数
  def test01: Unit ={
    def product(implicit x : Double, y : Double) = x * y;
    implicit val d = 2.55
    println(product);
  }
  def test02: Unit ={
    def product(implicit x : Double, y : Double) = x * y;
    implicit val d = 2.55
    //全部不指定
    println("全部不指定：" + product);
    //部分指定，错误
    //println(product(3.0));
    //全部指定
    println("全部指定：" + product(3.0, 3.0));
  }
  //进行函数柯里化
  def test03: Unit ={
    def product(x : Double)(implicit y : Double) = x * y;
    implicit val d = 4.0;
    println("进行函数柯里化：" + product(3))
  }
  //柯里化函数的implicit只能作用于最后一个参数
  def test04: Unit ={
    def product(x : Double)(y : Double)(implicit z : Double)= x * y * z;
    implicit val d = 5.0;
    println(product(3.0)(4.0));
  }
  def test05: Unit ={
    def product(x : Double)(y : Double) = x * y;
    val p1 = product _;
    println("两个参数的偏应用函数：" + p1(3.0)(4.0));
    val p2 = product(3.0) _;
    println("一个参数的偏应用函数：" + p2(4.0));
    def product1(x : Double)(implicit y : Double) = x * y;
    //val p3 = product1 _;  定义为隐式参数后，便不能使用其偏应用函数
  }
}