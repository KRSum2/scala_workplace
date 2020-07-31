package test.Actor


import akka.actor.TypedActor.{PostStop, PreStart}
import akka.actor.{ActorSystem, TypedActor, TypedProps}
import akka.event.Logging

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._

object Typed_Actor extends App{
  //Typed Actor定义
  trait Squarer{
    def squareDontCare(i : Int) : Unit; //fire-and-forget
    def square(i : Int) : Future[Int]   //非阻塞式send-request-reply消息
    def squareNowPlease(i : Int) : Option[Int]  //阻塞式send-request-reply消息
    def squareNow(i : Int) : Int //阻塞式send-request-reply消息
  }

  class SquarerImpl(val name : String) extends Squarer with PostStop with PreStart{
    def this() = this("SquarerImpl");
    override def squareDontCare(i: Int): Unit = i * i;
    override def square(i: Int): Future[Int] = Promise.successful(i * i).future;
    override def squareNowPlease(i: Int): Option[Int] = Some(i * i);
    override def squareNow(i: Int): Int = i * i

    override def postStop(): Unit = log.info("TypedActor Stopped")
    override def preStart(): Unit = log.info("TypedActor Started")
  }

  //Typed Actor创建
  val system = ActorSystem("TypedActorSystem");
  val log = Logging(system, this.getClass);
  val mySquarer : Squarer = TypedActor(system).typedActorOf(TypedProps[SquarerImpl](), "mySquarer")  //使用默认构造函数创建
  val otherSquarer : Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer], new SquarerImpl("SquarerImpl")), "otherSquarer");//使用非默认构造函数创建

  //消息发送
  mySquarer.squareDontCare(10)   //fire-forget消息发送

  val oSquarer = mySquarer.squareNowPlease(10);  //send-request-reply消息发送
  log.info("oSquarer=" + oSquarer);

  val iSquarer = mySquarer.squareNow(10);
  log.info("iSquarer=" + iSquarer);

  val fSquarer = mySquarer.square(10);
  val result = Await.result(fSquarer, 5 seconds);
  log.info("fSquarer=" + result);

  //三种关闭方法
  system.shutdown();
  TypedActor(system).poisonPill(otherSquarer);
  TypedActor(system).stop(mySquarer);

}
