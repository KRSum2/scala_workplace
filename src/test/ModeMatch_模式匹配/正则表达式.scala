package test.ModeMatch_模式匹配

import java.util.regex.Pattern

import scala.util.matching.Regex

/**
  * 正则表达式
  */
object RegularExpressionTestMain {
  def main(args: Array[String]): Unit = {
    val r = new RegularExpressionTest;
    println("-------------Java正则表达式--------------");
    r.JavaRegularExpressionDemo;
    println("-------------Scala正则表达式--------------");
    r.ScalaRegularExpressionDemo;
    println("-------------正则表达式常用方法--------------");
    r.useMethod;
    println("-------------正则表达式在模式匹配中的应用--------------");
    r.regularAndMode;
    r.regularAndMode1;
  }
}

class RegularExpressionTest{
  //java正则表达式测试
  def JavaRegularExpressionDemo: Unit ={
    //正则表达式待匹配的字符串
    val line = "Hadoop has been the most popular big data processing tool since 2005-11-21";
    //正则表达式，用于匹配年-月-日这样的日期格式
    val rgex = "(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)";
    //根据正则表达式创建Pattern对象
    val pattern = Pattern.compile(rgex);
    //创建Matcher对象
    val m = pattern.matcher(line);
    if (m.find()){
      // m.group(0)返回的整个匹配结果，即(\d\d\d\d)-(\d\d)-(\d\d)匹配结果
      println(m.group(0));
      //m.group(1)返回第一个分组匹配的结果，即(\d\d\d\d)年的匹配结果;
      println(m.group(1));
      //m.group(2)返回第二个分组匹配的结果，即(\d\d)月的匹配结果;
      println(m.group(2));
      //m.group(3)返回第三个分组匹配的结果，即(\d\d)日的匹配结果;
      println(m.group(3));
    }else{
      println("未找到匹配项");
    }
  }

  //scala正则表达式测试
  def ScalaRegularExpressionDemo: Unit ={
    //正则表达式待匹配的字符串
    val line = "Hadoop has been the most popular big data processing tool since 2005-11-21";
    //正则表达式，用于匹配年-月-日这样的日期格式
    val rgex = """(\d\d\d\d)-(\d\d)-(\d\d)""".r;
    //创建Matcher对象
    val m = rgex.pattern.matcher(line);
    if (m.find()){
      // m.group(0)返回的整个匹配结果，即(\d\d\d\d)-(\d\d)-(\d\d)匹配结果
      println(m.group(0));
      //m.group(1)返回第一个分组匹配的结果，即(\d\d\d\d)年的匹配结果;
      println(m.group(1));
      //m.group(2)返回第二个分组匹配的结果，即(\d\d)月的匹配结果;
      println(m.group(2));
      //m.group(3)返回第三个分组匹配的结果，即(\d\d)日的匹配结果;
      println(m.group(3));
    }else{
      println("未找到匹配项");
    }
  }

  //正则表达式常用方法
  def useMethod: Unit ={
    //正则表达式待匹配的字符串
    val line = "Hadoop has been the most popular big data processing tool since 2015-12-31 2016-02-20";
    //正则表达式，用于匹配年-月-日这样的日期格式
    val rgex = """(\d\d\d\d)-(\d\d)-(\d\d)""".r;
    //方法①findAllIn   匹配字符串中所有与输入模式相匹配的字符
    for (date <- rgex.findAllIn(line)) println("findAllIn方法：" + date);
    //方法②findAllMatchIn  返回所有匹配模式，groupCount返回的是匹配模式的分组数
    for (date <- rgex.findAllMatchIn(line)) println("findAllMatchIn方法：" + date + "  ---分组数---  " +  date.groupCount);
    //方法③findFirstIn,返回匹配成功的第一个字符串，匹配失败返回None
    rgex.findFirstIn(line) match {
      case Some(date) => println("findFirstIn方法：" + date);
      case None => println("No copyright")
    }
    //方法④findFirstMatchIn,返回匹配成功的字符串
    val rgex1 = new Regex("""(\d\d\d\d)-(\d\d)-(\d\d)""", "year", "month", "day");
    rgex1.findFirstMatchIn(line) match {
      case Some(y) => println("findFirstMatchIn方法：" + y.group("year"));
      //case Some(m) => println("findFirstMatchIn方法：" + m.group("month"));
      case None => println("未匹配成功");
    }
    //方法⑤replaceAllIn，替换匹配成功的字符串
    println("replaceAllIn方法：" + rgex1.replaceAllIn(line, m => m.group("month") + "/" + m.group("day")));
  }

  //正则表达式与模式匹配
  def regularAndMode: Unit ={
    //正则表达式待匹配的字符串
    val line = "2015-12-31 2016-02-20";
    //正则表达式，用于匹配年-月-日这样的日期格式
    val regx = """(\d\d\d\d)-(\d\d)-(\d\d)""".r;
    //提取模式分组信息方式①
    for (date <- regx.findAllIn(line)){
      date match {
        case regx(year, month, day) => println(s"match语句中的模式匹配：year=$year, month=$month, day=$day");
        case _ =>;
      }
    }
    //提取模式分组信息方式②
    for (regx(year, month, day) <- regx.findAllIn(line)){
      println(s"for循环中的正则表达式模式匹配：year=$year, month=$month, day=$day");
    }
  }

  def regularAndMode1: Unit ={
    //正则表达式待匹配的字符串
    val line = "2015-12-31 2016-02-20";
    //正则表达式，用于匹配年-月-日这样的日期格式
    val regx = """(\d\d\d\d)-(\d\d)-(\d\d)""".r;
    //findFirstMatchIn提取模式分组信息方式,返回类型Option[Match]
    regx.findFirstMatchIn(line) match {
      case Some(regx(year, month, day)) => println(s"findFirstMatchIn与模式匹配：year=$year, month=$month, day=$day");
      case None => println("未匹配成功");
    }
    //findFirstIn提取模式分组信息方式,返回类型Option[String]
    regx.findFirstIn(line) match {
      case Some(regx(year, month, day)) => println(s"findFirstIn与模式匹配：year=$year, month=$month, day=$day");
      case None => println("未匹配成功");
    }
  }

}
