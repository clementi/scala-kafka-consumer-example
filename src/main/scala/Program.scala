import java.time.Duration
import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.{LongDeserializer, StringDeserializer}

import scala.collection.JavaConverters._

object Program extends App {
  val consumer = createConsumer[Long, String]

  while (true) {
    val consumerRecords = consumer.poll(Duration.ofMillis(1000))
    consumerRecords.forEach { record =>
      println("-------------------------")
      println(s"Key: ${record.key}")
      println(s"Value: ${record.value}")
      println(s"Partition: ${record.partition}")
      println(s"Offset: ${record.offset}")
    }
    consumer.commitAsync()
  }
  consumer.close()

  private def createConsumer[A, B]: KafkaConsumer[A, B] = {
    val properties = new Properties
    properties.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(GROUP_ID_CONFIG, "consumerGroup10")
    properties.put(KEY_DESERIALIZER_CLASS_CONFIG, reflect.classTag[LongDeserializer].runtimeClass.getName)
    properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, reflect.classTag[StringDeserializer].runtimeClass.getName)
    properties.put(MAX_POLL_RECORDS_CONFIG, 1)
    properties.put(ENABLE_AUTO_COMMIT_CONFIG, "false")
    properties.put(AUTO_OFFSET_RESET_CONFIG, "earliest")

    val consumer = new KafkaConsumer[A, B](properties)
    consumer.subscribe(Seq("demo").asJava)
    consumer
  }
}
