package test.类型参数
import scala.collection._
/**
  * 泛型接口和泛型方法
  */
object 泛型接口和泛型方法 extends App {
  //泛型接口
  //trait Map[A, B] extends Iterable[(A, B)]
   //                with GenMap[A, B]
    //               with Map[A, B]
    //               with MapLike[A, B, Map[A, B]]{
    //泛型方法
    //override def empty: Map[A, B] = Map.empty
    //override def seq: Map[A, B] = this;
    //def withDefault(d : A => B) : scala.collection.mutable.Map[A, B] = new Map.WithDefault[A, B](this, d);
    //def withDefaultValue(d : B) : scala.collection.mutable.Map[A, B] = new Map.WithDefault[A, B](this, x => d);
  //}
}
