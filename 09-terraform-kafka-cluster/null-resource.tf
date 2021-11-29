resource "null_resource" "arviu-kafka-1" {
   connection {
    host = aws_instance.arivu-aws-kafka.public_ip
    agent = true
    user = "${var.EC2_USER}"
  }

   provisioner "remote-exec" {
     inline = [
            "sh /Softwares/kafka_2.11-2.4.1/bin/kkstart.sh",
            "ps aux|grep kafka"
]      
    
  }
    depends_on = [null_resource.kafka-node2, null_resource.kafka-node3, null_resource.kafka-master, aws_route53_record.arivu-route-53-record-1, aws_route53_record.arivu-route-53-record-2 ]

  
}

resource "null_resource" "arviu-kafka-2" {
   connection {
    host = aws_instance.arivu-aws-kafka-2.public_ip
    agent = true
    user = "${var.EC2_USER}"
  }

   provisioner "remote-exec" {
     inline = [
            "sh /Softwares/kafka_2.11-2.4.1/bin/kkstart2.sh",
            "ps aux|grep kafka"
]      
    
  }
   depends_on = [null_resource.kafka-node2, null_resource.kafka-node3, null_resource.kafka-master, aws_route53_record.arivu-route-53-record-1, aws_route53_record.arivu-route-53-record-2 ]

  
}




resource "null_resource" "arviu-kafka-3" {
   connection {
    host = aws_instance.arivu-aws-kafka-3.public_ip
    agent = true
    user = "${var.EC2_USER}"
  }

   provisioner "remote-exec" {
     inline = [
            "sh /Softwares/kafka_2.11-2.4.1/bin/kkstart3.sh",
            "ps aux|grep kafka"
]      
    
  }
    depends_on = [null_resource.kafka-node2, null_resource.kafka-node3, null_resource.kafka-master, aws_route53_record.arivu-route-53-record-1, aws_route53_record.arivu-route-53-record-2 ]

  
}