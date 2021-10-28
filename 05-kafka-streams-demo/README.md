# Grouping Of Data using KStream

In this example we will group the data based on word and produce the count of it.


- Create two topics "order-input-topic", "order-output-topic"
  - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic word-count

- Start the application
  - mvn spring-boot:run

- Produce data on word-count topic
  - Hello world
  - hello world
  - helloworld

- Check the application log based on data entered, words will be grouped and corresponding count will be displayed

