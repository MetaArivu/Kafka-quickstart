# Kafka Tools

## Install Monitor Tools
- [Zoo Navigator](https://hub.docker.com/r/elkozmon/zoonavigator)
- [Kafka Manager](https://github.com/yahoo/CMAK) OR [Here](https://hub.docker.com/r/hlebalbau/kafka-manager/)
- [Kafka Monitor](https://github.com/linkedin/kafka-monitor)

## Zoo Navigator
Steps for this are provided in [here](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/04-zookeeper-tools.md)

## Kafka Manager
- nano kafka-manager-docker-compose.yml, copy cotent from [here](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/kafka-manager-docker-compose.yml)
- docker-compose -f kafka-manager-docker-compose.yml up -d
- Access tool using public-ip:9000

- <img width="1673" alt="Screen Shot 2021-11-01 at 11 10 00 PM" src="https://user-images.githubusercontent.com/23295769/139715592-d26b5ac7-1a7b-4dfc-a377-911ec707a3a4.png">
- <img width="1645" alt="Screen Shot 2021-11-01 at 11 10 18 PM" src="https://user-images.githubusercontent.com/23295769/139715611-932c4ed0-70d3-4852-abd9-fe230aae2375.png">

## Kafka Monitor
WIP

## Kafka Topic UI
- nano kafka-topics-ui-docker-compose.yml, copy content into this from [here](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/kafka-topics-ui-docker-compose.yml). Make sure you have updated the IP address with your Admin Server public address..
- docker-compose -f  kafka-topics-ui-docker-compose.yml up -d
- Access tool using public-ip:8000





