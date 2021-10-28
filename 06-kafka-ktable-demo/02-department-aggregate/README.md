# Grouping and Aggregating Data using KTABLE

In this example we will Group the Employee data using department-id and then calculate total salary and average salary of the department.

- Create Topic called "employee-topic"
    - kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic employee-topic

- Produce the message
    - kafka-console-producer --topic employee-topic --broker-list localhost:9092 --property parse.key=true --property key.separator=":"
    - Publish Sample Data, Here key is Employee Id of and Value Employee JSON
        - 1:{"empId": "1", "name": "John", "deptId": "engineering", "salary": 5000}
        - 2:{"empId": "2", "name": "Jane", "deptId": "accounts", "salary": 8000}
        - 3:{"empId": "3", "name": "Virat", "deptId": "engineering", "salary": 3000}
        - 4:{"empId": "4", "name": "Sachin", "deptId": "support", "salary": 7000}
        - 5:{"empId": "5", "name": "Saurab", "deptId": "dev", "salary": 6000}
    - You will be able to see total salary and average salary for engineering, accounts, support & dev departmeent
    - Publish Some more Sample Data, Here key is Employee Id of and Value Employee JSON
        - 1:{"empId": "1", "name": "John", "deptId": "support", "salary": 5000}
        - 4:{"empId": "4", "name": "Melinda", "deptId": "engineering", "salary": 7000}
    - You will be able to see changed total salary and average salary for engineering, accounts, support & dev departmeent
    - Publish Some more Sample Data, Here key is Employee Id of and Value Employee JSON
        - 1:{"empId": "1", "name": "John", "deptId": "support", "salary": 6000}
    - You will be able to see changed total salary and average salary for engineering, accounts, support & dev departmeent


