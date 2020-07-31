package test.类型参数

object 高级类型 extends App {

}


/**
  * 单例类型
  */
object 单例类型 extends App{
  /**
    * 单例对象
    */
  object Cat
  import scala.reflect.runtime.universe.typeOf
  println(typeOf[Cat.type]);
  val x : Cat.type = Cat;

  /**
    * 普通类
    */
  class Dog
  val dog1 = new Dog;
  val t1 = typeOf[dog1.type];
  println("dog1.type是什么类型？------" + t1);             //dog1的单例类型为dog1.type
  val x1 : dog1.type = dog1;    //dog1.type为Dog类的子类且它有唯一的对象实例dog1
  typeOf[dog1.type] <:< typeOf[Dog] ;  //验证dog1.type为Dog类的子类这一事实

  val dog2 = new Dog;
  val t2 = typeOf[dog2.type];
  println("dog2.type是什么类型？------" + t2);             //dog2的单例类型为dog2.type
  val x2 : dog2.type = dog2;    //dog2.type为Dog类的子类且它有唯一的对象实例dog2
  //val x : dog1.type = dog2;    //dog1.type只有唯一的对象实例dog1，将dog2赋值给dog1.type类型的变量会产生类型不匹配
  val b1 = typeOf[dog1.type] == typeOf[dog2.type];
  val b2 = dog1.getClass == dog2.getClass;
  println("dog1.type和dog2.type的类型是否相同？------" + b1);
  println("dog1和dog2对象的类是相同的------" + b2)
}

object 单例类型的链式调用 extends App{
  class Pet{
    private var name : String = null;
    private var weight : Float = 0.0f
    def setName(name : String) : this.type = {  //返回类型为单例类型
      this.name = name
      //返回调用对象，类型为Pet
      this
    }
    def setWeight(weight : Float) : this.type = {   //返回类型为单例类型
      this.weight = weight
      //返回调用对象，类型为Pet
      this
    }
    override def toString: String = s"name = $name, weight = $weight";
  }

  class Dog extends Pet{
    private var age : Int = 0;
    def setAge(age : Int) : this.type = {     //返回类型为单例类型
      this.age = age;
      //返回调用对象，类型为Dog
      this;
    }
    override def toString: String = super.toString + s",age = $age";
  }
  println(new Dog().setAge(11).setName("Nacy").setWeight(20.0f));
  println(new Dog().setName("john").setWeight(30.0f).setAge(21));
}

/**
  * 类型投影
  */
object 类型投影 extends App{
  import scala.reflect.runtime.universe.typeOf;
  class Outter{
    val x : Int = 0;
    class Inner;
  }
  val o1 = new Outter;
  val o2 = new Outter;
  val b : Boolean = typeOf[o1.Inner] == typeOf[o2.Inner];
  println("o1.Inner和o2.Inner的类型是否相同？----" + b);
}

object 类型投影应用 extends App{
  class Outter{
    val x : Int = 0;
    class Inner;
    def test1(i : Inner) = i;                          //定义Outter的成员方法test1，方法参数为内部类Inner
    def test2(i : Outter # Inner) = i;        //将o1.Inner和o2.Inner两种不同的类型投影为Outter # Inner的子类
  }
  val o1 = new Outter;
  val o2 = new Outter;
  val inner1 = new o1.Inner;
  val inner2 = new o2.Inner;

  o1.test1(inner1);
  //o1.test1(inner2);     //编译报错，因为o1.Inner和o2.Inner是两种不同的类型
  o1.test2(inner1);
  o1.test2(inner2);       //使用类型投影后，便可以运行
}

/**
  * 类型别名
  */
object 类型别名 extends App{
  type JavaHashMap1 = java.util.HashMap[String, String];      //类型别名必须指定具体泛型参数类型的K,V
  val map1 = new JavaHashMap1;
  map1.put("Hadoop", "21");

  //类型重命名
  import java.util.{HashMap => JavaHashMap2}            //类型重命名不能指定具体泛型参数类型的K,V
  val map2 = new JavaHashMap2[String, Int]();
  map2.put("Hive", 32);
}

/**
  * 抽象类型
  */
object 抽象类型 extends App{
  abstract class Food;
  class Rice extends Food{
    override def toString: String = "粮食";
  }
  class Meat extends Food{
    override def toString: String = "肉";
  }
  class Animal{
    type FoodType;                                                //定义一抽象类型FoodType
    def eat(f : FoodType) = f;             //函数参数类型为FoodType
  }
  class Human extends Animal{
    type FoodType = Food; //子类中确定其具体类型为Food
    override def eat(f: FoodType): Food = f
  }
  class Tiger extends Animal{
    type FoodType = Meat; //子类中确定其具体类型为Meat
    override def eat(f: FoodType): Meat = f
  }
  val human = new Human;
  val tiger = new Tiger;
  println("人可以吃：" + human.eat(new Rice));
  println("人可以吃：" + human.eat(new Meat));
  println("老虎可以吃：" + human.eat(new Meat));
}

/**
  * 复合类型
  */
object 复合类型 extends App{
  class TestA;
  class TestB extends TestA with Cloneable;
  def test(x : TestA with Cloneable) = println("ok");   //TestA with Cloneable为复合类型，TestB为其子类
  test(new TestB);
  class TestC extends TestB
  test(new TestC)
  //取别名
  type CompoundType = TestA with Cloneable;
  def test1(x : CompoundType) = println("ok");
  test1(new TestB);
  test1(new TestC);
}

/**
  * 函数类型
  */
object 函数类型 extends App{
  //三种函数是等价的，只是表达方式不一样
  val sum1 = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2;
  }
  val sum2 = (x : Int, y :Int) => x + y;
  def sum3(x : Int, y : Int) = x + y;
  println(sum1(7, 3));
  println(sum2(7, 3));
  println(sum3(7, 3));
}