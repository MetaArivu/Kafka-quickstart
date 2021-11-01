# KAFKA AWS Setup

- As we are going to have 3 broker setup we will create 3 similar instance as we created for zookeeper

- Create 3 EBS volume and attache to above instance respectively

- Update Security Group to allow inbound traffic oon  9092

- Login to 3 instance of KAFKA and mount the drives
    - sudo su
    - lsblk
    - file -s /dev/xvdf
    - apt-get install -y xfsprogs
    - fdisk /dev/xvdf
    - mkfs.xfs -f /dev/xvdf
    - mkdir /data/kafka
    - mount -t xfs /dev/xvdf /data/kafka
    - chown -R ubuntu:ubuntu /data/kafka
    - df -h /data/kafka
    - cp /etc/fstab /etc/fstab.bak # backup
    - echo '/dev/xvdf /data/kafka xfs defaults 0 0' >> /etc/fstab
    - reboot
    - sudo service zookeeper start



