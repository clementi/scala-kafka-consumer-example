import java.time.Duration

import Implicits._
import org.apache.kafka.clients.consumer.KafkaConsumer

object Program extends App {
  val consumer = createConsumer[Long, String]
  consumer.subscribe("demo")

  while (true) {
    val consumerRecords = consumer.poll(Duration.ofMillis(1000))
    consumerRecords.forEach { record =>
      println("-------------------------")
      println(s"Key:       ${record.key}")
      println(s"Value:     ${record.value}")
      println(s"Partition: ${record.partition}")
      println(s"Offset:    ${record.offset}")
    }
    consumer.commitAsync()
  }
  consumer.close()

  private def createConsumer[A, B]: KafkaConsumer[A, B] = {
    new KafkaConsumer[A, B](PropertiesFactory.getProperties)
  }
}
