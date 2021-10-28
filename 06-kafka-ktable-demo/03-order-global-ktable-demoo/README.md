# GlobaleKTable 

Please go through below links

- http://timvanlaer.be/2017-06-28/working-with-globalktables/
- https://supergloo.com/kafka-streams/globalktable-vs-ktable-in-kafka-streams/
- https://kafka.apache.org/20/documentation/streams/developer-guide/dsl-api.html#join-co-partitioning-requirements


This demo focus on enriching of the order data, our order data has customer id, lineitems but downstream application requires more data like customer name and customer address also. 

We will bring all customer data to customer-details topic, which we will be using as GlobalKTable too enrich the order data. i.e once order is received customer data will be joined based on order customer-id.

- Create two topics order-details & customer-details
     - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-details
      - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic customer-details

