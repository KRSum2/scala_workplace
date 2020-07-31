package test.Actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

object Actor的其他使用方法 extends App {
  class StringActor extends Actor{
    val log = Logging(context.system, this);

    //创建Actor时调用，在接受和处理信息前执行。主要用于Actor的初始化等工作
    override def preStart(): Unit = {
      log.info("preStart method in StringActor");
    }

    //Actor停止时调用的方法
    override def postStop(): Unit = {
      log.info("postStop method in StringActor");
    }
    //有未能处理的消息时调用
    override def unhandled(message : Any)={
      log.info("unhandled method in StringActor");
      super.unhandled(message);
    }
    override def receive: Receive = {
      case s : String => log.info("received message: \n" + s);
    }
  }
  val system = ActorSystem("StringSystem");
  val stringActor = system.actorOf(Props[StringActor], "StringActor");
  //给StringActor发送字符串信息
  stringActor ! "Creating Actors with default constructor";
  //给StringActor发送整型数据，触发调用unhandled方法，因为定义的receive方法只能处理String类型的数据
  stringActor ! 123;
  system.shutdown();
}
