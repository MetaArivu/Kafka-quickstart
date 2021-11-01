# Zookeeper Setup

- sudo apt-get update && \
      sudo apt-get -y install wget ca-certificates zip net-tools vim nano tar netcat

- sudo apt-get -y install openjdk-8-jdk

- sudo sysctl vm.swappiness=1

- echo 'vm.swappiness=1' | sudo tee --append /etc/sysctl.conf

- echo "172.31.9.1 kafka1
172.31.9.1 zookeeper1
172.31.35.20 kafka2
172.31.35.20 zookeeper2" | sudo tee --append /etc/hosts

- Download and Setup
    - wget https://archive.apache.org/dist/kafka/0.10.2.1/kafka_2.12-0.10.2.1.tgz
    - tar -xvzf kafka_2.12-0.10.2.1.tgz
    - rm kafka_2.12-0.10.2.1.tgz
    - mv kafka_2.12-0.10.2.1 kafka
    - cd kafka/


- Install Zookeeper boot scripts
    - sudo nano /etc/init.d/zookeeper
    - copy content from zookeeper-service and save it
    - sudo chmod +x /etc/init.d/zookeeper
    - sudo chown root:root /etc/init.d/zookeeper
    - sudo update-rc.d zookeeper defaults

- Start Zookeeper
    - sudo service zookeeper start
    - nc -vz localhost 2181 OR echo "ruok" | nc localhost 2181 ; echo OR cat logs/zookeeper.out


- Find handy four letter commands [here](https://zookeeper.apache.org/doc/r3.4.8/zookeeperAdmin.html#sc_zkCommands)


