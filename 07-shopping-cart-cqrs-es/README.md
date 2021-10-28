# Event Sourcing & CQRS Using Kafka

This section focus on implementing Event Sourcing & CQRS using Kafka KStream & KTable. 

Here we have build small Shopping Cart Service functionality, it will be divided in 3 modules
- 1 - Shopping Cart Write API, this API's are called by user when he adds, remove to cart or checkout carts. Based on API called ITEM ADDED, ITEM REMOVED or CHECKOUT event will be published to KAFKA on cart-event topic.
- 2 - Shopping Cart Aggregator, this module uses KSTREAM and GLOBALTABLE. It will consume all the events from cart-event and aggregate it, once done new state will be generted and push to another topic.
- 3 - Shopping CART Read API, this API reads the data READ DB and shows latest state of User Cart.

![WhatsApp Image 2021-10-28 at 4 16 30 PM](https://user-images.githubusercontent.com/23295769/139241202-d8ef26b8-86f6-484a-908b-038fda1a70fd.jpeg)

Note: DB Connector PART is WIP.

Demo:
- Create cart-events, product-details-events & cart-details-event topics
    - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic cart-events 
    - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic product-details-events
    - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic cart-details-event


- Push Products details to Kafka
    - Note: We are doing this as DB connector are WIP
    - kafka-console-producer --topic product-details-events --broker-list localhost:9092 --property parse.key=true --property key.separator=":"
    - Sample Data
        - 1:{"productId":"1","productName":"IPhone 12","price":"65000","stockQty":768}
        - 2:{"productId":"2","productName":"IPhone 13","price":"125000","stockQty":230}

- Start Shopping Cart Write
    - mvn spring-boot:run

- Call API where user can add/remove item from cart
    - 1 - curl -X POST \
  http://localhost:8080/api/v1/add \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ac24a5e8-80da-bb5f-e091-45a55f5f854a' \
  -d '{
	"customerId":"123",
	"itemId": "1",
	"qty":1
}'
    - 2 - Send above request multiple time and then change the itemId to 2 and send similar request
    - 3 - To Remove Item from cart, curl -X POST \
  http://localhost:8080/api/v1/remove \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: b1afcd58-672f-4758-5ed7-1965c759222f' \
  -d '{
	"customerId":"123",
	"itemId": "2",
	"qty":1
}'

- Start Shopping Cart Aggregator
    - mvn spring-boot:run 
    - Once application is started, it will consume event published and will generate new state and publish new event on cart-details-event

- Start Shopping Cart Read
    - mvn spring-boot:run
    - To get latest state of Shopping Cart use,
    curl -X GET \
  http://localhost:7000/api/v1/123 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 93ae3fc2-fd95-763c-5912-783d125bbad7'