package configuration

import zio._
import zio.kafka.client._
import pureconfig._
import pureconfig.generic.auto._

case class Config(kafka: KafkaConfig)
case class KafkaConfig(consumer: ConsumerSettings, producer: ProducerSettings)

trait Configuration extends Serializable {
  val config: Configuration.Service[Any]
}

object Configuration {
  trait Service[R] {
    val load: RIO[R, Config]
  }
}

trait Live extends Configuration {
  import Configuration._

  val config: Service[Any] = new Service[Any] {
    val load: Task[Config] = Task.effect(ConfigSource.default.loadOrThrow[Config])
  }
}

trait Test extends Configuration {
  import Configuration._

  val config: Service[Any] = new Service[Any] {
    val load: Task[Config] = Task.effectTotal(Config(???))
  }
}
