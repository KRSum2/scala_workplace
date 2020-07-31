package test.Function

/**
  * 部分函数应用
  */
object PartFunctionTestMain {
  def main(args: Array[String]): Unit = {
    val p = new PartFunctionTest
    println("-------------部分应用函数的使用：--------------")
    p.Part;
  }
}

class PartFunctionTest{
  //部分函数应用
  def Part: Unit ={
    def product(x : Int, y : Int, z : Int) = x * y * z;
    def product_1 = product(_ : Int, 2, 3);
    println("使用1个参数部分应用函数：" + product_1(2));
    def product_2_1 = product(_ : Int, _ : Int, 3);
    def product_2_2 = product(2, _ : Int, _ : Int);
    println("使用2个参数部分应用函数：" + product_2_1(2, 2));
    println("使用2个参数部分应用函数：" + product_2_2(2, 3));
  }
}