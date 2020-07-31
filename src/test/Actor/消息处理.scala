package test.Actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * Fire-And-Forget(!)重载方法tell
  */
object 消息处理_1 extends App {
  //定义几种不同的消息
  case class Start(var msg : String)
  case class Run(var msg : String)
  case class Stop(var msg : String)
  class OtherActor extends Actor{
    val log = Logging(context.system, this);
    override def receive: Receive = {
      case s : String => log.info("received message: \n" + s);
      case _ => log.info("received unknow message");
    }
  }
  class ExampleActor extends Actor{
    val log = Logging(context.system, this);
    val other = context.actorOf(Props[OtherActor], "OtherActor")
    override def receive: Receive = {
      case Start(msg) => other ! msg;            //使用Fire-And-Forget消息模型向OtherActor发送消息，隐式地传递sender
      case Run(msg) => other.tell(msg, sender);  //使用Fire-And-Forget消息模型向OtherActor发送消息，直接调用tell方法，显式指定sender
    }
  }
  val system = ActorSystem("MessageProcessingSystem")
  val exampleActor = system.actorOf(Props[ExampleActor], "ExampleActor");
  exampleActor ! Start("Starting");
  exampleActor ! Run("Running");
  system.shutdown();
}


import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.{ask, pipe}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Send-And-Receive-Future(?)重载方法ask
  */
object 消息处理_2 extends App{
  //消息：个人基础信息
  case class BasicInfo(id : Int, val name : String, age : Int);
  //消息：个人兴趣信息
  case class InterestInfo(id : Int, val interest : String)
  //消息：完整个人信息
  case class Person(basicInfo: BasicInfo, interestInfo: InterestInfo)

  //基础信息对应Actor
  class BasicInfoActor extends Actor{
    val log = Logging(context.system, this)
    override def receive: Receive = {
      //处理发送而来的用户ID，然后将结果发送给sender（本例对应CombineActor）
      case id : Int => log.info("id=" + id);sender ! new BasicInfo(id, "John", 19);
      case _ => log.info("receive unknown message");
    }
  }

  //兴趣爱好对应Actor
  class InterestInfoActor extends Actor {
    val log = Logging(context.system, this)
    override def receive: Receive = {
      //处理发送而来的用户ID，然后将结果发送给sender（本例对应CombineActor）
      case id : Int => log.info("id=" + id);sender ! new InterestInfo(id, "足球");
      case _ => log.info("receive unknown message");
    }
  }

  //Person完整信息对应Actor
  class PersonActor extends Actor{
    val log = Logging(context.system, this)
    override def receive: Receive = {
      case person : Person => log.info("Person=" + person);
      case _ => log.info("receive unknown message");
    }
  }

  class CombineActor extends Actor{
    implicit val timeout = Timeout(5 seconds);
    val basicInfoActor = context.actorOf(Props[BasicInfoActor], "BasicInfoActor");
    val interestInfoActor = context.actorOf(Props[InterestInfoActor], "InterestInfoActor");
    val personActor = context.actorOf(Props[PersonActor], "PersonActor");
    override def receive: Receive = {
      case id:Int =>
        val combineResult:Future[Person] = for{
            //向basicInfo 发送 send-and-receive-future 消息 mapTo方法返回将返回结果映射为BasicInfo类型
            basicInfo <-   ask(basicInfoActor,id).mapTo[BasicInfo]
            //向InterestInfo发送 send-and-receive-future 消息
            //三种方式
            interestInfo <- ask(interestInfoActor,id).mapTo[InterestInfo]
            //interestInfo <- (interestInfoActor ask id).mapTo[InterestInfo]
            //interestInfo <- (interestInfoActor ? id).mapTo[InterestInfo]
          } yield Person(basicInfo,interestInfo)
        //将Future结果发送给PersonActor
        pipe(combineResult).to(personActor)
    }
  }
  val system = ActorSystem("Send-And-Receive-Future");
  val combineActor = system.actorOf(Props[CombineActor], "CombineActor");
  combineActor ! 12345;
  Thread.sleep(5000);
  system.shutdown();

}