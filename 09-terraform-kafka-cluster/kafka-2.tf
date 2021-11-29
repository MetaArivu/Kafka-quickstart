

###########
# Instances
###########

resource "aws_instance" "arivu-aws-kafka-2" {
  ami                    = "${var.instance_ami}"
  instance_type          = "${var.instance_type}"
  subnet_id              = "${var.subnet_ids}"
  vpc_security_group_ids = ["${aws_security_group.arivu-aws-kafka2-sg.id}"]
  provisioner "file" {
    source      = "zookeeper2.properties"
    destination = "/tmp/zookeeper2.properties"
  }

  provisioner "file" {
    source      = "server2.properties"
    destination = "/tmp/server2.properties"
  }
  provisioner "file" {
    source      = "kafka-node2.sh"
    destination = "/tmp/kafka-node2.sh"
  }
  
  provisioner "remote-exec" {
        inline = [
            "chmod +x /tmp/kafka-node2.sh",
            "/tmp/kafka-node2.sh"
]                 
    }
  tags = {
    Name = "arivu-aws-kafka-2"
  }

    key_name = "${aws_key_pair.kafka2-key-pair.id}"

  connection {
      agent = true
      host = self.public_ip
      user = "${var.EC2_USER}"

  }
}

################
# Security Group
################

resource "aws_security_group" "arivu-aws-kafka2-sg" {
  name   = "arivu-aws-kafka2-sg"
  vpc_id = "${var.vpc_id}"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "TCP"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 9092
    to_port     = 9092
    protocol    = "TCP"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 2181
    to_port     = 2181
    protocol    = "TCP"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 2888
    to_port     = 3888
    protocol    = "TCP"
    cidr_blocks = ["0.0.0.0/0"]
  }


  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "arivu-aws-kafka2-sg"
  }
}

resource "aws_key_pair" "kafka2-key-pair" {
    key_name = "kafka2-key-pair"
    public_key = "${file(var.PUBLIC_KEY_PATH_2)}"
}

resource "null_resource" "kafka-node2" {
   connection {
    host = aws_instance.arivu-aws-kafka-2.public_ip
    agent = true
    user = "${var.EC2_USER}"
  }

   provisioner "remote-exec" {
     inline = [
            "sh /Softwares/kafka_2.11-2.4.1/bin/zkstart2.sh",
            "ps aux|grep kafka"
]      
    
  }
    depends_on = [aws_instance.arivu-aws-kafka, aws_instance.arivu-aws-kafka-2, aws_route53_record.arivu-route-53-record-2]

  
}

