# Kafka Broker Setup

- Configure open file descriptors
    - echo "* hard nofile 100000 * soft nofile 100000" | sudo tee --append /etc/security/limits.conf

- "reboot" machine soo above configuration is pickedup

- Copy kafka-server.properties from repo in  ~/kafka/config/server.properties
    - change advertise hostname and id accordingly on server

- Configure kafka as service
    - sudo nano /etc/init.d/kafka
    - copy the content from [kafka-service](https://github.com/MetaArivu/Kafka-examples/blob/main/08-cluster-setup/kafka-service)
    - sudo chmod +x /etc/init.d/kafka
    - sudo chown root:root /etc/init.d/kafka
    - sudo update-rc.d kafka defaults

- Start kafka
    - sudo service kafka start

- Verify it's working
    - nc -vz localhost 9092
    - look at the server logs "tail -f /home/ubuntu/kafka/logs/server.log"

- Repeat above steps on 2 other instance

- Once all the broker are started login to Zookeeper Navvigator, you will be able to see new kafka configuration folder as below
    - <img width="1679" alt="Screen Shot 2021-11-01 at 4 18 49 PM" src="https://user-images.githubusercontent.com/23295769/139660361-513e0598-2945-44b4-9d82-afdd94caf2f6.png">





