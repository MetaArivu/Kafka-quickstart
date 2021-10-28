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

- Push Customer Details 
    -  kafka-console-producer --topic customer-details --broker-list localhost:9092 --property parse.key=true --property key.separator=":"
    - Sample Data
        - cust-123:{"customerId":"cust-123","customerName":"John Doe","addrress":{"label":"Home","address1":"E205, Park Street","address2":"East Road","city":"Pune","province":"MH","county":"IN","postalCode":"411029"}}

- Push Order Details 
    -  kafka-console-producer --topic order-details --broker-list localhost:9092 --property parse.key=true --property key.separator=":"
    - Sample Data
        - bff924f9-0837-4ab0-951b-8c397fd9c6d4:{"id":"bff924f9-0837-4ab0-951b-8c397fd9c6d4","customerId":"cust-123","date":1635399776069,"lineItems":[{"itemId":"item-1","item":"IPhone 13","qty":1,"price":130000,"total":130000},{"itemId":"item-1","item":"MacBook Pro 15","qty":1,"price":230000,"total":230000},{"itemId":"item-1","item":"Airpod","qty":1,"price":24000,"total":24000}],"addrress":{"label":"Home","address1":"E205, East Street Park","address2":"MG Road","city":"Pune","province":"MH","county":"India","postalCode":"411028"},"total":384000}

- Check Application LOG, you will be able to see ORDER details is enhanced with Customer Address



