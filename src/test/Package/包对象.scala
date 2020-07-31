package test

/**
  * 包对象
  */
package object PackageObjectTestMain {
  val PI = 3.1415926;
  val THETA = 2.0;
  val SIGMA = 1.9;
}

class Computation{
  def computeArea(r : Double) = PackageObjectTestMain.PI * r * r
}