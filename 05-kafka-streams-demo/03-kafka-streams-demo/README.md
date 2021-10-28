# Advance feeatures of KStream

Previously we seen how to work with KStream. In this demo we will focus on some advance feature like 
- How to send data to another Topic without defining OutPut Channel in YML
- Group the data based on some Key and apply Reduce. In Simple Stream demo we calculate Points for that order only. In this example we will group the data based on customer and calculate total points for that customer along with total points for that order.
- Handling incorrect event data. For example Order is not having Address Or Zero Items, it will be send to ERROR Topic
- Branch the data. For example if Order address in INDIA then send Order to INDIA Topic else send to INTERNATIONAL Topic


- Create two topics "order-input-topic", "order-output-topic"
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-details
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic india-order-details
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic international-order-details
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic loyalty-topic

- Start the application
  - mvn spring-boot:run

- Example 1: Stream data too another topic without configuring OutPut-Channel
  - Produce data on order-details topic
  - kafka-console-producer --broker-list localhost:9092 --topic order-details --property parse.key=true --property key.separator=":"
  - Enter below sample data
    - bff924f9-0837-4ab0-951b-8c397fd9c6d4:{"id":"bff924f9-0837-4ab0-951b-8c397fd9c6d4","customerId":"79b96745-e9ca-40f6-b867-62ab96195c98","date":1635399776069,"lineItems":[{"itemId":"item-1","item":"IPhone 13","qty":1,"price":130000,"total":130000},{"itemId":"item-1","item":"MacBook Pro 15","qty":1,"price":230000,"total":230000},{"itemId":"item-1","item":"Airpod","qty":1,"price":24000,"total":24000}],"addrress":{"label":"Home","address1":"E205, East Street Park","address2":"MG Road","city":"Pune","province":"MH","county":"India","postalCode":"411028"},"total":384000}
   
- Example 2: Grouping of data based on Customer ID and then Reducing it to calculate total loyalty point earned
  - Produce data on order-details topic 
  - Note: To Run this uncomment commented method from [ORDER-Service](https://github.com/MetaArivu/spring-kaka-examples/blob/main/05-kafka-streams-demo/03-kafka-streams-demo/src/main/java/com/streams/service/OrderService.java)
  - kafka-console-producer --broker-list localhost:9092 --topic order-details --property parse.key=true --property key.separator=":"
  - Enter below sample data
    - bff924f9-0837-4ab0-951b-8c397fd9c6d4:{"id":"bff924f9-0837-4ab0-951b-8c397fd9c6d4","customerId":"79b96745-e9ca-40f6-b867-62ab96195c98","date":1635399776069,"lineItems":[{"itemId":"item-1","item":"IPhone 13","qty":1,"price":130000,"total":130000},{"itemId":"item-1","item":"MacBook Pro 15","qty":1,"price":230000,"total":230000},{"itemId":"item-1","item":"Airpod","qty":1,"price":24000,"total":24000}],"addrress":{"label":"Home","address1":"E205, East Street Park","address2":"MG Road","city":"Pune","province":"MH","county":"India","postalCode":"411028"},"total":384000}
   
- Example 4: Branching of output data, i.e based on country data will be send to different topics
  - kafka-console-producer --broker-list localhost:9092 --topic order-details --property parse.key=true --property key.separator=":"
  - Enter below sample data
    - bff924f9-0837-4ab0-951b-8c397fd9c6d4:{"id":"bff924f9-0837-4ab0-951b-8c397fd9c6d4","customerId":"79b96745-e9ca-40f6-b867-62ab96195c98","date":1635399776069,"lineItems":[{"itemId":"item-1","item":"IPhone 13","qty":1,"price":130000,"total":130000},{"itemId":"item-1","item":"MacBook Pro 15","qty":1,"price":230000,"total":230000},{"itemId":"item-1","item":"Airpod","qty":1,"price":24000,"total":24000}],"addrress":{"label":"Home","address1":"E205, East Street Park","address2":"MG Road","city":"Pune","province":"MH","county":"India","postalCode":"411028"},"total":384000}
    - bff924f9-0837-4ab0-951b-8c397fd9c6d4:{"id":"bff924f9-0837-4ab0-951b-8c397fd9c6d4","customerId":"79b96745-e9ca-40f6-b867-62ab96195c98","date":1635399776069,"lineItems":[{"itemId":"item-1","item":"IPhone 13","qty":1,"price":130000,"total":130000},{"itemId":"item-1","item":"MacBook Pro 15","qty":1,"price":230000,"total":230000},{"itemId":"item-1","item":"Airpod","qty":1,"price":24000,"total":24000}],"addrress":{"label":"Home","address1":"E205, East Street Park","address2":"MG Road","city":"Pune","province":"MH","county":"US","postalCode":"411028"},"total":384000}
  - First message will be streamed on india-order-details topic and second message will be streamed to international-order-details topic. Branching is happening based on incoming order details country attribute.

