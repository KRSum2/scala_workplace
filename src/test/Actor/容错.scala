package test.Actor

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration._

object 容错 extends App {
  case class NormalMessage()
  class ChildActor extends Actor with ActorLogging{
    var state : Int = 0;
    override def preStart()= log.info("启动 ChildActor,其hashcode为：" + this.hashCode());
    override def postStop()= log.info("停止 ChildActor,其hashcode为：" + this.hashCode());

    override def receive: Receive = {
      case value : Int => if (value <= 0) throw new ArithmeticException("数字小于等于0") else state = value;
      case result : NormalMessage => sender ! state;
      case ex : NullPointerException => throw new NullPointerException("空指针");
      case _ => throw new IllegalArgumentException("非法参数");
    }
  }

  class SupervisorActor extends Actor with ActorLogging{
    val childActor = context.actorOf(Props[ChildActor], "ChildActor")

    override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 10 seconds){
      case _ : ArithmeticException => Resume      //采用Resume（恢复）机制
      case _ : NullPointerException => Restart    //采用Restart（重启）机制
      case _ : IllegalArgumentException => Stop   //采用Stop（停止）机制
      case _ : Exception => Escalate              //采用Escalate（上报）机制
    }
    override def receive: Receive = {
      case msg : NormalMessage => childActor.tell(msg, sender)
      case msg : Object => childActor ! msg
    }
  }

  val system = ActorSystem("FaultToleranceSystem")
  val log = system.log
  val supervisor = system.actorOf(Props[SupervisorActor], "SupervisorActor")

  //正数，消息正常处理
  supervisor ! 5
  implicit val timeout = Timeout(5 second)
  var future = (supervisor ? new NormalMessage).mapTo[Int];
  var resultMsg = Await.result(future, timeout.duration);
  log.info("结果：" + resultMsg);

  //负数，Actor会抛出异常，Superrvisor使用Resume处理机制
  supervisor ! -5
  future = (supervisor ? new NormalMessage).mapTo[Int];
  resultMsg = Await.result(future, timeout.duration);
  log.info("结果：" + resultMsg);

  //空指针消息，Actor会抛出异常，Superrvisor使用Restart处理机制
  supervisor ! new NullPointerException
  future = (supervisor ? new NormalMessage).mapTo[Int];
  resultMsg = Await.result(future, timeout.duration);
  log.info("结果：" + resultMsg);

  //String类型参数为非法参数，Actor会抛出异常，Superrvisor使用Restart处理机制
  supervisor ? "字符串"
  system.shutdown();
}
