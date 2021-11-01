# Zookeeper setup on AWS

 - Note down the subnet for your region, my is as below

 <img width="1342" alt="Screen Shot 2021-11-01 at 12 32 55 PM" src="https://user-images.githubusercontent.com/23295769/139635736-e88de887-64dc-45b2-89ad-ad2920795cbf.png">

 - Create EC2 Instance with "Ubuntu Server 20.04 LTS (HVM), SSD Volume Type"

 - t2 Medium

 - Select "ap-south-1b" and private  IP as 172.31.9.1 instead of Auto Assign

 - On storage section deselect "Delete on termission"

 - Create new security group with followin details

 <img width="1674" alt="Screen Shot 2021-11-01 at 12 56 26 PM" src="https://user-images.githubusercontent.com/23295769/139636442-e97d382e-4fd6-4977-9873-2d2d516e64b2.png">

- Launc the address

- Note: Goto to network interfaces and un-select the change termination behaviour. This will make sure you don't loose the IP assigned.



