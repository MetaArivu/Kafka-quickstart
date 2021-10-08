# Schema Registry & AVRO
This section focus on how to enable usage of Confluent Schema Registry and Avro serialization format in your Spring Boot applications.

- https://www.confluent.io/product/confluent-platform/data-compatibility/
- https://avro.apache.org/docs/1.9.2/gettingstartedjava.html

Using Avro schemas with schema registry, you can establish a data contract between your microservices applications which communicate over kafka.

- AVRO Event schema can be found [here](https://github.com/MetaArivu/spring-kaka-examples/blob/main/schema-registry-with-avro/kafka-producer-avro/src/main/resources/avro/user-event.avsc)

- You can generate schema using "mvn clean install"

- Check the AVRO [producer](https://github.com/MetaArivu/spring-kaka-examples/blob/main/schema-registry-with-avro/kafka-producer-avro/src/main/java/com/kafka/producer/config/KafkaConfig.java) and [consumer](https://github.com/MetaArivu/spring-kaka-examples/blob/main/schema-registry-with-avro/kafka-consumer-avro/src/main/java/com/kafka/consumer/config/KafkaConfig.java) configuration

- Start Producer and publish event to kafka using below curl command

curl -X POST
http://localhost:8081/api/v1/publish/simple
-H 'cache-control: no-cache'
-H 'content-type: application/json'
-H 'postman-token: f9eb6205-3c22-5427-3be1-8f342dda0e45'
-d '{ "firstName":"John", "lastName":"Doe", "email":"John.Doe@gmail.com" }'

- When event is published you can validate the schema in Coonfluent Schema Registry which is running locally

<img width="1675" alt="Screen Shot 2021-10-08 at 10 00 58 PM" src="https://user-images.githubusercontent.com/23295769/136591809-979764c7-4e62-4f70-ad8b-36bff4c1eeec.png">






