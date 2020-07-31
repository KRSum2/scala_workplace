package test.类型参数

object 多重界定 extends App {
  class A[T]
  class B[T]
  implicit val a = new A[String]
  implicit val b = new B[String]
  //多重上下文界定，必须存在两个隐式值，类型为A[T],B[T]类型
  //前面定义的两个隐式值a，b
  def test [T : A : B](x : T) = println(x)
  test("测试");


  implicit def t2A[T](x : T) = new A[T]
  implicit def t2B[T](x : T) = new B[T]
  //多重视图定义，必须T到A，T到B的隐式转化
  //前面定义的两个隐式转换函数就是
  def test2[T <% A[T] <% B[T]](x : T) = println(x)
  test2("测试2");
}
