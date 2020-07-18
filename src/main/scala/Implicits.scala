import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

object Implicits {
  implicit class RichKafkaConsumer[A, B](consumer: KafkaConsumer[A, B]) {
    def subscribe(topic: String): Unit = {
      consumer.subscribe(Seq(topic))
    }

    def subscribe(topics: Seq[String]): Unit = {
      consumer.subscribe(topics.asJava)
    }
  }
}
