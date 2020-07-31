package test

import org.apache.spark.SparkConf
object SparkDemo01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application") //.setMaster("local")
  }
}
