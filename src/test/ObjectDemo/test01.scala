package test.ObjectDemo

object test01 {
  def main(args: Array[String]): Unit = {
    val person = new Person();
    println(person.getAge);

    val student = new Student();
    println(student.name)

    val teacher = new Teacher("LSH", 2);
    val person1 = new Person1();
    println(person1.name1="list");

    val person2 = new Person2;
    println(person2.update("Tony"));

    val cat = new Cat;
    println(cat.sex);

    val person4 = new Person4;
    person4.myName = "小可爱"
    val i = person4.myName;
    println(i);
    println(person4.hisName)

    val student1 = new Student1
    println(student1.eat("吃饭了"))
    println(student1.work);

  }
}
