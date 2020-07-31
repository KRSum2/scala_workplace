package test.Actor

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.event.Logging


object 停止Actor extends App {
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

  class ContextActor extends Actor{
    val log = Logging(context.system, this);
    val stringActor = context.actorOf(Props[StringActor], "StringActor");
    override def receive: Receive = {
      case s : String => {
        log.info("received message: \n" + s);
        stringActor ! s;
        Thread.sleep(1000);    //加上睡眠，保证消息发送到StringActor后，处理完才停止StringActor；否则会出现was not delivered未送达的情况
        context.stop(stringActor);  //停止StringActor
      }
      case _ => log.info("received unknow message");
    }
    override def postStop(): Unit = {
      log.info("postStop in ContextActor");
    }
  }
  val system = ActorSystem("StringSystem");
  val contextActor = system.actorOf(Props[ContextActor], "ContextActor")
  contextActor ! "Creating Actors with implicit val context"
  //关闭ActorSystem
  system.shutdown();
  //发送PoisonPill消息，停止Actor
  //contextActor ! PoisonPill
}
