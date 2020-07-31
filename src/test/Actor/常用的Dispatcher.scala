package test.Actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

object 常用的Dispatcher extends App {
  class StringActor extends Actor{
    val log = Logging(context.system, this);
    override def receive: Receive = {
      case s : String => log.info("received message: \n" + s);
      case _ => log.info("received unknow message");
    }

    override def postStop(): Unit = {
      log.info("postStop in StringActor");
    }
  }
  //从application.conf配置文件中加载dispatcher配置信息
  val system = ActorSystem.create("DispatcherSystem", ConfigFactory.load().getConfig("Akk-Default-Dispatcher-Example"));
  //创建Actor时通过withDispatcher方法指定自定义的Dispatcher
  val stringActor = system.actorOf(Props[StringActor].withDispatcher("defaultDispatcher"), "StringActor");
  stringActor ! "Test";
  system.shutdown();
}

//application.conf
/*
Akk-Default-Dispatcher-Example{
  defaultDispatcher{
    //指定Dispatcher的类型，type = Dispatcher表示使用默认的类型
    type = Dispatcher
    //指定ExecutionService的类型
    executor = "fork-join-executor"
    //fork-join-executor的配置信息
    fork-join-executor{
      //与并行度乘积因子一起，控制最小的线程数
      parallelism-min = 2
      //并行度因子
      parallelism-factor = 2.0
      //与并行度乘积因子一起，控制最大的线程数
      parallelism-max = 6
    }
  }
}*/
