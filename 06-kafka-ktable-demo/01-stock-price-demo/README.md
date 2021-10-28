# Stock Price KTable Example

- Create Topic called "stock-tick-topic"
    - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic stock-tick-topic

- Produce the message
    - kafka-console-producer --topic stock-tick-topic --broker-list localhost:9092 --property parse.key=true --property key.separator=":"
    - Publish Sample Data, Here key is Stock of Company and Value is Stock Price
        - HDFCBANK:1120
        - HDFCBANK:1150
        - TCS:3920
        - RIL:3300
        - ICICIBANK:860
        - HDFCBANK:2050
        - HDFC:4100
        - ASIANPAINTS:2283
        - HDFCBANK:1180

- Check application log you will be able to see logs with latest price of Stock.