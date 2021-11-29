#!/bin/bash -v
## installing Java and kafka
sudo apt-get -y update
sudo apt-get -y install openjdk-8-jdk

sudo mkdir /Softwares/
sudo chown -R ubuntu:ubuntu /Softwares/
sudo chmod -R 777 /Softwares/
cd /Softwares/
git clone https://github.com/meta-magic/jdk.git


cp -rf jdk/kafka_2.11-2.4.1 .


mkdir zoo_data
echo "3" > /Softwares/zoo_data/myid

mkdir kafka_broker

rm -rf jdk/

## exporting java path 
cat >> /home/ubuntu/.profile <<EOL
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export JRE_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
export PATH=$PATH:$JAVA_HOME/bin
EOL
cd
. .profile
java -version
echo "$JAVA_HOME"
# Add custom zookeeper and kafka configuration
 rm -rf /Softwares/kafka_2.11-2.4.1/config/server.properties
 rm -rf /Softwares/kafka_2.11-2.4.1/config/zookeeper.properties
 cp -rf /tmp/zookeeper3.properties /Softwares/kafka_2.11-2.4.1/config/
 cp -rf /tmp/server3.properties /Softwares/kafka_2.11-2.4.1/config/
