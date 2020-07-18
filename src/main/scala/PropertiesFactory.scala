import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig.{AUTO_OFFSET_RESET_CONFIG, BOOTSTRAP_SERVERS_CONFIG, ENABLE_AUTO_COMMIT_CONFIG, GROUP_ID_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG, MAX_POLL_RECORDS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG}
import org.apache.kafka.common.serialization.{LongDeserializer, StringDeserializer}

object PropertiesFactory {
  def getProperties: Properties = {
    val properties = new Properties

    properties.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(GROUP_ID_CONFIG, "consumerGroup10")
    properties.put(KEY_DESERIALIZER_CLASS_CONFIG, reflect.classTag[LongDeserializer].runtimeClass.getName)
    properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, reflect.classTag[StringDeserializer].runtimeClass.getName)
    properties.put(MAX_POLL_RECORDS_CONFIG, 1)
    properties.put(ENABLE_AUTO_COMMIT_CONFIG, "false")
    properties.put(AUTO_OFFSET_RESET_CONFIG, "earliest")

    properties
  }
}
