# Working with KStream

This demo focus of how you can configure KStream application using Spring. Heree we will configure input stream i.e streaming event from some topic, once event is consumed it will be enriched and will stream to another topic.

For demo we will consume order data which consist of TOTAL amount, based on TOTAL amount loyalty points will be calculated and it will be stream to another topic.

- Create two topics "order-input-topic", "order-output-topic"
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-input-topic
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-output-topic

- Start the application
  - mvn spring-boot:run

- Produce data on order-input-topic topic
  - kafka-console-producer --broker-list localhost:9092 --topic order-input-topic --property parse.key=true --property key.separator=":"
  - Enter below sample data
    - ord-123:{"orderId":"ord-123","total":"2200"}
    - ord-1234:{"orderId":"ord-1234","total":"4340"}

- Check Application log, you should be able to see received data and new streamed data 

- Check order-output-topic



