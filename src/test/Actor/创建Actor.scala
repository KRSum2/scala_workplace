package test.Actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * Actor的创建
  */
object 创建Actor方式_1 extends App {
    //使用默认构造函数创建
  class StringActor extends Actor{
      val log = Logging(context.system, this);
      override def receive: Receive = {
        case s : String => log.info("received message: " + s);
        case _ => log.info("received unknow message");
      }
    }
  //创建ActorSystem,ActorSystem是创建和查找Actor的入口
  //ActorSystem管理Actor共享配置信息如分发器(dispatchers)、部署(deployments)等
  val system = ActorSystem("StringSystem");
  //使用默认的构造函数创建Actor实例
  val stringActor = system.actorOf(Props[StringActor], "StringActor");
  //给stringActor发送字符串信息
  stringActor !"Creating Actors with default constructor";
  //关闭ActorSystem
  system.shutdown();
}


object 创建Actor方式_2 extends App {
  //使用非默认构造函数创建
  class StringActor(name : String) extends Actor{
    val log = Logging(context.system, this);
    override def receive: Receive = {
      case s : String => log.info("received message: " + s);
      case _ => log.info("received unknow message");
    }
  }
  //创建ActorSystem,ActorSystem是创建和查找Actor的入口
  //ActorSystem管理Actor共享配置信息如分发器(dispatchers)、部署(deployments)等
  val system = ActorSystem("StringSystem");
  //使用非默认的构造函数创建Actor实例，注意这里是Props(),而非Props[]
  val stringActor = system.actorOf(Props(new StringActor("StringActor")), "StringActor");
  //给stringActor发送字符串信息
  stringActor ! "Creating Actors with default constructor";
  //关闭ActorSystem
  system.shutdown();
}

object 创建Actor方式_3 extends App {
  //通过隐式变量context创建Actor
  class StringActor extends Actor{
    val log = Logging(context.system, this);
    override def receive: Receive = {
      case s : String => log.info("received message: \n" + s);
      case _ => log.info("received unknow message");
    }
  }
  //再定义一个Actor，在内部通过context创建Actor
  class ContextActor extends Actor{
    val log = Logging(context.system, this);
    //通过context创建StringActor
    var stringActor = context.actorOf(Props[StringActor], "StringActor");
    override def receive: Receive = {
      case s : String => log.info("received message: \n" + s); stringActor !s
      case _ => log.info("received unknow message");
    }
  }
  val system = ActorSystem("StringSystem")
  val contextActor = system.actorOf(Props[ContextActor], "ContextActor");
  //给contextActor发送字符串信息
  contextActor ! "Creating Actors with default constructor";
  //关闭ActorSystem
  system.shutdown();
}
