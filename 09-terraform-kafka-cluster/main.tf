

###########
# Instances
###########

resource "aws_instance" "arivu-aws-kafka" {
  ami                    = "${var.instance_ami}"
  instance_type          = "${var.instance_type}"
  subnet_id              = "${var.subnet_ids}"
  vpc_security_group_ids = ["${aws_security_group.arivu-aws-kafka-sg.id}"]
  provisioner "file" {
    source      = "zookeeper.properties"
    destination = "/tmp/zookeeper.properties"
  }

  provisioner "file" {
    source      = "server.properties"
    destination = "/tmp/server.properties"
  }
  provisioner "file" {
    source      = "kafka-master.sh"
    destination = "/tmp/kafka-master.sh"
  }
  
  provisioner "remote-exec" {
        inline = [
            "chmod +x /tmp/kafka-master.sh",
            "/tmp/kafka-master.sh"
]                 
    }
  tags = {
    Name = "arivu-aws-kafka"
  }

    key_name = "${aws_key_pair.kafka-key-pair.id}"

  connection {
      agent = true
      host = self.public_ip
      user = "${var.EC2_USER}"

  }
}

################
# Security Group
################

resource "aws_security_group" "arivu-aws-kafka-sg" {
  name   = "arivu-aws-kafka-sg"
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
    Name = "arivu-aws-kafka-sg"
  }
}

resource "aws_key_pair" "kafka-key-pair" {
    key_name = "kafka-key-pair"
    public_key = "${file(var.PUBLIC_KEY_PATH)}"
}

resource "null_resource" "kafka-master" {
   connection {
    host = aws_instance.arivu-aws-kafka.public_ip
    agent = true
    user = "${var.EC2_USER}"
  }

   provisioner "remote-exec" {
     inline = [
            "sh /Softwares/kafka_2.11-2.4.1/bin/zkstart.sh",
            "ps aux|grep kafka"
]      
    
  }
    depends_on = [aws_instance.arivu-aws-kafka, aws_route53_record.arivu-route-53-record-1]

  
}

