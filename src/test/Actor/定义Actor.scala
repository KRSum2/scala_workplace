package test.Actor

import akka.actor.Actor
import akka.event.Logging

object 定义Actor extends App {
  class StringActor extends Actor{
    val log = Logging(context.system, this);
    override def receive: Receive = {
      case s : String => log.info("received message: " + s);
      case _ => log.info("received unknow message");
    }
  }
}
