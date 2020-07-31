package test.Actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.routing.RandomRouter

object 使用Router extends App {

  class IntActor extends Actor {
    val log = Logging(context.system, this);

    override def receive: Receive = {
      case s: Int => log.info("received message: \n" + s);
      case _ => log.info("received unknow message");
    }
  }
  val system = ActorSystem("RandomRouterExample");
  val randomRouter = system.actorOf(Props[IntActor].withRouter(RandomRouter(5)),"IntActor");
  1 to 10 foreach{
    i => randomRouter ! i
  }
  system.shutdown();
}
