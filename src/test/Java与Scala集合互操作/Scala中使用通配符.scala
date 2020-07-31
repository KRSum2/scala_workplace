package test.Java与Scala集合互操作
import java.util.List
import  scala.collection.JavaConversions._

object Scala中使用通配符 extends App {
  class ScalaExistTypeToJavaWildcardGeneric{
    //采用Scala中的存在类型与Java中的通配符泛型进行互操作
    def printList(list: List[T] forSome {type T}) = {
      list.foreach(println);
    }
    //与上面一样的方法
    def printList2(list: List[_]) = {
      list.foreach(println);
    }
  }
  val s = new ScalaExistTypeToJavaWildcardGeneric;
  s.printList(JavaWildcardGeneric.getList);
  s.printList2(JavaWildcardGeneric.getList);
}
