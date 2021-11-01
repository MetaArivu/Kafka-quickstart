# Zookeeper Navigator

- sudo apt-get update

- sudo apt-get install -y apt-transport-https  ca-certificates  curl  software-properties-common

- curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

- sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

- sudo apt-get update
- sudo apt-get install -y docker-ce docker-compose

- sudo usermod -aG docker $(whoami)

- sudoo docker run hello-world

- Update Host Files
echo "172.31.9.1 kafka1
172.31.9.1 zookeeper1
172.31.35.20 kafka2
172.31.35.20 zookeeper2" | sudo tee --append /etc/hosts

- create new file "" and copy cotent from zoonavigator-docker-compose.yml 

- docker-compose -f zoonavigator-docker-compose.yml up -d

- Once it started get instance publich IP and access the ZooNavigator
    - http://13.233.59.6:8001/
    - enter "zookeeper1:2181,zookeeper3:2181" in connection string and proceed
    - Once you are in editor u can login and see the nodes which are conneted to zookeeper
    - <img width="1680" alt="Screen Shot 2021-11-01 at 3 27 58 PM" src="https://user-images.githubusercontent.com/23295769/139654554-55040a16-3f7c-49c5-8f10-6d6b97dd3f4d.png">

