package app

import zio._
import zio.console._
import zio.duration._
import zio.kafka.client._
//import zio.stream._
//import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization._

import configuration._

object Main extends App {

  implicit val serdeString = Serdes.String

  def run(args: List[String]) = {
    (for {
      c <- configuration.load
      fib <- Consumer.consumeWith[Console, String, String](Subscription.Topics(Set("test")), c.kafka.consumer) {
        (key, value) => putStrLn(s"Received message ${key}: ${value}")
        // Perform an effect with the received message
      }.fork
      _ <- ZIO.sleep(20.seconds)
      _ <- fib.interrupt
      _ <- fib.join.ignore
    } yield ()).fold(_ => 1, _ => 0)
  }

}
