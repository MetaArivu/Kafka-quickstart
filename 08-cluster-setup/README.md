# Kafka & Zookeeper 

## Kafka Setup Architecture

We will setup below Kafka Architecture 
![WhatsApp Image 2021-11-03 at 10 52 44 AM](https://user-images.githubusercontent.com/23295769/140013322-c9720806-d1a8-429e-9793-d94cc468385e.jpeg)

# Steps to follow
- Setup 3 EC2 Instance, [Link](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/01-aws-ec2-instance-setup.md)
- Quorum Zookeeper Setup, [Link1](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/02-zookeeper-setup.md) & [Link2](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/03-zookeeper-quorum-setup.md)
- Zookeeper Navigator tool, [Link](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/04-zookeeper-tools.md)
- EBS Volumne Mapping For Kafka Broker, [Link](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/05-kafka-aws-ebs-volumne-setup.md)
- Kafka Broker Setup, [Link](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/06-kafka-broker-setup.md)
- Testing Kafka, [Link](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/07-testing-kafka-cluster.md)
- Monitoring Tools, [Link](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/08-monitoring-tools.md)

## While setting Zookeeper & Kafka consider below steps

## Zookeeper

### It provides various features for various distrubuted Application like
- Distributed Configuration
- Self election
- Coordination
- Key value store
- It has internal Tree Structure
    - Each node is called zNode
    - zNode are persistance
    - zNode has path
    - zNode can be watched for changes

### Roles
- Every broker rregister itself by sending heartbeats
- Maintains the list of Topic along with other Topic details like Partitions, Replication factor etc
- Election of leader if one broker goes down
- Stores the Kafka Cluster-Id

### Zookeeper Quorum Sizing, No of zookeeper server 2N+1 (1,3,5,7..). Ideally 3 should be good.


### Zookeeper/Kafka Performance
- Use FAST Disk e.g SSD
- No RAM Swap
- Set Snapshot and Log on seperate Disk
- High performance Network
- Setup good number of zookeeper Server
- On Cloud setup Zookeeper in same region
- Setup Zookeeper and Kafka on seperate machines







