# Region

variable "region" {
  description = "The AWS region to create things in"
  default     = "us-east-2"
}




# Ubuntu 16.04 LTS

variable "instance_ami" {
  description = "ubuntu"
  default     = "ami-00399ec92321828f5"

}



variable "instance_type" {
  description = "Instance AWS type"
  default     = "t2.medium"
}



# Key Pair Name
variable "PRIVATE_KEY_PATH" {
  default = "kafka-key-pair"
}

variable "PUBLIC_KEY_PATH" {
  default = "kafka-key-pair.pub"
}

variable "PRIVATE_KEY_PATH_2" {
  default = "kafka2-key-pair"
}

variable "PUBLIC_KEY_PATH_2" {
  default = "kafka2-key-pair.pub"
}

variable "PRIVATE_KEY_PATH_3" {
  default = "kafka3-key-pair"
}

variable "PUBLIC_KEY_PATH_3" {
  default = "kafka3-key-pair.pub"
}
# Vpc

variable "vpc_id" {
  default     = "vpc-069dd3f23d5ca1b76"
}

# Subnet
variable "subnet_ids" {
  default = "subnet-0027a5c84d51ac332"
}

# Security Group

variable "cidr_blocks" {
  default     = "0.0.0.0/0"
  description = "CIDR for sg"
}


## user name
variable "EC2_USER" {
  default = "ubuntu"
}
