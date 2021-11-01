# Kafka Examples Using Spring

This tutorial focus of different features of KAKFA

## 1: [Kafka Setup](https://github.com/MetaArivu/spring-kaka-examples/tree/main/01-kafka-setup)
   - Kafka Setup
   - Kafka SSL Configuration 

## 2: [Kafka Producer](https://github.com/MetaArivu/spring-kaka-examples/tree/main/02-spring-kafka-producer)
  This demo focus on following feature
- 1 Asynchronous Simple Event Publisher
- 2 Event Publisher with key, this will  make sure event with same key goes to same partition
- 3 Event Publisher with callback method
- 4 Publish event with headers
- 5 Publish event in synchronous way
- 6 How to use embeded kafka for unit testing

## 3: [Kafka Consumer](https://github.com/MetaArivu/spring-kaka-examples/tree/main/03-spring-kafka-consumer)
This Deemo Focus on following features
- 1 Simple event consumer
- 2 Event consumer with consumer rercord, this will give you more information of message like Key, Partition, Offset etc
- 3 Event consumer with header, this will give you all the standard header and custom header information
- 4 How to handle exception in generic way
- 5 Manual Acknowledgement 
- 6 Concurrent Message Listener
- 7 Retry

## 4: [Schema Registry & AVRO](https://github.com/MetaArivu/spring-kaka-examples/tree/main/04-schema-registry-with-avro)
This section focus on how to enable usage of Confluent Schema Registry and Avro serialization format in your Spring Boot applications.

## 5: KStream & KTable

<img width="1127" alt="screen shot 2018-05-18 at 10 47 07 am" src="https://user-images.githubusercontent.com/23295769/40217157-e59d4812-5a88-11e8-8f9f-dc7ab0d04930.png">

## 5.1: [KStream](https://github.com/MetaArivu/spring-kaka-examples/tree/main/05-kafka-streams-demo)
This section focus on how to use KStream
- 1 Working with KStream
- 2 Implementing Exactly Once Pattern
- 3 Handling Business Error
- 4 Branching
- 5 Reducing
- 6 Aggregation
- 7 Joining KStreams and Global Table

## 5.2: [KTable](https://github.com/MetaArivu/spring-kaka-examples/tree/main/06-kafka-ktable-demo)
This section focus on how to use KTable
- 1 Working with Ktable
- 2 Aggregation
- 3 Reducing
- 4 Global Table

## 6: [CQRS and EventSourcing](https://github.com/MetaArivu/spring-kaka-examples/tree/main/07-shopping-cart-cqrs-es)
This section focus on implementing Event Sourcing & CQRS using Kafka KStream & KTable. Here we have build small Shopping Cart Service functionality. 

![WhatsApp Image 2021-10-28 at 4 16 30 PM](https://user-images.githubusercontent.com/23295769/139241202-d8ef26b8-86f6-484a-908b-038fda1a70fd.jpeg)

## 7: [Kafka Cluster Setup](https://github.com/MetaArivu/Kafka-examples/tree/main/08-cluster-setup)
This section focus on setting Kafka Cluster Setup. In this demo we will focus on setting 3 zookeeper with 3 broker setup.

## License  

Copyright © [MetaMagic Global Inc](http://www.metamagicglobal.com/), 2021-22.  All rights reserved.

Licensed under the Apache 2.0 License.

**Enjoy!**
