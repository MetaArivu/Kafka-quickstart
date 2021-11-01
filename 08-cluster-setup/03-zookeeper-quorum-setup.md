# Zookeeper Quorum Setup

- Create the AMI image of insatance which is created in previious step (03-zookeeper-setup.md)

- Follow similar steps as 02-aws-setup.md only assign below private IP
    - 172.31.25.20 for **-1a subnet
    - use secutiry group which was create previously

- Login to new instane and check network connection i.e see if you are able to connecct to other instance zookeeper
    - nc -vz zookeeper2 2181

- Create Data Folder
    - sudo mkdir -p /data/zookeeper
    - sudo chown -R ubuntu:ubuntu /data/
    - echo "1" > /data/zookeeper/myid OR echo "3" > /data/zookeeper/myid

- Stop the zookeeper 
    - sudo service zookeeper stop

- Configure the zookeeper
    - Copy Zookeeper config file from repo, it has required configuration
    - Start the zookeeper & check it is properly started


