package test.ModeMatch_模式匹配

/**
  * 模式匹配样例类和样例对象
  */
object SamplesTestMain{
  def main(args: Array[String]): Unit = {
    println("-------------模式匹配样例类和样例对象--------------");
    val s = new SamplesTest;
    println("--模式匹配样例类--");
    s.SamplesClass;
    println("--模式匹配样例对象--");
    s.SamplesObject;
  }
}

class SamplesTest{
  //模式匹配与样例类
  def SamplesClass{
    //handleMessage函数会处理所有可能的情况，即穷举所有的DeployMessage的子类
    def handleMessage(msg : DeployMessage) = msg match {
      case RegisterWorker(id, host, port) => s"The worker $id is registering on $host:$port";
      case UnRegisterWorker(id, host, port) => s"The worker $id is unregistering on $host:$port";
      case Heartbeat(id) => s"The worker $id is sending heartbeat";
      //case RegisterWorkerState => "Request Worker State"   注释掉会出现编译警告，这就是sealed定义的作用
    }
    val msgRegister = RegisterWorker("204799", "192.168.1.109", 8079);
    println(handleMessage(msgRegister));
  }

  //模式匹配与样例对象
  def SamplesObject{
    //handleMessage函数会处理所有可能的情况，即穷举所有的DeployMessage的子类
    def handleMessage(msg : DeployMessage) = msg match {
      case RegisterWorker(id, host, port) => s"The worker $id is registering on $host:$port";
      case UnRegisterWorker(id, host, port) => s"The worker $id is unregistering on $host:$port";
      case Heartbeat(id) => s"The worker $id is sending heartbeat";
      case RegisterWorkerState => "Request Worker State"
    }
    val msgRegister = RegisterWorker("204799", "192.168.1.109", 8079);
    println(handleMessage(msgRegister));
    println(handleMessage(RegisterWorkerState));
  }
}



//定义一个sealed trait DeployMessage
sealed trait DeployMessage;
//定义三个具体的子类，全部为case class（样例类，一定要传参数，即需要自己的成员域）
case class RegisterWorker(id : String, host : String, port : Int) extends DeployMessage
case class UnRegisterWorker(id : String, host : String, port : Int) extends DeployMessage
case class Heartbeat(workerId : String) extends DeployMessage
//定义样例对象（可以不传参数，即可以没有自己的成员域）
case object RegisterWorkerState extends DeployMessage;

